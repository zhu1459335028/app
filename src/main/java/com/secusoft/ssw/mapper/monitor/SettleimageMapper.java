package com.secusoft.ssw.mapper.monitor;


import com.secusoft.ssw.model.monitor.*;
import com.secusoft.ssw.model.viewobject.Request.SettleimageVO;
import com.secusoft.ssw.model.viewobject.Request.SettleimageunitmonVO;
import com.secusoft.ssw.model.viewobject.Response.SettleunitmonLHVO;
import com.secusoft.ssw.model.viewobject.Response.ThresholdvalueRspVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SettleimageMapper {

    @Insert("insert into settleimage (imgurl,code,name,outletid) values (#{vo.imgurl},#{vo.code},#{vo.name},#{outletid}) ")
    void saveSettleImageInfo(@Param("outletid")Integer outletid, @Param("vo")SettleimageVO vo);

    /**
     * settleimage
     * @param outletid
     * @return
     */
    @Select("select * from settleimage where outletid=#{outletid} order by id desc ")
    List<Settleimage> querySettleImageInfo(@Param("outletid")Integer outletid);

    @Select("select * from settleimage where id=#{id} and outletid=#{outletid} limit 1 ")
    Settleimage querySettleImageInfoById(@Param("outletid")Integer outletid, @Param("id")Integer id);

    /**
     * 图纸与监测点关联表
     * @param settleimageid
     * @return
     */
    @Select("select * from settleimageunitmon where settleimageid=#{settleimageid} and outletid=#{outletid} ")
    List<Settleimageunitmon> querySettleimageunitmonbySettleimageid(@Param("settleimageid")Integer settleimageid,@Param("outletid")Integer outletid);

    /**
     * 监测点类型及对应类型编号
     * @param id
     * @return
     */
    @Select("select * from settleunitmon where id=#{id} and outletid=#{outletid} ")
    Settleunitmon querySettleunitmon(@Param("id")Integer id,@Param("outletid")Integer outletid);


    /**
     * 阈值条目与监测点类型关联表
     * @param settleunitmonid
     * @return
     */
    @Select("select * from thresholdvalue where settleunitmonid=#{settleunitmonid} and outletid=#{outletid} ")
    List<Thresholdvalue> queryThresholdvalue(@Param("settleunitmonid")Integer settleunitmonid,@Param("outletid")Integer outletid);

    /**
     * 阈值条目名称
     * @param id
     * @return
     */
    @Select("select * from thresholdname where id=#{id} and outletid=#{outletid} ")
    ThresholdName queryThresholdName(@Param("id")Integer id,@Param("outletid")Integer outletid);

    @Select("select s1.*,s.settleunitmonid as settleunitmonid from settleimageunitmon s left join settleunitmon s1 on s.settleunitmonid=s1.id where s.settleimageid=#{id} and s.outletid=#{outletid} group by s1.name ")
    List<SettleunitmonLHVO> queryThisImageSettleunitmon(@Param("id")Integer id, @Param("outletid")Integer outletid);


    @Select("select * from settleimageunitmon where settleunitmonid=#{id} and settleimageid=#{imageid} and outletid=#{outletid} ")
    List<Settleimageunitmon> querySettleimageunitmonbySettleunitmonid(@Param("id")Integer id,@Param("imageid")Integer imageid,@Param("outletid")Integer outletid);

    @Select("select * from settlement where location_no=#{locationno} and outletid=#{outletid} order by id desc limit 1 ")
    Settlement querySettleimageById(@Param("locationno")String locationno,@Param("outletid")Integer outletid);

    @Select("select * from settlement where location_no=#{locationno} and outletid=#{outletid} order by id desc limit 3 ")
    List<Settlement> querySettleimageByIdLimit3(@Param("locationno")String locationno,@Param("outletid")Integer outletid);

    @Select("select tv.*,tn.name as name,tn.type as type from thresholdvalue tv left join thresholdname tn on tv.thrnameid=tn.id where tv.settleunitmonid=#{id} and tv.outletid=#{outletid} order by tv.settleunitmonid asc ")
    List<ThresholdvalueRspVO> queryThresholdvalueListBysettleunitmonid(@Param("id")Integer id, @Param("outletid")Integer outletid);

    @Select("select * from\n" +
            "(select * from settlement where location_no=#{locationno} and outletid=#{outletid}  order by dt desc limit 7 ) t\n" +
            "order by t.dt asc ")
    List<Settlement> querySettlementBylLcationno(@Param("locationno")String locationno, @Param("outletid")Integer outletid);

    @Select("select * from settleimageunitmon where parentlocationno=#{parentlocationno} and outletid=#{outletid} ")
    List<Settleimageunitmon> querySettleimageunitmonByParentlocationno(@Param("parentlocationno")String parentlocationno, @Param("outletid")Integer outletid);

    @Insert("insert into settleimageunitmon (x,y,settleimageid,settleunitmonid,locationno,outletid) value(#{vo.x},#{vo.y},#{vo.settleimageid},#{vo.settleunitmonid},#{vo.locationno},#{outletid}) ")
    void addsettleunitmon(@Param("outletid")Integer outletid, @Param("vo")SettleimageunitmonVO vo);


    @Select("select * from settleimageunitmon where locationno=#{locationno} and outletid=#{outletid} limit 1 ")
    Settleimageunitmon querySettleimageunitmonbyLocationno(@Param("outletid")Integer outletid,@Param("locationno")String locationno);

    @Insert("insert into settleimageunitmon (x,y,settleimageid,settleunitmonid,locationno,outletid,parentlocationno) value(#{vo.x},#{vo.y},#{vo.settleimageid},#{vo.settleunitmonid},#{vo.locationno},#{outletid},#{uuid}) ")
    void addsettleunitmonuuid(@Param("outletid")Integer outletid, @Param("vo")SettleimageunitmonVO vo, @Param("uuid")String uuid);


    @Select("select count(*) from settleimageunitmon where locationno=#{locationno} and outletid=#{outletid} limit 1 ")
    Integer querySettleimageunitmonbyLocationnoCount(@Param("outletid")Integer outletid,@Param("locationno")String locationno);



    @Select("select id from settleimageunitmon where locationno=#{locationno} and outletid=#{outletid} limit 1 ")
    Integer querySettleimageunitmonIdbyLocationno(@Param("outletid")Integer outletid,@Param("locationno")String locationno);


    @Select("select * from thresholdvaluepoint where location_no=#{locationno} and settleunitmonid=#{settleunitmonid} and thrnameid=#{thrnameid} and outletid=#{outletid} limit 1  ")
    Thresholdvaluepoint queryThresholdvaluepointByLocationnoAndSettleunitmonid(@Param("locationno")String locationno,@Param("settleunitmonid")Integer settleunitmonid,@Param("thrnameid")Integer thrnameid,@Param("outletid")Integer outletid);

    @Select("select locationno from settleimageunitmon where id=#{id} and outletid=#{outletid} ")
    String queryPointLocationno(@Param("id")Integer id, @Param("outletid")Integer outletid);
}
