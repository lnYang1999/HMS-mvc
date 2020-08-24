package com.bjtu.edu.dao;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.entity.Homework;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.entity.StudentHomework;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @project: HMS-mvc
 * @description: StudentHomeworkDao层测试类
 * @author: ysp
 * @create: 2020/08/18
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentHomeworkDaoTest extends BaseTest {
    @Autowired
    private StudentHomeworkDao studentHomeworkDao;

    @Test
    public void testAQueryStudentHomework(){
        List<StudentHomework> studentHomeworkList = studentHomeworkDao.queryStudentHomework();
        assertEquals(2, studentHomeworkList.size());
        System.out.println(studentHomeworkList.size());
    }

    @Test
    public void testBQueryStudentHomeworkById(){
        StudentHomework studentHomework = studentHomeworkDao.queryStudentHomeworkById(1L);
        System.out.println(studentHomework.getSubmitContent());
    }

    @Test
    public void testCAddStudentHomework(){
        StudentHomework studentHomework = new StudentHomework();
        studentHomework.setSubmitContent("不会不会");
        studentHomework.setSubmitFile("测试测试");
        studentHomework.setCreateTime(new Date());
        Homework homework = new Homework();
        homework.setHomeworkId(2L);
        studentHomework.setHomework(homework);
        Student student = new Student();
        student.setStudentId(22L);
        studentHomework.setStudent(student);

        int effectedNum = studentHomeworkDao.addStudentHomework(studentHomework);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testDModifyStudentHomework(){
        StudentHomework studentHomework = new StudentHomework();
        studentHomework.setStudentHomeworkId(2L);
        studentHomework.setSubmitContent("见博客地址：...");

        int effectedNum = studentHomeworkDao.modifyStudentHomework(studentHomework);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testEDeleteStudentHomework(){
        int effectedNum = studentHomeworkDao.deleteStudentHomework(2L);
        assertEquals(1,effectedNum);
    }
}
