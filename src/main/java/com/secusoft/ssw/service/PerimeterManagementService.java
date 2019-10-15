package com.secusoft.ssw.service;

import java.util.List;
import java.util.Map;

public interface PerimeterManagementService {
    List<Map<String, Object>> getCameraNoAndImageUrlByOid(Integer valueOf, Integer valueOf1);

    Map<String, Object> getPerimeterPicturesById(int monitorId,int outletId, int id);
}
