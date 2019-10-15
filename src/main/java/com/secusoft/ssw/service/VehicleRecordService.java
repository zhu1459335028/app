package com.secusoft.ssw.service;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.viewobject.Request.VehicleRecordVo;

public interface VehicleRecordService {

    /**
     * 条件查询进出记录
     * @param currentMonitorId
     * @param vehicleRecordVo
     * @return
     */
    public GlobalApiResult<Object> getVehicleRecordPage(Integer currentMonitorId,Integer outletId, VehicleRecordVo vehicleRecordVo);

    /**
     * 补录接口
     * @param currentMonitorId
     * @param id
     * @param time
     * @return
     */
    GlobalApiResult<Object> updateVehicle(Integer currentMonitorId,Integer outletId, int id, String bltime);
}
