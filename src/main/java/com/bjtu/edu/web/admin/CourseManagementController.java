package com.bjtu.edu.web.admin;

import com.bjtu.edu.dto.CourseExecution;
import com.bjtu.edu.entity.Course;
import com.bjtu.edu.enums.ClazzStateEnum;
import com.bjtu.edu.enums.CourseStateEnum;
import com.bjtu.edu.service.CourseService;
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
 * @description: 课程管理类-管理员界面
 * @author: ysp
 * @create: 2020/08/17
 */
@Controller
@RequestMapping("/admin")
public class CourseManagementController {
    @Autowired
    private CourseService courseService;

    /**
     * @author: ysp
     * @description: 列出所有课程列表-返回为Layui类型
     * @createTime: 2020/8/13 9:51
     *
     * @param
     * @return com.bjtu.edu.util.Layui
     */
    @RequestMapping(value = "/listCourse",method = RequestMethod.GET)
    @ResponseBody
    private Layui listCourse(){
        //查询课程列表数据
        List<Course> courseList = courseService.getCourseList();
        return Layui.data(courseList.size(),courseList);
    }

    /**
     * @author: ysp
     * @description: 列出所有课程列表-返回为Map类型
     * @createTime: 2020/8/13 9:52
     *
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCourseMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCourseMap(){
        //查询课程列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<Course> courseList = courseService.getCourseList();
        modelMap.put("success",true);
        modelMap.put("courseList",courseList);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 根据courseId返回唯一的课程信息-返回为Map类型
     * @createTime: 2020/8/13 9:52
     *
     * @param courseId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCourseById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCourseById(@RequestParam("courseId") Long courseId){
        Course course = courseService.getCourseById(courseId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("course",course);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 添加课程信息
     * @createTime: 2020/8/13 9:52
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的course实体，根据解析好的数据添加课程信息
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addCourse(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        String courseStr = HttpServletRequestUtil.getString(request,"courseStr");
        if (courseStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入课程信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Course course;
        try {
            course = mapper.readValue(courseStr, Course.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        CourseExecution courseExecution = courseService.addCourse(course);
        if (courseExecution.getState() == ClazzStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg",courseExecution.getStateInfo());
        }
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 修改课程信息
     * @createTime: 2020/8/23 9:53
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的course实体，根据解析好的数据修改课程信息
    @RequestMapping(value = "/modifyCourse", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyCourse(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //1.接受并转化相应的参数，包括课程信息
        //获取前端传过来的班级信息，并将它转换成Course实体类；
        String courseStr = HttpServletRequestUtil.getString(request,"courseStr");
        ObjectMapper mapper = new ObjectMapper();
        Course course;
        try {
            course = mapper.readValue(courseStr,Course.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //2.修改课程
        if(course != null && course.getCourseId() != null){
            CourseExecution courseExecution;
            courseExecution = courseService.modifyCourse(course);
            if (courseExecution.getState() == CourseStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",courseExecution.getStateInfo());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入要修改的课程信息");
            return modelMap;
        }
        //3.返回结果
    }

    /**
     * @author: ysp
     * @description: 删除课程信息
     * @createTime: 2020/8/23 9:54
     *
     * @param courseId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //根据前端路由路径中传递的courseId值删除指定课程信息
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> deleteCourse(@RequestParam("courseId") Long courseId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (courseId != null) {
            CourseExecution courseExecution = courseService.deleteCourse(courseId);
            if (courseExecution.getState() == CourseStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", courseExecution.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "未选择要删除的班级");
        }
        return modelMap;
    }
}
