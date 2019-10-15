package com.secusoft.ssw.mapper.monitor;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccessDeviceMapper {

    @Select("select type from AccessDevice where outletid=#{outletid} and accessdeviceid=#{accessdeviceid}")
    Integer selectAccessDeviceTypeByAccessDeviceId(@Param("outletid") int outletid,@Param("accessdeviceid") String accessdeviceid);

}
