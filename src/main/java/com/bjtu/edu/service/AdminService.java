package com.bjtu.edu.service;

import com.bjtu.edu.dto.AdminExecution;
import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.entity.Admin;

public interface AdminService {
    /**
     * @author: ysp
     * @description: 通过管理员ID获取指定管理员信息
     * @createTime: 2020/8/12 22:49
     *
     * @param adminId
     * @return com.bjtu.edu.entity.Admin
     */
    Admin getAdminById(long adminId);

    /**
     * @author: ysp
     * @description: 修改管理员信息
     * @createTime: 2020/8/14 19:32
     *
     * @param admin
     * @param imageHolder
     * @return com.bjtu.edu.dto.AdminExecution
     */
    AdminExecution modifyAdmin(Admin admin, ImageHolder imageHolder);
}
