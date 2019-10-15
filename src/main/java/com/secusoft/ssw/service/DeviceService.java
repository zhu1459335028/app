package com.secusoft.ssw.service;

import com.secusoft.ssw.model.viewobject.Request.DeviceMg;
import com.secusoft.ssw.model.viewobject.Response.DeviceCameraVO;
import com.secusoft.ssw.model.viewobject.Response.DeviceManageVO;
import com.secusoft.ssw.model.viewobject.Response.DevicetypenameVO;

import java.util.List;

public interface DeviceService {

    List<DeviceCameraVO> getDeviceCamera(int monitorId,int userId,int outletId);

    void setCamera(int monitorId, int userId, int outletId, int cameraNo, int playback, int second, int enableSlicing, int slicingTime);

    Object saveDeviceType(Integer currentMonitorId, Integer currentOutletId, DevicetypenameVO devicetypenameVO);

    Object saveDevice(Integer currentMonitorId, Integer currentOutletId, DeviceManageVO deviceManageVO);

    Object deleteDevice(Integer currentMonitorId, Integer currentOutletId, List<String> list);

    Object queryAllDevice(Integer currentMonitorId, Integer currentOutletId, DeviceMg deviceMg);

    Object queryDeviceType(Integer currentMonitorId, Integer currentOutletId);

    Object queryOneDevice(Integer currentMonitorId, Integer currentOutletId, Integer id);
}
