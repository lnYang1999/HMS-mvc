package com.bjtu.edu.service.Impl;

import com.bjtu.edu.dao.StudentHomeworkDao;
import com.bjtu.edu.dto.FileHolder;
import com.bjtu.edu.dto.StudentHomeworkExecution;
import com.bjtu.edu.entity.StudentHomework;
import com.bjtu.edu.enums.StudentHomeworkStateEnum;
import com.bjtu.edu.exception.StudentHomeworkOperationException;
import com.bjtu.edu.service.StudentHomeworkService;
import com.bjtu.edu.util.FileUtil;
import com.bjtu.edu.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @project: HMS-mvc
 * @description: StudentHomeworkService实现类
 * @author: ysp
 * @create: 2020/08/18
 */
@Service
public class StudentHomeworkServiceImpl implements StudentHomeworkService {
    @Autowired
    private StudentHomeworkDao studentHomeworkDao;

    /**
     * @return java.util.List<com.bjtu.edu.entity.StudentHomework>
     * @author: ysp
     * @description: 查询全部学生作业列表信息
     * @createTime: 2020/8/18 12:38
     */
    @Override
    public List<StudentHomework> getStudentHomeworkList() {
        return studentHomeworkDao.queryStudentHomework();
    }

    /**
     * @param studentHomeworkId
     * @return com.bjtu.edu.entity.StudentHomework
     * @author: ysp
     * @description: 通过学生作业ID获取指定学生作业信息
     * @createTime: 2020/8/18 12:39
     */
    @Override
    public StudentHomework getStudentHomeworkById(long studentHomeworkId) {
        return studentHomeworkDao.queryStudentHomeworkById(studentHomeworkId);
    }

    /**
     * @param courseId
     * @return java.util.List<com.bjtu.edu.entity.StudentHomework>
     * @author: ysp
     * @description: 通过课程ID获取学生作业列表信息
     * @createTime: 2020/8/21 9:03
     */
    @Override
    public List<StudentHomework> getStudentHomeworkByCourseId(long courseId) {
        return studentHomeworkDao.queryStudentHomeworkByCourseId(courseId);
    }

    /**
     * @param teacherId
     * @return java.util.List<com.bjtu.edu.entity.StudentHomework>
     * @author: ysp
     * @description: 通过教师ID获取学生作业列表信息
     * @createTime: 2020/8/21 9:04
     */
    @Override
    public List<StudentHomework> getStudentHomeworkByTeacherId(long teacherId) {
        return studentHomeworkDao.queryStudentHomeworkByTeacherId(teacherId);
    }

    /**
     * @param studentHomeworkCondition
     * @return com.bjtu.edu.entity.StudentHomework
     * @author: ysp
     * @description: 通过传入的信息判断是否存在符合条件的学生作业信息
     * @createTime: 2020/8/22 17:05
     */
    @Override
    public StudentHomework getStudentHomeworkCondition(StudentHomework studentHomeworkCondition) {
        int count = studentHomeworkDao.queryStudentHomeworkExist(studentHomeworkCondition);
        //通过count的值判断是否找到该信息：
        //如果找到，则查询该信息并返回StudentHomework实体类信息
        //如果找不到，返回空值null
        if (count == 1){
            return studentHomeworkDao.queryStudentHomeworkByCondition(studentHomeworkCondition);
        }else {
            return null;
        }
    }

    /**
     * @param studentHomework
     * @param fileHolder
     * @return com.bjtu.edu.dto.StudentHomeworkExecution
     * @author: ysp
     * @description: 新增学生作业信息
     * @createTime: 2020/8/18 12:39
     */
    @Override
    public StudentHomeworkExecution addStudentHomework(StudentHomework studentHomework, FileHolder fileHolder) {
        //空值判断
        if (studentHomework == null ){
            return new StudentHomeworkExecution(StudentHomeworkStateEnum.EMPTY);
        }
        try {
            //设置创建时间
            studentHomework.setCreateTime(new Date());
            //添加学生作业信息
            int effectedNum = studentHomeworkDao.addStudentHomework(studentHomework);
            //判断是否添加成功
            if (effectedNum <= 0){
                throw new StudentHomeworkOperationException("添加学生作业信息失败");
            } else {
                //如果条件成功，判断是否需要处理文件流
                if (fileHolder != null){
                    if (fileHolder.getFile() != null){
                        //存储文件
                        try {
                            //生成文件
                            addStudentHomeworkFile(studentHomework,fileHolder);
                        }catch (Exception e){
                            throw new StudentHomeworkOperationException("addStudentHomeworkFile error:" + e.getMessage());
                        }
                        //更新学生作业的文件地址
                        effectedNum = studentHomeworkDao.modifyStudentHomework(studentHomework);
                        //判断是否更新成功
                        if (effectedNum <= 0){
                            throw new StudentHomeworkOperationException("更新文件地址失败");
                        }
                    }
                }
            }
        }catch (Exception e){
            throw new StudentHomeworkOperationException("addStudentHomework error:" + e.getMessage());
        }
        return new StudentHomeworkExecution(StudentHomeworkStateEnum.SUCCESS,studentHomework);
    }

