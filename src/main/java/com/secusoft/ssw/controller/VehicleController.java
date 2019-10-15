package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.Vehicle;
import com.secusoft.ssw.model.viewobject.Request.VehiclePageVo;
import com.secusoft.ssw.model.viewobject.Request.VehicleRecordVo;
import com.secusoft.ssw.service.VehicleRecordService;
import com.secusoft.ssw.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
@Api(value="Vehicle-Controller" , description="车辆管理相关接口")
public class VehicleController extends BaseController{

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleRecordService vehicleRecordService;

    /**
     * 所属公司
     */
    @ApiOperation("所属公司下拉列表")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/queryCompanyList")
    public GlobalApiResult<Object> queryCompanyList() {
        GlobalApiResult<Object> result=vehicleService.queryCompanyList(getCurrentMonitorId(),getCurrentOutletId());
        return result;
    }


    /**
     * 车辆类型
     */
    @ApiOperation("车辆类型下拉列表")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/queryVehicleTypeList")
    public GlobalApiResult<Object> queryVehicleTypeList() {
        GlobalApiResult<Object> result=vehicleService.queryVehicleTypeList(getCurrentMonitorId(),getCurrentOutletId());
        return result;
    }

    /**
     * 新增车辆信息
     */
    @ApiOperation("添加或者修改车辆信息添加时没有id属性，修改时有id属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "车辆ID", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "type", value = "车辆类型", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "plate_number", value = "车牌号", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "contacts", value = "联系人", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "phone", value = "联系人电话", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "company", value = "所属公司", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "headimageurl", value = "车头照片", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "bodyimageurl", value = "全车照片", required = false , dataType="String", paramType="query")
    })
    @PostMapping("/saveORupdateVehicle")
    public GlobalApiResult<Object> saveORupdateVehicle(Vehicle vehicle) {
        GlobalApiResult<Object> result=vehicleService.saveORupdateVehicle(getCurrentMonitorId(),getCurrentOutletId(),vehicle);
        return result;
    }


    /**
     * 删除车辆信息
     */
    @ApiOperation("删除车辆多个id用逗号分隔例：1,2,3")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "ids", value = "车辆ids", required = true , dataType="String", paramType="query")
    })
    @PostMapping("/deleteVehicle")
    public GlobalApiResult<Object> deleteVehicleByIds(@Param("ids") String ids) {
        GlobalApiResult<Object> result=vehicleService.deleteVehicleByIds(getCurrentMonitorId(),getCurrentOutletId(),Arrays.asList(ids.split(",")));
        return result;
    }


    /**
     * 车辆信息列表
     */
    @ApiOperation("车辆信息列表（分页查询）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "condition", value = "条件", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "plate_number", value = "车牌号", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "type", value = "车辆类型", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "company", value = "所属公司", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true , dataType="int", paramType="query")
    })
    @PostMapping("/getVehicleList")
    public GlobalApiResult<Object> getVehicleList(VehiclePageVo vehiclePageVo) {
        GlobalApiResult<Object> result=vehicleService.getVehicleList(getCurrentMonitorId(),getCurrentOutletId(),vehiclePageVo);
        return result;
    }


    /**
     * 车辆进出记录列表
     */
    @ApiOperation("车辆进出记录列表（分页查询）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "type", value = "车辆类型", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "company", value = "公司", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "status", value = "状态", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endtime", value = "结束时间", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "plate_number", value = "车牌号", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true , dataType="int", paramType="query")
    })
    @PostMapping("/getVehcileRecord")
    public GlobalApiResult<Object> getVehicleList(VehicleRecordVo vehicleRecordVo) {
        System.out.println(getCurrentMonitorId()+"============"+getCurrentOutletId());
//        if(StringUtils.isNotBlank(vehicleRecordVo.getStarttime())){
//            vehicleRecordVo.setStarttime(vehicleRecordVo.getStarttime()+" 00:00:00");
//        }
//        if(StringUtils.isNotBlank(vehicleRecordVo.getEndtime())){
//            vehicleRecordVo.setEndtime(vehicleRecordVo.getEndtime()+" 00:00:00");
//        }
        GlobalApiResult<Object> result=vehicleRecordService.getVehicleRecordPage(getCurrentMonitorId(),getCurrentOutletId(),vehicleRecordVo);
        return result;
    }


    /**
     * 补录
     */
    @ApiOperation("补录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "id", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "bltime", value = "时间", required = true , dataType="String", paramType="query")
    })
    @PostMapping("/updateVehicle")
    public GlobalApiResult<Object> updateVehicle(@RequestParam int id, @RequestParam String bltime) {
        GlobalApiResult<Object> result=vehicleRecordService.updateVehicle(getCurrentMonitorId(),getCurrentOutletId(),id,bltime);
        return result;
    }


    /**
     *工地车辆数量--数据统计
     */
    @ApiOperation("工地车辆数量--数据统计")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/queryVehicleCount")
    public GlobalApiResult<Object> queryVehicleCount() {
        GlobalApiResult<Object> result=vehicleService.queryVehicleCount(getCurrentMonitorId(),getCurrentOutletId());
        return result;
    }

    /**
     *今日进出统计--数据统计
     */
    @ApiOperation("今日进出统计--数据统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "flag", value = "0当天,1本周,2本月", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/queryToDayInOutCount")
    public GlobalApiResult<Object> queryToDayInOutCount(@RequestParam int flag) {
        GlobalApiResult<Object> result=vehicleService.queryToDayInOutCount(getCurrentMonitorId(),getCurrentOutletId(),flag);
        return result;
    }



    /**
     *今日车辆公司进出统计--数据统计
     */
    @ApiOperation("今日车辆公司进出统计--数据统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "flag", value = "0当天,1本周,2本月", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/queryVehicleCompanyToDayInOutCount")
    public GlobalApiResult<Object> queryVehicleCompanyToDayInOutCount(@RequestParam int flag) {
        GlobalApiResult<Object> result=vehicleService.queryVehicleCompanyToDayInOutCount(getCurrentMonitorId(),getCurrentOutletId(),flag);
        return result;
    }


    /**
     *工地进出车辆统计--数据统计
     */
    @ApiOperation("工地进出车辆统计--数据统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endtime", value = "结束时间", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/queryVehicleInOutCount")
    public GlobalApiResult<Object> queryVehicleInOutCount(@RequestParam String starttime, @RequestParam String endtime) {
        GlobalApiResult<Object> result=vehicleService.queryVehicleInOutCount(getCurrentMonitorId(),getCurrentOutletId(),starttime,endtime);
        return result;
    }

    /**
     *各公司车辆进出趟数统计--数据统计
     */
    @ApiOperation("各公司车辆进出趟数统计--数据统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endtime", value = "结束时间", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/queryVehicleCompanyInOutCount")
    public GlobalApiResult<Object> queryVehicleCompanyInOutCount(@RequestParam String starttime, @RequestParam String endtime) {
        GlobalApiResult<Object> result=vehicleService.queryVehicleCompanyInOutCount(getCurrentMonitorId(),getCurrentOutletId(),starttime,endtime);
        return result;
    }



    /**
     *大屏弹框
     */
    @ApiOperation("大屏弹框")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "vehicletype", value = "车辆类型", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/queryVehicleScreenMessage")
    public GlobalApiResult<Object> queryVehicleScreenMessage(String vehicletype) {
        GlobalApiResult<Object> result=vehicleService.queryVehicleScreenMessage(getCurrentMonitorId(),getCurrentOutletId(),vehicletype);
        return result;
    }


    @Value("${vehicle.image.path}")
    private String vehicleImagePath;

    @Value("${mvcvehicle.image.path}")
    private String vmcVehicleImagePath;


    @ApiOperation(value = "web上传车辆图片")
    @PostMapping("/uploadVehicleImage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    public GlobalApiResult<String> uploadVehicleImage(@RequestParam(value = "image",required = false) MultipartFile image){
        String outletid=getCurrentOutletId().toString();
        return GlobalApiResult.success(ResourceController.saveFile(vehicleImagePath+"/"+outletid,image,"/resource/getUploadVehicleImage/"+outletid+"/"));
    }


    /**
     *保存大屏显示车辆类型
     */
    @ApiOperation("保存大屏显示车辆类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "screenvheicle", value = "车辆类型，若类型有多个则用,分隔", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/saveScreenVehicleType")
    public GlobalApiResult<Object> saveScreenVehicleType(@Param("screenvheicle") String screenvheicle) {
        GlobalApiResult<Object> result=vehicleService.saveScreenVehicleType(getCurrentMonitorId(),getCurrentOutletId(),screenvheicle);
        return result;
    }

    /**
     * 大屏车辆类型列表
     */
    @ApiOperation("大屏车辆类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/queryScreenVehicleType")
    public GlobalApiResult<Object> queryScreenVehicleType() {
        GlobalApiResult<Object> result=vehicleService.queryScreenVehicleType(getCurrentMonitorId(),getCurrentOutletId());
        return result;
    }


    /**
     * 修改后的--大屏车辆类型列表
     */
    @ApiOperation("大屏车辆进出列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/queryScreenVehicleData")
    public GlobalApiResult<Object> queryScreenVehicleData() {
        GlobalApiResult<Object> result=vehicleService.queryScreenVehicleData(getCurrentMonitorId(),getCurrentOutletId());
        return result;
    }






}
