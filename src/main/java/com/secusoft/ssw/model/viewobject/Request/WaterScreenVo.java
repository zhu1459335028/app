package com.secusoft.ssw.model.viewobject.Request;

import io.swagger.annotations.ApiModel;

/**
 * 水位接收
 */
public class  WaterScreenVo {

    /**
     * 设备id
     */
    private String device_id;

    /**
     * 监测时间
     */
    private String time;

    /**
     * 水位数据
     */
    private String water_level;

    private Integer outletid;

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWater_level() {
        return water_level;
    }

    public void setWater_level(String water_level) {
        this.water_level = water_level;
    }
}
