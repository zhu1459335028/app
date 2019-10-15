package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Vehicle;
import com.secusoft.ssw.model.viewobject.Request.VehiclePageVo;
import com.secusoft.ssw.model.viewobject.Response.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 车辆管理Mapper
 */
@Mapper
public interface VehicleMapper {

    /**
     * @Insert("INSERT INTO vehicle (plate_number, type, contacts, phone, company," +
     *             "<if test='headimageurl!=null'>headimageurl</if>," +
     *             "<if test='bodyimageurl!=null'>bodyimageurl</if>," +
     *             "outletid) VALUES (#{plate_number}, #{type}, #{contacts}, #{phone}, #{company}, " +
     *             "<if test='headimageurl!=null'>#{headimageurl}</if>," +
     *             "<if test='bodyimageurl!=null'>#{bodyimageurl}</if>," +
     *             " #{outletid})")
     * @param vehicle
     * @return
     */
    @Insert("INSERT INTO vehicle (plate_number, type, contacts, phone, company,headimageurl,bodyimageurl," +
            "outletid) VALUES (#{plate_number}, #{type}, #{contacts}, #{phone}, #{company},#{headimageurl},#{bodyimageurl}," +
            "#{outletid})")
    Integer saveVehicle(Vehicle vehicle);

    @Update("<script> UPDATE vehicle <set> plate_number=#{plate_number}, type=#{type}, contacts=#{contacts}, phone=#{phone}, company=#{company}, " +
            "headimageurl=#{headimageurl}," +
            "bodyimageurl=#{bodyimageurl}," +
            "</set>"+
            "WHERE id=#{id} and outletid=#{outletid} </script>")
    Integer updateVehicle(Vehicle vehicle);

    @Delete("<script>" +
            "delete from vehicle where id in " +
            "  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
            "    separator=\",\" close=\")\">\n" +
            "            #{item}\n" +
            "  </foreach>" +
            "and outletid=#{outletid} </script>")
    void deleteVehicleByIds(List<String> list,@Param("outletid")Integer outletid);

    @Select("<script>" +
            "select c.* from vehicle c where 1=1 " +
            "<if test='condition!=null'> and (c.code like CONCAT('%',#{condition},'%')  or c.type like CONCAT('%',#{condition},'%') or c.contacts like CONCAT('%',#{condition},'%') or c.phone like CONCAT('%',#{condition},'%') or c.company like CONCAT('%',#{condition},'%') ) </if> " +
            "<if test='type!=null'> and c.type=#{type} </if> " +
            "<if test='company!=null'> and c.company=#{company} </if> " +
            "<if test='plate_number!=null'> and c.plate_number like CONCAT('%',#{plate_number},'%') </if> " +
            "and c.outletid=#{outletid} order by c.id desc" +
            "</script>")
    List<Vehicle> getVehicleList(VehiclePageVo vehiclePageVo);


    @Select("<script>" +
            "select count(*) from vehicle c where 1=1 " +
            "<if test='condition!=null'> and (c.plate_number like CONCAT('%',#{condition},'%')  or c.type like CONCAT('%',#{condition},'%') or c.contacts like CONCAT('%',#{condition},'%') or c.phone like CONCAT('%',#{condition},'%') or c.company like CONCAT('%',#{condition},'%') ) </if> " +
            "<if test='type!=null'> and c.type=#{type} </if> " +
            "<if test='company!=null'> and c.company=#{company} </if> " +
            "<if test='plate_number!=null'> and c.plate_number like CONCAT('%',#{plate_number},'%') </if> " +
            " and c.outletid=#{outletid} order by c.id desc" +
            "</script>")
    int getVehicleListCount(VehiclePageVo vehiclePageVo);

    @Select("select distinct(company) from vehicle where outletid=#{outletid} order by id desc")
    List<String> queryCompanyList(@Param("outletid")Integer outletid);

    @Select("select distinct(type) from vehicle where outletid=#{outletid} order by id desc")
    List<String> queryVehicleTypeList(@Param("outletid")Integer outletid);

    @Select("select type as name,count(id) as value from vehicle where outletid=#{outletid} group by type")
    List<VehicleCountVO> queryVehicleCount(@Param("outletid")Integer outletid);

    @Select("select count(id) from vehicle where outletid=#{outletid}")
    Integer querySumnumber(@Param("outletid")Integer outletid);

