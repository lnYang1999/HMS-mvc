package com.bjtu.edu.service;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.dto.FileHolder;
import com.bjtu.edu.dto.HomeworkExecution;
import com.bjtu.edu.dto.StudentHomeworkExecution;
import com.bjtu.edu.entity.Course;
import com.bjtu.edu.entity.Homework;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.entity.StudentHomework;
import com.bjtu.edu.enums.HomeworkStateEnum;
import com.bjtu.edu.enums.StudentHomeworkStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @project: HMS-mvc
 * @description: StudentHomeworkService层测试类
 * @author: ysp
 * @create: 2020/08/18
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentHomeworkServiceTest extends BaseTest {
    @Autowired
    private StudentHomeworkService studentHomeworkService;

    @Test
    public void testAQueryStudentHomework(){
        List<StudentHomework> studentHomeworkList = studentHomeworkService.getStudentHomeworkList();
        assertEquals(3,studentHomeworkList.size());
        System.out.println(studentHomeworkList.size());
    }

    @Test
    public void testBQueryStudentHomeworkById(){
        StudentHomework studentHomework = studentHomeworkService.getStudentHomeworkById(1L);
        assertEquals("我不会写啊",studentHomework.getSubmitContent());
        System.out.println(studentHomework.getSubmitContent());
    }

    @Test
    @Ignore
    public void testCAddStudentHomework() throws FileNotFoundException {
        StudentHomework studentHomework = new StudentHomework();
        studentHomework.setSubmitContent("见上传文件");
        Homework homework = new Homework();
        homework.setHomeworkId(3L);
        Student student = new Student();
        student.setStudentId(11L);
        studentHomework.setHomework(homework);
        studentHomework.setStudent(student);

        // 创建文件流
        File studentHomeworkFile = new File("C:/Users/hp/Desktop/杨烁平-实习简历.pdf");
        InputStream is = new FileInputStream(studentHomeworkFile);
        FileHolder fileHolder = new FileHolder(studentHomeworkFile.getName(), is);

        StudentHomeworkExecution studentHomeworkExecution = studentHomeworkService.addStudentHomework(studentHomework,fileHolder);
        assertEquals(StudentHomeworkStateEnum.SUCCESS.getState(),studentHomeworkExecution.getState());
        System.out.println(studentHomeworkExecution.getStateInfo());
    }

    @Test
    @Ignore
    public void testDModifyStudentHomework() throws FileNotFoundException {
        StudentHomework studentHomework = new StudentHomework();
        studentHomework.setStudentHomeworkId(4L);
        studentHomework.setSubmitContent("更改了上传文件");

        // 创建文件流
        File homeworkFile = new File("C:/Users/hp/Desktop/杨烁平-实习简历.pdf");
        InputStream is = new FileInputStream(homeworkFile);
        FileHolder fileHolder = new FileHolder(homeworkFile.getName(), is);

        StudentHomeworkExecution studentHomeworkExecution = studentHomeworkService.modifyStudentHomework(studentHomework,fileHolder);
        assertEquals(StudentHomeworkStateEnum.SUCCESS.getState(),studentHomeworkExecution.getState());
        System.out.println(studentHomeworkExecution.getStateInfo());
    }

    @Test
    public void testEDeleteStudentHomework() throws FileNotFoundException {
        StudentHomework studentHomework = new StudentHomework();
        studentHomework.setStudentHomeworkId(5L);
        studentHomework.setSubmitContent("见上传文件");
        Homework homework = new Homework();
        homework.setHomeworkId(2L);
        Student student = new Student();
        student.setStudentId(11L);
        studentHomework.setHomework(homework);
        studentHomework.setStudent(student);

        // 创建文件流
        File studentHomeworkFile = new File("C:/Users/hp/Desktop/杨烁平-实习简历.pdf");
        InputStream is = new FileInputStream(studentHomeworkFile);
        FileHolder fileHolder = new FileHolder(studentHomeworkFile.getName(), is);

        StudentHomeworkExecution studentHomeworkExecution1 = studentHomeworkService.addStudentHomework(studentHomework,fileHolder);
        assertEquals(StudentHomeworkStateEnum.SUCCESS.getState(),studentHomeworkExecution1.getState());
        System.out.println(studentHomeworkExecution1.getStateInfo());

        StudentHomeworkExecution studentHomeworkExecution2 = studentHomeworkService.deleteStudentHomework(5L);
        assertEquals(HomeworkStateEnum.SUCCESS.getState(),studentHomeworkExecution2.getState());
        System.out.println(studentHomeworkExecution2.getStateInfo());
    }
}
