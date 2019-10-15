package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.dto.CheckingDTO;
import com.secusoft.ssw.model.monitor.FoundInOutVo;
import com.secusoft.ssw.model.viewobject.Response.FoundaperVO;
import com.secusoft.ssw.model.viewobject.Response.LedDisplayResVo;
import com.secusoft.ssw.model.viewobject.Response.LedDisplayResVoTwo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Mapper
public interface CheckingMapper {

	@Select("select c.imageUrl from ${tableName} c where c.checkId=#{id} and c.outletid=#{outletid}")
	String selectImageUrlById(@Param("tableName") String tableName, @Param("id") int id,@Param("outletid") Integer outletid);

	@Select("SELECT c.*,d.type as devicetype FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where e.id=#{employeeid} and d.outletid=#{outletid} and d.type in (1,2) order by c.time desc limit 1")
	CheckingDTO selectLastTimeByEmployeeId(@Param("tableName") String tableName, @Param("employeeid") String employeeid,@Param("outletid") Integer outletid);

	@Select("SELECT c.*,d.type as devicetype FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where e.id=#{employeeid} and d.outletid=#{outletid} and d.type in (1,2) and date(c.time)=curdate() order by c.time asc")
	List<CheckingDTO> selectTodayCheckingByEmployeeId(@Param("tableName") String tableName, @Param("employeeid") String employeeid,@Param("outletid") Integer outletid);

	@Select("SELECT c.*,d.type as devicetype FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where e.id=#{employeeid} and d.outletid=#{outletid} and d.type in (1,2) order by c.time asc")
	List<CheckingDTO> selectCheckingByEmployeeId(@Param("tableName") String tableName, @Param("employeeid") String employeeid,@Param("outletid") Integer outletid);

	/**
	 * 获取当前基坑打卡信息
	 * 该工地下基坑每人当天最新打卡信息
	 * @param tableName
	 * @param outletid
	 * @return
	 */
	@Select("SELECT max(c.time),e.id as employeeId,d.type as devicetype FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where e.outletid=#{outletid} and date(c.time) = curdate() and d.type in (3,4) group by e.id")
	List<CheckingDTO> selectEmployeeToDayChecking(@Param("tableName") String tableName, @Param("outletid") int outletid);

	/**
	 * 获取该工地下特定岗位7天内的在岗信息
	 * @param tableName
	 * @param outletid
	 * @return
	 */
	@Select("SELECT c.time,d.type as devicetype FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where e.outletid=#{outletid} and e.jobid=#{jobid} and d.type in (1,2) " +
			"and DATE_SUB(CURDATE(), INTERVAL 7 DAY) < date(c.time) order by c.time")
	List<CheckingDTO> selectCheckingsByOutletId(@Param("tableName") String tableName,
									   @Param("outletid") int outletid,
									   @Param("jobid") String jobid);

	/**
	 * 获取特定日期入坑或出坑的次数
	 * @param tableName
	 * @param outletid
	 * @return
	 */
	@Select("select count(1) from (SELECT count(e.id) FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where e.outletid=#{outletid} and date(c.time) = date(#{day}) and d.type=#{type} group by e.id) as t")
	Integer countEmployeeByOutletIdAndTimeAndType(@Param("tableName") String tableName,
								@Param("outletid") int outletid,
								@Param("day") Date day,
								@Param("type") int type);

	@Select("<script> SELECT c.*,d.type as devicetype FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where d.type in (1,2) and e.outletid=#{outletid} and e.id=#{employeeId} " +
			"<if test='startDate!=null'> and c.time &gt;=#{startDate} </if> " +
			"<if test='endDate!=null'> and c.time &lt;=#{endDate} </if> " +
			"order by c.time </script>")
	List<CheckingDTO> selectCheckingsByEmployeeId(@Param("tableName") String tableName,
												@Param("employeeId") String employeeId,
												  @Param("startDate") Date startDate,
												  @Param("endDate") Date endDate,
												  @Param("outletid") int outletid);

	/**
	 * 获取工地该部门下当前最新打卡信息
	 * @param tableName
	 * @param outletid
	 * @return
	 */
	@Select("SELECT max(c.time) as time,e.id as employeeid,d.type as devicetype FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where e.outletid=#{outletid} and e.departmentid=#{departmentid} " +
			"and date(c.time) = curdate() and d.type in (1,2) group by e.id")
	List<CheckingDTO> selectEmployeeToDayCheckingByDepartmentId(@Param("tableName") String tableName,
																@Param("outletid") int outletid,
																@Param("departmentid") String departmentid);

	/**
	 * 获取当天打卡入场或出场的总人数
	 */
//	@Select("select count(1) from (SELECT count(e.id) FROM ${tableName} c " +
//			"inner join accessDevice d on d.accessDeviceId = c.deviceId " +
//			"inner join employee e on e.cardId = c.cardId " +
//			"where e.outletId=#{outletId} and date(c.time) = curDate() and d.type=#{type} group by e.id) as t")
//	Integer countByOutletIdAndType(@Param("tableName") String tableName,
//												  @Param("outletId") int outletId,
//												  @Param("type") int type);

