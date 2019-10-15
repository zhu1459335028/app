package com.secusoft.ssw.model.monitor;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 实时报告记录表
 * @author yaojiacheng
 * 2018年6月6日
 */
@ApiModel(value="实时报告信息集")
public class Realtimereporthistory {
    @ApiModelProperty(hidden = true)
    private int id;
    @ApiModelProperty("报告编号")
    private String reportid;
    @ApiModelProperty("报告地址")
    private String reporturl;
    @ApiModelProperty(hidden = true)
    private int adminid;
    @ApiModelProperty(hidden = true)
    private String adminname;
    @ApiModelProperty("门店ID")
    private int outletid;
    @ApiModelProperty("门店名称")
    private String outletname;
    @ApiModelProperty("设备编号")
    private int deviceno;
    @ApiModelProperty("设备名称")
    private String devicename;
    @ApiModelProperty("相机编号")
    private int camerano;
    @ApiModelProperty("相机名称/报警来源")
    private String cameraname;
    @ApiModelProperty("检查项ID")
    private int itemid;
    @ApiModelProperty("检查项名称/报警信息")
    private String itemname;
    @ApiModelProperty("图片地址")
    private String majorpic;
    @ApiModelProperty(hidden = true)
    private String minorpic;
    @ApiModelProperty(hidden = true)
    private String videoinfo;
    @ApiModelProperty("生成时间")
    @JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8")
    private Timestamp createtime;
    @ApiModelProperty(hidden = true)
    private String comment;
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8")
    private Timestamp actiontime;
    @ApiModelProperty(hidden = true)
    private int brandid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public String getReporturl() {
        return reporturl;
    }

    public void setReporturl(String reporturl) {
        this.reporturl = reporturl;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public int getOutletid() {
        return outletid;
    }

    public void setOutletid(int outletid) {
        this.outletid = outletid;
    }

    public String getOutletname() {
        return outletname;
    }

    public void setOutletname(String outletname) {
        this.outletname = outletname;
    }

    public int getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(int deviceno) {
        this.deviceno = deviceno;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public int getCamerano() {
        return camerano;
    }

    public void setCamerano(int camerano) {
        this.camerano = camerano;
    }

    public String getCameraname() {
        return cameraname;
    }

    public void setCameraname(String cameraname) {
        this.cameraname = cameraname;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getMajorpic() {
        return majorpic;
    }

    public void setMajorpic(String majorpic) {
        this.majorpic = majorpic;
    }

    public String getMinorpic() {
        return minorpic;
    }

    public void setMinorpic(String minorpic) {
        this.minorpic = minorpic;
    }

    public String getVideoinfo() {
        return videoinfo;
    }

    public void setVideoinfo(String videoinfo) {
        this.videoinfo = videoinfo;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getActiontime() {
        return actiontime;
    }

    public void setActiontime(Timestamp actiontime) {
        this.actiontime = actiontime;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }
}
