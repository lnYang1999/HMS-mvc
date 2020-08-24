package com.bjtu.edu.web.teacher;

import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.TeacherExecution;
import com.bjtu.edu.entity.Teacher;
import com.bjtu.edu.enums.TeacherStateEnum;
import com.bjtu.edu.service.TeacherService;
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
 * @description: 设置管理类-教师界面
 * @author: ysp
 * @create: 2020/08/19
 */
@Controller
@RequestMapping("/teacher")
public class TeacherSettingsController {
    @Autowired
    private TeacherService teacherService;

    /**
     * @author: ysp
     * @description: 获取教师信息
     * @createTime: 2020/8/23 10:11
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/getTeacherInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getTeacherInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        long teacherId = (long) request.getSession().getAttribute("teacherId");
        Teacher teacher = teacherService.getTeacherById(teacherId);
        modelMap.put("success",true);
        modelMap.put("teacher",teacher);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 修改教师信息
     * @createTime: 2020/8/23 10:11
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/modifyTeacher", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyTeacher(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        // 接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
        Teacher teacher;

        // 若请求中存在文件流，则取出相关的文件
        CommonsMultipartFile teacherImg = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            teacherImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("teacherImg");
        }

        try {
            String teacherStr = HttpServletRequestUtil.getString(request, "teacherStr");
            // 尝试获取前端传过来的表单string流并将其转换成Teacher实体类
            teacher = mapper.readValue(teacherStr, Teacher.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        // 非空判断
        if (teacher != null) {
            try {
                ImageHolder imageHolder = null;
                // 开始进行教师信息变更操作
                if (teacherImg != null){
                    imageHolder = new ImageHolder(teacherImg.getOriginalFilename(), teacherImg.getInputStream());
                }
                TeacherExecution teacherExecution = teacherService.modifyTeacher(teacher, imageHolder);
                if (teacherExecution.getState() == TeacherStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", teacherExecution.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入要修改的教师信息");
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
        long teacherId = (long) request.getSession().getAttribute("teacherId");
        Teacher teacher = teacherService.getTeacherById(teacherId);
        if (!teacher.getPassword().equals(oldPassword)){
            modelMap.put("success",false);
            modelMap.put("errMsg", "原密码不正确");
            return modelMap;
        }
        teacher.setPassword(newPassword);
        TeacherExecution teacherExecution = teacherService.modifyTeacher(teacher,null);
        if (teacherExecution.getState() == TeacherStateEnum.SUCCESS.getState()){
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", teacherExecution.getStateInfo());
        }
        return modelMap;
    }
}
