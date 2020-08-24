package com.bjtu.edu.dao;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.entity.Course;
import com.bjtu.edu.entity.Homework;
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
 * @description: HomeworkDao层测试类
 * @author: ysp
 * @create: 2020/08/18
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomeworkDaoTest extends BaseTest {
    @Autowired
    private HomeworkDao homeworkDao;

    @Test
    public void testAQueryHomework(){
        List<Homework> homeworkList = homeworkDao.queryHomework();
        assertEquals(1, homeworkList.size());
        System.out.println(homeworkList.size());
    }

    @Test
    public void testBQueryHomeworkById(){
        Homework homework = homeworkDao.queryHomeworkById(1L);
        System.out.println(homework.getHomeworkName());
    }

    @Test
    public void testCAddHomework(){
        Homework homework = new Homework();
        homework.setHomeworkName("软件测试第一次作业");
        homework.setHomeworkDesc("编写一个程序对一元二次方程根的几种情况进行单元测试");
        Course course = new Course();
        course.setCourseId(3L);
        homework.setCourse(course);
        homework.setHomeworkFile("测试");
        homework.setSubmitTime("8月30日 8:00");
        homework.setCreateTime(new Date());

        int effectedNum = homeworkDao.addHomework(homework);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testDModifyHomework(){
        Homework homework = new Homework();
        homework.setHomeworkId(2L);
        homework.setSubmitTime("8月30日 11:00");
        homework.setLastEditTime(new Date());

        int effectedNum = homeworkDao.modifyHomework(homework);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testEDeleteHomework(){
        int effectedNum = homeworkDao.deleteHomework(2L);
        assertEquals(1,effectedNum);
    }
}
