package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.common.exception.ServiceException;
import com.secusoft.ssw.model.viewobject.Request.DeviceMg;
import com.secusoft.ssw.model.viewobject.Request.DeviceVO;
import com.secusoft.ssw.model.viewobject.Response.DeviceCameraVO;
import com.secusoft.ssw.model.viewobject.Response.DeviceManageVO;
import com.secusoft.ssw.model.viewobject.Response.DevicetypenameVO;
import com.secusoft.ssw.service.DeviceService;
import com.secusoft.ssw.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DeviceController
 * @Author jcyao
 * @Description
 * @Date 2018/9/5 14:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/device")
@Api(value = "Device-Controller", description = "设备配置相关接口")
public class DeviceController extends BaseController{

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value="获取设备相机信息")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/deviceCamera")
    public GlobalApiResult<List<DeviceCameraVO>> getDeviceCamera(){
        return GlobalApiResult.success(deviceService.getDeviceCamera(getCurrentMonitorId(),getCurrentUserId(),getCurrentOutletId()));
    }

    @ApiOperation(value="相机轮播信息设置和抓图信息设置")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "cameraNo", value = "相机编号", required = true, dataType="int", paramType="path")})
    @PostMapping("/setCamera/{cameraNo}")
    public GlobalApiResult<String> setCamera(@PathVariable Integer cameraNo, @RequestBody DeviceVO deviceVO){
        if(deviceVO==null||(deviceVO.getPlayback()==1&&deviceVO.getSecond()==0)||
        (deviceVO.getEnableSlicing()==1&&deviceVO.getSlicingTime()==0)){
            throw new ServiceException("入参有误");
        }
        System.out.println("=====================userid:"+getCurrentUserId());
        deviceService.setCamera(getCurrentMonitorId(),getCurrentUserId(),getCurrentOutletId(),cameraNo,deviceVO.getPlayback(),
                deviceVO.getSecond(),deviceVO.getEnableSlicing(),deviceVO.getSlicingTime());
        return GlobalApiResult.success("success");
    }

    @ApiOperation(value="插入一机一档设备类型信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "devicetype", value = "设备类型", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/saveDeviceType")
    public GlobalApiResult<Object> saveDeviceType(@RequestBody DevicetypenameVO devicetypenameVO){
        if (StringUtil.isBlank(devicetypenameVO.getDevicetype()) || devicetypenameVO == null){
            return GlobalApiResult.failure("传入的设备类型不能为空");
        }
        return GlobalApiResult.success(deviceService.saveDeviceType(getCurrentMonitorId(),getCurrentOutletId(),devicetypenameVO));
    }

    @ApiOperation(value="更新、插入一机一档设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "id", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "devicename", value = "设备名称", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "devicetypeid", value = "设备类型id", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "devicemodel", value = "设备型号", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "iscertificateofinspection", value = "有无合格证  0 无  1 有", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "examineid", value = "检验编号", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "examinetime", value = "检验日期", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "isputonrecord", value = "是否备案 0 否 1是", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "drivername", value = "司机名称", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "telephone", value = "联系方式", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "operate", value = "操作证号", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "qualifiedimage", value = "合格证书图片", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "operateimage", value = "操作人证明图片", required = false , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "fileurl", value = "文档url", required = false , dataType="String", paramType="query")
    })
    @PostMapping("/saveOrUpdateDevice")
    public GlobalApiResult<Object> saveOrUpdateDevice(@RequestBody DeviceManageVO deviceManageVO){
        if (deviceManageVO == null){
            return GlobalApiResult.failure("传入的设备信息不能为空");
        }
        return GlobalApiResult.success(deviceService.saveDevice(getCurrentMonitorId(),getCurrentOutletId(),deviceManageVO));
    }


    /**
     * 删除单个及多个一机一档设备管理信息
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "ids", value = "ids示：1,2,3 ", required = true , dataType="String", paramType="query")
            })
    @GetMapping("/deleteDevice")
    @ApiOperation("删除设备信息单个或多个1,2,3")
    public GlobalApiResult<Object> deleteDevice(@Param("ids") String ids){

        return GlobalApiResult.success(deviceService.deleteDevice(getCurrentMonitorId(), getCurrentOutletId(), Arrays.asList(ids.split(","))));

    }

    /**
     * 获取一机一档设备管理信息
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "devicetype", value = "设备类型", required = false , dataType="String", paramType="query"),
                    @ApiImplicitParam(name = "iscertificateofinspection", value = "有无合格证  0 无  1 有", required = false , dataType="String", paramType="query"),
                    @ApiImplicitParam(name = "isputonrecord", value = "是否备案 0 否 1是", required = false , dataType="String", paramType="query"),
                    @ApiImplicitParam(name = "currentPage", value = "当前页数", required = true , dataType="Integer", paramType="query"),
                    @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true , dataType="String", paramType="query")
            })
    @GetMapping("/queryAllDevice")
    @ApiOperation("获取一机一档设备管理信息")
    public GlobalApiResult<Object> queryAllDevice(DeviceMg deviceMg){

        return GlobalApiResult.success(deviceService.queryAllDevice(getCurrentMonitorId(), getCurrentOutletId(),deviceMg));

    }

    @ApiOperation(value="获取一机一档设备类型信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
    })
    @GetMapping("/queryDeviceType")
    public GlobalApiResult<Object> queryDeviceType(){

        return GlobalApiResult.success(deviceService.queryDeviceType(getCurrentMonitorId(),getCurrentOutletId()));
    }
    @ApiOperation(value="获取一机一档单个设备信息")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "id", required = true , dataType="Integer", paramType="query")
    })
    @GetMapping("/queryDevice/{id}")
    public GlobalApiResult<Object> queryDevice(@PathVariable Integer id){

        return GlobalApiResult.success(deviceService.queryOneDevice(getCurrentMonitorId(),getCurrentOutletId(),id));
    }


    @Value("${device.image.path}")
    private String uploadDeviceImage;


    @ApiOperation(value = "上传一机一档图片")
    @PostMapping("/uploadDeviceImage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    public GlobalApiResult<String> uploadDeviceImage(@RequestParam(value = "image",required = false) MultipartFile image){
        String outletid=getCurrentOutletId().toString();
        return GlobalApiResult.success(ResourceController.saveFile(uploadDeviceImage+"/"+outletid,image,"/resource/getDeviceImage/"+outletid+"/"));
    }
    @ApiOperation(value = "上传一机一档文档")
    @PostMapping("/uploadDeviceFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    public GlobalApiResult<Map> uploadDeviceFile(@RequestParam(value = "file",required = false) MultipartFile file) throws Throwable {
        String outletid=getCurrentOutletId().toString();
        return GlobalApiResult.success(ResourceController.saveFiles(uploadDeviceImage+"/"+outletid,file,"/resource/getDeviceFile/"+outletid+"/"));
    }


}
