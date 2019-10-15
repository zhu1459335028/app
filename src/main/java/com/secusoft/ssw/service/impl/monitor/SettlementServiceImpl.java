package com.secusoft.ssw.service.impl.monitor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.SettleimageMapper;
import com.secusoft.ssw.mapper.monitor.SettlementMapper;
import com.secusoft.ssw.mapper.monitor.SettleunitmonMapper;
import com.secusoft.ssw.model.monitor.*;
import com.secusoft.ssw.model.viewobject.Request.*;
import com.secusoft.ssw.model.viewobject.Response.SettleunitmonLHVO;
import com.secusoft.ssw.model.viewobject.Response.ThresholdvalueRspVO;
import com.secusoft.ssw.service.SettlementService;
import com.secusoft.ssw.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    SettlementMapper settlementMapper;

    @Autowired
    SettleunitmonMapper settleunitmonMapper;

    @Override
    public PageInfo<Settlement> getSettlement(int monitorId, int outletId, String startTime, String endTime, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Settlement> settlements = settlementMapper.selectSettlement(outletId, startTime, endTime);
        PageInfo<Settlement> pageInfo = new PageInfo<>(settlements);
        return pageInfo;
    }

    @Override
    public int addSettlement(int monitorId, int outletId, Settlement settlement) {
        settlement.setOutletid(outletId);
        Integer settleunitmonid = settlement.getSettleunitmonid();
        if (settleunitmonid<0){
            settlement.setSettleunitmonid(Math.abs(settleunitmonid));
        }
        settlementMapper.insert(settlement);
        return settlement.getId();
    }

    @Override
    public void updateSettlement(int monitorId, int outletId, Settlement settlement) {
        settlement.setOutletid(outletId);
        if(settlement.getSettleunitmonid()==null){
            settlement.setSettleunitmonid(settlementMapper.getSettleunitmonidById(settlement.getId(),outletId));
        }
       settlementMapper.updateByPrimaryKey(settlement);
    }

    @Override
    public void deleteSettlement(int monitorId, int outletId, Integer id) {
        settlementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Settlement> getAllSettlement(int monitorId, int outletId, int currentPage, int pageSize, Integer settleunitmonid) {
        PageHelper.startPage(currentPage, pageSize);
        //List<Settlement> settlements = settlementMapper.selectAll();
        List<Settlement> settlements = settlementMapper.query(settleunitmonid, outletId);
        PageInfo<Settlement> pageInfo = new PageInfo<>(settlements);
        return pageInfo;


    }

    //以下为新增
    @Override
    public List<SettleunitmonVO> selectSettleunitmon(Integer currentMonitorId, Integer outletId) {
        List<SettleunitmonVO> list = settleunitmonMapper.selectSettleunitmon(outletId);
        return list;
    }


    @Override
    public Object saveOrUpdateSettleunitmon(Integer currentMonitorId, Integer outletid, SettleunitmonVO settleunitmonVO) {
        String msg = "";
        Integer id;
        Settleunitmon settleunitmon = new Settleunitmon();
        settleunitmon.setName(settleunitmonVO.getName());
        settleunitmon.setCode(settleunitmonVO.getCode());
        settleunitmon.setUnit(settleunitmonVO.getUnit());
        settleunitmon.setType(settleunitmonVO.getType());
        settleunitmon.setOutletid(outletid);
        if (settleunitmonVO.getId() == null) {
            //添加
            Settleunitmon settleunitmon1 = settleunitmonMapper.querySettleunitmon(outletid, settleunitmon);
            if (settleunitmon1 != null) {
                return GlobalApiResult.failure("该标识已经存在请更换标识后在添加");
            }
            id = settleunitmonMapper.saveSettleunitmon(settleunitmon);
            msg = "添加成功";
        } else {
            //修改
            settleunitmon.setId(settleunitmonVO.getId());
            id = settleunitmonMapper.updateSettleunitmon(settleunitmon,outletid);
            msg = "修改成功";
        }
        return msg;
    }


    @Override
    public Object deleteSettleunitmon(Integer currentMonitorId, Integer currentOutletId, List<String> list) {
        settleunitmonMapper.deleteSettleunitmon(list,currentOutletId);
        return GlobalApiResult.success("删除成功");
    }


    /**
     *
     * @param currentMonitorId
     * @param outletid
     * @param thresholdNameVO
     * @return
     */
    @Override
    public List<ThresholdName> queryThresholdName(Integer currentMonitorId, Integer outletid, ThresholdNameVO thresholdNameVO) {
        List<ThresholdName> list=new ArrayList<>();

        if(thresholdNameVO.getLocationno()!=null){
            //根据点位编号获取该点位的类型
            Integer id=settleunitmonMapper.querySettleunitmonidByLocationno(thresholdNameVO.getLocationno(),outletid);
            thresholdNameVO.setId(id);

            //list = settleunitmonMapper.queryThresholdName(outletid, thresholdNameVO.getId());
            List<ThresholdNameTwoVO> list1=settleunitmonMapper.queryQueryThresholdNameANDVAL(outletid, thresholdNameVO.getId());
                for (ThresholdNameTwoVO thresholdNameTwoVO : list1) {
                    //根据点位编号查询私有阈值
                    Thresholdvaluepoint thresholdvaluepoint = settleimageMapper.
                            queryThresholdvaluepointByLocationnoAndSettleunitmonid(thresholdNameVO.getLocationno(), thresholdNameTwoVO.getSettleunitmonid(), thresholdNameTwoVO.getThrnameid(), outletid);
                    //若私有阈值存在则返回私有阈值给前端展示
                    if (thresholdvaluepoint != null) {
                        if (StringUtils.isNotBlank(thresholdvaluepoint.getMax_val())) {
                            ThresholdName thresholdName = new ThresholdName();
                            thresholdName.setId(thresholdNameTwoVO.getId());
                            thresholdName.setName(thresholdNameTwoVO.getName());
                            thresholdName.setOutletid(outletid);
                            thresholdName.setType(thresholdNameTwoVO.getType());
                            thresholdName.setNumber(thresholdNameTwoVO.getNumber());
                            thresholdName.setMax_val(thresholdvaluepoint.getMax_val());
                            thresholdName.setValue(thresholdvaluepoint.getMax_val());
                            list.add(thresholdName);
                        }
                    }
                }
                if(list==null||list.isEmpty()){
                    list = settleunitmonMapper.queryThresholdName(outletid, thresholdNameVO.getId());
                }
        }else{
             list = settleunitmonMapper.queryThresholdName(outletid, thresholdNameVO.getId());
        }

        return list;
    }


    @Override
    public Object addThresholdList(Integer currentMonitorId, Integer outletid, List<ThresholdvalueVO> list) {
        String msg = "";
        for (ThresholdvalueVO vo : list) {
            if (vo.getId() == null) {
                //插入
                settleunitmonMapper.saveThresholdvalue(vo, outletid);
                msg = "插入成功";
            } else {
                //更新
                settleunitmonMapper.updateThresholdvalue(vo, outletid);
                msg = "更新成功";
            }
        }

        return msg;
    }

    @Autowired
    SettleimageMapper settleimageMapper;

    @Override
    public Object saveSettleImageInfo(Integer currentMonitorId, Integer currentOutletId, SettleimageVO settleimageVO) {
        settleimageMapper.saveSettleImageInfo(currentOutletId, settleimageVO);
        return "添加成功";
    }


    @Override
    public Object querySettleImageInfo(Integer currentMonitorId, Integer currentOutletId) {
        //List<Settleimage> list=settleimageMapper.querySettleImageInfo(currentOutletId);
        return querySettleScreen(currentMonitorId, currentOutletId);
    }

    @Override
    public Object querySettleImageInfoById(Integer currentMonitorId, Integer currentOutletId, Integer id) {
        Settleimage settleimage = settleimageMapper.querySettleImageInfoById(currentOutletId, id);
        return settleimage;
    }


    /**
     * 测量测绘大屏 todo 添加阈值
     *
     * @param currentMonitorId
     * @param outletid
     * @return
     */
    @Override
    public Object querySettleScreen(Integer currentMonitorId, Integer outletid) {
        //
        List<Object> list = new ArrayList<>();
        List<Settleimage> Settleimagelist = settleimageMapper.querySettleImageInfo(outletid);//查询工地图片
        for (Settleimage settleimage : Settleimagelist) {
            Map<String, Object> map2 = new HashMap<>();
            //查询该图片上有几种点位类型
            List<Object> points = new ArrayList<>();
            List<SettleunitmonLHVO> list1 = settleimageMapper.queryThisImageSettleunitmon(settleimage.getId(), outletid);
            //遍历每种点位类型
            Map<String, Object> img = new HashMap<>();
            img.put("id", settleimage.getId());
            img.put("url", settleimage.getImgurl());
            Map<String, Object> map1 = new HashMap<>();
            for (SettleunitmonLHVO lhvo : list1) {
                //查询每种点位类型的所有点的信息
                List<Settleimageunitmon> Settleimageunitmonlist = settleimageMapper.querySettleimageunitmonbySettleunitmonid(lhvo.getSettleunitmonid(), settleimage.getId(),outletid);
                for (Settleimageunitmon settleimageunitmon : Settleimageunitmonlist) {
                    Map<String, Object> point = new HashMap<>();
                    //获取每个点位的测量具体数据，仪器名称
                    Settlement settlement = settleimageMapper.querySettleimageById(settleimageunitmon.getLocationno(), outletid);
                    point.put("x", settleimageunitmon.getX());//点位的x,y坐标
                    point.put("y", settleimageunitmon.getY());
                    point.put("locationno", settleimageunitmon.getLocationno());//测点编号
                    point.put("type", lhvo.getType()); //'1沉降、2斜侧、3位移,4轴力,5水位'
                    point.put("code", lhvo.getCode());
                    point.put("alarm", 0);
                    point.put("pointtypeid",lhvo.getSettleunitmonid());
                    point.put("id",settleimageunitmon.getId());
                    if (settlement != null) {
                        //获取某个点位的信息
                        List<ThresholdvalueRspVO> list2 = settleimageMapper.queryThresholdvalueListBysettleunitmonid(lhvo.getId(), outletid);
                        //获取本次变量
                        Float currentval = settlement.getCurrent_val();
                        //获取累计变量
                        Float accumulateval = settlement.getAccumulate_val();

                        //
                        for (ThresholdvalueRspVO thresholdvalue : list2) {
                            String str = thresholdvalue.getMax_val();
                            //根据点位编号。settleunitmonid，thrnameid查询私有阈值
                            Thresholdvaluepoint thresholdvaluepoint=settleimageMapper.
                                    queryThresholdvaluepointByLocationnoAndSettleunitmonid(settleimageunitmon.getLocationno(),thresholdvalue.getSettleunitmonid(),thresholdvalue.getThrnameid(),outletid);
                             //判断私有 阈值存不存在
                            if(thresholdvaluepoint!=null){
                                if(StringUtils.isNotBlank(thresholdvaluepoint.getMax_val())){
                                    str=thresholdvaluepoint.getMax_val();
                                }
                            }
                            //获取阈值
                            // todo
                            if (StringUtils.isNotBlank(str)) {
                                List<String> list3 = new ArrayList<>();
                                String str1 = str.substring(0, str.indexOf(","));//截取@之前的字符串
                                String str2 = str.substring(str.indexOf(",") + 1, str.length());
                                if (StringUtils.isNotBlank(str1)) {
                                    list3.add(str1);
                                }
                                if (StringUtils.isNotBlank(str2)) {
                                    list3.add(str2);
                                }
                                if (!list3.isEmpty()) {
                                    Float max = Float.valueOf(Collections.max(list3));
                                    Float min = Float.valueOf(Collections.min(list3));
                                    // 1预警，2报警，3严重 (0报警  1不报警)
                                    //warningalarm  1预警
                                    //seriousalarm  2报警
                                    //submittedalarm  3严重
                                    if (thresholdvalue.getType() == 1) {
                                        if (currentval > min) {
                                            point.put("alarm", 1);
                                        }
                                        if (thresholdvalue.getSettleunitmonid()!=2){
                                            if (accumulateval > min) {
                                                point.put("alarm", 1);
                                            }
                                        }

                                    }
                                    if (thresholdvalue.getType() == 2) {
                                        if (currentval > min) {
                                            point.put("alarm", 2);
                                        }
                                        if (thresholdvalue.getSettleunitmonid()!=2) {
                                            if (accumulateval > min) {
                                                point.put("alarm", 2);
                                            }
                                        }
                                        List<Settlement> list4 = settleimageMapper.querySettleimageByIdLimit3(settleimageunitmon.getLocationno(), outletid);
                                        int a = 0;
                                        int b = 0;
                                        for (Settlement settlement1 : list4) {
                                            Float currentval1 = settlement.getCurrent_val();
                                            Float accumulateval1 = settlement.getAccumulate_val();
                                            if (currentval1 > min) {
                                                a++;
                                            }
                                            if (thresholdvalue.getSettleunitmonid()!=2) {
                                                if (accumulateval1 > min) {
                                                    b++;
                                                }
                                            }
                                        }
                                        if (a >= 3 || b >= 3) {
                                            point.put("alarm", 3);
                                        }
                                    }
                                    //1沉降、2斜侧、3位移,4轴力,5水位
                                    if (thresholdvalue.getType() == 3) {
                                        //lhvo.getType();
                                        if (lhvo.getType() == 1 || lhvo.getType() == 2 || lhvo.getType() == 3) {
                                            if (thresholdvalue.getSettleunitmonid()!=2) {
                                                if (accumulateval > min) {
                                                    point.put("alarm", 3);
                                                }
                                            }
                                        }
                                        if (lhvo.getType() == 4) {
                                            if (currentval > min) {
                                                point.put("alarm", 3);
                                            }
                                        }
                                        if (lhvo.getType() == 5) {
                                            if (currentval > min) {
                                                point.put("alarm", 3);
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                    points.add(point);
                }
            }

            map2.put("img", img);
            map2.put("points", points);
//            map1.put("imageId",settleimage.getId());
//            map2.put(settleimage.getImgurl(),map1);
            list.add(map2);
        }

        return list;
    }

    /**
     * 弹窗
     *
     * @param currentMonitorId
     * @param currentOutletId
     * @param id
     * @return
     */
    @Override
    public Object querySettleScreenPopupWindow(Integer currentMonitorId, Integer outletid, String locationno) {
        Settlement sett = settleimageMapper.querySettleimageById(locationno, outletid);
        if (sett == null||sett.equals("")){
            return GlobalApiResult.failure("测量值为空");
        }
        //根据测点编号查询对应的阈值
        Map<String, Object> map = new HashMap<>();
        map.put("locationno", locationno);
        map.put("thevar", sett.getCurrent_val());
        map.put("cumulativevar", sett.getAccumulate_val());
        List<Object> dates = new ArrayList<>();
        List<Object> currents = new ArrayList<>();
        List<Object> accumulates = new ArrayList<>();
        //根据测点编号获取最近7条测量记录数据
        List<Settlement> list = settleimageMapper.querySettlementBylLcationno(locationno, outletid);
        for (Settlement settlement : list) {
            //获取本次变量


            dates.add(DateUtils.getDate2String(settlement.getDt(), "yyyy-MM-dd"));
            Float currentval = settlement.getCurrent_val();
            //获取累计变量
            Float accumulateval = settlement.getAccumulate_val();
            currents.add(currentval);
            accumulates.add(accumulateval);
            //得到监测点类型
            Settleunitmon settleunitmon = settleimageMapper.querySettleunitmon(settlement.getSettleunitmonid(), outletid);
            List<Thresholdvalue> thresholdvalueList = settleimageMapper.queryThresholdvalue(settleunitmon.getId(), outletid);
//            for(Thresholdvalue thresholdvalue:thresholdvalueList){
//                //根据阈值条目id--thrnameid查询阈值条目名称
//                ThresholdName thresholdName=settleimageMapper.queryThresholdName(thresholdvalue.getThrnameid(),outletid);
//                //1沉降、2斜侧、3位移、4轴力、5水位,
//                if(settleunitmon.getType()==4){
//
//                }
//                if(settleunitmon.getType()==2){
//
//                }
//            }
        }
        map.put("dates", dates);
        map.put("currents", currents);
        map.put("accumulates", accumulates);
        return map;
    }


    /**
     * 轴力弹框 type=4        1沉降、2斜侧、3位移、4轴力、5水位
     *
     * @param currentMonitorId
     * @param currentOutletId
     * @param locationno
     * @return
     */
    @Override
    public Object querySettleScreenPopupWindowAxialforce(Integer currentMonitorId, Integer outletid, String locationno) {
        //Settlement sett = settleimageMapper.querySettleimageById(locationno, outletid);
        Settleimageunitmon settleimageunitmon1=settleimageMapper.querySettleimageunitmonbyLocationno(outletid,locationno);
        Map<String, Object> map = new HashMap<>();

        List<Object> list1 = new ArrayList<>();
        //查询当前settleimageunitmon编号下的轴力点位列表
        Map<String,Object> curveline=new HashMap<>();
        List<Object> date=new ArrayList<>();
        List<Object> series=new ArrayList<>();
        List<Settleimageunitmon> list = settleimageMapper.querySettleimageunitmonByParentlocationno(settleimageunitmon1.getParentlocationno(), outletid);
        Map<String, Object> map2 = new HashMap<>();
        for (Settleimageunitmon settleimageunitmon : list) {

            //列表
            Settlement settlement = settleimageMapper.querySettleimageById(settleimageunitmon.getLocationno(), outletid);
            if (settlement == null||settlement.equals("")){
                return GlobalApiResult.failure("测量值为空");
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("name", settlement.getLocation_no());
            map1.put("last", settlement.getLast_val());
            map1.put("curr", settlement.getCurrent_val());
            map1.put("currentvariation", settlement.getCurrent_val() - settlement.getLast_val());
            list1.add(map1);
            //曲线

            List<Object> dates = new ArrayList<>();
            List<Object> currents = new ArrayList<>();

            Map<String, Object> map3 = new HashMap<>();
            List<Settlement> list2 = settleimageMapper.querySettlementBylLcationno(settlement.getLocation_no(), outletid);
            for (Settlement settlement1 : list2) {
                dates.add(DateUtils.getDate2String(settlement1.getDt(), "yyyy-MM-dd"));
                currents.add(settlement1.getCurrent_val());
            }
            map2.put("date",dates);
            map3.put("name",settlement.getLocation_no());
            map3.put("data", currents);
            series.add(map3);
        }
        curveline.put("date",map2.get("date"));
        curveline.put("series",series);
        map.put("header", list1);
        map.put("curveline", curveline);
        return map;
    }

    /**
     * type=2   1沉降、2斜侧、3位移、4轴力、5水位
     * 侧斜弹框
     *
     * @param currentMonitorId
     * @param currentOutletId
     * @param locationno
     * @return
     */
    @Override
    public Object querySettleScreenPopupWindowSite(Integer currentMonitorId, Integer outletid, String locationno) {
        Settlement sett = settleimageMapper.querySettleimageById(locationno, outletid);
        if (sett == null||sett.equals("")){
            return GlobalApiResult.failure("测量值为空");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("locationno", locationno);
        List<Object> depth = new ArrayList<>();
        List<Object> accumulates = new ArrayList<>();
        //根据测点编号获取最近7条测量记录数据
        List<Settlement> list = settleimageMapper.querySettlementBylLcationno(locationno, outletid);
        for (Settlement settlement : list){
            depth.add(settlement.getDepth());
            accumulates.add(settlement.getAccumulate_val());
        }
        map.put("dept", depth);
        map.put("accumulates", accumulates);
        return map;
    }

    @Override
    public Object querySettleScreenPopupWindowWater(Integer currentMonitorId, Integer outletid, String locationno) {
        Settlement sett = settleimageMapper.querySettleimageById(locationno, outletid);
        if (sett == null||sett.equals("")){
            return GlobalApiResult.failure("测量值为空");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("locationno", locationno);
        List<Object> x = new ArrayList<>();
        List<Object> y = new ArrayList<>();
        //根据测点编号获取最近7条测量记录数据
        List<Settlement> list = settleimageMapper.querySettlementBylLcationno(locationno, outletid);
        for (Settlement settlement : list) {
            x.add(DateUtils.getDate2String(settlement.getDt(), "yyyy-MM-dd"));
            y.add(settlement.getCurrent_val());
        }
        map.put("x", x);
        map.put("y", y);
        map.put("currents",sett.getCurrent_val());
        map.put("accumulates",sett.getAccumulate_val());
        return map;
    }



    /**
     * 添加监测点
     *
     * @param currentMonitorId
     * @param currentOutletId
     * @param settleimageunitmonVO
     * @return
     */
    @Override
    @Transactional
    public GlobalApiResult<Object> addsettleunitmon(Integer currentMonitorId, Integer outletid, List<SettleimageunitmonVO> list) {
        //根据编号查询当前编号是否重复
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        for (SettleimageunitmonVO settleimageunitmonVO : list) {
            //判断是否是轴力
            Settleunitmon settleunitmon = settleimageMapper.querySettleunitmon(settleimageunitmonVO.getSettleunitmonid(), outletid);
            Settleimageunitmon settleimageunitmon = settleimageMapper.querySettleimageunitmonbyLocationno(outletid, settleimageunitmonVO.getLocationno());
            Integer num = settleimageMapper.querySettleimageunitmonbyLocationnoCount(outletid, settleimageunitmonVO.getLocationno());
            if (num >= 1) {
                return GlobalApiResult.failure("当前编号已经存在：" + settleimageunitmon.getLocationno());
            }
            if (settleunitmon.getType() == 4) {
                settleimageMapper.addsettleunitmonuuid(outletid, settleimageunitmonVO, uuid);
            } else {
                settleimageMapper.addsettleunitmon(outletid, settleimageunitmonVO);
            }
        }
        return GlobalApiResult.success();
    }


    /**
     * 添加阈值条目
     *
     * @param currentMonitorId
     * @param outletid
     * @param vo
     * @return
     */
    @Override
    public Object saveOrUpdatethresholdname(Integer currentMonitorId, Integer outletid, ThresholdName vo) {
        String msg = "";
        if (vo.getId() == null) {
            //插入
            settleunitmonMapper.saveThresholdname(vo, outletid);
            msg = "插入成功";
        } else {
            //更新
            settleunitmonMapper.updateThresholdname(vo, outletid);
            msg = "更新成功";
        }
        return msg;
    }

    @Transactional
    @Override
    public String saveList(Integer currentMonitorId, Integer currentOutletId, List<Settlement> list1) {
        settlementMapper.saveList(currentOutletId, list1);
        return "添加成功";
    }


    @Override
    public GlobalApiResult<Object> saveOrUpdateLocationNoThresholdList(Integer currentMonitorId, Integer outletid, List<ThresholdvalueTwoVO> list) {
        String msg = "";
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //教验当前编号的维一性
        String locano = "";
        Integer id = null;
        for (ThresholdvalueTwoVO thresholdvalueTwoVO : list) {
            Settleimageunitmon settleimageunitmon = settleimageMapper.querySettleimageunitmonbyLocationno(outletid, thresholdvalueTwoVO.getLocationno());
            Integer num = settleimageMapper.querySettleimageunitmonbyLocationnoCount(outletid, thresholdvalueTwoVO.getLocationno());
            if (num >= 2) {
                return GlobalApiResult.failure("当前编号已经存在：" + settleimageunitmon.getLocationno());
            }
            locano = thresholdvalueTwoVO.getLocationno();
            thresholdvalueTwoVO.getId();
            id=thresholdvalueTwoVO.getId();
        }
        //获取旧的点位编号
        Integer sid=null;
        String oldno=settleimageMapper.queryPointLocationno(id,outletid);
        if(id!=null){
            sid=settleunitmonMapper.querySettleunitmonidByLocationno(locano,outletid);
            if(sid==null){
                sid=settleunitmonMapper.querySettleunitmonidByLocationno(oldno,outletid);
            }
        }
        SettleimageunitmonVO settleimageunitmonVO = new SettleimageunitmonVO();
        for (ThresholdvalueTwoVO thresholdvalueTwoVO : list){
            //保存阈值

            ThresholdvalueVO vo = new ThresholdvalueVO();
            vo.setId(thresholdvalueTwoVO.getId());
            if(id!=null){
                vo.setSettleunitmonid(sid);
            }else{
                vo.setSettleunitmonid(thresholdvalueTwoVO.getSettleunitmonid());
            }
            vo.setThrnameid(thresholdvalueTwoVO.getThrnameid());
            vo.setValue(thresholdvalueTwoVO.getValue());
           // settleunitmonMapper.saveThresholdvaluepoint(vo, thresholdvalueTwoVO.getLocationno(), outletid);
            if (vo.getId() == null){
                //插入私有阈值表
                settleunitmonMapper.saveThresholdvaluepoint(vo, thresholdvalueTwoVO.getLocationno(), outletid);
                msg = "插入成功";
            }else{
                //更新私有阈值


                if(StringUtils.isNotBlank(oldno)&&StringUtils.isNotBlank(thresholdvalueTwoVO.getLocationno())){
                    //如果新旧点位编号不相等则列新点位编号
                    if(!thresholdvalueTwoVO.getLocationno().equals(oldno)){
                        settleunitmonMapper.updateSettleimageunitmonlocationno(oldno,thresholdvalueTwoVO.getLocationno(),outletid);
                        settleunitmonMapper.updateThresholdvaluepointlocationno(oldno,thresholdvalueTwoVO.getLocationno(),outletid);
                        settleunitmonMapper.updateSettlementlocationno(oldno,thresholdvalueTwoVO.getLocationno(),outletid);
                    }
                    //
                    if(sid!=null){
                        thresholdvalueTwoVO.setSettleunitmonid(sid);
                    }
                    List<Thresholdvaluepoint> list1=settleunitmonMapper.queryThresholdvaluepointByLocationno(thresholdvalueTwoVO,outletid);
                    if(list1==null||list1.isEmpty()){
                        settleunitmonMapper.saveThresholdvaluepoint(vo, thresholdvalueTwoVO.getLocationno(), outletid);
                    }else{
                        oldno=thresholdvalueTwoVO.getLocationno();
                        settleunitmonMapper.updateThresholdvaluepoint(vo, thresholdvalueTwoVO.getLocationno(), outletid);
                    }

                }
                msg = "更新成功";
            }
            //保存点位
            settleimageunitmonVO.setLocationno(thresholdvalueTwoVO.getLocationno());
            settleimageunitmonVO.setX(thresholdvalueTwoVO.getX());
            settleimageunitmonVO.setY(thresholdvalueTwoVO.getY());
            settleimageunitmonVO.setSettleimageid(thresholdvalueTwoVO.getSettleimageid());
            settleimageunitmonVO.setSettleunitmonid(thresholdvalueTwoVO.getSettleunitmonid());
        }
        if(id == null) {
          //判断是否是轴力
          if (settleimageunitmonVO != null) {
              Settleunitmon settleunitmon = settleimageMapper.querySettleunitmon(settleimageunitmonVO.getSettleunitmonid(), outletid);
              Settleimageunitmon settleimageunitmon = settleimageMapper.querySettleimageunitmonbyLocationno(outletid, settleimageunitmonVO.getLocationno());
              Integer num = settleimageMapper.querySettleimageunitmonbyLocationnoCount(outletid, settleimageunitmonVO.getLocationno());
              if (num >= 1) {
                  return GlobalApiResult.failure("当前编号已经存在：" + settleimageunitmon.getLocationno());
              }
              if (settleunitmon.getType() == 4) {
                  settleimageMapper.addsettleunitmonuuid(outletid, settleimageunitmonVO, uuid);
              } else {
                  settleimageMapper.addsettleunitmon(outletid, settleimageunitmonVO);
              }
              id = settleimageMapper.querySettleimageunitmonIdbyLocationno(outletid, locano);
          }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("locationno", locano);
        map.put("id", id);
        return  GlobalApiResult.success(map);
    }





    @Override
    public Object deletePoint(Integer currentMonitorId, Integer outletid, String locationno){
        System.out.println(locationno);
        //{"locationno":"JGC121"}
        JSON json=JSON.parseObject(locationno);
        String str21=((JSONObject) json).getString("locationno");
        List<String> str = Arrays.asList(str21.split(","));
        for (String str1 : str) {
            //
            Settleimageunitmon settleimageunitmon = settleunitmonMapper.querySettleimageunitmonBylocationno(str1, outletid);
            //删除点位对应的信息
            settleunitmonMapper.deletedeletePoint(str1, outletid);
            //删除点位对应的阈值
            settleunitmonMapper.deletePointthresholdvalue(str1, outletid);
            //删除对应的测量记录
            settleunitmonMapper.deletesettlementbylocationno(str1, outletid);
        }
        return "删除成功";
    }
}
