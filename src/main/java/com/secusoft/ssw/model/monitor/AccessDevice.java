package com.secusoft.ssw.model.monitor;

/**
 * @ClassName AccessDevice
 * @Author jcyao
 * @Description 人脸比对设备表
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
public class AccessDevice {

    private int id;

    private String accessdeviceid;

    private String devicename;

    private int outletid;

    private String manufacturer;

    //所属的ICS Server
    private int icsserverid;

    //进出类型：1.工地入口，2.工地出口，3.基坑入口，4.基坑出口
    private int type;

    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessdeviceid() {
        return accessdeviceid;
    }

    public void setAccessdeviceid(String accessdeviceid) {
        this.accessdeviceid = accessdeviceid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public int getOutletid() {
        return outletid;
    }

    public void setOutletid(int outletid) {
        this.outletid = outletid;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getIcsserverid() {
        return icsserverid;
    }

    public void setIcsserverid(int icsserverid) {
        this.icsserverid = icsserverid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
