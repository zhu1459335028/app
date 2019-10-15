package com.secusoft.ssw.service;

import com.secusoft.ssw.model.monitor.Realtimereporthistory;
import com.secusoft.ssw.model.viewobject.Response.PatrolRecordVO;
import com.secusoft.ssw.model.viewobject.Response.RealTimeReportVO;

import java.util.List;
import java.util.Map;

public interface ReportService {

    Map<String,List<RealTimeReportVO>> getReportHistory(int monitorId, int outletId, String startDate , String endDate);

    Map<String,List<PatrolRecordVO>> getPatrolReportHistory(int monitorId, int outletId, String startDate , String endDate);

    Realtimereporthistory getRealReportByReportId(int monitorId,int outletid, String reportId);

}
