package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.viewobject.Response.CameraEmapInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CameraEmapMapper {

	@Select("SELECT ce.*,c.chanindex,v.ipaddress,v.tcpport,v.username,v.passwd,v.devicetype FROM CameraEmap ce " +
			"inner join camera c on c.camerano = ce.cameraid " +
			"inner join videodevice v on v.deviceno = c.deviceno " +
			"where ce.emapid=#{emapid} and ce.outletid =#{outletid}")
	List<CameraEmapInfoVO> selectCameraEmapByEMapId(@Param("outletid") int outletId,@Param("emapid") int emapid);

}
