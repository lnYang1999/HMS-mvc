package com.bjtu.edu.dao;

import com.bjtu.edu.entity.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: ysp
 * @description: 作业实体类dao层接口
 * @createTime: 2020/8/15 11:23
 */
public interface HomeworkDao {
    /**
     * @author: ysp
     * @description: 查询所有作业列表
     * @createTime: 2020/8/18 9:34
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.Homework>
     */
    List<Homework> queryHomework();

    /**
     * @author: ysp
     * @description: 通过homeworkId查询指定作业信息
     * @createTime: 2020/8/18 9:34
     *
     * @param homeworkId
     * @return com.bjtu.edu.entity.Homework
     */
    Homework queryHomeworkById(long homeworkId);

    /**
     * @author: ysp
     * @description: 通过courseId查询作业信息列表
     * @createTime: 2020/8/18 9:34
     *
     * @param courseId
     * @return com.bjtu.edu.entity.Homework
     */
    List<Homework> queryHomeworkByCourseId(long courseId);

    /**
     * @author: ysp
     * @description: 通过teacherId查询作业信息列表
     * @createTime: 2020/8/21 10:51
     *
     * @param teacherId
     * @return java.util.List<com.bjtu.edu.entity.Homework>
     */
    List<Homework> queryHomeworkByTeacherId(long teacherId);

    /**
     * @author: ysp
     * @description: 通过studentId查询作业信息列表
     * @createTime: 2020/8/23 12:31
     *
     * @param studentId
     * @return java.util.List<com.bjtu.edu.entity.Homework>
     */
    List<Homework> queryHomeworkByStudentId(@Param("studentId") long studentId);

    /**
     * @author: ysp
     * @description: 新增作业信息
     * @createTime: 2020/8/18 9:34
     *
     * @param homework
     * @return int
     */
    int addHomework(Homework homework);

    /**
     * @author: ysp
     * @description: 修改作业信息
     * @createTime: 2020/8/18 9:35
     *
     * @param homework
     * @return int
     */
    int modifyHomework(Homework homework);

    /**
     * @author: ysp
     * @description: 通过homeworkId删除指定作业信息
     * @createTime: 2020/8/18 9:37
     *
     * @param homeworkId
     * @return int
     */
    int deleteHomework(long homeworkId);
}
