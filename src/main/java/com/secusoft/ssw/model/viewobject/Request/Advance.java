package com.secusoft.ssw.model.viewobject.Request;

public class Advance extends PageReqAbstractModel{
    private String tunnellingdate;
    private Integer outletid; //工地id

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    public String getTunnellingdate() {
        return tunnellingdate;
    }

    public void setTunnellingdate(String tunnellingdate) {
        this.tunnellingdate = tunnellingdate;
    }
}
