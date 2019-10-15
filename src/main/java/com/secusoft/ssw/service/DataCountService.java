package com.secusoft.ssw.service;

import com.secusoft.ssw.model.viewobject.Response.ChartVO;
import com.secusoft.ssw.model.viewobject.Response.PatrolReportDataCountVO;

import java.util.Date;
import java.util.List;

public interface DataCountService {

    PatrolReportDataCountVO getPatrolReportDataCount(int monitorId, int outletId, String startTime, String endTime);

    ChartVO getRealTimeReportDataCount(int monitorId, int outletId, String startTime, String endTime, List<Date> dates);

}
