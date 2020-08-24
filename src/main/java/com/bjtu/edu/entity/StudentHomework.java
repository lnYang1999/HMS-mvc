package com.bjtu.edu.entity;

import java.util.Date;

/**
 * @project: HMS-mvc
 * @description: 学生作业实体类
 * @author: ysp
 * @create: 2020/08/01
 */
public class StudentHomework {
    //学生作业ID
    private Long studentHomeworkId;
    //作业提交内容
    private String submitContent;
    //作业提交文件
    private String submitFile;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;

    //作业，表示学生提交的作业信息属于哪个作业类别下
    private Homework homework;
    //学生，表示提交的作业信息属于哪个学生
    private Student student;

    public Long getStudentHomeworkId() {
        return studentHomeworkId;
    }

    public void setStudentHomeworkId(Long studentHomeworkId) {
        this.studentHomeworkId = studentHomeworkId;
    }

    public String getSubmitContent() {
        return submitContent;
    }

    public void setSubmitContent(String submitContent) {
        this.submitContent = submitContent;
    }

    public String getSubmitFile() {
        return submitFile;
    }

    public void setSubmitFile(String submitFile) {
        this.submitFile = submitFile;
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

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
