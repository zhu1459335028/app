package com.secusoft.ssw.model.monitor;
import java.sql.Timestamp;

public class PatrolRecordHistory {
    private int id;
    private String reportId;
    private int outletId;
    private int deviceNo;
    private int cameraNo;
    private int operatorId;
    private int adminId;
    private String clientIp;
    private Timestamp commitTime;
    private String itemId;
    private String comment;
    private String majorPic;
    private String minorPic;
    private String videoInfo;
    private Timestamp auditTime;
    private int auditResult;//审核结果0:不通过,1:通过,2:已生成报告
    private Timestamp actionTime;
    private String majorMd5;
    private String minorMd5;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public int getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(int deviceNo) {
        this.deviceNo = deviceNo;
    }

    public int getCameraNo() {
        return cameraNo;
    }

    public void setCameraNo(int cameraNo) {
        this.cameraNo = cameraNo;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Timestamp commitTime) {
        this.commitTime = commitTime;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMajorPic() {
        return majorPic;
    }

    public void setMajorPic(String majorPic) {
        this.majorPic = majorPic;
    }

    public String getMinorPic() {
        return minorPic;
    }

    public void setMinorPic(String minorPic) {
        this.minorPic = minorPic;
    }

    public String getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(String videoInfo) {
        this.videoInfo = videoInfo;
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

    public Timestamp getActionTime() {
        return actionTime;
    }

    public void setActionTime(Timestamp actionTime) {
        this.actionTime = actionTime;
    }

    public String getMajorMd5() {
        return majorMd5;
    }

    public void setMajorMd5(String majorMd5) {
        this.majorMd5 = majorMd5;
    }

    public String getMinorMd5() {
        return minorMd5;
    }

    public void setMinorMd5(String minorMd5) {
        this.minorMd5 = minorMd5;
    }
}

