package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.viewobject.Request.DeviceManage;
import com.secusoft.ssw.model.viewobject.Request.DeviceMg;
import com.secusoft.ssw.model.viewobject.Response.DeviceManageVO;
import com.secusoft.ssw.model.viewobject.Response.DevicetypenameVO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface DeviceManageMapper {
    @Insert("INSERT INTO devicetypename(devicetype, outletid) VALUES (#{devicetype}, #{outletid}) ")
    void saveDeviceType(DevicetypenameVO devicetypenameVO);
    @Insert("INSERT INTO devicemanage (devicename,devicetypeid,devicemodel,iscertificateofinspection,examineid,examinetime,isputonrecord,drivername,telephone,operate,qualifiedimage,operateimage,fileurl,outletid) \n" +
            "VALUES (#{devicename}, #{devicetypeid},#{devicemodel}, #{iscertificateofinspection},#{examineid}, #{examinetime},#{isputonrecord}, #{drivername},#{telephone}, #{operate},#{qualifiedimage},#{operateimage},#{fileurl},#{outletid}) ")
    void saveDevice(DeviceManageVO deviceManageVO);
    @Select("SELECT * FROM devicemanage WHERE devicename = #{devicename} AND outletid=#{outletid} LIMIT 1")
    DeviceManageVO queryDevice(@Param("devicename") String devicename,@Param("outletid") Integer outletid);
    @Update("UPDATE devicemanage SET  devicename=#{devicename},devicetypeid=#{devicetypeid} ,devicemodel=#{devicemodel},iscertificateofinspection=#{iscertificateofinspection} ,examineid=#{examineid},examinetime=#{examinetime},\n" +
            " isputonrecord=#{isputonrecord},drivername=#{drivername} ,telephone=#{telephone},operate=#{operate},qualifiedimage=#{qualifiedimage},operateimage=#{operateimage},fileurl=#{fileurl} \n" +
            "where id=#{id} and outletid=#{outletid}")
    void updateDevice(DeviceManageVO deviceManageVO);
    @Delete("<script>" +
            "delete from devicemanage where id in " +
            "  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
            "    separator=\",\" close=\")\">\n" +
            "            #{item}\n" +
            "  </foreach>" +
            " </script>")
    void deleteDevice(List<String> list);
    @Select("<script>SELECT * FROM devicemanage dm LEFT JOIN devicetypename dt \n" +
            "ON dm.devicetypeid = dt.id WHERE dm.outletid=#{outletid} " +
            "<if test='iscertificateofinspection!=null and iscertificateofinspection != 2'> and dm.iscertificateofinspection=#{iscertificateofinspection} </if> " +
            "<if test='isputonrecord!=null and isputonrecord != 2'> and dm.isputonrecord=#{isputonrecord} </if> " +
            "<if test='devicetype!=null'> and dt.devicetype like CONCAT('%',#{devicetype},'%') </if> " +
            "order by dm.id desc" +
            "</script>" )
    List<DeviceManage> queryAllDevice(DeviceMg deviceMg);
    @Select("SELECT * FROM devicetypename WHERE outletid = #{outletid} ")
    List<DevicetypenameVO> queryDeviceType(@Param("outletid") Integer outletid);
    @Select("SELECT * FROM devicetypename WHERE devicetype = #{devicetype} AND  outletid = #{outletid} ORDER BY id DESC LIMIT 1 ")
    DevicetypenameVO queryDeviceByType(@Param("devicetype") String devicetype,@Param("outletid") Integer outletid);

    @Select("SELECT dm.*,dt.devicetype FROM devicemanage dm LEFT JOIN devicetypename dt \n" +
            "            ON dm.devicetypeid = dt.id WHERE dm.id =#{id}  AND dm.outletid=#{outletid} LIMIT 1 ")
    DeviceManage queryOneDevice(@Param("id") Integer id,@Param("outletid") Integer outletid);
}
