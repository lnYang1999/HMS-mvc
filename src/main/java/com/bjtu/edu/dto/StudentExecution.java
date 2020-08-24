package com.bjtu.edu.dto;

import com.bjtu.edu.entity.Student;
import com.bjtu.edu.enums.StudentStateEnum;

import java.util.List;

/**
 * @project: HMS-mvc
 * @description: Student构造类
 * @author: ysp
 * @create: 2020/08/03
 */
public class StudentExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的student
    private Student student;

    // 获取的student列表
    private List<Student> studentList;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getTeacherList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    // 空的构造器
    public StudentExecution() {
    }

    // 失败的构造器
    public StudentExecution(StudentStateEnum studentStateEnum) {
        this.state = studentStateEnum.getState();
        this.stateInfo = studentStateEnum.getStateInfo();
    }

    // 成功的构造器
    public StudentExecution(StudentStateEnum studentStateEnum, Student student) {
        this.state = studentStateEnum.getState();
        this.stateInfo = studentStateEnum.getStateInfo();
        this.student = student;
    }

    // 成功的构造器
    public StudentExecution(StudentStateEnum studentStateEnum, List<Student> studentList) {
        this.state = studentStateEnum.getState();
        this.stateInfo = studentStateEnum.getStateInfo();
        this.studentList = studentList;
    }
}
