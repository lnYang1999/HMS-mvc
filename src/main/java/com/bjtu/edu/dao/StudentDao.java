package com.bjtu.edu.dao;

import com.bjtu.edu.entity.Student;

import java.util.List;

/**
 * @author: ysp
 * @description: 学生实体类dao层接口
 * @createTime: 2020/8/10 11:23
 */
public interface StudentDao {
    /**
     * @author: ysp
     * @description: 查询所有学生列表
     * @createTime: 2020/8/15 16:36
     *
     * @param 
     * @return java.util.List<com.bjtu.edu.entity.Student>
     */
    List<Student> queryStudent();

    /**
     * @author: ysp
     * @description: 通过studentId查询指定学生信息
     * @createTime: 2020/8/12 22:06
     *
     * @param studentId
     * @return com.bjtu.edu.entity.Student
     */
    Student queryStudentById(long studentId);
    
    /**
     * @author: ysp
     * @description: 新增学生信息
     * @createTime: 2020/8/15 16:37
     *
     * @param student
     * @return int
     */
    int addStudent(Student student);
    
    /**
     * @author: ysp
     * @description: 修改学生信息
     * @createTime: 2020/8/15 16:37
     *
     * @param student
     * @return int
     */
    int modifyStudent(Student student);
    
    /**
     * @author: ysp
     * @description: 通过studentId删除指定学生
     * @createTime: 2020/8/15 17:57
     *
     * @param studentId
     * @return int
     */
    int deleteStudent(long studentId);
}
