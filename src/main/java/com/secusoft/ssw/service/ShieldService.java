package com.secusoft.ssw.service;

import com.secusoft.ssw.model.monitor.Shield;
import com.secusoft.ssw.model.monitor.Shieldadvance;
import com.secusoft.ssw.model.viewobject.Request.Advance;
import com.secusoft.ssw.model.viewobject.Request.ShieldadvanceVO;
import com.secusoft.ssw.model.viewobject.Request.ShieldpointVO;
import com.secusoft.ssw.model.viewobject.Response.DangerousVO;

import java.util.List;

public interface ShieldService {


    List<String> queryAllid(Integer currentMonitorId, Integer currentOutletId);

    Object saveOrUpdateShieldadvance(Integer currentMonitorId, Integer currentOutletId, ShieldadvanceVO shieldadvanceVO);

    Object queryAllShieldadvance(Integer currentMonitorId, Integer currentOutletId, Advance advance);

    Object queryShieldadvance(Integer currentMonitorId, Integer currentOutletId, Integer segmentno);

    Object deleteShieldadvance(Integer currentMonitorId, Integer currentOutletId, List<String> asList);

    Object saveOrUpdateShieldpoint(Integer currentMonitorId, Integer currentOutletId, ShieldpointVO shieldpointVO);

    Object selectShieldpoint(Integer currentMonitorId, Integer currentOutletId, float number);

    Object selectAllShieldpoint(Integer currentMonitorId, Integer currentOutletId);

    Object saveOrUpdateDangerous(Integer currentMonitorId, Integer currentOutletId, DangerousVO dangerousVO);

    Object selectDangerous(Integer currentMonitorId, Integer currentOutletId, Integer id);
}