	@Select("select count(1) from (SELECT count(e.id) FROM ${tableName} c " +
			"inner join accessDevice d on d.accessDeviceId = c.deviceId " +
			"inner join employee e on e.cardId = c.cardId " +
			"where e.outletid=#{outletid} and date(c.time) = curDate() and d.type=#{type} group by e.id) as t")
	Integer countByOutletIdAndType(@Param("tableName") String tableName,
								   @Param("outletid") int outletId,
								   @Param("type") int type);

	@Select("select c.* from ${tableName} c left join accessdevice a on c.deviceid=a.accessdeviceid where a.type in(1,2) and a.outletid=#{outletid}  order by c.checkid desc limit 1 ")
	CheckingDTO selectLastChecking(@Param("tableName") String tableName,@Param("outletid") int outletid);


	/**
	 * 查询当前时间基坑进出人数  3.基坑入口，4.基坑出口',
	 * @param tableName
	 * @param outletid
	 * @param day
	 * @param type
	 * @return
	 */
	@Select("select count(1) from (SELECT count(e.id) FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where d.id=#{id} and e.outletid=#{outletid} and date(c.time) = date(#{day}) and d.type=#{type} group by e.id) as t")
	Integer countEmployeeByOutletIdAndTimeAndTypeAndid(@Param("tableName") String tableName,
												  @Param("outletid") int outletid,
												  @Param("day") Date day,
												  @Param("type") int type,
													@Param("type") Integer id);



	@Select("SELECT c.cardid FROM ${tableName} c " +
			"inner join accessdevice d on d.accessdeviceid = c.deviceid " +
			"inner join employee e on e.cardid = c.cardid " +
			"where e.outletid=#{outletid} and date(c.time) = date(#{day}) and d.type=#{type} group by e.id ")
	List<String> queryCardidsByEmployeeByOutletIdAndTimeAndType(@Param("tableName") String tableName,
												  @Param("outletid") int outletid,
												  @Param("day") Date day,
												  @Param("type") int type);

	@Select("<script>select e.id,e.name as empname,d.name as deptname,e.phone from employee e left join department d on e.departmentid=d.id " +
			"where e.cardid in " +
			"  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
			"    separator=\",\" close=\")\">\n" +
			"            #{item}\n" +
			"  </foreach>" +
			"</script>")
	List<FoundaperVO> queryListFoundPerVOBylist(@Param("list")List<String> list);

	@Select("select c.imageurl,d.type from ${tableName} c inner join accessdevice d on d.accessdeviceid = c.deviceid where d.type in (3,4) and d.outletid=#{outletid} order by c.checkid desc limit 1 ")
	FoundInOutVo queryNewDataUrlAndType(@Param("tableName") String tableName, @Param("outletid") int outletid);



	@Select("<script>select d.name as deptname,count(e.cardid) as number from employee e left join department d on e.departmentid=d.id " +
			"where e.cardid in " +
			"  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
			"    separator=\",\" close=\")\">\n" +
			"            #{item}\n" +
			"  </foreach>" +
			" group by d.name "+
			"</script>")
	List<LedDisplayResVo> queryLedDisplayCount(@Param("list")List<String> list);

	@Select("select c.imageurl from ${tableName} c inner join accessdevice d on d.accessdeviceid = c.deviceid where c.cardid=#{cardid} and d.type=#{type} order by c.checkid desc limit 1 ")
	String queryZxImageUrl(@Param("tableName") String tableName, @Param("cardid")String cardid, @Param("type")String type);


	@Select("select c.imageurl from ${tableName} c inner join accessdevice d on d.accessdeviceid = c.deviceid where d.type in(3,4) order by c.checkid desc limit 1 ")
	String queryZxImageUrlInit(@Param("tableName") String tableName);

	@Select("<script>select d.id,d.name as deptname,0 as number from department d where d.outletid=#{outletid} " +
			"and d.id not in " +
			"  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
			"    separator=\",\" close=\")\">\n" +
			"            #{item}\n" +
			"  </foreach> " +
			"and d.name is not null; </script>")
	Set<LedDisplayResVoTwo> queryLedDisplayCountAll(@Param("outletid") int outletid,@Param("list")List<String> list);


	@Select("<script>select d.id,d.name as deptname,count(e.cardid) as number from employee e left join department d on e.departmentid=d.id " +
			"where d.outletid=#{outletid} and e.cardid in " +
			"  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
			"    separator=\",\" close=\")\">\n" +
			"            #{item}\n" +
			"  </foreach> " +
			" group by d.name "+
			"</script>")
	Set<LedDisplayResVoTwo> queryFoundLedDisplayCount(@Param("outletid") int outletid,@Param("list")List<String> list);

	@Select("select count(*) from foundpernum where outletid=#{outletid}")
	Integer queryFoundSumPerNum(@Param("outletid") int outletid);
}
