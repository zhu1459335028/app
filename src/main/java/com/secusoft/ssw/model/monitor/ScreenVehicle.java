package com.secusoft.ssw.model.monitor;

/**
 * 大屏与车辆类型关联表
 */
public class ScreenVehicle {

    /**
     * id
     */
    private Integer id;

    /**
     * 车辆类型
     */
    private String vehicletype;

    /**
     * 工地id
     */
    private Integer outletid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }
}
