package com.secusoft.ssw.mapper.monitor;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FoundpernumMapper {
    
    @Delete("delete from foundpernum where cardid=#{cardid} and outletid=#{outletid}")
    void delete(@Param("cardid") String cardid, @Param("outletid") Integer outletid);


    @Insert("INSERT INTO foundpernum (cardid, outletid) VALUES(#{cardid}, #{outletid})")
    void save(@Param("cardid") String cardid, @Param("outletid") Integer outletid);

    @Select("select cardid from foundpernum where outletid=#{outletid} ")
    List<String> queryCardidList(@Param("outletid") Integer outletid);


    @Select("select count(cardid) from foundpernum where outletid=#{outletid} ")
    Integer queryFoundPersonSum(@Param("outletid") Integer outletid);
}
