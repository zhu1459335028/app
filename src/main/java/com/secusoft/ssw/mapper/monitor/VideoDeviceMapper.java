package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Videodevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VideoDeviceMapper {

	@Select("SELECT d.* FROM VideoDevice d " +
			"inner join videooutlet o on o.deviceno=d.deviceno " +
			"where o.outletid=#{outletid}")
	List<Videodevice> selectVideoDeviceByOutletId(@Param("outletid") int outletid);

}
