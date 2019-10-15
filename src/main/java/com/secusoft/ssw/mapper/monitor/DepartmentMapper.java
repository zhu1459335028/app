package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {

	@Select("SELECT * FROM department where outletid=#{outletid}")
	List<Department> selectDepartmentByOutletId(@Param("outletid") int outletid);

	@Select("SELECT * FROM department where outletid=#{outletid} and type=#{type}")
	List<Department> selectDepartmentByOutletIdAndType(@Param("outletid") int outletid,@Param("type") int type);

	@Select("SELECT d.type FROM department d " +
			"inner join employee e on e.departmentid=d.id and e.outletid=d.outletid " +
			"where e.id=#{employeeid}")
	Integer selectDepartmentTypeByEmployeeId(@Param("employeeid") String employeeid);

}
