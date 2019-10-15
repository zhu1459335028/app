package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.viewobject.Response.ChartVO;
import com.secusoft.ssw.model.viewobject.Response.PatrolReportDataCountVO;
import com.secusoft.ssw.service.DataCountService;
import com.secusoft.ssw.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName DataCountController
 * @Author jcyao
 * @Description
 * @Date 2018/11/29 13:09
 * @Version 1.0
 */
@RestController
@RequestMapping("/dataCount")
@Api(value = "Data-Count-Controller", description = "数据统计展示相关接口")
public class DataCountController extends BaseController {

    @Autowired
    private DataCountService dataCountService;

    @ApiOperation(value="查询运营报警统计")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(年-月-日)", dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(年-月-日)", dataType="String", paramType="query")})
    @GetMapping("/getPatrolReportDataCount")
    public GlobalApiResult<PatrolReportDataCountVO> getPatrolReportDataCount(@RequestParam(required = false) String startTime,
                                                                             @RequestParam(required = false) String endTime){
        return GlobalApiResult.success(dataCountService.getPatrolReportDataCount(getCurrentMonitorId(),getCurrentOutletId(),startTime,endTime));
    }

    @ApiOperation(value="查询实时报警统计")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(年-月-日)", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(年-月-日)", required = true , dataType="String", paramType="query")})
    @GetMapping("/getRealTimeReportDataCount")
    public GlobalApiResult<ChartVO> getRealTimeReportDataCount(@RequestParam String startTime,@RequestParam String endTime){
        Date startDate = DateUtils.parseDate(startTime,"yyyy-MM-dd");
        Date endDate = DateUtils.parseDate(endTime,"yyyy-MM-dd");
        long betweenDays = DateUtils.betweenDays(endDate,startDate);
        if(betweenDays<0){
            return GlobalApiResult.failure("时间选择有误");
        }
        List<Date> dates = new LinkedList<>();
        for(int i=0;i<=betweenDays;i++){
            dates.add(DateUtils.subDay(startDate,-i));
        }
        return GlobalApiResult.success(dataCountService.getRealTimeReportDataCount(getCurrentMonitorId(),getCurrentOutletId(),startTime,endTime,dates));
    }

}
