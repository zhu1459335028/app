package com.secusoft.ssw.mapper.monitor;

import com.secusoft.ssw.model.monitor.Camera;
import com.secusoft.ssw.model.viewobject.Response.CamerasVO;
import com.secusoft.ssw.model.viewobject.Response.ShieldCameraVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface CameraMapper {

	@Select("SELECT c.deviceno,c.chanindex,c.cameraname,d.devicetype,d.ipaddress,d.tcpport,d.username,d.passwd FROM camera c " +
			"inner join videodevice d on d.deviceno=c.deviceno " +
			"where c.camerano=#{camerano} and c.outletid=#{outletid}")
	CamerasVO selectCameraByCameraNO(@Param("camerano") Integer camerano,@Param("outletid") Integer outletid);

	@Select("SELECT c.camerano,c.deviceno,c.chanindex,c.cameraname,d.devicetype,d.ipaddress,d.tcpport,d.username,d.passwd FROM camera c " +
			"inner join videodevice d on d.deviceno=c.deviceno " +
			"inner join videooutlet o on o.deviceno=c.deviceno " +
			"where o.outletid=#{outletid}")
	List<CamerasVO> selectCameraByOutletId(@Param("outletid") Integer outletid);

	@Select("SELECT * FROM camera where deviceno=#{deviceno} and outletid=#{outletid}")
	List<Camera> selectCameraByDeviceNo(@Param("deviceno") Integer deviceno,@Param("outletid") Integer outletid);

	@Select("select * from camera where cameraNO = #{cameraNo} and outletid=#{outletid}")
	Camera getCameraNoByCno(int cameraNo,@Param("outletid") Integer outletid);

	@Update("UPDATE camera SET deviceNO=#{deviceno}, chanIndex=#{chanindex}, cameraName=#{cameraname},POS_X=#{pos_x}, POS_Y = #{pos_y}," +
			"POS_FontSize = #{pos_fontsize},POS_FontType = #{pos_fonttype},POS_FontColor = #{pos_fontcolor}," +
			"POS_FontBold = #{pos_fontbold},useProfile = #{useprofile},mainProfile = #{mainprofile}," +
			"subProfile = #{subprofile},enabled = #{enabled},checkSD = #{checksd},checkLowBattery = #{checklowbattery}," +
			"authority = #{authority},suspend=#{suspend},cameraType = #{cameratype},slicingTime = #{slicingTime}," +
			"enableSlicing = #{enableSlicing},outletid=#{outletid} WHERE cameraNo=#{camerano} and outletid=#{outletid}")
	void updateCamera(Camera camera);



	@Select("<script>SELECT c.camerano,c.deviceno,c.chanindex,c.cameraname,d.devicetype,d.ipaddress,d.tcpport,d.username,d.passwd FROM camera c " +
			"inner join videodevice d on d.deviceno=c.deviceno " +
			"where c.camerano in " +
			"  <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\"\n" +
			"                 separator=\",\" close=\")\">\n" +
			"            #{item}\n" +
			"        </foreach> " +
			"and c.outletid=#{outletid}</script>")
	List<ShieldCameraVO> queryCameraByCameraNO(@Param("list") List<String> list,@Param("outletid") Integer outletid);

}
