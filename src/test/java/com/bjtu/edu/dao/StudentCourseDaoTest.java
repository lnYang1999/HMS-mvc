package com.bjtu.edu.dao;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.entity.Course;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.entity.StudentCourse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @project: HMS-mvc
 * @description: StudentCourseDao层测试类
 * @author: ysp
 * @create: 2020/08/17
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCourseDaoTest extends BaseTest {
    @Autowired
    private StudentCourseDao studentCourseDao;

    @Test
    public void testAQueryStudentCourse(){
        List<StudentCourse> studentCourseList = studentCourseDao.queryStudentCourse();
        assertEquals(3,studentCourseList.size());
        System.out.println(studentCourseList.size());
    }

    @Test
    public void testBQueryStudentCourseById(){
        StudentCourse studentCourse = studentCourseDao.queryStudentCourseById(1L);
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
        studentCourse.setCreateTime(new Date());
        int effectedNum = studentCourseDao.addStudentCourse(studentCourse);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
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
        studentCourse.setLastEditTime(new Date());
        int effectedNum = studentCourseDao.modifyStudentCourse(studentCourse);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testEDeleteStudentCourse(){
        int effectedNum = studentCourseDao.deleteStudentCourse(33L);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }
}