    @Select("<script>" +
            "select v.company as company,count(DISTINCT(v.id)) as number from vehiclerecord ve  left join vehicle v on ve.plate_number=v.plate_number where 1=1 " +
            "<if test='flag ==0'> and TO_DAYS(ve.head_imagetime)=TO_DAYS(NOW()) </if>" +
            "<if test='flag ==1'> and YEARWEEK(DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d'))=YEARWEEK(NOW()) </if>" +
            "<if test='flag ==2'> and DATE_FORMAT(ve.head_imagetime,'%Y%m')=DATE_FORMAT(CURDATE(),'%Y%m') </if>" +
            "and v.outletid=#{outletid} and ve.head_imageurl is not null group by v.company" +
            "</script>")
    List<VehicleCompanyVO> queryVehicleCompanyToDayInOutCount(@Param("flag")int flag,@Param("outletid")Integer outletid);


    @Select("<script>select " +
            "'其他' AS company, " +
            "count(DISTINCT(ve.plate_number)) AS number " +
            "from  vehiclerecord ve where ve.outletid=#{outletid} and plate_number not in (select DISTINCT(vee.plate_number) from vehicle vee left join vehiclerecord v on vee.plate_number=v.plate_number) " +
            "<if test='flag ==0'> and TO_DAYS(ve.head_imagetime)=TO_DAYS(NOW()) </if>" +
            "<if test='flag ==1'> and YEARWEEK(DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d'))=YEARWEEK(NOW()) </if>" +
            "<if test='flag ==2'> and DATE_FORMAT(ve.head_imagetime,'%Y%m')=DATE_FORMAT(CURDATE(),'%Y%m') </if>" +
            "and ve.head_imageurl is not null</script>")
    List<VehicleCompanyVO> queryOtherVehicleCompanyToDayInOutCount(@Param("flag")int flag,@Param("outletid")Integer outletid);

    @Select("<script>" +
            "select v.company,count(DISTINCT(ve.uuid)) as number " +
            "from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number where 1=1 " +
            "<if test='starttime !=null'> and ve.head_imagetime &gt;=#{starttime} </if>" +
            "<if test='endtime !=null'> and ve.head_imagetime &lt;=#{endtime} </if>" +
            "and v.outletid=#{outletid} and ve.head_imageurl is not null and ve.head_imagetime is not null group by v.company" +
            "</script>")
    List<VehicleCompanyVO> queryVehicleCompanyInOutCount(@Param("starttime")String starttime, @Param("endtime")String endtime,@Param("outletid")Integer outletid);


    @Select("<script>SELECT " +
            "'外来车辆' AS company, " +
            "count(DISTINCT(ve.uuid)) AS number " +
            "FROM " +
            "vehiclerecord ve " +
            "WHERE ve.outletid=#{outletid} " +
            "AND ve.plate_number not in (select DISTINCT(vee.plate_number) from vehicle vee left join vehiclerecord v on vee.plate_number=v.plate_number) " +
            "<if test='starttime !=null'> and ve.head_imagetime &gt;=#{starttime} </if>" +
            "<if test='endtime !=null'> and ve.head_imagetime &lt;=#{endtime} </if>" +
            "AND ve.head_imageurl IS NOT NULL " +
            "AND ve.head_imagetime IS NOT NULL </script>")
    List<VehicleCompanyVO> queryOtherVehicleCompanyInOutCount(@Param("starttime")String starttime, @Param("endtime")String endtime,@Param("outletid")Integer outletid);

    @Select("<script>" +
            "select v.type as typename,count(ve.plate_number) as numbver,ve.head_imageurl as url " +
            "from  vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number where 1=1 " +
            "<if test='type==0'> and ve.type=#{type} </if>" +
            "<if test='type==1'> and ve.type=#{type} </if>" +
            "<if test='flag ==0'> and TO_DAYS(ve.head_imagetime)=TO_DAYS(NOW()) </if>" +
            "<if test='flag ==1'> and YEARWEEK(DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d'))=YEARWEEK(NOW()) </if>" +
            "<if test='flag ==2'> and DATE_FORMAT(ve.head_imagetime,'%Y%m')=DATE_FORMAT(CURDATE(),'%Y%m') </if>" +
            "group by v.type,ve.type " +
            "</script>")
    List<VehicleTypeVo> queryEntryCountInfo(@Param("type")int type,@Param("flag")int flag);



