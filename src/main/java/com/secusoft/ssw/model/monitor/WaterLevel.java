package com.secusoft.ssw.model.monitor;

import java.util.Date;

/**
 * 测量记录表
 */
public class WaterLevel {

    private Integer id;

    /**
     * 水位值
     */
    private String watervalue;

    /**
     * 设置id
     */
    private String deviceid;

    /**
     * 采集时间
     */
    private Date monitortime;

    private Integer outletid;

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    private Date inserttime;

    private Date updatetime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWatervalue() {
        return watervalue;
    }

    public void setWatervalue(String watervalue) {
        this.watervalue = watervalue;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Date getMonitortime() {
        return monitortime;
    }

    public void setMonitortime(Date monitortime) {
        this.monitortime = monitortime;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
