package com.bjtu.edu.dao;

import com.bjtu.edu.entity.Course;

import java.util.List;

/**
 * @author: ysp
 * @description: 课程实体类dao层接口
 * @createTime: 2020/8/15 11:23
 */
public interface CourseDao {
    /**
     * @author: ysp
     * @description: 查询所有课程列表
     * @createTime: 2020/8/16 22:33
     *
     * @param 
     * @return java.util.List<com.bjtu.edu.entity.Course>
     */
    List<Course> queryCourse();
    
    /**
     * @author: ysp
     * @description: 通过courseId查询指定课程信息
     * @createTime: 2020/8/16 22:33
     *
     * @param courseId
     * @return com.bjtu.edu.entity.Course
     */
    Course queryCourseById(long courseId);

    /**
     * @author: ysp
     * @description: 通过teacherId查询课程信息列表
     * @createTime: 2020/8/20 12:09
     *
     * @param teacherId
     * @return java.util.List<com.bjtu.edu.entity.Course>
     */
    List<Course> queryCourseByTeacherId(long teacherId);
    
    /**
     * @author: ysp
     * @description: 新增课程信息
     * @createTime: 2020/8/16 22:33
     *
     * @param course
     * @return int
     */
    int addCourse(Course course);
    
    /**
     * @author: ysp
     * @description: 修改课程信息
     * @createTime: 2020/8/16 22:35
     *
     * @param course
     * @return int
     */
    int modifyCourse(Course course);
    
    /**
     * @author: ysp
     * @description: 通过courseId删除指定课程信息
     * @createTime: 2020/8/16 23:00
     *
     * @param courseId
     * @return int
     */
    int deleteCourse(long courseId);
}
