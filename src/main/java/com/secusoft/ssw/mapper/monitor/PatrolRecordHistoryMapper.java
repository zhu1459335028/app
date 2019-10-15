package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.viewobject.Response.PatrolRecordHistoryVO;
import com.secusoft.ssw.model.viewobject.Response.PatrolRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName PatrolRecordHistoryMapper
 * @Author jcyao
 * @Description
 * @Date 2018/11/16 11:10
 * @Version 1.0
 */
@Mapper
public interface PatrolRecordHistoryMapper {

    @Select("select r.*,i.itemName from patrolRecordHistory r " +
            "inner join checkStandardItem i on i.id = r.itemId " +
            "where r.outletId = #{outletId} and date(r.auditTime)=curDate() and r.auditResult=2 " +
            "order by r.id desc limit 3")
    List<PatrolRecordHistoryVO> selectCurDatePatrolRecordHistoryVOByOutletIdLimit(Integer outletId);

    @Select("<script> SELECT r.*,i.itemName,i.type,i.severity,c.cameraName FROM patrolRecordHistory r " +
            "inner join checkStandardItem i on i.id = r.itemId " +
            "inner join camera c on c.cameraNo=r.cameraNo " +
            "where r.outletId=#{outletId} and r.auditResult=2 " +
            "<if test='startDate!=null'> and r.auditTime &gt;=#{startDate} </if> " +
            "<if test='endDate!=null'> and r.auditTime &lt;=#{endDate} </if> " +
            "order by r.auditTime desc limit 200 </script>")
    List<PatrolRecordVO> selectPatrolRecordByOutletIdAndDate(@Param("outletId") int outletId,
                                                       @Param("startDate") String startDate,
                                                       @Param("endDate") String endDate);

    @Select("<script> SELECT count(*) FROM patrolRecordHistory " +
            "where outletid=#{outletid} and itemId=#{itemId} and auditResult=2 " +
            "<if test='startDate!=null'> and auditTime &gt;=#{startDate} </if> " +
            "<if test='endDate!=null'> and auditTime &lt;=#{endDate} </if></script>")
    Integer countByOutletIdAndItemId(@Param("outletid") int outletid,@Param("itemId") int itemId,
                                                @Param("startDate") String startDate,@Param("endDate") String endDate);

}
