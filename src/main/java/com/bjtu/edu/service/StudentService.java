package com.bjtu.edu.service;

import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.StudentExecution;
import com.bjtu.edu.entity.Student;

import java.util.List;

public interface StudentService {
    /**
     * @author: ysp
     * @description: 查询全部教师信息
     * @createTime: 2020/8/15 19:50
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.Student>
     */
    List<Student> getStudentList();

    /**
     * @author: ysp
     * @description: 通过学生ID获取指定学生信息
     * @createTime: 2020/8/12 22:40
     *
     * @param studentId
     * @return com.bjtu.edu.entity.Student
     */
    Student getStudentById(long studentId);

    /**
     * @author: ysp
     * @description: 新增学生信息
     * @createTime: 2020/8/15 19:54
     *
     * @param student
     * @param imageHolder
     * @return com.bjtu.edu.dto.StudentExecution
     */
    StudentExecution addStudent(Student student, ImageHolder imageHolder);

    /**
     * @author: ysp
     * @description: 修改学生信息
     * @createTime: 2020/8/15 19:55
     *
     * @param student
     * @param imageHolder
     * @return com.bjtu.edu.dto.StudentExecution
     */
    StudentExecution modifyStudent(Student student, ImageHolder imageHolder);

    /**
     * @author: ysp
     * @description: 删除指定学生信息
     * @createTime: 2020/8/15 19:56
     *
     * @param studentId
     * @return com.bjtu.edu.dto.StudentExecution
     */
    StudentExecution deleteStudent(long studentId);
}
