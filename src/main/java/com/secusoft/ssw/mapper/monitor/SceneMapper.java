package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Scene;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SceneMapper {

	@Select("SELECT * FROM scene where outletid=#{outletid} ")
	List<Scene> selectALL(@Param("outletid") Integer outletid);

}
