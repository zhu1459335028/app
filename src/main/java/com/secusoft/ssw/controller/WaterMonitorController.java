package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.viewobject.Request.QueryWaterVO;
import com.secusoft.ssw.service.WaterLevelService;
import com.secusoft.ssw.service.WaterMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/watermonitor")
@Api(value="WaterMonitor-Controller" , description="测量点相关接口")
public class WaterMonitorController extends BaseController{

    @Autowired
    private WaterMonitorService waterMonitorService;

    /**
     *监测列表
     */
    @ApiOperation("添加坑深")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "depth", value = "坑深度", required = true , dataType="String", paramType="query")
    })
    @GetMapping("/saveORupdateWaterDepth")
    public GlobalApiResult<Object> saveWaterDepth(String depth){
        GlobalApiResult<Object> result=waterMonitorService.saveWaterMonitor(getCurrentMonitorId(),getCurrentOutletId(),depth);
        return result;
    }


    /**
     *监测列表
     */
    @ApiOperation("查询坑深")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/queryWaterDepth")
    public GlobalApiResult<Object> queryWaterDepth(String depth){
        GlobalApiResult<Object> result=waterMonitorService.queryWaterDepth(getCurrentMonitorId(),getCurrentOutletId());
        return result;
    }

}
