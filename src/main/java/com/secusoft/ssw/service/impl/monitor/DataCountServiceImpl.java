package com.secusoft.ssw.service.impl.monitor;

import com.secusoft.ssw.mapper.monitor.AlgorithumMapper;
import com.secusoft.ssw.mapper.monitor.CheckStandardMapper;
import com.secusoft.ssw.mapper.monitor.PatrolRecordHistoryMapper;
import com.secusoft.ssw.mapper.monitor.RealTimeReportHistoryMapper;
import com.secusoft.ssw.model.monitor.Algorithum;
import com.secusoft.ssw.model.monitor.CheckStandardItem;
import com.secusoft.ssw.model.monitor.CheckStandardSubdirectory;
import com.secusoft.ssw.model.viewobject.Response.ChartVO;
import com.secusoft.ssw.model.viewobject.Response.PatrolReportDataCountVO;
import com.secusoft.ssw.service.DataCountService;
import com.secusoft.ssw.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName DataCountServiceImpl
 * @Author jcyao
 * @Description
 * @Date 2018/11/29 14:28
 * @Version 1.0
 */
@Service
public class DataCountServiceImpl implements DataCountService {

    @Autowired
    private PatrolRecordHistoryMapper patrolRecordHistoryMapper;
    @Autowired
    private RealTimeReportHistoryMapper realTimeReportHistoryMapper;
    @Autowired
    private CheckStandardMapper checkStandardMapper;
    @Autowired
    private AlgorithumMapper algorithumMapper;

    @Override
    public PatrolReportDataCountVO getPatrolReportDataCount(int monitorId, int outletId, String startTime, String endTime) {
        PatrolReportDataCountVO patrolReportDataCountVO = new PatrolReportDataCountVO();
        List<PatrolReportDataCountVO.Top> tops = new ArrayList<>();
        List<PatrolReportDataCountVO.Down> downs = new ArrayList<>();
        List<CheckStandardSubdirectory> subdirectories = checkStandardMapper.selectSubdirectoryByOutletId(outletId);
        for(CheckStandardSubdirectory checkStandardSubdirectory : subdirectories){
            PatrolReportDataCountVO.Top top = patrolReportDataCountVO.new Top();
            PatrolReportDataCountVO.Down down = patrolReportDataCountVO.new Down();
            top.setName(checkStandardSubdirectory.getSubdirectoryName());
            top.setDrilldown(checkStandardSubdirectory.getId());
            down.setId(checkStandardSubdirectory.getId());
            down.setName(checkStandardSubdirectory.getSubdirectoryName());
            List<CheckStandardItem> items = checkStandardMapper.selectItemBySubdirectoryId(checkStandardSubdirectory.getId(),outletId);
            List<List<Object>> downData = new ArrayList<>();
            int totalCount = 0;
            for(CheckStandardItem checkStandardItem : items){
                List<Object> list = new LinkedList<>();
                list.add(checkStandardItem.getItemName());
                int count = patrolRecordHistoryMapper.countByOutletIdAndItemId(outletId,checkStandardItem.getId(),startTime,endTime);
                list.add(count);
                downData.add(list);
                totalCount += count;
            }
            down.setData(downData);
            downs.add(down);
            top.setY(totalCount);
            tops.add(top);
        }
        patrolReportDataCountVO.setTops(tops);
        patrolReportDataCountVO.setDowns(downs);
        return patrolReportDataCountVO;
    }

    @Override
    public ChartVO getRealTimeReportDataCount(int monitorId, int outletId, String startTime, String endTime, List<Date> dates) {
        ChartVO chartVO = new ChartVO();
        List<String> x = new LinkedList<>();
        List<ChartVO.YValue> y = new ArrayList<>();
        for(Date date : dates){
            x.add(DateUtils.parseDateStr(date,"yyyy-MM-dd"));
        }
        List<Algorithum> algorithums = algorithumMapper.selectAll(outletId);
        Integer itemid1 = realTimeReportHistoryMapper.selectItemidBuOutletId(outletId);
        for(Algorithum algorithum : algorithums){
            ChartVO.YValue yValue = chartVO.new YValue();
            yValue.setName(algorithum.getCnName());
            List<Integer> data = new LinkedList<>();
            for(String date : x){
                System.out.println("======================"+algorithum.getServiceId());
                System.out.println("======================"+date);
                data.add(realTimeReportHistoryMapper.countReportByOutletidAndItemIdAndDay1(outletId,algorithum.getServiceId(),itemid1,date));
            }
            yValue.setData(data);
            y.add(yValue);
        }
        chartVO.setX(x);
        chartVO.setY(y);
        return chartVO;
    }
}
