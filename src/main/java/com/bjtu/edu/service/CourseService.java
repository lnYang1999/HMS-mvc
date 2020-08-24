package com.bjtu.edu.service;

import com.bjtu.edu.dto.CourseExecution;
import com.bjtu.edu.entity.Course;

import java.util.List;

public interface CourseService {
    /**
     * @author: ysp
     * @description: 查询全部课程信息
     * @createTime: 2020/8/16 23:08
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.Course>
     */
    List<Course> getCourseList();

    /**
     * @author: ysp
     * @description: 通过课程ID获取指定课程信息
     * @createTime: 2020/8/16 23:08
     *
     * @param courseId
     * @return com.bjtu.edu.entity.Course
     */
    Course getCourseById(long courseId);

    /**
     * @author: ysp
     * @description: 通过教师ID获取指定课程信息
     * @createTime: 2020/8/16 23:08
     *
     * @param teacherId
     * @return com.bjtu.edu.entity.Course
     */
    List<Course> getCourseByTeacherId(long teacherId);

    /**
     * @author: ysp
     * @description: 新增课程信息
     * @createTime: 2020/8/16 23:08
     *
     * @param course
     * @return com.bjtu.edu.dto.CourseExecution
     */
    CourseExecution addCourse(Course course);

    /**
     * @author: ysp
     * @description: 修改课程信息
     * @createTime: 2020/8/16 23:09
     *
     * @param course
     * @return com.bjtu.edu.dto.CourseExecution
     */
    CourseExecution modifyCourse(Course course);

    /**
     * @author: ysp
     * @description: 删除指定课程信息
     * @createTime: 2020/8/16 23:19
     *
     * @param courseId
     * @return com.bjtu.edu.dto.CourseExecution
     */
    CourseExecution deleteCourse(long courseId);
}
