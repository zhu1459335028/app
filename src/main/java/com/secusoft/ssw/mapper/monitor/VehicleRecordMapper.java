package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.VehicleRecord;
import com.secusoft.ssw.model.viewobject.Request.VehicleRecordVo;
import com.secusoft.ssw.model.viewobject.Response.VehicleRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface VehicleRecordMapper {

    /**
     * 分页查询
     * @param vehicleRecordVo
     *       */
    @Select("<script>select v.type as vehicletype,v.company as company,ve.id as id,ve.type as entryouttype,ve.plate_number,ve.head_imageurl," +
            "DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d %H:%i:%s') AS head_imagetime,ve.body_imageurl," +
            "DATE_FORMAT(ve.body_imagetime,'%Y-%m-%d %H:%i:%s') AS body_imagetime,ve.device_head as camera_head,ve.camera_body,ve.device_head_id,ve.camera_body_id," +
            "DATE_FORMAT(ve.inserttime,'%Y-%m-%d %H:%i:%s') AS inserttime,ve.patch,ve.uuid " +
            "from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number where 1=1 " +
            "<if test='type!=null'> and v.type=#{type} </if> " +
            "<if test='company!=null'> and v.company=#{company} </if> " +
            "<if test='starttime!=null'> and ve.head_imagetime &gt;=#{starttime} </if> " +
            "<if test='endtime!=null'> and ve.head_imagetime &lt;=#{endtime} </if> " +
            "<if test='plate_number!=null'> and ve.plate_number like CONCAT('%',#{plate_number},'%') </if> " +
            "and ve.outletid=#{outletid} group by ve.uuid order by ve.id desc " +
            "</script>")
    List<VehicleRecordVO> queryVehicleRecordList(VehicleRecordVo vehicleRecordVo);

    /**
     * 查询 另一半数据
     * @param uuid
     * @param entryouttype
     * @return
     */
    @Select("<script>" +
            "select id,\n" +
            " plate_number,\n" +
            " head_imageurl,\n" +
            " head_imagetime,\n" +
            " body_imageurl,\n" +
            " body_imagetime,\n" +
            " type,\n" +
            " device_head as camera_head,\n" +
            " camera_body,\n" +
            " device_head_id,\n" +
            " camera_body_id,\n" +
            " patch,\n" +
            " uuid,\n" +
            " inserttime,\n" +
            " updatetime\n " +
            "from vehiclerecord where 1=1 and outletid=#{outletid}" +
            "<if test='uuid!=null'>and uuid=#{uuid} </if> " +
            "<if test='entryouttype!=null'>and type=#{entryouttype} </if> " +
            "</script>")
    VehicleRecord queryConditionList(@Param("uuid")String uuid, @Param("entryouttype")Integer entryouttype,@Param("outletid") Integer outletid);


    @Select("<script>select count(DISTINCT ve.uuid) " +
            "from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number where 1=1 " +
            "<if test='type!=null'> and v.type=#{type} </if> " +
            "<if test='company!=null'> and v.company=#{company} </if> " +
            "<if test='starttime!=null'> and ve.head_imagetime &gt;=#{starttime} </if> " +
            "<if test='endtime!=null'> and ve.head_imagetime &lt;=#{endtime} </if> " +
            "<if test='plate_number!=null'> and ve.plate_number like CONCAT('%',#{plate_number},'%') </if> " +
            "and ve.outletid=#{outletid}" +
            "</script>")
    Integer queryVehicleRecordCount(VehicleRecordVo vehicleRecordVo);

    @Update("UPDATE vehiclerecord SET patch=1,head_imagetime=#{bltime} where id=#{id} and outletid = #{outletid}")
    Integer updateVehicleRecord(@Param("id")int id, @Param("bltime")String bltime, @Param("outletid")Integer outletid);


    @Select("<script>select '未知' as vehicletype, '未登记' as company, \n" +
            "ve2.id as id,\n" +
            "ve2.type as entryouttype,\n" +
            "ve2.plate_number,\n" +
            "ve2.head_imageurl,\n" +
            "DATE_FORMAT(ve2.head_imagetime,'%Y-%m-%d %H:%i:%s') AS head_imagetime,\n" +
            "ve2.body_imageurl,\n" +
            "DATE_FORMAT(ve2.body_imagetime,'%Y-%m-%d %H:%i:%s') AS body_imagetime,\n" +
            "ve2.device_head as camera_head,\n" +
            "ve2.camera_body,\n" +
            "ve2.device_head_id,\n" +
            "ve2.camera_body_id,\n" +
            "DATE_FORMAT(ve2.inserttime,'%Y-%m-%d %H:%i:%s') AS inserttime,\n" +
            "ve2.patch,ve2.uuid  \n" +
            "from vehiclerecord ve2 where 1=1 and ve2.outletid =#{outletid} " +
            "<if test='starttime!=null'> and ve2.head_imagetime &gt;=#{starttime} </if> " +
            "<if test='endtime!=null'> and ve2.head_imagetime &lt;=#{endtime} </if> " +
            "<if test='plate_number!=null'> and ve2.plate_number like CONCAT('%',#{plate_number},'%') </if> " +
            "and ve2.id not in (select ve.id from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number \n" +
            "where 1=1 and v.outletid=#{outletid} group by ve.uuid order by ve.id desc) group by ve2.uuid order by ve2.id desc\n</script>")
    List<VehicleRecordVO> queryVehicleRecordListByIds(VehicleRecordVo vehicleRecordVo);



    @Select("<script>select count(DISTINCT(ve2.uuid)) " +
            "from vehiclerecord ve2 where 1=1 " +
            "<if test='starttime!=null'> and ve2.head_imagetime &gt;=#{starttime} </if> " +
            "<if test='endtime!=null'> and ve2.head_imagetime &lt;=#{endtime} </if> " +
            "<if test='plate_number!=null'> and ve2.plate_number like CONCAT('%',#{plate_number},'%') </if> " +
            "and ve2.id not in (select ve.id from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number " +
            "where 1=1 and v.outletid=#{outletid} group by ve.uuid order by ve.id desc)</script>")
    Integer queryVehicleRecordCount2(VehicleRecordVo vehicleRecordVo);


    @Select("<script>select * from vehiclerecord where plate_number=#{str} " +
            "<if test='starttime !=null'> and head_imagetime &gt;=#{starttime} </if> " +
            "<if test='endtime !=null'> and head_imagetime &lt;=#{endtime} </if> " +
            "and head_imageurl is not null and head_imagetime is not null group by uuid </script>")
    List<VehicleRecord> queryVehicleRecordListByPlateNumber(@Param("str")String str,@Param("starttime")String starttime, @Param("endtime")String endtime);

    @Select("select * from vehiclerecord where  uuid=#{uuid} and type=#{type} and head_imageurl is not null and head_imagetime is not null")
    VehicleRecord queryVehicleRecord(@Param("uuid")String uuid, @Param("type")String type);

    @Select("<script>select IFNULL(v.type,'未知') as vehicletype,IFNULL(v.company,'未登记') as company, \n" +
            "ve2.id as id,\n" +
            "ve2.type as entryouttype,\n" +
            "ve2.plate_number,\n" +
            "ve2.head_imageurl,\n" +
            "DATE_FORMAT(ve2.head_imagetime,'%Y-%m-%d %H:%i:%s') AS head_imagetime,\n" +
            "ve2.body_imageurl,\n" +
            "DATE_FORMAT(ve2.body_imagetime,'%Y-%m-%d %H:%i:%s') AS body_imagetime,\n" +
            "ve2.device_head as camera_head,\n" +
            "ve2.camera_body,\n" +
            "ve2.device_head_id,\n" +
            "ve2.camera_body_id,\n" +
            "DATE_FORMAT(ve2.inserttime,'%Y-%m-%d %H:%i:%s') AS inserttime,\n" +
            "ve2.patch,ve2.uuid  \n" +
            "from vehiclerecord ve2 left join vehicle v on ve2.plate_number=v.plate_number where 1=1 and ve2.outletid = #{outletid}" +
            "<if test='type!=null'> and v.type=#{type} </if> " +
            "<if test='company!=null'> and v.company=#{company} </if> " +
            "<if test='starttime!=null'> and ve2.head_imagetime &gt;=#{starttime} </if> " +
            "<if test='endtime!=null'> and ve2.head_imagetime &lt;=#{endtime} </if> " +
            "<if test='plate_number!=null'> and ve2.plate_number like CONCAT('%',#{plate_number},'%') </if> " +
            "group by ve2.uuid order by ve2.id desc\n</script>")
    List<VehicleRecordVO> queryVehicleRecordListAll(VehicleRecordVo vehicleRecordVo);
    @Select("<script>select count(DISTINCT(ve2.uuid)) " +
            "from vehiclerecord ve2 left join vehicle v on ve2.plate_number=v.plate_number where 1=1 and ve2.outletid = #{outletid}" +
            "<if test='type!=null'> and v.type=#{type} </if> " +
            "<if test='company!=null'> and v.company=#{company} </if> " +
            "<if test='starttime!=null'> and ve2.head_imagetime &gt;=#{starttime} </if> " +
            "<if test='endtime!=null'> and ve2.head_imagetime &lt;=#{endtime} </if> " +
            "<if test='plate_number!=null'> and ve2.plate_number like CONCAT('%',#{plate_number},'%') </if> " +
            "</script>")
    Integer queryVehicleRecordCounts(VehicleRecordVo vehicleRecordVo);

}
