package com.secusoft.ssw.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.secusoft.ssw.common.GlobalApiResult;

import com.secusoft.ssw.model.viewobject.Request.EnvironmentalVo;
import com.secusoft.ssw.model.viewobject.Request.FoundationVo;
import com.secusoft.ssw.model.viewobject.Request.Iocontroller;
import com.secusoft.ssw.model.viewobject.Response.IocontrollerVO;
import com.secusoft.ssw.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/gas")
@Api(value="Gas-Controller",description="气体以及环境相关数据")
public class GasController extends BaseController{
    @Autowired
    private GasService gasService;

    @ApiOperation("根据日期查询环境相关测量测绘数据并分页")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "startTime", value = "开始时间时间戳", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间时间戳", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "types", value = "类型(多个用逗号隔开)", required = true , dataType="String", paramType="query")})
    @GetMapping("/getGasByTimeRange")
    public GlobalApiResult<Object> getGas(@RequestParam String startTime,@RequestParam String endTime, @RequestParam String types,
                                          @RequestParam Integer currentPage,@RequestParam  Integer pageSize){
        String startDate = "";
        String endDate = "";
        try {
            Long startL  = Long.valueOf(startTime);
            Long endL = Long.valueOf(endTime);
            if(startL>=endL){
                return GlobalApiResult.failure("时间选择有误");
            }
            startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(startTime) * 1000));
            endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(endTime) * 1000));
        }catch (Exception e){
            return GlobalApiResult.failure("查询时间参数不合法");
        }

        return GlobalApiResult.success();
    }

    @ApiOperation(value="分页获取所有环境安全")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true , dataType="int", paramType="query")})
    @GetMapping("/getEnvironmentalList")
    public GlobalApiResult<Object> getEnvironmentalList(@RequestParam int currentPage, @RequestParam int pageSize) {
        List<Integer> types = Arrays.asList(new Integer[]{6,7,8,9,10,11,12,13});
        return GlobalApiResult.success(gasService.getEnvironmentalList(getCurrentMonitorId(),getCurrentOutletId(), currentPage, pageSize,types));
    }

    @ApiOperation(value="保存环境安全")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @PostMapping("/saveEnvironmental")
    public GlobalApiResult<String> saveEnvironmental( @RequestBody EnvironmentalVo environmentalVo){
        return GlobalApiResult.success(gasService.saveEnvironmental(getCurrentMonitorId(),getCurrentOutletId(),environmentalVo));
    }

    @ApiOperation(value="更新环境安全")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")})
    @PostMapping("/updateEnvironmental")
    public GlobalApiResult<String> updateEnvironmental(@RequestBody EnvironmentalVo environmentalVo){
        if(environmentalVo.getId()==null || environmentalVo.getId() == ""){
            GlobalApiResult.failure("更新失败,对象必须包含id属性");
        }
        gasService.updateEnvironmental(getCurrentMonitorId(),getCurrentOutletId(),environmentalVo);
        return GlobalApiResult.success("更新成功");
    }

    @ApiOperation(value="删除环境安全/基坑安全数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "1,2,3,4", required = true , dataType="String", paramType="query")})
    @GetMapping("/delete")
    public GlobalApiResult<Object> deleteGas(@RequestParam String id){
        if(null==id||id==""){
            GlobalApiResult.failure("id必须不为空");
        }

        List<Integer> ids = JSONArray.parseArray("["+id+"]", Integer.class);
        gasService.deleteGas(getCurrentMonitorId(),getCurrentOutletId(),ids);
        return GlobalApiResult.success("删除成功");
    }

    @ApiOperation(value="分页获取所有基坑安全")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true , dataType="int", paramType="query")})
    @GetMapping("/getFoundationList")
    public GlobalApiResult<PageInfo<FoundationVo>> getFoundationList(@RequestParam int currentPage, @RequestParam int pageSize) {
        List<Integer> types = Arrays.asList(new Integer[]{1, 2, 3, 4});
        return GlobalApiResult.success(gasService.getFoundationList(getCurrentMonitorId(),getCurrentOutletId(), currentPage, pageSize,types));
    }

    @ApiOperation(value="保存基坑安全")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @PostMapping("/saveFoundationVo")
    public GlobalApiResult<String> saveFoundationVo( @RequestBody FoundationVo foundationVo){
        return GlobalApiResult.success(gasService.saveFoundationVo(getCurrentMonitorId(),getCurrentOutletId(),foundationVo));
    }

    @ApiOperation(value="更新基坑安全")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")})
    @PostMapping("/updateFoundationVo")
    public GlobalApiResult<String> updateFoundationVo(@RequestBody FoundationVo foundationVo){
        if(foundationVo.getId()==null || foundationVo.getId() == ""){
            GlobalApiResult.failure("更新失败,对象必须包含id属性");
        }
        gasService.updateFoundationVo(getCurrentMonitorId(),getCurrentOutletId(),foundationVo);
        return GlobalApiResult.success("更新成功");
    }

    @ApiOperation(value="保存或修改环境安全io控制器信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "iodeviceid", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "ioname", value = "每页条数", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "devicename", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "username", value = "每页条数", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "password", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "type", value = "每页条数", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "factory", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "road", value = "每页条数", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "devicetype", value = "当前页", required = true , dataType="int", paramType="query")
    })
    @PostMapping("/saveGasIoInfo")
    public Object saveGasIoInFO( @RequestBody Iocontroller iocontroller){
        return gasService.saveGasIoInFO(getCurrentMonitorId(),getCurrentOutletId(),iocontroller);
    }

    @ApiOperation(value="获取io控制器信息列表")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/selectAllIoInfo")
    public  GlobalApiResult<Object> selectAllIoInfo(){
        return GlobalApiResult.success(gasService.selectAllIoInfo(getCurrentMonitorId(),getCurrentOutletId()));
    }
    @ApiOperation(value="获取单个io控制器信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "id", required = true , dataType="int", paramType="query")})
    @GetMapping("/selectOneIoInfo/{id}")
    public  GlobalApiResult<Object> selectOneIoInfo(@PathVariable Integer id){
        if (id == null){
            return GlobalApiResult.failure("id不能为空");
        }
        return GlobalApiResult.success(gasService.selectOneIoInfo(getCurrentMonitorId(),getCurrentOutletId(),id));
    }

    @ApiOperation(value="删除单个io控制器信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "id", required = true , dataType="int", paramType="query")})
    @GetMapping("/deleteOneIoInfo/{id}")
    public  GlobalApiResult<Object> deleteOneIoInfo(@PathVariable Integer id){
        if (id == null){
            return GlobalApiResult.failure("id不能为空");
        }
        return GlobalApiResult.success(gasService.deleteOneIoInfo(getCurrentMonitorId(),getCurrentOutletId(),id));
    }







}