package com.bjtu.edu.dao;

import com.bjtu.edu.entity.Teacher;

import java.util.List;

/**
 * @author: ysp
 * @description: 教师实体类dao层接口
 * @createTime: 2020/8/10 11:23
 */
public interface TeacherDao {
    /**
     * @author: ysp
     * @description: 查询所有教师列表
     * @createTime: 2020/8/13 17:17
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.Teacher>
     */
    List<Teacher> queryTeacher();

    /**
     * @author: ysp
     * @description: 通过teacherId查询指定教师信息
     * @createTime: 2020/8/12 22:06
     *
     * @param teacherId
     * @return com.bjtu.edu.entity.Teacher
     */
    Teacher queryTeacherById(long teacherId);

    /**
     * @author: ysp
     * @description: 新增教师信息
     * @createTime: 2020/8/13 17:18
     *
     * @param teacher
     * @return int
     */
    int addTeacher(Teacher teacher);

    /**
     * @author: ysp
     * @description: 修改教师信息
     * @createTime: 2020/8/13 17:19
     *
     * @param teacher
     * @return int
     */
    int modifyTeacher(Teacher teacher);

    /**
     * @author: ysp
     * @description: 通过teacherId删除指定教师
     * @createTime: 2020/8/13 17:19
     *
     * @param teacherId
     * @return int
     */
    int deleteTeacher(long teacherId);
}
