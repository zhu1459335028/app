package com.secusoft.ssw.service;

import java.util.Map;

public interface ProgressstatisticsService {

    void saveProgressstatistics(int i, Integer outletid, String hpjsq,String shileldname);

    Map<String,Object> queryatestWeek(int i, Integer outletid, String shieldname,Map<String,Object> outputmap);
}
