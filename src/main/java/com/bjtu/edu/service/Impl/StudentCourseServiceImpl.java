package com.bjtu.edu.service.Impl;

import com.bjtu.edu.dao.StudentCourseDao;
import com.bjtu.edu.dto.StudentCourseExecution;
import com.bjtu.edu.entity.StudentCourse;
import com.bjtu.edu.enums.StudentCourseStateEnum;
import com.bjtu.edu.exception.StudentCourseOperationException;
import com.bjtu.edu.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @project: HMS-mvc
 * @description: StudentCourseService实现类
 * @author: ysp
 * @create: 2020/08/17
 */
@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Autowired
    private StudentCourseDao studentCourseDao;

    /**
     * @return java.util.List<com.bjtu.edu.entity.StudentCourse>
     * @author: ysp
     * @description: 查询全部选课信息
     * @createTime: 2020/8/17 10:40
     */
    @Override
    public List<StudentCourse> getStudentCourseList() {
        return studentCourseDao.queryStudentCourse();
    }

    /**
     * @param studentCourseId
     * @return com.bjtu.edu.entity.StudentCourse
     * @author: ysp
     * @description: 通过选课ID获取指定选课信息
     * @createTime: 2020/8/17 10:40
     */
    @Override
    public StudentCourse getStudentCourseById(long studentCourseId) {
        return studentCourseDao.queryStudentCourseById(studentCourseId);
    }

    /**
     * @param studentId
     * @return java.util.List<com.bjtu.edu.entity.StudentCourse>
     * @author: ysp
     * @description: 通过studentId查询选课信息列表
     * @createTime: 2020/8/23 11:28
     */
    @Override
    public List<StudentCourse> getStudentCourseByStudentId(long studentId) {
        return studentCourseDao.queryStudentCourseByStudentId(studentId);
    }

    /**
     * @param courseId
     * @return com.bjtu.edu.entity.StudentCourse
     * @author: ysp
     * @description: 通过课程ID获取学生列表信息
     * @createTime: 2020/8/20 22:45
     */
    @Override
    public List<StudentCourse> getStudentByCourseId(long courseId) {
        return studentCourseDao.queryStudentByCourseId(courseId);
    }

    /**
     * @param studentCourse
     * @return com.bjtu.edu.dto.StudentCourseExecution
     * @author: ysp
     * @description: 新增选课信息
     * @createTime: 2020/8/17 10:41
     */
    @Override
    public StudentCourseExecution addStudentCourse(StudentCourse studentCourse) {
        //空值判断
        if (studentCourse == null){
            return new StudentCourseExecution(StudentCourseStateEnum.EMPTY);
        }
        //给选课信息赋初始值
        studentCourse.setCreateTime(new Date());
        //添加选课信息
        int effectedNum = studentCourseDao.addStudentCourse(studentCourse);
        //判断是否添加成功
        if (effectedNum <= 0){
            throw new StudentCourseOperationException("添加选课信息失败");
        }
        return new StudentCourseExecution(StudentCourseStateEnum.SUCCESS,studentCourse);
    }

    /**
     * @param studentCourse
     * @return com.bjtu.edu.dto.StudentCourseExecution
     * @author: ysp
     * @description: 修改选课信息
     * @createTime: 2020/8/17 10:41
     */
    @Override
    public StudentCourseExecution modifyStudentCourse(StudentCourse studentCourse) {
        //空值判断
        if (studentCourse == null || studentCourse.getStudentCourseId() == null){
            return new StudentCourseExecution(StudentCourseStateEnum.EMPTY);
        }
        //给选课信息赋初始值
        studentCourse.setLastEditTime(new Date());
        //修改选课信息
        int effectedNum = studentCourseDao.modifyStudentCourse(studentCourse);
        //判断是否修改成功
        if (effectedNum <= 0){
            throw new StudentCourseOperationException("修改选课信息失败");
        }
        return new StudentCourseExecution(StudentCourseStateEnum.SUCCESS,studentCourse);
    }

    /**
     * @param studentCourseId
     * @return com.bjtu.edu.dto.StudentCourseExecution
     * @author: ysp
     * @description: 删除指定选课信息
     * @createTime: 2020/8/17 10:41
     */
    @Override
    public StudentCourseExecution deleteStudentCourse(long studentCourseId) {
        //删除该选课信息
        int effectedNum = studentCourseDao.deleteStudentCourse(studentCourseId);
        //判断是否删除成功
        if (effectedNum <= 0){
            throw new StudentCourseOperationException("选课信息删除失败");
        }else {
            return new StudentCourseExecution(StudentCourseStateEnum.SUCCESS);
        }
    }
}
