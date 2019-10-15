package com.secusoft.ssw.mapper.monitor;

import java.util.List;

import com.secusoft.ssw.model.monitor.Settlement;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface SettlementMapper extends BaseMapper<Settlement> {


    @Select("SELECT * FROM settlement WHERE outletId = #{outletId} AND (dt BETWEEN #{startTime} AND #{endTime})")
    List<Settlement> selectSettlement(@Param("outletId") Integer outletId,
                        @Param("startTime") String startTime, @Param("endTime") String endTime);


    @Select("<script>select * from settlement where 1=1 " +
            "<if test='settleunitmonid!=null'>and settleunitmonid=#{settleunitmonid}</if> "+
            "and outletid=#{outletId} </script> ")
    List<Settlement> query(@Param("settleunitmonid")Integer settleunitmonid,@Param("outletId")Integer outletId);



    @Insert("<script>" +
            "INSERT INTO `settlement` (location,location_no,last_val,current_val,accumulate_val,rate,remark,dt, " +
            "equipment,equipment_no,outletid,depth,maxalarm,minalarm,settleunitmonid,waterdepth,lastaccumulate_val,thecurrent_var) VALUES " +
            "<foreach item = 'item' index = 'index' collection='Settlementlist' separator=','>" +
            "(#{item.location}, #{item.location_no}, #{item.last_val}, #{item.current_val}, #{item.accumulate_val}, " +
            "#{item.rate}, #{item.remark}, #{item.dt}, #{item.equipment}, #{item.equipment_no} ," +
            "#{item.outletid}, #{item.depth}, #{item.maxalarm}, #{item.minalarm}, #{item.settleunitmonid}, " +
            "#{item.waterdepth}, #{item.lastaccumulate_val}, #{item.thecurrent_var} " +
            ")" +
            "</foreach>" +
            "</script>")
    void saveList(@Param("outletId") Integer outletId, @Param("Settlementlist")List<Settlement> Settlementlist);
    @Select("select settleunitmonid from settlement where id = #{id} and outletId = #{outletId}")
    Integer getSettleunitmonidById(Integer id,@Param("outletId")Integer outletId);

}