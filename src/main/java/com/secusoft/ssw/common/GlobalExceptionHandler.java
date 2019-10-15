package com.secusoft.ssw.common;


import com.secusoft.ssw.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalExceptionHandler
 * @Author jcyao
 * @Description 统一异常处理
 * @Date 2018/8/30 18:16
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GlobalApiResult<String> defaultErrorHandler(HttpServletRequest request, Exception e){
        logger.error("统一异常处理捕获了异常，请求地址："+request.getRequestURL(),e);
        if(e instanceof ServiceException){
            ServiceException se = (ServiceException) e;
            return GlobalApiResult.failure(se.getCode(),se.getMessage());
        }else{
            return GlobalApiResult.failure("系统异常，请稍后重试");
        }
    }

}
