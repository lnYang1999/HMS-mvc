package com.bjtu.edu.web.admin;

import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.dto.StudentExecution;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.enums.StudentStateEnum;
import com.bjtu.edu.service.StudentService;
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
 * @description: 学生管理类-管理员界面
 * @author: ysp
 * @create: 2020/08/15
 */
@Controller
@RequestMapping("/admin")
public class StudentManagementController {
    @Autowired
    private StudentService studentService;

    /**
     * @author: ysp
     * @description: 列出所有学生列表-返回为Layui类型
     * @createTime: 2020/8/23 10:01
     *
     * @param
     * @return com.bjtu.edu.util.Layui
     */
    @RequestMapping(value = "/listStudent",method = RequestMethod.GET)
    @ResponseBody
    public Layui listStudent(){
        //查询显示列表数据
        List<Student> studentList = studentService.getStudentList();
        return Layui.data(studentList.size(),studentList);
    }

    /**
     * @author: ysp
     * @description: 列出所有学生列表-返回为Map类型
     * @createTime: 2020/8/23 10:01
     *
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listStudentMap",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listStudentMap(){
        //查询学生列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<Student> studentList = studentService.getStudentList();
        modelMap.put("success",true);
        modelMap.put("studentList",studentList);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 根据studentId返回唯一的学生信息-返回为Map类型
     * @createTime: 2020/8/23 10:02
     *
     * @param studentId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listStudentById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listStudentById(@RequestParam("studentId") Long studentId){
        Student student = studentService.getStudentById(studentId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("student",student);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 添加学生信息
     * @createTime: 2020/8/23 10:02
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的student实体，根据解析好的数据添加学生信息
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addStudent(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        //1.接受并转化相应的参数
        //获取前端传过来的学生信息，并将它转换成实体类；
        //同时获取前端传递过来的文件流，将它接受到studentImg里面去
        String studentStr = HttpServletRequestUtil.getString(request,"studentStr");
        if (studentStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入学生信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Student student;
        try {
            student = mapper.readValue(studentStr,Student.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile studentImg;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        studentImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("studentImg");

        //2.添加学生信息
        StudentExecution studentExecution;
        try {
            ImageHolder imageHolder = null;
            if (studentImg != null){
                imageHolder = new ImageHolder(studentImg.getOriginalFilename(),studentImg.getInputStream());
            }
            studentExecution = studentService.addStudent(student,imageHolder);
            if (studentExecution.getState() == StudentStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",studentExecution.getStateInfo());
            }
        } catch (IOException e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
        //3.返回结果
    }

    /**
     * @author: ysp
     * @description: 修改学生信息
     * @createTime: 2020/8/23 10:02
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的student实体，根据解析好的数据修改学生信息
    @RequestMapping(value = "/modifyStudent",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyTeacher(HttpServletRequest request) throws IOException {
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
        try {
            ImageHolder imageHolder = null;
            // 开始进行学生信息变更操作
            if (studentImg != null){
                imageHolder = new ImageHolder(studentImg.getOriginalFilename(),studentImg.getInputStream());
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
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 删除学生信息
     * @createTime: 2020/8/23 10:03
     *
     * @param studentId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的studentId值删除指定学生信息
    @RequestMapping(value = "/deleteStudent",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> deleteStudent(@RequestParam("studentId") Long studentId){
        Map<String,Object> modelMap = new HashMap<>();
        studentService.deleteStudent(studentId);
        modelMap.put("success",true);
        return modelMap;
    }
}
