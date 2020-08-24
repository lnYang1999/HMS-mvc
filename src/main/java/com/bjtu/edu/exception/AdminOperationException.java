package com.bjtu.edu.exception;

/**
 * @project: HMS-mvc
 * @description: Admin操作异常类
 * @author: ysp
 * @create: 2020/08/04
 */
public class AdminOperationException extends RuntimeException{
    private static final long serialVersionUID = -4473514521615519498L;

    public AdminOperationException(String msg){super(msg);}
}
