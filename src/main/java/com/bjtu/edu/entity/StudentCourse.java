package com.bjtu.edu.entity;

import java.util.Date;

/**
 * @project: HMS-mvc
 * @description: 学生选课实体类
 * @author: ysp
 * @create: 2020/08/01
 */
public class StudentCourse {
    //学生选课ID
    private Long studentCourseId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;

    //课程，表示选课的课程
    private Course course;
    //学生，表示选课的学生
    private Student student;

    public Long getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(Long studentCourseId) {
        this.studentCourseId = studentCourseId;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
