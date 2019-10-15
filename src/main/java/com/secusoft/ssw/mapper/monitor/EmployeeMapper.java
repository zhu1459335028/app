package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Employee;
import com.secusoft.ssw.model.monitor.EmployeeInfo;
import com.secusoft.ssw.model.viewobject.Request.EmployeeVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeListInfoVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

	@Select("SELECT * FROM employee where jobid=#{jobid} and outletid=#{outletid}")
	List<Employee> selectEmployeeByJobIdAndOutletId(@Param("jobid") String jobid,@Param("outletid") int outletid);

	@Select("select count(1) FROM employee e " +
			"inner join department d on d.id=e.departmentid " +
			"where e.outletid=#{outletid} and d.path like CONCAT('%\\_',#{departmentid},'\\_%') ")
	int countEmployeeByDepartmentId(@Param("departmentid") String departmentid,@Param("outletid") int outletid);

	@Select("select * FROM employee e " +
			"inner join department d on d.id=e.departmentid " +
			"where e.outletid=#{outletid} and d.path like CONCAT('%\\_',#{departmentid},'\\_%') ")
	List<Employee> selectEmployeesByDepartmentId(@Param("departmentid") String departmentid,@Param("outletid") int outletid);

	@Select("select e.*,d.name as departmentName,j.name as jobName FROM employee e " +
			"left join department d on d.id=e.departmentid " +
			"left join job j on j.id=e.jobid " +
			"where e.id=#{id} and e.outletid =#{outletid}")
	EmployeeInfoVO selectEmployeeByEmployeeId(@Param("outletid") int outletid,@Param("id") String id);

	@Select("<script> select e.*,j.name as jobName FROM employee e " +
			"inner join department d on d.id=e.departmentid " +
			"left join job j on j.id=e.jobid " +
			"<if test='departmentid!=0'> where e.outletid =#{outletid} and d.path like CONCAT('%\\_',#{departmentid},'\\_%')</if> </script>")
	List<EmployeeListInfoVO> selectEmployeeByDepartmentId(@Param("departmentid") String departmentid,@Param("outletid") int outletid);

	@Select("SELECT * FROM employee where cardid=#{cardid} and outletid=#{outletid}")
	Employee selectEmployeeByCardIdAndOutletId(@Param("cardid") String cardid,@Param("outletid") int outletid);


	@Update("<script>update employee set cardfronturl=#{cardfronturl},cardreverseurl=#{cardreverseurl} where id=#{id} and outletid=#{outletid} </script>")
	Integer updateemployeeInfo(EmployeeVO employeeVo);

	@Select("<script>select e.*,d.name as departmentName,j.name as jobName FROM employee e " +
			"left join department d on d.id=e.departmentid " +
			"left join job j on j.id=e.jobid " +
			"where e.cardid in " +
			"  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
			"    separator=\",\" close=\")\">\n" +
			"            #{item}\n" +
			"  </foreach> " +
			" and e.outletid=#{outletid} </script>")
	List<EmployeeInfoVO> selectEmployeeByEmployeeCardids(@Param("list")List<String> list,@Param("outletid") int outletid);

	@Select("SELECT count(*) FROM employee where outletid=#{outletid} ")
	Integer querySumPersonNum(@Param("outletid") int outletid);
	@Insert("INSERT INTO employeearchivesinfo (employeeid,corporationcontent,corporationperiod,corporationname,itemcontent,itemperiod,itemname,classescontent,classesperiod,classesname,remarks,achievement,qualified,explains,constructionname,supervisorname,image,fileurl,filename,outletid)\n" +
			"VALUES (#{employeeid}, #{corporationcontent}, #{corporationperiod}, #{corporationname},#{itemcontent}, #{itemperiod}, #{itemname}, #{classescontent},#{classesperiod}, #{classesname}, #{remarks}, #{achievement}, #{qualified}, #{explains}, #{constructionname}, #{supervisorname}, #{image},#{fileurl},#{filename}, #{outletid})")
    void savePeopleInfo(EmployeeInfo employeeInfo);
	@Update("UPDATE employeearchivesinfo SET corporationcontent = #{corporationcontent},corporationperiod = #{corporationperiod},corporationname = #{corporationname},itemcontent = #{itemcontent},itemperiod= #{itemperiod},itemname = #{itemname},classescontent=#{classescontent},\n" +
			"classesperiod = #{classesperiod},classesname = #{classesname},remarks = #{remarks},achievement= #{achievement},qualified = #{qualified},explains = #{explains},constructionname = #{constructionname},supervisorname = #{supervisorname},image = #{image},fileurl = #{fileurl},filename = #{filename} WHERE id = #{id} and outletid = #{outletid} ")
	void updatePeopleInfo(EmployeeInfo employeeInfo);

	@Select("SELECT  * FROM employeearchivesinfo WHERE employeeid = #{employeeid} AND outletid=#{outletid} limit 1 ")
	EmployeeInfo queryEmployeeById(@Param("employeeid") Integer employeeid,@Param("outletid") int outletid);
}
