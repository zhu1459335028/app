package com.secusoft.ssw.controller;

import com.github.pagehelper.PageInfo;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.Settlement;
import com.secusoft.ssw.model.monitor.ThresholdName;
import com.secusoft.ssw.model.viewobject.Request.*;
import com.secusoft.ssw.service.SettlementService;
import com.secusoft.ssw.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/settlement")
@Api(value="Settlement-Controller" , description="测量测绘(沉降)相关接口")
public class SettlementController extends BaseController{


    @Autowired
    SettlementService settlementService;
      
    @ApiOperation(value="根据日期查询获取沉降相关测量测绘数据并分页")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "startTime", value = "开始时间时间戳", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间时间戳", required = true , dataType="String", paramType="query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true , dataType="int", paramType="query")})
    @GetMapping("/getByTimerange")
    public GlobalApiResult<Object> getByTimerange(@RequestParam String startTime,@RequestParam String endTime, int currentPage, int pageSize){
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
        PageInfo<Settlement> info = settlementService.getSettlement(getCurrentMonitorId(), getCurrentOutletId(), startDate , endDate, currentPage, pageSize);
        return GlobalApiResult.success(info);
    }

      
    @ApiOperation(value="分页获取所有沉降相关测量测绘数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true , dataType="int", paramType="query"),
            @ApiImplicitParam(name = "settleunitmonid", value = "点位类型id 不传查询默认", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true , dataType="int", paramType="query")})
    @GetMapping("/getAll")
    public GlobalApiResult<Object> getAllSettlement(int currentPage, int pageSize,Integer settleunitmonid){
        
        return GlobalApiResult.success(settlementService.getAllSettlement(getCurrentMonitorId(),getCurrentOutletId(), currentPage, pageSize,settleunitmonid));
    }

    @ApiOperation(value="添加沉降相关测量测绘数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")})
    @PostMapping("/add")
    public GlobalApiResult<Object> addSettlement(@RequestBody Settlement settlement){
        return GlobalApiResult.success(settlementService.addSettlement(getCurrentMonitorId(),getCurrentOutletId(), settlement));
    }

    @ApiOperation(value="更新沉降相关测量测绘数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")})
    @PostMapping("/update")
    public GlobalApiResult<Object> updateSettlement(@RequestBody Settlement settlement){
        if(null==settlement.getId()){
            GlobalApiResult.failure("更新失败,对象必须包含id属性");
        }
        settlementService.updateSettlement(getCurrentMonitorId(),getCurrentOutletId(), settlement);
        return GlobalApiResult.success("更新成功");
    }

    @ApiOperation(value="删除沉降相关测量测绘数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header"),
                        @ApiImplicitParam(name = "id", value = "id", required = true , dataType="Integer", paramType="query")})
    @GetMapping("/delete")
    public GlobalApiResult<Object> deleteSettlement(Integer id){
        if(null==id){
            GlobalApiResult.failure("id必须不为空");
        }
        settlementService.deleteSettlement(getCurrentMonitorId(),getCurrentOutletId(), id);
        return GlobalApiResult.success("删除成功");
    }




    @Value("${settle.image.path}")
    private String settleImagePath;



    @ApiOperation(value = "web上传测绘测量图片接口")
    @PostMapping("/uploadSettleImage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    public GlobalApiResult<String> uploadSettleImage(@RequestParam(value = "image",required = true) MultipartFile image){
        String outletid=getCurrentOutletId().toString();
        return GlobalApiResult.success(ResourceController.saveFile(settleImagePath+"/"+outletid,image,"/resource/getUploadSettleImage/"+outletid+"/"));
    }




    /**
     *阈值条目
     */
    @ApiOperation(value = "添加或者修改阈值条目类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "添加时不传id修改时传id", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "name", value = "中文名称", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "type", value = "阈值条目类型：1预警，2报警，3严重", required = true , dataType="Integer", paramType="query")
    })
    @GetMapping("/saveOrUpdatethresholdname")
    public GlobalApiResult<Object> saveOrUpdatethresholdname(ThresholdName thresholdName){
        return GlobalApiResult.success(settlementService.saveOrUpdatethresholdname(getCurrentMonitorId(),getCurrentOutletId(),thresholdName));
    }


    /**
     *添加点位类型==新增
     */
    @ApiOperation(value = "添加或者修改点位类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "添加时不传id修改时传id", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "name", value = "中文名称", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "code", value = "英文编码", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "type", value = "监测点类型：1沉降、2斜侧、3位移、4轴力、5水位", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "unit", value = "对应单位", required = true , dataType="Integer", paramType="query")
    })
    @GetMapping("/saveOrUpdateSettleunitmon")
    public GlobalApiResult<Object> saveOrUpdateSettleunitmon(SettleunitmonVO settleunitmonVO){
        return GlobalApiResult.success(settlementService.saveOrUpdateSettleunitmon(getCurrentMonitorId(),getCurrentOutletId(),settleunitmonVO));
    }


    /**
     *删除点位类型==新增
     */
    @ApiOperation(value = "删除点位类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "ids", value = "点位类型id示例： 1,2,3", required = true , dataType="String", paramType="query")
    })
    @GetMapping("/deleteSettleunitmon")
    public GlobalApiResult<Object> deleteSettleunitmon(@Param("ids") String ids){
        return GlobalApiResult.success(settlementService.deleteSettleunitmon(getCurrentMonitorId(),getCurrentOutletId(), Arrays.asList(ids.split(","))));
    }

    /**
     *获取点位类型下拉列表==新增
     */
    @ApiOperation(value = "获取点位类型下拉列表")
    @ApiImplicitParam(name = "token", value = "身份凭证", required = true , dataType="String", paramType="header")
    @GetMapping("/selectSettleunitmon")
    public GlobalApiResult<List<SettleunitmonVO>> selectSettleunitmon(){
        return GlobalApiResult.success(settlementService.selectSettleunitmon(getCurrentMonitorId(),getCurrentOutletId()));
    }

    /**
     * 根据点位类型获取阈值条目
     */
    @ApiOperation(value = "根据点位类型获取阈值条目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "点位类型id", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "locationno", value = "点位编号", required = false , dataType="String", paramType="query")
    })
    @GetMapping("/queryThresholdName")
    public GlobalApiResult<List<ThresholdName>> queryThresholdName(ThresholdNameVO thresholdNameVO){
        return GlobalApiResult.success(settlementService.queryThresholdName(getCurrentMonitorId(),getCurrentOutletId(),thresholdNameVO));
    }

    /**
     *
     * 11
     * 根据点位类型添加(不需要传id)/或修改阈值组(需要传id)
     */
    @ApiOperation(value = "根据点位类型添加/或修改阈值组--公有阈值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "添加时不传id修改时传id", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "settleunitmonid", value = "点位类型id", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "thrnameid", value = "阈值条目id", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "value", value = "阈值示例：1,2", required = true , dataType="Integer", paramType="query")
    })
    @PostMapping("/saveOrUpdateThresholdList")
    public GlobalApiResult<Object> saveOrUpdateThresholdList(@RequestBody  List<ThresholdvalueVO> list){
        return GlobalApiResult.success(settlementService.addThresholdList(getCurrentMonitorId(),getCurrentOutletId(),list));
    }


    /**
     * 根据点位编号删除点位
     */
    @ApiOperation(value = "根据点位编号删除点位,且删除该点位的私有阈值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "locationno", value = "点位编号", required = true , dataType="String", paramType="query")
    })
    @PostMapping("/deletePoint")
    public GlobalApiResult<Object> deletePoint(@RequestBody String locationno){
        return GlobalApiResult.success(settlementService.deletePoint(getCurrentMonitorId(),getCurrentOutletId(),locationno));
    }


    /**
     *
     * 11-1
     * 根据点位类型添加(不需要传id)/或修改阈值组(需要传id)
     */
    @ApiOperation(value = "添加点位时添加该点位的私有阈值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "添加时不传id修改时传id", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "settleunitmonid", value = "点位类型id", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "thrnameid", value = "阈值条目id", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "value", value = "阈值示例：1,2", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "x", value = "x轴", required = true , dataType="Float", paramType="query"),
            @ApiImplicitParam(name = "y", value = "y轴", required = true , dataType="Float", paramType="query"),
            @ApiImplicitParam(name = "settleimageid", value = "图片id", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "locationno", value = "测点编号(后台会检验惟一性)", required = true , dataType="String", paramType="query")
    })
    @PostMapping("/saveOrUpdateLocationNoThresholdList")
    public GlobalApiResult<Object> saveOrUpdateLocationNoThresholdList(@RequestBody  List<ThresholdvalueTwoVO> list){

        GlobalApiResult<Object> result=settlementService.saveOrUpdateLocationNoThresholdList(getCurrentMonitorId(),getCurrentOutletId(),list);
        return result;
    }
    //添加点位接口

    /**
     * 222
     * 添加点位接口
     * @param list
     * @return
     */
    @ApiOperation(value = "添加点位接口/请求参数用数json数组据组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "x", value = "x轴", required = true , dataType="Float", paramType="query"),
            @ApiImplicitParam(name = "y", value = "y轴", required = true , dataType="Float", paramType="query"),
            @ApiImplicitParam(name = "settleimageid", value = "图片id", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "settleunitmonid", value = "监测点类型id", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "locationno", value = "测点编号(后台会检验惟一性)", required = true , dataType="String", paramType="query")
    })
    @PostMapping("/addsettleunitmon")
    public GlobalApiResult<Object> addsettleunitmon(@RequestBody List<SettleimageunitmonVO> list){
        GlobalApiResult<Object> result=settlementService.addsettleunitmon(getCurrentMonitorId(),getCurrentOutletId(),list);
        return result;
    }







    /**
     *保存测绘测量图片信息接口--1
     */
    @ApiOperation(value = "保存测绘测量图片信息接口")
    @PostMapping("/saveSettleImageInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "imgurl", value = "图片url", required = true , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "code", value = "图片code", required = false , dataType="Integer", paramType="query"),
            @ApiImplicitParam(name = "name", value = "图片名称", required = false , dataType="Integer", paramType="query")
    })
    public GlobalApiResult<Object> saveSettleImageInfo(@RequestBody  SettleimageVO settleimageVO){
        return GlobalApiResult.success(settlementService.saveSettleImageInfo(getCurrentMonitorId(),getCurrentOutletId(),settleimageVO));
    }


    /**
     *
     * @return--2
     */
    @ApiOperation(value = "查询测绘测量图片信息列表接口")
    @PostMapping("/querySettleImageInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
    })
    public GlobalApiResult<Object> querySettleImageInfo(){
        return GlobalApiResult.success(settlementService.querySettleImageInfo(getCurrentMonitorId(),getCurrentOutletId()));
    }



    @ApiOperation(value = "查询单张测绘测量图片信息列表接口")
    @PostMapping("/querySettleImageInfoById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "点位类型id", required = true , dataType="Integer", paramType="query")
    })
    public GlobalApiResult<Object> querySettleImageInfoById(@Param("id") Integer id){
        return GlobalApiResult.success(settlementService.querySettleImageInfoById(getCurrentMonitorId(),getCurrentOutletId(),id));
    }




    @ApiOperation(value = "测绘大屏")
    @PostMapping("/querySettleScreen")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    public GlobalApiResult<Object> querySettleScreen(){
        return GlobalApiResult.success(settlementService.querySettleScreen(getCurrentMonitorId(),getCurrentOutletId()));
    }


    @ApiOperation(value = "测绘大屏普通弹窗 type=1,3")
    @PostMapping("/querySettleScreenPopupWindow")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "locationno", value = "测点编号", required = true , dataType="Integer", paramType="query")
    })
    public GlobalApiResult<Object> querySettleScreenPopupWindow(@Param("locationno") String locationno){
        return GlobalApiResult.success(settlementService.querySettleScreenPopupWindow(getCurrentMonitorId(),getCurrentOutletId(),locationno));

    }


    @ApiOperation(value = "轴力弹窗  type=4")
    @PostMapping("/querySettleScreenPopupWindowAxialforce")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "locationno", value = "测点编号", required = true , dataType="Integer", paramType="query")
    })
    public GlobalApiResult<Object> querySettleScreenPopupWindowAxialforce(@Param("locationno") String locationno){
        return GlobalApiResult.success(settlementService.querySettleScreenPopupWindowAxialforce(getCurrentMonitorId(),getCurrentOutletId(),locationno));

    }


    @ApiOperation(value = "测斜弹窗 type=2")
    @PostMapping("/querySettleScreenPopupWindowSite")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "locationno", value = "测点编号", required = true , dataType="Integer", paramType="query")
    })
    public GlobalApiResult<Object> querySettleScreenPopupWindowSite(@Param("locationno") String locationno){
        return GlobalApiResult.success(settlementService.querySettleScreenPopupWindowSite(getCurrentMonitorId(),getCurrentOutletId(),locationno));

    }



    @ApiOperation(value = "水位弹窗 type=5")
    @PostMapping("/querySettleScreenPopupWindowWater")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "locationno", value = "测点编号", required = true , dataType="Integer", paramType="query")
    })
    public GlobalApiResult<Object> querySettleScreenPopupWindowWater(@Param("locationno") String locationno){
        return GlobalApiResult.success(settlementService.querySettleScreenPopupWindowWater(getCurrentMonitorId(),getCurrentOutletId(),locationno));

    }




    @ApiOperation(value = "导入测量excel")
    @PostMapping("/importexcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份凭证", required = true, dataType = "String", paramType = "header")
    })
    public GlobalApiResult<Object> importexcel(@RequestParam(value = "file",required = true) MultipartFile file) throws Exception {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)){
            //return "文件不能为空";
            return GlobalApiResult.failure("文件不能为空");
        }
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        if (!prefix.toLowerCase().contains("xls") && !prefix.toLowerCase().contains("xlsx") ){
            return GlobalApiResult.failure("文件格式异常，请上传Excel文件格式");

        }
        // 防止生成的临时文件重复-建议使用UUID
        final File excelFile = File.createTempFile(System.currentTimeMillis()+"", prefix);
        file.transferTo(excelFile);

        //由于2003和2007的版本所使用的接口不一样，所以这里统一用Workbook做兼容
        boolean isExcel2003 = prefix.toLowerCase().endsWith("xls")?true:false;
        Workbook workbook = null;
        if(isExcel2003){
            workbook = new HSSFWorkbook(new FileInputStream(excelFile));
        }else{
            workbook = new XSSFWorkbook(new FileInputStream(excelFile));
        }
        //Excel表中的内容
        List<Map<String,Object>> list = new ArrayList<>();
        List<Settlement> list1=new ArrayList<>();
        for(int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++){
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {//判断当前的sheet页有没有数据
                continue;//如果没有，则跳过当前的循环，进入下次循环
            }
            if(sheetNum==0){
                for(int i=1; i<sheet.getLastRowNum()+1; i++){
                    Row row =sheet.getRow(i);//得到当前行的每一列数据
                    row.getCell(0).setCellType(CellType.STRING);
                    row.getCell(1).setCellType(CellType.STRING);
                    row.getCell(2).setCellType(CellType.STRING);
                    row.getCell(3).setCellType(CellType.STRING);
                    row.getCell(4).setCellType(CellType.STRING);
                    row.getCell(5).setCellType(CellType.STRING);
                    row.getCell(6).setCellType(CellType.STRING);
                    row.getCell(7).setCellType(CellType.STRING);
                    row.getCell(8).setCellType(CellType.NUMERIC);
                    row.getCell(9).setCellType(CellType.STRING);
                    row.getCell(10).setCellType(CellType.STRING);
                    String location_no = row.getCell(0).getStringCellValue(); //测点编号
                    if (StringUtils.isEmpty(location_no)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+0+"列不能为空!");
                    }
                    String location = row.getCell(1).getStringCellValue(); //测点位置
                    if (StringUtils.isEmpty(location)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+1+"列不能为空!");
                    }
                    String last_val = row.getCell(2).getStringCellValue(); //上次变量
                    if (StringUtils.isEmpty(last_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+2+"列不能为空!");
                    }
                    String current_val = row.getCell(3).getStringCellValue(); //本次变量
                    if (StringUtils.isEmpty(current_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+3+"列不能为空!");
                    }

                    String accumulate_val = row.getCell(4).getStringCellValue(); //累计变量
                    if (StringUtils.isEmpty(accumulate_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+4+"列不能为空!");
                    }
                    String rate = row.getCell(5).getStringCellValue(); //变化速率
                    if (StringUtils.isEmpty(rate)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+5+"列不能为空!");
                    }
                    String equipment_no = row.getCell(6).getStringCellValue(); //仪器编号
                    if (StringUtils.isEmpty(equipment_no)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+6+"列不能为空!");
                    }
                    String equipment = row.getCell(7).getStringCellValue(); //仪器名称
                    if (StringUtils.isEmpty(equipment)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+7+"列不能为空!");
                    }
                    Date dt = row.getCell(8).getDateCellValue(); //监测日期
                    if (dt==null){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+8+"列不能为空!");
                    }
                    String remark = row.getCell(9).getStringCellValue(); //备注
                    if (StringUtils.isEmpty(remark)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+9+"列不能为空!");
                    }
                    String settleunitmonid = row.getCell(10).getStringCellValue(); //当前所属类型id
                    if (StringUtils.isEmpty(settleunitmonid)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+10+"列不能为空!");
                        //return "sheet"+sheetNum+"：中的第" + i+"行，第"+10+"列不能为空!";
                    }
                    Settlement settlement=new Settlement();
                    settlement.setLocation_no(location_no);
                    settlement.setLocation(location);
                    settlement.setLast_val(Float.valueOf(last_val));
                    settlement.setCurrent_val(Float.valueOf(current_val));
                    settlement.setAccumulate_val(Float.valueOf(accumulate_val));
                    settlement.setRate(Float.valueOf(rate));
                    settlement.setEquipment_no(equipment_no);
                    settlement.setEquipment(equipment);
                    settlement.setDt(dt);
                    settlement.setOutletid(getCurrentOutletId());
                    settlement.setSettleunitmonid(Integer.valueOf(settleunitmonid));
                    settlement.setRemark(remark);
                    list1.add(settlement);
                }
            }
            if(sheetNum==1){
                for(int i=1; i<sheet.getLastRowNum()+1; i++){
                    Row row =sheet.getRow(i);//得到当前行的每一列数据
                    row.getCell(0).setCellType(CellType.STRING);//
                    row.getCell(1).setCellType(CellType.STRING);
                    row.getCell(2).setCellType(CellType.STRING);
                    row.getCell(3).setCellType(CellType.STRING);
                    row.getCell(4).setCellType(CellType.STRING);
                    row.getCell(5).setCellType(CellType.STRING);
                    row.getCell(6).setCellType(CellType.STRING);
                    row.getCell(7).setCellType(CellType.STRING);
                    row.getCell(8).setCellType(CellType.STRING);
                    row.getCell(9).setCellType(CellType.NUMERIC);
                    row.getCell(10).setCellType(CellType.STRING);
                    row.getCell(11).setCellType(CellType.STRING);
                    String location_no = row.getCell(0).getStringCellValue(); //测点编号
                    if (StringUtils.isEmpty(location_no)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+0+"列不能为空!");
                    }
                    String location = row.getCell(1).getStringCellValue(); //测点位置
                    if (StringUtils.isEmpty(location)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+1+"列不能为空!");
                    }
                    String depth = row.getCell(2).getStringCellValue(); //深度
                    if (StringUtils.isEmpty(depth)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+2+"列不能为空!");
                    }
                    String current_val = row.getCell(3).getStringCellValue(); //本次变量
                    if (StringUtils.isEmpty(current_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+3+"列不能为空!");
                    }
                    String last_val = row.getCell(4).getStringCellValue(); //上次累计变量
                    if (StringUtils.isEmpty(last_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+4+"列不能为空!");
                    }
                    String accumulate_val = row.getCell(5).getStringCellValue(); //累计变量
                    if (StringUtils.isEmpty(accumulate_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+5+"列不能为空!");
                    }
                    String rate = row.getCell(6).getStringCellValue(); //变化速率
                    if (StringUtils.isEmpty(rate)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+6+"列不能为空!");
                    }
                    String equipment_no = row.getCell(7).getStringCellValue(); //仪器编号
                    if (StringUtils.isEmpty(equipment_no)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+7+"列不能为空!");
                    }
                    String equipment = row.getCell(8).getStringCellValue(); //仪器名称
                    if (StringUtils.isEmpty(equipment)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+8+"列不能为空!");
                    }
                    Date dt = row.getCell(9).getDateCellValue(); //监测日期
                    if (dt==null){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+9+"列不能为空!");
                    }
                    String remark = row.getCell(10).getStringCellValue(); //备注
                    if (StringUtils.isEmpty(remark)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+10+"列不能为空!");
                    }
                    String settleunitmonid = row.getCell(11).getStringCellValue(); //当前所属类型id
                    if (StringUtils.isEmpty(settleunitmonid)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+11+"列不能为空!");
                    }
                    Settlement settlement=new Settlement();
                    settlement.setLocation_no(location_no);
                    settlement.setLocation(location);
                    settlement.setLast_val(Float.valueOf(last_val));//上次累计变量
                    settlement.setDepth(Float.valueOf(depth));
                    settlement.setCurrent_val(Float.valueOf(current_val));
                    settlement.setAccumulate_val(Float.valueOf(accumulate_val));
                    settlement.setRate(Float.valueOf(rate));
                    settlement.setEquipment_no(equipment_no);
                    settlement.setEquipment(equipment);
                    settlement.setDt(dt);
                    settlement.setOutletid(getCurrentOutletId());
                    settlement.setSettleunitmonid(Integer.valueOf(settleunitmonid));
                    settlement.setRemark(remark);
                    list1.add(settlement);
                }
            }
            if(sheetNum==2){
                for(int i=1; i<sheet.getLastRowNum()+1; i++){
                    Row row =sheet.getRow(i);//得到当前行的每一列数据
                    row.getCell(0).setCellType(CellType.STRING);
                    row.getCell(1).setCellType(CellType.STRING);
                    row.getCell(2).setCellType(CellType.STRING);
                    row.getCell(3).setCellType(CellType.STRING);
                    row.getCell(4).setCellType(CellType.STRING);
                    row.getCell(5).setCellType(CellType.STRING);
                    row.getCell(6).setCellType(CellType.STRING);
                    row.getCell(7).setCellType(CellType.STRING);
                    row.getCell(8).setCellType(CellType.STRING);
                    row.getCell(9).setCellType(CellType.STRING);
                    row.getCell(10).setCellType(CellType.NUMERIC);
                    row.getCell(11).setCellType(CellType.STRING);
                    row.getCell(12).setCellType(CellType.STRING);
                    String location_no = row.getCell(0).getStringCellValue(); //测点编号
                    if (StringUtils.isEmpty(location_no)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+0+"列不能为空!");
                    }
                    String location = row.getCell(1).getStringCellValue(); //测点位置
                    if (StringUtils.isEmpty(location)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+1+"列不能为空!");
                    }
                    String last_val = row.getCell(2).getStringCellValue(); //上次轴力、
                    if (StringUtils.isEmpty(last_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+2+"列不能为空!");
                    }
                    String current_val = row.getCell(3).getStringCellValue(); //本次轴力
                    if (StringUtils.isEmpty(current_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+3+"列不能为空!");
                    }
                    String thecurrent_var = row.getCell(4).getStringCellValue(); //本次变化量
                    if (StringUtils.isEmpty(thecurrent_var)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+4+"列不能为空!");
                    }
                    String rate = row.getCell(5).getStringCellValue(); //变化速率
                    if (StringUtils.isEmpty(rate)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+5+"列不能为空!");
                    }
                    String maxalarm = row.getCell(6).getStringCellValue(); //本次变化量
                    if (StringUtils.isEmpty(maxalarm)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+6+"列不能为空!");
                    }
                    String minalarm = row.getCell(7).getStringCellValue(); //变化速率
                    if (StringUtils.isEmpty(minalarm)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+7+"列不能为空!");
                    }

                    String equipment_no = row.getCell(8).getStringCellValue(); //仪器编号
                    if (StringUtils.isEmpty(equipment_no)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+8+"列不能为空!");
                    }
                    String equipment = row.getCell(9).getStringCellValue(); //仪器名称
                    if (StringUtils.isEmpty(equipment)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+9+"列不能为空!");
                    }
                    Date dt = row.getCell(10).getDateCellValue(); //监测日期
                    if (dt==null){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+10+"列不能为空!");
                    }
                    String remark = row.getCell(11).getStringCellValue(); //备注
                    if (StringUtils.isEmpty(remark)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+11+"列不能为空!");
                    }
                    String settleunitmonid = row.getCell(12).getStringCellValue(); //当前所属类型id
                    if (StringUtils.isEmpty(settleunitmonid)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+12+"列不能为空!");
                    }
                    Settlement settlement=new Settlement();
                    settlement.setLocation_no(location_no);
                    settlement.setLocation(location);
                    settlement.setLast_val(Float.valueOf(last_val));//上次累计变量
                    settlement.setCurrent_val(Float.valueOf(current_val));
                    settlement.setThecurrent_var(Float.valueOf(thecurrent_var));
                    settlement.setRate(Float.valueOf(rate));
                    settlement.setMaxalarm(Float.valueOf(maxalarm));
                    settlement.setMinalarm(Float.valueOf(minalarm));
                    settlement.setEquipment_no(equipment_no);
                    settlement.setEquipment(equipment);
                    settlement.setDt(dt);
                    settlement.setRemark(remark);
                    settlement.setOutletid(getCurrentOutletId());
                    settlement.setSettleunitmonid(Integer.valueOf(settleunitmonid));
                    list1.add(settlement);
                }
            }
            if(sheetNum==3){
                for(int i=1; i<sheet.getLastRowNum()+1; i++){
                    Row row =sheet.getRow(i);//得到当前行的每一列数据
                    row.getCell(0).setCellType(CellType.STRING);
                    row.getCell(1).setCellType(CellType.STRING);
                    row.getCell(2).setCellType(CellType.STRING);
                    row.getCell(3).setCellType(CellType.STRING);
                    row.getCell(4).setCellType(CellType.STRING);
                    row.getCell(5).setCellType(CellType.STRING);
                    row.getCell(6).setCellType(CellType.STRING);
                    row.getCell(7).setCellType(CellType.STRING);
                    row.getCell(8).setCellType(CellType.STRING);
                    row.getCell(9).setCellType(CellType.NUMERIC);
                    row.getCell(10).setCellType(CellType.STRING);
                    row.getCell(11).setCellType(CellType.STRING);
                    String location_no = row.getCell(0).getStringCellValue(); //测点编号
                    if (StringUtils.isEmpty(location_no)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+0+"列不能为空!");
                    }
                    String location = row.getCell(1).getStringCellValue(); //测点位置
                    if (StringUtils.isEmpty(location)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+1+"列不能为空!");
                    }
                    String last_val = row.getCell(2).getStringCellValue(); //上次变量
                    if (StringUtils.isEmpty(last_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+2+"列不能为空!");
                    }
                    String current_val = row.getCell(3).getStringCellValue(); //本次变量
                    if (StringUtils.isEmpty(current_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+3+"列不能为空!");
                    }
                    String accumulate_val = row.getCell(4).getStringCellValue(); //累计变量
                    if (StringUtils.isEmpty(accumulate_val)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+4+"列不能为空!");
                    }
                    String rate = row.getCell(5).getStringCellValue(); //变化速率
                    if (StringUtils.isEmpty(rate)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+1+"列不能为空!");
                    }
                    String waterdepth = row.getCell(6).getStringCellValue(); //变化速率
                    if (StringUtils.isEmpty(waterdepth)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+6+"列不能为空!");
                    }
                    String equipment_no = row.getCell(7).getStringCellValue(); //仪器编号
                    if (StringUtils.isEmpty(equipment_no)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+7+"列不能为空!");
                    }
                    String equipment = row.getCell(8).getStringCellValue(); //仪器名称
                    if (StringUtils.isEmpty(equipment)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+8+"列不能为空!");
                    }
                    Date dt = row.getCell(9).getDateCellValue(); //监测日期
                    if (dt==null){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+9+"列不能为空!");
                    }
                    String remark = row.getCell(10).getStringCellValue(); //备注
                    if (StringUtils.isEmpty(remark)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+10+"列不能为空!");
                    }
                    String settleunitmonid = row.getCell(11).getStringCellValue(); //当前所属类型id
                    if (StringUtils.isEmpty(settleunitmonid)){
                        return GlobalApiResult.failure("sheet"+sheetNum+"：中的第" + i+"行，第"+11+"列不能为空!");
                    }
                    Settlement settlement=new Settlement();
                    settlement.setLocation_no(location_no);
                    settlement.setLocation(location);
                    settlement.setLast_val(Float.valueOf(last_val));//上次累计变量
                    settlement.setCurrent_val(Float.valueOf(current_val));
                    settlement.setAccumulate_val(Float.valueOf(accumulate_val));
                    settlement.setRate(Float.valueOf(rate));
                    settlement.setWaterdepth(Float.valueOf(waterdepth));
                    settlement.setEquipment_no(equipment_no);
                    settlement.setEquipment(equipment);
                    settlement.setDt(dt);
                    settlement.setOutletid(getCurrentOutletId());
                    settlement.setSettleunitmonid(Integer.valueOf(settleunitmonid));
                    list1.add(settlement);
                }
            }
              //这里重1开始，跳过了标题，直接从第二行开始解
        }
       String str= settlementService.saveList(getCurrentMonitorId(),getCurrentOutletId(),list1);
        //删除临时转换的文件
        if (excelFile.exists()) {
            excelFile.delete();
        }
        //list就是具体内容，剩下的就是自己处理具体业务了
        System.out.println("上传的内容就是这个了：" + list);
        return GlobalApiResult.success(str);
    }



}