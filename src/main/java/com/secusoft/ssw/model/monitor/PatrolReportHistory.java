package com.secusoft.ssw.model.monitor;

import java.sql.Timestamp;

/**
 * @ClassName PatrolReportHistory
 * @Description
 * @Author jcyao
 * @Date 2018/11/16 10:19
 **/
public class PatrolReportHistory {
    private int id;
    private int outletId;
    private String reportId;
    private String reportUrl;
    private String reportPdf;
    private int adminId;
    private String recordId;
    private Timestamp createTime;
    private Timestamp readTime;
    private Timestamp sendTime;
    private int severity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getReportPdf() {
        return reportPdf;
    }

    public void setReportPdf(String reportPdf) {
        this.reportPdf = reportPdf;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getReadTime() {
        return readTime;
    }

    public void setReadTime(Timestamp readTime) {
        this.readTime = readTime;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
}
