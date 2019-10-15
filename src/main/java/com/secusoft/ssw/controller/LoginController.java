package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.common.exception.ServiceException;
import com.secusoft.ssw.model.viewobject.Request.AppOutlet;
import com.secusoft.ssw.model.viewobject.Request.LoginAppUser;
import com.secusoft.ssw.model.viewobject.Request.LoginRequestVO;
import com.secusoft.ssw.service.LoginService;
import com.secusoft.ssw.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Api(value = "Login-Controller", description = "登录相关接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     */
    @ApiOperation(value="登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public GlobalApiResult<Object> login(@RequestBody LoginAppUser loginAppUser){
        if(StringUtil.isAnyBlank(loginAppUser.getUsername(),loginAppUser.getUserpswd())){
            throw new ServiceException("入参有误");
        }
        return GlobalApiResult.success(loginService.login1(1,loginAppUser));
    }


    /**
     * 登录
     */
    @ApiOperation(value="选择工地获取token")
    @RequestMapping(value = "/createToken",method = RequestMethod.POST)
    @ResponseBody
    public GlobalApiResult<String> getAll(@RequestBody AppOutlet appOutlet){
        return GlobalApiResult.success(loginService.resultToken(
                1,appOutlet));
    }


}
