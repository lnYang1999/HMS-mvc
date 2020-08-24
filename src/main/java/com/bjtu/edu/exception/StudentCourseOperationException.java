package com.bjtu.edu.exception;

/**
 * @project: HMS-mvc
 * @description: StudentCourse操作异常类
 * @author: ysp
 * @create: 2020/08/04
 */
public class StudentCourseOperationException extends RuntimeException{
    private static final long serialVersionUID = -4815731819042253407L;

    public StudentCourseOperationException(String msg){super(msg);}
}
