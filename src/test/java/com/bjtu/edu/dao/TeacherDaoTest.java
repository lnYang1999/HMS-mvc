package com.bjtu.edu.dao;

import com.bjtu.edu.BaseTest;
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
 * @description: TeacherDao层测试类
 * @author: ysp
 * @create: 2020/08/12
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeacherDaoTest extends BaseTest {
    @Autowired
    private TeacherDao teacherDao;

    @Test
    public void testAQueryTeacher(){
        List<Teacher> teacherList = teacherDao.queryTeacher();
        assertEquals(2, teacherList.size());
        System.out.println(teacherList.size());
    }

    @Test
    public void testBQueryTeacherById(){
        Teacher teacher = teacherDao.queryTeacherById(11L);
        System.out.println(teacher.getTeacherName());
    }

    @Test
    public void testCAddTeacher(){
        Teacher teacher = new Teacher();
        teacher.setTeacherId(33L);
        teacher.setTeacherName("张丽");
        teacher.setProfileImg(null);
        teacher.setGender("女");
        teacher.setEmail("zhangli1@bjtu.edu.cn");
        teacher.setTeacherDesc("xx大学软件学院教授，毕业于xx大学，在xx大学任教五年");
        teacher.setCreateTime(new Date());
        int effectedNum = teacherDao.addTeacher(teacher);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testDModifyTeacher(){
        Teacher teacher = new Teacher();
        teacher.setTeacherId(33L);
        teacher.setTeacherName("张梅");
        teacher.setLastEditTime(new Date());
        int effectedNum = teacherDao.modifyTeacher(teacher);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testEDeleteTeacher(){
        teacherDao.deleteTeacher(33L);
    }
}
