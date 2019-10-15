package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.AccessDeviceEmap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccessDeviceEmapMapper {

    @Select("select a.accessdeviceid,a.emapid,a.coordinate,a.remarks,d.type as direction from AccessDeviceEmap a " +
            "inner join AccessDevice d on d.id=a.accessdeviceid " +
            "where a.emapid=#{emapid} and a.outletid =#{outletid}")
    List<AccessDeviceEmap> selectByEmapId(@Param("outletid") int outletId,@Param("emapid") int emapid);

}
