package com.bjtu.edu.service;

import com.bjtu.edu.BaseTest;
import com.bjtu.edu.entity.Admin;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @project: HMS-mvc
 * @description: AdminService层测试类
 * @author: ysp
 * @create: 2020/08/12
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminServiceTest extends BaseTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void testBQueryAdminById(){
        Admin admin = adminService.getAdminById(9180L);
        assertEquals("ysp",admin.getAdminName());
        System.out.println(admin.getAdminName());
    }
}
