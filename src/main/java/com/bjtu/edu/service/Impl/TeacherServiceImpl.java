package com.bjtu.edu.service.Impl;

import com.bjtu.edu.dao.TeacherDao;
import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.TeacherExecution;
import com.bjtu.edu.entity.Teacher;
import com.bjtu.edu.enums.TeacherStateEnum;
import com.bjtu.edu.exception.TeacherOperationException;
import com.bjtu.edu.service.TeacherService;
import com.bjtu.edu.util.ImageUtil;
import com.bjtu.edu.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @project: HMS-mvc
 * @description: TeacherServiceImpl
 * @author: ysp
 * @create: 2020/08/12
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    /**
     * @return java.util.List<com.bjtu.edu.entity.Teacher>
     * @author: ysp
     * @description: 查询全部教师信息
     * @createTime: 2020/8/13 17:45
     */
    @Override
    public List<Teacher> getTeacherList() {
        return teacherDao.queryTeacher();
    }

    /**
     * @param teacherId
     * @return com.bjtu.edu.entity.Teacher
     * @author: ysp
     * @description: 通过教师ID获取指定教师信息
     * @createTime: 2020/8/12 22:41
     */
    @Override
    public Teacher getTeacherById(long teacherId) {
        return teacherDao.queryTeacherById(teacherId);
    }

    /**
     * @param teacher
     * @return com.bjtu.edu.dto.TeacherExecution
     * @author: ysp
     * @description: 新增教师信息
     * @createTime: 2020/8/13 18:00
     */
    @Override
    public TeacherExecution addTeacher(Teacher teacher, ImageHolder thumbnail) {
        //空值判断
        if (teacher == null || teacher.getTeacherId() == null){
            return new TeacherExecution(TeacherStateEnum.EMPTY);
        }
        try {
            //设置创建时间
            teacher.setCreateTime(new Date());
            //添加教师信息
            int effectedNum = teacherDao.addTeacher(teacher);
            if (effectedNum <= 0){
                throw new TeacherOperationException("添加教师失败");
            } else {
                if (thumbnail.getImage() != null){
                    //存储图片
                    try {
                        addTeacherImg(teacher,thumbnail);
                    }catch (Exception e){
                        throw new TeacherOperationException("addTeacherImg error:" + e.getMessage());
                    }
                    //更新教师头像的图片地址
                    effectedNum = teacherDao.modifyTeacher(teacher);
                    if (effectedNum <= 0){
                        throw new TeacherOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new TeacherOperationException("addTeacher error:" + e.getMessage());
        }
        return new TeacherExecution(TeacherStateEnum.SUCCESS,teacher);
    }

    /**
     * @param teacher
     * @return com.bjtu.edu.dto.TeacherExecution
     * @author: ysp
     * @description: 修改教师信息
     * @createTime: 2020/8/13 18:01
     */
    @Override
    public TeacherExecution modifyTeacher(Teacher teacher, ImageHolder imageHolder) {
        //空值判断
        if (teacher == null || teacher.getTeacherId() == null){
            return new TeacherExecution(TeacherStateEnum.EMPTY);
        }
        try {
            //1.判断是否需要处理图片
            if (imageHolder != null){
                if (imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())){
                    Teacher tempTeacher = teacherDao.queryTeacherById(teacher.getTeacherId());
                    //如果原路径下有图片存在，则删除原路径下的所有图片
                    if (tempTeacher.getProfileImg() != null){
                        ImageUtil.deleteFileOrPath(tempTeacher.getProfileImg());
                    }
                    addTeacherImg(teacher, imageHolder);
                }
            }

            //2.修改教师信息
            //设置更新时间
            teacher.setLastEditTime(new Date());
            //修改教师信息
            int effectedNum = teacherDao.modifyTeacher(teacher);
            //判断是否修改成功
            if (effectedNum <= 0){
                return new TeacherExecution(TeacherStateEnum.INNER_ERROR);
            }else {
                teacher = teacherDao.queryTeacherById(teacher.getTeacherId());
                return new TeacherExecution(TeacherStateEnum.SUCCESS,teacher);
            }
        }catch (Exception e){
            throw new TeacherOperationException("modifyTeacherError:" + e.getMessage());
        }
    }

    /**
     * @param teacherId
     * @return com.bjtu.edu.dto.TeacherExecution
     * @author: ysp
     * @description: 删除指定教师
     * @createTime: 2020/8/13 18:04
     */
    @Override
    public TeacherExecution deleteTeacher(long teacherId) {
        //根据teacherId获取原来的图片并删除
        Teacher teacher = teacherDao.queryTeacherById(teacherId);
        if (teacher.getProfileImg() != null){
            ImageUtil.deleteFileOrPath(teacher.getProfileImg());
        }

        //删除该教师信息
        try {
            int effectedNum = teacherDao.deleteTeacher(teacherId);
            //判断是否删除成功
            if (effectedNum <= 0) {
                throw new TeacherOperationException("教师信息删除失败");
            } else {
                return new TeacherExecution(TeacherStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new TeacherOperationException("deleteTeacher error:" + e.getMessage());
        }
    }

    private void addTeacherImg(Teacher teacher, ImageHolder thumbnail) {
        //获取teacher图片目录的相对值路径
        String dest = PathUtil.getTeacherImagePath(teacher.getTeacherId());
        //在相应目录下生成上传的图片，并返回新生成的图片路径
        String teacherImgAddr = ImageUtil.generateThumbnail(thumbnail,dest);
        //将新生成的图片路径赋值到teacher的字段中
        teacher.setProfileImg(teacherImgAddr);
    }
}
