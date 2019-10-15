package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.Threshold;
import com.secusoft.ssw.service.ThresholdService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Arrays;
import java.util.List;

/**
 * 需要修改
 */
@RestController
@RequestMapping("/threshold")
@Api(value="Threshold-Controller" , description="阈值相关接口")
public class ThresholdController extends BaseController{


    @Autowired
    ThresholdService thresholdService;

    
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "type", value = "阈值type", required = true , dataType="String", paramType="query")})
    @GetMapping("/get")
    @ApiOperation("获取指定type阈值")
    public GlobalApiResult<Object> getThreshold(String type) {
        Threshold threshold = thresholdService.getThreshold(getCurrentMonitorId(), getCurrentOutletId(), type);
        if(null==threshold){
            threshold = new Threshold();
        }
        return GlobalApiResult.success(threshold);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")})
    @PostMapping("/update")
    @ApiOperation("更新阈值")
    public GlobalApiResult<String> updateThreshold(@RequestBody Threshold threshold){
        if(null==threshold.getType()||"".equals(threshold.getType())){
            GlobalApiResult.failure("threshold 必须包含id和type");
        }
        thresholdService.updateThreshold(getCurrentMonitorId(), getCurrentOutletId(), threshold);
        return GlobalApiResult.success("更新成功");
    }


    /**
     * 添加水位阈值接口
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "id", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "max_val", value = "最大值及颜色", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "min_val", value = "最小值及颜色", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "starttimestr", value = "阈值开始时间 年月日时分秒", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endtimestr", value = "阈值结束时间 年月日时分秒", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "identical_val", value = "恒等值及颜色", required = false , dataType="String", paramType="query")
    })
    @PostMapping("/SaveOrUpdateThreshold")
    @ApiOperation("添加水位阈值接口")
    public GlobalApiResult<Object> SaveOrUpdateThreshold(@RequestBody List<Threshold> threshold){
        GlobalApiResult<Object> result=thresholdService.SaveOrUpdateThreshold(getCurrentMonitorId(), getCurrentOutletId(),threshold);
        return result;
    }


    /**
     * 查询水位阈值列表
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
            })
    @PostMapping("/queryThreshold")
    @ApiOperation("查询水位阈值列表")
    public GlobalApiResult<Object> queryThreshold(Threshold threshold){
        GlobalApiResult<Object> result=thresholdService.queryThreshold(getCurrentMonitorId(), getCurrentOutletId(), threshold);
        return result;
    }


    /**
     * 删除水位阈值
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "ids", value = "水位ids示：1,2,3 ", required = true , dataType="String", paramType="query")
            })
    @PostMapping("/deleteThreshold")
    @ApiOperation("删除水位阈值个或多个1,2,4")
    public GlobalApiResult<Object> deleteThreshold(@Param("ids") String ids){
        GlobalApiResult<Object> result=thresholdService.deleteThreshold(getCurrentMonitorId(), getCurrentOutletId(), Arrays.asList(ids.split(",")));
        return result;
    }

    /**
     * 设置垂直水平位移阈值接口
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "id", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "type", value = "类型", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "max_val", value = "最大值及颜色", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "min_val", value = "最小值及颜色", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "starttimestr", value = "阈值开始时间 年月日时分秒", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endtimestr", value = "阈值结束时间 年月日时分秒", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "identical_val", value = "恒等值及颜色", required = false , dataType="String", paramType="query")
    })
    @PostMapping("/saveOrUpdateLevel")
    @ApiOperation("添加水平位移阈值接口")
    public GlobalApiResult<Object> SaveOrUpdateLevel(@RequestBody Threshold threshold){
        GlobalApiResult<Object> result=thresholdService.SaveOrUpdateLevel(getCurrentMonitorId(), getCurrentOutletId(),threshold);
        return result;
    }

    /**
     * 删除水平或垂直报警值
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "id", value = "id", required = true , dataType="Integer", paramType="query")
            })
    @GetMapping("/deleteThresholdLevel")
    @ApiOperation("删除水平或垂直阈值")
    public GlobalApiResult<Object> deleteThresholdLevel(@RequestParam Integer id){
        GlobalApiResult<Object> result=thresholdService.deleteThresholdLevel(getCurrentMonitorId(), getCurrentOutletId(), id);
        return result;
    }


}