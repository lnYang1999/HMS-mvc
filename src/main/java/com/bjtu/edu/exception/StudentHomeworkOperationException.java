package com.bjtu.edu.exception;

/**
 * @project: HMS-mvc
 * @description: StudentHomework操作异常类
 * @author: ysp
 * @create: 2020/08/04
 */
public class StudentHomeworkOperationException extends RuntimeException{
    private static final long serialVersionUID = 8353226200023490354L;

    public StudentHomeworkOperationException(String msg){super(msg);}
}
