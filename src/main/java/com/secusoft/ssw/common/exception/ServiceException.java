package com.secusoft.ssw.common.exception;

/**
 * @ClassName ServiceException
 * @Author jcyao
 * @Description 业务异常类
 * @Date 2018/7/24 11:03
 * @Version 1.0
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -1065311170761086894L;
    private int code;

    public ServiceException(String msg){
        super(msg);
        this.code=500;
    }

    public ServiceException(int code,String msg){
        super(msg);
        this.code=code;
    }

    public int getCode() {
        return code;
    }

}
