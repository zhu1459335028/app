package com.secusoft.ssw.service;


import com.github.pagehelper.PageInfo;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.Settlement;
import com.secusoft.ssw.model.monitor.ThresholdName;
import com.secusoft.ssw.model.viewobject.Request.*;

import java.util.List;

public interface SettlementService{



    /**
     * 分页查询所有沉降相关数据
     */
    PageInfo<Settlement> getAllSettlement(int monitorId, int outletId, int currentPage, int pageSize,Integer settleunitmonid);

    /**
     * 根据时间段分页查询沉降相关数据
     */
    PageInfo<Settlement> getSettlement(int monitorId, int outletId, String startTime, String endTime, int currentPage, int pageSize) ;

    /**
     * 添加沉降数据
     */
    int addSettlement(int monitorId, int outletId, Settlement settlement);

    /**
     * 更新沉降数据
     */
    void updateSettlement(int monitorId, int outletId, Settlement settlement);

    /**
     * 删除沉降数据
     */
    void deleteSettlement(int monitorId, int outletId, Integer id);


    /**
     * 点位类型
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    List<SettleunitmonVO> selectSettleunitmon(Integer currentMonitorId, Integer currentOutletId);

    Object saveOrUpdateSettleunitmon(Integer currentMonitorId, Integer currentOutletId, SettleunitmonVO settleunitmonVO);

    Object deleteSettleunitmon(Integer currentMonitorId, Integer currentOutletId, List<String> list);


    List<ThresholdName> queryThresholdName(Integer currentMonitorId, Integer currentOutletId, ThresholdNameVO thresholdNameVO);

    Object addThresholdList(Integer currentMonitorId, Integer currentOutletId, List<ThresholdvalueVO> list);

    Object saveSettleImageInfo(Integer currentMonitorId, Integer currentOutletId, SettleimageVO settleimageVO);

    Object querySettleImageInfo(Integer currentMonitorId, Integer currentOutletId);

    Object querySettleImageInfoById(Integer currentMonitorId, Integer currentOutletId, Integer id);

    Object querySettleScreen(Integer currentMonitorId, Integer currentOutletId);

    Object querySettleScreenPopupWindow(Integer currentMonitorId, Integer currentOutletId, String locationno);

    Object querySettleScreenPopupWindowAxialforce(Integer currentMonitorId, Integer currentOutletId, String locationno);

    Object querySettleScreenPopupWindowSite(Integer currentMonitorId, Integer currentOutletId, String locationno);

    GlobalApiResult<Object> addsettleunitmon(Integer currentMonitorId, Integer currentOutletId, List<SettleimageunitmonVO> list);

    Object saveOrUpdatethresholdname(Integer currentMonitorId, Integer currentOutletId, ThresholdName thresholdName);

    String saveList(Integer currentMonitorId, Integer currentOutletId, List<Settlement> list1);

    GlobalApiResult<Object> saveOrUpdateLocationNoThresholdList(Integer currentMonitorId, Integer currentOutletId, List<ThresholdvalueTwoVO> list);

    Object deletePoint(Integer currentMonitorId, Integer currentOutletId, String locationno);

    Object querySettleScreenPopupWindowWater(Integer currentMonitorId, Integer currentOutletId, String locationno);
}