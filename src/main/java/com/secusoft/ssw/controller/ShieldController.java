package com.secusoft.ssw.controller;

import com.alibaba.fastjson.JSON;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.Shieldadvance;
import com.secusoft.ssw.model.monitor.Threshold;
import com.secusoft.ssw.model.monitor.Thresholdvertocal;
import com.secusoft.ssw.model.viewobject.Request.*;
import com.secusoft.ssw.model.viewobject.Response.DangerousVO;
import com.secusoft.ssw.service.ShieldService;
import com.secusoft.ssw.service.ThresholdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/shield")
@Api(value="Shield-Controller" , description="盾构相关接口")
public class ShieldController extends BaseController{

    @Autowired
    ShieldService shieldService;

    @Value("${shield.image.path}")
    private String shieldImagePath;


    @Autowired
    ThresholdService thresholdService;

    @ApiOperation(value = "web上传盾构图片")
    @PostMapping("/uploadShieldImage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    public GlobalApiResult<String> uploadShieldImage(@RequestParam(value = "image",required = true) MultipartFile image){
        String outletid=getCurrentOutletId().toString();
        return GlobalApiResult.success(ResourceController.saveFile(shieldImagePath+"/"+outletid,image,"/resource/getUploadShieldImage/"+outletid+"/"));
    }




    @ApiOperation(value="获取盾构姿态报警状态值及颜色")
    @GetMapping("/getShield")
    @ResponseBody
    public GlobalApiResult<Object> getShield(@RequestParam String datetime) {
        Integer levelstatus=0;// 0不报警 1报警   水平位移报警状态
        Integer verticalstatus=0;// 0不报警 1报警 垂直位移报警状态
        Threshold thresholdendlevel=thresholdService.queryLevelThreshold(getCurrentMonitorId(),getCurrentOutletId(),datetime);
        Threshold thresholdendvertical=thresholdService.queryVerticalThreshold(getCurrentMonitorId(),getCurrentOutletId(),datetime);
        Thresholdvertocal thresholdvertocal = null;
        //如果传过来的水平位移、垂直位移不为空存入db，查询出来

            //如果传过来的水平位移、垂直位移空直接从db查询出来
        thresholdvertocal =  thresholdService.queryLevelAndVertical(getCurrentMonitorId(),getCurrentOutletId());
        if (thresholdvertocal == null){
            thresholdvertocal =  new Thresholdvertocal();
            thresholdvertocal.setLevel("0");
            thresholdvertocal.setVertical("0");
        }

        HashMap<String, Object> map = new HashMap<>();
        //水平位移
        List<BigDecimal> listthres=new ArrayList<>();
        if (thresholdendlevel != null && StringUtils.isNotBlank(thresholdendlevel.getMax_val())){
            String str = thresholdendlevel.getMax_val();
            String levelcoler = str.substring(str.indexOf("#"),str.length()-2);
            map.put("levelcoler",levelcoler);
            lists(thresholdendlevel.getMax_val(),listthres);
            BigDecimal level = listthres.get(0);
            BigDecimal avgDaily_db_47_52  = new  BigDecimal(thresholdvertocal.getLevel());
            //如果传过来的水平平均值比设置的阈值大则报警
            if (avgDaily_db_47_52 .compareTo(level) > 0){
                levelstatus = 1;
            }  else {
                // 否则不报警
                levelstatus = 0;
            }
        }else {
            levelstatus = 0;
        }
        //垂直位移
        List<BigDecimal> listthres1=new ArrayList<>();
        if (thresholdendvertical != null && StringUtils.isNotBlank(thresholdendvertical.getMax_val())){
            String str = thresholdendvertical.getMax_val();
            String verticalcoler = str.substring(str.indexOf("#"),str.length()-2);
            map.put("verticalcoler",verticalcoler);
            System.out.println("coler============:"+verticalcoler);
            lists(thresholdendvertical.getMax_val(),listthres1);
            BigDecimal vertical = listthres1.get(0);
            System.out.println("value============:"+vertical);
            BigDecimal avgDaily_db_47_56  = new  BigDecimal(thresholdvertocal.getVertical());

            //vertical
            if (avgDaily_db_47_56.compareTo(vertical) > 0){
                verticalstatus = 1;
            }  else {
                // 否则不报警
                verticalstatus = 0;
            }
        }else {
            verticalstatus = 0;
        }
        map.put("levelstatus",levelstatus);
        map.put("verticalstatus",verticalstatus);

        return GlobalApiResult.success(map);
    }

    private List<BigDecimal> lists(String str,List<BigDecimal> list){
        Map maps = (Map) JSON.parse(str);
        for (Object map : maps.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
            list.add(new BigDecimal(((Map.Entry)map).getKey()+""));
        }
        return list;
    }


