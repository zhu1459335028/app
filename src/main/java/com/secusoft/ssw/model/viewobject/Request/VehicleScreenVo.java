package com.secusoft.ssw.model.viewobject.Request;

public class VehicleScreenVo {
    /**工地id*/
    private String monitorid;

    /**工地id*/
    private String outletid;

    /***最新记录uuid*/
    private String uuid;

    /**进出类型0进，1出*/
    private String entryoutype;

    public String getMonitorid() {
        return monitorid;
    }

    public void setMonitorid(String monitorid) {
        this.monitorid = monitorid;
    }

    public String getOutletid() {
        return outletid;
    }

    public void setOutletid(String outletid) {
        this.outletid = outletid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEntryoutype() {
        return entryoutype;
    }

    public void setEntryoutype(String entryoutype) {
        this.entryoutype = entryoutype;
    }
}
