package com.secusoft.ssw.service;

import com.secusoft.ssw.common.GlobalApiResult;

public interface WaterMonitorService {

    /**
     * 保存坑深
     * @param currentMonitorId
     * @param currentOutletId
     * @param depth
     * @return
     */
    GlobalApiResult<Object> saveWaterMonitor(Integer currentMonitorId, Integer currentOutletId, String depth);

    GlobalApiResult<Object> queryWaterDepth(Integer currentMonitorId, Integer currentOutletId);
}
