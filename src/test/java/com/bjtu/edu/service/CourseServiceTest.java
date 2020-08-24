package com.bjtu.edu.service;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.dto.CourseExecution;
import com.bjtu.edu.entity.Course;
import com.bjtu.edu.entity.Teacher;
import com.bjtu.edu.enums.CourseStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @project: HMS-mvc
 * @description: CourseService层测试类
 * @author: ysp
 * @create: 2020/08/16
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceTest extends BaseTest {
    @Autowired
    private CourseService courseService;

    @Test
    public void testAQueryCourse(){
        List<Course> courseList = courseService.getCourseList();
        assertEquals(2,courseList.size());
        System.out.println(courseList.size());
    }

    @Test
    public void testBQueryCourseById(){
        Course course = courseService.getCourseById(1L);
        assertEquals("操作系统",course.getCourseName());
        System.out.println(course.getCourseName());
    }

    @Test
    public void testCAddCourse(){
        Course course = new Course();
        course.setCourseId(233L);
        course.setCourseName("软件测试");
        course.setCourseDesc("主要讲授常用软件测试方法-黑盒测试、白盒测试等");
        course.setCourseSize(44);
        course.setCreateTime(new Date());
        Teacher teacher = new Teacher();
        teacher.setTeacherId(11L);
        course.setTeacher(teacher);

        CourseExecution courseExecution = courseService.addCourse(course);
        assertEquals(CourseStateEnum.SUCCESS.getState(), courseExecution.getState());
        System.out.println(courseExecution.getStateInfo());
    }

    @Test
    public void testDModifyCourse(){
        Course course = new Course();
        course.setCourseId(233L);
        course.setLastEditTime(new Date());
        Teacher teacher = new Teacher();
        teacher.setTeacherId(22L);
        course.setTeacher(teacher);

        CourseExecution courseExecution = courseService.modifyCourse(course);
        assertEquals(CourseStateEnum.SUCCESS.getState(), courseExecution.getState());
        System.out.println(courseExecution.getStateInfo());
    }

    @Test
    public void testEDeleteCourse(){
        CourseExecution courseExecution = courseService.deleteCourse(233L);
        assertEquals(CourseStateEnum.SUCCESS.getState(), courseExecution.getState());
        System.out.println(courseExecution.getStateInfo());
    }
}
