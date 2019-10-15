package com.secusoft.ssw.service;

import com.secusoft.ssw.model.monitor.GasVos;
import com.secusoft.ssw.model.viewobject.Request.GasVo;
import com.secusoft.ssw.model.viewobject.Response.ComeOnAndOutVO;

public interface VmcServerService {

    String getEmployeeIdByCardId(int monitorId, int outletId, String cardId);

    boolean isJobTypeByEmployeeId(int monitorId, String employeeId);

    Integer getAccessDeviceTypeByDeviceId(int monitorId, int outletId, String accessDeviceId);

    String getCheckingImageUrlByCheckId(int monitorId, int outletId, int checkId);

    Integer getJobTypeByEmployeeId(int monitorId, String employeeId);
    
    ComeOnAndOutVO comeOnAndOut(int monitorId, int outletId);

//    Object insertGas(GasVos gasVos);


    void insertGaswdz(int monitorId, int outletid, GasVo gasVo);
    void insertGaswdzhbx(int monitorId, int outletid, GasVo gasVo);

}
