package com.secusoft.ssw.model.viewobject.Response;

public class ScreenVehicleVO {
    //进场个数
    private Integer entryNum;
    //出场个数
    private Integer outNum;
    //在场个数
    private Integer onlineNum;
    //漏拍个数
    private Integer leakyNum;

    public Integer getLeakyNum() {
        return leakyNum;
    }


    public void setLeakyNum(Integer leakyNum) {
        this.leakyNum = leakyNum;
    }

    //车辆类型
    private String vehicletype;

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }

    public Integer getOutNum() {
        return outNum;
    }

    public void setOutNum(Integer outNum) {
        this.outNum = outNum;
    }

    public Integer getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Integer onlineNum) {
        this.onlineNum = onlineNum;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }
}
