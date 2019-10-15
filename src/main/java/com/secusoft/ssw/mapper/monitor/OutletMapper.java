package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Outlet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OutletMapper {

	@Select("SELECT outletid FROM customeroutlet where customerid=#{customerid}")
	List<Integer> selectOutletIdByUserId(@Param("customerid") int customerid);

	@Select("SELECT outletname FROM outlet where outletid=#{outletid}")
	String selectOutletNameByOutletId(@Param("outletid") int outletid);

	@Select("SELECT * FROM outlet where outletid=#{outletid}")
	Outlet selectOutletByOutletId(@Param("outletid")Integer outletid);
}