    @ApiOperation(value="保存或修改盾构推进信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "segmentno", value = "管片环号", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "construction", value = "施工单位", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "supervise", value = "监理单位", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tunnellingdate", value = "掘进日期", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tunnellingtime", value = "掘进时间", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "slipcastingtime", value = "注浆时间", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "slipcastingmeasure", value = "注浆方量", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "assembledate", value = "拼装日期", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "assembletime", value = "拼装时间", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "driver", value = "盾构主司机", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "segmentassemble", value = "管片拼装手", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "qualitymanager", value = "质量总管", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "beonduty", value = "值班工程师", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "foreman", value = "班组长", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "superviseengineer", value = "监理工程师", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "factory", value = "管片生产厂家", required = false, dataType = "String", paramType = "query")

    })
    @PostMapping("/saveOrUpdateShieldadvance")
    public GlobalApiResult<Object> saveOrUpdateShieldadvance(@RequestBody ShieldadvanceVO shieldadvanceVO){
        if (shieldadvanceVO == null){
            return GlobalApiResult.failure("输入信息不能为空");
        }
        return GlobalApiResult.success(shieldService.saveOrUpdateShieldadvance(getCurrentMonitorId(),getCurrentOutletId(),shieldadvanceVO));
    }

    /**
     * 获取盾构推进信息
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "tunnellingdate", value = "设备类型", required = true , dataType="String", paramType="query"),
                    @ApiImplicitParam(name = "currentPage", value = "当前页", required = true , dataType="String", paramType="query"),
                    @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true , dataType="String", paramType="query")
            })
    @GetMapping("/queryAllShieldadvance")
    @ApiOperation("获取盾构推进信息")
    public GlobalApiResult<Object> queryAllShieldadvance(Advance advance){

        return GlobalApiResult.success(shieldService.queryAllShieldadvance(getCurrentMonitorId(), getCurrentOutletId(),advance));

    }
    /**
     * 获取指定环号盾构推进信息
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "segmentno", value = "管片环号", required = true , dataType="Integer", paramType="query")
            })
    @GetMapping("/queryShieldadvance/{segmentno}")
    @ApiOperation("获取指定环号盾构推进信息")
    public GlobalApiResult<Object> queryShieldadvance(@PathVariable Integer segmentno){
        System.out.println("管片环号为===============================================："+segmentno);
        return GlobalApiResult.success(shieldService.queryShieldadvance(getCurrentMonitorId(), getCurrentOutletId(),segmentno));
    }

    /**
     * 删除单个及多个盾构推进信息
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "ids", value = "ids示：1,2,3 ", required = true , dataType="String", paramType="query")
            })
    @GetMapping("/deleteShieldadvance")
    @ApiOperation("删除盾构推进信息单个或多个1,2,3")
    public GlobalApiResult<Object> deleteShieldadvance(@Param("ids") String ids){

        return GlobalApiResult.success(shieldService.deleteShieldadvance(getCurrentMonitorId(), getCurrentOutletId(), Arrays.asList(ids.split(","))));

    }


    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "query"),
                    @ApiImplicitParam(name = "stages", value = "实时阶段", required = true, dataType = "Integer", paramType = "query"),
                    @ApiImplicitParam(name = "date", value = "日期", required = true, dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = "number", value = "环数", required = true, dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = "mileage", value = "里程", required = true , dataType="String", paramType="query"),
                    @ApiImplicitParam(name = "show", value = "说明", required = true , dataType="String", paramType="query")
            })
    @ApiOperation("保存修改盾构点位")
    @PostMapping("/saveOrUpdateShieldpoint")
    public Object saveOrUpdateShieldpoint(@RequestBody ShieldpointVO shieldpointVO){
        if (shieldpointVO == null){
            return GlobalApiResult.failure("输入信息不能为空");
        }
        return shieldService.saveOrUpdateShieldpoint(getCurrentMonitorId(),getCurrentOutletId(),shieldpointVO);
    }
    /**
     * 获取单个盾构点位信息
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "number", value = "环数", required = true , dataType="float", paramType="query")
            })
    @GetMapping("/selectShieldpoint/{number}")
    @ApiOperation("获取单个盾构点位信息")
    public GlobalApiResult<Object> selectShieldpoint(@PathVariable float number){
        return GlobalApiResult.success(shieldService.selectShieldpoint(getCurrentMonitorId(), getCurrentOutletId(),number));
    }

    /**
     * 获取全部点位信息
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            })
    @GetMapping("/selectAllShieldpoint")
    @ApiOperation("获取所有盾构点位信息")
    public GlobalApiResult<Object> selectAllShieldpoint(){
        return GlobalApiResult.success(shieldService.selectAllShieldpoint(getCurrentMonitorId(), getCurrentOutletId()));
    }

    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "pointname", value = "点位名称", required = true , dataType="String", paramType="query"),
                    @ApiImplicitParam(name = "remarks", value = "备注", required = true , dataType="String", paramType="query")

            })
    @ApiOperation("保存修改危险点位")
    @PostMapping("/saveOrUpdateDangerous")
    public GlobalApiResult<Object> saveOrUpdateDangerous(@RequestBody DangerousVO dangerousVO){
        if (dangerousVO == null){
            return GlobalApiResult.failure("输入信息不能为空");
        }
        return GlobalApiResult.success(shieldService.saveOrUpdateDangerous(getCurrentMonitorId(),getCurrentOutletId(),dangerousVO));
    }

    /**
     * 获取单个盾构点位信息
     */
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                    @ApiImplicitParam(name = "id", value = "环数", required = true , dataType="Integer", paramType="query")
            })
    @GetMapping("/selectDangerous/{id}")
    @ApiOperation("获取单个危险点位信息")
    public GlobalApiResult<Object> selectDangerous(@PathVariable Integer id){
        return GlobalApiResult.success(shieldService.selectDangerous(getCurrentMonitorId(), getCurrentOutletId(),id));
    }


}
