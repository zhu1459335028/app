package com.secusoft.ssw.model.monitor;

import java.util.Date;

/**
 * 车辆进出记录
 */
public class VehicleRecord {

    private Integer id;

    private String plate_number;

    private String head_imageurl;

    private Date head_imagetime;

    private String body_imageurl;

    private Date body_imagetime;

    private int type;

    private String camera_head;

    private String camera_body;

    private String device_head_id;

    private String camera_body_id;

    private String patch;

    private String uuid;

    private Date inserttime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getHead_imageurl() {
        return head_imageurl;
    }

    public void setHead_imageurl(String head_imageurl) {
        this.head_imageurl = head_imageurl;
    }

    public Date getHead_imagetime() {
        return head_imagetime;
    }

    public void setHead_imagetime(Date head_imagetime) {
        this.head_imagetime = head_imagetime;
    }

    public String getBody_imageurl() {
        return body_imageurl;
    }

    public void setBody_imageurl(String body_imageurl) {
        this.body_imageurl = body_imageurl;
    }

    public Date getBody_imagetime() {
        return body_imagetime;
    }

    public void setBody_imagetime(Date body_imagetime) {
        this.body_imagetime = body_imagetime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCamera_head() {
        return camera_head;
    }

    public void setCamera_head(String camera_head) {
        this.camera_head = camera_head;
    }

    public String getCamera_body() {
        return camera_body;
    }

    public void setCamera_body(String camera_body) {
        this.camera_body = camera_body;
    }

    public String getDevice_head_id() {
        return device_head_id;
    }

    public void setDevice_head_id(String device_head_id) {
        this.device_head_id = device_head_id;
    }

    public String getCamera_body_id() {
        return camera_body_id;
    }

    public void setCamera_body_id(String camera_body_id) {
        this.camera_body_id = camera_body_id;
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

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}



