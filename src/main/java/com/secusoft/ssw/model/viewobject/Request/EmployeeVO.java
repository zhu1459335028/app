package com.secusoft.ssw.model.viewobject.Request;

public class EmployeeVO {
    private Integer outletid;
    private String id;

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    /**正面url*/
    private String cardfronturl;

    /**反面url*/
    private String cardreverseurl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardfronturl() {
        return cardfronturl;
    }

    public void setCardfronturl(String cardfronturl) {
        this.cardfronturl = cardfronturl;
    }

    public String getCardreverseurl() {
        return cardreverseurl;
    }

    public void setCardreverseurl(String cardreverseurl) {
        this.cardreverseurl = cardreverseurl;
    }
}
