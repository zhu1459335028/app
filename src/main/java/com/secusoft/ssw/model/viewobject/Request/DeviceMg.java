package com.secusoft.ssw.model.viewobject.Request;

public class DeviceMg extends PageReqAbstractModel{

    private String devicetype;//设备类型
    private Integer iscertificateofinspection;//有无合格证 0无 1有
    private Integer isputonrecord;// 是否备案  0否1是
    private Integer outletid; //工地id

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public Integer getIscertificateofinspection() {
        return iscertificateofinspection;
    }

    public void setIscertificateofinspection(Integer iscertificateofinspection) {
        this.iscertificateofinspection = iscertificateofinspection;
    }

    public Integer getIsputonrecord() {
        return isputonrecord;
    }

    public void setIsputonrecord(Integer isputonrecord) {
        this.isputonrecord = isputonrecord;
    }
}
