package com.secusoft.ssw.service.impl.monitor;

import com.alibaba.fastjson.JSONObject;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.ThresholdMapper;
import com.secusoft.ssw.model.monitor.Threshold;
import com.secusoft.ssw.model.monitor.Thresholdvertocal;
import com.secusoft.ssw.model.viewobject.Response.ThresholdRespVO;
import com.secusoft.ssw.service.ThresholdService;

import com.secusoft.ssw.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

@Service
public class ThresholdServiceImpl implements ThresholdService {

    @Autowired
    private ThresholdMapper thresholdMapper;

    @Override
    public Threshold getThreshold(int monitorId, int outletId, Integer id) {
        return thresholdMapper.selectThresholdById(outletId, id);
    }

    @Override
    public Threshold getThreshold(int monitorId, int outletId, String type) {
        return thresholdMapper.selectThresholdByType(outletId, type);
    }

    @Override
    public void updateThreshold(int monitorId, int outletId, Threshold threshold) {
        threshold.setOutletid(outletId);
        Threshold oldThreshold = thresholdMapper.selectThresholdByType(outletId, threshold.getType());
        if(null==oldThreshold){
            thresholdMapper.insertThreshold(threshold);
        }else{
            thresholdMapper.updateThreshold(threshold);
        }
    }


    @Override
    public GlobalApiResult<Object> SaveOrUpdateThreshold(Integer currentMonitorId, Integer currentOutletId, List<Threshold> list) {
        String msg="";
        System.out.println(JSONObject.toJSONString(list));
        thresholdMapper.delete("water_level",currentOutletId);
        for(Threshold threshold:list){
            if(threshold.getId()!=null){
                threshold.setId(null);
            }
            threshold.setOutletid(currentOutletId);
            if(StringUtils.isNotBlank(threshold.getStarttimestr())){
                threshold.setStarttime(DateUtils.parseDate(threshold.getStarttimestr(),"yyyy-MM-dd HH:mm:ss"));
            }
            if(StringUtils.isNotBlank(threshold.getEndtimestr())){
                threshold.setEndtime(DateUtils.parseDate(threshold.getEndtimestr(),"yyyy-MM-dd HH:mm:ss"));
            }
            threshold.setType("water_level");
            if(threshold.getId()!=null){//更新

                thresholdMapper.updateWaterThreshold(threshold);
                msg="更新成功";
            }else{//
                //
                //查询最大水位子类型
                Integer subtype=thresholdMapper.queryMaxSubType(currentOutletId);
                if(subtype ==null){
                    threshold.setTypesub(0);
                }else {
                    threshold.setTypesub(subtype+1);
                }
                thresholdMapper.insertWaterThreshold(threshold);
                msg="添加成功";
            }
        }
        return GlobalApiResult.success(msg);
    }



    @Override
    public GlobalApiResult<Object> queryThreshold(Integer currentMonitorId, Integer currentOutletId, Threshold threshold) {
        List<ThresholdRespVO> list=thresholdMapper.queryWaterThresholdList(currentOutletId);
        return GlobalApiResult.success(list);
    }

    @Override
    public GlobalApiResult<Object> deleteThreshold(Integer currentMonitorId, Integer currentOutletId, List<String> list) {
        thresholdMapper.deleteThreshold(list,currentOutletId);
        return GlobalApiResult.success("删除成功");
    }

    @Override
    public Threshold queryLevelThreshold(int monitorId, Integer outletid, String datetime) {
        return thresholdMapper.queryLevelThreshold(outletid,datetime);
    }

    @Override
    public Threshold queryVerticalThreshold(int monitorId, Integer outletid, String datetime) {
        return thresholdMapper.queryVerticalThreshold(outletid,datetime);
    }

    @Override
    public GlobalApiResult<Object> SaveOrUpdateLevel(Integer currentMonitorId, Integer currentOutletId, Threshold threshold) {
        String msg="";

            threshold.setOutletid(currentOutletId);
            if(StringUtils.isNotBlank(threshold.getStarttimestr())){
                threshold.setStarttime(DateUtils.parseDate(threshold.getStarttimestr(),"yyyy-MM-dd HH:mm:ss"));
            }
            if(StringUtils.isNotBlank(threshold.getEndtimestr())){
                threshold.setEndtime(DateUtils.parseDate(threshold.getEndtimestr(),"yyyy-MM-dd HH:mm:ss"));
            }
            if(threshold.getId()!=null){//更新

                thresholdMapper.updateWaterThreshold(threshold);
                msg="更新成功";
            }else{//
                Threshold ts = thresholdMapper.selectThresholdByType(currentOutletId, threshold.getType());
                if (ts != null){
                    return GlobalApiResult.failure("已有该报警值，不能重复添加！");
                }
                if(StringUtils.isBlank(threshold.getStarttimestr())&&StringUtils.isBlank(threshold.getEndtimestr())){
                   threshold.setStarttimestr(DateUtils.parseDateStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    threshold.setEndtimestr(DateUtils.parseDateStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    System.out.println(threshold.getStarttimestr());
                    thresholdMapper.insertWaterThreshold(threshold);
                }else {
                    thresholdMapper.insertWaterThreshold(threshold);
                }

                msg="添加成功";
            }

        return GlobalApiResult.success(msg);
    }

    @Override
    public Thresholdvertocal saveLevelAndVertical(int monitorId,String avgDaily_db47_52, String avgDaily_db47_56,Integer outletid) {
        thresholdMapper.saveLevelAndVertical(avgDaily_db47_52,avgDaily_db47_56,outletid);
        Thresholdvertocal thresholdvertocal = thresholdMapper.queryLevelAndVertical(outletid);
        return  thresholdvertocal;
    }

    @Override
    public Thresholdvertocal queryLevelAndVertical(int monitorId,Integer outletid) {
        Thresholdvertocal thresholdvertocal = thresholdMapper.queryLevelAndVertical(outletid);
        return thresholdvertocal;
    }

    @Override
    public GlobalApiResult<Object> deleteThresholdLevel(Integer currentMonitorId, Integer currentOutletId, Integer id) {
        if (id == null){
            return GlobalApiResult.failure("id不能为空");
        }
        thresholdMapper.deleteThresholdLevel(currentOutletId,id);
        return GlobalApiResult.success("删除成功");
    }
}