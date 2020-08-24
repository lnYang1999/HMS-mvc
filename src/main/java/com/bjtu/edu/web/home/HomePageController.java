package com.bjtu.edu.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @project: HMS-mvc
 * @description: 跳转界面相关类-登录界面
 * @author: ysp
 * @create: 2020/08/13
 */
@Controller
@RequestMapping("home")
public class HomePageController {
    /**
     * 学生主界面路由
     */
    @RequestMapping(value = "/studentIndex",method = RequestMethod.GET)
    private String studentIndex() {
        return "student/student_index";
    }

    /**
     * 教师主界面路由
     */
    @RequestMapping(value = "/teacherIndex",method = RequestMethod.GET)
    private String teacherIndex() {
        return "teacher/teacher_index";
    }

    /**
     * 管理员主界面路由
     */
    @RequestMapping(value = "/adminIndex",method = RequestMethod.GET)
    private String adminIndex() {
        return "admin/admin_index";
    }

    /**
     * 退出登录返回登录界面路由
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    private String logout() { return "login"; }
}
