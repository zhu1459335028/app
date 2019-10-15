package com.secusoft.ssw.service;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.Vehicle;
import com.secusoft.ssw.model.viewobject.Request.VehiclePageVo;
import com.secusoft.ssw.model.viewobject.Response.VehicleScreenVO;

import java.util.List;

public interface VehicleService {

    /**
     * 保存或添加车辆信息
     * @param vehicle
     * @return
     */
    GlobalApiResult<Object> saveORupdateVehicle(int monitorId,Integer outletId,Vehicle vehicle);

    /**
     * 删除一条或者多条车辆信息
     * @param currentMonitorId
     * @param asList
     * @return
     */
    GlobalApiResult<Object> deleteVehicleByIds(Integer currentMonitorId,Integer outletId, List<String> list);

    /**
     * 条件分页查询车辆列表
     * @param currentMonitorId
     * @param vehiclePageVo
     * @return
     */
    GlobalApiResult<Object> getVehicleList(Integer currentMonitorId,Integer outletId, VehiclePageVo vehiclePageVo);

    /**
     * 车辆类型
     * @param currentMonitorId
     * @return
     */
    GlobalApiResult<Object> queryVehicleTypeList(Integer currentMonitorId,Integer outletId);

    /**
     * 所属公司
     * @param currentMonitorId
     * @return
     */
    GlobalApiResult<Object> queryCompanyList(Integer currentMonitorId,Integer outletId);

    /**
     *
     * @param currentMonitorId
     * @return
     */
    GlobalApiResult<Object> queryVehicleCount(Integer currentMonitorId,Integer outletId);

    /**
     * 今日车辆公司进出统计
     * @param currentMonitorId
     * @return
     */
    GlobalApiResult<Object> queryVehicleCompanyToDayInOutCount(Integer currentMonitorId,Integer outletId,int flag);

    /**
     * 今日进出统计
     * @param currentMonitorId
     * @return
     */
    GlobalApiResult<Object> queryToDayInOutCount(Integer currentMonitorId,Integer outletId,int flag);

    /**
     * 车辆进出
     * @param currentMonitorId
     * @param starttime
     * @param endtime
     * @return
     */
    GlobalApiResult<Object> queryVehicleInOutCount(Integer currentMonitorId,Integer outletId, String starttime, String endtime);


    /**
     * 公司车辆进出
     * @param currentMonitorId
     * @param starttime
     * @param endtime
     * @return
     */
    GlobalApiResult<Object> queryVehicleCompanyInOutCount(Integer currentMonitorId,Integer outletId, String starttime, String endtime);


    /**
     * 大屏
     * @param monitorId
     * @param uuid
     * @param entryoutype
     * @return
     */
    VehicleScreenVO queryVehicleScreen(String monitorId,Integer outletId,String uuid, String entryoutype);

    /**
     * 大屏弹框
     * @param currentMonitorId
     * @return
     */
    GlobalApiResult<Object> queryVehicleScreenMessage(Integer currentMonitorId,Integer outletId,String vehicletype);


    /**
     * 大屏初始化
     * @param valueOf
     * @param valueOf1
     * @return
     */
    VehicleScreenVO queryNewVehicleScreenImage(Integer monitorId, Integer outletId);


    /**
     * 保存大屏显式的车辆类型
     * @param currentMonitorId
     * @param currentOutletId
     * @param screenvheicle
     * @return
     */
    GlobalApiResult<Object> saveScreenVehicleType(Integer currentMonitorId, Integer currentOutletId, String screenvheicle);


    /**
     * 大屏车辆类型列表
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    GlobalApiResult<Object> queryScreenVehicleType(Integer currentMonitorId, Integer currentOutletId);

    /**
     * 大屏
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    GlobalApiResult<Object> queryScreenVehicleData(Integer currentMonitorId, Integer currentOutletId);
}
