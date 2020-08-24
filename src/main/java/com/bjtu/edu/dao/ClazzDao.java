package com.bjtu.edu.dao;

import com.bjtu.edu.entity.Clazz;

import java.util.List;

/**
 * @author: ysp
 * @description: 班级实体类dao层接口
 * @createTime: 2020/8/2 11:23
 */
public interface ClazzDao {
    /**
     * @author: ysp
     * @description: 查询所有班级列表
     * @createTime: 2020/8/3 11:38
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.Clazz>
     */
    List<Clazz> queryClazz();

    /**
     * @author: ysp
     * @description: 通过clazzId查询指定班级信息
     * @createTime: 2020/8/3 11:41
     *
     * @param clazzId
     * @return com.bjtu.edu.entity.Clazz
     */
    Clazz queryClazzById(long clazzId);

    /**
     * @author: ysp
     * @description: 新增班级信息
     * @createTime: 2020/8/3 11:44
     *
     * @param clazz
     * @return int
     */
    int addClazz(Clazz clazz);

    /**
     * @author: ysp
     * @description: 修改班级信息
     * @createTime: 2020/8/3 13:41
     *
     * @param aClazz
     * @return int
     */
    int modifyClazz(Clazz aClazz);

    /**
     * @author: ysp
     * @description: 通过clazzId删除指定班级
     * @createTime: 2020/8/3 11:52
     *
     * @param clazzId
     * @return int
     */
    int deleteClazz(long clazzId);
}
