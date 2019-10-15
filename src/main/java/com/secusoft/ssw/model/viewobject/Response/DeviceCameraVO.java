package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName DeviceCameraVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/5 17:47
 * @Version 1.0
 */
@ApiModel(value="设备相机树结构结果集")
public class DeviceCameraVO {

    @ApiModelProperty("设备/相机的上级编号")
    private String deviceno;
    @ApiModelProperty("设备/相机的编号")
    private String camerano;
    @ApiModelProperty("设备/相机的名称")
    private String name;
    @ApiModelProperty("设备类型")
    private int devicetype;
    @ApiModelProperty("类型：1.设备、2.相机")
    private int type;
    @ApiModelProperty("相机是否设置了轮播：1.是、0.否")
    private int playback;
    @ApiModelProperty("相机轮播时间(秒)")
    private int second;
    @ApiModelProperty("相机切图时长")
    private int slicingTime;
    @ApiModelProperty("1为周界轮播相机;0则不是周界轮播相机")
    private int enableSlicing;
    @ApiModelProperty("执行方式：1.单次执行，0.循环执行")
    private int grouptype;
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

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    public String getCamerano() {
        return camerano;
    }

    public void setCamerano(String camerano) {
        this.camerano = camerano;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPlayback() {
        return playback;
    }

    public void setPlayback(int playback) {
        this.playback = playback;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getGrouptype() {
        return grouptype;
    }

    public void setGrouptype(int grouptype) {
        this.grouptype = grouptype;
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
    public int getSlicingTime() {
        return slicingTime;
    }
    public void setSlicingTime(int slicingTime) {
        this.slicingTime = slicingTime;
    }

    public int getEnableSlicing() {
        return enableSlicing;
    }

    public void setEnableSlicing(int enableSlicing) {
        this.enableSlicing = enableSlicing;
    }
}
