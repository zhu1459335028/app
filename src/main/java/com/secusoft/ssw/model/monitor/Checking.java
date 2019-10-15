package com.secusoft.ssw.model.monitor;

import java.util.Date;

/**
 * @ClassName Checking
 * @Author jcyao
 * @Description 考勤记录表
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
public class Checking {

    private int checkid;

    private Date time;

    private String deviceid;

    private String cardid;

    //比对方式，1.指纹，2.人脸，3.刷卡
    private int type;

    public int getCheckid() {
        return checkid;
    }

    public void setCheckid(int checkid) {
        this.checkid = checkid;
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
}
