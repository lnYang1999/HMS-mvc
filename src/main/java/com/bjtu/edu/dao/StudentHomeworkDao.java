package com.bjtu.edu.dao;

import com.bjtu.edu.entity.Homework;
import com.bjtu.edu.entity.StudentHomework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: ysp
 * @description: 学生作业实体类dao层接口
 * @createTime: 2020/8/15 11:23
 */
public interface StudentHomeworkDao {
    /**
     * @author: ysp
     * @description: 查询所有学生作业列表
     * @createTime: 2020/8/18 9:34
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.StudentHomework>
     */
    List<StudentHomework> queryStudentHomework();

    /**
     * @author: ysp
     * @description: 通过studentHomeworkId查询指定学生作业信息
     * @createTime: 2020/8/18 9:34
     *
     * @param studentHomeworkId
     * @return com.bjtu.edu.entity.StudentHomework
     */
    StudentHomework queryStudentHomeworkById(long studentHomeworkId);

    /**
     * @author: ysp
     * @description: 通过courseId查询学生作业信息列表
     * @createTime: 2020/8/21 8:50
     *
     * @param courseId
     * @return java.util.List<com.bjtu.edu.entity.StudentHomework>
     */
    List<StudentHomework> queryStudentHomeworkByCourseId(long courseId);

    /**
     * @author: ysp
     * @description: 通过teacherId查询学生作业信息列表
     * @createTime: 2020/8/21 13:53
     *
     * @param teacherId
     * @return java.util.List<com.bjtu.edu.entity.StudentHomework>
     */
    List<StudentHomework> queryStudentHomeworkByTeacherId(long teacherId);

    /**
     * @author: ysp
     * @description: 查询指定的学生作业信息是否存在
     * @createTime: 2020/8/22 16:48
     *
     * @param studentHomeworkCondition
     * @return int
     */
    int queryStudentHomeworkExist(@Param("studentHomeworkCondition") StudentHomework studentHomeworkCondition);

    /**
     * @author: ysp
     * @description: 通过传入的条件获取指定的学生信息
     * @createTime: 2020/8/22 17:15
     *
     * @param studentHomeworkCondition
     * @return com.bjtu.edu.entity.StudentHomework
     */
    StudentHomework queryStudentHomeworkByCondition(@Param("studentHomeworkCondition") StudentHomework studentHomeworkCondition);

    /**
     * @author: ysp
     * @description: 新增学生作业信息
     * @createTime: 2020/8/18 9:34
     *
     * @param studentHomework
     * @return int
     */
    int addStudentHomework(StudentHomework studentHomework);

    /**
     * @author: ysp
     * @description: 修改学生作业信息
     * @createTime: 2020/8/18 9:35
     *
     * @param studentHomework
     * @return int
     */
    int modifyStudentHomework(StudentHomework studentHomework);

    /**
     * @author: ysp
     * @description: 通过studentHomeworkId删除学生作业信息
     * @createTime: 2020/8/18 9:35
     *
     * @param studentHomeworkId
     * @return int
     */
    int deleteStudentHomework(long studentHomeworkId);
}
