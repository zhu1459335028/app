package com.secusoft.ssw.model.viewobject.Request;

public class Iocontroller {

    private Integer id;
    private String iodeviceid;
    private String ioname;
    private String devicename;
    private String username;
    private String password;
    private String type;
    private String online;
    private String factory;
    private String road;
    private String devicetype;
    private Integer outletid;

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIodeviceid() {
        return iodeviceid;
    }

    public void setIodeviceid(String iodeviceid) {
        this.iodeviceid = iodeviceid;
    }

    public String getIoname() {
        return ioname;
    }

    public void setIoname(String ioname) {
        this.ioname = ioname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}
