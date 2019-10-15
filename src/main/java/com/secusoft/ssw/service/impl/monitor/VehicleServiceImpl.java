package com.secusoft.ssw.service.impl.monitor;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.ScreenVehicleMapper;
import com.secusoft.ssw.mapper.monitor.VehicleMapper;
import com.secusoft.ssw.mapper.monitor.VehicleRecordMapper;
import com.secusoft.ssw.model.monitor.Vehicle;
import com.secusoft.ssw.model.monitor.VehicleRecord;
import com.secusoft.ssw.model.viewobject.Request.VehiclePageVo;
import com.secusoft.ssw.model.viewobject.Request.VehicleRecordVo;
import com.secusoft.ssw.model.viewobject.Response.*;
import com.secusoft.ssw.service.VehicleService;
import com.secusoft.ssw.util.DateUtils;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleMapper vehicleMapper;

    @Autowired
    ScreenVehicleMapper screenVehicleMapper;

    @Autowired
    VehicleRecordMapper vehicleRecordMapper;

    @Transactional
    @Override
    public GlobalApiResult<Object> saveORupdateVehicle(int monitorId, Integer outletId, Vehicle vehicle) {
        String msg="";
        Integer id;
        vehicle.setOutletid(outletId);
        if(vehicle.getId()==null){
            //添加
              Vehicle vehicle1=vehicleMapper.queryVehiclePlateNumber(outletId,vehicle.getPlate_number());
              if(vehicle1!=null){
                  return GlobalApiResult.failure("该车牌车辆已经存在");
              }
             id=vehicleMapper.saveVehicle(vehicle);
            msg="添加成功";
        }else{
            //修改
             id=vehicleMapper.updateVehicle(vehicle);
            msg="修改成功";
        }
        return GlobalApiResult.success(msg);
    }

    @Transactional
    @Override
    public GlobalApiResult<Object> deleteVehicleByIds(Integer currentMonitorId,Integer outletId, List<String> list) {
        vehicleMapper.deleteVehicleByIds(list,outletId);
        return GlobalApiResult.success("删除成功");
    }

    @Override
    public GlobalApiResult<Object> getVehicleList(Integer currentMonitorId,Integer outletId, VehiclePageVo vehiclePageVo) {
        vehiclePageVo.setOutletid(outletId);
        PageHelper.startPage(vehiclePageVo.getCurrentPage(),vehiclePageVo.getPageSize());
        List<Vehicle> vehicles = vehicleMapper.getVehicleList(vehiclePageVo);
        int count=vehicleMapper.getVehicleListCount(vehiclePageVo);
        PageInfo<Vehicle> pageInfo = new PageInfo<>(vehicles);
        pageInfo.setTotal(count);
        return GlobalApiResult.success(pageInfo);
    }


    @Override
    public GlobalApiResult<Object> queryVehicleTypeList(Integer currentMonitorId,Integer outletid) {
        List<String> list = vehicleMapper.queryVehicleTypeList(outletid);
        return GlobalApiResult.success(list);
    }

    @Override
    public GlobalApiResult<Object> queryCompanyList(Integer currentMonitorId,Integer outletid) {
        List<String> list = vehicleMapper.queryCompanyList(outletid);
        return GlobalApiResult.success(list);
    }


    @Override
    public GlobalApiResult<Object> queryVehicleCount(Integer currentMonitorId,Integer outletid) {
        List<VehicleCountVO> list=vehicleMapper.queryVehicleCount(outletid);
        Integer sumnumber=vehicleMapper.querySumnumber(outletid);
        Map<String,Object> map=new HashMap<>();
        map.put("sumnumber",sumnumber);
        map.put("VehicleTypeCount",list);
        return GlobalApiResult.success(map);
    }


    /**
     *今日进出统计
     * @param currentMonitorId
     * @return
     */
    @Override
    public GlobalApiResult<Object> queryToDayInOutCount(Integer currentMonitorId,Integer outletid,int flag) {

        //查询所有车辆类型
        List<String> vehicleTypelist = vehicleMapper.queryVehicleTypeList(outletid);
        //组装数据
        List<String> x=new ArrayList<>();
        List<Object> y=new ArrayList<>();
        Map<String,Object> map0=new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> entryMap=new HashMap<>();
        entryMap.put("name","进场");
        Map<String,Object> outMap=new HashMap<>();
        outMap.put("name","出场");
        List<String> entryList=new ArrayList<>();
        List<String> outList=new ArrayList<>();
        //遍历类型
        for(String str:vehicleTypelist){
             //通过车辆类型查询当前时间内车辆进出情况
            List<VehicleTypeVo> entryoutlist=new ArrayList<>();
            if(str.equals("未知")){
                entryoutlist=vehicleMapper.queryOtherEntryOutList(str,flag,outletid);
            }else{
                 entryoutlist=vehicleMapper.queryEntryOutList(str,flag,outletid);
            }

            if(entryoutlist.isEmpty()){
                continue;
            }
            x.add(str);
            for(VehicleTypeVo vehicleTypeVo:entryoutlist){
                if(StringUtils.isBlank(vehicleTypeVo.getTypename())){
                    continue;
                }
                //组装数据
                if(entryoutlist.size()==1){
                    if(vehicleTypeVo.getType()==0&& StringUtils.isNotBlank(vehicleTypeVo.getUrl())){
                        entryList.add(vehicleTypeVo.getNumber().toString());
                        outList.add("0");
                    }
                    if(vehicleTypeVo.getType()==1&& StringUtils.isNotBlank(vehicleTypeVo.getUrl())){
                        outList.add(vehicleTypeVo.getNumber().toString());
                        entryList.add("0");
                    }

                }
                if(entryoutlist.size()==2){
                    if(vehicleTypeVo.getType()==0&& StringUtils.isNotBlank(vehicleTypeVo.getUrl())){
                        entryList.add(vehicleTypeVo.getNumber().toString());
                    }
                    if(vehicleTypeVo.getType()==1&& StringUtils.isNotBlank(vehicleTypeVo.getUrl())){
                        outList.add(vehicleTypeVo.getNumber().toString());
                    }
                }
            }
            entryMap.put("data",entryList);
            outMap.put("data",outList);
            map.put("entry",entryMap);
            map.put("out",outMap);
        }//其他
        //List<VehicleTypeVo> otherList=vehicleMapper.queryEntryOutListOther(flag,outletid);

        y.add(map);
        map0.put("x",x);
        map0.put("y",y);
        return GlobalApiResult.success(map0);
    }


    /**
     * 今日车辆公司进出统计
     * @param currentMonitorId
     * @return
     */
    @Override
    public GlobalApiResult<Object> queryVehicleCompanyToDayInOutCount(Integer currentMonitorId,Integer outletid,int flag) {
        List<VehicleCompanyVO> list=vehicleMapper.queryVehicleCompanyToDayInOutCount(flag,outletid);
        //其他
        List<VehicleCompanyVO> list1=vehicleMapper.queryOtherVehicleCompanyToDayInOutCount(flag,outletid);
        if(!list1.isEmpty()&&list1.get(0).getNumber()>0){
            list.addAll(list1);
        }
        List<String> x=new ArrayList<>();
        List<String> y=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        for(VehicleCompanyVO vehicleCompanyVO:list){
            y.add(vehicleCompanyVO.getCompany());
            x.add(vehicleCompanyVO.getNumber().toString());
        }
        map.put("x",x);
        map.put("y",y);

        return GlobalApiResult.success(map);
    }


    /**
     * 工地进出车辆统计
     * @param currentMonitorId
     * @param starttime
     * @param endtime
     * @return
     */
    @Override
    public GlobalApiResult<Object> queryVehicleInOutCount(Integer currentMonitorId,Integer outletid, String starttime, String endtime) {
        //查询所有车辆类型
        //2019-02-18~2019-02-20
        List<String> x=new ArrayList<>();
        List<Object> y=new ArrayList<>();
        Map<String,Object> map0=new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> entryMap=new HashMap<>();
        entryMap.put("name","进场");
        Map<String,Object> outMap=new HashMap<>();
        outMap.put("name","出场");
        List<String> entryList=new ArrayList<>();
        List<String> outList=new ArrayList<>();
        List<String> datas= DateUtils.findDaysStr(starttime,endtime);
        for(String str:datas){
            List<VehicleCountVO> list=vehicleMapper.queryEveryDayInOutNumber(str,outletid);

            x.add(str);
            if(list.isEmpty()||list==null){
                entryList.add("0");
                outList.add("0");
                entryMap.put("data",entryList);
                outMap.put("data",outList);
                map.put("entry",entryMap);
                map.put("out",outMap);
                continue;
            }
            CountNum(map, entryMap, outMap, entryList, outList, list);

        }
        y.add(map);
        map0.put("x",x);
        map0.put("y",y);
        return GlobalApiResult.success(map0);
    }

    private void CountNum(Map<String, Object> map, Map<String, Object> entryMap, Map<String, Object> outMap, List<String> entryList, List<String> outList, List<VehicleCountVO> list) {
        for(VehicleCountVO vehicleCountVO:list){
            if(vehicleCountVO!=null) {
                if (list.size() == 1) {
                    if (Integer.valueOf(vehicleCountVO.getName()) == 1) {
                        entryList.add("0");
                        outList.add(vehicleCountVO.getValue().toString());
                    }
                    if (Integer.valueOf(vehicleCountVO.getName()) == 0) {
                        entryList.add(vehicleCountVO.getValue().toString());
                        outList.add("0");
                    }
                }
            }
            if(list.size()==2){
                if(vehicleCountVO!=null){
                if(Integer.valueOf(vehicleCountVO.getName())==1){
                    outList.add(vehicleCountVO.getValue().toString());
                }
                if(Integer.valueOf(vehicleCountVO.getName())==0){
                    entryList.add(vehicleCountVO.getValue().toString());
                }
                }
            }
        }
        entryMap.put("data",entryList);
        outMap.put("data",outList);
        map.put("entry",entryMap);
        map.put("out",outMap);
    }

    /**
     * 各公司车辆进出趟数统计
     * @param currentMonitorId
     * @param starttime
     * @param endtime
     * @return
     */
    @Override
    public GlobalApiResult<Object> queryVehicleCompanyInOutCount(Integer currentMonitorId,Integer outletid, String starttime, String endtime) {
      //  结果不精确
        List<VehicleCompanyVO> list=vehicleMapper.queryVehicleCompanyInOutCount(starttime,endtime,outletid);
        //其他
        List<VehicleCompanyVO> list1=vehicleMapper.queryOtherVehicleCompanyInOutCount(starttime,endtime,outletid);
        if(list1.get(0).getNumber()>0){
            list.addAll(list1);
        }
        //改造如下
//        List<VehicleCompanyVO> list=new ArrayList<>();
//       //// 查询公司
//        List<String> companylist=vehicleMapper.queryCompanyList(outletid);
//        for(String str:companylist){
//            List<String> plateNumber;
//            VehicleCompanyVO vehicleCompanyVO=new VehicleCompanyVO();
//            if(str.equals("未登记")){
//                plateNumber=vehicleMapper.queryWDJVehcileByCompany(str);
//                vehicleCompanyVO.setCompany("外来车辆");
//            }else{
//                plateNumber=vehicleMapper.queryVehcileByCompany(str);
//                vehicleCompanyVO.setCompany(str);
//            }
//            Integer num=0;
//            for(String platestr:plateNumber){
//                List<VehicleRecord> vehicleRecord=vehicleRecordMapper.queryVehicleRecordListByPlateNumber(platestr,starttime,endtime);
//
//                for(VehicleRecord record:vehicleRecord){
//                    String type=null;
//                    if(record.getType()==0){
//                        type="1";
//                    }else{
//                        type="0";
//                    }
//                    VehicleRecord recordother=vehicleRecordMapper.queryVehicleRecord(record.getUuid(),type);
//                    if(record!=null && recordother!=null){
//                        num++;
//                    }
//                }
//            }
//            if(num>0){
//                vehicleCompanyVO.setNumber(num);
//                list.add(vehicleCompanyVO);
//            }
//        }



        List<String> x=new ArrayList<>();
        List<String> y=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> data=new HashMap<>();
        for(VehicleCompanyVO vehicleCompanyVO:list){
            y.add(vehicleCompanyVO.getCompany());
            x.add(vehicleCompanyVO.getNumber().toString());
        }
        map.put("y",y);
        map.put("x",x);
        data.put("data",map);
        return GlobalApiResult.success(map);
    }


    /**
     * 大屏
     * @param monitorId
     * @param uuid
     * @param entryoutype
     * @return
     */
    @Override
    public VehicleScreenVO queryVehicleScreen(String monitorId,Integer outletid, String uuid, String entryoutype) {
        VehicleScreenVO vehicleScreenVO=vehicleMapper.queryVehicleScreen(uuid, entryoutype,outletid);
        //根据车牌号码查询车类型
        String type=vehicleMapper.queryVehicleType(vehicleScreenVO.getPlate_number(),outletid);
        if(StringUtils.isBlank(type)){
            vehicleScreenVO.setVehicletype("未知");
        }else{
            vehicleScreenVO.setVehicletype(type);
        }
        Map<String, Object> map = getVehicleMap(outletid);
        vehicleScreenVO.setData(map);
        return vehicleScreenVO;
    }

    /**
     *大屏弹框
     * @param currentMonitorId
     * @return
     */
    @Override
    public GlobalApiResult<Object> queryVehicleScreenMessage(Integer currentMonitorId,Integer outletid,String vehicletype) {

      //  List<VehicleCountVO> list=vehicleMapper.queryVehicleCount(outletid);//
     //   Integer sumnumber=vehicleMapper.querySumnumber(outletid);//总的车辆数
        //查询最近三辆车通行记录
        List<VehicleScreenMessageVO> vehiclelist=null;
        if(StringUtils.isNotBlank(vehicletype)){
            if(vehicletype.equals("未知")){
                vehiclelist=vehicleMapper.queryUnknownVehicleScreenMessage(outletid);
            }else {
                vehiclelist=vehicleMapper.queryVehicleScreenMessageType(outletid,vehicletype);
            }


        }else{
             vehiclelist=vehicleMapper.queryVehicleScreenMessage(outletid);
        }
        for(VehicleScreenMessageVO vehicleScreenMessageVO:vehiclelist){
            //根据车牌号码查询车类型
            String type=vehicleMapper.queryVehicleType(vehicleScreenMessageVO.getPlate_number(),outletid);
            if(StringUtils.isBlank(type)){
                vehicleScreenMessageVO.setVehicletype("未知");
            }else{
                vehicleScreenMessageVO.setVehicletype(type);
            }
        }
        Map<String,Object> map=new HashMap<>();
        //map.put("vehiclesum",sumnumber);
       // map.put("vehicletypelist",list);
        map.put("vehicleimagelist",vehiclelist);
        return GlobalApiResult.success(map);
    }

    @Override
    public VehicleScreenVO queryNewVehicleScreenImage(Integer monitorId, Integer currentOutletId) {
        VehicleScreenVO vehicleScreenVO=vehicleMapper.queryNewVehicleScreenImage(monitorId,currentOutletId);
        if (vehicleScreenVO !=null){
            //根据车牌号码查询车类型
            String type=vehicleMapper.queryVehicleType(vehicleScreenVO.getPlate_number(),currentOutletId);
            if(StringUtils.isBlank(type)){
                vehicleScreenVO.setVehicletype("未知");
            }else{
                vehicleScreenVO.setVehicletype(type);
            }
            Map<String, Object> map = getVehicleMap(currentOutletId);
            vehicleScreenVO.setData(map);
        }
        return vehicleScreenVO;
    }




    @Transactional
    @Override
    public GlobalApiResult<Object> saveScreenVehicleType(Integer currentMonitorId, Integer currentOutletId, String screenvheicle) {
        List<String> list= Arrays.asList(screenvheicle.split(","));
        screenVehicleMapper.deleteAll();
        for(String str:list){
            List<String> list1=screenVehicleMapper.queryScreenVehicleTypeList(currentOutletId,str);
            if(list1.size()>0){
                return GlobalApiResult.failure(str+":该车辆类型已经存在请重新选择");
            }
        }
        screenVehicleMapper.saveScreenVehicleType(currentOutletId,list);
        return  GlobalApiResult.success("保存成功");
    }



    /**
     * 大屏车辆类型列表
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    @Override
    public GlobalApiResult<Object> queryScreenVehicleType(Integer currentMonitorId, Integer currentOutletId) {
        List<String> list=screenVehicleMapper.queryScreenVehicleType(currentOutletId);
        return GlobalApiResult.success(list);
    }


    /**
     * 大屏
     * @param currentMonitorId
     * @param currentOutletId
     * @return
     */
    @Override
    public GlobalApiResult<Object> queryScreenVehicleData(Integer currentMonitorId, Integer currentOutletId) {
        VehicleScreenVO vehicleScreenVO=vehicleMapper.queryNewVehicleScreenImage(currentMonitorId,currentOutletId);
        //根据车牌号码查询车类型
//        String type=vehicleMapper.queryVehicleType(vehicleScreenVO.getPlate_number());
//        if(StringUtils.isBlank(type)){
//            vehicleScreenVO.setVehicletype("未知");
//        }else{
//            vehicleScreenVO.setVehicletype(type);
//        }
        Map<String, Object> map = getVehicleMap(currentOutletId);
        vehicleScreenVO.setData(map);
        return GlobalApiResult.success(vehicleScreenVO);
    }


    /**
     *
     * @param currentOutletId
     * @return
     */
    private Map<String, Object> getVehicleMap(Integer currentOutletId) {
        //查询大屏车辆管理类型前两条车型
        List<String> list = screenVehicleMapper.queryScreenVehicleType(currentOutletId);
        if(list.size()==0||list==null){
            list=vehicleMapper.queryVehicleTypeListTop3(currentOutletId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<ScreenVehicleVO> list1 = new ArrayList<>();
         //查询所有进场的个数
        Integer entrySum = screenVehicleMapper.queryAllVehicleTypeInOutCountToDay(currentOutletId,  0);
        //查询所有出场的个数
        Integer outSum = screenVehicleMapper.queryAllVehicleTypeInOutCountToDay(currentOutletId,  1);
        //查询漏拍的总数量
        Integer leakySum = screenVehicleMapper.queryAllLeakySum(currentOutletId);
        for (String str : list) {
            //统计每种车辆当天进出情况
            Integer entry =0;
            Integer out = 0;
            Integer leaky = 0;
            //未知类型车辆统计
            if(str.equals("未知")){
                //查询未知进场的个数
                 entry = screenVehicleMapper.queryScreenUnknownVehicleTypeInOutCountToDay(currentOutletId, str, 0);
                //查询未知出场的个数
                 out = screenVehicleMapper.queryScreenUnknownVehicleTypeInOutCountToDay(currentOutletId, str, 1);
                //查询未知漏拍的个数
                 leaky = screenVehicleMapper.queryScreenUnknownVehicleTypeLeakySum(currentOutletId, str);
            }else{
                //非未知
                entry = screenVehicleMapper.queryScreenVehicleTypeInOutCountToDay(currentOutletId, str, 0);
                out = screenVehicleMapper.queryScreenVehicleTypeInOutCountToDay(currentOutletId, str, 1);
                leaky = screenVehicleMapper.queryScreenUnknownVehicleLeakySum(currentOutletId, str);
            }
            System.out.println("运输车进：" + entry + "=======:出" + out);
            ScreenVehicleVO screenVehicleVO = new ScreenVehicleVO();
            screenVehicleVO.setVehicletype(str);
            if (entry == null) {
                //如果进场个数等于空，个数设置为0
                screenVehicleVO.setEntryNum(0);
            } else {
                //如果进场个数不等于空，把个数写进去
                screenVehicleVO.setEntryNum(entry);
            }
            if (out == null) {
                //如果出场个数等于空，个数设置为0
                screenVehicleVO.setOutNum(0);
            } else {
                //反之
                screenVehicleVO.setOutNum(out);
            }
            if (leaky == null){
                //如果漏拍数量等于空设置为0
                screenVehicleVO.setLeakyNum(0);
            }else {
                screenVehicleVO.setLeakyNum(leaky);
            }
            //进场个数-出场个数=未知在场个数
            Integer on = screenVehicleVO.getEntryNum() - screenVehicleVO.getOutNum();
            if (on < 0) {
                screenVehicleVO.setOnlineNum(0);
            } else {
                screenVehicleVO.setOnlineNum(on);
            }
//            Integer le = screenVehicleVO.getOnlineNum() + screenVehicleVO.getOutNum() - screenVehicleVO.getEntryNum();
//            if (le < 0) {
//                screenVehicleVO.setOnlineNum(0);
//            } else {
//                screenVehicleVO.setOnlineNum(le);
//            }

//            entrySum = entrySum + screenVehicleVO.getEntryNum();
//            outSum = outSum + screenVehicleVO.getOutNum();
            //添加到list集合中
            list1.add(screenVehicleVO);
        }
        if (entrySum == null) {//如果所有进场个数为空设置为0
            entrySum=0;
        }
        if (outSum == null) {//如果所有出场个数为空设置为0
            outSum=0;
        }
        if (leakySum == null){//如果所有出场个数为空设置为0
            leakySum=0;
        }
        Integer onLineSum = entrySum - outSum;//总在场个数如果小于0，设置为0
        if (onLineSum < 0){
            onLineSum = 0;
        }
      //  Integer leSum = onLineSum + outSum - entrySum;
//        if (onLineSum < 0){
//            onLineSum = 0;
//        }
        map.put("entrySum", entrySum);
        map.put("outSum", outSum);
        map.put("leakySum", leakySum);
        VehicleRecordVo vehicleRecordVo=new VehicleRecordVo();
        map.put("onLineSum", CountOnLineVehicleNum(currentOutletId,vehicleRecordVo));//总在场个数
        System.out.println("==============="+CountOnLineVehicleNum(currentOutletId,vehicleRecordVo));
        map.put("data", list1);
        return map;
    }



    public Integer CountOnLineVehicleNum(Integer outletid, VehicleRecordVo vehicleRecordVo) {
        vehicleRecordVo.setOutletid(outletid);
        List<VehicleRecordVO> list=new ArrayList<>();
        Date data=new Date();
        vehicleRecordVo.setEndtime(DateUtils.getDate2String(data,"yyyy-MM-dd")+" 23:59:59");
        vehicleRecordVo.setStarttime(DateUtils.getDate2String(data,"yyyy-MM-dd")+" 00:00:00");

        Integer sum=0;
        System.out.println(vehicleRecordVo.getCompany());
        list =vehicleRecordMapper.queryVehicleRecordListByIds(vehicleRecordVo);
        list.addAll(vehicleRecordMapper.queryVehicleRecordList(vehicleRecordVo));
//        list.addAll(vehicleRecordMapper.queryVehicleRecordList(vehicleRecordVo));
        List<String> list1=new ArrayList<>();
        for(VehicleRecordVO vehicleRecordVO:list){
            Integer entryouttype;
            if(vehicleRecordVO.getEntryouttype()==0){
                entryouttype=1;
            }else{
                entryouttype=0;
            }
            //通过uuid与进出类型查询另一条数据
            VehicleRecord vehicleRecord=vehicleRecordMapper.queryConditionList(vehicleRecordVO.getUuid(),entryouttype,outletid);
            //进数据组装
            VehicleRecordReVO respvo=new VehicleRecordReVO();
            respvo.setVehicleType(vehicleRecordVO.getVehicletype());
            respvo.setCompany(vehicleRecordVO.getCompany());
            respvo.setPlate_number(vehicleRecordVO.getPlate_number());

            //判断车辆状态--
            if(vehicleRecordVO.getEntryouttype()==1&&vehicleRecord.getType()==1){
                respvo.setVehicleStatus("异常");
            }else if(vehicleRecordVO.getEntryouttype()==0&&vehicleRecord.getType()==0){
                respvo.setVehicleStatus("异常");
            }else if(vehicleRecordVO.getEntryouttype()==0&&vehicleRecord.getType()==1&& vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getHead_imagetime()!=null&&vehicleRecord.getId()>vehicleRecordVO.getId()){
                respvo.setVehicleStatus("离场");
            }else if(vehicleRecordVO.getEntryouttype()==0 &&vehicleRecord.getType()==1&& vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getHead_imagetime()!=null&&vehicleRecordVO.getId()<vehicleRecord.getId()){
                respvo.setVehicleStatus("场内");
            }else if(vehicleRecordVO.getEntryouttype()==0&& vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getHead_imagetime()!=null&&vehicleRecord.getId()<vehicleRecordVO.getId()){
                respvo.setVehicleStatus("离场");
            }else if(vehicleRecordVO.getEntryouttype()==1&& vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getHead_imagetime()!=null&&vehicleRecordVO.getId()<vehicleRecord.getId()){
                respvo.setVehicleStatus("场内");
            }else if(vehicleRecord.getType()==0&&vehicleRecordVO.getEntryouttype()==1 && vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getId()<vehicleRecordVO.getId()){
                respvo.setVehicleStatus("离场");
            }else if(vehicleRecord.getType()==0&&vehicleRecordVO.getEntryouttype()==1 && vehicleRecordVO.getHead_imagetime()!=null && vehicleRecord.getHead_imagetime()==null &&vehicleRecord.getId()>vehicleRecordVO.getId()){
                respvo.setVehicleStatus("异常");
            }else if(vehicleRecord.getType()==1&&vehicleRecordVO.getEntryouttype()==0 && vehicleRecord.getHead_imagetime()!=null &&vehicleRecord.getId()<vehicleRecordVO.getId()){
                respvo.setVehicleStatus("场内");
            }else if(vehicleRecord.getType()==1&&vehicleRecordVO.getEntryouttype()==0 && vehicleRecord.getHead_imagetime()!=null &&vehicleRecord.getId()>vehicleRecordVO.getId()){
                respvo.setVehicleStatus("异常");
            }else if(vehicleRecord.getType()==0&&vehicleRecordVO.getEntryouttype()==1 && vehicleRecordVO.getHead_imagetime()==null &&vehicleRecord.getId()<vehicleRecordVO.getId()){
                respvo.setVehicleStatus("离场");
            }else if(vehicleRecord.getType()==0&&vehicleRecordVO.getEntryouttype()==1 && vehicleRecordVO.getHead_imagetime()==null &&vehicleRecord.getId()>vehicleRecordVO.getId()){
                respvo.setVehicleStatus("场内");
            }else if(vehicleRecord.getType()==1&&vehicleRecordVO.getEntryouttype()==0 && vehicleRecord.getHead_imagetime()==null &&vehicleRecord.getId()<vehicleRecordVO.getId()){
                respvo.setVehicleStatus("离场");
            }else if(vehicleRecord.getType()==1&&vehicleRecordVO.getEntryouttype()==0 && vehicleRecord.getHead_imagetime()==null &&vehicleRecord.getId()>vehicleRecordVO.getId()){
                respvo.setVehicleStatus("场内");
            }

            if(vehicleRecordVO.getPatch().equals("1")||vehicleRecord.getPatch().equals("1")){
                respvo.setVehicleStatus("离场");
            }
            if(respvo.getVehicleStatus().equals("场内")){
                sum=sum+1;
            }
            list1.add(respvo.getPlate_number()+":"+respvo.getVehicleStatus());
        }
        System.out.println(JSON.toJSONString(list1));
        return sum;

    }


}
