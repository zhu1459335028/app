package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.viewobject.Response.PatrolRecordVO;
import com.secusoft.ssw.model.viewobject.Response.RealTimeReportVO;
import com.secusoft.ssw.service.ReportService;
import com.secusoft.ssw.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ReportController
 * @Author jcyao
 * @Description
 * @Date 2018/9/4 19:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/report")
@Api(value = "Report-Controller", description = "报警记录相关接口")
public class ReportController extends BaseController{

    @Autowired
    private ReportService reportService;

    @ApiOperation(value="查询实时报警记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(年-月-日 时:分)", dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(年-月-日 时:分)", dataType="String", paramType="query")})
    @GetMapping("/reportHistory")
    public GlobalApiResult<Map<String,List<RealTimeReportVO>>> getReportHistory(@RequestParam(required=false) String startTime,
                                                                                @RequestParam(required=false) String endTime){
        if(StringUtil.isBlank(startTime) || "null".equals(startTime.trim())){
            startTime = null;
        }
        if(StringUtil.isBlank(endTime) || "null".equals(endTime.trim())){
            endTime = null;
        }
        return GlobalApiResult.success(reportService.getReportHistory(getCurrentMonitorId(),getCurrentOutletId(),startTime,endTime));
    }

    @ApiOperation(value="查询运营报警记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(年-月-日 时:分)", dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(年-月-日 时:分)", dataType="String", paramType="query")})
    @GetMapping("/patrolReportHistory")
    public GlobalApiResult<Map<String,List<PatrolRecordVO>>> getPatrolReportHistory(@RequestParam(required=false) String startTime,
                                                                                    @RequestParam(required=false) String endTime){
        if(StringUtil.isBlank(startTime) || "null".equals(startTime.trim())){
            startTime = null;
        }
        if(StringUtil.isBlank(endTime) || "null".equals(endTime.trim())){
            endTime = null;
        }
        return GlobalApiResult.success(reportService.getPatrolReportHistory(getCurrentMonitorId(),getCurrentOutletId(),startTime,endTime));
    }

}
