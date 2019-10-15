package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.PlayGroup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PlayGroupMapper {

	@Select("SELECT * FROM PlayGroup where userid = #{userid} and outletid = #{outletid}")
	PlayGroup selectPlayGroupByUserIdAndOutletId(@Param("userid") int userid,@Param("outletid") int outletid);

	@Insert("insert into PlayGroup (userid,outletid,grouptype,groupinfo) " +
			"values (#{userid},#{outletid},#{grouptype},#{groupinfo})")
	void insertPlayGroup(PlayGroup playGroup);

	@Insert("update PlayGroup set userid=#{userid},outletid=#{outletid},grouptype=#{grouptype},groupinfo=#{groupinfo} " +
			"where groupid=#{groupid} and outletid = #{outletid}")
	void updatePlayGroup(PlayGroup playGroup);

}
