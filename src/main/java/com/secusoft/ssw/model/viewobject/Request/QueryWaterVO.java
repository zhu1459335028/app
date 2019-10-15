package com.secusoft.ssw.model.viewobject.Request;

public class QueryWaterVO extends PageReqAbstractModel{

    private String starttime;

    private String endtime;

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
