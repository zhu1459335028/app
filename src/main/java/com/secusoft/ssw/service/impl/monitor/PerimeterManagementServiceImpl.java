package com.secusoft.ssw.service.impl.monitor;

import com.secusoft.ssw.mapper.monitor.PerimeterManagementMapper;
import com.secusoft.ssw.service.PerimeterManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PerimeterManagementServiceImpl implements PerimeterManagementService {
    @Autowired
    private PerimeterManagementMapper perimeterManagementMapper;
    @Value("${ssm.base.url}")
    private String ssmBaseUrl;

    @Override
    public List<Map<String, Object>> getCameraNoAndImageUrlByOid(Integer monitorId, Integer outletId) {
        List<Map<String, Object>> mapList = perimeterManagementMapper.getCameraNoAndImageUrlByOid(outletId);
        for (Map<String,Object> map : mapList) {
            map.put("imageUrl",ssmBaseUrl+map.get("imageUrl"));
        }
        return mapList;
    }

    @Override
    public Map<String, Object> getPerimeterPicturesById(int monitorId,int outletId, int id) {
        Map<String, Object> map = perimeterManagementMapper.getPerimeterPicturesById(id,outletId);
        map.put("imageUrl",ssmBaseUrl+map.get("imageUrl"));
        return map;
    }
}
