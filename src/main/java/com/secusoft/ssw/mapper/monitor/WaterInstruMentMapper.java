package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.WaterInstruMent;
import com.secusoft.ssw.model.viewobject.Request.WaterEquipmentVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 仪器
 */
@Mapper
public interface WaterInstruMentMapper {

    @Select("select deviceid,devicename from waterinstrument where outletid=#{outletid} order by id desc ")
    List<WaterEquipmentVO> queryEquipmentID(@Param("outletid") int outletid);

    @Insert("INSERT INTO waterinstrument(deviceid, devicename, adduser, middlevalue, poorvalue, wmid,outletid) VALUES (#{deviceid}, #{devicename}, #{adduser},#{middlevalue}, #{poorvalue}, #{wmid},#{outletid})")
    void save(WaterInstruMent waterInstruMent);



    @Select("select deviceid,devicename from waterinstrument where wmid=#{wmid} and outletid=#{outletid} order by id desc ")
    List<WaterEquipmentVO> queryEquipmentByWmid(@Param("wmid") int wmid,@Param("outletid") int outletid);
}
