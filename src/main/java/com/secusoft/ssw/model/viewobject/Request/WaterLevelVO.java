package com.secusoft.ssw.model.viewobject.Request;

public class WaterLevelVO {

    private  Integer id;

    private String watervalue;

    private String monitortime;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getMonitortime() {
        return monitortime;
    }

    public void setMonitortime(String monitortime) {
        this.monitortime = monitortime;
    }
}
