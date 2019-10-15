package com.secusoft.ssw.model.viewobject.Request;

import java.util.List;

public class ThresholdvalueTwoVO {




    private Integer id;
    /**
     * 监测点类型id
     */
    private Integer settleunitmonid;
    /**
     * 阈值条目id
     */
    private Integer thrnameid;
    /**
     * 阈值上限值
     */
    private String value;

    /**
     * 点位x轴
     */
    private Float x;
    /**
     * 点位y轴
     */
    private Float y;

    /**
     * 测点编号
     */
    private String locationno;

    /**
     * 测量图片id
     */
    private Integer settleimageid;

    public Integer getSettleimageid() {
        return settleimageid;
    }

    public void setSettleimageid(Integer settleimageid) {
        this.settleimageid = settleimageid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSettleunitmonid() {
        return settleunitmonid;
    }

    public void setSettleunitmonid(Integer settleunitmonid) {
        this.settleunitmonid = settleunitmonid;
    }

    public Integer getThrnameid() {
        return thrnameid;
    }

    public void setThrnameid(Integer thrnameid) {
        this.thrnameid = thrnameid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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

    public String getLocationno() {
        return locationno;
    }

    public void setLocationno(String locationno) {
        this.locationno = locationno;
    }
}
