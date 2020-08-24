package com.bjtu.edu.web.student;

import com.bjtu.edu.dto.FileHolder;
import com.bjtu.edu.dto.StudentHomeworkExecution;
import com.bjtu.edu.entity.Homework;
import com.bjtu.edu.entity.Student;
import com.bjtu.edu.entity.StudentHomework;
import com.bjtu.edu.enums.HomeworkStateEnum;
import com.bjtu.edu.enums.StudentHomeworkStateEnum;
import com.bjtu.edu.service.HomeworkService;
import com.bjtu.edu.service.StudentHomeworkService;
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
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project: HMS-mvc
 * @description: 作业相关操作类-学生界面
 * @author: ysp
 * @create: 2020/08/23
 */
@Controller
@RequestMapping("/student")
public class StudentHomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private StudentHomeworkService studentHomeworkService;

    /**
     * @author: ysp
     * @description: 列出所有作业列表-返回为Map类型
     * @createTime: 2020/8/23 16:48
     *
     * @param studentId
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listHomeworkMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listHomeworkMap(@RequestParam("studentId") Long studentId, HttpServletRequest request){
        //查询作业列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<Homework> homeworkList = homeworkService.getHomeworkByStudentId(studentId);
        modelMap.put("success",true);
        modelMap.put("homeworkList",homeworkList);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 根据courseId返回作业列表-返回为Layui类型
     * @createTime: 2020/8/23 16:49
     *
     * @param courseId
     * @return com.bjtu.edu.util.Layui
     */
    @RequestMapping(value = "/listHomeworkByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listHomeworkByCourseId(@RequestParam("courseId") Long courseId){
        List<Homework> homeworkList = homeworkService.getHomeworkByCourseId(courseId);
        return Layui.data(homeworkList.size(),homeworkList);
    }

    /**
     * @author: ysp
     * @description: 根据courseId返回作业列表-返回为Map类型
     * @createTime: 2020/8/23 20:03
     *
     * @param courseId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listHomeworkMapByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object>  listHomeworkMapByCourseId(@RequestParam("courseId") Long courseId){
        Map<String,Object> modelMap = new HashMap<>();
        List<Homework> homeworkList = homeworkService.getHomeworkByCourseId(courseId);
        modelMap.put("success",true);
        modelMap.put("homeworkList",homeworkList);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 根据studentHomeworkId返回唯一的学生作业信息-返回为Map类型
     * @createTime: 2020/8/23 10:15
     *
     * @param studentHomeworkId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/getStudentHomeworkById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getStudentHomeworkById(@RequestParam("studentHomeworkId") Long studentHomeworkId){
        Map<String,Object> modelMap = new HashMap<>();
        StudentHomework studentHomework = studentHomeworkService.getStudentHomeworkById(studentHomeworkId);
        modelMap.put("success",true);
        modelMap.put("studentHomework",studentHomework);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 根据homeworkId和studentId返回学生作业列表-返回为Map类型
     * @createTime: 2020/8/23 10:15
     *
     * @param homeworkId
     * @param studentId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/getStudentHomeworkByCondition",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getStudentHomeworkByCondition(@RequestParam("homeworkId") Long homeworkId,@RequestParam("studentId") Long studentId){
        Map<String,Object> modelMap = new HashMap<>();
        StudentHomework studentHomeworkCondition = new StudentHomework();
        Student student = new Student();
        Homework homework = new Homework();
        student.setStudentId(studentId);
        homework.setHomeworkId(homeworkId);
        studentHomeworkCondition.setStudent(student);
        studentHomeworkCondition.setHomework(homework);
        StudentHomework studentHomework = studentHomeworkService.getStudentHomeworkCondition(studentHomeworkCondition);

        if (studentHomework == null){
            modelMap.put("success",false);
            return modelMap;
        }
        modelMap.put("success",true);
        modelMap.put("studentHomework",studentHomework);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 添加学生作业信息
     * @createTime: 2020/8/23 10:16
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的studentHomework实体，根据解析好的数据添加学生作业信息
    @RequestMapping(value = "/addStudentHomework",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addStudentHomework(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        //1.接受并转化相应的参数
        //获取前端传过来的作业信息，并将它转换成实体类；
        //同时获取前端传递过来的文件流，将它接受到studentHomeworkFile里面去
        String studentHomeworkStr = HttpServletRequestUtil.getString(request,"studentHomeworkStr");
        if (studentHomeworkStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入要提交的作业信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        StudentHomework studentHomework;
        try {
            studentHomework = mapper.readValue(studentHomeworkStr,StudentHomework.class);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile studentHomeworkFile;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        studentHomeworkFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("studentHomeworkFile");

        //2.添加学生作业信息
        StudentHomeworkExecution studentHomeworkExecution;
        try {
            FileHolder fileHolder = null;
            if (studentHomeworkFile != null){
                fileHolder = new FileHolder(studentHomeworkFile.getOriginalFilename(),studentHomeworkFile.getInputStream());
            }
            studentHomeworkExecution = studentHomeworkService.addStudentHomework(studentHomework,fileHolder);
            if (studentHomeworkExecution.getState() == StudentHomeworkStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",studentHomeworkExecution.getStateInfo());
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
     * @description: 修改学生作业信息
     * @createTime: 2020/8/23 10:16
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的studentHomework实体，根据解析好的数据修改学生作业信息
    @RequestMapping(value = "/modifyStudentHomework",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyStudentHomework(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        // 接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
        StudentHomework studentHomework;

        // 若请求中存在文件流，则取出相关的文件
        CommonsMultipartFile studentHomeworkFile = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            studentHomeworkFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("studentHomeworkFile");
        }

        try {
            String studentHomeworkStr = HttpServletRequestUtil.getString(request, "studentHomeworkStr");
            // 尝试获取前端传过来的表单string流并将其转换成Student实体类
            studentHomework = mapper.readValue(studentHomeworkStr, StudentHomework.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        try {
            FileHolder fileHolder = null;
            // 开始进行学生信息变更操作
            if (studentHomeworkFile != null){
                fileHolder = new FileHolder(studentHomeworkFile.getOriginalFilename(),studentHomeworkFile.getInputStream());
            }
            StudentHomeworkExecution studentHomeworkExecution = studentHomeworkService.modifyStudentHomework(studentHomework, fileHolder);
            if (studentHomeworkExecution.getState() == HomeworkStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", studentHomeworkExecution.getStateInfo());
            }
        } catch (RuntimeException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        return modelMap;
    }
}
