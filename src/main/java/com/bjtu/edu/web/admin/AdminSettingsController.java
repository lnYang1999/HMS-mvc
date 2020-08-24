package com.bjtu.edu.web.admin;

import com.bjtu.edu.dto.AdminExecution;
import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.entity.Admin;
import com.bjtu.edu.enums.AdminStateEnum;
import com.bjtu.edu.service.AdminService;
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
 * @description: 设置管理类-管理员界面
 * @author: ysp
 * @create: 2020/08/14
 */
@Controller
@RequestMapping("/admin")
public class AdminSettingsController {
    @Autowired
    private AdminService adminService;

    /**
     * @author: ysp
     * @description: 获取管理员信息
     * @createTime: 2020/8/13 9:38
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //从session中获取当前管理员的Id，并通过Id获取管理员的全部信息，同时返回Map
    //前端可以通过返回的Map来获取到后端传递的值
    @RequestMapping(value = "/getAdminInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getAdminInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        long adminId = (long) request.getSession().getAttribute("adminId");
        Admin admin = adminService.getAdminById(adminId);
        modelMap.put("success",true);
        modelMap.put("admin",admin);
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 修改管理员信息
     * @createTime: 2020/8/13 9:42
     *
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    //获取前端ajax传递的字符串，解析字符串为相应的图片流和admin实体，根据解析好的数据修改管理员信息
    @RequestMapping(value = "/modifyAdmin", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyAdmin(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        // 接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
        Admin admin;

        // 若请求中存在文件流，则取出相关的文件
        CommonsMultipartFile adminImg = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            adminImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("adminImg");
        }

        try {
            String adminStr = HttpServletRequestUtil.getString(request, "adminStr");
            // 尝试获取前端传过来的表单string流并将其转换成Admin实体类
            admin = mapper.readValue(adminStr, Admin.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        // 非空判断
        if (admin != null) {
            try {
                ImageHolder imageHolder = null;
                // 开始进行管理员信息变更操作
                if (adminImg != null){
                    imageHolder = new ImageHolder(adminImg.getOriginalFilename(),adminImg.getInputStream());
                }
                AdminExecution adminExecution = adminService.modifyAdmin(admin, imageHolder);
                if (adminExecution.getState() == AdminStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", adminExecution.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入要修改的管理员信息");
        }
        return modelMap;
    }

    /**
     * @author: ysp
     * @description: 修改密码
     * @createTime: 2020/8/23 9:44
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
        long adminId = (long) request.getSession().getAttribute("adminId");
        Admin admin = adminService.getAdminById(adminId);
        if (!admin.getPassword().equals(oldPassword)){
            modelMap.put("success",false);
            modelMap.put("errMsg", "原密码不正确");
            return modelMap;
        }
        admin.setPassword(newPassword);
        AdminExecution adminExecution = adminService.modifyAdmin(admin,null);
        if (adminExecution.getState() == AdminStateEnum.SUCCESS.getState()){
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", adminExecution.getStateInfo());
        }
        return modelMap;
    }
}
