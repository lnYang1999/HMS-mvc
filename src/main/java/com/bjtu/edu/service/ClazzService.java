package com.bjtu.edu.service;

import com.bjtu.edu.dto.ClazzExecution;
import com.bjtu.edu.entity.Clazz;

import java.util.List;

public interface ClazzService {
    /**
     * @author: ysp
     * @description: 查询全部班级信息
     * @createTime: 2020/8/3 20:17
     *
     * @param
     * @return java.util.List<com.bjtu.edu.entity.Clazz>
     */
    List<Clazz> getClazzList();
    
    /**
     * @author: ysp
     * @description: 通过班级Id查询唯一的班级信息
     * @createTime: 2020/8/3 20:05
     *
     * @param clazzId
     * @return java.util.List<com.bjtu.edu.entity.Clazz>
     */
    Clazz getClazzById(long clazzId);

    /**
     * @author: ysp
     * @description: 新增班级信息
     * @createTime: 2020/8/3 20:06
     *
     * @param clazz
     * @return com.bjtu.edu.dto.ClazzExecution
     */
    ClazzExecution addClazz(Clazz clazz);

    /**
     * @author: ysp
     * @description: 修改班级信息
     * @createTime: 2020/8/3 20:11
     *
     * @param clazz
     * @return com.bjtu.edu.dto.ClazzExecution
     */
    ClazzExecution modifyClazz(Clazz clazz);

    /**
     * @author: ysp
     * @description: 删除指定班级
     * @createTime: 2020/8/3 20:16
     *
     * @param clazzId
     * @return com.bjtu.edu.dto.ClazzExecution
     */
    ClazzExecution deleteClazz(long clazzId);
}
