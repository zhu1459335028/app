package com.secusoft.ssw.controller;

import com.secusoft.ssw.constant.RequestAttributesConstant;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BaseController
 * @Author jcyao
 * @Description
 * @Date 2018/11/16 9:49
 * @Version 1.0
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    public Integer getCurrentMonitorId(){
        return Integer.valueOf(request.getAttribute(RequestAttributesConstant.monitorId).toString());
    }

    public Integer getCurrentOutletId(){
        return Integer.valueOf(request.getAttribute(RequestAttributesConstant.outletId).toString());
    }

    public Integer getCurrentUserId(){
        return Integer.valueOf(request.getAttribute(RequestAttributesConstant.userId).toString());
    }

    public HttpServletRequest getRequest() {
        return request;
    }
}
