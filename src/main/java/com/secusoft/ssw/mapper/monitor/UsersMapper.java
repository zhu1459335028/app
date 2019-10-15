package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.viewobject.Request.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UsersMapper {

	@Select("SELECT * FROM customer where username = #{username}")
	Customer selectUserByUserName(@Param("username") String username);

	@Select("select u.userId from users u inner join userCamera uc " +
			"on u.userId = uc.userId where u.authLevel = #{authLevel} and uc.cameraNo = #{cameraNo} and u.outletid=#{outletid}")
	List<Integer> getUserIdListByCNoAndAuthLevel(@Param("cameraNo") int cameraNo,@Param("authLevel") int i,@Param("outletid") int outletid);

	@Update("update customer set lastoutletid=#{lastoutletid} where id=#{id}  ")
	void updateLastOutletId(@Param("lastoutletid")Integer lastoutletid, @Param("id")Integer id);


}
