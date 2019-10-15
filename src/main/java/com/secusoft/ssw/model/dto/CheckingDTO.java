package com.secusoft.ssw.model.dto;

import java.util.Date;

/**
 * @ClassName CheckingDTO
 * @Author jcyao
 * @Description 考勤记录相关数据集
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
public class CheckingDTO {

    private int checkid;

    private String employeeid;

    private Date time;

    private String deviceid;

    private String cardid;

    //比对方式，1.指纹，2.人脸，3.刷卡
    private int type;

    //进出类型：1.工地入口，2.工地出口，3.基坑入口，4.基坑出口
    private int devicetype;

    private String imageurl;

    public int getCheckid() {
        return checkid;
    }

    public void setCheckid(int checkid) {
        this.checkid = checkid;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "CheckingDTO{" +
                "checkid=" + checkid +
                ", employeeid='" + employeeid + '\'' +
                ", time=" + time +
                ", deviceid='" + deviceid + '\'' +
                ", cardid='" + cardid + '\'' +
                ", type=" + type +
                ", devicetype=" + devicetype +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
