package com.bjtu.edu.web.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @project: HMS-mvc
 * @description: 跳转界面相关类-学生界面
 * @author: ysp
 * @create: 2020/08/23
 */
@Controller
@RequestMapping("student")
public class StudentPageController {
    /**
     * 学生主界面路由
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private String index() { return "student/student_index"; }

    /**
     * 基本设置界面路由
     */
    @RequestMapping(value = "/set",method = RequestMethod.GET)
    private String set() { return "student/set"; }

    /**
     * 提交作业界面路由
     */
    @RequestMapping(value = "/submit",method = RequestMethod.GET)
    private String submit() { return "student/submit_homework"; }

    /**
     * 选课列表界面路由
     */
    @RequestMapping(value = "/course",method = RequestMethod.GET)
    private String course() { return "student/course_list"; }

    /**
     * 课程详情界面路由
     */
    @RequestMapping(value = "/courseInfo",method = RequestMethod.GET)
    private String courseInfo() { return "student/course_info"; }

    /**
     * 选课学生列表界面路由
     */
    @RequestMapping(value = "/studentInfo",method = RequestMethod.GET)
    private String studentInfo() { return "student/student_info"; }

    /**
     * 作业提交情况界面路由
     */
    @RequestMapping(value = "/studentHomeworkList",method = RequestMethod.GET)
    private String studentHomeworkList() { return "student/studentHomework_list"; }

    /**
     * 提交作业详情界面路由
     */
    @RequestMapping(value = "/studentHomeworkInfo",method = RequestMethod.GET)
    private String studentHomeworkInfo() { return "student/studentHomework_info"; }

    /**
     * 修改提交的作业界面路由
     */
    @RequestMapping(value = "/modifySubmitHomework",method = RequestMethod.GET)
    private String modifySubmitHomework() { return "student/modify_submitHomework"; }
}
