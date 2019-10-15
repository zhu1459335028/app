package com.secusoft.ssw.mapper.monitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface PerimeterManagementMapper {

    @Select("SELECT p.imageUrl,p.CameraNo,c.cameraName FROM perimeterPictures p INNER JOIN (SELECT max(id) as id from perimeterPictures " +
            "GROUP BY cameraNo) pe on p.id = pe.id INNER JOIN camera c on p.CameraNo = c.CameraNo where p.outletId = #{outletId} and c.enableSlicing = 1 and c.enabled = 1")
    List<Map<String, Object>> getCameraNoAndImageUrlByOid(Integer outletId);

    @Select("SELECT p.imageUrl,p.CameraNo,c.cameraName FROM perimeterPictures p " +
            "inner join camera c on p.CameraNo = c.CameraNo where p.id = #{id} and p.outletId = #{outletid} ")
    Map<String, Object> getPerimeterPicturesById(int id,@Param("outletid") int outletid);
}
