package com.secusoft.ssw.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.*;
import com.secusoft.ssw.model.viewobject.Request.IoStatus;
import com.secusoft.ssw.model.viewobject.Response.*;
import com.secusoft.ssw.service.HomePageService;
import com.secusoft.ssw.util.MyHttpClientPool;
import com.secusoft.ssw.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/homePage")
@Api(value = "Home-Page-Controller", description = "首页相关接口")
public class HomePageController extends BaseController{

    @Autowired
    private HomePageService homePageService;

    @Value("${websetup.base.url}")
    private String websetupBaseUrl;

    @Value("${io.base.url}")
    private String iobaseurl;

    @Value("${ionuberid.hbx}")
    private String ionuberidhbx;

    @Value("${ionuberid.qhx}")
    private String ionuberidqhx;

    @Value("${ionuberid.bhx}")
    private String ionuberidbhx;



    @ApiOperation(value="获取需轮播的相机通道信息")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/camera")
    public GlobalApiResult<List<CamerasVO>> getCameras(){
        return GlobalApiResult.success(homePageService.getCameras(getCurrentMonitorId(),getCurrentUserId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取电子地图信息")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/emap")
    public GlobalApiResult<NewEMap> getEMap(){
        NewEMap eMap = homePageService.getEMap(getCurrentMonitorId(),getCurrentOutletId());
        if(eMap!=null && !StringUtil.isBlank(eMap.getMapurl())){
            eMap.setMapurl(websetupBaseUrl+eMap.getMapurl());
        }
        return GlobalApiResult.success(eMap);
    }

    @ApiOperation(value="获取地图中的相机信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "地图编号", required = true , dataType="int", paramType="path")})
    @GetMapping("/cameraMap/{id}")
    public GlobalApiResult<List<CameraEmapInfoVO>> getCameraMap(@PathVariable int id){
        return GlobalApiResult.success(homePageService.getCameraEmaps(getCurrentMonitorId(),getCurrentOutletId(),id));
    }

    @ApiOperation(value="获取地图中的门禁设备信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "id", value = "地图编号", required = true , dataType="int", paramType="path")})
    @GetMapping("/accessDeviceMap/{id}")
    public GlobalApiResult<List<AccessDeviceEmap>> getAccessDeviceMap(@PathVariable int id){
        return GlobalApiResult.success(homePageService.getAccessDeviceMap(getCurrentMonitorId(),getCurrentOutletId(),id));
    }

    @ApiOperation(value="获取工地概况信息")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/siteInfo")
    public GlobalApiResult<List<BuildingSiteVO>> getSiteInfo(){
        return GlobalApiResult.success(homePageService.getSiteInfo(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取今日实时报警提醒信息")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/reportInfo")
    public GlobalApiResult<List<Realtimereporthistory>> getReportInfo(){
        return GlobalApiResult.success(homePageService.getReportInfo(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取今日运营报警")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/patrolReportInfo")
    public GlobalApiResult<List<PatrolRecordHistoryVO>> getPatrolReportInfo(){
        return GlobalApiResult.success(homePageService.getPatrolReportInfo(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取ICS服务器在线状态",notes="返回结果：1.在线、2.不在线")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/icsStatus")
    public GlobalApiResult<Integer> getIcsServerStatus(){
        if("on".equals(VmcServerController.serviceStatus.get(getCurrentMonitorId()+"_"+getCurrentOutletId()))){
            return GlobalApiResult.success(1);
        }else{
            return GlobalApiResult.success(2);
        }
    }

    @ApiOperation(value="获取基坑当前人员信息")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/foundationDitch/peopleCount")
    public GlobalApiResult<List<EmployeeInfoVO>> getFDPeopleCount(){
        return GlobalApiResult.success(homePageService.getFDPeopleCount(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取违规事件统计（柱状图）")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/wgsjtj1")
    public GlobalApiResult<ChartVO> getWgsjtj1(){
        return GlobalApiResult.success(homePageService.getWgsjtj1(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取违规事件统计（折线图）")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/wgsjtj2")
    public GlobalApiResult<ChartVO> getWgsjtj2(){
        return GlobalApiResult.success(homePageService.getWgsjtj2(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取项目管理人员在岗时间")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/zgsj")
    public GlobalApiResult<ChartVO> getZgsj(){
        return GlobalApiResult.success(homePageService.getZgsj(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取基坑出入人口统计")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/jkrktj")
    public GlobalApiResult<ChartVO> getJkrktj(){
        return GlobalApiResult.success(homePageService.getJkrktj(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取施工班在岗情况")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/constructionOnwork")
    public GlobalApiResult<ConstructionOnWorkVO> getConstructionOnwork(){
        return GlobalApiResult.success(homePageService.getConstructionOnwork(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取当前入场总人数和出场总人数")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/comeOnAndOut")
    public GlobalApiResult<ComeOnAndOutVO> comeOnAndOut(){
        return GlobalApiResult.success(homePageService.comeOnAndOut(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取气体检测数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "gas_type", value = "气体类型 1:可燃气 2:氧气 3:一氧化碳 4:硫化氢", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间时间戳", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间时间戳", required = true , dataType="String", paramType="query")})
    @GetMapping("/gas")
    public GlobalApiResult<Object> gas(@RequestParam Integer gas_type, @RequestParam String startTime,@RequestParam String endTime){
        String startDate = "";
        String endDate = "";
        try {
            Long startL  = Long.valueOf(startTime);
            Long endL = Long.valueOf(endTime);
            if(startL>=endL){
                return GlobalApiResult.failure("时间选择有误");
            }
            startDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.valueOf(startTime) * 1000));
            endDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.valueOf(endTime) * 1000));
        }catch (Exception e){
            return GlobalApiResult.failure("查询时间参数不合法");
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("gas", homePageService.getGas(getCurrentMonitorId(),getCurrentOutletId(), gas_type ,startDate , endDate));
        resultMap.put("threshold", homePageService.getThreshold(getCurrentMonitorId(),getCurrentOutletId(), "gas_"+gas_type));
        
        return GlobalApiResult.success(resultMap);
    }

    @ApiOperation(value="获取沉降相关测量测绘数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "startTime", value = "开始时间时间戳", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间时间戳", required = true , dataType="String", paramType="query")})
    @GetMapping("/settlement")
    public GlobalApiResult<List<Settlement>> settlement(@RequestParam String startTime,@RequestParam String endTime){
        String startDate = "";
        String endDate = "";
        try {
            Long startL  = Long.valueOf(startTime);
            Long endL = Long.valueOf(endTime);
            if(startL>=endL){
                return GlobalApiResult.failure("时间选择有误");
            }
            startDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.valueOf(startTime) * 1000));
            endDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.valueOf(endTime) * 1000));
        }catch (Exception e){
            return GlobalApiResult.failure("查询时间参数不合法");
        }
        
        return GlobalApiResult.success(homePageService.getSettlement(getCurrentMonitorId(),getCurrentOutletId(), startDate , endDate));
    }


    @ApiOperation(value="获取阈值")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "thresholdType", value = "阈值类型 例如可燃气阈值类型gas_1", required = true , dataType="String", paramType="query")})
    @GetMapping("/threshold")
    public GlobalApiResult<Threshold> Threshold(@RequestParam String thresholdType){
        return GlobalApiResult.success(homePageService.getThreshold(getCurrentMonitorId(),getCurrentOutletId(), thresholdType));
    }




    @ApiOperation(value="基坑管理")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/foundManage")
    public GlobalApiResult<Map<String,Object>> foundManage() throws ParseException {
        Map<String,Object> map=homePageService.foundManage(getCurrentMonitorId(),getCurrentOutletId());
        return GlobalApiResult.success(map);
    }


    @ApiOperation(value="人员管理")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/personManage")
    public GlobalApiResult<Map<String,Object>> personManage() throws ParseException {
        Map<String,Object> map=homePageService.personManage(getCurrentMonitorId(),getCurrentOutletId());
        return GlobalApiResult.success(map);
    }


    @ApiOperation(value="施工天数")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/constructiondays")
    public GlobalApiResult<Map<String,Object>> constructiondays() throws ParseException {
        Map<String,Object> map=homePageService.constructiondays(getCurrentMonitorId(),getCurrentOutletId());
        return GlobalApiResult.success(map);
    }


    /**
     * 人员管理弹w窗===getConstructionOnwork
     * @param foundid
     * @return
     * @throws ParseException
     */
//    @ApiOperation(value="人员管理弹窗")
//    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
//    @GetMapping("/personManagePopupWindow")
//    public GlobalApiResult<Map<String,Object>> personManagePopupWindow() throws ParseException {
//        Map<String,Object> map=homePageService.personManagePopupWindow(getCurrentMonitorId(),getCurrentOutletId());
//        return GlobalApiResult.success(map);
//    }



    @ApiOperation(value="首页水位弹窗")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "foundid", value = "基坑ID", required = true , dataType="Integer", paramType="query")})
    @GetMapping("/homePageWaterPopupWindow")
    public GlobalApiResult<Map<String,Object>> homePageWaterPopupWindow(@RequestParam Integer foundid) {
        Map<String,Object> map=homePageService.homePageWaterPopupWindow(getCurrentMonitorId(),getCurrentOutletId(),foundid);
        return GlobalApiResult.success(map);
    }



    @ApiOperation(value="首页气体弹窗")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/homePageGasPopupWindow")
    public GlobalApiResult<Map<String, Object>> homePageGasPopupWindow() {
        Map<String, Object> map=homePageService.homePageGasPopupWindow(getCurrentMonitorId(),getCurrentOutletId());
        return GlobalApiResult.success(map);
    }



    @ApiOperation(value="首页有害气体弹窗")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/homePageHarmfulGasPopupWindow")
    public GlobalApiResult<HarmfulGasVO> homePageHarmfulGasPopupWindow() {
        HarmfulGasVO harmfulGasVO=homePageService.homePageHarmfulGasPopupWindow(getCurrentMonitorId(),getCurrentOutletId());
        return GlobalApiResult.success(harmfulGasVO);
    }
    @ApiOperation(value="添加工地开始时间")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "startTime", value = "开始日期", required = true , dataType="String", paramType="query"),
    })
    @GetMapping("/homePageSaveData")
    public GlobalApiResult<Object> addDate(@RequestParam String startTime) {

        return GlobalApiResult.success(homePageService.saveData(getCurrentMonitorId(),getCurrentOutletId(),startTime));

    }
    @ApiOperation(value="查询工地开始时间")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
    })
    @GetMapping("/homePageQueryDate")
    public GlobalApiResult<Object> queryDate() {

        return GlobalApiResult.success(homePageService.queryData(getCurrentMonitorId(),getCurrentOutletId()));

    }

    @ApiOperation(value="查询工地title")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
    })
    @GetMapping("/homePageQueryTitle")
    public GlobalApiResult<Object> queryTitle() {

        return GlobalApiResult.success(homePageService.queryTitle(getCurrentMonitorId(),getCurrentOutletId()));

    }

