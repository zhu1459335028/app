package com.secusoft.ssw.model.viewobject.Request;

public class SettleimageunitmonVO {

    /**
     * 点位x轴
     */
    private Float x;
    /**
     * 点位y轴
     */
    private Float y;
    /**
     * 测量图片id
     */
    private Integer settleimageid;
    /**
     * 监测点类型id
     */
    private Integer settleunitmonid;
    /**
     * 测点编号
     */
    private String locationno;



    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Integer getSettleimageid() {
        return settleimageid;
    }

    public void setSettleimageid(Integer settleimageid) {
        this.settleimageid = settleimageid;
    }

    public Integer getSettleunitmonid() {
        return settleunitmonid;
    }

    public void setSettleunitmonid(Integer settleunitmonid) {
        this.settleunitmonid = settleunitmonid;
    }

    public String getLocationno() {
        return locationno;
    }

    public void setLocationno(String locationno) {
        this.locationno = locationno;
    }
}
