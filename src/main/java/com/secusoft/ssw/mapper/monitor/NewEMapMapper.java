package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.NewEMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewEMapMapper {

	@Select("SELECT * FROM NewEMap where outletid=#{outletid}")
	List<NewEMap> selectEMapByOutletId(@Param("outletid") int outletid);

}
