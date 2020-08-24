package com.bjtu.edu.dao;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.entity.Course;
import com.bjtu.edu.entity.Teacher;
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
 * @description: CourseDao层测试类
 * @author: ysp
 * @create: 2020/08/16
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseDaoTest extends BaseTest {
    @Autowired
    private CourseDao courseDao;

    @Test
    public void testAQueryCourse(){
        List<Course> courseList = courseDao.queryCourse();
        assertEquals(2, courseList.size());
        System.out.println(courseList.size());
    }

    @Test
    public void testBQueryCourseById(){
        Course course = courseDao.queryCourseById(1L);
        System.out.println(course.getCourseName());
    }

    @Test
    @Ignore
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

        int effectedNum = courseDao.addCourse(course);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testDModifyCourse(){
        Course course = new Course();
        course.setCourseId(3L);
        course.setLastEditTime(new Date());
        Teacher teacher = new Teacher();
        teacher.setTeacherId(22L);
        course.setTeacher(teacher);

        int effectedNum = courseDao.modifyCourse(course);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testEDeleteCourse(){
        int effectedNum = courseDao.deleteCourse(233L);
        assertEquals(1,effectedNum);
    }
}
