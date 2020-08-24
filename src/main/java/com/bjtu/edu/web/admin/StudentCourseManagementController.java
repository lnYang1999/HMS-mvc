package com.bjtu.edu.web.admin;

import com.bjtu.edu.dto.StudentCourseExecution;
import com.bjtu.edu.entity.StudentCourse;
import com.bjtu.edu.enums.StudentCourseStateEnum;
import com.bjtu.edu.service.StudentCourseService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project: HMS-mvc
 * @description: 选课管理类-管理员界面
 * @author: ysp
 * @create: 2020/08/17
 */
@Controller
@RequestMapping("/admin")
public class StudentCourseManagementController {
    @Autowired
    private StudentCourseService studentCourseService;

    /**
     * @author: ysp
     * @description: 列出所有选课列表-返回为Layui类型
     * @createTime: 2020/8/23 9:56
     *
     * @param
     * @return com.bjtu.edu.util.Layui
     */
    @RequestMapping(value = "/listStudentCourse",method = RequestMethod.GET)
    @ResponseBody
    public Layui listStudentCourse(){
        //查询选课列表数据
        List<StudentCourse> studentCourseList = studentCourseService.getStudentCourseList();
        return Layui.data(studentCourseList.size(),studentCourseList);
    }

    /**
     * @author: ysp
     * @description: 根据studentCourseId返回唯一的选课信息-返回为Map类型
     * @createTime: 2020/8/23 9:57
     *
     * @param studentCourseId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listStudentCourseById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listStudentCourseById(@RequestParam("studentCourseId") Long studentCourseId){
        StudentCourse studentCourse = studentCourseService.getStudentCourseById(studentCourseId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("studentCourse",studentCourse);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 添加选课信息
     * @createTime: 2020/8/23 9:57
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的studentCourse实体，根据解析好的数据添加选课信息
    @RequestMapping(value = "/addStudentCourse", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addStudentCourse(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String studentCourseStr = HttpServletRequestUtil.getString(request,"studentCourseStr");
        if (studentCourseStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入选课信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        StudentCourse studentCourse;
        try {
            studentCourse = mapper.readValue(studentCourseStr, StudentCourse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        StudentCourseExecution studentCourseExecution = studentCourseService.addStudentCourse(studentCourse);
        if (studentCourseExecution.getState() == StudentCourseStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg",studentCourseExecution.getStateInfo());
        }
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 修改选课信息
     * @createTime: 2020/8/23 9:57
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的studentCourse实体，根据解析好的数据修改选课信息
    @RequestMapping(value = "/modifyStudentCourse", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyStudentCourse(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String studentCourseStr = HttpServletRequestUtil.getString(request,"studentCourseStr");
        if (studentCourseStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入选课信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        StudentCourse studentCourse;
        try {
            studentCourse = mapper.readValue(studentCourseStr, StudentCourse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        if (studentCourse != null && studentCourse.getStudentCourseId() != null){
            StudentCourseExecution studentCourseExecution = studentCourseService.modifyStudentCourse(studentCourse);
            if (studentCourseExecution.getState() == StudentCourseStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg",studentCourseExecution.getStateInfo());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入要修改的选课信息Id号");
            return modelMap;
        }
    }

    /**
     * @author: ysp
     * @description: 删除选课信息
     * @createTime: 2020/8/23 9:58
     *
     * @param studentCourseId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的studentCourseId值删除指定选课信息
    @RequestMapping(value = "/deleteStudentCourse", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> deleteStudentCourse(@RequestParam("studentCourseId") Long studentCourseId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (studentCourseId != null){
            StudentCourseExecution studentCourseExecution = studentCourseService.deleteStudentCourse(studentCourseId);
            if (studentCourseExecution.getState() == StudentCourseStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg",studentCourseExecution.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "未选择要删除的选课信息");
        }
        return modelMap;
    }
}
