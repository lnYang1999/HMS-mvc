package com.bjtu.edu.entity;

import java.util.Date;

/**
 * @project: HMS-mvc
 * @description: 作业实体类
 * @author: ysp
 * @create: 2020/08/01
 */
public class Homework {
    //作业ID
    private Long homeworkId;
    //作业名称
    private String homeworkName;
    //作业详细要求
    private String homeworkDesc;
    //作业要求文件
    private String homeworkFile;
    //作业提交截至时间
    private String submitTime;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;

    //课程，表示该作业属于哪个课程下
    private Course course;

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getHomeworkDesc() {
        return homeworkDesc;
    }

    public void setHomeworkDesc(String homeworkDesc) {
        this.homeworkDesc = homeworkDesc;
    }

    public String getHomeworkFile() {
        return homeworkFile;
    }

    public void setHomeworkFile(String homeworkFile) {
        this.homeworkFile = homeworkFile;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
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
}
