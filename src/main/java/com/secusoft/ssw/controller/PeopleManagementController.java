package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.EmployeeInfo;
import com.secusoft.ssw.model.viewobject.Request.EmployeeVO;
import com.secusoft.ssw.model.viewobject.Response.DepartmentInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeListInfoVO;
import com.secusoft.ssw.model.viewobject.Response.EmployeeOnWorkVO;
import com.secusoft.ssw.service.PeopleManagementService;
import com.secusoft.ssw.util.DateStyle;
import com.secusoft.ssw.util.DateUtils;
import com.secusoft.ssw.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/peopleManage")
@Api(value = "People-Management-Controller", description = "人员管理相关接口")
public class PeopleManagementController extends BaseController{

    @Autowired
    private PeopleManagementService peopleManagementService;

    @ApiOperation(value="获取组织架构")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/department")
    public GlobalApiResult<List<DepartmentInfoVO>> getDepartment(){
        return GlobalApiResult.success(peopleManagementService.getDepartment(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取员工列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "departmentId", value = "部门编号，当为0时表示所有部门", required = true , dataType="String", paramType="path")})
    @GetMapping("/employeeList/{departmentId}")
    public GlobalApiResult<List<EmployeeListInfoVO>> getEmployeeList(@PathVariable String departmentId){
        return GlobalApiResult.success(peopleManagementService.getEmployeeList(getCurrentMonitorId(),getCurrentOutletId(),departmentId));
    }

    @ApiOperation(value="获取在岗记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "employeeId", value = "员工编号", required = true , dataType="String", paramType="path"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(年-月-日)", dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(年-月-日)", dataType="String", paramType="query")})
    @GetMapping("/onWork/{employeeId}")
    public GlobalApiResult<List<EmployeeOnWorkVO>> getOnWork(@PathVariable String employeeId,
            @RequestParam(required=false) String startTime, @RequestParam(required=false) String endTime){
        Date startDate = null,endDate = null;
        if(!StringUtil.isBlank(startTime)){
            startDate = DateUtils.StringToDate(startTime+" 00:00:00", DateStyle.YYYY_MM_DD_HH_MM_SS);
        }
        if(!StringUtil.isBlank(endTime)){
            endDate = DateUtils.StringToDate(endTime+" 23:59:59", DateStyle.YYYY_MM_DD_HH_MM_SS);
        }
        return GlobalApiResult.success(peopleManagementService.getOnWork(getCurrentMonitorId(),getCurrentOutletId(),employeeId,startDate,endDate));
    }

    @ApiOperation(value="获取员工基本信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "employeeId", value = "员工编号", required = true , dataType="String", paramType="path")})
    @GetMapping("/employeeInfo/{employeeId}")
    public GlobalApiResult<EmployeeInfoVO> getEmployeeInfo(@PathVariable String employeeId){
        return GlobalApiResult.success(peopleManagementService.getEmployeeInfo(getCurrentMonitorId(),getCurrentOutletId(),employeeId));
    }


    /**
     *EmployeeVO
     */
    @ApiOperation(value="更新员工身份证照片")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "cardfronturl", value = "身份证正面url", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "cardreverseurl", value = "身份证反面url", required = false, dataType = "String", paramType = "query")
    })
    @PostMapping("/updateemployeeInfo")
    public GlobalApiResult<Object> updateemployeeInfo(EmployeeVO employeeVo){
        return GlobalApiResult.success(peopleManagementService.updateemployeeInfo(getCurrentMonitorId(),getCurrentOutletId(),employeeVo));
    }




    @Value("${person.image.path}")
    private String uploadPersonImage;


    @ApiOperation(value = "web上传身份证图片")
    @PostMapping("/uploadPersonImage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    public GlobalApiResult<String> uploadAttendanceImage(@RequestParam(value = "image",required = false) MultipartFile image){
        String outletid=getCurrentOutletId().toString();
        return GlobalApiResult.success(ResourceController.saveFile(uploadPersonImage+"/"+outletid,image,"/resource/getUploadPersonImage/"+outletid+"/"));
    }


    @ApiOperation(value = "web上传人员文档")
    @PostMapping("/uploadPersonFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    public GlobalApiResult<Map> uploadPersonFile(@RequestParam(value = "file",required = false) MultipartFile file) throws Throwable {
        String outletid=getCurrentOutletId().toString();
        return GlobalApiResult.success(ResourceController.saveFiles(uploadPersonImage+"/"+outletid,file,"/resource/getUploadPersonFile/"+outletid+"/"));
    }

    /**
     *EmployeeVO
     */
    @ApiOperation(value="保存或修改一人一档信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "id", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "employeeid", value = "人员id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "corporationcontent", value = "公司级内容", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "corporationperiod", value = "公司级日期及学时", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "corporationname", value = "公司级姓名及职务", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "itemcontent", value = "项目部级内容", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "itemperiod", value = "项目部级日期及学时", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "itemname", value = "项目部级姓名及职务", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "classescontent", value = "班组级内容", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "classesperiod", value = "班组级日期及学时", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "classesname", value = "班组级姓名及职务", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "achievement", value = "成绩", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "qualified", value = "是否合格", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "explains", value = "考试说明", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "constructionname", value = "施工单位培训组织人员姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "supervisorname", value = "监理单位审查人员姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "fileurl", value = "fileurl", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "image", value = "人员图片", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/saveOrUpdateEmployeeInfo")
    public GlobalApiResult<Object> saveOrUpdateEmployeeInfo(@RequestBody EmployeeInfo employeeInfo){
        if (employeeInfo == null){
            return GlobalApiResult.failure("输入信息不能为空");
        }
        return GlobalApiResult.success(peopleManagementService.savePeopleInfo(getCurrentMonitorId(),getCurrentOutletId(),employeeInfo));
    }


    @ApiOperation(value="获取员工一人一档信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "employeeId", value = "员工编号", required = true , dataType="String", paramType="path")})
    @GetMapping("/queryEmployee/{employeeId}")
    public GlobalApiResult<Object> queryEmployee(@PathVariable Integer employeeId){
        return GlobalApiResult.success(peopleManagementService.queryEmployee(getCurrentMonitorId(),getCurrentOutletId(),employeeId));
    }





}
