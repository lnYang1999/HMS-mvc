package com.bjtu.edu.service;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.StudentExecution;
import com.bjtu.edu.entity.Clazz;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.enums.StudentStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @project: HMS-mvc
 * @description: StudentService层测试类
 * @author: ysp
 * @create: 2020/08/12
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentServiceTest extends BaseTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void testAQueryStudent(){
        List<Student> studentList = studentService.getStudentList();
        assertEquals(2,studentList.size());
        System.out.println(studentList.size());
    }

    @Test
    public void testBQueryStudentById(){
        Student student = studentService.getStudentById(11L);
        assertEquals("洋洋",student.getStudentName());
        System.out.println(student.getStudentName());
    }

    @Test
    public void testCAddStudent() throws FileNotFoundException {
        Student student = new Student();
        student.setStudentId(33L);
        student.setStudentName("小明");
        student.setGender("男");
        student.setEmail("xiaoming@bjtu.edu.cn");
        student.setStudentDesc("本科生，大三");
        Clazz clazz = new Clazz();
        clazz.setClazzId(1701L);
        student.setClazz(clazz);
        student.setCreateTime(new Date());

        // 创建缩略图文件流
        File thumbnailFile = new File("F:/照片/17301053.JPG");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);

        StudentExecution studentExecution = studentService.addStudent(student,thumbnail);
        assertEquals(StudentStateEnum.SUCCESS.getState(),studentExecution.getState());
        System.out.println(studentExecution.getStateInfo());
    }

    @Test
    public void testDModifyStudent() throws FileNotFoundException {
        Student student = new Student();
        student.setStudentId(33L);
        student.setStudentDesc("大四本科生");

        // 创建缩略图文件流
        File thumbnailFile = new File("E:/壁纸/微信图片_20180221212912.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);

        StudentExecution studentExecution = studentService.modifyStudent(student,thumbnail);
        assertEquals(StudentStateEnum.SUCCESS.getState(),studentExecution.getState());
        System.out.println(studentExecution.getStateInfo());
    }

    @Test
    public void testEDeleteStudent(){
        StudentExecution studentExecution = studentService.deleteStudent(33L);
        assertEquals(StudentStateEnum.SUCCESS.getState(),studentExecution.getState());
        System.out.println(studentExecution.getStateInfo());
    }
}
