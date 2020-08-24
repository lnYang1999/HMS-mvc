package com.bjtu.edu.service;

import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.TeacherExecution;
import com.bjtu.edu.entity.Teacher;

import java.util.List;

public interface TeacherService {
    /**
     * @author: ysp
     * @description: 查询全部教师信息
     * @createTime: 2020/8/13 17:45
     *
     * @param 
     * @return java.util.List<com.bjtu.edu.entity.Teacher>
     */
    List<Teacher> getTeacherList();
    
    /**
     * @author: ysp
     * @description: 通过教师ID获取指定教师信息
     * @createTime: 2020/8/12 22:49
     *
     * @param teacherId
     * @return com.bjtu.edu.entity.Teacher
     */
    Teacher getTeacherById(long teacherId);

    /**
     * @author: ysp
     * @description: 新增教师信息
     * @createTime: 2020/8/13 21:09
     *
     * @param teacher
     * @param thumbnail
     * @return com.bjtu.edu.dto.TeacherExecution
     */
    TeacherExecution addTeacher(Teacher teacher, ImageHolder thumbnail);

    /**
     * @author: ysp
     * @description: 修改教师信息
     * @createTime: 2020/8/13 21:10
     *
     * @param teacher
     * @param thumbnail
     * @return com.bjtu.edu.dto.TeacherExecution
     */
    TeacherExecution modifyTeacher(Teacher teacher, ImageHolder thumbnail);
    
    /**
     * @author: ysp
     * @description: 删除指定教师信息
     * @createTime: 2020/8/13 18:04
     *
     * @param teacherId
     * @return com.bjtu.edu.dto.TeacherExecution
     */
    TeacherExecution deleteTeacher(long teacherId);
}
