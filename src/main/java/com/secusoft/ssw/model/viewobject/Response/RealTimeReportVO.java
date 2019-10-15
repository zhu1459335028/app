package com.secusoft.ssw.model.viewobject.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 实时报告
 * @author yaojiacheng
 * 2018年6月6日
 */
@ApiModel(value="实时报告信息集")
public class RealTimeReportVO {
    @ApiModelProperty("报告编号")
    private String reportid;
    @ApiModelProperty("报告地址")
    private String reporturl;
    @ApiModelProperty("检查项名称/报警信息")
    private String itemname;
    @ApiModelProperty("相机名称/报警来源")
    private String cameraname;
    @ApiModelProperty("图片地址")
    private String majorpic;
    @ApiModelProperty("生成时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createtime;
    @ApiModelProperty("当天报告数量")
    private int todayReportNum;

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

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getCameraname() {
        return cameraname;
    }

    public void setCameraname(String cameraname) {
        this.cameraname = cameraname;
    }

    public String getMajorpic() {
        return majorpic;
    }

    public void setMajorpic(String majorpic) {
        this.majorpic = majorpic;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public int getTodayReportNum() {
        return todayReportNum;
    }

    public void setTodayReportNum(int todayReportNum) {
        this.todayReportNum = todayReportNum;
    }
}
