package com.secusoft.ssw.mapper.monitor;


import com.secusoft.ssw.model.monitor.Settleimageunitmon;
import com.secusoft.ssw.model.monitor.Settleunitmon;
import com.secusoft.ssw.model.monitor.ThresholdName;
import com.secusoft.ssw.model.monitor.Thresholdvaluepoint;
import com.secusoft.ssw.model.viewobject.Request.SettleunitmonVO;
import com.secusoft.ssw.model.viewobject.Request.ThresholdNameTwoVO;
import com.secusoft.ssw.model.viewobject.Request.ThresholdvalueTwoVO;
import com.secusoft.ssw.model.viewobject.Request.ThresholdvalueVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SettleunitmonMapper {

    @Select("select id,name,code,unit,type from settleunitmon where outletid=#{outletid}")
    List<SettleunitmonVO> selectSettleunitmon(@Param("outletid") Integer outletid);




    @Select("select * from settleunitmon where code=#{settleunitmon.code} and outletid=#{outletid} ")
    Settleunitmon querySettleunitmon(@Param("outletid") Integer outletid, @Param("settleunitmon")Settleunitmon settleunitmon);

    @Insert("insert into settleunitmon (name,code,unit,outletid,type) values (#{settleunitmon.name},#{settleunitmon.code},#{settleunitmon.unit},#{settleunitmon.outletid},#{settleunitmon.type}) ")
    Integer saveSettleunitmon(@Param("settleunitmon")Settleunitmon settleunitmon);

    @Update("update settleunitmon set name=#{settleunitmon.name},code=#{settleunitmon.code},unit=#{settleunitmon.unit},type=#{settleunitmon.type} where id=#{settleunitmon.id} and outletid=#{outletid} ")
    Integer updateSettleunitmon(@Param("settleunitmon")Settleunitmon settleunitmon,@Param("outletid") Integer outletid);

    @Delete("<script>" +
            "delete from settleunitmon where id in " +
            "  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
            "    separator=\",\" close=\")\">\n" +
            "            #{item}\n" +
            "  </foreach>" +
            "and outletid=#{outletid} </script>")
    void deleteSettleunitmon(List<String> list,@Param("outletid")Integer outletid);

    @Select("select tn.*,tv.max_val,tv.max_val as value,tv.id as thresholeid from thresholdvalue tv INNER JOIN thresholdname tn on tv.thrnameid=tn.id where tv.settleunitmonid=#{id} and tv.outletid=#{outletid} ")
    List<ThresholdName> queryThresholdName(@Param("outletid")Integer outletid, @Param("id")Integer id);

    @Insert("INSERT INTO thresholdvalue(settleunitmonid,thrnameid,max_val,outletid)values(#{vo.settleunitmonid},#{vo.thrnameid},#{vo.value},#{outletid}) ")
    void saveThresholdvalue( @Param("vo")ThresholdvalueVO vo, @Param("outletid")Integer outletid);

    @Update("update thresholdvalue set settleunitmonid=#{vo.settleunitmonid},thrnameid=#{vo.thrnameid},max_val=#{vo.value} where id=#{vo.id} and outletid=#{outletid} ")
    void updateThresholdvalue(@Param("vo")ThresholdvalueVO vo, @Param("outletid")Integer outletid);



    @Update("update thresholdname set name=#{vo.name},type=#{vo.type} where id=#{vo.id} and outletid=#{outletid}")
    void updateThresholdname(@Param("vo")ThresholdName vo, @Param("outletid")Integer outletid);

    @Insert("insert into thresholdname (name,type,outletid)value(#{vo.name},#{vo.type},#{outletid})")
    void saveThresholdname(@Param("vo")ThresholdName vo, @Param("outletid")Integer outletid);

    @Insert("INSERT INTO thresholdvaluepoint(settleunitmonid,thrnameid,max_val,outletid,location_no)values(#{vo.settleunitmonid},#{vo.thrnameid},#{vo.value},#{outletid},#{locationno}) ")
    void saveThresholdvaluepoint(@Param("vo")ThresholdvalueVO vo, @Param("locationno")String locationno, @Param("outletid")Integer outletid);


    @Update("update thresholdvaluepoint set settleunitmonid=#{vo.settleunitmonid},thrnameid=#{vo.thrnameid},max_val=#{vo.value} where thrnameid=#{vo.thrnameid} and location_no=#{locationno} and outletid=#{outletid} ")
    void updateThresholdvaluepoint(@Param("vo")ThresholdvalueVO vo, @Param("locationno")String locationno, @Param("outletid")Integer outletid);

    @Select("select * from settleimageunitmon where locationno=#{locationno} or parentlocationno=#{locationno} and outletid=#{outletid} ")
    Settleimageunitmon querySettleimageunitmonBylocationno(@Param("locationno")String locationno, @Param("outletid")Integer outletid);

    @Delete("delete from settleimageunitmon where (locationno=#{locationno} or parentlocationno=#{locationno}) and outletid=#{outletid} ")
    void deletedeletePoint(@Param("locationno")String locationno, @Param("outletid")Integer outletid);

    @Delete("delete from thresholdvaluepoint where location_no=#{locationno} and outletid=#{outletid} ")
    void deletePointthresholdvalue(@Param("locationno")String locationno, @Param("outletid")Integer outletid);


    @Select("select tn.*,tv.max_val,tv.max_val as value,tv.id as thresholeid,tv.settleunitmonid as settleunitmonid,tv.thrnameid as thrnameid from thresholdvalue tv INNER JOIN thresholdname tn on tv.thrnameid=tn.id where tv.settleunitmonid=#{id} and tv.outletid=#{outletid} ")
    List<ThresholdNameTwoVO> queryQueryThresholdNameANDVAL(@Param("outletid")Integer outletid, @Param("id")Integer id);



    @Update("update settleimageunitmon set locationno=#{newno} where locationno=#{oldno} and outletid=#{outletid} ")
    void updateSettleimageunitmonlocationno(@Param("oldno")String oldno, @Param("newno")String newno,@Param("outletid")Integer outletid);

    @Delete("delete from settlement where location_no=#{str1} and outletid=#{outletid} ")
    void deletesettlementbylocationno(@Param("str1")String str1, @Param("outletid")Integer outletid);

    @Update("update thresholdvaluepoint set location_no=#{newno} where location_no=#{oldno} and outletid=#{outletid} ")
    void updateThresholdvaluepointlocationno(@Param("oldno")String oldno, @Param("newno")String newno,@Param("outletid")Integer outletid);

    @Update("update settlement set location_no=#{newno} where location_no=#{oldno} and outletid=#{outletid} ")
    void updateSettlementlocationno(@Param("oldno")String oldno, @Param("newno")String newno,@Param("outletid")Integer outletid);

    @Select("select * from thresholdvaluepoint where location_no=#{vo.locationno} and settleunitmonid=#{vo.settleunitmonid} and thrnameid=#{vo.thrnameid} and outletid=#{outletid} ")
    List<Thresholdvaluepoint> queryThresholdvaluepointByLocationno(@Param("vo") ThresholdvalueTwoVO vo, @Param("outletid")Integer outletid);

    @Select("select settleunitmonid from settleimageunitmon where locationno=#{locationno} and outletid=#{outletid} limit 1 ")
    Integer querySettleunitmonidByLocationno(@Param("locationno")String locationno, @Param("outletid")Integer outletid);
}
