package com.bjtu.edu.exception;

/**
 * @project: HMS-mvc
 * @description: Student操作异常类
 * @author: ysp
 * @create: 2020/08/04
 */
public class StudentOperationException extends RuntimeException{
    private static final long serialVersionUID = -6415835066202472815L;

    public StudentOperationException(String msg){super(msg);}
}
