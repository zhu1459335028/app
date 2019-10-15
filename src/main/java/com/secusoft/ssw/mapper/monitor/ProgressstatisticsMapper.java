package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Progressstatistics;
import com.secusoft.ssw.model.viewobject.Response.MilesEveryDayVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 推进表
 */
@Mapper
public interface ProgressstatisticsMapper {
    @Select("select * from progressstatistics order by id desc limit 1 " )
    Progressstatistics queryMaxRecord(Integer outletid);



    @Insert("INSERT INTO `progressstatistics`(`currentnumber`, `currentmileage`, `everynumber`, `date`, `outletid`, `shieldid`,historynumber )  " +
            "VALUES ( #{currentnumber}, #{currentmileage}, #{everynumber}, #{date}, #{outletid}, #{shieldid},#{historynumber}); ")
    void save(Progressstatistics p);

    @Update("update progressstatistics set currentnumber=#{currentnumber},currentmileage=#{currentmileage},everynumber=#{everynumber},historynumber=#{historynumber}  where id=#{id} and outletid=#{outletid} ")
    void update(@Param("outletid") float outletid, @Param("currentnumber")float currentnumber, @Param("currentmileage")float currentmileage, @Param("everynumber")float everynumber, @Param("id")Integer id, @Param("historynumber") float historynumber);


    @Select("select * from progressstatistics where shieldid=#{shieldid} and outletid=#{outletid} order by id desc limit 1 " )
    Progressstatistics queryProgressByShieldByid(@Param("shieldid") Integer shieldid,@Param("outletid") Integer outletid);


    @Select("select * from progressstatistics p where p.shieldid=#{shieldid} and p.outletid=#{outletid} and DATE_FORMAT(p.date,'%Y-%m-%d')=#{str} limit 1 ")
    Progressstatistics queryByidanddate(@Param("shieldid") Integer shieldid,@Param("str") String str,@Param("outletid") Integer outletid);

    @Select("select DATE_FORMAT(date,'%Y-%m-%d') as date,historynumber as number from progressstatistics where shieldid=#{shield} and outletid=#{outletid} order by id desc  ")
    List<MilesEveryDayVO> queryMailesEveryDayNumber(@Param("shield") Integer shield,@Param("outletid") Integer outletid);


    @Select("select IFNULL(everynumber,0) from progressstatistics p where p.shieldid=#{shield} and p.outletid=#{outletid} and DATE_FORMAT(p.date,'%Y-%m-%d')!=#{nowdate} order by id desc limit 1\n ")
    Integer queryYesterdaynumber(@Param("nowdate") String nowdate, @Param("shield") Integer shield,@Param("outletid") Integer outletid);
}
