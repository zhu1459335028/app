package com.secusoft.ssw.model.viewobject.Response;

import java.util.Date;

/**
 * 用于接收查询结果
 */
public class VehicleRecordVO {

    private Integer id;

    /**
     * 车辆类型
     */
    private String vehicletype;

    /**
     * 进出类型
     */
    private int entryouttype;

    /**
     * 公司
     */
    private String company;

    /**
     * 车牌号
     */
    private String plate_number;

    /**头 照片*/
    private String head_imageurl;

    private String head_imagetime;

    /**身 照片*/
    private String body_imageurl;

    private String body_imagetime;

    private String camera_head;

    private String camera_body;

    private String device_head_id;

    private String camera_body_id;

    private String inserttime;

    /**
     * 是否补录
     */
    private String patch;

    /**
     * uuid标识一进一出标识
     */
    private String uuid;

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

    public int getEntryouttype() {
        return entryouttype;
    }

    public void setEntryouttype(int entryouttype) {
        this.entryouttype = entryouttype;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getHead_imagetime() {
        return head_imagetime;
    }

    public void setHead_imagetime(String head_imagetime) {
        this.head_imagetime = head_imagetime;
    }

    public String getBody_imageurl() {
        return body_imageurl;
    }

    public void setBody_imageurl(String body_imageurl) {
        this.body_imageurl = body_imageurl;
    }

    public String getBody_imagetime() {
        return body_imagetime;
    }

    public void setBody_imagetime(String body_imagetime) {
        this.body_imagetime = body_imagetime;
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

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
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
