package com.bjtu.edu.dao;

import com.bjtu.edu.entity.Admin;

/**
 * @author: ysp
 * @description: 管理员实体类dao层接口
 * @createTime: 2020/8/12 11:23
 */
public interface AdminDao {
    /**
     * @author: ysp
     * @description: 通过adminId查询指定管理员信息
     * @createTime: 2020/8/12 22:06
     *
     * @param adminId
     * @return com.bjtu.edu.entity.Admin
     */
    Admin queryAdminById(long adminId);

    /**
     * @author: ysp
     * @description: 修改管理员信息
     * @createTime: 2020/8/14 19:21
     *
     * @param admin
     * @return int
     */
    int modifyAdmin(Admin admin);
}
