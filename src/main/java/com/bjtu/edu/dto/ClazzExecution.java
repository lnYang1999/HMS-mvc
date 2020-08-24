package com.bjtu.edu.dto;

import com.bjtu.edu.entity.Clazz;
import com.bjtu.edu.enums.ClazzStateEnum;

import java.util.List;

/**
 * @project: HMS-mvc
 * @description: Clazz构造类
 * @author: ysp
 * @create: 2020/08/03
 */
public class ClazzExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的clazz
    private Clazz clazz;

    // 获取的clazz列表
    private List<Clazz> clazzList;

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

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public List<Clazz> getClazzList() {
        return clazzList;
    }

    public void setClazzList(List<Clazz> clazzList) {
        this.clazzList = clazzList;
    }

    // 空的构造器
    public ClazzExecution() {
    }

    // 失败的构造器
    public ClazzExecution(ClazzStateEnum clazzStateEnum) {
        this.state = clazzStateEnum.getState();
        this.stateInfo = clazzStateEnum.getStateInfo();
    }

    // 成功的构造器
    public ClazzExecution(ClazzStateEnum clazzStateEnum, Clazz clazz) {
        this.state = clazzStateEnum.getState();
        this.stateInfo = clazzStateEnum.getStateInfo();
        this.clazz = clazz;
    }

    // 成功的构造器
    public ClazzExecution(ClazzStateEnum clazzStateEnum, List<Clazz> clazzList) {
        this.state = clazzStateEnum.getState();
        this.stateInfo = clazzStateEnum.getStateInfo();
        this.clazzList = clazzList;
    }
}
