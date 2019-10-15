package com.secusoft.ssw.service;

import com.github.pagehelper.PageInfo;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.viewobject.Request.EnvironmentalVo;
import com.secusoft.ssw.model.viewobject.Request.FoundationVo;
import com.secusoft.ssw.model.viewobject.Request.GasVo;
import com.secusoft.ssw.model.viewobject.Request.Iocontroller;
import com.secusoft.ssw.model.viewobject.Response.IocontrollerVO;

import java.util.List;

public interface GasService{


    PageInfo<EnvironmentalVo> getEnvironmentalList(Integer currentMonitorId, Integer currentOutletId, int currentPage, int pageSize, List<Integer> types);

    String saveEnvironmental(Integer currentMonitorId, Integer currentOutletId, EnvironmentalVo gasVo);

    void deleteGas(Integer currentMonitorId,Integer currentOutletId, List<Integer> id);

    void updateEnvironmental(Integer currentMonitorId,Integer currentOutletId, EnvironmentalVo gas);

    PageInfo<FoundationVo> getFoundationList(Integer currentMonitorId, Integer currentOutletId, int currentPage, int pageSize, List<Integer> types);

    String saveFoundationVo(Integer currentMonitorId, Integer currentOutletId, FoundationVo foundationVo);

    void updateFoundationVo(Integer currentMonitorId, Integer currentOutletId, FoundationVo foundationVo);

    void syncGasVo(int i,int outletid, GasVo gasVo);

    Object saveGasIoInFO(Integer currentMonitorId, Integer currentOutletId, Iocontroller iocontroller);

    Object selectAllIoInfo(Integer currentMonitorId, Integer currentOutletId);


    Object selectOneIoInfo(Integer currentMonitorId, Integer currentOutletId, Integer id);


    Object deleteOneIoInfo(Integer currentMonitorId, Integer currentOutletId, Integer id);


}