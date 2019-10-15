package com.secusoft.ssw.mapper.monitor;


import com.secusoft.ssw.model.monitor.Foundaccessdevice;
import com.secusoft.ssw.model.monitor.Foundation;
import com.secusoft.ssw.model.monitor.Foundwatermonitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 基坑信息表
 */
@Mapper
public interface FoundationMapper {


    @Select("select * from foundation where outletid=#{outletid} ")
    List<Foundation> queryFoundationAll(@Param("outletid") Integer outletid );


    @Select("select f.*,d.type from foundaccessdevice  f LEFT join  accessdevice d on f.accessdeviceid=d.id where f.foundid=#{foundid} ")
    List<Foundaccessdevice> queryFoundaccessdeviceByFoundid(@Param("foundid") Integer foundid);

    @Select("select * from foundwatermonitor where foundid=#{foundid} and outletid=#{outletid} ")
    List<Foundwatermonitor> queryFoundwatermonitorByFoundid(@Param("foundid") Integer foundid,@Param("outletid") Integer outletid );

}
