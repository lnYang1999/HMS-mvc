package com.bjtu.edu.dto;

import com.bjtu.edu.entity.Teacher;
import com.bjtu.edu.enums.TeacherStateEnum;

import java.util.List;

/**
 * @project: HMS-mvc
 * @description: Teacher构造类
 * @author: ysp
 * @create: 2020/08/03
 */
public class TeacherExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的teacher
    private Teacher teacher;

    // 获取的teacher列表
    private List<Teacher> teacherList;

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    // 空的构造器
    public TeacherExecution() {
    }

    // 失败的构造器
    public TeacherExecution(TeacherStateEnum teacherStateEnum) {
        this.state = teacherStateEnum.getState();
        this.stateInfo = teacherStateEnum.getStateInfo();
    }

    // 成功的构造器
    public TeacherExecution(TeacherStateEnum teacherStateEnum, Teacher teacher) {
        this.state = teacherStateEnum.getState();
        this.stateInfo = teacherStateEnum.getStateInfo();
        this.teacher = teacher;
    }

    // 成功的构造器
    public TeacherExecution(TeacherStateEnum teacherStateEnum, List<Teacher> teacherList) {
        this.state = teacherStateEnum.getState();
        this.stateInfo = teacherStateEnum.getStateInfo();
        this.teacherList = teacherList;
    }
}
