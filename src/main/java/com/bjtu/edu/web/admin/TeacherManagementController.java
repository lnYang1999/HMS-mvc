package com.bjtu.edu.web.admin;

import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.TeacherExecution;
import com.bjtu.edu.entity.Teacher;
import com.bjtu.edu.enums.TeacherStateEnum;
import com.bjtu.edu.service.TeacherService;
import com.bjtu.edu.util.HttpServletRequestUtil;
import com.bjtu.edu.util.Layui;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project: HMS-mvc
 * @description: 教师管理类-管理员界面
 * @author: ysp
 * @create: 2020/08/13
 */
@Controller
@RequestMapping("/admin")
public class TeacherManagementController {
    @Autowired
    private TeacherService teacherService;

    /**
     * @author: ysp
     * @description: 列出所有教师列表-返回为Layui类型
     * @createTime: 2020/8/23 10:05
     *
     * @param
     * @return com.bjtu.edu.util.Layui
     */
    @RequestMapping(value = "/listTeacher",method = RequestMethod.GET)
    @ResponseBody
    public Layui listTeacher(){
        //查询教师列表数据
        List<Teacher> teacherList = teacherService.getTeacherList();
        return Layui.data(teacherList.size(), teacherList);
    }

    /**
     * @author: ysp
     * @description: 列出所有教师列表-返回为Map类型
     * @createTime: 2020/8/23 10:05
     *
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listTeacherMap",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listTeacherMap(){
        //查询教师列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<Teacher> teacherList = teacherService.getTeacherList();
        modelMap.put("success",true);
        modelMap.put("teacherList",teacherList);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 根据teacherId返回唯一的教师信息-返回为Map类型
     * @createTime: 2020/8/23 10:05
     *
     * @param teacherId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listTeacherById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listTeacherById(@RequestParam("teacherId") Long teacherId){
        Teacher teacher = teacherService.getTeacherById(teacherId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("teacher",teacher);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 添加教师信息
     * @createTime: 2020/8/23 10:06
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的teacher实体，根据解析好的数据添加教师信息
    @RequestMapping(value = "/addTeacher",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addTeacher(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        //1.接受并转化相应的参数
        //获取前端传过来的教师信息，并将它转换成实体类；
        //同时获取前端传递过来的文件流，将它接受到teacherImg里面去
        String teacherStr = HttpServletRequestUtil.getString(request,"teacherStr");
        ObjectMapper mapper = new ObjectMapper();
        Teacher teacher;
        try {
            teacher = mapper.readValue(teacherStr,Teacher.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile teacherImg;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            teacherImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("teacherImg");
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","上传图片不能为空");
            return modelMap;
        }

        //2.添加教师信息
        if(teacher != null && teacherImg != null){
            TeacherExecution teacherExecution;
            try {
                ImageHolder imageHolder = new ImageHolder(teacherImg.getOriginalFilename(),teacherImg.getInputStream());
                teacherExecution = teacherService.addTeacher(teacher,imageHolder);
                if (teacherExecution.getState() == TeacherStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",teacherExecution.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入教师信息");
            return modelMap;
        }
        //3.返回结果
    }

    /**
     * @author: ysp
     * @description: 修改教师信息
     * @createTime: 2020/8/23 10:06
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的teacher实体，根据解析好的数据修改教师信息
    @RequestMapping(value = "/modifyTeacher",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyTeacher(HttpServletRequest request) throws IOException {
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
                    imageHolder = new ImageHolder(teacherImg.getOriginalFilename(),teacherImg.getInputStream());
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
     * @description: 删除教师信息
     * @createTime: 2020/8/23 10:07
     *
     * @param teacherId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的teacherId值删除指定教师信息
    @RequestMapping(value = "/deleteTeacher",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> deleteTeacher(@RequestParam("teacherId") Long teacherId){
        Map<String,Object> modelMap = new HashMap<>();
        teacherService.deleteTeacher(teacherId);
        modelMap.put("success",true);
        return modelMap;
    }
}