    @Select("<script>" +
            "select v.type as typename, ve.type,count(ve.plate_number) as number,ve.head_imageurl as url " +
            "from  vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number where 1=1 " +
            "<if test='str !=null'> and v.type=#{str} </if>" +
            "<if test='flag ==0'> and TO_DAYS(ve.head_imagetime)=TO_DAYS(NOW()) </if>" +
            "<if test='flag ==1'> and YEARWEEK(DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d'))=YEARWEEK(NOW()) </if>" +
            "<if test='flag ==2'> and DATE_FORMAT(ve.head_imagetime,'%Y%m')=DATE_FORMAT(CURDATE(),'%Y%m') </if>" +
            " and v.outletid=#{outletid} and ve.head_imageurl is not null group by v.type,ve.type " +
            "</script>")
    List<VehicleTypeVo> queryEntryOutList(@Param("str")String str, @Param("flag")int flag,@Param("outletid")Integer outletid);

    @Select("<script>" +
            "select '其他' as typename, ve.type,count(ve.plate_number) as number,ve.head_imageurl as url " +
            "from  vehiclerecord ve where ve.outletid=#{outletid} and ve.plate_number not in (select vee.plate_number from vehiclerecord vee left join vehicle v on vee.plate_number=v.plate_number where v.type !='未知') " +
            "<if test='flag ==0'> and TO_DAYS(ve.head_imagetime)=TO_DAYS(NOW()) </if>" +
            "<if test='flag ==1'> and YEARWEEK(DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d'))=YEARWEEK(NOW()) </if>" +
            "<if test='flag ==2'> and DATE_FORMAT(ve.head_imagetime,'%Y%m')=DATE_FORMAT(CURDATE(),'%Y%m') </if>" +
            "and ve.head_imageurl is not null group by ve.type " +
            "</script>")
    List<VehicleTypeVo> queryOtherEntryOutList(@Param("str")String str, @Param("flag")int flag,@Param("outletid")Integer outletid);



    @Select("<script>select type as name,count(*) as value from vehiclerecord where outletid=#{outletid} and DATE_FORMAT(head_imagetime,'%Y-%m-%d') =#{str} GROUP  by type</script>")
    List<VehicleCountVO> queryEveryDayInOutNumber(@Param("str")String str,@Param("outletid")Integer outletid);


//    @Select("<script>select ve.type as name,count(ve.id) as value from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number " +
//            "where 1=1 and v.outletid=#{outletid} and DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d')= #{str} and ve.head_imageurl is not null  group by ve.type</script>")
//    List<VehicleCountVO> queryEveryDayInOutNumber(@Param("str")String str,@Param("outletid")Integer outletid);


    @Select("<script>select type as name,count(id) as value from vehiclerecord where outletid=#{outletid} and id not in (select ve.id from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number where ve.outletid=#{outletid}) " +
            "and DATE_FORMAT(head_imagetime,'%Y-%m-%d')=#{str} " +
            "and head_imageurl is not null  group by type </script>")
    List<VehicleCountVO> queryOhterEveryDayInOutNumber(@Param("str")String str,@Param("outletid")Integer outletid);

    @Select("<script>" +
            "select " +
            "ve.plate_number as plate_number, " +
            "ve.type as entryouttype,  " +
            "DATE_FORMAT (ve.head_imagetime,'%Y-%m-%d %H:%i:%s') as datatime, " +
            "ve.head_imageurl as imageurl " +
            "from vehiclerecord ve  " +
            "where 1=1 and ve.outletid=#{outletid} and ve.uuid=#{uuid} and ve.type=#{entryoutype} limit 1 " +
            "</script>")
    VehicleScreenVO queryVehicleScreen(@Param("uuid")String uuid, @Param("entryoutype")String entryoutype,@Param("outletid")Integer outletid);

//    @Select("select v.plate_number as plate_number,v.type as vehicletype,ve.type as entryouttype, " +
//            "ve.head_imageurl as headimageurl, " +
//            "DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d %H:%i:%s') as headdatatime, " +
//            "ve.device_head as headsourcename, " +
//            "ve.body_imageurl as bodyimageurl, " +
//            "DATE_FORMAT(ve.body_imagetime,'%Y-%m-%d %H:%i:%s') as bodydatatime, " +
//            "ve.camera_body as bodsourceyname " +
//            "from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number " +
//            "where 1=1 and v.outletid=#{outletid} and ve.head_imageurl is not null ORDER BY ve.id desc limit 0,3 ")
//    List<VehicleScreenMessageVO> queryVehicleScreenMessage(@Param("outletid")Integer outletid);


