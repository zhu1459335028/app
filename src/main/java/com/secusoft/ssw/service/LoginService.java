package com.secusoft.ssw.service;

import com.secusoft.ssw.model.viewobject.Request.AppOutlet;
import com.secusoft.ssw.model.viewobject.Request.LoginAppUser;
import com.secusoft.ssw.model.viewobject.Request.LoginRequestVO;

public interface LoginService {

//    int getMonitorIdByMonitorName(int monitorId, String monitorName);

    Object login1(int monitorId, LoginAppUser loginAppUser);

    String resultToken(int monitorId, AppOutlet appOutlet);
}
