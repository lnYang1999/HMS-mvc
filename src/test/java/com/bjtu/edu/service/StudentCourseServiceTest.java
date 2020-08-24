package com.bjtu.edu.service;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.dto.StudentCourseExecution;
import com.bjtu.edu.entity.Course;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.entity.StudentCourse;
import com.bjtu.edu.enums.StudentCourseStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @project: HMS-mvc
 * @description: StudentCourseService层测试类
 * @author: ysp
 * @create: 2020/08/17
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCourseServiceTest extends BaseTest {
    @Autowired
    private StudentCourseService studentCourseService;

    @Test
    public void testAQueryStudentCourse(){
        List<StudentCourse> studentCourseList = studentCourseService.getStudentCourseList();
        assertEquals(3,studentCourseList.size());
        System.out.println(studentCourseList.size());
    }

    @Test
    public void testBQueryStudentCourseById(){
        StudentCourse studentCourse = studentCourseService.getStudentCourseById(1L);
        assertEquals("操作系统",studentCourse.getCourse().getCourseName());
        System.out.println(studentCourse.getCourse().getCourseName());
    }

    @Test
    public void testCAddStudentCourse(){
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentCourseId(33L);
        Course course = new Course();
        course.setCourseId(2L);
        studentCourse.setCourse(course);
        Student student = new Student();
        student.setStudentId(11L);
        studentCourse.setStudent(student);

        StudentCourseExecution studentCourseExecution = studentCourseService.addStudentCourse(studentCourse);
        assertEquals(StudentCourseStateEnum.SUCCESS.getState(),studentCourseExecution.getState());
        System.out.println(studentCourseExecution.getStateInfo());
    }

    @Test
    public void testDModifyStudentCourse(){
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentCourseId(33L);
        Course course = new Course();
        course.setCourseId(1L);
        studentCourse.setCourse(course);
        Student student = new Student();
        student.setStudentId(22L);
        studentCourse.setStudent(student);

        StudentCourseExecution studentCourseExecution = studentCourseService.modifyStudentCourse(studentCourse);
        assertEquals(StudentCourseStateEnum.SUCCESS.getState(),studentCourseExecution.getState());
        System.out.println(studentCourseExecution.getStateInfo());
    }

    @Test
    public void testEDeleteStudentCourse(){
        StudentCourseExecution studentCourseExecution = studentCourseService.deleteStudentCourse(33L);
        assertEquals(StudentCourseStateEnum.SUCCESS.getState(),studentCourseExecution.getState());
        System.out.println(studentCourseExecution.getStateInfo());
    }
}
