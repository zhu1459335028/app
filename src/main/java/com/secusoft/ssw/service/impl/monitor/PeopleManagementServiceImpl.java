package com.secusoft.ssw.service.impl.monitor;

import com.alibaba.fastjson.JSON;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.CheckingMapper;
import com.secusoft.ssw.mapper.monitor.DepartmentMapper;
import com.secusoft.ssw.mapper.monitor.EmployeeMapper;
import com.secusoft.ssw.model.dto.CheckingDTO;
import com.secusoft.ssw.model.monitor.Department;
import com.secusoft.ssw.model.monitor.EmployeeInfo;
import com.secusoft.ssw.model.viewobject.Request.EmployeeVO;
import com.secusoft.ssw.model.viewobject.Response.DepartmentInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeListInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeOnWorkVO;
import com.secusoft.ssw.service.PeopleManagementService;
import com.secusoft.ssw.util.DateUtils;
import com.secusoft.ssw.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PeopleManagementServiceImpl
 * @Author jcyao
 * @Description
 * @Date 2018/9/6 18:49
 * @Version 1.0
 */
@Service
public class PeopleManagementServiceImpl implements PeopleManagementService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private CheckingMapper checkingMapper;

    @Override
    public List<DepartmentInfoVO> getDepartment(int monitorId, int outletId) {
        List<DepartmentInfoVO> result = new ArrayList<>();
        List<Department> departments = departmentMapper.selectDepartmentByOutletId(outletId);
        for(Department department : departments){
            DepartmentInfoVO departmentInfoVO = new DepartmentInfoVO();
            departmentInfoVO.setId(department.getId());
            departmentInfoVO.setName(department.getName());
            departmentInfoVO.setParentid(department.getParentid());
            departmentInfoVO.setPeopleNum(employeeMapper.countEmployeeByDepartmentId(department.getId(),outletId));
            result.add(departmentInfoVO);
        }
        return result;
    }

    @Override
    public EmployeeInfoVO getEmployeeInfo(int monitorId, int outletId,String employeeId) {
        return employeeMapper.selectEmployeeByEmployeeId(outletId,employeeId);
    }

    @Override
    public List<EmployeeListInfoVO> getEmployeeList(int monitorId, int outletId, String departmentId) {
        List<EmployeeListInfoVO> result = new ArrayList<>();
        List<EmployeeListInfoVO> employeeListInfoVOs = employeeMapper.selectEmployeeByDepartmentId(departmentId,outletId);
        for(EmployeeListInfoVO employeeListInfoVO : employeeListInfoVOs){
            if("1".equals(employeeListInfoVO.getSex())){
                employeeListInfoVO.setSex("男");
            }else if("2".equals(employeeListInfoVO.getSex())){
                employeeListInfoVO.setSex("女");
            }else{
                employeeListInfoVO.setSex(null);
            }
            List<CheckingDTO> checkings = checkingMapper.selectTodayCheckingByEmployeeId("checking_"+outletId,employeeListInfoVO.getId(),outletId);
            if(checkings!=null && checkings.size()>0){
                //根据员工今日最后一次打卡是否是进场来判断该员工目前是否在岗
                employeeListInfoVO.setOnLine("否");
                if(checkings.get(checkings.size()-1).getDevicetype()==1){
                    employeeListInfoVO.setOnLine("是");
                }
                long todaySumTime = 0l;
                CheckingDTO lastCheckingDTO = null;
                for(CheckingDTO checkingDTO : checkings){
                    if(lastCheckingDTO!=null && lastCheckingDTO.getDevicetype()==1 && checkingDTO.getDevicetype()==2){
                        todaySumTime += (checkingDTO.getTime().getTime()-lastCheckingDTO.getTime().getTime());
                    }
                    lastCheckingDTO = checkingDTO;
                    //员工今日第一次进场打卡的时间作为今日到岗时间
                    if(checkingDTO.getDevicetype()==1 && employeeListInfoVO.getTodayTime()==null){
                        employeeListInfoVO.setTodayTime(checkingDTO.getTime());
                    }
                }
                employeeListInfoVO.setTodaySumTime(sumTimeToString(todaySumTime));
            }
            List<CheckingDTO> allCheckings = checkingMapper.selectCheckingByEmployeeId("checking_"+outletId,employeeListInfoVO.getId(),outletId);
            long allSumTime = 0l;
            CheckingDTO lastCheckingDTO = null;
            for(CheckingDTO checkingDTO : allCheckings){
                if(lastCheckingDTO!=null && lastCheckingDTO.getDevicetype()==1 && checkingDTO.getDevicetype()==2
                        && DateUtils.getIntervalDays(lastCheckingDTO.getTime(),checkingDTO.getTime())==0){
                    allSumTime += (checkingDTO.getTime().getTime()-lastCheckingDTO.getTime().getTime());
                }
                lastCheckingDTO = checkingDTO;
            }
            employeeListInfoVO.setAllSumTime(sumTimeToString(allSumTime));
            result.add(employeeListInfoVO);
        }
        return result;
    }

    @Override
    public List<EmployeeOnWorkVO> getOnWork(int monitorId, int outletId, String employeeId, Date startDate, Date endDate) {
        List<EmployeeOnWorkVO> result = new ArrayList<>();
        List<CheckingDTO> checkingDTOs = checkingMapper.selectCheckingsByEmployeeId("checking_"+outletId,employeeId,startDate,endDate,outletId);
        //将考勤信息按天放到各个List中
        List<List<CheckingDTO>> dayCheckingDTOs = new ArrayList<>();
        List<CheckingDTO> dayCheckingDTO = new ArrayList<>();
        CheckingDTO lastCheckingDTO = null;
        for(CheckingDTO checkingDTO : checkingDTOs){
            if(lastCheckingDTO!=null){
                if(DateUtils.getIntervalDays(lastCheckingDTO.getTime(),checkingDTO.getTime())==0){
                    dayCheckingDTO.add(checkingDTO);
                }else{
                    dayCheckingDTOs.add(dayCheckingDTO);
                    dayCheckingDTO = new ArrayList<>();
                    dayCheckingDTO.add(checkingDTO);
                }
            } else {
                dayCheckingDTO.add(checkingDTO);
            }
            lastCheckingDTO = checkingDTO;
        }
        if(dayCheckingDTO.size()>0){
            dayCheckingDTOs.add(dayCheckingDTO);
        }
        long allSumTime = 0;
        for(List<CheckingDTO> oneDayCheckings : dayCheckingDTOs){
            EmployeeOnWorkVO employeeOnWorkVO = new EmployeeOnWorkVO();
            employeeOnWorkVO.setDayTime(oneDayCheckings.get(0).getTime());
            CheckingDTO lastCheckingDTO_ = null;
            long daySumtime = 0;
            int rgCount = 0;
            int cgCount = 0;
            for(CheckingDTO checkingDTO : oneDayCheckings){
                if(checkingDTO.getDevicetype()==1){
                    rgCount++;
                }else{
                    cgCount++;
                }
                employeeOnWorkVO.setCrTime(StringUtil.isBlank(employeeOnWorkVO.getCrTime())?
                        DateUtils.parseDateStr(checkingDTO.getTime(),"HH:mm"):
                        (employeeOnWorkVO.getCrTime()+","+DateUtils.parseDateStr(checkingDTO.getTime(),"HH:mm")));
                employeeOnWorkVO.setStatus(StringUtil.isBlank(employeeOnWorkVO.getStatus())?
                        (checkingDTO.getDevicetype()==1?"进":"出"):
                        (employeeOnWorkVO.getStatus()+","+(checkingDTO.getDevicetype()==1?"进":"出")));
                if(lastCheckingDTO_!=null && lastCheckingDTO_.getDevicetype()==1 && checkingDTO.getDevicetype()==2){
                    daySumtime += (checkingDTO.getTime().getTime()-lastCheckingDTO_.getTime().getTime());
                }
                lastCheckingDTO_ = checkingDTO;
            }
            employeeOnWorkVO.setDaySumTime(sumTimeToString(daySumtime));
            employeeOnWorkVO.setError(rgCount>cgCount?1:(rgCount==cgCount?0:2));
            result.add(employeeOnWorkVO);
            allSumTime += daySumtime;
        }
        for(EmployeeOnWorkVO employeeOnWorkVO : result){
            employeeOnWorkVO.setAllSumTime(sumTimeToString(allSumTime));
        }
//        Collections.reverse(result);

        return result;
    }


    @Override
    public Object updateemployeeInfo(int currentMonitorId, int currentOutletId, EmployeeVO employeeVo) {
        employeeVo.setOutletid(currentOutletId);
        Integer a=employeeMapper.updateemployeeInfo(employeeVo);
        return "更新成功";
    }

    @Override
    public Object savePeopleInfo(Integer currentMonitorId, Integer currentOutletId, EmployeeInfo employeeInfo) {
      employeeInfo.setOutletid(currentOutletId);
      String msg = "" ;
      employeeInfo.setCorporationcontent("1、国务院和建设部制定的安全生产方针、政策和规程、规范；\n" +
              "2、安全生产、劳动保护的意义和任务；\n" +
              "3、安全生产六大纪律、十项安全技术措施、安全生产“十不准”和其他的规章制度；\n" +
              "4、分公司安全生产形势和任务以及主要类别事故的预防，如高处坠落、触电、物体打击、机具伤害等；\n" +
              "5、公司以往发生的重大伤亡事故分析及应吸取的教训；\n" +
              "6、事故发生后，如何抢救伤员、排险、保护现场和及时报告；\n" +
              "7、增强个人安全保护意识，认真开展我不伤害自己、不伤害别人、不被他人伤害的安全生产活动。");
      employeeInfo.setItemcontent("1、建筑工程施工特点及安全生产基本知识；\n" +
              "2、本单位安全生产规章、制度、纪律和安全注意事项；\n" +
              "3、各工种的安全技术操作规程、规定、文明生产要求；\n" +
              "4、机械设备、电气安全及高处作业等安全基本知识和防护措施；\n" +
              "5、防火、防毒和防爆安全知识及预防措施；\n" +
              "6、防护用具、用品的正确使用规定；\n" +
              "7、爱护施工现场各类安全防护设施、设备、器具，严禁擅自拆卸、损坏及有关奖罚规定。");
      employeeInfo.setClassescontent("1、班组安全作业活动制度及纪律；\n" +
              "2、本班组的作业特点及安全操作规程和作业危险区域、部位及其安全防护要求、措施；\n" +
              "3、爱护和正确使用安全防护装置（设施）及个人劳保用品；\n" +
              "4、本岗位易发生事故的不安全因素及其防护对策；\n" +
              "5、本岗位的作业环境及使用的机械设备、工具的安全要求。");
      if (employeeInfo.getId()==null){
          EmployeeInfo emp = employeeMapper.queryEmployeeById(employeeInfo.getEmployeeid(), currentOutletId);
          if (emp != null){
             return GlobalApiResult.failure("该员工档案已存在,不能重复录入！");
          }
          employeeMapper.savePeopleInfo(employeeInfo);
          msg = "添加成功";
      }else {
          employeeMapper.updatePeopleInfo(employeeInfo);
          msg = "更新成功";
      }
        return msg;
    }

    @Override
    public Object queryEmployee(Integer currentMonitorId, Integer currentOutletId, Integer employeeId) {
        if (employeeId == null){
            return GlobalApiResult.failure("员工Id不能为空");
        }
        return employeeMapper.queryEmployeeById(employeeId,currentOutletId);
    }


    private String sumTimeToString(long sumTime){
        if(sumTime>0){
            String sumTimeStr = "";
            long hour = sumTime/(3600*1000);
            long minute = (sumTime-(hour*3600*1000))/(60*1000);
            if(hour>0){
                sumTimeStr += hour+"小时";
            }
            if(minute>0){
                sumTimeStr += minute+"分钟";
            }
            if(!StringUtil.isBlank(sumTimeStr)){
                return sumTimeStr;
            }
        }
        return null;
    }

}
