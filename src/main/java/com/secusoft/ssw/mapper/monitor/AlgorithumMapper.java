package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Algorithum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AlgorithumMapper {

    @Select("select * from algorithum where enable=1 and outletid=#{outletid}")
    List<Algorithum> selectAll(@Param("outletid") int outletid);

}
