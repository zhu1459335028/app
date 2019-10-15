package com.secusoft.ssw.model.viewobject.Response;

import java.util.Date;

public class TargetgoalsVO {

    /**
     * 目标名称
     */
    private String targetname;
    /**
     * 目标环数
     */
    private Float targetring;
    /**
     * 目标里程
     */
    private Float targetmileage;
    /**
     * 起始日期
     */
    private String startdate;
    /**
     * 结束日期
     */
    private String enddate;


    public String getTargetname() {
        return targetname;
    }

    public void setTargetname(String targetname) {
        this.targetname = targetname;
    }

    public Float getTargetring() {
        return targetring;
    }

    public void setTargetring(Float targetring) {
        this.targetring = targetring;
    }

    public Float getTargetmileage() {
        return targetmileage;
    }

    public void setTargetmileage(Float targetmileage) {
        this.targetmileage = targetmileage;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
