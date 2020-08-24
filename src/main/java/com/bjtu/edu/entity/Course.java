package com.bjtu.edu.entity;

import java.util.Date;

/**
 * @project: HMS-mvc
 * @description: 课程实体类
 * @author: ysp
 * @create: 2020/08/01
 */
public class Course {
    //课程ID
    private Long courseId;
    //课程名称
    private String courseName;
    //简介
    private String courseDesc;
    //课程容量
    private Integer courseSize;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;

    //教师，表示该课程是哪个教师教授的
    private Teacher teacher;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public Integer getCourseSize() {
        return courseSize;
    }

    public void setCourseSize(Integer courseSize) {
        this.courseSize = courseSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Teacher getTeacher() { return teacher; }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
