package com.secusoft.ssw.mapper.monitor;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName BrandMapper
 * @Author jcyao
 * @Description
 * @Date 2018/12/17 14:05
 * @Version 1.0
 */
@Mapper
public interface BrandMapper {

    @Select("select b.brandName from brand b " +
            "inner join outlet o on o.topBrandId=b.id " +
            "where o.outletId=#{outletId}")
    String selectTopBrandNameByOutletId(int outletId);

}
