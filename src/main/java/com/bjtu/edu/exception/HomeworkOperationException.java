package com.bjtu.edu.exception;

/**
 * @project: HMS-mvc
 * @description: Homework操作异常类
 * @author: ysp
 * @create: 2020/08/04
 */
public class HomeworkOperationException extends RuntimeException{
    private static final long serialVersionUID = 7621921089468686702L;

    public HomeworkOperationException(String msg){super(msg);}
}