    @ApiOperation(value="保存施工图")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "name", value = "图片名", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "imageurl", value = "开始日期", required = true , dataType="String", paramType="query")
    })
    @GetMapping("/homePageSaveDgImage")
    public GlobalApiResult<Object> savePageImage(@RequestParam String imageurl,@RequestParam String name) {
        return GlobalApiResult.success(homePageService.savePageImage(getCurrentMonitorId(),getCurrentOutletId(),imageurl,name));

    }

    @ApiOperation(value="查询首页施工图")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
    })
    @GetMapping("/homePageQueryDgImage")
    public GlobalApiResult<Object> queryDgImage() {
        return GlobalApiResult.success(homePageService.queryDgImage(getCurrentMonitorId(),getCurrentOutletId()));

    }
    @ApiOperation(value="保存盾构图")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "imageurl", value = "图片路径", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "type", value = "type", required = true , dataType="String", paramType="query")
    })
    @GetMapping("/saveDgImage")
    public GlobalApiResult<Object> SaveDgImage(@RequestParam String imageurl,@RequestParam Integer type) {
        return GlobalApiResult.success(homePageService.SaveDgImage(getCurrentMonitorId(),getCurrentOutletId(),imageurl,type));

    }
    @ApiOperation(value="查询盾构图")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
    })
    @GetMapping("/QueryDgImage")
    public GlobalApiResult<Object> QueryDgImage() {
        return GlobalApiResult.success(homePageService.queryImage(getCurrentMonitorId(),getCurrentOutletId()));

    }

    @ApiOperation(value="发送撤离状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "iostatus", value = "撤离状态", required = true , dataType="String", paramType="query"),
    })
    @GetMapping("/sendIoStatus")
    public Object sendIoStatus(@RequestParam String evacuate,@RequestParam String devicetype) {
        if (StringUtil.isBlank(evacuate)){
            return GlobalApiResult.failure("状态不能为空");
        }
        String devicename = homePageService.selectDeviceNameByDevicetype(getCurrentMonitorId(), getCurrentOutletId(), devicetype);
        System.out.println("返回结果==================================："+evacuate);
        IocontrollerVO iocontrollerVO = homePageService.selectRoad(getCurrentMonitorId(),devicename, getCurrentOutletId());
        HashMap<String, String> map = new HashMap<>();
        map.put("roadnum",iocontrollerVO.getRoad());
        map.put("evacuate",evacuate);
        map.put("outletId",getCurrentOutletId().toString());
        if (iocontrollerVO == null){
                map.put("ionumID","");
        }else {
                map.put("ionumID",iocontrollerVO.getIodeviceid());
        }
        String url=iobaseurl;
        StringEntity stringEntity = new  StringEntity(JSON.toJSONString(map), "UTF-8");
        String result = MyHttpClientPool.fetchByPostMethod(url, stringEntity);
        JSONObject ioresult = JSONObject.parseObject(result);
        Object sta = ioresult.get("result");
        String status = sta.toString();
        System.out.println("================"+status);
        homePageService.updateStatus(getCurrentMonitorId(),status,getCurrentOutletId(),iocontrollerVO.getIodeviceid());
        return  ioresult;
    }
    @ApiOperation(value="获取撤离状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "iostatus", value = "撤离状态", required = true , dataType="String", paramType="query"),
    })
    @GetMapping("/queryIoStatus")
    public GlobalApiResult<Object> queryIoStatus() {

        return  GlobalApiResult.success(homePageService.queryIoStatus(getCurrentMonitorId(),getCurrentOutletId()));
    }

    @ApiOperation(value="获取域名")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
    })
    @GetMapping("/queryIpPort")
    public GlobalApiResult<Object> queryIpPort() {
        return  GlobalApiResult.success(homePageService.queryIpPort(getCurrentMonitorId(),getCurrentOutletId()));
    }
    @ApiOperation(value="发送喷淋状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "evacuate", value = "喷淋状态", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "id", value = "id", required = true , dataType="String", paramType="query")
    })
    @GetMapping("/sendIoPlStatus")
    public Object sendIoPlStatus(@RequestParam String evacuate,@RequestParam Integer id) {
        if (StringUtil.isBlank(evacuate)){
            return GlobalApiResult.failure("状态不能为空");
        }
        System.out.println("返回结果==================================："+evacuate);
        IocontrollerVO iocontrollerVO = homePageService.selectReportdevice(getCurrentMonitorId(), getCurrentOutletId(), id);
        HashMap<String, String> map = new HashMap<>();
        if (!StringUtil.isBlank(iocontrollerVO.getRoad())){
            map.put("roadnum",iocontrollerVO.getRoad());
        }
        map.put("evacuate",evacuate);
        map.put("outletId",getCurrentOutletId().toString());
        if (StringUtil.isBlank(iocontrollerVO.getIodeviceid())){
                map.put("ionumID","");
        }else {
                map.put("ionumID",iocontrollerVO.getIodeviceid());
        }
        String url=iobaseurl;
        StringEntity stringEntity = new  StringEntity(JSON.toJSONString(map), "UTF-8");
        String result = MyHttpClientPool.fetchByPostMethod(url, stringEntity);
        JSONObject ioresult = JSONObject.parseObject(result);
        Object sta = ioresult.get("result");
        String status = sta.toString();
        System.out.println("================"+status);
        homePageService.updateStatus(getCurrentMonitorId(),status,getCurrentOutletId(),iocontrollerVO.getIodeviceid());
        return  ioresult;
    }

    @ApiOperation(value="修改首页喷淋手动自动")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
    })
    @PostMapping("/updateAutomanual")
    public GlobalApiResult<Object> updateAutomanual(@RequestBody AutomanualVO automanualVO) {
        if (automanualVO.getId()==null|| automanualVO == null){
            return GlobalApiResult.failure("参数不能为空");
        }
        return  GlobalApiResult.success(homePageService.updateAutomanual(getCurrentMonitorId(),getCurrentOutletId(),automanualVO));
    }





}
