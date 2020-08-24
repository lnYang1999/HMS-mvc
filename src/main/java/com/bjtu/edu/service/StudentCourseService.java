package com.bjtu.edu.service;

import com.bjtu.edu.dto.StudentCourseExecution;
import com.bjtu.edu.entity.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    /**
     * @author: ysp
     * @description: 查询全部选课信息
     * @createTime: 2020/8/17 10:40
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.StudentCourse>
     */
    List<StudentCourse> getStudentCourseList();

    /**
     * @author: ysp
     * @description: 通过选课ID获取指定选课信息
     * @createTime: 2020/8/17 10:40
     *
     * @param studentCourseId
     * @return com.bjtu.edu.entity.StudentCourse
     */
    StudentCourse getStudentCourseById(long studentCourseId);

    /**
     * @author: ysp
     * @description: 通过studentId查询选课信息列表
     * @createTime: 2020/8/23 11:29
     *
     * @param studentId
     * @return java.util.List<com.bjtu.edu.entity.StudentCourse>
     */
    List<StudentCourse> getStudentCourseByStudentId(long studentId);

    /**
     * @author: ysp
     * @description: 通过课程ID获取学生列表信息
     * @createTime: 2020/8/20 22:45
     *
     * @param courseId
     * @return com.bjtu.edu.entity.StudentCourse
     */
    List<StudentCourse> getStudentByCourseId(long courseId);

    /**
     * @author: ysp
     * @description: 新增选课信息
     * @createTime: 2020/8/17 10:41
     *
     * @param studentCourse
     * @return com.bjtu.edu.dto.StudentCourseExecution
     */
    StudentCourseExecution addStudentCourse(StudentCourse studentCourse);

    /**
     * @author: ysp
     * @description: 修改选课信息
     * @createTime: 2020/8/17 10:41
     *
     * @param studentCourse
     * @return com.bjtu.edu.dto.StudentCourseExecution
     */
    StudentCourseExecution modifyStudentCourse(StudentCourse studentCourse);

    /**
     * @author: ysp
     * @description: 删除指定选课信息
     * @createTime: 2020/8/17 11:01
     *
     * @param studentCourseId
     * @return com.bjtu.edu.dto.StudentCourseExecution
     */
    StudentCourseExecution deleteStudentCourse(long studentCourseId);
}
