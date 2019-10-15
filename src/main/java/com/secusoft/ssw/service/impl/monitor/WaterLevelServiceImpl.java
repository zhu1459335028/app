package com.secusoft.ssw.service.impl.monitor;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.ThresholdMapper;
import com.secusoft.ssw.mapper.monitor.WaterInstruMentMapper;
import com.secusoft.ssw.mapper.monitor.WaterLevelMapper;
import com.secusoft.ssw.mapper.monitor.WaterMonitorMapper;
import com.secusoft.ssw.model.monitor.Threshold;
import com.secusoft.ssw.model.monitor.WaterInstruMent;
import com.secusoft.ssw.model.monitor.WaterLevel;
import com.secusoft.ssw.model.monitor.WaterMonitor;
import com.secusoft.ssw.model.viewobject.Request.QueryWaterVO;
import com.secusoft.ssw.model.viewobject.Request.WaterEquipmentVO;
import com.secusoft.ssw.model.viewobject.Request.WaterLevelVO;
import com.secusoft.ssw.model.viewobject.Request.WaterScreenVo;
import com.secusoft.ssw.service.WaterLevelService;
import com.secusoft.ssw.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class WaterLevelServiceImpl implements WaterLevelService {

    @Autowired
    WaterLevelMapper waterLevelMapper;

    @Autowired
    WaterInstruMentMapper waterInstruMentMapper;

    @Autowired
    WaterMonitorMapper waterMonitorMapper;

    @Autowired
    private ThresholdMapper thresholdMapper;


    @Transactional
    @Override
    public void save(int monitorId, int outletId,WaterScreenVo waterScreenVo) {


        WaterLevel waterLevel=new WaterLevel();
        waterLevel.setDeviceid(waterScreenVo.getDevice_id());
        if(Double.parseDouble(waterScreenVo.getWater_level())>0){
            waterLevel.setWatervalue(Double.parseDouble(waterScreenVo.getWater_level())*-1+"");
        }        else{
            waterLevel.setWatervalue(waterScreenVo.getWater_level());
        }

        waterLevel.setMonitortime(DateUtils.parseDate(waterScreenVo.getTime(),"yyyy-MM-dd HH:mm:ss"));
        waterLevel.setOutletid(outletId);
        waterLevelMapper.save(waterLevel);
       //根据当前设备id查询当前设备是否已存在
        Integer id=waterLevelMapper.queryInstruMent(waterScreenVo.getDevice_id(),outletId);
        if(id==null){
            //查询最大水位测量点id
            Integer mid=waterMonitorMapper.queryMaxid(outletId);
            WaterMonitor waterMonitor=new WaterMonitor();
            if(mid==null){
                waterMonitor.setAddress("测量点0");
            }else{
                mid=mid+1;
                waterMonitor.setAddress("测量点"+mid);
            }
            waterMonitor.setLongitude("xxx");
            waterMonitor.setLatitude("xxx");
            waterMonitor.setOutletid(outletId);
            waterMonitorMapper.save(waterMonitor);

            WaterInstruMent waterInstruMent=new WaterInstruMent();
            waterInstruMent.setDeviceid(waterScreenVo.getDevice_id());
            waterInstruMent.setDevicename("水位"+waterScreenVo.getDevice_id());
            waterInstruMent.setAdduser("系统自动添加");
            waterInstruMent.setMiddlevalue("0");
            waterInstruMent.setPoorvalue("0");
            waterInstruMent.setWmid(waterMonitorMapper.queryMaxid(outletId));
            waterInstruMent.setOutletid(outletId);
            waterInstruMentMapper.save(waterInstruMent);
        }


    }

    @Override
    public GlobalApiResult<Object> queryWaterList(Integer currentMonitorId, Integer currentOutletId, QueryWaterVO queryWaterVO) {
        PageHelper.startPage(queryWaterVO.getCurrentPage(),queryWaterVO.getPageSize());
        return null;
    }


    /**
     * 查询大屏图表默认最近7条
     * @param currentMonitorId
     * @param currentOutletId
     * @param queryWaterVO
     * @return
     */
    @Override
    public GlobalApiResult<Object> queryWaterChart(Integer currentMonitorId, Integer currentOutletId, QueryWaterVO queryWaterVO) {

        //查询当前所有可用设备id
        List<WaterEquipmentVO> equipment=waterInstruMentMapper.queryEquipmentID(currentOutletId);
        List<Object> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        String depth=waterMonitorMapper.queryDepth(currentOutletId);
        for(WaterEquipmentVO waterEquipmentVO:equipment){
            List<Object> list1=new ArrayList<>();
           //根据id查询最新的7条记录及测量时间
            List<WaterLevelVO> waterLevelVOS=waterLevelMapper.queryNewWaterLevel(waterEquipmentVO.getDeviceid(),currentOutletId);

            System.out.println(JSON.toJSONString(waterLevelVOS));
            System.out.println(waterLevelVOS.size());
            String start="";
            String end="";
            if(!waterLevelVOS.isEmpty()&&waterLevelVOS.size()>0){
                 start =waterLevelVOS.get(0).getMonitortime();
                 end=waterLevelVOS.get(waterLevelVOS.size()-1).getMonitortime();
            }
            System.out.println("开始时间："+ start +  end);
            //根据第一条时间及最后一条时间查询 阈值
            Map<String,BigDecimal> thresholdmapstart=new HashMap<>();
            List<Threshold> thresholdstarts=thresholdMapper.queryWaterThresholdLists(currentOutletId,start);
            for(Threshold threshold:thresholdstarts){
                List<BigDecimal> startslist=new ArrayList<>();
                if(StringUtils.isNotBlank(threshold.getMax_val())){
                    lists(threshold.getMax_val(),startslist);
                }
                thresholdmapstart.put(threshold.getId()+"",new BigDecimal(Collections.min(startslist)+""));
                startslist.clear();
            }

            Map<String,BigDecimal> thresholdmapstartend=new HashMap<>();

            List<Threshold> thresholdends=thresholdMapper.queryWaterThresholdLists(currentOutletId,end);
            for(Threshold threshold:thresholdends){
                List<BigDecimal> endlist=new ArrayList<>();
                if(StringUtils.isNotBlank(threshold.getMax_val())){
                    lists(threshold.getMax_val(),endlist);
                }
                thresholdmapstartend.put(threshold.getId()+"",new BigDecimal(Collections.min(endlist)+""));
            }

            List<Map.Entry<String, BigDecimal>> thresholdmapstartlist = new ArrayList<Map.Entry<String, BigDecimal>>();
            thresholdmapstartlist.addAll(thresholdmapstart.entrySet());
            ListMapSort(thresholdmapstartlist);

            List<Map.Entry<String, BigDecimal>> thresholdmapstartendlist = new ArrayList<Map.Entry<String, BigDecimal>>();
            thresholdmapstartendlist.addAll(thresholdmapstartend.entrySet());
            ListMapSort(thresholdmapstartendlist);
            //获取最小的key的id  //获取最小的id查询最小阈值
            Threshold thresholdstart=null;
            if(thresholdmapstartlist.size()>0){
                System.out.println("key"+thresholdmapstartlist.get(0).getValue()+"-> value"+thresholdmapstartlist.get(0).getKey());
                 thresholdstart=thresholdMapper.selectThresholdById(currentOutletId,Integer.valueOf(thresholdmapstartlist.get(0).getKey()));
            }

            Threshold thresholdend=null;
            if(thresholdmapstartendlist.size()>0){
                System.out.println("key"+thresholdmapstartendlist.get(0).getValue()+"-> value"+thresholdmapstartendlist.get(0).getKey());
                thresholdend=thresholdMapper.selectThresholdById(currentOutletId,Integer.valueOf(thresholdmapstartendlist.get(0).getKey()));
            }

            //组装数据
            if(thresholdstart!=null&&thresholdend!=null&&(thresholdstart.getId().equals(thresholdend.getId()))){
                if(StringUtils.isNotBlank(thresholdend.getMax_val())){
                    strtoMap(waterLevelVOS,thresholdend.getMax_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMax_val(),list1));
                }
                if(StringUtils.isNotBlank(thresholdend.getMin_val())){
                    strtoMap(waterLevelVOS,thresholdend.getMin_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMin_val()));
                }
                if(StringUtils.isNotBlank(thresholdend.getIdentical_val())){
                    strtoMap(waterLevelVOS,thresholdend.getIdentical_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getIdentical_val()));
                }
            }else if(thresholdstart!=null&&thresholdend!=null&&(!thresholdstart.getId().equals(thresholdend.getId()))){
                if(StringUtils.isNotBlank(thresholdstart.getMax_val())){
                    strtoMap(waterLevelVOS,thresholdstart.getMax_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMax_val(),list1));
                }
                if(StringUtils.isNotBlank(thresholdstart.getMin_val())){
                    strtoMap(waterLevelVOS,thresholdstart.getMin_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMin_val()));
                }
                if(StringUtils.isNotBlank(thresholdstart.getIdentical_val())){
                    strtoMap(waterLevelVOS,thresholdstart.getIdentical_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getIdentical_val()));
                }

                if(StringUtils.isNotBlank(thresholdend.getMax_val())){
                    strtoMap(waterLevelVOS,thresholdend.getMax_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMax_val(),list1));
                }
                if(StringUtils.isNotBlank(thresholdend.getMin_val())){
                    strtoMap(waterLevelVOS,thresholdend.getMin_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMin_val()));
                }
                if(StringUtils.isNotBlank(thresholdend.getIdentical_val())){
                    strtoMap(waterLevelVOS,thresholdend.getIdentical_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getIdentical_val()));
                }
            }else if(thresholdstart!=null && thresholdend==null){
                if(StringUtils.isNotBlank(thresholdstart.getMax_val())){
                    strtoMap(waterLevelVOS,thresholdstart.getMax_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMax_val(),list1));
                }
                if(StringUtils.isNotBlank(thresholdstart.getMin_val())){
                    strtoMap(waterLevelVOS,thresholdstart.getMin_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMin_val()));
                }
                if(StringUtils.isNotBlank(thresholdstart.getIdentical_val())){
                    strtoMap(waterLevelVOS,thresholdstart.getIdentical_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getIdentical_val()));
                }
            }else if(thresholdend!=null && thresholdstart==null){
                if(StringUtils.isNotBlank(thresholdend.getMax_val())){
                    strtoMap(waterLevelVOS,thresholdend.getMax_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMax_val(),list1));
                }
                if(StringUtils.isNotBlank(thresholdend.getMin_val())){
                    strtoMap(waterLevelVOS,thresholdend.getMin_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getMin_val()));
                }
                if(StringUtils.isNotBlank(thresholdend.getIdentical_val())){
                    strtoMap(waterLevelVOS,thresholdend.getIdentical_val(),list1);
                    //list1.add(strtoMap(waterLevelVOS,thresholdend.getIdentical_val()));
                }
            }


            Map<String,Object> waterlevelmap=new HashMap<>();
            waterlevelmap.put("name",waterEquipmentVO.getDevicename());
           // waterlevelmap.put("name","");
            waterlevelmap.put("type","line");
            List<Object> datalist=new ArrayList<>();
            for(WaterLevelVO waterLevelVO:waterLevelVOS){
                List<Object> list2=new ArrayList<>();
                list2.add(waterLevelVO.getMonitortime());
                //Threshold threshold=thresholdMapper.queryWaterThreshold(currentOutletId,waterLevelVO.getMonitortime());
                if(StringUtils.isNotBlank(depth)){
                    //List<Integer> list3=strtoList(threshold.getMax_val());
                    BigDecimal d   =   new   BigDecimal(waterLevelVO.getWatervalue());
                    BigDecimal d1   =   new   BigDecimal(depth);
                    BigDecimal sum=d1.subtract(d);
                    if(d.compareTo(BigDecimal.ZERO)==0){
                        sum=d1;
                    }
                    list2.add(sum);
                }else{
                    list2.add(waterLevelVO.getWatervalue());
                }
                datalist.add(list2);
            }
            waterlevelmap.put("data",datalist);
            list1.add(waterlevelmap);
            map.put(waterEquipmentVO.getDevicename(),list1);
        }
      //  map.put("depth",depth);
        return GlobalApiResult.success(map);
    }



    private List<Object> strtoMap(List<WaterLevelVO> list,String str,List<Object> list0){
        List<Object> list2=new ArrayList<>();
        Map maps = (Map) JSON.parse(str);
        for (Object map : maps.entrySet()){
            Map<String,Object>  strmap=new HashMap<>();
            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
            //strmap.put("name",((Map.Entry)map).getKey().toString());
            strmap.put("name","");
            List<Object> list1=assemblyThreshold(list,((Map.Entry)map).getKey().toString());
            strmap.put("data",list1);
            strmap.put("type","line");
            strmap.put("color",((Map.Entry)map).getValue().toString());
            list0.add(strmap);
        }

        return list0;
    }

    private List<Object> assemblyThreshold(List<WaterLevelVO> list,String str){
        List<Object> datalist=new ArrayList<>();
        for(WaterLevelVO waterLevelVO:list){
            Map<String,Object>  map=new HashMap<>();
            List<Object> strings=new ArrayList<>();
//            List<String> str=new ArrayList<>();
//            map.put(waterLevelVO.getMonitortime(),Integer.valueOf(str));
            strings.add(waterLevelVO.getMonitortime());
            strings.add(Integer.valueOf(str));
            datalist.add(strings);
        }
        return datalist;
    }


    @Override
    public Map<String,Object> queryWaterScreen(Integer currentMonitorId, Integer currentOutletId) {
        //查询当前所有可用设备
        List<WaterEquipmentVO> equipment=waterInstruMentMapper.queryEquipmentID(currentOutletId);
        //根据设备查询最新的数据
        List<String> x=new ArrayList<>();
        List<String> y=new ArrayList<>();

        List<Integer> maxs=new ArrayList<>();
        List<Integer> mins=new ArrayList<>();
        List<Integer> identicals=new ArrayList<>();
        List<String> z=new ArrayList<>();//1报警  0不报警
        String depth=waterMonitorMapper.queryDepth(currentOutletId);
        for(WaterEquipmentVO waterEquipmentVO:equipment){
            //根据设备ID查询最新的值==处理预警值
            List<String> list=new ArrayList<>();
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

            Double d=Double.parseDouble(waterLevelVO.getWatervalue());
            Integer wvalue= d.intValue();
            BigDecimal aa   =   new   BigDecimal(depth);
            BigDecimal bb  =   new   BigDecimal(waterLevelVO.getWatervalue());
            BigDecimal sum=aa.subtract(bb);
            if(bb.compareTo(BigDecimal.ZERO)==0){
                sum=aa;
            }

            if(StringUtils.isNotBlank(depth)){
                Integer depth1=Integer.valueOf(depth);
                if(thresholdend==null){
                    y.add(sum+"");
                    maxs.add(0);
                    continue;
                }
            if(StringUtils.isNotBlank(thresholdend.getMax_val())){
                List<Integer> list1=strtoList(thresholdend.getMax_val());
                Integer max=Collections.min(list1);
                BigDecimal min=Collections.min(listthres);
               // BigDecimal cc  =   new   BigDecimal(String.valueOf(min));
                //Integer sum=depth1-wvalue;
                if(aa.compareTo(BigDecimal.ZERO)<0 && bb.compareTo(BigDecimal.ZERO)<=0){
                    if(sum.compareTo(min)>0){
                        maxs.add(1);
                    }else{
                        maxs.add(0);
                    }
                    y.add(sum+"");
                }
            }else{
                y.add(waterLevelVO.getWatervalue());
            }
//            if(StringUtils.isNotBlank(thresholdend.getMin_val())){
//                List<Integer> list2=strtoList(thresholdend.getMax_val());
//                Integer max=Collections.min(list2);
//                Integer min=Collections.min(list2);
//                Integer sum=depth-wvalue;
//                if(depth<0 && wvalue<0){
//                    if(sum>min){
//                        mins.add(1);
//                    }else{
//                        mins.add(0);
//                    }
//                }
//                if(Integer.valueOf(waterLevelVO.getWatervalue())<min){
//                    mins.add(1);
//                }else{
//                    mins.add(0);
//                }

//            if(StringUtils.isNotBlank(thresholdend.getIdentical_val())){
//                List<Integer> list3=strtoList(thresholdend.getMax_val());
//                Integer iden=Collections.min(list3);
//                Integer sum=depth-wvalue;
//                if(depth<0 && wvalue<0){
//                    if(sum>iden){
//                        identicals.add(1);
//                    }else{
//                        identicals.add(0);
//                    }
//                }
//            }
          //  z.add()
        }else{
                maxs.add(0);
                mins.add(0);
                identicals.add(0);
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("x",x);
        map.put("y",y);
        map.put("maxs",maxs);
        map.put("mins",mins);
        map.put("identicals",identicals);
        map.put("depth",depth);
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


}
