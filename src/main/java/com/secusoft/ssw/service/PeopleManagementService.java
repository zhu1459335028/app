package com.secusoft.ssw.service;

import com.secusoft.ssw.model.monitor.EmployeeInfo;
import com.secusoft.ssw.model.viewobject.Request.EmployeeVO;
import com.secusoft.ssw.model.viewobject.Response.DepartmentInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeListInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeOnWorkVO;

import java.util.Date;
import java.util.List;

public interface PeopleManagementService {

    List<DepartmentInfoVO> getDepartment(int monitorId, int outletId);

    EmployeeInfoVO getEmployeeInfo(int monitorId, int outletId ,String employeeId);

    List<EmployeeListInfoVO> getEmployeeList(int monitorId, int outletId, String departmentId);

    List<EmployeeOnWorkVO> getOnWork(int monitorId, int outletId, String employeeId, Date startDate, Date endDate);


    Object updateemployeeInfo(int currentMonitorId, int currentOutletId, EmployeeVO employeeVO);

    Object savePeopleInfo(Integer currentMonitorId, Integer currentOutletId, EmployeeInfo employeeInfo);

    Object queryEmployee(Integer currentMonitorId, Integer currentOutletId, Integer employeeId);
}
