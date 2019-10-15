package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.WaterLevel;
import com.secusoft.ssw.model.viewobject.Request.WaterLevelVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 测量记录
 */
@Mapper
public interface WaterLevelMapper {



    @Insert("INSERT INTO waterlevel(watervalue, deviceid, monitortime, outletid) VALUES (#{watervalue}, #{deviceid}, #{monitortime}, #{outletid})")
    void save(WaterLevel waterLevel);

    @Select("select id as id ,watervalue as watervalue,DATE_FORMAT(monitortime,'%Y-%m-%d %H:%i:%s') as monitortime from waterlevel where deviceid=#{deviceid} and outletid=#{outletid} order by id desc limit 7 ")
    List<WaterLevelVO> queryNewWaterLevel(@Param("deviceid")String deviceid,@Param("outletid") int outletid);

    @Select("SELECT " +
            "wm.address AS address, " +
            "wl.watervalue AS watervalue, " +
            "DATE_FORMAT(wl.monitortime,'%Y-%m-%d %H:%i:%s') AS monitortime " +
            "FROM " +
            "waterlevel wl " +
            "LEFT JOIN waterinstrument wi ON wl.deviceid = wi.deviceid " +
            "LEFT JOIN watermonitor wm ON wi.wmid = wm.id where wl.deviceid=#{deviceid} and wm.address!='depth' and wl.outletid=#{outletid} order by wl.id desc limit 1 ")
    WaterLevelVO qieruNewValue(@Param("deviceid")String deviceid,@Param("outletid") int outletid);

    @Select("select id from waterinstrument where deviceid=#{device_id} and outletid=#{outletid}")
    Integer queryInstruMent(@Param("device_id")String device_id,@Param("outletid") int outletid);
}
