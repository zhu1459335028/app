package com.secusoft.ssw.model.viewobject.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName LoginRequestVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/4 9:33
 * @Version 1.0
 */
@ApiModel(value="登录参数集")
public class LoginRequestVO {

    @ApiModelProperty("运营中心名称")
    private String monitorName;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String passWord;

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
