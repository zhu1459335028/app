package com.secusoft.ssw.model.viewobject.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * @ClassName PatrolRecordHistoryVO
 * @Author jcyao
 * @Description
 * @Date 2018/11/16 10:21
 * @Version 1.0
 */
@ApiModel(value="运营报告违规记录")
public class PatrolRecordVO {

    @ApiModelProperty("记录编号")
    private int id;
    @ApiModelProperty("检查项ID")
    private String itemId;
    @ApiModelProperty("检查项名称/报警信息")
    private String itemName;
    @ApiModelProperty("视频通道/报警来源")
    private String cameraName;
    @ApiModelProperty("图片地址")
    private String majorPic;
    @ApiModelProperty("审核时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp auditTime;
    @ApiModelProperty("类型：1.好 2.坏")
    private int type;
    @ApiModelProperty("程度")
    private int severity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getMajorPic() {
        return majorPic;
    }

    public void setMajorPic(String majorPic) {
        this.majorPic = majorPic;
    }

    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
}
