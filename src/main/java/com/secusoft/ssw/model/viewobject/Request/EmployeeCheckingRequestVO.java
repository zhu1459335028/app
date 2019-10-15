package com.secusoft.ssw.model.viewobject.Request;

import java.util.List;

/**
 * @ClassName EmployeeCheckingRequestVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/14 16:17
 * @Version 1.0
 */
public class EmployeeCheckingRequestVO {

    private String monitorId;
    private String outletId;
    private List<RecordVO> records;

    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public List<RecordVO> getRecords() {
        return records;
    }

    public void setRecords(List<RecordVO> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "EmployeeCheckingRequestVO{" +
                "monitorId='" + monitorId + '\'' +
                ", outletId='" + outletId + '\'' +
                ", records=" + records +
                '}';
    }
}
