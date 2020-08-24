package com.bjtu.edu.dao;

import com.bjtu.edu.entity.Homework;
import com.bjtu.edu.entity.StudentCourse;

import java.util.List;

/**
 * @author: ysp
 * @description: 选课实体类dao层接口
 * @createTime: 2020/8/15 11:23
 */
public interface StudentCourseDao {
    /**
     * @author: ysp
     * @description: 查询所有选课列表
     * @createTime: 2020/8/17 8:36
     *
     * @param 
     * @return java.util.List<com.bjtu.edu.entity.StudentCourse>
     */
    List<StudentCourse> queryStudentCourse();
    
    /**
     * @author: ysp
     * @description: 通过studentCourseId查询指定选课信息
     * @createTime: 2020/8/17 8:37
     *
     * @param studentCourseId
     * @return com.bjtu.edu.entity.StudentCourse
     */
    StudentCourse queryStudentCourseById(long studentCourseId);


    /**
     * @author: ysp
     * @description: 通过studentId查询选课信息列表
     * @createTime: 2020/8/23 11:23
     *
     * @param studentId
     * @return java.util.List<com.bjtu.edu.entity.Course>
     */
    List<StudentCourse> queryStudentCourseByStudentId(long studentId);

    /**
     * @author: ysp
     * @description: 通过courseId查询选课信息列表
     * @createTime: 2020/8/20 22:34
     *
     * @param courseId
     * @return com.bjtu.edu.entity.StudentCourse
     */
    List<StudentCourse> queryStudentByCourseId(long courseId);
    
    /**
     * @author: ysp
     * @description: 新增选课信息
     * @createTime: 2020/8/17 8:37
     *
     * @param studentCourse
     * @return int
     */
    int addStudentCourse(StudentCourse studentCourse);
    
    /**
     * @author: ysp
     * @description: 修改选课信息
     * @createTime: 2020/8/17 8:37
     *
     * @param studentCourse
     * @return int
     */
    int modifyStudentCourse(StudentCourse studentCourse);
    
    /**
     * @author: ysp
     * @description: 通过studentCourseId删除指定选课信息
     * @createTime: 2020/8/17 8:49
     *
     * @param studentCourseId
     * @return int
     */
    int deleteStudentCourse(long studentCourseId);
}
