package com.bjtu.edu.service;

import com.bjtu.edu.dto.FileHolder;
import com.bjtu.edu.dto.HomeworkExecution;
import com.bjtu.edu.entity.Homework;

import java.util.List;

public interface HomeworkService {
    /**
     * @author: ysp
     * @description: 查询全部作业信息
     * @createTime: 2020/8/18 12:34
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.Homework>
     */
    List<Homework> getHomeworkList();

    /**
     * @author: ysp
     * @description: 通过作业ID查询指定作业信息
     * @createTime: 2020/8/18 12:35
     *
     * @param homeworkId
     * @return com.bjtu.edu.entity.Homework
     */
    Homework getHomeworkById(long homeworkId);

    /**
     * @author: ysp
     * @description: 通过课程ID查询作业列表信息
     * @createTime: 2020/8/20 17:07
     *
     * @param courseId
     * @return java.util.List<com.bjtu.edu.entity.Homework>
     */
    List<Homework> getHomeworkByCourseId(long courseId);

    /**
     * @author: ysp
     * @description: 通过教师ID查询作业列表信息
     * @createTime: 2020/8/21 10:49
     *
     * @param teacherId
     * @return java.util.List<com.bjtu.edu.entity.Homework>
     */
    List<Homework> getHomeworkByTeacherId(long teacherId);

    /**
     * @author: ysp
     * @description: 通过学生ID查询作业列表信息
     * @createTime: 2020/8/23 12:45
     *
     * @param studentId
     * @return java.util.List<com.bjtu.edu.entity.Homework>
     */
    List<Homework> getHomeworkByStudentId(long studentId);

    /**
     * @author: ysp
     * @description: 新增作业信息
     * @createTime: 2020/8/18 12:35
     *
     * @param homework
     * @param fileHolder
     * @return com.bjtu.edu.dto.HomeworkExecution
     */
    HomeworkExecution addHomework(Homework homework, FileHolder fileHolder);

    /**
     * @author: ysp
     * @description: 修改作业信息
     * @createTime: 2020/8/18 12:35
     *
     * @param homework
     * @param fileHolder
     * @return com.bjtu.edu.dto.HomeworkExecution
     */
    HomeworkExecution modifyHomework(Homework homework, FileHolder fileHolder);

    /**
     * @author: ysp
     * @description: 删除指定作业信息
     * @createTime: 2020/8/18 12:45
     *
     * @param homeworkId
     * @return com.bjtu.edu.dto.HomeworkExecution
     */
    HomeworkExecution deleteHomework(long homeworkId);
}
