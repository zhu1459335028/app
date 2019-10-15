package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.viewobject.Response.TargetgoalsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  * 阶段目标表 Mapper 接口
 * </p>
 *
 * @author wdz
 * @since 2019-04-11
 */
@Mapper
public interface TargetgoalsMapper {

    @Select("select targetname,targetring,targetmileage,startdate,enddate from targetgoals where shieldid=#{id} and outletid=#{outletid} ")
    List<TargetgoalsVO> queryTargetListByShielId(@Param("id") Integer id,@Param("outletid") Integer outletid);


}