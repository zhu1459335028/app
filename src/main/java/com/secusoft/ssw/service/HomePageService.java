package com.secusoft.ssw.service;

import com.secusoft.ssw.model.monitor.*;
import com.secusoft.ssw.model.viewobject.Request.IoOnline;
import com.secusoft.ssw.model.viewobject.Request.IoStatus;
import com.secusoft.ssw.model.viewobject.Response.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface HomePageService {

    List<CamerasVO> getCameras(int monitorId,int userId,int outletId);

    NewEMap getEMap(int monitorId, int outletId);

    List<CameraEmapInfoVO> getCameraEmaps(int monitorId, int outletId,int emapId);

    List<AccessDeviceEmap> getAccessDeviceMap(int monitorId, int outletId,int emapId);

    List<BuildingSiteVO> getSiteInfo(int monitorId,int outletId);

    List<Realtimereporthistory> getReportInfo(int monitorId, int outletId);

    List<PatrolRecordHistoryVO> getPatrolReportInfo(int monitorId, int outletId);

    List<EmployeeInfoVO> getFDPeopleCount(int monitorId, int outletId);

    ChartVO getWgsjtj1(int monitorId, int outletId);

    ChartVO getWgsjtj2(int monitorId, int outletId);

    ChartVO getZgsj(int monitorId, int outletId);

    ChartVO getJkrktj(int monitorId, int outletId);

    ConstructionOnWorkVO getConstructionOnwork(int monitorId, int outletId);

    ComeOnAndOutVO comeOnAndOut(int monitorId, int outletId);

    List<Gas> getGas(int monitorId, int outletId, int type, String startTime, String endTime);

    List<Settlement> getSettlement(int monitorId, int outletId, String startTime, String endTime) ;

    Threshold getThreshold(int monitorId, int outletId, String thresholdType);


    Map<String,Object> foundManage(Integer currentMonitorId, Integer currentOutletId) throws ParseException;

    Map<String, Object> queryLedDisplay(int monitorId, int outletId) throws ParseException;

    void saveFoundinout(int monitorId, int outletId, FountVo fountVo);

    Map<String, Object> zxFountInOutData(int monitorId, int outletId,FountVo fountVo);

    Map<String, Object> personManage(Integer currentMonitorId, Integer currentOutletId);

    Map<String, Object> constructiondays(Integer currentMonitorId, Integer currentOutletId);


    /**
     * 人员管理弹窗
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    Map<String, Object> personManagePopupWindow(Integer currentMonitorId, Integer currentOutletId);

    Map<String, Object> homePageWaterPopupWindow(Integer currentMonitorId, Integer currentOutletId, Integer foundid);

    Map<String, Object> homePageGasPopupWindow(Integer currentMonitorId, Integer currentOutletId);

    Map<String, Object> homePageGasPopupWindowInit(Integer currentMonitorId, Integer currentOutletId);

    HarmfulGasVO homePageHarmfulGasPopupWindow(Integer currentMonitorId, Integer currentOutletId);

    Object saveData(Integer currentMonitorId, Integer currentOutletId, String startTime);

    String queryData(Integer currentMonitorId, Integer currentOutletId);


    Map queryTitle(Integer currentMonitorId, Integer currentOutletId);

    List<Homepageimage> queryDgImage(Integer currentMonitorId, Integer currentOutletId);



    Object savePageImage(Integer currentMonitorId, Integer currentOutletId, String imageurl, String name);

    Object SaveDgImage(Integer currentMonitorId, Integer currentOutletId, String imageurl, Integer type);

    Object queryImage(Integer currentMonitorId, Integer currentOutletId);

    Object updateStatus(Integer currentMonitorId,String status, Integer currentOutletId, String iodeviceid);

    Object queryIoStatus(Integer currentMonitorId, Integer currentOutletId);

    Object queryIpPort(Integer currentMonitorId, Integer currentOutletId);

    IocontrollerVO selectRoad(Integer currentMonitorId,String devicename, Integer currentOutletId);

    Object updateOnline(Integer currentMonitorId, List<IoStatus> ioStatus);

    String selectDeviceNameByDevicetype(Integer currentMonitorId, Integer currentOutletId, String devicetype);

    IoOnline queryIoDevice(Integer currentMonitorId,String iodeviceid);

    String queryPm25(Integer currentMonitorId, Integer outletid);

    ThresholdRespVO queryPm25ValueColer(Integer currentMonitorId, Integer outletid);

    List<String> selectDeviceNameByDevicetypes(Integer currentMonitorId, Integer outletid, String devicetype);

    Object updateAutomanual(Integer currentMonitorId, Integer currentOutletId, AutomanualVO automanualVO);

    IocontrollerVO selectReportdevice(Integer currentMonitorId, Integer currentOutletId, Integer id);
}
