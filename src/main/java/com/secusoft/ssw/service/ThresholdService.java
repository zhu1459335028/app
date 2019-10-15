package com.secusoft.ssw.service;


import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.Threshold;
import com.secusoft.ssw.model.monitor.Thresholdvertocal;

import java.util.List;

public interface ThresholdService{



    /**
     * 查询阈值
     */
    Threshold getThreshold(int monitorId, int outletId, Integer id);
    /**
     * 查询阈值
     */
    Threshold getThreshold(int monitorId, int outletId, String type);
    
    /**
     * 更新阈值
     */
    void updateThreshold(int monitorId, int outletId, Threshold threshold);

    /**
     * 水位添加阈值
     * @param currentMonitorId
     * @param currentOutletId
     * @param threshold
     * @return
     */
    GlobalApiResult<Object> SaveOrUpdateThreshold(Integer currentMonitorId, Integer currentOutletId, List<Threshold> threshold);

    /**
     * 查询阈值列表
     * @param currentMonitorId
     * @param currentOutletId
     * @param threshold
     * @return
     */
    GlobalApiResult<Object> queryThreshold(Integer currentMonitorId, Integer currentOutletId, Threshold threshold);


    GlobalApiResult<Object> deleteThreshold(Integer currentMonitorId, Integer currentOutletId, List<String> asList);

    Threshold queryLevelThreshold(int monitorId, Integer outletid, String datetime);

    Threshold queryVerticalThreshold(int monitorId, Integer outletid, String datetime);

    GlobalApiResult<Object> SaveOrUpdateLevel(Integer currentMonitorId, Integer currentOutletId, Threshold threshold);

    Thresholdvertocal saveLevelAndVertical(int monitorId,String avgDaily_db47_52, String avgDaily_db47_56, Integer outletid);

    Thresholdvertocal queryLevelAndVertical(int monitorId,Integer outletid);

    GlobalApiResult<Object> deleteThresholdLevel(Integer currentMonitorId, Integer currentOutletId, Integer id);
}