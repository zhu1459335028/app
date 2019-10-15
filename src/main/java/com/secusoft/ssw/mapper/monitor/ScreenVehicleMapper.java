package com.secusoft.ssw.mapper.monitor;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScreenVehicleMapper {


    @Select("select vehicletype from screenvehicle where outletid=#{outletid} limit 2 ")
    List<String> queryScreenVehicleType(@Param("outletid") Integer outletid);


    @Select("select vehicletype from screenvehicle where outletid=#{outletid} and vehicletype=#{str} ")
    List<String> queryScreenVehicleTypeList(@Param("outletid") int outletid,@Param("str") String str);


    @Insert("<script>insert into screenvehicle " +
            "(vehicletype,outletid) " +
            "values " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item}" +
            ",#{outletid})</foreach></script>")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    void saveScreenVehicleType(@Param("outletid") int outletid, @Param("list") List<String> list);


    @Select("<script>SELECT " +
            "IFNULL(count(*),0) as num " +
            "FROM " +
            "vehiclerecord ve " +
            "LEFT JOIN vehicle v ON ve.plate_number = v.plate_number " +
            "WHERE " +
            "to_days(ve.head_imagetime) = to_days(now()) " +
            "<if test=\"str != null and str !=''\"> and v.type=#{str} </if> " +
            "and ve.type=#{entryouttype} "+
            "and ve.head_imagetime is not null " +
            "and ve.outletid=#{outletid} "+
            "group by ve.type </script>")
    Integer queryScreenVehicleTypeInOutCountToDay(@Param("outletid") int outletid, @Param("str") String str,@Param("entryouttype") int entryouttype);

    @Delete("delete from screenvehicle")
    void deleteAll();


    @Select("<script>" +
            "select count(*) " +
            "FROM vehiclerecord ve " +
            "WHERE " +
            "to_days(ve.head_imagetime) = to_days(now()) " +
            "and ve.head_imagetime is not null " +
            "and ve.type=#{entryouttype} "+
            "and ve.outletid=#{outletid} "+
            "and ve.plate_number not in (SELECT plate_number from vehicle where outletid=#{outletid}) </script>")
    Integer queryScreenUnknownVehicleTypeInOutCountToDay(@Param("outletid") int outletid, @Param("str") String str,@Param("entryouttype") int entryouttype);



    @Select("<script>SELECT " +
            "IFNULL(count(*),0) as num " +
            "FROM " +
            "vehiclerecord ve " +
            "WHERE " +
            "to_days(ve.head_imagetime) = to_days(now()) " +
            "and ve.type=#{entryouttype} "+
            "and ve.outletid=#{outletid} "+
            "and ve.head_imagetime is not null " +
            " </script>")
    Integer queryAllVehicleTypeInOutCountToDay(@Param("outletid") int outletid, @Param("entryouttype") int entryouttype);

    @Select("<script>SELECT " +
            "IFNULL(count(*),0) as num " +
            "FROM " +
            "vehiclerecord ve " +
            "WHERE " +
            "to_days(ve.inserttime) = to_days(now()) " +
            "and ve.patch = 2 " +
            "and ve.outletid=#{outletid} "+
            "and ve.type = 0 "+
            "and ve.head_imagetime is null " +
            " </script>")
    Integer queryAllLeakySum(@Param("outletid") int outletid);
    @Select("<script>" +
            "select count(*) " +
            "FROM vehiclerecord ve " +
            "WHERE " +
            "to_days(ve.inserttime) = to_days(now()) " +
            "and ve.patch = 2 " +
            "and ve.head_imagetime is null " +
            "and ve.type= 0 "+
            "and ve.outletid=#{outletid} "+
            "and ve.plate_number not in (SELECT plate_number from vehicle where outletid=#{outletid}) </script>")
    Integer queryScreenUnknownVehicleTypeLeakySum(@Param("outletid") int outletid, @Param("str") String str);
    @Select("<script>SELECT " +
            "IFNULL(count(*),0) as num " +
            "FROM " +
            "vehiclerecord ve " +
            "LEFT JOIN vehicle v ON ve.plate_number = v.plate_number " +
            "WHERE " +
            "to_days(ve.inserttime) = to_days(now()) " +
            "and  ve.patch = 2 " +
            "<if test=\"str != null and str !=''\"> and v.type=#{str} </if> " +
            "and ve.type=0 "+
            "and ve.head_imagetime is null " +
            "and ve.outletid=#{outletid} </script>")
    Integer queryScreenUnknownVehicleLeakySum(@Param("outletid") int outletid, @Param("str") String str);
}
