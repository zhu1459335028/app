package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Threshold;

import com.secusoft.ssw.model.monitor.Thresholdvertocal;
import com.secusoft.ssw.model.viewobject.Response.ThresholdRespVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ThresholdMapper {

    @Select("SELECT * FROM threshold WHERE type=#{thresholdType} AND outletid=#{outletid} ORDER BY id DESC LIMIT 1")
    Threshold selectThresholdByType(@Param("outletid") Integer outletid, @Param("thresholdType") String thresholdType);

    @Select("SELECT * FROM threshold WHERE id=#{id} AND outletid=#{outletid} LIMIT 1")
    Threshold selectThresholdById(@Param("outletid") Integer outletid, @Param("id") Integer id);

    @Insert("<script>INSERT INTO threshold (type, max_val, min_val, identical_val, outletid) VALUES (#{type}, #{max_val}, #{min_val}, #{identical_val}, #{outletid})</script>")
    void insertThreshold(Threshold threshold);
    
    @Update("UPDATE threshold SET max_val=#{max_val}, min_val=#{min_val}, identical_val=#{identical_val} WHERE type=#{type} AND outletid=#{outletid}")
    int updateThreshold(Threshold threshold);

    @Select("select max(typesub) from threshold where type='water_level' AND outletid=#{outletid} \n")
    Integer queryMaxSubType(@Param("outletid") Integer outletid);

    @Insert("<script>INSERT INTO threshold (type, max_val, min_val, identical_val, outletid, starttime, endtime, typesub) VALUES (#{type}, #{max_val}, #{min_val}, #{identical_val}, #{outletid}, #{starttimestr}, #{endtimestr}, #{typesub})</script>")
    void insertWaterThreshold(Threshold threshold);

    @Update("UPDATE threshold SET max_val=#{max_val}, min_val=#{min_val}, identical_val=#{identical_val}, starttime=#{starttime}, endtime=#{endtime}, typesub=#{typesub} WHERE id=#{id} AND outletid=#{outletid}")
    void updateWaterThreshold(Threshold threshold);

    @Select("select id as id ,type as type,max_val as max_val,min_val as min_val,identical_val as identical_val,DATE_FORMAT(starttime,'%Y-%m-%d %H:%i:%s') as starttimestr,DATE_FORMAT(endtime,'%Y-%m-%d %H:%i:%s') as endtimestr from threshold where type='water_level' and outletid=#{currentOutletId} order by typesub desc \n ")
    List<ThresholdRespVO> queryWaterThresholdList(@Param("currentOutletId")Integer currentOutletId);


    @Select("<script>SELECT * FROM threshold WHERE type='water_level' and outletid=#{outletid} and #{str} &gt;= starttime and #{str} &lt;= endtime limit 1 </script>")
    Threshold queryWaterThreshold(@Param("outletid") Integer outletid, @Param("str") String str);

    @Delete("<script>" +
            "delete from threshold where id in " +
            "  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
            "    separator=\",\" close=\")\">\n" +
            "            #{item}\n" +
            "  </foreach>" +
            "and outletid=#{outletid} </script>")
    void deleteThreshold(List<String> list,@Param("outletid") Integer outletid);


    @Delete("delete from threshold where type=#{water_level} and outletid=#{outletid}")
    void delete(@Param("water_level") String water_level,@Param("outletid") Integer outletid);


    @Select("<script>SELECT * FROM threshold WHERE type='water_level' and outletid=#{outletid} and #{str} &gt;= starttime and #{str} &lt;= endtime  </script>")
    List<Threshold> queryWaterThresholdLists(@Param("outletid") Integer outletid, @Param("str") String str);
    @Select("<script>SELECT * FROM threshold WHERE type='level' and outletid=#{outletid} and #{str} &gt;= starttime and #{str} &lt;= endtime limit 1 </script>")
    Threshold queryLevelThreshold(@Param("outletid") Integer outletid, @Param("str") String str);
    @Select("<script>SELECT * FROM threshold WHERE type='vertical' and outletid=#{outletid} and #{str} &gt;= starttime and #{str} &lt;= endtime limit 1 </script>")
    Threshold queryVerticalThreshold(@Param("outletid") Integer outletid, @Param("str") String str);
    @Insert("<script>INSERT INTO thresholdvertocal (level, vertical, outletid) VALUES (#{level}, #{vertical}, #{outletid})</script>")
    void saveLevelAndVertical(@Param("level") String level,@Param("vertical") String vertical,@Param("outletid") Integer outletid);
    @Select("SELECT * FROM thresholdvertocal WHERE outletid=#{outletid} ORDER BY id DESC LIMIT 1;")
    Thresholdvertocal queryLevelAndVertical(@Param("outletid") Integer outletid);

    @Delete("delete from threshold where id=#{id} and outletid=#{outletid}")
    void deleteThresholdLevel(@Param("outletid") Integer outletid, @Param("id") Integer id);
    @Select("<script>SELECT * FROM threshold WHERE type='level' and outletid=#{outletid} ORDER BY id DESC limit 1 </script>")
    Threshold queryLevel(Integer currentOutletId);
    @Select("<script>SELECT * FROM threshold WHERE type='vertical' and outletid=#{outletid} ORDER BY id DESC limit 1 </script>")
    Threshold queryVertical(Integer currentOutletId);
}