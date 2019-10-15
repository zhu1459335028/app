package com.secusoft.ssw.mapper.monitor;

import java.util.List;

import com.secusoft.ssw.model.monitor.Gas;
import com.secusoft.ssw.model.viewobject.Request.Iocontroller;
import com.secusoft.ssw.model.viewobject.Response.GasRespVO;
import com.secusoft.ssw.model.viewobject.Response.IocontrollerVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GasMapper {



//    @Select("SELECT * FROM gas WHERE outletid=#{outletid} AND type=#{gasType} AND (dt BETWEEN #{startTime} AND #{endTime}) order by dt asc")
//    List<Gas> selectGas(@Param("outletid") Integer outletid, @Param("gasType") Integer gasType,
//                        @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("<script>SELECT id,tbm_range,dttte,DATE_FORMAT(dt,'%Y-%m-%d') as dtStr,dt,type,val,rummager,outletid FROM gas WHERE outletid=#{outletid} AND type=#{gasType} AND dt BETWEEN  #{startTime} AND #{endTime} order by id desc limit 1 </script>")
    List<Gas> selectGas(@Param("outletid") Integer outletid, @Param("gasType") Integer gasType,
                        @Param("startTime") String startTime, @Param("endTime") String endTime);


    @Select("<script>SELECT group_concat(type) as type,group_concat(val) as val,group_concat(id) as id," +
            "tbm_range,dttte,dt,rummager,outletid FROM gas WHERE outletId = #{currentOutletId} and type in " +
            "<foreach collection='types' open='(' close=')' item='type' separator=','>#{type}</foreach>" +
            " group by dt ORDER BY dt DESC </script>")
    List<Gas> getGasListByTypes(@Param("currentOutletId") Integer currentOutletId, @Param("types") List<Integer> types);




    @Select("<script>SELECT ifnull(val,0) FROM gas WHERE outletId = #{currentOutletId} and type=#{types} "+
            " order by id desc limit 1 </script>")
    String getGasListByType(@Param("currentOutletId") Integer currentOutletId, @Param("types") Integer types);

    @Insert("<script>insert into gas " +
            "(tbm_range,dttte,dt,type,val,rummager,outletid,device_id) " +
            "values <foreach collection='list' item='r' separator=','>" +
            "(#{r.tbm_range},#{r.dttte},#{r.dt},#{r.type},#{r.val},#{r.rummager}" +
            ",#{r.outletid},#{r.device_id})</foreach></script>")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    void saveGasList(@Param("list") List<Gas> gasList);

    @Delete("<script>delete from gas where id in " +
            "<foreach collection='ids' open='(' close=')' item='id' separator=','>#{id}</foreach>" +
            "and outletid = #{outletid} </script>")
    void deleteGasByIdList(@Param("ids") List<Integer> id,@Param("outletid") Integer outletid);

    @Update("update gas set tbm_range = #{tbm_range},dttte = #{dttte},dt = #{dt},type = #{type},val = #{val}," +
            "rummager = #{rummager},outletid = #{outletid} where id = #{id} and outletid = #{outletid} ")
    void updateGas(Gas gas);

    @Select("<script>SELECT * FROM gas WHERE id in " +
            "<foreach collection='ids' open='(' close=')' item='id' separator=','>#{id}</foreach>" +
            "and outletid = #{outletid} </script> ")
    List<Gas> getGasListByIds(@Param("ids") List<Integer> ids,@Param("outletid") Integer outletid);

    @Update({"<script>" +
            "<foreach collection='gasList' item='gas' separator=';'>" +
            " update gas set tbm_range = #{gas.tbm_range, jdbcType=VARCHAR}," +
            " dttte = #{gas.dttte, jdbcType=INTEGER}," +
            " dt = #{gas.dt, jdbcType=TIMESTAMP}," +
            " type = #{gas.type, jdbcType=INTEGER}," +
            " val = #{gas.val, jdbcType=VARCHAR}," +
            " rummager = #{gas.rummager, jdbcType=VARCHAR}," +
            " outletid = #{gas.outletid, jdbcType=INTEGER} where " +
            "id = #{gas.id, jdbcType=INTEGER}" +
            "</foreach></script>"})
    void updateGasList(@Param("gasList") List<Gas> gasList);

    @Select("<script>select count(1) from (SELECT group_concat(type) as type,group_concat(val) as val,group_concat(id) as id," +
            "tbm_range,dttte,dt,rummager,outletid FROM gas WHERE outletId = #{currentOutletId} and type in " +
            "<foreach collection='types' open='(' close=')' item='type' separator=','>#{type}</foreach>" +
            " group by dt) as g</script>")
    int getGasListCountByTypes(@Param("currentOutletId") Integer currentOutletId,@Param("types") List<Integer> types);

    @Select("select max(id) as id,type as name,val as value from gas where outletid=#{outletid} and type in (6,7,8,9,10,11,13)  group by type ; ")
    List<GasRespVO> homePageGasPopupWindow(@Param("outletid") Integer outletid);

    @Select("select max(id) as id,type as name,val as value from gas where outletid=#{outletid} and type in (6,7,8)  group by type ; ")
    List<GasRespVO> homePageGasPopupWindowInit(@Param("outletid") Integer outletid);


    @Select("select id as id,type as name,val as value from gas where outletid=#{outletid} and type=#{name} and id in(#{id}) order by id desc limit 1 ; ")
    GasRespVO queryLast(@Param("outletid")Integer outletid, @Param("name")String name, @Param("id")Integer id);

    @Select("select id as id,type as name,val as value from gas where outletid=#{outletid} and type=#{type} order by id desc limit 1; ")
    GasRespVO queryGasByType(@Param("outletid")Integer outletid, @Param("type")String type);
    @Select("select device_id from gas where outletid=#{outletid} order by id desc limit 1; ")
    String quertDevice(Integer outletid);
    @Select("select * from reportdevice where devicename=#{devicename} and outletid=#{outletid} limit 1; ")
    IocontrollerVO selectGasIoInFO(@Param("devicename")String devicename, @Param("outletid")Integer outletid);
    @Insert("INSERT INTO reportdevice (iodeviceid,ioname,devicename,username,password,type,online,factory,road,devicetype,outletid) \n" +
            "VALUES (#{iodeviceid}, #{ioname},#{devicename}, #{username}, #{password},#{type}, #{online}, #{factory}, #{road},#{devicetype},#{outletid})")
    void saveGasIoInFO(Iocontroller iocontroller);
    @Update("UPDATE reportdevice SET iodeviceid = #{iodeviceid},ioname = #{ioname},devicename = #{devicename},username = #{username},password= #{password},type = #{type},factory=#{factory},\n" +
            "road = #{road},devicetype = #{devicetype} WHERE id = #{id} and outletid = #{outletid}")
    void updateGasIoInFO(Iocontroller iocontroller);
    @Select("select * from reportdevice where outletid=#{outletid}  ORDER BY devicetype asc ")
    List<IocontrollerVO> selectAllIoInfo(@Param("outletid")Integer outletid);
    @Select("select * from reportdevice where id=#{id} and outletid=#{outletid} limit 1 ")
    IocontrollerVO selectOneIoInfo(@Param("id")Integer id, @Param("outletid")Integer outletid);
    @Delete("delete from reportdevice where id=#{id} and outletid=#{outletid} ")
    void deleteOneIoInfo(@Param("id")Integer id, @Param("outletid")Integer outletid);
    @Select("select * from reportdevice where devicetype=#{devicetype} and outletid=#{outletid} limit 1 ")
    Iocontroller selectIoBydevicetype(@Param("devicetype")String devicetype, @Param("outletid")Integer outletid);
}