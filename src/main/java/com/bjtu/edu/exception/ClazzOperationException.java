package com.bjtu.edu.exception;

/**
 * @project: HMS-mvc
 * @description: Clazz操作异常类
 * @author: ysp
 * @create: 2020/08/03
 */
public class ClazzOperationException extends RuntimeException{
    private static final long serialVersionUID = 1110885646012316735L;

    public ClazzOperationException(String msg){super(msg);}
}
