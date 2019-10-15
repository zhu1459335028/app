package com.secusoft.ssw.model.monitor;

/**
 * 水位仪器
 */
public class WaterInstruMent {

    private int id;

    /**
     * 设备编号
     */
    private String deviceid;

    /**
     * 设备名称
     */
    private String devicename;

    /**
     * 添加人
     */
    private String adduser;

    /**
     * 中间值
     */
    private String middlevalue;

    /**
     * 差值
     */
    private String poorvalue;

    /**
     * 监测点id
     */
    private int wmid;

    /**
     * 工地id
     */
    private int outletid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }

    public String getMiddlevalue() {
        return middlevalue;
    }

    public void setMiddlevalue(String middlevalue) {
        this.middlevalue = middlevalue;
    }

    public String getPoorvalue() {
        return poorvalue;
    }

    public void setPoorvalue(String poorvalue) {
        this.poorvalue = poorvalue;
    }

    public int getWmid() {
        return wmid;
    }

    public void setWmid(int wmid) {
        this.wmid = wmid;
    }

    public int getOutletid() {
        return outletid;
    }

    public void setOutletid(int outletid) {
        this.outletid = outletid;
    }
}
