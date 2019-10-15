package com.secusoft.ssw.model.viewobject.Response;


import io.swagger.annotations.ApiModelProperty;

public class ShieldCameraVO {
    @ApiModelProperty("相机编号")
    private int camerano;
    @ApiModelProperty("设备编号")
    private int deviceno;
    @ApiModelProperty("设备类型")
    private int devicetype;
    @ApiModelProperty("相机名称")
    private String cameraname;
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
    @ApiModelProperty("轮播时间（秒）")
    private int carousel;
    @ApiModelProperty("执行方式：1.单次执行，0.循环执行")
    private int grouptype;

    public int getCamerano() {
        return camerano;
    }

    public void setCamerano(int camerano) {
        this.camerano = camerano;
    }

    public int getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(int deviceno) {
        this.deviceno = deviceno;
    }

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }

    public String getCameraname() {
        return cameraname;
    }

    public void setCameraname(String cameraname) {
        this.cameraname = cameraname;
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

    public int getCarousel() {
        return carousel;
    }

    public void setCarousel(int carousel) {
        this.carousel = carousel;
    }

    public int getGrouptype() {
        return grouptype;
    }

    public void setGrouptype(int grouptype) {
        this.grouptype = grouptype;
    }
}
