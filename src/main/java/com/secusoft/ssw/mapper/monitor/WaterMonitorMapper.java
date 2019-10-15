package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Settlement;
import com.secusoft.ssw.model.monitor.WaterMonitor;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * 监测点
 */
@Mapper
public interface WaterMonitorMapper {

    @Select("select max(id) from watermonitor where outletid=#{outletId}")
    Integer queryMaxid(@Param("outletId")int outletId);


    @Insert("<script>INSERT INTO watermonitor(address, longitude, latitude,outletid,depth) VALUES (#{waterMonitor.address}, #{waterMonitor.longitude}, #{waterMonitor.latitude},#{waterMonitor.outletid},#{waterMonitor.depth}) </script>")
    Integer save(@Param("waterMonitor")WaterMonitor waterMonitor);


    @Select("select depth from watermonitor where address='depth' and outletid=#{outletId} and  depth is not null")
    String queryDepth(@Param("outletId")int outletId);

    @Select("select depth from watermonitor where outletid=#{currentOutletId} and  depth is not null limit 1")
    String queryWaterDepth(@Param("currentOutletId") Integer currentOutletId);


    @Delete("delete from watermonitor where address='depth' and outletid=#{outletId} and  depth is not null ")
    void delete(@Param("outletId")int outletId);
}
