package com.bjtu.edu.service;

import com.bjtu.edu.dto.FileHolder;
import com.bjtu.edu.dto.StudentHomeworkExecution;
import com.bjtu.edu.entity.StudentHomework;

import java.util.List;

public interface StudentHomeworkService {
    /**
     * @author: ysp
     * @description: 查询全部学生作业列表信息
     * @createTime: 2020/8/18 12:38
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.StudentHomework>
     */
    List<StudentHomework> getStudentHomeworkList();

    /**
     * @author: ysp
     * @description: 通过学生作业ID获取指定学生作业信息
     * @createTime: 2020/8/18 12:39
     *
     * @param studentHomeworkId
     * @return com.bjtu.edu.entity.StudentHomework
     */
    StudentHomework getStudentHomeworkById(long studentHomeworkId);

    /**
     * @author: ysp
     * @description: 通过课程ID获取学生作业列表信息
     * @createTime: 2020/8/21 9:04
     *
     * @param courseId
     * @return java.util.List<com.bjtu.edu.entity.StudentHomework>
     */
    List<StudentHomework> getStudentHomeworkByCourseId(long courseId);

    /**
     * @author: ysp
     * @description: 通过教师ID获取学生作业列表信息
     * @createTime: 2020/8/21 9:04
     *
     * @param teacherId
     * @return java.util.List<com.bjtu.edu.entity.StudentHomework>
     */
    List<StudentHomework> getStudentHomeworkByTeacherId(long teacherId);

    /**
     * @author: ysp
     * @description: 通过传入的信息判断是否存在符合条件的学生作业信息
     * @createTime: 2020/8/22 17:13
     *
     * @param studentHomeworkCondition
     * @return com.bjtu.edu.entity.StudentHomework
     */
    StudentHomework getStudentHomeworkCondition(StudentHomework studentHomeworkCondition);

    /**
     * @author: ysp
     * @description: 新增学生作业信息
     * @createTime: 2020/8/18 12:39
     *
     * @param studentHomework
     * @param fileHolder
     * @return com.bjtu.edu.dto.StudentHomeworkExecution
     */
    StudentHomeworkExecution addStudentHomework(StudentHomework studentHomework, FileHolder fileHolder);

    /**
     * @author: ysp
     * @description: 修改学生作业信息
     * @createTime: 2020/8/18 12:39
     *
     * @param studentHomework
     * @param fileHolder
     * @return com.bjtu.edu.dto.StudentHomeworkExecution
     */
    StudentHomeworkExecution modifyStudentHomework(StudentHomework studentHomework, FileHolder fileHolder);

    /**
     * @author: ysp
     * @description: 删除指定学生作业信息
     * @createTime: 2020/8/18 12:45
     *
     * @param studentHomeworkId
     * @return com.bjtu.edu.dto.StudentHomeworkExecution
     */
    StudentHomeworkExecution deleteStudentHomework(long studentHomeworkId);
}
