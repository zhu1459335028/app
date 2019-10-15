package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.viewobject.Request.QueryWaterVO;
import com.secusoft.ssw.service.WaterLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waterlevel")
@Api(value="Water-Controller" , description="水位管理相关接口")
public class WaterLevelController extends BaseController{

    @Autowired
    private WaterLevelService waterLevelService;

//    /**
//     *监测列表
//     */
//    @ApiOperation("水位--监测列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
//            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true , dataType="int", paramType="query"),
//            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true , dataType="int", paramType="query"),
//            @ApiImplicitParam(name = "starttime", value = "开始时间", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "endtime", value = "结束时间", required = true, dataType = "String", paramType = "query")
//    })
//    @GetMapping("/queryWaterList")
//    public GlobalApiResult<Object> queryWaterList(QueryWaterVO queryWaterVO) {
//        GlobalApiResult<Object> result=waterLevelService.queryWaterList(getCurrentMonitorId(),getCurrentOutletId(),queryWaterVO);
//        return result;
//    }



    /**
     *监测列表
     */
    @ApiOperation("水位--监测列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "starttime", value = "预留开始时间 年月日时分秒", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endtime", value = "预留结束时间 年月日时分秒", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/queryWaterChart")
    public GlobalApiResult<Object> queryWaterChart(QueryWaterVO queryWaterVO) {
        GlobalApiResult<Object> result=waterLevelService.queryWaterChart(getCurrentMonitorId(),getCurrentOutletId(),queryWaterVO);
        return result;
    }

}
