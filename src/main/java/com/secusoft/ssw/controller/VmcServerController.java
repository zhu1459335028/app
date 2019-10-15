package com.secusoft.ssw.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.ThresholdMapper;
import com.secusoft.ssw.model.monitor.*;
import com.secusoft.ssw.model.viewobject.Request.*;
import com.secusoft.ssw.model.viewobject.Response.*;
import com.secusoft.ssw.service.*;
import com.secusoft.ssw.util.DateStyle;
import com.secusoft.ssw.util.DateUtils;
//import com.secusoft.ssw.util.ElasticSearchUtil;
import com.secusoft.ssw.util.MyHttpClientPool;
import com.secusoft.ssw.util.StringUtil;
import com.secusoft.ssw.websocket.WebSocket;

import com.secusoft.ssw.websocket.WebSocketToTunnel8;
import com.secusoft.ssw.websocket.WebSocketTwo;
import com.secusoft.ssw.websocket.WebSocketTwo8;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;

import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/vmcServer")
@Api(value = "Vmc-Server-Controller", description = "供VMCServer调用的接口")
public class VmcServerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static ConcurrentHashMap<String,String> serviceStatus = new ConcurrentHashMap<>();

    @Autowired
    private VmcServerService vmcServerService;
    @Autowired
    private ReportService reportService;
    @Value("${report.base.url}")
    private String reportBaseUrl;
    @Value("${ssm.base.url}")
    private String ssmBaseUrl;
    @Value("${io.base.url}")
    private String iobaseurl;

    @Autowired
    private PerimeterManagementService perimeterManagementService;
    @Autowired
    private GasService gasService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private WaterLevelService waterLevelService;

    @Autowired
    ThresholdService thresholdService;



    @Autowired
    private  ProgressstatisticsService progressstatisticsService;

    @Autowired
    private  HomePageService homePageService;

    private ExecutorService cachedThreadPool ;
    @PostConstruct
    public void setup(){
        cachedThreadPool = Executors.newCachedThreadPool();
    }

    @ApiOperation(value="同步ICS服务状态")
    @PostMapping("/icsStatus")
    public GlobalApiResult<String> syncIcsStatus(@RequestBody ServiceStatusRequestsVO serviceStatusRequestsVO){
//        logger.info("收到ICS服务状态通知："+serviceStatusRequestsVO);
        if(serviceStatusRequestsVO.getIcsserver()!=null){
            for(ServiceStatusRequestVO serviceStatusRequestVO : serviceStatusRequestsVO.getIcsserver()){
                serviceStatus.put(serviceStatusRequestVO.getMonitorid()+"_"+serviceStatusRequestVO.getOutletid(),serviceStatusRequestVO.getState());
                WebSocketMessageVO webSocketMessage = new WebSocketMessageVO();
                webSocketMessage.setType(4);
                Map<String,Object> map = new HashMap<>();
                map.put("value","on".equals(serviceStatusRequestVO.getState())?1:2);
                webSocketMessage.setData(map);
                WebSocket.sendMessage(WebSocket.OUTLETID,serviceStatusRequestVO.getMonitorid()+"_"+serviceStatusRequestVO.getOutletid(),webSocketMessage);
            }
        }
        return GlobalApiResult.success("success");
    }

    @ApiOperation(value="推送报告")
    @PostMapping("/syncReport")
    public GlobalApiResult<String> syncReport(@RequestBody Map<String,String> paramMap){
        logger.info("收到报告通知："+paramMap);
        int monitorId = Integer.valueOf(paramMap.get("monitorid"));
        int outletid = Integer.valueOf(paramMap.get("outletid"));
        int type = Integer.valueOf(paramMap.get("type"));
        String reportid = paramMap.get("reportid");
        if(monitorId>0 && type>0 && !StringUtil.isBlank(reportid)){
            if(type==4){
                //推送实时报告
                Realtimereporthistory realtimereporthistory = reportService.getRealReportByReportId(monitorId,outletid,reportid);
                if(realtimereporthistory!=null){
                    WebSocketMessageVO webSocketMessage = new WebSocketMessageVO();
                    webSocketMessage.setType(5);
                    Map<String,Object> map = new HashMap<>();
                    map.put("reportid",reportid);
                    map.put("itemname",realtimereporthistory.getItemname());
                    if(realtimereporthistory.getReporturl().contains("http://")){
                        map.put("reporturl", realtimereporthistory.getReporturl());
                    }else{
                        map.put("reporturl", reportBaseUrl+realtimereporthistory.getReporturl());
                    }

                    map.put("cameraname",realtimereporthistory.getCameraname());

                    if(realtimereporthistory.getMajorpic().contains("http://")){
                        map.put("majorpic",realtimereporthistory.getMajorpic());
                    }else{
                        map.put("majorpic",reportBaseUrl+realtimereporthistory.getMajorpic());
                    }

                    map.put("createtime", DateUtils.parseDateStr(realtimereporthistory.getCreatetime(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
                    webSocketMessage.setData(map);
                    WebSocket.sendMessage(WebSocket.OUTLETID,monitorId+"_"+realtimereporthistory.getOutletid(),webSocketMessage);
                }
            }
        }
        return GlobalApiResult.success("success");
    }

    @ApiOperation(value="员工打卡")
    @PostMapping("/employeeChecking")
    public GlobalApiResult<String> employeeChecking(@RequestBody EmployeeCheckingRequestVO employeeCheckingRequestVO){
        logger.info("收到员工打卡通知："+employeeCheckingRequestVO);
        if(employeeCheckingRequestVO!=null && employeeCheckingRequestVO.getMonitorId()!=null && employeeCheckingRequestVO.getOutletId()!=null){
            int monitorId = Integer.valueOf(employeeCheckingRequestVO.getMonitorId());
            int outletId = Integer.valueOf(employeeCheckingRequestVO.getOutletId());
            List<RecordVO> records = employeeCheckingRequestVO.getRecords();
            logger.info("收到员工打卡通知records数据为："+JSON.toJSONString(records));
            if(records!=null && records.size()>0){
                logger.info("开始遍历records：");
                for(RecordVO record : records){
                    logger.info("开始遍历records："+JSON.toJSONString(record));
                    String employeeId = vmcServerService.getEmployeeIdByCardId(monitorId,outletId,record.getCardId());
                    if(!StringUtil.isBlank(employeeId)){
                        int type = vmcServerService.getAccessDeviceTypeByDeviceId(monitorId,outletId,record.getDeviceId());
                        logger.info("查询type类型为："+type);
                        if(type==1 || type==2){

                            logger.info("开始封装records给websocket传到前端");
                            Map<String,Object> permap=homePageService.personManage(monitorId,outletId);
                            WebSocketMessageVO personSocket = new WebSocketMessageVO();
                            personSocket.setType(13);
                            personSocket.setData(permap);
                            WebSocket.sendMessage(WebSocket.OUTLETID,monitorId+"_"+outletId,personSocket);
                            logger.info("封装records给websocket传到前端的数据为："+JSON.toJSONString(personSocket));
                        }
                    }
                }
                logger.info("遍历结束");
            }
        }
        logger.info("业务执行结束");
        return GlobalApiResult.success("success");
    }

    @ApiOperation(value="周界推图")
    @GetMapping("/pushImage/{monitorId}/{outletId}/{id}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "monitorId", value = "运营中心id", required = true , dataType="int", paramType="path"),
        @ApiImplicitParam(name = "outletId", value = "工地id", required = true , dataType="int", paramType="path"),
        @ApiImplicitParam(name = "id", value = "周界图片记录id", required = true , dataType="int", paramType="path")})
    public GlobalApiResult<String> pushImage(@PathVariable int monitorId,@PathVariable int outletId,@PathVariable int id){
        logger.info("收到周界推图通知");
        Map<String,Object> perimeterPictures = perimeterManagementService.getPerimeterPicturesById(monitorId,outletId,id);
        WebSocketMessageVO webSocketMessage = new WebSocketMessageVO();
        List<Map<String,Object>> mapList = new ArrayList<>();
        mapList.add(perimeterPictures);
        Map<String,Object> map = new HashMap<>();
        map.put("data",mapList);
        webSocketMessage.setData(map);
        webSocketMessage.setType(6);
        WebSocket.sendMessage(WebSocket.OUTLETID,monitorId+"_"+outletId,webSocketMessage);
        return GlobalApiResult.success("success");
    }

    @ApiOperation(value="平台推送气体")
    @PostMapping("/syncGas/{outletid}")
    public GlobalApiResult<String> syncGasVo(@PathVariable Integer outletid,@RequestBody GasVo gasVo){
        logger.info("接收气体数据:[{}]============================================================="+JSON.toJSONString(gasVo));
        logger.info("outletid为:[{}]============================================================="+outletid);
        if(gasVo != null) {
            if (outletid == 2) {
                logger.info("收到平台推送气体:[{}]" + JSON.toJSONString(gasVo));
                gasService.syncGasVo(1, outletid, gasVo);
            }
            if (outletid == 3) {
                vmcServerService.insertGaswdz(1, outletid, gasVo);
            }
            if (outletid == 5 ){
                Float fx = Float.valueOf(gasVo.getWinddirection());
                logger.info("风向为==============："+StringUtil.windDirectionSwitch(fx));
                gasVo.setWinddirection(StringUtil.windDirectionSwitch(fx));
                vmcServerService.insertGaswdzhbx(1, outletid, gasVo);
            }
            WebSocketMessageVO gasSocket = new WebSocketMessageVO();
            Map<String, Object> list = homePageService.homePageGasPopupWindowInit(1, outletid);
            String pm25 = homePageService.queryPm25(1, outletid);
            ThresholdRespVO thresholdResp = homePageService.queryPm25ValueColer(1, outletid);
            Integer reportStatus=0;
            List<BigDecimal> listthres=new ArrayList<>();
            if (thresholdResp != null && StringUtils.isNotBlank(thresholdResp.getMax_val())){
                String str = thresholdResp.getMax_val();
                String color = str.substring(str.indexOf("#"),str.length()-2);
                list.put("color",color);
                lists(thresholdResp.getMax_val(),listthres);
                BigDecimal szpm25 = listthres.get(0);
                if (!StringUtil.isBlank(pm25)){
                    BigDecimal pm25va  = new  BigDecimal(pm25);
                    //如果传过来的值比设置的阈值大则报警
                    if (pm25va .compareTo(szpm25) > 0){
                        reportStatus = 1;
                    }  else {
                        // 否则不报警
                        reportStatus = 0;
                    }
                }
            }
            String evacuate = "";
            if (reportStatus == 1){
                evacuate = "yes";
            }else {
                evacuate = "no";
            }
            //查询喷淋IO控制器
            List<String> devicenames = homePageService.selectDeviceNameByDevicetypes(1, outletid, "2");
            logger.info("报警状态YesOrNo:==================================："+evacuate);
            String status =null;
            try {
           for (String devicename:devicenames) {
               if (!StringUtil.isBlank(devicename)) {
                   IocontrollerVO iocontrollerVO = homePageService.selectRoad(1, devicename, outletid);
                   HashMap<String, String> map1 = new HashMap<>();
                   if (!StringUtil.isBlank(iocontrollerVO.getRoad())){
                       map1.put("roadnum",iocontrollerVO.getRoad());
                   }
                   map1.put("evacuate",evacuate);
                   map1.put("outletId",outletid.toString());
                   if (StringUtil.isBlank(iocontrollerVO.getIodeviceid())){
                       map1.put("ionumID","");
                   }else {
                       map1.put("ionumID",iocontrollerVO.getIodeviceid());
                   }
                   String url = iobaseurl;
                   StringEntity stringEntity = new StringEntity(JSON.toJSONString(map1), "UTF-8");
                   String result = MyHttpClientPool.fetchByPostMethod(url, stringEntity);
                   JSONObject ioresult = JSONObject.parseObject(result);
                   Object sta = ioresult.get("result");
                   status = sta.toString();
                   logger.info("调用VMCIO接口返回结果================" + status);
                   //更新喷淋status
                   homePageService.updateStatus(1, status, outletid, iocontrollerVO.getIodeviceid());
                   list.put(iocontrollerVO.getIodeviceid(), sta);
                   Thread.currentThread().sleep(500);
               }
           }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.put("reportStatus",reportStatus);
            logger.info("报警状态 1为报警 ，0为不报警 ：==========="+reportStatus);
            gasSocket.setType(14);
            gasSocket.setData(list);
            WebSocket.sendMessage(WebSocket.OUTLETID, 1 + "_" + outletid, gasSocket);
        }
        return GlobalApiResult.success("success");
    }
    private List<BigDecimal> lists(String str,List<BigDecimal> list){
        Map maps = (Map) JSON.parse(str);
        for (Object map : maps.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
            list.add(new BigDecimal(((Map.Entry)map).getKey()+""));
        }
        return list;
    }

    /**
     * vmc与大屏交互接口
     * @param vehicleScreenVo
     * @return
     */
    @ApiOperation(value="vmc与大屏交互接口")
    @PostMapping("/vehicleScreen")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monitorid", value = "运营中心id", required = true, dataType = "String", paramType="query"),
            @ApiImplicitParam(name = "outletid", value = "工地id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "最新uuid", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "entryoutype", value = "进出类型0进，1出", required = true, dataType = "String", paramType = "query")
    })
    public GlobalApiResult<String> vehicleScreen(@RequestBody VehicleScreenVo vehicleScreenVo){
        //根据uuid和进出类型查询最新数据
        logger.info("根据uuid和进出类型查询最新数据");
        VehicleScreenVO vehicleScreenVO=vehicleService.queryVehicleScreen(vehicleScreenVo.getMonitorid(),Integer.valueOf(vehicleScreenVo.getOutletid()),vehicleScreenVo.getUuid(),vehicleScreenVo.getEntryoutype());

        if(vehicleScreenVO != null){
            WebSocketMessageVO webSocketMessage = new WebSocketMessageVO();
            webSocketMessage.setData(vehicleScreenVO);
            webSocketMessage.setType(8);
            WebSocket.sendMessage(WebSocket.OUTLETID,vehicleScreenVo.getMonitorid()+"_"+vehicleScreenVo.getOutletid(),webSocketMessage);
            logger.info("根据uuid和进出类型查询最新数据给websocket =="+JSONObject.toJSONString(webSocketMessage));
        }
        logger.info("根据uuid和进出类型查询最新数据结束");
        return GlobalApiResult.success("success");
    }




    @ApiOperation(value="水位接收与大屏接口")
    @PostMapping("/syncWaterScreen")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_id", value = "设备id", required = true, dataType = "String", paramType="query"),
            @ApiImplicitParam(name = "time", value = "时间 年月日时分秒", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "water_level", value = "水位数据", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "outletid", value = "工地id", required = true, dataType = "Integer", paramType = "query")
    })
    public GlobalApiResult<Object> syncWaterScreen(@RequestBody WaterScreenVo waterScreenVo, HttpServletResponse response) throws ParseException {

        waterLevelService.save(1,waterScreenVo.getOutletid(),waterScreenVo);
        //注：这里第三方平台传过来数据没有携带monitorId和outletId，
        // 故暂时写死（目前运营中心和工地的id是固定的），看看后续能不能改进
       // Map<String,Object> map=waterLevelService.queryWaterScreen(1,2);
        logger.info("水位接收与大屏接口开始调用"+JSON.toJSONString(waterScreenVo));
        if(waterScreenVo != null){

            Map<String,Object> homemap=homePageService.foundManage(1,waterScreenVo.getOutletid());
            WebSocketMessageVO homepage = new WebSocketMessageVO();
            homepage.setType(12);
            homepage.setData(homemap);
            WebSocket.sendMessage(WebSocketTwo.OUTLETID,1+"_"+waterScreenVo.getOutletid(),homepage);
            logger.info("水位接收与大屏接口开始结束"+JSON.toJSONString(waterScreenVo));
            //
        }
        return GlobalApiResult.success("success");
    }


    @ApiOperation(value="收到基坑进出")
    @PostMapping("/foundinout")
    public GlobalApiResult<Object> foundinout(@RequestBody FountVo fountVo) throws ParseException {
        logger.info("收到基坑进出："+fountVo);
        if(fountVo!=null && fountVo.getMonitorId()!=null && fountVo.getOutletId()!=null){
            int monitorId = Integer.valueOf(fountVo.getMonitorId());
            int outletId = Integer.valueOf(fountVo.getOutletId());
            homePageService.saveFoundinout(monitorId,outletId,fountVo);

            Map<String,Object> homemap=homePageService.foundManage(monitorId,outletId);
            WebSocketMessageVO homepage = new WebSocketMessageVO();
            homepage.setType(12);
            homepage.setData(homemap);
            WebSocket.sendMessage(WebSocketTwo.OUTLETID,monitorId+"_"+outletId,homepage);
            logger.info("收到基坑进出数据为"+JSON.toJSONString(homepage));
            Map<String, Object> ledmap=homePageService.queryLedDisplay(monitorId,outletId);
            return GlobalApiResult.success(ledmap);
        }

        logger.info("业务执行结束");
        return GlobalApiResult.failure("业务执行结束");
    }



        @ApiOperation(value="接收盾构数据")
        @PostMapping("/syncShieldqhx")
        @ResponseBody
        public GlobalApiResult<String> syncShield(@RequestBody ShieldEs shield){
            System.out.println(JSON.toJSONString(GlobalApiResult.success("success")));
            logger.info("接收盾构数据:[{}]"+JSON.toJSONString(shield));
            List<Object> esList=new ArrayList<>();
            if(shield != null){
                final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials("elastic", "secusoftofzhrmghg2019@123"));
                RestClient restClient = RestClient.builder(new HttpHost("es-cn-mp916vnnf000j15g8.public.elasticsearch.aliyuncs.com", 9200))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        }).build();
                try {
                    //因为推过来的数据有时会没有平均值，所以选择平均值存数据库，不影响es取值，不然取出来的最新值是没有平均值的，因为es是按时间排序取最新一条数据的。
                    Thresholdvertocal thresholdvertocal = null;
                    //如果传过来的水平位移、垂直位移不为空存入db，查询出来
                    if (StringUtils.isNotBlank(shield.getAvgDaily_db47_56())&&StringUtils.isNotBlank(shield.getAvgDaily_db47_52())){
                       thresholdvertocal = thresholdService.saveLevelAndVertical(1, shield.getAvgDaily_db47_52(), shield.getAvgDaily_db47_56(), shield.getOutletid());
                    }else {
                        //如果传过来的水平位移、垂直位移空直接从db查询出来
                        thresholdvertocal =  thresholdService.queryLevelAndVertical(1,shield.getOutletid());
                        //如果第一次没有值则设置为0
                        if (thresholdvertocal == null){
                            thresholdvertocal =  new Thresholdvertocal();
                            thresholdvertocal.setLevel("0");
                            thresholdvertocal.setVertical("0");
                            thresholdvertocal.setOutletid(shield.getOutletid());
                        }
                    }
                    progressstatisticsService.saveProgressstatistics(1,shield.getOutletid(),shield.getDb49_404(),shield.getShieldno());
                    Map<String,Object> map1=new HashMap<>();
                    Map<String,Object> map= progressstatisticsService.queryatestWeek(1,shield.getOutletid(),shield.getShieldno(),map1);
                    shield.setAvgDaily_db47_52(thresholdvertocal.getLevel());
                    shield.setAvgDaily_db47_56(thresholdvertocal.getVertical());
                    HttpEntity entity = new NStringEntity(JSON.toJSONString(shield), ContentType.APPLICATION_JSON);
                    Response indexResponse = restClient.performRequest(
                            "POST",
                            "/shields7s/shield7",
                            Collections.<String, String>emptyMap(),
                            entity);
                    String shieldss = JSON.toJSONString(shield);
                    logger.info("盾构数据已经存入es========================================================================");
                    map1.put("es",JSONObject.parseObject(shieldss));
                    map1.put("mysql",map);
                    esList.add(map1);
                    WebSocketMessageVO webSocketMessage = new WebSocketMessageVO();
                    webSocketMessage.setType(00);
                    webSocketMessage.setData(esList);
                    WebSocketTwo.sendMessage(WebSocketTwo.OUTLETID,1+"_"+shield.getOutletid(),webSocketMessage);
                    return GlobalApiResult.success("success");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return GlobalApiResult.failure("false");

        }

    @ApiOperation(value="8.2接收盾构数据")
    @PostMapping("/syncShieldsIs8")
    @ResponseBody
    public GlobalApiResult<String> syncShieldsIs8(@RequestBody Shields8 shields8) {
        System.out.println(JSON.toJSONString(GlobalApiResult.success("success")));
        logger.info("接收盾构数据:[{}]"+JSON.toJSONString(shields8));
        List<Object> esList=new ArrayList<>();
        if(shields8 != null){
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials("elastic", "secusoftofzhrmghg2019@123"));
            RestClient restClient = RestClient.builder(new HttpHost("es-cn-mp916vnnf000j15g8.public.elasticsearch.aliyuncs.com", 9200))
                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        }
                    }).build();
            try {

                HttpEntity entity = new NStringEntity(JSON.toJSONString(shields8), ContentType.APPLICATION_JSON);
                Response indexResponse = restClient.performRequest(
                        "POST",
                        "/shields8s/shields",
                        Collections.<String, String>emptyMap(),
                        entity);
                System.out.println("outletid=================:"+shields8.getOutletid());
                progressstatisticsService.saveProgressstatistics(1,shields8.getOutletid(),shields8.getHpjsq(),shields8.getSbbh());
                Map<String,Object> map1=new HashMap<>();
                Map<String,Object> map= progressstatisticsService.queryatestWeek(1,shields8.getOutletid(),shields8.getSbbh(),map1);
                String shield1 = JSON.toJSONString(shields8);
                System.out.println("数据："+JSONObject.parseObject(shield1));
                map1.put("es",JSONObject.parseObject(shield1));
                map1.put("mysql",map);
                esList.add(map1);
                WebSocketMessageVO webSocketMessage = new WebSocketMessageVO();
                webSocketMessage.setType(00);
                webSocketMessage.setData(esList);
                WebSocketTwo8.sendMessage(WebSocketTwo8.OUTLETID,1+"_"+shields8.getOutletid(),webSocketMessage);
                return GlobalApiResult.success("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return GlobalApiResult.failure("false");

    }
    @ApiOperation(value="8.2接收导向数据")
    @PostMapping("/syncTunnelIs8")
    @ResponseBody
    public GlobalApiResult<String> syncTbmIs8(@RequestBody Tbm8 tbm8) {
        System.out.println(JSON.toJSONString(GlobalApiResult.success("success")));
        logger.info("接收盾构数据:[{}]"+JSON.toJSONString(tbm8));
        List<Object> esList=new ArrayList<>();
        if(tbm8 != null){
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials("elastic", "secusoftofzhrmghg2019@123"));
            RestClient restClient = RestClient.builder(new HttpHost("es-cn-mp916vnnf000j15g8.public.elasticsearch.aliyuncs.com", 9200))
                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        }
                    }).build();
            try {
                HttpEntity entity = new NStringEntity(JSON.toJSONString(tbm8), ContentType.APPLICATION_JSON);
                Response indexResponse = restClient.performRequest(
                        "POST",
                        "/tunnel8/tbm",
                        Collections.<String, String>emptyMap(),
                        entity);
//              progressstatisticsService.saveProgressstatistics(1,shields8.getOutletid(),shields8.getDb49_404(),shields8.getShieldno());
                Map<String,Object> map1=new HashMap<>();
//              Map<String,Object> map= progressstatisticsService.queryatestWeek(1,shield.getOutletid(),shield.getShieldno(),map1);
                String tb8s = JSONObject.toJSONString(tbm8);
                map1.put("es",JSONObject.parseObject(tb8s));
                esList.add(map1);
                WebSocketMessageVO webSocketMessage = new WebSocketMessageVO();
                webSocketMessage.setType(00);
                webSocketMessage.setData(esList);
                WebSocketToTunnel8.sendMessage(WebSocketTwo8.OUTLETID,1+"_"+tbm8.getOutletid(),webSocketMessage);
                return GlobalApiResult.success("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return GlobalApiResult.failure("false");

    }

    @ApiOperation(value="更新在线状态")
    @PostMapping("/updateIoStatus")
    public Object updateIoStatus(@RequestBody List<IoStatus> ioStatus) {
        if (ioStatus == null){
            return GlobalApiResult.failure("参数不能为空");
        }
        Object result = homePageService.updateOnline(1, ioStatus);
        IoOnline OnlineAndStstus = null;
        for (IoStatus io : ioStatus) {
             OnlineAndStstus = homePageService.queryIoDevice(1,io.getIodeviceid());
        }
        WebSocketMessageVO webSocketMessage = new WebSocketMessageVO();
        webSocketMessage.setData(OnlineAndStstus);
        webSocketMessage.setType(20);
        WebSocket.sendMessage(WebSocket.OUTLETID,1+"_"+OnlineAndStstus.getOutletid(),webSocketMessage);
        logger.info("最新数据给websocket =="+JSONObject.toJSONString(webSocketMessage));
        return result;
    }

}
