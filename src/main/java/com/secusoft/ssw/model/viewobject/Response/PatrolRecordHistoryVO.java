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
@ApiModel(value="运营报告审核项")
public class PatrolRecordHistoryVO {

    @ApiModelProperty("记录编号")
    private int id;
    @ApiModelProperty("检查项ID")
    private String itemId;
    @ApiModelProperty("检查项名称/报警信息")
    private String itemName;
    @ApiModelProperty("图片地址")
    private String majorPic;
    @ApiModelProperty("审核时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp auditTime;
    @ApiModelProperty("审核结果0:不通过,1:通过,2:已生成报告")
    private int auditResult;

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

    public int getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(int auditResult) {
        this.auditResult = auditResult;
    }
}
