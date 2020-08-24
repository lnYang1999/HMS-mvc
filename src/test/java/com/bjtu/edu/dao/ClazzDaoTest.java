package com.bjtu.edu.dao;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.entity.Clazz;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @project: HMS-mvc
 * @description: ClassDao层测试类
 * @author: ysp
 * @create: 2020/08/03
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClazzDaoTest extends BaseTest {
    @Autowired
    private ClazzDao clazzDao;

    @Test
    public void testAQueryClazz(){
        List<Clazz> clazzList = clazzDao.queryClazz();
        assertEquals(2, clazzList.size());
        System.out.println(clazzList.size());
    }

    @Test
    public void testBQueryClazzById(){
        Clazz clazz = clazzDao.queryClazzById(1701L);
        System.out.println(clazz.getClazzDesc());
    }

    @Test
    public void testCAddClazz(){
        Clazz clazz = new Clazz();
        clazz.setClazzId(1801L);
        clazz.setClazzNumber(40);
        clazz.setClazzDesc("软件学院1801班");
        clazz.setCreateTime(new Date());
        int effectedNum = clazzDao.addClazz(clazz);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testDModifyClazz(){
        Clazz clazz = new Clazz();
        clazz.setClazzId(1801L);
        clazz.setClazzNumber(80);
        clazz.setClazzDesc("计算机学院1801班");
        clazz.setLastEditTime(new Date());
        int effectedNum = clazzDao.modifyClazz(clazz);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }

    @Test
    public void testEDeleteClazz(){
        clazzDao.deleteClazz(1801L);
    }
}
