package com.bjtu.edu.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @project: HMS-mvc
 * @description: 界面跳转管理类-管理员界面
 * @author: ysp
 * @create: 2020/08/13
 */
@Controller
@RequestMapping("admin")
public class AdminPageController {
    /**
     * 添加教师界面路由
     */
    @RequestMapping(value = "/teacherAdd",method = RequestMethod.GET)
    private String teacherAdd() { return "admin/add_teacher"; }

    /**
     * 教师列表界面路由
     */
    @RequestMapping(value = "/teacherList",method = RequestMethod.GET)
    private String teacherList() { return "admin/teacher_list"; }

    /**
     * 添加学生界面路由
     */
    @RequestMapping(value = "/studentAdd",method = RequestMethod.GET)
    private String studentAdd() { return "admin/add_student"; }

    /**
     * 学生列表界面路由
     */
    @RequestMapping(value = "/studentList",method = RequestMethod.GET)
    private String studentList() { return "admin/student_list"; }

    /**
     * 添加班级界面路由
     */
    @RequestMapping(value = "/clazzAdd",method = RequestMethod.GET)
    private String clazzAdd() { return "admin/add_clazz"; }

    /**
     * 班级列表界面路由
     */
    @RequestMapping(value = "/clazzList",method = RequestMethod.GET)
    private String clazzList() { return "admin/clazz_list"; }

    /**
     * 添加课程界面路由
     */
    @RequestMapping(value = "/courseAdd",method = RequestMethod.GET)
    private String courseAdd() { return "admin/add_course"; }

    /**
     * 课程列表界面路由
     */
    @RequestMapping(value = "/courseList",method = RequestMethod.GET)
    private String courseList() { return "admin/course_list"; }

    /**
     * 添加选课信息界面路由
     */
    @RequestMapping(value = "/studentCourseAdd",method = RequestMethod.GET)
    private String studentCourseAdd() { return "admin/add_studentCourse"; }

    /**
     * 选课列表界面路由
     */
    @RequestMapping(value = "/studentCourseList",method = RequestMethod.GET)
    private String studentCourseList() { return "admin/studentCourse_list"; }

    /**
     * 作业列表界面路由
     */
    @RequestMapping(value = "/homeworkList",method = RequestMethod.GET)
    private String homeworkList() { return "admin/homework_list"; }

    /**
     * 学生作业列表界面路由
     */
    @RequestMapping(value = "/studentHomeworkList",method = RequestMethod.GET)
    private String studentHomeworkList() { return "admin/studentHomework_list"; }

    /**
     * 个人信息界面路由
     */
    @RequestMapping(value = "/adminInfo",method = RequestMethod.GET)
    private String adminModify() { return "admin/info"; }

    /**
     * 修改密码界面路由
     */
    @RequestMapping(value = "/passwordModify",method = RequestMethod.GET)
    private String passwordModify() { return "admin/password"; }
}
