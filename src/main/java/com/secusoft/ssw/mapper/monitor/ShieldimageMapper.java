package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.viewobject.Response.ShieldImageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShieldimageMapper {



    @Select("select imageUrl from shieldimage where shieldid=#{id} and outletid=#{outletid}  ")
    String queryShieldimageUrl(@Param("id") Integer id, @Param("outletid") Integer outletid);

    @Select("select imageUrl,type from shieldimage where shieldid=#{id} and outletid=#{outletid}  ")
    List<ShieldImageVo> queryShieldimageUrlList(@Param("id") Integer id, @Param("outletid") Integer outletid);
}
