package com.bjtu.edu.service;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.dto.FileHolder;
import com.bjtu.edu.dto.HomeworkExecution;
import com.bjtu.edu.entity.Course;
import com.bjtu.edu.entity.Homework;
import com.bjtu.edu.enums.HomeworkStateEnum;
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
 * @description: HomeworkService层测试类
 * @author: ysp
 * @create: 2020/08/18
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomeworkServiceTest extends BaseTest {
    @Autowired
    private HomeworkService homeworkService;

    @Test
    public void testAQueryHomework(){
        List<Homework> homeworkList = homeworkService.getHomeworkList();
        assertEquals(3,homeworkList.size());
        System.out.println(homeworkList.size());
    }

    @Test
    public void testBQueryHomeworkById(){
        Homework homework = homeworkService.getHomeworkById(1L);
        assertEquals("操作系统第一次作业",homework.getHomeworkName());
        System.out.println(homework.getHomeworkName());
    }

    @Test
    @Ignore
    public void testCAddHomework() throws FileNotFoundException {
        Homework homework = new Homework();
        homework.setHomeworkName("操作系统第二次作业");
        homework.setHomeworkDesc("编写一个程序模拟哲学家进餐问题");
        homework.setSubmitTime("8月22日 11:00");
        Course course = new Course();
        course.setCourseId(1L);
        homework.setCourse(course);

        // 创建文件流
        File homeworkFile = new File("C:/Users/hp/Desktop/杨烁平-实习简历.pdf");
        InputStream is = new FileInputStream(homeworkFile);
        FileHolder fileHolder = new FileHolder(homeworkFile.getName(), is);

        HomeworkExecution homeworkExecution = homeworkService.addHomework(homework,fileHolder);
        assertEquals(HomeworkStateEnum.SUCCESS.getState(),homeworkExecution.getState());
        System.out.println(homeworkExecution.getStateInfo());
    }

    @Test
    @Ignore
    public void testDModifyHomework() throws FileNotFoundException {
        Homework homework = new Homework();
        homework.setHomeworkId(3L);
        homework.setSubmitTime("8月24日 11:00");

        // 创建文件流
        File homeworkFile = new File("C:/Users/hp/Desktop/英语短语.docx");
        InputStream is = new FileInputStream(homeworkFile);
        FileHolder fileHolder = new FileHolder(homeworkFile.getName(), is);

        HomeworkExecution homeworkExecution = homeworkService.modifyHomework(homework,fileHolder);
        assertEquals(HomeworkStateEnum.SUCCESS.getState(),homeworkExecution.getState());
        System.out.println(homeworkExecution.getStateInfo());
    }

    @Test
    public void testEDeleteHomework() throws FileNotFoundException {
        Homework homework = new Homework();
        homework.setHomeworkId(4L);
        homework.setHomeworkName("JavaEE大作业");
        homework.setHomeworkDesc("编写一个作业管理系统");
        homework.setSubmitTime("8月25日 11:00");
        Course course = new Course();
        course.setCourseId(4L);
        homework.setCourse(course);

        // 创建文件流
        File homeworkFile = new File("C:/Users/hp/Desktop/杨烁平-实习简历.pdf");
        InputStream is = new FileInputStream(homeworkFile);
        FileHolder fileHolder = new FileHolder(homeworkFile.getName(), is);

        HomeworkExecution homeworkExecution1 = homeworkService.addHomework(homework,fileHolder);
        assertEquals(HomeworkStateEnum.SUCCESS.getState(),homeworkExecution1.getState());
        System.out.println(homeworkExecution1.getStateInfo());

        HomeworkExecution homeworkExecution2 = homeworkService.deleteHomework(4L);
        assertEquals(HomeworkStateEnum.SUCCESS.getState(),homeworkExecution2.getState());
        System.out.println(homeworkExecution2.getStateInfo());
    }
}
