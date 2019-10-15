package com.secusoft.ssw.service.impl.monitor;

import com.secusoft.ssw.mapper.monitor.PatrolRecordHistoryMapper;
import com.secusoft.ssw.mapper.monitor.RealTimeReportHistoryMapper;
import com.secusoft.ssw.model.monitor.Realtimereporthistory;
import com.secusoft.ssw.model.viewobject.Response.PatrolRecordVO;
import com.secusoft.ssw.model.viewobject.Response.RealTimeReportVO;
import com.secusoft.ssw.service.ReportService;
import com.secusoft.ssw.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName ReportServiceImpl
 * @Author jcyao
 * @Description
 * @Date 2018/9/4 19:58
 * @Version 1.0
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private RealTimeReportHistoryMapper realTimeReportHistoryMapper;
    @Autowired
    private PatrolRecordHistoryMapper patrolRecordHistoryMapper;

    @Value("${report.base.url}")
    private String reportBaseUrl;

    @Override
    public Map<String,List<RealTimeReportVO>> getReportHistory(int monitorId, int outletId, String startDate, String endDate) {
        Map<String,List<RealTimeReportVO>> result = new LinkedHashMap<>();
        List<Realtimereporthistory> reports = realTimeReportHistoryMapper.selectReportByOutletidAndDate(outletId,startDate,endDate);
        if(reports!=null && reports.size()>0){
            List<RealTimeReportVO> realTimeReportVOs = new ArrayList<>();
            Realtimereporthistory lastReport = null;
            for(Realtimereporthistory realtimereporthistory : reports){
                if(lastReport!=null && DateUtils.getIntervalDays(lastReport.getCreatetime(),realtimereporthistory.getCreatetime())!=0){
                    result.put(DateUtils.DateToString(lastReport.getCreatetime(),"yyyy-MM-dd"),realTimeReportVOs);
                    realTimeReportVOs = new ArrayList<>();
                }
                RealTimeReportVO realTimeReportVO = new RealTimeReportVO();
                realTimeReportVO.setReportid(realtimereporthistory.getReportid());

                if(realtimereporthistory.getReporturl().contains("http://")){
                    realTimeReportVO.setReporturl(realtimereporthistory.getReporturl());
                }else {
                    realTimeReportVO.setReporturl(reportBaseUrl+realtimereporthistory.getReporturl());
                }
                realTimeReportVO.setItemname(realtimereporthistory.getItemname());
                realTimeReportVO.setCameraname(realtimereporthistory.getCameraname());

                if(realtimereporthistory.getMajorpic().contains("http://")){
                    realTimeReportVO.setMajorpic(realtimereporthistory.getMajorpic());
                }else{
                    realTimeReportVO.setMajorpic(reportBaseUrl+realtimereporthistory.getMajorpic());
                }
                realTimeReportVO.setCreatetime(realtimereporthistory.getActiontime());
                realTimeReportVOs.add(realTimeReportVO);
                lastReport = realtimereporthistory;
            }
            result.put(DateUtils.DateToString(lastReport.getCreatetime(),"yyyy-MM-dd"),realTimeReportVOs);
        }
        return result;
    }

    @Override
    public Map<String, List<PatrolRecordVO>> getPatrolReportHistory(int monitorId, int outletId, String startDate, String endDate) {
        Map<String, List<PatrolRecordVO>> result = new LinkedHashMap<>();
        List<PatrolRecordVO> patrolRecordVOS = patrolRecordHistoryMapper.selectPatrolRecordByOutletIdAndDate(outletId,startDate,endDate);
        if(patrolRecordVOS!=null && !patrolRecordVOS.isEmpty()){
            List<PatrolRecordVO> patrolRecords = new ArrayList<>();
            PatrolRecordVO lastPatrolRecord = null;
            for(PatrolRecordVO patrolRecordVO : patrolRecordVOS){
                if(lastPatrolRecord!=null && DateUtils.getIntervalDays(lastPatrolRecord.getAuditTime(),patrolRecordVO.getAuditTime())!=0){
                    result.put(DateUtils.DateToString(lastPatrolRecord.getAuditTime(),"yyyy-MM-dd"),patrolRecords);
                    patrolRecords = new ArrayList<>();
                }
                PatrolRecordVO patrolRecord = new PatrolRecordVO();
                patrolRecord.setAuditTime(patrolRecordVO.getAuditTime());
                patrolRecord.setCameraName(patrolRecordVO.getCameraName());
                patrolRecord.setId(patrolRecordVO.getId());
                patrolRecord.setItemId(patrolRecordVO.getItemId());
                patrolRecord.setItemName(patrolRecordVO.getItemName());
                if(patrolRecordVO.getMajorPic().contains("http://")){
                    patrolRecord.setMajorPic(patrolRecordVO.getMajorPic());
                }else{
                    patrolRecord.setMajorPic(reportBaseUrl+patrolRecordVO.getMajorPic());
                }
                patrolRecord.setSeverity(patrolRecordVO.getSeverity());
                patrolRecord.setType(patrolRecordVO.getType());
                patrolRecords.add(patrolRecord);
                lastPatrolRecord = patrolRecordVO;
            }
            result.put(DateUtils.DateToString(lastPatrolRecord.getAuditTime(),"yyyy-MM-dd"),patrolRecords);
        }
        return result;
    }

    @Override
    public Realtimereporthistory getRealReportByReportId(int monitorId,int outletid, String reportId) {
        return realTimeReportHistoryMapper.selectReportByReportId(reportId,outletid);
    }

}
