package com.secusoft.ssw.service;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.viewobject.Request.QueryWaterVO;
import com.secusoft.ssw.model.viewobject.Request.WaterScreenVo;

import java.util.Map;

public interface WaterLevelService {

    /**
     * 添加保存水位信息
     * @param waterScreenVo
     */
    void save(int monitorId, int outletId,WaterScreenVo waterScreenVo);

    /**
     * 查询水位历史记录
     * @param currentMonitorId
     * @param currentOutletId
     * @param queryWaterVO
     * @return
     */
    GlobalApiResult<Object> queryWaterList(Integer currentMonitorId, Integer currentOutletId, QueryWaterVO queryWaterVO);

    /**
     * 查询历史水位图表
     * @param currentMonitorId
     * @param currentOutletId
     * @param queryWaterVO
     * @return
     */
    GlobalApiResult<Object> queryWaterChart(Integer currentMonitorId, Integer currentOutletId, QueryWaterVO queryWaterVO);


    /**
     * 查询大屏
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    Map<String,Object> queryWaterScreen(Integer currentMonitorId, Integer currentOutletId);
}
