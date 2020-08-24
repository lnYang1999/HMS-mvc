package com.bjtu.edu.web.home;

import com.bjtu.edu.entity.Admin;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.entity.Teacher;
import com.bjtu.edu.service.AdminService;
import com.bjtu.edu.service.StudentService;
import com.bjtu.edu.service.TeacherService;
import com.bjtu.edu.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @project: HMS-mvc
 * @description: 登录相关管理类
 * @author: ysp
 * @create: 2020/08/13
 */
@Controller
@RequestMapping("/home")
public class HomeManagementController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;

    /**
     * @author: ysp
     * @description: 登录信息处理
     * @createTime: 2020/8/14 14:17
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> login(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();

        int type = HttpServletRequestUtil.getInt(request,"type");
        long userId = HttpServletRequestUtil.getLong(request,"userId");
        String inputPassword = HttpServletRequestUtil.getString(request,"password");
        String realPassword;

        if (type == 0){
            Student student = studentService.getStudentById(userId);
            realPassword = student.getPassword();
            if (realPassword.equals(inputPassword)){
                request.getSession().setAttribute("studentId",userId);
                modelMap.put("redirect",true);
                modelMap.put("url","/home/studentIndex?studentId="+student.getStudentId());
            }else {
                modelMap.put("redirect",false);
            }
        }else if (type == 1){
            Teacher teacher = teacherService.getTeacherById(userId);
            realPassword = teacher.getPassword();
            if (realPassword.equals(inputPassword)){
                request.getSession().setAttribute("teacherId",userId);
                modelMap.put("redirect",true);
                modelMap.put("url","/home/teacherIndex?teacherId="+teacher.getTeacherId());
            }else {
                modelMap.put("redirect",false);
            }
        }else if (type == 2){
            Admin admin = adminService.getAdminById(userId);
            realPassword = admin.getPassword();
            if (realPassword.equals(inputPassword)){
                request.getSession().setAttribute("adminId",userId);
                modelMap.put("redirect",true);
                modelMap.put("url","/home/adminIndex?adminName="+admin.getAdminName());
            }else {
                modelMap.put("redirect",false);
            }
        }else {
            modelMap.put("redirect",false);
        }
        return modelMap;
    }
}
