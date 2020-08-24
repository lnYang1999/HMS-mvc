package com.bjtu.edu.service.Impl;

import com.bjtu.edu.dao.StudentDao;
import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.StudentExecution;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.enums.StudentStateEnum;
import com.bjtu.edu.exception.StudentOperationException;
import com.bjtu.edu.service.StudentService;
import com.bjtu.edu.util.ImageUtil;
import com.bjtu.edu.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @project: HMS-mvc
 * @description: StudentService实现类
 * @author: ysp
 * @create: 2020/08/12
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    /**
     * @return java.util.List<com.bjtu.edu.entity.Student>
     * @author: ysp
     * @description: 查询全部教师信息
     * @createTime: 2020/8/15 19:50
     */
    @Override
    public List<Student> getStudentList() {
        return studentDao.queryStudent();
    }

    /**
     * @param studentId
     * @return com.bjtu.edu.entity.Student
     * @author: ysp
     * @description: 通过学生ID获取指定学生信息
     * @createTime: 2020/8/12 22:40
     */
    @Override
    public Student getStudentById(long studentId) {
        return studentDao.queryStudentById(studentId);
    }

    /**
     * @param student
     * @param imageHolder
     * @return com.bjtu.edu.dto.StudentExecution
     * @author: ysp
     * @description: 新增学生信息
     * @createTime: 2020/8/15 19:54
     */
    @Override
    public StudentExecution addStudent(Student student, ImageHolder imageHolder) {
        //空值判断
        if (student == null || student.getStudentId() == null){
            return new StudentExecution(StudentStateEnum.EMPTY);
        }
        try {
            //设置创建时间
            student.setCreateTime(new Date());
            //添加学生信息
            int effectedNum = studentDao.addStudent(student);
            //判断是否添加成功
            if (effectedNum <= 0){
                throw new StudentOperationException("添加学生信息失败");
            } else {
                //如果条件成功，判断是否需要处理图片
                if (imageHolder != null){
                    if (imageHolder.getImage() != null){
                        //存储图片
                        try {
                            //生成图片
                            addStudentImg(student,imageHolder);
                        }catch (Exception e){
                            throw new StudentOperationException("addStudentImg error:" + e.getMessage());
                        }
                        //更新学生头像的图片地址
                        effectedNum = studentDao.modifyStudent(student);
                        //判断是否更新成功
                        if (effectedNum <= 0){
                            throw new StudentOperationException("更新图片地址失败");
                        }
                    }
                }
            }
        }catch (Exception e){
            throw new StudentOperationException("addStudent error:" + e.getMessage());
        }
        return new StudentExecution(StudentStateEnum.SUCCESS,student);
    }

    /**
     * @param student
     * @param imageHolder
     * @return com.bjtu.edu.dto.StudentExecution
     * @author: ysp
     * @description: 修改学生信息
     * @createTime: 2020/8/15 19:55
     */
    @Override
    public StudentExecution modifyStudent(Student student, ImageHolder imageHolder) {
        //空值判断
        if (student == null || student.getStudentId() == null){
            return new StudentExecution(StudentStateEnum.EMPTY);
        }
        try {
            //1.判断是否需要处理图片
            if (imageHolder != null){
                if (imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())){
                    Student tempStudent = studentDao.queryStudentById(student.getStudentId());
                    //如果原路径下有图片存在，则删除原路径下的所有图片
                    if (tempStudent.getProfileImg() != null){
                        ImageUtil.deleteFileOrPath(tempStudent.getProfileImg());
                    }
                    addStudentImg(student, imageHolder);
                }
            }

            //2.修改学生信息
            //设置更新时间
            student.setLastEditTime(new Date());
            //修改学生信息
            int effectedNum = studentDao.modifyStudent(student);
            //判断是否修改成功
            if (effectedNum <= 0){
                return new StudentExecution(StudentStateEnum.INNER_ERROR);
            }else {
                student = studentDao.queryStudentById(student.getStudentId());
                return new StudentExecution(StudentStateEnum.SUCCESS,student);
            }
        }catch (Exception e){
            throw new StudentOperationException("modifyStudentError:" + e.getMessage());
        }
    }

    /**
     * @param studentId
     * @return com.bjtu.edu.dto.StudentExecution
     * @author: ysp
     * @description: 删除指定学生信息
     * @createTime: 2020/8/15 19:56
     */
    @Override
    public StudentExecution deleteStudent(long studentId) {
        // 根据studentId获取原来的图片并删除
        Student student = studentDao.queryStudentById(studentId);
        if (student.getProfileImg() != null){
            ImageUtil.deleteFileOrPath(student.getProfileImg());
        }

        //删除该学生信息
        try {
            int effectedNum = studentDao.deleteStudent(studentId);
            //判断是否删除成功
            if (effectedNum <= 0) {
                throw new StudentOperationException("学生信息删除失败");
            } else {
                return new StudentExecution(StudentStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new StudentOperationException("deleteStudent error:" + e.getMessage());
        }
    }

    private void addStudentImg(Student student, ImageHolder imageHolder) {
        //获取student图片目录的相对值路径
        String dest = PathUtil.getStudentImagePath(student.getStudentId());
        //在相应目录下生成上传的图片，并返回新生成的图片路径
        String studentImgAddr = ImageUtil.generateThumbnail(imageHolder,dest);
        //将新生成的图片路径赋值到student的字段中
        student.setProfileImg(studentImgAddr);
    }
}
