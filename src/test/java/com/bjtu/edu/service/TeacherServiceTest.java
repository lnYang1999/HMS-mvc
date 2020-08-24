package com.bjtu.edu.service;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.TeacherExecution;
import com.bjtu.edu.entity.Teacher;
import com.bjtu.edu.enums.TeacherStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
 * @description: TeacherService层测试类
 * @author: ysp
 * @create: 2020/08/12
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeacherServiceTest extends BaseTest {
    @Autowired
    private TeacherService teacherService;

    @Test
    public void testAQueryTeacher(){
        List<Teacher> teacherList = teacherService.getTeacherList();
        assertEquals(2,teacherList.size());
        System.out.println(teacherList.size());
    }

    @Test
    public void testBQueryTeacherById(){
        Teacher teacher = teacherService.getTeacherById(11L);
        assertEquals("赵刚",teacher.getTeacherName());
        System.out.println(teacher.getTeacherName());
    }

    @Test
    public void testCAddTeacher() throws FileNotFoundException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(33L);
        teacher.setTeacherName("张丽");
        teacher.setGender("女");
        teacher.setEmail("zhangli1@bjtu.edu.cn");
        teacher.setTeacherDesc("xx大学软件学院教授，毕业于xx大学，在xx大学任教五年");
        teacher.setCreateTime(new Date());

        // 创建缩略图文件流
        File thumbnailFile = new File("F:/照片/17301053.JPG");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);

        TeacherExecution teacherExecution = teacherService.addTeacher(teacher,thumbnail);
        assertEquals(TeacherStateEnum.SUCCESS.getState(),teacherExecution.getState());
        System.out.println(teacherExecution.getStateInfo());
    }

    @Test
    public void testDModifyTeacher() throws FileNotFoundException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(33L);
        teacher.setTeacherName("张梅");
        teacher.setLastEditTime(new Date());

        // 创建缩略图文件流
        File thumbnailFile = new File("E:/壁纸/微信图片_20180221212912.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);

        TeacherExecution teacherExecution = teacherService.modifyTeacher(teacher,thumbnail);
        assertEquals(TeacherStateEnum.SUCCESS.getState(),teacherExecution.getState());
        System.out.println(teacherExecution.getStateInfo());
    }

    @Test
    public void testEDeleteTeacher(){
        TeacherExecution teacherExecution = teacherService.deleteTeacher(33L);
        assertEquals(TeacherStateEnum.SUCCESS.getState(),teacherExecution.getState());
        System.out.println(teacherExecution.getStateInfo());
    }
}
