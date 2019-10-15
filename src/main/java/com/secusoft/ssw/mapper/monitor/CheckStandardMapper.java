package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.CheckStandardItem;
import com.secusoft.ssw.model.monitor.CheckStandardSubdirectory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName CheckStandardMapper
 * @Author jcyao
 * @Description
 * @Date 2018/11/29 14:43
 * @Version 1.0
 */
@Mapper
public interface CheckStandardMapper {

    @Select("select s.* from checkStandardSubdirectory s " +
            "inner join checkStandardDirectory d on d.id = s.directoryId " +
            "inner join standardOutlet o on o.standardId = d.templateId " +
            "where o.outletId=#{outletId} and s.isDelete=0")
    List<CheckStandardSubdirectory> selectSubdirectoryByOutletId(Integer outletId);

    @Select("select * from checkStandardItem where subDirectoryId=#{subDirectoryId} and outletid=#{outletid} and isDelete=0")
    List<CheckStandardItem> selectItemBySubdirectoryId(Integer subDirectoryId,@Param("outletid") Integer outletid);

}
