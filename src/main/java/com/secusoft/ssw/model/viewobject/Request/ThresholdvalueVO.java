package com.secusoft.ssw.model.viewobject.Request;

public class ThresholdvalueVO {

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
