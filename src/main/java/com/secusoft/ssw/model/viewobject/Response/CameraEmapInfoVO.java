package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName CameraEmapInfoVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/10 16:28
 * @Version 1.0
 */
@ApiModel(value="地图中的相机信息集")
public class CameraEmapInfoVO {

    @ApiModelProperty("相机编号")
    private int cameraid;
    @ApiModelProperty("地图编号")
    private int emapid;
    @ApiModelProperty("在地图中的坐标点:'h,w'")
    private String coordinate;
    @ApiModelProperty("相机在地图上的方向")
    private String direction;
    @ApiModelProperty("相机在地图上的视野角度")
    private String scopeangle;
    @ApiModelProperty("相机在地图上的视野距离")
    private String scopedistance;
    @ApiModelProperty("ip")
    private String ipaddress;
    @ApiModelProperty("tcp端口")
    private int tcpport;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String passwd;
    @ApiModelProperty("通道号")
    private int chanindex;
    @ApiModelProperty("设备类型")
    private int devicetype;

    public int getCameraid() {
        return cameraid;
    }

    public void setCameraid(int cameraid) {
        this.cameraid = cameraid;
    }

    public int getEmapid() {
        return emapid;
    }

    public void setEmapid(int emapid) {
        this.emapid = emapid;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getScopeangle() {
        return scopeangle;
    }

    public void setScopeangle(String scopeangle) {
        this.scopeangle = scopeangle;
    }

    public String getScopedistance() {
        return scopedistance;
    }

    public void setScopedistance(String scopedistance) {
        this.scopedistance = scopedistance;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public int getTcpport() {
        return tcpport;
    }

    public void setTcpport(int tcpport) {
        this.tcpport = tcpport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getChanindex() {
        return chanindex;
    }

    public void setChanindex(int chanindex) {
        this.chanindex = chanindex;
    }

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }
}
