package com.secusoft.ssw.model.viewobject.Response;

import java.util.Date;

public class SettleunitmonLHVO {


    /**
     * 监测点类型id
     */
    private Integer settleunitmonid;
    /**
     * 阈值条目id
     */
    private Integer id;
    /**
     * 中文名称
     */
    private String name;
    /**
     * 编号
     */
    private String code;
    /**
     * 单位
     */
    private String unit;
    /**
     * 添加时间
     */
    private Date inserttime;
    /**
     * 更新时间
     */
    private Date updatetime;
    /**
     * 工地id
     */
    private Integer outletid;

    /**
     * 1沉降、2斜侧、3位移,4轴力,5水位
     */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSettleunitmonid() {
        return settleunitmonid;
    }

    public void setSettleunitmonid(Integer settleunitmonid) {
        this.settleunitmonid = settleunitmonid;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }
}
