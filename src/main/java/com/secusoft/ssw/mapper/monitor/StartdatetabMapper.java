package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.DeviceIpPort;
import com.secusoft.ssw.model.monitor.Homepageimage;
import com.secusoft.ssw.model.monitor.Shieldimage;
import com.secusoft.ssw.model.monitor.Startdatetab;
import com.secusoft.ssw.model.viewobject.Request.IoOnline;
import com.secusoft.ssw.model.viewobject.Request.IoStatus;
import com.secusoft.ssw.model.viewobject.Response.IocontrollerVO;
import com.secusoft.ssw.model.viewobject.Response.ThresholdRespVO;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface StartdatetabMapper {

    @Select("select * from startdatetab where outletid=#{outletid} order by id desc limit 1 ")
    List<Startdatetab> queryConstructiondays(@Param("outletid") Integer outletid);
    @Insert("INSERT INTO startdatetab (outletid,startdate) VALUES (#{outletid},#{startdate})")
    void save(@Param("outletid")Integer currentOutletId, @Param("startdate")String startTime);
    @Select("select startdate from startdatetab where outletid=#{outletid} order by id desc limit 1 ")
    String queryStartTime(@Param("outletid")Integer currentOutletId);
    @Select("SELECT id from startdatetab where outletid= #{outletid} and startdate = #{startdate} order by id desc limit 1")
    Integer queryIdByStartTime(@Param("outletid")Integer currentOutletId, @Param("startdate")String startTime);
    @Delete("DELETE FROM startdatetab where id != #{id} and outletid= #{outletid}")
    void deleteStartTime(@Param("outletid")Integer currentOutletId, @Param("id")Integer id);
    @Select("SELECT title from outlet where outletid= #{outletid}")
    String queryTitle(@Param("outletid")Integer outletid);
    @Select("SELECT imageurl FROM homepageimage WHERE type = 1 and outletid= #{outletid}")
    String queryDgImage(@Param("outletid")Integer outletid);
    @Select("SELECT * FROM homepageimage WHERE outletid= #{outletid}")
    List<Homepageimage> querySgImage(@Param("outletid")Integer outletid);
    @Insert("INSERT INTO homepageimage (imageurl,name,outletid) VALUES (#{imageurl},#{name},#{outletid})")
    void saveImage(@Param("imageurl")String imageurl, @Param("name")String name, @Param("outletid")Integer outletid);
    @Insert("INSERT INTO shieldimage (imageurl,type,outletid) VALUES (#{imageurl},#{type},#{outletid})")
    void saveDgImage(@Param("imageurl")String imageurl, @Param("type")Integer type, @Param("outletid")Integer outletid);
    @Select("SELECT * FROM shieldimage WHERE outletid= #{outletid} and type = 3 ORDER BY id DESC LIMIT 1 ")
    Shieldimage queryImage(@Param("outletid")Integer outletid);

    @Select("SELECT * FROM reportdevice WHERE outletid= #{outletid}  ORDER BY id DESC LIMIT 1")
    IocontrollerVO queryIoStatus(@Param("outletid")Integer outletid);
    @Select("SELECT DomainName,TCPPort  FROM videodevice WHERE outletid= #{outletid} LIMIT 1")
    DeviceIpPort selectIpPort(@Param("outletid")Integer outletid);
    @Update("UPDATE reportdevice SET `status` =#{status} WHERE  iodeviceid= #{iodeviceid} and outletid= #{outletid} ")
    void updateStatus(@Param("status")String status,@Param("iodeviceid")String iodeviceid,@Param("outletid")Integer outletid);
    @Select("INSERT INTO reportstatus (status,ionumid,outletid) VALUES (#{status},#{ionumid},#{outletid})")
    void savaStatus(@Param("status")String status,@Param("ionumid")String ionumid, @Param("outletid")Integer outletid);
    @Select("SELECT *  FROM reportdevice WHERE devicename= #{devicename} and outletid= #{outletid} LIMIT 1")
    IocontrollerVO selectRoad(@Param("devicename")String devicename, @Param("outletid")Integer outletid);
    @Select("SELECT devicename  FROM reportdevice WHERE devicetype= #{devicetype} and outletid= #{outletid} LIMIT 1")
    String selectDeviceNameByDevicetype(@Param("devicetype")String devicetype, @Param("outletid")Integer outletid);
    @Select("SELECT *  FROM reportdevice WHERE iodeviceid= #{iodeviceid} LIMIT 1")
    IoOnline queryIoDevice(@Param("iodeviceid")String iodeviceid);
    @Update("UPDATE reportdevice SET `online` =#{online} WHERE  iodeviceid= #{iodeviceid} ")
    void updateOnline(@Param("online")String online,@Param("iodeviceid")String iodeviceid);
    @Select("SELECT val FROM gas WHERE type= #{type} and outletid= #{outletid} order by id desc limit 1")
    String queryPm25(@Param("type")Integer type, Integer outletid);
    @Select("SELECT max_val FROM threshold WHERE type= #{type} and outletid= #{outletid}  limit 1")
    ThresholdRespVO queryPm25ValueColer(@Param("type")String type, Integer outletid);
    @Select("SELECT devicename  FROM reportdevice WHERE devicetype= #{devicetype} and automanual= #{automanual} and outletid= #{outletid} ")
    List<String> selectDeviceNameByDevicetypes(@Param("devicetype")String devicetype,@Param("automanual")Integer automanual, @Param("outletid")Integer outletid);
    @Update("UPDATE reportdevice SET `automanual` =#{automanual} WHERE  id= #{id} and outletid= #{outletid} ")
    void updateAutomanual(@Param("id")Integer id, @Param("automanual")Integer automanual, @Param("outletid")Integer outletid);
    @Select("SELECT *  FROM reportdevice WHERE id= #{id} and outletid= #{outletid} LIMIT 1")
    IocontrollerVO selectReportdevice(@Param("id")Integer id, @Param("outletid")Integer outletid);
}
