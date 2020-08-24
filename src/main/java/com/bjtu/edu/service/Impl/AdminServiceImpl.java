package com.bjtu.edu.service.Impl;

import com.bjtu.edu.dao.AdminDao;
import com.bjtu.edu.dto.AdminExecution;
import com.bjtu.edu.dto.ImageHolder;
import com.bjtu.edu.entity.Admin;
import com.bjtu.edu.enums.AdminStateEnum;
import com.bjtu.edu.exception.AdminOperationException;
import com.bjtu.edu.service.AdminService;
import com.bjtu.edu.util.ImageUtil;
import com.bjtu.edu.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @project: HMS-mvc
 * @description: AdminService实现类
 * @author: ysp
 * @create: 2020/08/12
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    /**
     * @param adminId
     * @return com.bjtu.edu.entity.Admin
     * @author: ysp
     * @description: 通过管理员ID获取指定管理员信息
     * @createTime: 2020/8/12 22:43
     */
    @Override
    public Admin getAdminById(long adminId) {
        return adminDao.queryAdminById(adminId);
    }

    /**
     * @param admin
     * @param imageHolder
     * @return com.bjtu.edu.dto.AdminExecution
     * @author: ysp
     * @description: 修改管理员信息
     * @createTime: 2020/8/14 19:32
     */
    @Override
    public AdminExecution modifyAdmin(Admin admin, ImageHolder imageHolder) {
        //空值判断
        if (admin == null || admin.getAdminId() == null){
            return new AdminExecution(AdminStateEnum.EMPTY);
        }
        try {
            //1.判断是否需要处理图片
            if (imageHolder != null){
                if (imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())){
                    Admin tempAdmin = adminDao.queryAdminById(admin.getAdminId());
                    //如果原路径下有图片存在，则删除原路径下的所有图片
                    if (tempAdmin.getProfileImg() != null){
                        ImageUtil.deleteFileOrPath(tempAdmin.getProfileImg());
                    }
                    //添加新的图片
                    addAdminImg(admin, imageHolder);
                }
            }

            //2.修改管理员信息
            //设置更新时间
            admin.setLastEditTime(new Date());
            //修改管理员信息
            int effectedNum = adminDao.modifyAdmin(admin);
            //判断是否修改成功
            if (effectedNum <= 0){
                return new AdminExecution(AdminStateEnum.INNER_ERROR);
            }else {
                admin = adminDao.queryAdminById(admin.getAdminId());
                return new AdminExecution(AdminStateEnum.SUCCESS,admin);
            }
        }catch (Exception e){
            throw new AdminOperationException("modifyAdminError:" + e.getMessage());
        }
    }

    private void addAdminImg(Admin admin, ImageHolder thumbnail) {
        //获取admin图片目录的相对值路径
        String dest = PathUtil.getAdminImagePath(admin.getAdminId());
        String adminImgAddr = ImageUtil.generateThumbnail(thumbnail,dest);
        admin.setProfileImg(adminImgAddr);
    }
}