    @Select("select ve.plate_number as plate_number,ve.type as entryouttype, " +
            "ve.head_imageurl as headimageurl, " +
            "DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d %H:%i:%s') as headdatatime, " +
            "ve.device_head as headsourcename, " +
            "ve.body_imageurl as bodyimageurl, " +
            "DATE_FORMAT(ve.body_imagetime,'%Y-%m-%d %H:%i:%s') as bodydatatime, " +
            "ve.camera_body as bodsourceyname " +
            "from vehiclerecord ve " +
            "where 1=1 and ve.outletid=#{outletid} and ve.head_imageurl is not null ORDER BY ve.id desc limit 0,3 ")
    List<VehicleScreenMessageVO> queryVehicleScreenMessage(@Param("outletid")Integer outletid);

    @Select("select id from vehicle where plate_number not like '未登记' or type not like '未知' ")
    List<String> queryVehicleList();

    @Select("<script>select \"其它\" as typename, ve2.type,count(ve2.plate_number) as number,ve2.head_imageurl as url\n" +
            "from  vehiclerecord ve2 where 1=1 and  ve2.outletid=#{outletid} \n" +
            "and ve2.id not in (select ve.id from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number \n" +
            "where 1=1 and v.outletid=#{outletid} group by ve.uuid order by ve.id desc)group by ve2.type order by ve2.id desc \n</script>")
    List<VehicleTypeVo> queryEntryOutListOther(int flag, Integer outletid);

    @Select("<script>" +
            "select " +
            "ve.plate_number as plate_number,  " +
            "ve.type as entryouttype,  " +
            "DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d %H:%i:%s') as datatime,  " +
            "ve.head_imageurl as imageurl  " +
            "from vehiclerecord ve " +
            "where 1=1 and ve.outletid=#{outletId} and ve.head_imageurl is not null ORDER BY ve.id desc limit 1 " +
            "</script>")
    VehicleScreenVO queryNewVehicleScreenImage(@Param("monitorId")Integer monitorId, @Param("outletId")Integer outletId);

    /**
     *
     * @param plate_number
     * @return
     */
    @Select("select type from vehicle where plate_number=#{plate_number} and outletid=#{outletId}")
    String queryVehicleType(@Param("plate_number")String plate_number,@Param("outletId")Integer outletId);

    @Select("select * from vehicle where plate_number=#{plate_number} and outletid=#{outletId}")
    Vehicle queryVehiclePlateNumber(@Param("outletId")Integer outletId, @Param("plate_number")String plate_number);


    @Select("select ve.plate_number as plate_number,ve.type as entryouttype, " +
            "ve.head_imageurl as headimageurl, " +
            "DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d %H:%i:%s') as headdatatime, " +
            "ve.device_head as headsourcename, " +
            "ve.body_imageurl as bodyimageurl, " +
            "DATE_FORMAT(ve.body_imagetime,'%Y-%m-%d %H:%i:%s') as bodydatatime, " +
            "ve.camera_body as bodsourceyname " +
            "from vehiclerecord ve left join vehicle v on ve.plate_number=v.plate_number " +
            "where 1=1 and ve.outletid=#{outletid} and v.type=#{type} and ve.head_imageurl is not null ORDER BY ve.id desc limit 0,3 ")
    List<VehicleScreenMessageVO> queryVehicleScreenMessageType(@Param("outletid")Integer outletid ,@Param("type") String type  );


    @Select("select distinct(type) from vehicle where outletid=#{outletid} limit 2")
    List<String> queryVehicleTypeListTop3(@Param("outletid")Integer outletid);


    @Select("select ve.plate_number as plate_number,ve.type as entryouttype, " +
            "ve.head_imageurl as headimageurl, " +
            "DATE_FORMAT(ve.head_imagetime,'%Y-%m-%d %H:%i:%s') as headdatatime, " +
            "ve.device_head as headsourcename, " +
            "ve.body_imageurl as bodyimageurl, " +
            "DATE_FORMAT(ve.body_imagetime,'%Y-%m-%d %H:%i:%s') as bodydatatime, " +
            "ve.camera_body as bodsourceyname " +
            "from vehiclerecord ve " +
            "where ve.plate_number not in(select v.plate_number from vehicle v where v.outletid=#{outletid}) and ve.head_imageurl is not null ORDER BY ve.id desc limit 0,3 ")
    List<VehicleScreenMessageVO> queryUnknownVehicleScreenMessage(@Param("outletid")Integer outletid);


    @Select("select plate_number from vehicle where company=#{str}")
    List<String> queryVehcileByCompany(@Param("str")String str);

    @Select("select DISTINCT(ve.plate_number) from vehiclerecord ve where ve.plate_number not in (select DISTINCT(plate_number) from vehicle) ")
    List<String> queryWDJVehcileByCompany(@Param("str")String str);
}
