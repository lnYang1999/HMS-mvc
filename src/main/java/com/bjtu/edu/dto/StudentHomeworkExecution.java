package com.bjtu.edu.dto;

import com.bjtu.edu.entity.StudentHomework;
import com.bjtu.edu.enums.StudentHomeworkStateEnum;

import java.util.List;

/**
 * @project: HMS-mvc
 * @description: StudentHomework构造类
 * @author: ysp
 * @create: 2020/08/03
 */
public class StudentHomeworkExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的studentHomework
    private StudentHomework studentHomework;

    // 获取的studentHomework列表
    private List<StudentHomework> studentHomeworkList;

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

    public StudentHomework getStudentHomework() { return studentHomework; }

    public void setStudentHomework(StudentHomework studentHomework) {
        this.studentHomework = studentHomework;
    }

    public List<StudentHomework> getStudentHomeworkList() { return studentHomeworkList; }

    public void setStudentHomeworkList(List<StudentHomework> studentHomeworkList) { this.studentHomeworkList = studentHomeworkList; }

    // 空的构造器
    public StudentHomeworkExecution() {
    }

    // 失败的构造器
    public StudentHomeworkExecution(StudentHomeworkStateEnum studentHomeworkStateEnum) {
        this.state = studentHomeworkStateEnum.getState();
        this.stateInfo = studentHomeworkStateEnum.getStateInfo();
    }

    // 成功的构造器
    public StudentHomeworkExecution(StudentHomeworkStateEnum studentHomeworkStateEnum, StudentHomework studentHomework) {
        this.state = studentHomeworkStateEnum.getState();
        this.stateInfo = studentHomeworkStateEnum.getStateInfo();
        this.studentHomework = studentHomework;
    }

    // 成功的构造器
    public StudentHomeworkExecution(StudentHomeworkStateEnum studentHomeworkStateEnum, List<StudentHomework> studentHomeworkList) {
        this.state = studentHomeworkStateEnum.getState();
        this.stateInfo = studentHomeworkStateEnum.getStateInfo();
        this.studentHomeworkList = studentHomeworkList;
    }
}
