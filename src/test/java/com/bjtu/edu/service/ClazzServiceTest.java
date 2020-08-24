package com.bjtu.edu.service;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.dto.ClazzExecution;
import com.bjtu.edu.entity.Clazz;
import com.bjtu.edu.enums.ClazzStateEnum;
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
 * @description: ClazzService层测试类
 * @author: ysp
 * @create: 2020/08/03
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClazzServiceTest extends BaseTest {
    @Autowired
    private ClazzService clazzService;

    @Test
    public void testAQueryClazz(){
        List<Clazz> classList = clazzService.getClazzList();
        assertEquals(2,classList.size());
        System.out.println(classList.size());
    }

    @Test
    public void testBQueryClazzById(){
        Clazz clazz = clazzService.getClazzById(1701L);
        assertEquals("软件学院1701班",clazz.getClazzDesc());
        System.out.println(clazz.getClazzDesc());
    }

    @Test
    public void testCAddClazz(){
        Clazz clazz = new Clazz();
        clazz.setClazzId(1801L);
        clazz.setClazzNumber(40);
        clazz.setClazzDesc("软件学院1801班");
        clazz.setCreateTime(new Date());
        ClazzExecution clazzExecution = clazzService.addClazz(clazz);
        assertEquals(ClazzStateEnum.SUCCESS.getState(),clazzExecution.getState());
        System.out.println(clazzExecution.getStateInfo());
    }

    @Test
    public void testDUpdateClazz(){
        Clazz clazz = new Clazz();
        clazz.setClazzId(1801L);
        clazz.setClazzNumber(80);
        clazz.setClazzDesc("计算机学院1801班");
        ClazzExecution clazzExecution = clazzService.modifyClazz(clazz);
        assertEquals(ClazzStateEnum.SUCCESS.getState(),clazzExecution.getState());
        System.out.println(clazzExecution.getStateInfo());
    }

    @Test
    public void testEDeleteClazz(){
        ClazzExecution clazzExecution = clazzService.deleteClazz(1801L);
        assertEquals(ClazzStateEnum.SUCCESS.getState(),clazzExecution.getState());
        System.out.println(clazzExecution.getStateInfo());
    }
}
