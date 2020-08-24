package com.bjtu.edu.dto;

import com.bjtu.edu.entity.Course;
import com.bjtu.edu.enums.CourseStateEnum;

import java.util.List;

/**
 * @project: HMS-mvc
 * @description: Course构造类
 * @author: ysp
 * @create: 2020/08/03
 */
public class CourseExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的course
    private Course course;

    // 获取的course列表
    private List<Course> courseList;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Course getCourse() { return course; }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Course> getCourseList() { return courseList; }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    // 空的构造器
    public CourseExecution() {
    }

    // 失败的构造器
    public CourseExecution(CourseStateEnum courseStateEnum) {
        this.state = courseStateEnum.getState();
        this.stateInfo = courseStateEnum.getStateInfo();
    }

    // 成功的构造器
    public CourseExecution(CourseStateEnum courseStateEnum, Course course) {
        this.state = courseStateEnum.getState();
        this.stateInfo = courseStateEnum.getStateInfo();
        this.course = course;
    }

    // 成功的构造器
    public CourseExecution(CourseStateEnum courseStateEnum, List<Course> courseList) {
        this.state = courseStateEnum.getState();
        this.stateInfo = courseStateEnum.getStateInfo();
        this.courseList = courseList;
    }
}
