package com.bjtu.edu.web.student;

import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.StudentExecution;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.enums.StudentStateEnum;
import com.bjtu.edu.service.StudentService;
import com.bjtu.edu.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @project: HMS-mvc
 * @description: 设置管理类-学生界面
 * @author: ysp
 * @create: 2020/08/23
 */
@Controller
@RequestMapping("/student")
public class StudentSettingsController {
    @Autowired
    private StudentService studentService;


    /**
     * @author: ysp
     * @description: 获取学生信息
     * @createTime: 2020/8/23 10:23
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/getStudentInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getStudentInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        long studentId = (long) request.getSession().getAttribute("studentId");
        Student student = studentService.getStudentById(studentId);
        modelMap.put("success",true);
        modelMap.put("student",student);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 修改学生信息
     * @createTime: 2020/8/23 10:24
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/modifyStudent", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyStudent(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        // 接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
        Student student;

        // 若请求中存在文件流，则取出相关的文件
        CommonsMultipartFile studentImg = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            studentImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("studentImg");
        }

        try {
            String studentStr = HttpServletRequestUtil.getString(request, "studentStr");
            // 尝试获取前端传过来的表单string流并将其转换成Student实体类
            student = mapper.readValue(studentStr, Student.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        // 非空判断
        if (student != null) {
            try {
                ImageHolder imageHolder = null;
                // 开始进行教师信息变更操作
                if (studentImg != null){
                    imageHolder = new ImageHolder(studentImg.getOriginalFilename(), studentImg.getInputStream());
                }
                StudentExecution studentExecution = studentService.modifyStudent(student, imageHolder);
                if (studentExecution.getState() == StudentStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", studentExecution.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入要修改的学生信息");
        }
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 修改密码
     * @createTime: 2020/8/23 10:12
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应类型，根据解析好的数据修改密码
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyPassword(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String oldPassword = HttpServletRequestUtil.getString(request,"oldPassword");
        String newPassword = HttpServletRequestUtil.getString(request,"newPassword");
        long studentId = (long) request.getSession().getAttribute("studentId");
        Student student = studentService.getStudentById(studentId);
        if (!student.getPassword().equals(oldPassword)){
            modelMap.put("success",false);
            modelMap.put("errMsg", "原密码不正确");
            return modelMap;
        }
        student.setPassword(newPassword);
        StudentExecution studentExecution = studentService.modifyStudent(student,null);
        if (studentExecution.getState() == StudentStateEnum.SUCCESS.getState()){
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", studentExecution.getStateInfo());
        }
        return modelMap;
    }
}
