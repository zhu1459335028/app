package com.secusoft.ssw.service.impl.monitor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.*;
import com.secusoft.ssw.model.dto.CheckingDTO;
import com.secusoft.ssw.model.monitor.*;
import com.secusoft.ssw.model.viewobject.Request.IoOnline;
import com.secusoft.ssw.model.viewobject.Request.IoStatus;
import com.secusoft.ssw.model.viewobject.Request.WaterEquipmentVO;
import com.secusoft.ssw.model.viewobject.Request.WaterLevelVO;
import com.secusoft.ssw.model.viewobject.Response.*;
import com.secusoft.ssw.service.HomePageService;
import com.secusoft.ssw.util.DateUtils;
import com.secusoft.ssw.util.MyHttpClientPool;
import com.secusoft.ssw.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName HomePageServiceImpl
 * @Author jcyao
 * @Description
 * @Date 2018/9/4 13:53
 * @Version 1.0
 */
@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private PlayGroupMapper playGroupMapper;
    @Autowired
    private CameraMapper cameraMapper;
    @Autowired
    private NewEMapMapper newEMapMapper;
    @Autowired
    private CameraEmapMapper cameraEmapMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private OutletMapper outletMapper;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RealTimeReportHistoryMapper realTimeReportHistoryMapper;
    @Autowired
    private CheckingMapper checkingMapper;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private AccessDeviceEmapMapper accessDeviceEmapMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private GasMapper gasMapper;
    @Autowired
    private ThresholdMapper thresholdMapper;
    @Autowired
    private SettlementMapper settlementMapper;

    @Autowired
    private PatrolRecordHistoryMapper patrolRecordHistoryMapper;

    @Autowired
    FoundpernumMapper foundpernumMapper;

    @Autowired
    StartdatetabMapper startdatetabMapper;

    @Value("${report.base.url}")
    private String reportBaseUrl;
    @Value("${ssm.base.url}")
    private String ssmBaseUrl;

    @Override
    public List<CamerasVO> getCameras(int monitorId, int userId, int outletId) {
        List<CamerasVO> result = new ArrayList<>();
        PlayGroup playGroup = playGroupMapper.selectPlayGroupByUserIdAndOutletId(userId,outletId);
        if(playGroup!=null && !StringUtil.isBlank(playGroup.getGroupinfo())){
            for(String cameraInfo : playGroup.getGroupinfo().split(";")){
                String[] cameraInfo_ = cameraInfo.split(":");
                int cameraNo = Integer.valueOf(cameraInfo_[0]);
                int carousel = Integer.valueOf(cameraInfo_[1]);
                CamerasVO camerasVO = cameraMapper.selectCameraByCameraNO(cameraNo,outletId);
                if(camerasVO!=null){
                    camerasVO.setCamerano(cameraNo);
                    camerasVO.setCarousel(carousel);
                    camerasVO.setGrouptype(playGroup.getGrouptype());
                    result.add(camerasVO);
                }
            }
        }
        if(result.size()==0){
            List<CamerasVO> cameras = cameraMapper.selectCameraByOutletId(outletId);
            for(CamerasVO camerasVO : cameras){
                camerasVO.setCarousel(30);
                camerasVO.setGrouptype(0);
                result.add(camerasVO);
            }
        }
        return result;
    }

    @Override
    public NewEMap getEMap(int monitorId, int outletId) {
        List<NewEMap> eMaps = newEMapMapper.selectEMapByOutletId(outletId);
        return (eMaps!=null && eMaps.size()>0)?eMaps.get(0):null;
    }

    @Override
    public List<CameraEmapInfoVO> getCameraEmaps(int monitorId,int outletId, int emapId) {
        return cameraEmapMapper.selectCameraEmapByEMapId(outletId,emapId);
    }

    @Override
    public List<AccessDeviceEmap> getAccessDeviceMap(int monitorId,int outletId, int emapId) {
        return accessDeviceEmapMapper.selectByEmapId(outletId,emapId);
    }

    @Override
    public List<BuildingSiteVO> getSiteInfo(int monitorId, int outletId) {
        List<BuildingSiteVO> result = new ArrayList<>();
        String brandName = brandMapper.selectTopBrandNameByOutletId(outletId);
        String siteName = outletMapper.selectOutletNameByOutletId(outletId);
        List<Job> jobs = jobMapper.selectJobByOutletId(outletId);
        for(Job job : jobs){
            BuildingSiteVO buildingSiteVO = new BuildingSiteVO();
            buildingSiteVO.setBrandName(brandName);
            buildingSiteVO.setSiteName(siteName);
            buildingSiteVO.setJobName(job.getName());
            List<Employee> employees = employeeMapper.selectEmployeeByJobIdAndOutletId(job.getId(),job.getOutletid());
            List<JobUserVO> jobUsers = new ArrayList<>();
            for(Employee employee : employees){
                JobUserVO jobUserVO = new JobUserVO();
                jobUserVO.setEmployeeId(employee.getId());
                jobUserVO.setUserName(employee.getName());
                if(!StringUtil.isBlank(employee.getHeadurl())){
                    jobUserVO.setHeadUrl(ssmBaseUrl+employee.getHeadurl());
                }
                //根据员工今日最后一次打卡是否是进场来判断该员工目前是否在岗
                jobUserVO.setOnLine(2);
                CheckingDTO checkingDTO = checkingMapper.selectLastTimeByEmployeeId("checking_"+outletId,employee.getId(),outletId);
                if(checkingDTO!=null){
                    if(checkingDTO.getDevicetype()==1 && DateUtils.getIntervalDaysToToday(checkingDTO.getTime())==0){
                        jobUserVO.setOnLine(1);
                        jobUserVO.setOnTime(checkingDTO.getTime());
                        if(!StringUtil.isBlank(checkingDTO.getImageurl())){
                            jobUserVO.setHeadUrl(ssmBaseUrl+checkingDTO.getImageurl());
                        }
                    }
                }
                jobUsers.add(jobUserVO);
            }
            Collections.sort(jobUsers);
            //buildingSiteVO.setJobUsers(jobUsers);
            result.add(buildingSiteVO);
        }
        return result;
    }

    @Override
    public List<Realtimereporthistory> getReportInfo(int monitorId, int outletId) {
        System.out.println("reportBaseUrl============:"+reportBaseUrl);
        return realTimeReportHistoryMapper.selectReportByOutletId(outletId,reportBaseUrl);
    }

    @Override
    public List<PatrolRecordHistoryVO> getPatrolReportInfo(int monitorId, int outletId) {
        return patrolRecordHistoryMapper.selectCurDatePatrolRecordHistoryVOByOutletIdLimit(outletId);
    }

    @Override
    public List<EmployeeInfoVO> getFDPeopleCount(int monitorId, int outletId) {
//        List<EmployeeInfoVO> employeeInfoVOS = new ArrayList<>();
//        List<CheckingDTO> checkingDTOS = checkingMapper.selectEmployeeToDayChecking("checking_"+outletId,outletId);
//        if(checkingDTOS!=null && checkingDTOS.size()>0){
//            for(CheckingDTO checkingDTO : checkingDTOS){
//                if(checkingDTO.getDevicetype()==3){
//                    EmployeeInfoVO employeeInfoVO = employeeMapper.selectEmployeeByEmployeeId(checkingDTO.getEmployeeid());
//                    if(employeeInfoVO!=null){
//                        employeeInfoVOS.add(employeeInfoVO);
//                    }
//                }
//            }
//        }
        /**
         * 查询基坑人员信息
         */
        List<String> rucardids=foundpernumMapper.queryCardidList(outletId);
        List<EmployeeInfoVO> employeeInfoVOS=new ArrayList<>();
        if(rucardids!=null&&rucardids.size()>0){
            employeeInfoVOS = employeeMapper.selectEmployeeByEmployeeCardids(rucardids,outletId);
        }
        return employeeInfoVOS;
    }

    @Override
    public ChartVO getWgsjtj1(int monitorId, int outletId) {
        ChartVO chartVO = new ChartVO();
        List<String> xValues = new ArrayList<>();
        xValues.add("违规事件");
        chartVO.setX(xValues);
        List<ChartVO.YValue> yValues = new ArrayList<>();
        List<Scene> scenes = sceneMapper.selectALL(outletId);
        if(scenes!=null && scenes.size()>0){
            for(Scene scene : scenes){
                ChartVO.YValue yValue = chartVO.new YValue();
                yValue.setName(scene.getScenename());
                List<Integer> data = new ArrayList<>();
                data.add(realTimeReportHistoryMapper.countReportByOutletidAndItemId(outletId,scene.getId()));
                yValue.setData(data);
                yValues.add(yValue);
            }
        }
        chartVO.setY(yValues);
        return chartVO;
    }

    @Override
    public ChartVO getWgsjtj2(int monitorId, int outletId) {
        ChartVO chartVO = new ChartVO();
        List<String> xValues = new ArrayList<>();
        List<Date> days = getLastDays(7);
        for(Date day : days){
            xValues.add(DateUtils.DateToString(day,"yyyy-MM-dd"));
        }
        chartVO.setX(xValues);
        List<ChartVO.YValue> yValues = new ArrayList<>();
        List<Scene> scenes = sceneMapper.selectALL(outletId);
        if(scenes!=null && scenes.size()>0){
            for(Scene scene : scenes){
                ChartVO.YValue yValue = chartVO.new YValue();
                yValue.setName(scene.getScenename());
                List<Integer> data = new ArrayList<>();
                for(Date day : days){
                    data.add(realTimeReportHistoryMapper.countReportByOutletidAndItemIdAndDay(outletId,scene.getId(),day));
                }
                yValue.setData(data);
                yValues.add(yValue);
            }
        }
        chartVO.setY(yValues);
        return chartVO;
    }

    @Override
    public ChartVO getZgsj(int monitorId, int outletId) {
        ChartVO chartVO = new ChartVO();
        List<String> xValues = new ArrayList<>();
        xValues.add("项目管理人员");
        chartVO.setX(xValues);
        List<ChartVO.YValue> yValues = new ArrayList<>();
        List<Job> jobs = jobMapper.selectJobByOutletId(outletId);
        for(Job job : jobs){
            ChartVO.YValue yValue = chartVO.new YValue();
            yValue.setName(job.getName());
            List<CheckingDTO> checkingDTOs = checkingMapper.selectCheckingsByOutletId("checking_"+outletId,outletId,job.getId());
            int date = 0;
            if(checkingDTOs!=null && checkingDTOs.size()>0){
                for(int i=1;i<checkingDTOs.size();i++){
                    Date time1 = checkingDTOs.get(i-1).getTime();
                    int type1 = checkingDTOs.get(i-1).getDevicetype();
                    Date time2 = checkingDTOs.get(i).getTime();
                    int type2 = checkingDTOs.get(i).getDevicetype();
                    if(type1==1 && type2==2){
                        date += (time2.getTime()-time1.getTime());
                    }
                }
            }
            List<Integer> data = new ArrayList<>();
            data.add(date/(3600*1000));
            yValue.setData(data);
            yValues.add(yValue);
        }
        chartVO.setY(yValues);
        return chartVO;
    }

    @Override
    public ChartVO getJkrktj(int monitorId, int outletId) {
        ChartVO chartVO = new ChartVO();
        List<String> xValues = new ArrayList<>();
        List<ChartVO.YValue> yValues = new ArrayList<>();
        List<Date> days = getLastDays(1);
        ChartVO.YValue yValue1 = chartVO.new YValue();
        yValue1.setName("入基坑");
        List<Integer> data1 = new ArrayList<>();
        for(Date day : days){
            xValues.add(DateUtils.DateToString(day,"yyyy-MM-dd"));
            //查询基坑人数
            Integer ruKeng = checkingMapper.countEmployeeByOutletIdAndTimeAndType("checking_"+outletId,outletId,day,3);
            data1.add(ruKeng==null?0:ruKeng);
        }
        yValue1.setData(data1);
        yValues.add(yValue1);
        ChartVO.YValue yValue2 = chartVO.new YValue();
        yValue2.setName("出基坑");
        List<Integer> data2 = new ArrayList<>();
        for(Date day : days){
            Integer chuKeng = checkingMapper.countEmployeeByOutletIdAndTimeAndType("checking_"+outletId,outletId,day,4);
            data2.add(chuKeng==null?0:chuKeng);
        }
        yValue2.setData(data2);
        yValues.add(yValue2);
        chartVO.setX(xValues);
        chartVO.setY(yValues);
        return chartVO;
    }

    @Override
    public ConstructionOnWorkVO getConstructionOnwork(int monitorId, int outletId) {
        ConstructionOnWorkVO constructionOnWorkVO = new ConstructionOnWorkVO();
        List<String> xValues = new LinkedList<>();
        List<ConstructionOnWorkVO.YValue> yValues = new ArrayList<>();
        //所有施工班的部门
        List<Department> departments = departmentMapper.selectDepartmentByOutletIdAndType(outletId,1);
        ConstructionOnWorkVO.YValue yValue1 = constructionOnWorkVO.new YValue();
        yValue1.setName("已到");
        ConstructionOnWorkVO.YValue yValue2 = constructionOnWorkVO.new YValue();
        yValue2.setName("未到");
        List<Integer> data1 = new LinkedList<>();
        List<Integer> data2 = new LinkedList<>();
        List<String> onLinePersons = new LinkedList<>();
        List<String> offLinePersons = new LinkedList<>();
        int onLineCount = 0;
        int offLineCount = 0;
        for(Department department : departments){
            Map<String,String> names = new HashMap<>();
            List<Employee> employees = employeeMapper.selectEmployeesByDepartmentId(department.getId(),outletId);
            for(Employee employee : employees){
                names.put(employee.getId(),employee.getName());
            }
            xValues.add(department.getName());
            int count = 0;
            String onLinePerson = "";
            String offLinePerson = "";
            List<CheckingDTO> checkingDTOs = checkingMapper.selectEmployeeToDayCheckingByDepartmentId("checking_"+outletId,outletId,department.getId());
            for(CheckingDTO checkingDTO : checkingDTOs){
                if(checkingDTO.getDevicetype()==1){
                    count++;
                    onLineCount++;
                    String name = names.get(checkingDTO.getEmployeeid());
                    if(!StringUtil.isBlank(name)){
                        onLinePerson += name + "、";
                        names.remove(checkingDTO.getEmployeeid());
                    }
                }
            }
            for(String key : names.keySet()){
                offLinePerson += names.get(key) + "、";
                offLineCount++;
            }
            data1.add(count);
            data2.add(employeeMapper.countEmployeeByDepartmentId(department.getId(),outletId)-count);
            if(onLinePerson.length()>0){
                onLinePerson = onLinePerson.substring(0,onLinePerson.length()-1);
            }
            if(offLinePerson.length()>0){
                offLinePerson = offLinePerson.substring(0,offLinePerson.length()-1);
            }
            onLinePersons.add(onLinePerson);
            offLinePersons.add(offLinePerson);
        }
        yValue1.setData(data1);
        yValue1.setPersons(onLinePersons);
        yValue2.setData(data2);
        yValue2.setPersons(offLinePersons);
        yValues.add(yValue2);
        yValues.add(yValue1);
        constructionOnWorkVO.setX(xValues);
        constructionOnWorkVO.setY(yValues);
        constructionOnWorkVO.setOnLineCount(onLineCount);
        constructionOnWorkVO.setOffLineCount(offLineCount);
        return constructionOnWorkVO;
    }

    @Override
    public ComeOnAndOutVO comeOnAndOut(int monitorId, int outletId) {
        ComeOnAndOutVO comeOnAndOutVO = new ComeOnAndOutVO();
        comeOnAndOutVO.setOnCount(checkingMapper.countByOutletIdAndType("checking_"+outletId,outletId,1));
        comeOnAndOutVO.setOutCount(checkingMapper.countByOutletIdAndType("checking_"+outletId,outletId,2));
        CheckingDTO checkingDTO = checkingMapper.selectLastChecking("checking_"+outletId,outletId);
        if(checkingDTO!=null && !StringUtil.isBlank(checkingDTO.getImageurl())){
            comeOnAndOutVO.setImageUrl(checkingDTO.getImageurl());
        }
        return comeOnAndOutVO;
    }

    private List<Date> getLastDays(int days){
        List<Date> dates = new ArrayList<>();
        for(int i=0;i<days;i++){
            dates.add(DateUtils.subDay(new Date(),days-i-1));
        }
        return dates;
    }

    @Override
    public List<Gas> getGas(int monitorId, int outletId, int type, String startTime, String endTime) {

        String sfm=startTime.substring(startTime.length()-8,startTime.length());

        List<String> list=DateUtils.findDaysStr(startTime,endTime);
        List<Gas> gases=new ArrayList<>();

        for(String str:list){
            String startstr=str+" "+"00:00:00";
            String endTime1 = str+" 23:59:59 ";
            List<Gas> listgas=gasMapper.selectGas(outletId, type, startstr, endTime1);
            //List<Gas> listgas=gasMapper.selectGas(outletId, type, str+" 00:00:00", str+" 23:59:59");
            Gas gas=forEachListGas(listgas,str);
            gases.add(gas);
        }
        return gases;
    }

    /**
     * 遍历GAS求平均值
     * @param listgas
     * @param str
     * @return
     */
    private Gas forEachListGas(List<Gas> listgas,String str){
        BigDecimal sum=BigDecimal.ZERO;
        BigDecimal val=null;
        String  val1 ="";
        for(Gas gas:listgas){
                if (gas.getVal() instanceof String){
                   val1 =  gas.getVal();
                }else {
                    if(gas!=null){
                        if(StringUtils.isNotBlank(gas.getVal())){
                            sum=sum.add(new BigDecimal(gas.getVal()));
                        }
                    }
                }

            }
       if ( sum  != BigDecimal.ZERO ){
           if(sum.compareTo(BigDecimal.ZERO)==0){
               val=BigDecimal.ZERO;
           }else{
               val=sum.divide(new BigDecimal(listgas.size()),2, BigDecimal.ROUND_HALF_UP);
           }
       }

        Gas gas=new Gas();
        gas.setDtStr(DateUtils.getDate2String(DateUtils.parseDate(str,"yyyy-MM-dd"),"yyyy-MM-dd"));
        gas.setDt(DateUtils.parseDate(str,"yyyy-MM-dd"));
        if(!listgas.isEmpty()||listgas.size()>=1){
            if(listgas.get(0)!=null){
                if ( val==null){
                    gas.setVal(val1);
                }else {
                    gas.setVal(val+"");
                }

                gas.setDttte(listgas.get(0).getDttte());
                gas.setRummager(listgas.get(0).getRummager());
                gas.setTbm_range(listgas.get(0).getTbm_range());
                gas.setOutletid(listgas.get(0).getOutletid());
                gas.setType(listgas.get(0).getType());
                gas.setId(listgas.get(0).getId());
            }
        }
        return  gas;
    }

    @Override
    public List<Settlement> getSettlement(int monitorId, int outletId, String startTime, String endTime) {
        return settlementMapper.selectSettlement(outletId, startTime, endTime);
    }
    @Override
    public Threshold getThreshold(int monitorId, int outletId, String thresholdType) {
        return thresholdMapper.selectThresholdByType(outletId, thresholdType);
    }


    @Autowired
    FoundationMapper foundationMapper;
    @Autowired
    WaterLevelMapper waterLevelMapper;

    @Autowired
    WaterInstruMentMapper waterInstruMentMapper;

    @Autowired
    WaterMonitorMapper waterMonitorMapper;
    /**
     * 基坑管理
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    @Override
    public Map<String,Object> foundManage(Integer currentMonitorId, Integer outletId) throws ParseException {
        Map<String,Object> data=new HashMap<>();
        //3.基坑入口，4.基坑出口'

        //在坑人数
        Integer number=foundpernumMapper.queryFoundPersonSum(outletId);
        //查询当前时间入坑人数cardid
        data.put("number",number);
        //获取最新的进出图片
        FoundInOutVo foundInOutVo=checkingMapper.queryNewDataUrlAndType("checking_"+outletId,outletId);
        if(foundInOutVo!=null){
            data.put("url",foundInOutVo.getImageUrl());//最新图片url
            data.put("type",foundInOutVo.getType());//3.基坑入口，4.基坑出口'
        }
        //通过outid查询当前工地所有基坑名称及id
        List<Map<String,Object>> listmap=new ArrayList<>();
        List<Foundation> list=foundationMapper.queryFoundationAll(outletId);
        for(Foundation foundation:list){
            Map<String,Object> map=new HashMap<>();
            map.put("foundid",foundation.getId());
            map.put("foundnamne",foundation.getName());
            //通过基坑id查询基坑水位设备关联表获取水位设备id
            Map<String,Object> watermap=queryWaterMap(foundation.getId(),outletId);
            BigDecimal min= (BigDecimal) watermap.get("min");
            Integer wterlevelstatus= (Integer) watermap.get("wterlevelstatus");
            List<String> values= (List<String>) watermap.get("y");
            Integer ring=0;
            BigDecimal sum   = BigDecimal.ZERO;
            BigDecimal avg  = BigDecimal.ZERO;
            if(values!=null){
                for(String str:values){
                    ring++;
                    sum=sum.add(new BigDecimal(str));
                }
                avg=sum.divide(new BigDecimal(ring));
            }
//            //0正常 1不正常
//            BigDecimal avg=sum.divide(new BigDecimal(ring));
            if(min!=null){
                if(avg.compareTo(min)>0){
                    map.put("wterlevelstatus",1);
                }else{
                    map.put("wterlevelstatus",0);
                }
            }else{
                map.put("wterlevelstatus",0);
            }
            if(wterlevelstatus!=null){
                map.put("wterlevelstatus",1);
            }
            map.put("wterlevel",avg);
            watermap.remove("min");
            map.put("wterlevelcontent",watermap);
            map.put("noxiousgas",0);
            map.put("noxiousgasstatus",0);
            listmap.add(map);
        }
        data.put("found",listmap);
        data.put("settlestatus",0);

        return data;
    }

    /**
     *
     * @param foundid 基坑id
     * @param currentOutletId  工地id
     * @param depth 基坑深度
     * @return
     */
    public Map<String,Object> queryWaterMap(Integer foundid,Integer currentOutletId){
        //根据foundid和outletid查询基坑与水位监测点关联表==得到监测点表id
        Map<String,Object> map=new HashMap<>();
        List<Foundwatermonitor> foundwatermonitors=foundationMapper.queryFoundwatermonitorByFoundid(foundid,currentOutletId);
        //通过监测点表id
        List<String> x=new ArrayList<>();//监测点地址
        List<String> y=new ArrayList<>();//值
        List<Integer> maxs=new ArrayList<>();//1报警  0不报警
        for(Foundwatermonitor foundwatermonitor:foundwatermonitors){
            //根据监测点表id查询当前所有可用设备
            List<WaterEquipmentVO> equipment=waterInstruMentMapper.queryEquipmentByWmid(foundwatermonitor.getWmid(),currentOutletId);
            //根据设备ID查询最新的值==处理预警值
            String depth=waterMonitorMapper.queryDepth(currentOutletId);
            for(WaterEquipmentVO waterEquipmentVO:equipment){
                WaterLevelVO waterLevelVO=waterLevelMapper.qieruNewValue(waterEquipmentVO.getDeviceid(),currentOutletId);
                x.add(waterLevelVO.getAddress());
                Threshold thresholdend=thresholdMapper.queryWaterThreshold(currentOutletId,waterLevelVO.getMonitortime());
                List<Threshold> thresholds=thresholdMapper.queryWaterThresholdLists(currentOutletId,waterLevelVO.getMonitortime());
                List<BigDecimal> listthres=new ArrayList<>();
                for(Threshold threshold:thresholds){
                    if(StringUtils.isNotBlank(threshold.getMax_val())){
                        lists(threshold.getMax_val(),listthres);
                    }
                }
                BigDecimal aa   =   new   BigDecimal(depth);
                BigDecimal bb  =   new   BigDecimal(waterLevelVO.getWatervalue());
                BigDecimal sum=aa.subtract(bb);
                if(bb.compareTo(BigDecimal.ZERO)==0){
                    sum=aa;
                }
                if(StringUtils.isNotBlank(depth)){
                    if(thresholds==null){
                        y.add(sum+"");
                        maxs.add(0);
                        continue;
                    }

                    if(thresholdend!=null &&StringUtils.isNotBlank(thresholdend.getMax_val())){
                        BigDecimal min=Collections.min(listthres);
                        map.put("min",min);
                        if(aa.compareTo(BigDecimal.ZERO)<0 && bb.compareTo(BigDecimal.ZERO)<=0){
                            if(sum.compareTo(min)>0){
                                maxs.add(1);
                                map.put("wterlevelstatus",1);
                            }else{
                                maxs.add(0);
                            }
                            y.add(sum+"");
                        }
                    }else{
                        y.add(sum+"");
                    }

                }else{
                    maxs.add(0);
                }
            }
            map.put("x",x);
            map.put("y",y);
            map.put("maxs",maxs);
            map.put("depth",depth);
        }
        return map;
    }


    @Override
    public Map<String, Object> queryLedDisplay(int monitorId, int outletId) throws ParseException {
        Map<String, Object> map=new HashMap<>();
        List<String> rucardids=foundpernumMapper.queryCardidList(outletId);
        //根据cardid获取员工相关信息
        // List<LedDisplayResVo> list=checkingMapper.queryLedDisplayCount(rucardids);

        List<String> ids=new ArrayList<>();
        Set<LedDisplayResVoTwo> set1=checkingMapper.queryFoundLedDisplayCount(outletId,rucardids);
        for(LedDisplayResVoTwo ledDisplayResVoTwo:set1){
            ids.add(ledDisplayResVoTwo.getId());
        }
        ids.add("25");
        ids.add("34");
        Set<LedDisplayResVoTwo> set=checkingMapper.queryLedDisplayCountAll(outletId,ids);
        set.addAll(set1);
        //查询在坑总人数
        Integer sumpernum=checkingMapper.queryFoundSumPerNum(outletId);
        map.put("sumpernum",sumpernum);
        map.put("groupdata",set);
        return map;

    }


    @Override
    public void saveFoundinout(int monitorId, int outletId, FountVo fountVo) {
        if(fountVo.getType().equals("3")){
            foundpernumMapper.delete(fountVo.getCardid(),Integer.valueOf(fountVo.getOutletId()));
            foundpernumMapper.save(fountVo.getCardid(),Integer.valueOf(fountVo.getOutletId()));
        }
        if(fountVo.getType().equals("4")){
            foundpernumMapper.delete(fountVo.getCardid(),Integer.valueOf(fountVo.getOutletId()));
        }
    }

    @Override
    public Map<String, Object> zxFountInOutData(int monitorId, int outletId,FountVo fountVo) {
        Map<String, Object> map=new HashMap<>();
        Integer number=foundpernumMapper.queryFoundPersonSum(outletId);
        //
        //
        String url="";
        if(fountVo==null){
            url=checkingMapper.queryZxImageUrlInit("checking_"+outletId);
        }else{
            url=checkingMapper.queryZxImageUrl("checking_"+outletId,fountVo.getCardid(),fountVo.getType());
        }
        map.put("number",number);
        map.put("url",url);
        return map;
    }

    private List<Integer> strtoList(String str){
        Map maps = (Map) JSON.parse(str);
        List<Integer> list=new ArrayList<>();
        for (Object map : maps.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
            list.add(Integer.valueOf(((Map.Entry)map).getKey().toString()));
        }
        return list;
    }



    private List<BigDecimal> lists(String str,List<BigDecimal> list){
        Map maps = (Map) JSON.parse(str);
        for (Object map : maps.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
            list.add(new BigDecimal(((Map.Entry)map).getKey()+""));
        }
        return list;
    }


    private static void ListMapSort(List<Map.Entry<String, BigDecimal>> list) {
        Collections.sort(list, new Comparator<Map.Entry<String, BigDecimal>>() {
            public int compare(Map.Entry<String, BigDecimal> o1, Map.Entry<String, BigDecimal> o2) {
                // compareTo方法 (x < y) ? -1 : ((x == y) ? 0 : 1)
                return o1.getValue().compareTo(o2.getValue());
            }
        });
    }

    @Override
    public Map<String, Object> personManage(Integer currentMonitorId, Integer outletId) {
        Map<String,Object> map=new HashMap<>();
        Integer onCount=checkingMapper.countByOutletIdAndType("checking_"+outletId,outletId,1);//进场总人数
        Integer outCount=checkingMapper.countByOutletIdAndType("checking_"+outletId,outletId,2);//出场总人数\
        Integer attendance = onCount - outCount ;
        if (attendance< 0){
            attendance = 0;
        }
        if (outletId == 3){
        map.put("onCount",178);//入场总人数
        map.put("outCount",5);//出场总人数
        map.put("attendance",173);
        }else if (outletId == 2){
            map.put("onCount",89);//入场总人数
            map.put("outCount",5);//出场总人数
            map.put("attendance",84);
        }else {
            map.put("onCount",onCount);//入场总人数
            map.put("outCount",outCount);//出场总人数
            map.put("attendance",attendance);
        }


        //进出人数及照片url
        ComeOnAndOutVO comeOnAndOutVO = new ComeOnAndOutVO();
//        comeOnAndOutVO.setOnCount(checkingMapper.countByOutletIdAndType("checking_"+outletId,outletId,1));//入场总人数
//        comeOnAndOutVO.setOutCount(checkingMapper.countByOutletIdAndType("checking_"+outletId,outletId,2));//入场总人数
        CheckingDTO checkingDTO = checkingMapper.selectLastChecking("checking_"+outletId,outletId);
          if(checkingDTO!=null && !StringUtil.isBlank(checkingDTO.getImageurl())){
//            //comeOnAndOutVO.setImageUrl(checkingDTO.getImageurl());//url
            map.put("imageUrl",checkingDTO.getImageurl());
      }
        //重要岗位
        List<BuildingSiteVO> importantjob = new ArrayList<>();
        String brandName = brandMapper.selectTopBrandNameByOutletId(outletId);
        String siteName = outletMapper.selectOutletNameByOutletId(outletId);
        List<Job> jobs = jobMapper.selectJobByOutletId(outletId);
        for(Job job : jobs){
            BuildingSiteVO buildingSiteVO = new BuildingSiteVO();
            buildingSiteVO.setBrandName(brandName);
            buildingSiteVO.setSiteName(siteName);
            buildingSiteVO.setJobName(job.getName());
            List<Employee> employees = employeeMapper.selectEmployeeByJobIdAndOutletId(job.getId(),job.getOutletid());
            List<JobUserVO> jobUsers = new ArrayList<>();
            for(Employee employee : employees){
                JobUserVO jobUserVO = new JobUserVO();
                jobUserVO.setEmployeeId(employee.getId());
                jobUserVO.setUserName(employee.getName());
                if(!StringUtil.isBlank(employee.getHeadurl())){
                    jobUserVO.setHeadUrl(ssmBaseUrl+employee.getHeadurl());
                }
                //根据员工今日最后一次打卡是否是进场来判断该员工目前是否在岗
                jobUserVO.setOnLine(2);
                CheckingDTO checkingDTO2 = checkingMapper.selectLastTimeByEmployeeId("checking_"+outletId,employee.getId(),outletId);
                if(checkingDTO2!=null){
                    if(checkingDTO2.getDevicetype()==1 && DateUtils.getIntervalDaysToToday(checkingDTO2.getTime())==0){
                        jobUserVO.setOnLine(1);
                        jobUserVO.setOnTime(checkingDTO2.getTime());
                        if(!StringUtil.isBlank(checkingDTO2.getImageurl())){
                            jobUserVO.setHeadUrl(ssmBaseUrl+checkingDTO2.getImageurl());
                        }
                    }
                }
                jobUsers.add(jobUserVO);
            }
            Collections.sort(jobUsers);
            List<JobUserVO> jobUsers1 = new ArrayList<>();
            if (employees.size() == 0){
                buildingSiteVO.setEmployeeId("");
                buildingSiteVO.setHeadUrl("");
                buildingSiteVO.setOnLine(0);
                buildingSiteVO.setUserName("");
            }else {
                buildingSiteVO.setEmployeeId(jobUsers.get(0).getEmployeeId());
                buildingSiteVO.setHeadUrl(jobUsers.get(0).getHeadUrl());
                buildingSiteVO.setOnLine(jobUsers.get(0).getOnLine());
                buildingSiteVO.setUserName(jobUsers.get(0).getUserName());
            }

            importantjob.add(buildingSiteVO);
        }


         //实到应到
        ConstructionOnWorkVO constructionOnWorkVO = new ConstructionOnWorkVO();
        List<String> xValues = new LinkedList<>();
        List<ConstructionOnWorkVO.YValue> yValues = new ArrayList<>();
        //所有施工班的部门
        List<Department> departments = departmentMapper.selectDepartmentByOutletIdAndType(outletId,1);
        ConstructionOnWorkVO.YValue yValue1 = constructionOnWorkVO.new YValue();
        yValue1.setName("已到");
        ConstructionOnWorkVO.YValue yValue2 = constructionOnWorkVO.new YValue();
        yValue2.setName("未到");
        List<Integer> data1 = new LinkedList<>();
        List<Integer> data2 = new LinkedList<>();
        List<String> onLinePersons = new LinkedList<>();
        List<String> offLinePersons = new LinkedList<>();
        int onLineCount = 0;
        int offLineCount = 0;
        for(Department department : departments){
            Map<String,String> names = new HashMap<>();
            List<Employee> employees = employeeMapper.selectEmployeesByDepartmentId(department.getId(),outletId);
            for(Employee employee : employees){
                names.put(employee.getId(),employee.getName());
            }
            xValues.add(department.getName());
            int count = 0;
            String onLinePerson = "";
            String offLinePerson = "";
            List<CheckingDTO> checkingDTOs = checkingMapper.selectEmployeeToDayCheckingByDepartmentId("checking_"+outletId,outletId,department.getId());
            for(CheckingDTO checking : checkingDTOs){
                if(checking.getDevicetype()==1){
                    count++;
                    onLineCount++;
                    String name = names.get(checking.getEmployeeid());
                    if(!StringUtil.isBlank(name)){
                        onLinePerson += name + "、";
                        names.remove(checking.getEmployeeid());
                    }
                }
            }
            for(String key : names.keySet()){
                offLinePerson += names.get(key) + "、";
                offLineCount++;
            }
            data1.add(count);
            data2.add(employeeMapper.countEmployeeByDepartmentId(department.getId(),outletId)-count);
            if(onLinePerson.length()>0){
                onLinePerson = onLinePerson.substring(0,onLinePerson.length()-1);
            }
            if(offLinePerson.length()>0){
                offLinePerson = offLinePerson.substring(0,offLinePerson.length()-1);
            }
            onLinePersons.add(onLinePerson);
            offLinePersons.add(offLinePerson);
        }
        yValue1.setData(data1);
        yValue1.setPersons(onLinePersons);
        yValue2.setData(data2);
        yValue2.setPersons(offLinePersons);
        yValues.add(yValue2);
        yValues.add(yValue1);
        constructionOnWorkVO.setX(xValues);
        constructionOnWorkVO.setY(yValues);
        constructionOnWorkVO.setOnLineCount(onLineCount);
        constructionOnWorkVO.setOffLineCount(offLineCount);

        //查询总人数
        Integer sum=employeeMapper.querySumPersonNum(outletId);
        if (outletId == 3 ){
            map.put("onLineCount",178);
        }else if (outletId == 2 ){
            map.put("onLineCount",84);
        }else {
            map.put("onLineCount",onLineCount);
        }
        map.put("offLineCount",sum);
        BigDecimal divide=BigDecimal.ZERO;
        if(onLineCount==0){
            divide=BigDecimal.ZERO;
        }else{
            divide = new BigDecimal(onLineCount).divide(new BigDecimal(sum), 2, RoundingMode.HALF_UP);
        }
        map.put("onoffLinepercent",divide.multiply(new BigDecimal(100))+"%");
        map.put("importantjob",importantjob);
       // map.put("constructionOnWorkVO",constructionOnWorkVO);
        return map;
    }

    @Override
    public Map<String, Object> constructiondays(Integer currentMonitorId, Integer currentOutletId) {
        Map<String,Object> map=new HashMap<>();
        List<Startdatetab> list=startdatetabMapper.queryConstructiondays(currentOutletId);
        long daynumbers=0;
        if(list!=null&&list.size()>0){
            daynumbers=DateUtils.betweenDays(new Date(),list.get(0).getStartdate())+1;
        }
        map.put("daynumbers",daynumbers);
        return map;
    }


    /**
     * 人员管理弹窗
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    @Override
    public Map<String, Object> personManagePopupWindow(Integer currentMonitorId, Integer currentOutletId) {
        return null;
    }


    @Override
    public Map<String, Object> homePageWaterPopupWindow(Integer currentMonitorId, Integer outletId, Integer foundid) {
        Map<String,Object> watermap=queryWaterMap(foundid,outletId);
        watermap.remove("min");
        return watermap;
    }

    /**
     * 0上1下
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    @Override
    public Map<String, Object> homePageGasPopupWindowInit(Integer currentMonitorId, Integer currentOutletId){
        String types="6,7,8,9,10,11,12";
        List<String> list1=Arrays.asList(types.split(","));
        List<GasRespVO> list=new ArrayList<>();
        for(String str:list1){
            GasRespVO gasRespVO=gasMapper.queryGasByType(currentOutletId,str);
            list.add(gasRespVO);
        }

     //   List<GasRespVO> list=gasMapper.homePageGasPopupWindow(currentOutletId);
        String str= MyHttpClientPool.fetchByGetMethod("https://www.tianqiapi.com/api/?version=v6&appid=61783389&appsecret=VamwF6KQ&city=杭州");
        Map<String, Object> map=new HashMap<>();
        conversionType(list,str,currentOutletId,map);
        return map;
    }


    @Override
    public Map<String, Object> homePageGasPopupWindow(Integer currentMonitorId, Integer currentOutletId){
        String types="6,7,8,9,10,11,12";
        List<String> list1=Arrays.asList(types.split(","));
        List<GasRespVO> list=new ArrayList<>();
        for(String str:list1){
            GasRespVO gasRespVO=gasMapper.queryGasByType(currentOutletId,str);
            list.add(gasRespVO);
        }
        String str="";
        //List<GasRespVO> list=gasMapper.homePageGasPopupWindow(currentOutletId);
         str= MyHttpClientPool.fetchByGetMethod("https://www.tianqiapi.com/api/?version=v6&appid=61783389&appsecret=VamwF6KQ&city=杭州");
        Map<String, Object> map=new HashMap<>();
        conversionType(list,str,currentOutletId,map);
        return map;
    }

    private void conversionType(List<GasRespVO> list,String str,Integer outletid,Map<String, Object> map){
        System.out.println(str);
        JSONObject obj=JSON.parseObject(str);
        String errcode = obj.getString("errcode");
        if(errcode == null){

            System.out.println(obj.get("air_pm25"));
            String pm25=obj.getString("air_pm25");
            System.out.println(obj.get("win"));
            String win=obj.getString("win");
            System.out.println(obj.get("win_speed"));
            String win_speed=obj.getString("win_speed");
            System.out.println(obj.get("humidity"));
            String humidity=obj.getString("humidity");
            System.out.println(obj.get("tem"));
            String tem=obj.getString("tem");
            System.out.println(obj.get("pressure"));
            String pressure=obj.getString("pressure");
            List<Gas> gasList = new ArrayList<>();
            List<KeyValue> typeValueList = new ArrayList<>();
            typeValueList.add(new KeyValue("31",pm25));
            typeValueList.add(new KeyValue("32",win));
            typeValueList.add(new KeyValue("33",win_speed));
            typeValueList.add(new KeyValue("34",humidity));
            typeValueList.add(new KeyValue("35",tem));
            typeValueList.add(new KeyValue("36",pressure));
            for (KeyValue typeValue : typeValueList) {
                Gas gas = new Gas();
                String nowtimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                Date nowDate = DateUtils.StringToDate(nowtimeString);
                gas.setDt(nowDate);
                String deviceId = gasMapper.quertDevice(outletid);
                gas.setDevice_id(deviceId);
                gas.setOutletid(outletid);
                gas.setTbm_range("");
                gas.setType(typeValue.getType());
                gas.setVal(typeValue.getVal());
                gas.setRummager("WHJXCTKJ33725122468B");
                gasList.add(gas);
            }
            gasMapper.saveGasList(gasList);
            String types="31,32,33,34,35,36";
            List<String> AllType=Arrays.asList(types.split(","));
            Map<String,GasRespVO> mapHz=new HashMap();
            for(String hz:AllType){
                GasRespVO gasRespVO=gasMapper.queryGasByType(outletid,hz);
                if (hz.equals("31")){
                    mapHz.put("pm25",gasRespVO);
                }else if (hz.equals("32")){
                    mapHz.put("win",gasRespVO);
                }else if (hz.equals("33")){
                    mapHz.put("win_speed",gasRespVO);
                }else if (hz.equals("34")){
                    mapHz.put("humidity",gasRespVO);
                }else if (hz.equals("35")){
                    mapHz.put("tem",gasRespVO);
                } else if (hz.equals("36")){
                    mapHz.put("pressure",gasRespVO);
                }
            }
            for(GasRespVO gasRespVO:list){
                GasRespVO gasRespVO1=gasMapper.queryLast(outletid,gasRespVO.getName(),gasRespVO.getId());
            if (outletid != 5){
                if(gasRespVO.getName().equals("6")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("PM10");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(55+"");
                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("PM10",gasVo);
                }
            }else {
                if(gasRespVO.getName().equals("6")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("大气气压");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("pressure").getValue());
                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("pressure",gasVo);
                }
            }
                if (outletid != 5) {
                    if (gasRespVO.getName().equals("7")) {
                        HzGasVo gasVo = new HzGasVo();
                        gasVo.setName("PM25");
                        gasVo.setSite(gasRespVO.getValue());
                        gasVo.setHangzhou(mapHz.get("pm25").getValue());
                        CompareTO(gasRespVO, gasRespVO1, gasVo);
                        map.put("PM25", gasVo);
                    }
                }else {
                    if (gasRespVO.getName().equals("7")) {
                        HzGasVo gasVo = new HzGasVo();
                        gasVo.setName("PM25");
                        gasVo.setSite(gasRespVO.getValue());
                        gasVo.setHangzhou("0."+mapHz.get("pm25").getValue());
                        CompareTO(gasRespVO, gasRespVO1, gasVo);
                        map.put("PM25", gasVo);
                    }
                }
              if (outletid != 5){
                  if(gasRespVO.getName().equals("8")){
                      HzGasVo gasVo=new HzGasVo();
                      gasVo.setName("噪声");
                      gasVo.setSite(gasRespVO.getValue());
                      gasVo.setHangzhou(59.8+"");
                      CompareTO(gasRespVO, gasRespVO1, gasVo);
                      map.put("noise",gasVo);
                  }
              }
                if(gasRespVO.getName().equals("9")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("温度");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("tem").getValue());
                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("temperature",gasVo);
                }
                if(gasRespVO.getName().equals("10")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("湿度");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("humidity").getValue());
                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("humidity",gasVo);
                }
                if(gasRespVO.getName().equals("11")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("风速");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("win_speed").getValue());
                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("windspeed",gasVo);
                }
                if(gasRespVO.getName().equals("12")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("风向");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("win").getValue());
//                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("wind",gasVo);
                }
            }
        }else{
            //如果api接口调用异常直接从数据库取最后一次杭州天气数据
            for(GasRespVO gasRespVO:list){
                GasRespVO gasRespVO1=gasMapper.queryLast(outletid,gasRespVO.getName(),gasRespVO.getId());
                String types="31,32,33,34,35,36";
                List<String> AllType=Arrays.asList(types.split(","));
                Map<String,GasRespVO> mapHz=new HashMap();
                for(String hz:AllType){
                    GasRespVO gasRespVO11=gasMapper.queryGasByType(outletid,hz);
                    if (hz.equals("31")){
                        mapHz.put("pm25",gasRespVO11);
                    }else if (hz.equals("32")){
                        mapHz.put("win",gasRespVO11);
                    }else if (hz.equals("33")){
                        mapHz.put("win_speed",gasRespVO11);
                    }else if (hz.equals("34")){
                        mapHz.put("humidity",gasRespVO11);
                    }else if (hz.equals("35")){
                        mapHz.put("tem",gasRespVO11);
                    }else if (hz.equals("36")){
                        mapHz.put("pressure",gasRespVO);
                    }
                }
                if (outletid != 5){
                    if(gasRespVO.getName().equals("6")){
                        HzGasVo gasVo=new HzGasVo();
                        gasVo.setName("PM10");
                        gasVo.setSite(gasRespVO.getValue());
                        gasVo.setHangzhou(55+"");
                        CompareTO(gasRespVO, gasRespVO1, gasVo);
                        map.put("PM10",gasVo);
                    }
                }else {
                    if(gasRespVO.getName().equals("6")){
                        HzGasVo gasVo=new HzGasVo();
                        gasVo.setName("大气气压");
                        gasVo.setSite(gasRespVO.getValue());
                        gasVo.setHangzhou(mapHz.get("pressure").getValue());
                        CompareTO(gasRespVO, gasRespVO1, gasVo);
                        map.put("pressure",gasVo);
                    }
                }
                if(gasRespVO.getName().equals("7")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("PM25");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("pm25").getValue());
                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("PM25",gasVo);
                }
               if (outletid != 5){
                   if(gasRespVO.getName().equals("8")){
                       HzGasVo gasVo=new HzGasVo();
                       gasVo.setName("噪声");
                       gasVo.setSite(gasRespVO.getValue());
                       gasVo.setHangzhou(59.8+"");
                       CompareTO(gasRespVO, gasRespVO1, gasVo);
                       map.put("noise",gasVo);
                   }
               }
                if(gasRespVO.getName().equals("9")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("温度");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("tem").getValue());
                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("temperature",gasVo);
                }
                if(gasRespVO.getName().equals("10")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("湿度");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("humidity").getValue());
                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("humidity",gasVo);
                }
                if(gasRespVO.getName().equals("11")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("风速");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("win_speed").getValue());
                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("windspeed",gasVo);
                }
                if(gasRespVO.getName().equals("12")){
                    HzGasVo gasVo=new HzGasVo();
                    gasVo.setName("风向");
                    gasVo.setSite(gasRespVO.getValue());
                    gasVo.setHangzhou(mapHz.get("win").getValue());
//                    CompareTO(gasRespVO, gasRespVO1, gasVo);
                    map.put("wind",gasVo);
                }
            }
        }
    }

    /**
     * 对比上一次
     * @param gasRespVO
     * @param gasRespVO1
     * @param gasVo
     */
    private void CompareTO(GasRespVO gasRespVO, GasRespVO gasRespVO1, HzGasVo gasVo) {
        Integer a=new BigDecimal(gasRespVO.getValue()).compareTo(new BigDecimal(gasRespVO1.getValue()));
        if(a>0||a==0){
            gasVo.setState(0);
        }else{
            gasVo.setState(1);
        }
    }

    @Override
    public HarmfulGasVO homePageHarmfulGasPopupWindow(Integer currentMonitorId, Integer currentOutletId) {

        HarmfulGasVO harmfulGasVO=new HarmfulGasVO();
        harmfulGasVO.setYyht(gasMapper.getGasListByType(currentOutletId,3));
        harmfulGasVO.setLhq(gasMapper.getGasListByType(currentOutletId,4));
        harmfulGasVO.setKrq(gasMapper.getGasListByType(currentOutletId,1));
        harmfulGasVO.setYq(gasMapper.getGasListByType(currentOutletId,2));
        return harmfulGasVO;
    }
    @Override
    public Object saveData(Integer currentMonitorId, Integer currentOutletId, String startTime) {
        if (StringUtil.isBlank(startTime)){
            return GlobalApiResult.failure("日期不能为空");
        }
        Date startDate = DateUtils.StringToDate(startTime,"yyyy-MM-dd");
        String nowtimeString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date nowDate = DateUtils.StringToDate(nowtimeString);
        if(startDate.after(nowDate)){
            return GlobalApiResult.failure("日期传入时间不能大于今天");
        }
        startdatetabMapper.save(currentOutletId,startTime);
        Integer id = startdatetabMapper.queryIdByStartTime(currentOutletId, startTime);
        startdatetabMapper.deleteStartTime(currentOutletId,id);
        return "添加成功";
    }

    @Override
    public String queryData(Integer currentMonitorId, Integer currentOutletId) {
        String startTime = startdatetabMapper.queryStartTime(currentOutletId);

        return startTime;
    }

    @Override
    public Map<String, Object> queryTitle(Integer currentMonitorId, Integer currentOutletId) {
        HashMap<String, Object> map = new HashMap<>();
        String title = startdatetabMapper.queryTitle(currentOutletId);
        map.put("title",title);
        map.put("outletid",currentOutletId);
        return map;
    }

    @Override
    public List<Homepageimage> queryDgImage(Integer currentMonitorId, Integer currentOutletId) {

        List<Homepageimage> list=startdatetabMapper.querySgImage(currentOutletId);

        return list;
    }



    @Override
    public Object savePageImage(Integer currentMonitorId, Integer currentOutletId, String imageurl, String name) {
        startdatetabMapper.saveImage(imageurl,name,currentOutletId);
        return "success";
    }

    @Override
    public Object SaveDgImage(Integer currentMonitorId, Integer currentOutletId, String imageurl, Integer type) {
        startdatetabMapper.saveDgImage(imageurl,type,currentOutletId);
        return "success";
    }

    @Override
    public Object queryImage(Integer currentMonitorId, Integer currentOutletId) {
        return startdatetabMapper.queryImage(currentOutletId);
    }

    @Override
    public Object updateStatus(Integer currentMonitorId,String status, Integer currentOutletId, String iodeviceid) {
        startdatetabMapper.updateStatus(status,iodeviceid,currentOutletId);
        return "更新成功";
    }

    @Override
    public Object queryIoStatus(Integer currentMonitorId, Integer currentOutletId) {
        return  startdatetabMapper.queryIoStatus(currentOutletId);
    }

    @Override
    public Object queryIpPort(Integer currentMonitorId, Integer currentOutletId) {
        return startdatetabMapper.selectIpPort(currentOutletId);
    }

    @Override
    public IocontrollerVO selectRoad(Integer currentMonitorId,String devicename, Integer currentOutletId) {
        return startdatetabMapper.selectRoad(devicename,currentOutletId);
    }

    @Override
    public Object updateOnline(Integer currentMonitorId,  List<IoStatus> list) {
        for (IoStatus ioStatus : list) {
            IoOnline iocontrollerVO = startdatetabMapper.queryIoDevice(ioStatus.getIodeviceid());
            if (iocontrollerVO == null){
               return GlobalApiResult.failure("没有该IO控制器设备");
            }
            startdatetabMapper.updateOnline(ioStatus.getOnline(),ioStatus.getIodeviceid());
        }
        return  GlobalApiResult.success("更新成功");
    }

    @Override
    public String selectDeviceNameByDevicetype(Integer currentMonitorId, Integer currentOutletId, String devicetype) {
        return startdatetabMapper.selectDeviceNameByDevicetype(devicetype,currentOutletId);
    }

    @Override
    public IoOnline queryIoDevice(Integer currentMonitorId,String iodeviceid) {

        return startdatetabMapper.queryIoDevice(iodeviceid);
    }

    @Override
    public String queryPm25(Integer currentMonitorId, Integer outletid) {
        return  startdatetabMapper.queryPm25(7,outletid);
    }

    @Override
    public ThresholdRespVO queryPm25ValueColer(Integer currentMonitorId, Integer outletid) {
        return   startdatetabMapper.queryPm25ValueColer("gas_7",outletid);
    }

    @Override
    public List<String> selectDeviceNameByDevicetypes(Integer currentMonitorId, Integer outletid, String devicetype) {
        return startdatetabMapper.selectDeviceNameByDevicetypes(devicetype,-1,outletid);
    }

    @Override
    public Object updateAutomanual(Integer currentMonitorId, Integer currentOutletId, AutomanualVO automanualVO) {
        startdatetabMapper.updateAutomanual(automanualVO.getId(),automanualVO.getAutomanual(),currentOutletId);
        return "修改成功";
    }

    @Override
    public IocontrollerVO selectReportdevice(Integer currentMonitorId, Integer currentOutletId, Integer id) {
       return  startdatetabMapper.selectReportdevice(id,currentOutletId);
    }
}
