package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Realtimereporthistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface RealTimeReportHistoryMapper {

	@Select("SELECT * FROM realtimereporthistory where reportid=#{reportid} and outletid=#{outletid}")
	Realtimereporthistory selectReportByReportId(@Param("reportid") String reportid,@Param("outletid") int outletid);

	@Select("SELECT * FROM realtimereporthistory where outletid=#{outletid} and date(createtime) = curdate() order by createtime desc limit 1")
	Realtimereporthistory selectReportByOutletIdLimit(@Param("outletid") int outletid);

	@Select("SELECT id,reportid,reporturl,outletid,outletname,deviceno,devicename,camerano,cameraname,itemid,itemname,createtime,comment,actiontime,brandid,concat(#{reportBaseUrl},majorpic) as majorpic FROM realtimereporthistory where outletid=#{outletid} and date(createtime) = curdate() order by createtime desc")
	List<Realtimereporthistory> selectReportByOutletId(@Param("outletid") int outletid,@Param("reportBaseUrl") String reportBaseUrl);

	@Select("SELECT count(1) FROM realtimereporthistory where outletid=#{outletid} and date(createtime)=curdate()")
	Integer countTodayReportByOutletId(@Param("outletid") int outletid);
	@Select("<script> SELECT * FROM realtimereporthistory where outletid=#{outletid} " +
			"<if test='startDate!=null'> and createtime &gt;=#{startDate} </if> " +
			"<if test='endDate!=null'> and createtime &lt;=#{endDate} </if> " +
			"order by createtime desc limit 100</script>")
	List<Realtimereporthistory> selectReportByOutletidAndDate(@Param("outletid") int outletid,
															  @Param("startDate") String startDate,
															  @Param("endDate") String endDate);

	/**
	 * 获取最近7天内特定检查项的报告数量
	 * @param outletid
	 * @param itemid
	 * @return
	 */
	@Select("SELECT count(1) FROM realtimereporthistory " +
			"where outletid=#{outletid} and itemid=#{itemid} and DATE_SUB(CURDATE(), INTERVAL 7 DAY) < date(createtime)")
	int countReportByOutletidAndItemId(@Param("outletid") int outletid,@Param("itemid") int itemid);

	/**
	 * 获取特定日期特定检查项的报告数量
	 * @param outletid
	 * @param itemid
	 * @return
	 */
	@Select("SELECT count(1) FROM realtimereporthistory " +
			"where outletid=#{outletid} and itemid=#{itemid} and date(createtime)=date(#{day})")
	int countReportByOutletidAndItemIdAndDay(@Param("outletid") int outletid,
									   @Param("itemid") int itemid,
									   @Param("day") Date day);
	/**
	 * 获取特定日期特定检查项的报告数量
	 * @param outletid
	 * @param itemid
	 * @return
	 */
	@Select("SELECT count(1) FROM realtimereporthistory " +
			"where outletid=#{outletid} and itemid in(#{itemid},#{itemid1}) and DATE_FORMAT(createtime,'%Y-%m-%d')=#{day}")
	int countReportByOutletidAndItemIdAndDay1(@Param("outletid") int outletid,
											  int itemid,
											  int itemid1,
											 @Param("day") String day);
	@Select("SELECT itemid from realtimereporthistory WHERE outletid =#{outletid} ORDER BY id DESC LIMIT 1")
	Integer selectItemidBuOutletId(@Param("outletid") int outletid);
}
