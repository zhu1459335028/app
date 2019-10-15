package com.secusoft.ssw.model.viewobject.Response;

public class ThresholdvalueRspVO {

    private String name;

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
    private String max_val;
    /**
     * 下限值
     */
    private String min_val;
    /**
     * 恒等值
     */
    private String identical_val;
    /**
     * 工地id
     */
    private Integer outletid;


    /**
     * 1预警，2报警，3严重
     */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getMax_val() {
        return max_val;
    }

    public void setMax_val(String max_val) {
        this.max_val = max_val;
    }

    public String getMin_val() {
        return min_val;
    }

    public void setMin_val(String min_val) {
        this.min_val = min_val;
    }

    public String getIdentical_val() {
        return identical_val;
    }

    public void setIdentical_val(String identical_val) {
        this.identical_val = identical_val;
    }

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
