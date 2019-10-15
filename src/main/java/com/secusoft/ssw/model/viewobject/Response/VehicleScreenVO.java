package com.secusoft.ssw.model.viewobject.Response;

import java.util.Map;

public class VehicleScreenVO {

    private String plate_number;

    private String vehicletype;

    private String entryouttype;

    private String datatime;


    private String imageurl;

    private Object data;

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getEntryouttype() {
        return entryouttype;
    }

    public void setEntryouttype(String entryouttype) {
        this.entryouttype = entryouttype;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
