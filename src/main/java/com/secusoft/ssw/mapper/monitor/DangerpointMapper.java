package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.viewobject.Response.DangerpointVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  危险点Mapper 接口
 * </p>
 *
 * @author wdz
 * @since 2019-04-11
 */
@Mapper
public interface DangerpointMapper {

    @Select("select d.pointname,d.pointtype,d.startspacing,d.depth as dangerdepth,s.depth as standarddepth,s.drawlength from dangerpoint d left join shield s on d.shieldid=s.id where d.shieldid=#{id} and d.outletid=#{outletid} limit 1")
    List<DangerpointVO> queryDangerListByShielId(@Param("id") Integer id, @Param("outletid") Integer outletid);
}