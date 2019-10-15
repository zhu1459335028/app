package com.secusoft.ssw.mapper.global;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MonitorMapper {

    @Select("SELECT MonitorId FROM monitor where ShowName=#{ShowName}")
    Integer selectMonitorIdByShowName(@Param("ShowName") String ShowName);

}
