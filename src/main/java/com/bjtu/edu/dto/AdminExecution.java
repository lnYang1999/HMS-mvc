package com.bjtu.edu.dto;

import com.bjtu.edu.entity.Admin;
import com.bjtu.edu.enums.AdminStateEnum;

import java.util.List;

/**
 * @project: HMS-mvc
 * @description: Admin构造类
 * @author: ysp
 * @create: 2020/08/03
 */
public class AdminExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的admin
    private Admin admin;

    // 获取的admin列表
    private List<Admin> adminList;

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

    public Admin getAdmin() { return admin; }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Admin> getAdminList() { return adminList; }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    // 空的构造器
    public AdminExecution() {
    }

    // 失败的构造器
    public AdminExecution(AdminStateEnum adminStateEnum) {
        this.state = adminStateEnum.getState();
        this.stateInfo = adminStateEnum.getStateInfo();
    }

    // 成功的构造器
    public AdminExecution(AdminStateEnum adminStateEnum, Admin admin) {
        this.state = adminStateEnum.getState();
        this.stateInfo = adminStateEnum.getStateInfo();
        this.admin = admin;
    }

    // 成功的构造器
    public AdminExecution(AdminStateEnum adminStateEnum, List<Admin> adminList) {
        this.state = adminStateEnum.getState();
        this.stateInfo = adminStateEnum.getStateInfo();
        this.adminList = adminList;
    }
}
