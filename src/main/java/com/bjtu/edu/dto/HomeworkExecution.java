package com.bjtu.edu.dto;

import com.bjtu.edu.entity.Homework;
import com.bjtu.edu.enums.HomeworkStateEnum;

import java.util.List;

/**
 * @project: HMS-mvc
 * @description: Homework构造类
 * @author: ysp
 * @create: 2020/08/03
 */
public class HomeworkExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的homework
    private Homework homework;

    // 获取的homework列表
    private List<Homework> homeworkList;

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

    public Homework getHomework() { return homework; }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public List<Homework> getHomeworkList() { return homeworkList; }

    public void setHomeworkList(List<Homework> homeworkList) {
        this.homeworkList = homeworkList;
    }

    // 空的构造器
    public HomeworkExecution() {
    }

    // 失败的构造器
    public HomeworkExecution(HomeworkStateEnum homeworkStateEnum) {
        this.state = homeworkStateEnum.getState();
        this.stateInfo = homeworkStateEnum.getStateInfo();
    }

    // 成功的构造器
    public HomeworkExecution(HomeworkStateEnum homeworkStateEnum, Homework homework) {
        this.state = homeworkStateEnum.getState();
        this.stateInfo = homeworkStateEnum.getStateInfo();
        this.homework = homework;
    }

    // 成功的构造器
    public HomeworkExecution(HomeworkStateEnum homeworkStateEnum, List<Homework> homeworkList) {
        this.state = homeworkStateEnum.getState();
        this.stateInfo = homeworkStateEnum.getStateInfo();
        this.homeworkList = homeworkList;
    }
}
