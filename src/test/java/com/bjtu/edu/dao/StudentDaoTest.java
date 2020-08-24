package com.bjtu.edu.dao;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.entity.Clazz;
import com.bjtu.edu.entity.Student;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @project: HMS-mvc
 * @description: StudentDao层测试类
 * @author: ysp
 * @create: 2020/08/12
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDaoTest extends BaseTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    public void testAQueryStudent(){
        List<Student> studentList = studentDao.queryStudent();
        assertEquals(3, studentList.size());
        System.out.println(studentList.size());
    }

    @Test
    public void testBQueryStudentById(){
        Student student = studentDao.queryStudentById(11L);
        System.out.println(student.getStudentName());
    }

    @Test
    public void testCAddStudent(){
        Student student = new Student();
        student.setStudentId(33L);
        student.setStudentName("小明");
        student.setProfileImg("test");
        student.setGender("男");
        student.setEmail("xiaoming@bjtu.edu.cn");
        student.setStudentDesc("本科生，大三");
        Clazz clazz = new Clazz();
        clazz.setClazzId(1701L);
        student.setClazz(clazz);
        student.setCreateTime(new Date());

        int effectedNum = studentDao.addStudent(student);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testDModifyStudent(){
        Student student = new Student();
        Clazz clazz = new Clazz();
        clazz.setClazzId(1702L);
        student.setStudentId(33L);
        student.setStudentName("小张");
        student.setClazz(clazz);
        student.setLastEditTime(new Date());

        int effectedNum = studentDao.modifyStudent(student);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testEDeleteStudent(){
        int effectedNum = studentDao.deleteStudent(33L);
        assertEquals(1,effectedNum);
    }
}
