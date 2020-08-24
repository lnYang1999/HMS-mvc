package com.bjtu.edu.entity;

import java.util.Date;

/**
 * @project: HMS-mvc
 * @description: 学生实体类
 * @author: ysp
 * @create: 2020/08/01
 */
public class Student {
    //学生ID
    private Long studentId;
    //密码
    private String password;
    //学生姓名
    private String studentName;
    //头像路径
    private String profileImg;
    //性别
    private String gender;
    //邮箱
    private String email;
    //简介
    private String studentDesc;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;

    //班级,表示该学生属于哪个班级
    private Clazz clazz;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentDesc() {
        return studentDesc;
    }

    public void setStudentDesc(String studentDesc) {
        this.studentDesc = studentDesc;
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

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
