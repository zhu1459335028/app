package com.secusoft.ssw.model.viewobject.Request;

import io.swagger.annotations.ApiModelProperty;

public class LoginAppUser {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String userpswd;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }
}