    /**
     * @param studentHomework
     * @param fileHolder
     * @return com.bjtu.edu.dto.StudentHomeworkExecution
     * @author: ysp
     * @description: 修改学生作业信息
     * @createTime: 2020/8/18 12:39
     */
    @Override
    public StudentHomeworkExecution modifyStudentHomework(StudentHomework studentHomework, FileHolder fileHolder) {
        //空值判断
        if (studentHomework == null || studentHomework.getStudentHomeworkId() == null){
            return new StudentHomeworkExecution(StudentHomeworkStateEnum.EMPTY);
        }
        try {
            //1.判断是否需要处理文件
            if (fileHolder != null){
                if (fileHolder.getFile() != null && fileHolder.getFileName() != null && !"".equals(fileHolder.getFileName())){
                    StudentHomework tempStudentHomework = studentHomeworkDao.queryStudentHomeworkById(studentHomework.getStudentHomeworkId());
                    //如果原路径下有文件存在，则删除原路径下的所有文件
                    if (tempStudentHomework.getSubmitFile() != null){
                        FileUtil.deleteFileOrPath(tempStudentHomework.getSubmitFile());
                    }
                    //生成新的学生作业文件
                    addStudentHomeworkFile(studentHomework, fileHolder);
                }
            }

            //2.修改学生作业信息
            //设置更新时间
            studentHomework.setLastEditTime(new Date());
            //修改学生作业信息
            int effectedNum = studentHomeworkDao.modifyStudentHomework(studentHomework);
            //判断是否修改成功
            if (effectedNum <= 0){
                return new StudentHomeworkExecution(StudentHomeworkStateEnum.INNER_ERROR);
            }else {
                studentHomework = studentHomeworkDao.queryStudentHomeworkById(studentHomework.getStudentHomeworkId());
                return new StudentHomeworkExecution(StudentHomeworkStateEnum.SUCCESS,studentHomework);
            }
        }catch (Exception e){
            throw new StudentHomeworkOperationException("modifyStudentHomeworkError:" + e.getMessage());
        }
    }

    /**
     * @param studentHomeworkId
     * @return com.bjtu.edu.dto.StudentHomeworkExecution
     * @author: ysp
     * @description: 删除指定学生作业信息
     * @createTime: 2020/8/18 12:45
     */
    @Override
    public StudentHomeworkExecution deleteStudentHomework(long studentHomeworkId) {
        // 根据studentHomeworkId获取原来的文件并删除
        StudentHomework studentHomework = studentHomeworkDao.queryStudentHomeworkById(studentHomeworkId);
        if (studentHomework.getSubmitFile() != null){
            FileUtil.deleteFileOrPath(studentHomework.getSubmitFile());
        }

        // 删除该学生作业信息
        try {
            int effectedNum = studentHomeworkDao.deleteStudentHomework(studentHomeworkId);
            //判断是否删除成功
            if (effectedNum <= 0) {
                throw new StudentHomeworkOperationException("学生作业信息删除失败");
            } else {
                return new StudentHomeworkExecution(StudentHomeworkStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new StudentHomeworkOperationException("deleteStudentHomework error:" + e.getMessage());
        }
    }

    private void addStudentHomeworkFile(StudentHomework studentHomework, FileHolder fileHolder) {
        //获取studentHomework文件目录的相对值路径
        String dest = PathUtil.getStudentHomeworkFilePath(studentHomework.getStudentHomeworkId());
        //在相应目录下生成上传的文件，并返回新生成的文件路径
        String studentHomeworkFileAddr = FileUtil.generateNewFile(fileHolder,dest);
        //将新生成的文件路径赋值到studentHomework的字段中
        studentHomework.setSubmitFile(studentHomeworkFileAddr);
    }
}
