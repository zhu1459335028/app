package com.secusoft.ssw.model.viewobject.Request;

public class ThresholdNameTwoVO {

    /**
     * id
     */
    private Integer id;
    /**
     * 中文名称
     */
    private String name;

    /**
     * 工地id
     */
    private Integer outletid;

    /**
     * 1预警，2报警，3严重
     */
    private Integer type;

    /**
     * 框的个数
     */
    private Integer number;

    /**
     * ,tv.max_val
     */
    private String max_val;

    private String value;

    private Integer thresholeid;

    /**
     * 监测点类型id
     */
    private Integer settleunitmonid;
    /**
     * 阈值条目id
     */
    private Integer thrnameid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getMax_val() {
        return max_val;
    }

    public void setMax_val(String max_val) {
        this.max_val = max_val;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getThresholeid() {
        return thresholeid;
    }

    public void setThresholeid(Integer thresholeid) {
        this.thresholeid = thresholeid;
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
}
