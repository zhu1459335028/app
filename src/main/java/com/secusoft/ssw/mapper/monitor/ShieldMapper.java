package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Shield;
import com.secusoft.ssw.model.monitor.ShieldNuberAdcan;
import com.secusoft.ssw.model.monitor.Shieldadvance;
import com.secusoft.ssw.model.monitor.Shieldpoint;
import com.secusoft.ssw.model.viewobject.Request.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShieldMapper {

    @Select("select * from shield where shieldno=#{shileldname} and outletid=#{outletid} limit 1 ")
    Shield queryShieldByShieldName(@Param("outletid")Integer outletid,  @Param("shileldname")String shileldname);

    @Select("select max(id) from shield where outletid=#{outletid} ")
    Integer queryMaxId(@Param("outletid")Integer outletid);

    @Insert("INSERT INTO `shield` (`shieldname`, `shieldno`, `totalmileage`, `totalring`, `eachringmileage`, `outletid`) VALUES " +
            "(#{shieldname}, #{shieldno}, #{totalmileage}, #{totalring}, #{eachringmileage},#{outletid}); ")
    void save(Shield insert);

    @Select("select shieldno from shield where outletid=#{outletid} ")
    List<String> queryAllid(@Param("outletid") Integer outletId);

    @Select("select * from shield where shieldno=#{shieldname} and outletid=#{outletid}  ")
    Shield queryByShieldName(@Param("shieldname") String shieldname,@Param("outletid") Integer outletId);
    @Insert("INSERT INTO `shieldadvance` (`segmentno`, `construction`, `supervise`, intervalname,`tunnellingdate`, `tunnellingtime`, `slipcastingtime`,slipcastingmeasure,assembledate,assembletime,driver,segmentassemble,\n" +
            "qualitymanager,beonduty,foreman,superviseengineer,factory,outletid) \n" +
            "VALUES (#{segmentno},#{construction}, #{supervise},#{intervalname}, #{tunnellingdate}, #{tunnellingtime}, #{slipcastingtime}, #{slipcastingmeasure}, #{assembledate}, #{assembletime}, #{driver}, " +
            "#{segmentassemble}, #{qualitymanager}, #{beonduty}, #{foreman}, #{superviseengineer},#{factory},#{outletid});")
    void saveShieldadvance(ShieldadvanceVO shieldadvanceVO);
    @Update("UPDATE shieldadvance SET segmentno = #{segmentno},construction = #{construction},supervise = #{supervise},intervalname = #{intervalname},tunnellingdate = #{tunnellingdate},tunnellingtime = #{tunnellingtime},slipcastingtime= #{slipcastingtime},slipcastingmeasure = #{slipcastingmeasure},assembledate=#{assembledate}, \n" +
            "assembletime = #{assembletime},driver = #{driver},segmentassemble = #{segmentassemble},qualitymanager = #{qualitymanager},beonduty= #{beonduty},foreman = #{foreman},superviseengineer = #{superviseengineer},factory = #{factory} WHERE id = #{id} and outletid = #{outletid} ")
    void updateShieldadvance(ShieldadvanceVO shieldadvanceVO);
    @Select("SELECT * FROM shieldadvance WHERE segmentno = #{segmentno} and outletid=#{outletid} ")
    Shieldadvance queryShieldadvanceBySegmentno(@Param("segmentno")Integer segmentno,@Param("outletid")Integer outletid);
    @Select("<script>SELECT * FROM shieldadvance WHERE outletid =#{outletid} \n" +
            "<if test='tunnellingdate!=null '> and tunnellingdate like CONCAT('%',#{tunnellingdate},'%') </if> " +
            "order by segmentno desc </script>")
    List<Shieldadvance> queryAllShieldadvance(Advance advance);
    @Select("SELECT * FROM shieldadvance WHERE outletid =#{outletid} order by segmentno desc")
    List<Shieldadvance> selectAllShieldadvance(Advance advance);
    @Select("SELECT * FROM shieldadvance WHERE segmentno = #{segmentno} and outletid =#{outletid} ")
    List<Shieldadvance> selectShieldadvance(@Param("segmentno")Integer segmentno, @Param("outletid")Integer outletid);
    @Delete("<script>" +
            "delete from shieldadvance where id in " +
            "  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
            "    separator=\",\" close=\")\">\n" +
            "            #{item}\n" +
            "  </foreach>" +
            " </script>")
    void deleteShieldadvance(List<String> list);
    @Insert("INSERT INTO `shieldpoint` (`stages`, `date`, `number`, mileage,`describes`,`outletid`)\n" +
            "            VALUES (#{shieldpointVO.stages},#{shieldpointVO.date}, #{shieldpointVO.number},#{shieldpointVO.mileage}, #{shieldpointVO.describes}, #{outletid})")
    void saveShieldpoin(@Param("shieldpointVO")ShieldpointVO shieldpointVO,@Param("outletid")Integer outletid);
    @Update("UPDATE shieldpoint SET stages = #{shieldpointVO.stages},date = #{shieldpointVO.date},number = #{shieldpointVO.number},mileage = #{shieldpointVO.mileage},describes = #{shieldpointVO.describes} WHERE id = #{shieldpointVO.id} and outletid = #{outletid} ")
    void updateShieldpoin(@Param("shieldpointVO")ShieldpointVO shieldpointVO,@Param("outletid")Integer outletid);
    @Select("SELECT * FROM shieldpoint WHERE number = #{number} and outletid =#{outletid}  limit 1 ")
    Shieldpoint queryShieldpoint(@Param("number")float number, @Param("outletid")Integer outletid);
    @Select("SELECT * FROM shieldpoint WHERE number = #{number} and outletid =#{outletid}  limit 1")
    Shieldpoint selectShieldpointByNuber(@Param("number")float number, @Param("outletid")Integer outletid);
    @Select("SELECT * FROM shieldpoint WHERE outletid =#{outletid}")
    List<Shieldpoint> selectAllShieldpoint(@Param("outletid")Integer outletid);
    @Select("SELECT advancefx,totalring FROM shield WHERE outletid =#{outletid} ORDER BY id DESC LIMIT 1 ")
    ShieldNuberAdcan selectAdvancefxByOutletId(@Param("outletid")Integer outletid);
    @Insert("INSERT INTO `dangerpoint` (`shieldid`, `pointname`, `remarks`, outletid)\n" +
            "            VALUES (#{shieldid},#{pointname}, #{remarks}, #{outletid})")
    void saveDangerous(@Param("shieldid")Integer shieldid, @Param("pointname")String pointname, @Param("remarks")String remarks,@Param("outletid")Integer outletid);
    @Update("UPDATE dangerpoint SET pointname = #{pointname},remarks = #{remarks} WHERE id = #{id} and outletid = #{outletid} ")
    void UpdateDangerous(@Param("id")Integer id,@Param("pointname")String pointname, @Param("remarks")String remarks, @Param("outletid")Integer outletid);
    @Select("SELECT id,shieldid, pointname, remarks,outletid  FROM dangerpoint WHERE id = #{id} AND outletid = #{outletid} limit 1")
    Dangerous selectDangerous(@Param("id")Integer id, @Param("outletid")Integer outletid);
}
