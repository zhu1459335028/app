package com.secusoft.ssw.model.viewobject.Response;

import java.util.Date;
import java.util.List;

public class VehicleRecordReVO {
    private Integer entryId;

    private Integer outId;

    private String plate_number;

    private String entry_imageurl;

    private String entry_imagetime;

    private String out_imageurl;

    private String out_imagetime;

    private String vehicleType;

    private String company;

    private String vehicleStatus;

    List<VehiclerecordDetailoRespVO> detailoRespVos;

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getEntry_imageurl() {
        return entry_imageurl;
    }

    public void setEntry_imageurl(String entry_imageurl) {
        this.entry_imageurl = entry_imageurl;
    }

    public String getEntry_imagetime() {
        return entry_imagetime;
    }

    public void setEntry_imagetime(String entry_imagetime) {
        this.entry_imagetime = entry_imagetime;
    }

    public String getOut_imageurl() {
        return out_imageurl;
    }

    public void setOut_imageurl(String out_imageurl) {
        this.out_imageurl = out_imageurl;
    }

    public String getOut_imagetime() {
        return out_imagetime;
    }

    public void setOut_imagetime(String out_imagetime) {
        this.out_imagetime = out_imagetime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public List<VehiclerecordDetailoRespVO> getDetailoRespVos() {
        return detailoRespVos;
    }

    public void setDetailoRespVos(List<VehiclerecordDetailoRespVO> detailoRespVos) {
        this.detailoRespVos = detailoRespVos;
    }
}
