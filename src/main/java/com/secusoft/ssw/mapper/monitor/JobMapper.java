package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobMapper {

	@Select("SELECT * FROM job where outletid=#{outletid} and type=1")
	List<Job> selectJobByOutletId(@Param("outletid") int outletid);

	@Select("SELECT j.type FROM job j " +
			"inner join employee e on e.jobid=j.id and e.outletid=j.outletid " +
			"where e.id=#{employeeid}")
	Integer selectJobTypeByEmployeeId(@Param("employeeid") String employeeid);

}
