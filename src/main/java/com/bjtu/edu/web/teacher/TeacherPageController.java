package com.bjtu.edu.web.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @project: HMS-mvc
 * @description: 跳转界面相关类-教师界面
 * @author: ysp
 * @create: 2020/08/19
 */
@Controller
@RequestMapping("teacher")
public class TeacherPageController {
    /**
     * 教师主界面路由
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private String index() { return "teacher/teacher_index"; }

    /**
     * 基本设置界面路由
     */
    @RequestMapping(value = "/set",method = RequestMethod.GET)
    private String set() { return "teacher/set"; }

    /**
     * 发布作业界面路由
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    private String add() { return "teacher/add_homework"; }

    /**
     * 授课列表界面路由
     */
    @RequestMapping(value = "/course",method = RequestMethod.GET)
    private String course() { return "teacher/course_list"; }

    /**
     * 课程详情界面路由
     */
    @RequestMapping(value = "/courseInfo",method = RequestMethod.GET)
    private String courseInfo() { return "teacher/course_info"; }

    /**
     * 选课学生列表界面路由
     */
    @RequestMapping(value = "/studentInfo",method = RequestMethod.GET)
    private String studentInfo() { return "teacher/student_info"; }

    /**
     * 学生提交作业列表界面路由
     */
    @RequestMapping(value = "/submitHomeworkList",method = RequestMethod.GET)
    private String submitHomeworkList() { return "teacher/submitHomework_list"; }

    /**
     * 学生提交作业详情界面路由
     */
    @RequestMapping(value = "/studentHomeworkInfo",method = RequestMethod.GET)
    private String studentHomeworkInfo() { return "teacher/studentHomework_info"; }

    /**
     * 作业提交情况界面路由
     */
    @RequestMapping(value = "/studentHomeworkList",method = RequestMethod.GET)
    private String studentHomeworkList() { return "teacher/studentHomework_list"; }

    /**
     * 作业列表界面路由
     */
    @RequestMapping(value = "/homework",method = RequestMethod.GET)
    private String homework() { return "teacher/homework_list"; }
}
