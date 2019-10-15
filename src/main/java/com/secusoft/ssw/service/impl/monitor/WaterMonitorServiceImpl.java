package com.secusoft.ssw.service.impl.monitor;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.WaterMonitorMapper;
import com.secusoft.ssw.model.monitor.WaterMonitor;
import com.secusoft.ssw.service.WaterMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterMonitorServiceImpl implements WaterMonitorService {


    @Autowired
    WaterMonitorMapper waterMonitorMapper;


    @Override
    public GlobalApiResult<Object> saveWaterMonitor(Integer currentMonitorId, Integer currentOutletId, String depth) {
        WaterMonitor waterMonitor=new WaterMonitor();
        waterMonitor.setLongitude("depth");
        waterMonitor.setOutletid(currentOutletId);
        waterMonitor.setAddress("depth");
        waterMonitor.setLatitude("depth");
        if(Double.parseDouble(depth)>0){
            waterMonitor.setDepth(Double.parseDouble(depth)*-1+"");
        }else{
            waterMonitor.setDepth(depth);
        }
        waterMonitorMapper.delete(currentOutletId);
        waterMonitorMapper.save(waterMonitor);
        return GlobalApiResult.success("添加成功");
    }


    @Override
    public GlobalApiResult<Object> queryWaterDepth(Integer currentMonitorId, Integer currentOutletId) {
        String depth=waterMonitorMapper.queryWaterDepth(currentOutletId);
        return GlobalApiResult.success(depth);
    }
}
