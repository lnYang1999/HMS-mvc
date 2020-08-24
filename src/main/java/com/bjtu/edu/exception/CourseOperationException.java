package com.bjtu.edu.exception;

/**
 * @project: HMS-mvc
 * @description: Course操作异常类
 * @author: ysp
 * @create: 2020/08/04
 */
public class CourseOperationException extends RuntimeException{
    private static final long serialVersionUID = -7989645915942446156L;

    public CourseOperationException(String msg){super(msg);}
}
