package com.bjtu.edu.web.teacher;

import com.bjtu.edu.entity.Course;
import com.bjtu.edu.entity.StudentCourse;
import com.bjtu.edu.service.CourseService;
import com.bjtu.edu.service.StudentCourseService;
import com.bjtu.edu.util.Layui;
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
 * @description: 课程相关操作类-教师界面
 * @author: ysp
 * @create: 2020/08/20
 */
@Controller
@RequestMapping("/teacher")
public class TeacherCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentCourseService studentCourseService;

    /**
     * @author: ysp
     * @description: 列出所有课程列表-返回为Map类型
     * @createTime: 2020/8/23 10:18
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listCourseMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCourseMap(HttpServletRequest request){
        //查询课程列表数据
        Map<String,Object> modelMap = new HashMap<>();
        long teacherId = (long) request.getSession().getAttribute("teacherId");
        List<Course> courseList = courseService.getCourseByTeacherId(teacherId);
        modelMap.put("success",true);
        modelMap.put("courseList",courseList);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 根据courseId返回唯一的课程信息-返回为Map类型
     * @createTime: 2020/8/23 10:19
     *
     * @param courseId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/getCourseById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getCourseById(@RequestParam("courseId") Long courseId){
        Course course = courseService.getCourseById(courseId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("course",course);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 根据courseId返回学生列表-返回为Layui类型
     * @createTime: 2020/8/23 10:23
     *
     * @param courseId
     * @return com.bjtu.edu.util.Layui
     */
    @RequestMapping(value = "/listStudentByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listStudentByCourseId(@RequestParam("courseId") Long courseId){
        List<StudentCourse> studentCourseList = studentCourseService.getStudentByCourseId(courseId);
        return Layui.data(studentCourseList.size(),studentCourseList);
    }

    /**
     * @author: ysp
     * @description: 根据courseId返回学生列表-返回为Map类型
     * @createTime: 2020/8/23 10:23
     *
     * @param courseId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/listStudentMapByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listStudentMapByCourseId(@RequestParam("courseId") Long courseId){
        Map<String,Object> modelMap = new HashMap<>();
        List<StudentCourse> studentCourseList = studentCourseService.getStudentByCourseId(courseId);
        modelMap.put("success",true);
        modelMap.put("studentCourseList",studentCourseList);
        return modelMap;
    }
}
