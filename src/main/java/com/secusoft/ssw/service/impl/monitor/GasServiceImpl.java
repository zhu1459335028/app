package com.secusoft.ssw.service.impl.monitor;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.GasMapper;
import com.secusoft.ssw.model.monitor.Gas;
import com.secusoft.ssw.model.monitor.KeyValue;
import com.secusoft.ssw.model.monitor.ShieldNuberAdcan;
import com.secusoft.ssw.model.monitor.Shieldpoint;
import com.secusoft.ssw.model.viewobject.Request.EnvironmentalVo;
import com.secusoft.ssw.model.viewobject.Request.FoundationVo;
import com.secusoft.ssw.model.viewobject.Request.GasVo;
import com.secusoft.ssw.model.viewobject.Request.Iocontroller;
import com.secusoft.ssw.model.viewobject.Response.IocontrollerVO;
import com.secusoft.ssw.service.GasService;
import com.secusoft.ssw.util.DateUtils;
import com.secusoft.ssw.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GasServiceImpl implements GasService {
    @Autowired
    private GasMapper gasMapper;

    @Override
    public PageInfo<EnvironmentalVo> getEnvironmentalList(Integer currentMonitorId, Integer currentOutletId, int currentPage, int pageSize, List<Integer> types) {
        PageHelper.startPage(currentPage, pageSize);
        System.out.println("开始查询gasList ：===============================");
        List<Gas> gasList = gasMapper.getGasListByTypes(currentOutletId,types);
        System.out.println("开始查询gasList ：==============================="+gasList);
        int count = gasMapper.getGasListCountByTypes(currentOutletId,types);
        System.out.println("开始查询gcount：==============================="+count);
        List<EnvironmentalVo> gasVoList = new ArrayList<>();
        for (Gas gas : gasList) {
            EnvironmentalVo gasVo  = new EnvironmentalVo();
            System.out.println("============"+gas.getDt());
            gasVo.setDt(gas.getDt());
            gasVo.setRummager(gas.getRummager());
            gasVo.setTbm_range(gas.getTbm_range());
            gasVo.setId(gas.getId());
            String[] split = gas.getType().split(",");
            String[] val = StringUtil.tranferArr(gas.getVal());
            for (int i=0 ;i<=split.length-1;i++) {
                if (split[i].equals("6")) {
                    gasVo.setPM10(val[i]);
                }else if (split[i].equals("7")) {
                    gasVo.setPM25(val[i]);
                }else if (split[i].equals("8")) {
                    gasVo.setNoise(val[i]);
                }else if (split[i].equals("10")) {
                    gasVo.setHumidity(val[i]);
                }else if (split[i].equals("11")) {
                    gasVo.setWindSpeed(val[i]);
                }else if (split[i].equals("13")) {
                    gasVo.setWindPower(val[i]);
                }else if (split[i].equals("12")) {
                    gasVo.setWindDirection(val[i]);
                }else if (split[i].equals("9")) {
                    gasVo.setTemperature(val[i]);
                }

            }
            gasVoList.add(gasVo);
        }
        PageInfo<EnvironmentalVo> pageInfo = new PageInfo<>(gasVoList);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public String saveEnvironmental(Integer currentMonitorId, Integer currentOutletId, EnvironmentalVo gasVo) {
        List<KeyValue> typeValueList = new ArrayList<>();
        typeValueList.add(new KeyValue("6",gasVo.getPM10()));
        typeValueList.add(new KeyValue("7",gasVo.getPM25()));
        typeValueList.add(new KeyValue("8",gasVo.getNoise()));
        typeValueList.add(new KeyValue("9",gasVo.getTemperature()));
        typeValueList.add(new KeyValue("10",gasVo.getHumidity()));
        typeValueList.add(new KeyValue("11",gasVo.getWindSpeed()));
        typeValueList.add(new KeyValue("12",gasVo.getWindDirection()));
        typeValueList.add(new KeyValue("13",gasVo.getWindPower()));
        List<Gas> gasList = new ArrayList<>();
        for (KeyValue typeValue : typeValueList) {
            Gas gas = new Gas();
            gas.setDt(gasVo.getDt());
            gas.setOutletid(currentOutletId);
            gas.setRummager(gasVo.getRummager());
            gas.setTbm_range(gasVo.getTbm_range());
            gas.setType(typeValue.getType());
            gas.setVal(typeValue.getVal());
            gasList.add(gas);
        }
        gasMapper.saveGasList(gasList);
        List<String> idList = new ArrayList<>();
        for (Gas gas : gasList) {
            idList.add(gas.getId());
        }
        return StringUtils.join(idList,",");
    }

    @Override
    public void deleteGas(Integer currentMonitorId,Integer currentOutletId, List<Integer> ids) {
        gasMapper.deleteGasByIdList(ids,currentOutletId);
    }

    @Override
    @Transactional
    public void updateEnvironmental(Integer currentMonitorId,Integer currentOutletId, EnvironmentalVo environmentalVo) {
        List<Integer> ids = JSONArray.parseArray("["+environmentalVo.getId()+"]", Integer.class);
        List<Gas> gasList = gasMapper.getGasListByIds(ids,currentOutletId);
        for (Gas gas : gasList) {
            gas.setDt(environmentalVo.getDt());
            gas.setRummager(environmentalVo.getRummager());
            gas.setTbm_range(environmentalVo.getTbm_range());
            if (gas.getType().equals("6")) {
                gas.setVal(environmentalVo.getPM10());
            }else if (gas.getType().equals("7")) {
                gas.setVal(environmentalVo.getPM25());
            }else if (gas.getType().equals("8")) {
                gas.setVal(environmentalVo.getNoise());
            }else if (gas.getType().equals("10")) {
                gas.setVal(environmentalVo.getHumidity());
            }else if (gas.getType().equals("11")) {
                gas.setVal(environmentalVo.getWindSpeed());
            }else if (gas.getType().equals("13")) {
                gas.setVal(environmentalVo.getWindPower());
            }else if (gas.getType().equals("12")) {
                gas.setVal(environmentalVo.getWindDirection());
            }else if (gas.getType().equals("9")) {
                gas.setVal(environmentalVo.getTemperature());
            }
            gasMapper.updateGas(gas);
        }
//        gasMapper.updateGasList(gasList);
    }

    @Override
    public PageInfo<FoundationVo> getFoundationList(Integer currentMonitorId, Integer currentOutletId, int currentPage, int pageSize, List<Integer> types) {
        PageHelper.startPage(currentPage,pageSize);
        System.out.println("开始查询gasList ：===============================");
        List<Gas> gasList = gasMapper.getGasListByTypes(currentOutletId,types);
        System.out.println("gasList为==========================："+gasList);
        int count = gasMapper.getGasListCountByTypes(currentOutletId,types);
        System.out.println("count个数为 ：===============================："+count);
        List<FoundationVo> gasVoList = new ArrayList<>();
        for (Gas gas : gasList) {
            FoundationVo gasVo  = new FoundationVo();
            gasVo.setDt(gas.getDt());
            gasVo.setRummager(gas.getRummager());
            gasVo.setTbm_range(gas.getTbm_range());
            gasVo.setId(gas.getId());
            String[] split = gas.getType().split(",");
            String[] val = StringUtil.tranferArr(gas.getVal());
            for (int i=0;i<=split.length-1;i++) {
                if (split[i].equals("3")) {
                    gasVo.setCO(val[i]);
                }else if (split[i].equals("1")) {
                    gasVo.setFireO2(val[i]);
                }else if (split[i].equals("4")) {
                    gasVo.setH2S(val[i]);
                }else if (split[i].equals("2")) {
                    gasVo.setO2(val[i]);
                }
            }
            gasVoList.add(gasVo);
        }
        PageInfo<FoundationVo> pageInfo = new PageInfo<>(gasVoList);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public String saveFoundationVo(Integer currentMonitorId, Integer currentOutletId, FoundationVo foundationVo) {
        List<KeyValue> typeValueList = new ArrayList<>();
        typeValueList.add(new KeyValue("1",foundationVo.getFireO2()));
        typeValueList.add(new KeyValue("2",foundationVo.getO2()));
        typeValueList.add(new KeyValue("3",foundationVo.getCO()));
        typeValueList.add(new KeyValue("4",foundationVo.getH2S()));
        List<Gas> gasList = new ArrayList<>();
        for (KeyValue typeValue : typeValueList) {
            Gas gas = new Gas();
            gas.setDt(foundationVo.getDt());
            gas.setOutletid(currentOutletId);
            gas.setRummager(foundationVo.getRummager());
            gas.setTbm_range(foundationVo.getTbm_range());
            gas.setType(typeValue.getType());
            gas.setVal(typeValue.getVal());
            gasList.add(gas);
        }
        gasMapper.saveGasList(gasList);
        List<String> idList = new ArrayList<>();
        for (Gas gas : gasList) {
            idList.add(gas.getId());
        }
        return StringUtils.join(idList,",");
    }

    @Override
    @Transactional
    public void updateFoundationVo(Integer currentMonitorId,Integer currentOutletId, FoundationVo foundationVo) {
        List<Integer> ids = JSONArray.parseArray("["+foundationVo.getId()+"]", Integer.class);
        List<Gas> gasList = gasMapper.getGasListByIds(ids,currentOutletId);
        for (Gas gas : gasList) {
            gas.setDt(foundationVo.getDt());
            gas.setRummager(foundationVo.getRummager());
            gas.setTbm_range(foundationVo.getTbm_range());
            if (gas.getType().equals("1")) {
                gas.setVal(foundationVo.getFireO2());
            }else if (gas.getType().equals("2")) {
                gas.setVal(foundationVo.getO2());
            }else if (gas.getType().equals("3")) {
                gas.setVal(foundationVo.getCO());
            }else if (gas.getType().equals("4")) {
                gas.setVal(foundationVo.getH2S());
            }
            gasMapper.updateGas(gas);
        }
//        gasMapper.updateGasList(gasList);
    }

    @Override
    public void syncGasVo(int i,int outletid, GasVo gasVo) {
        List<KeyValue> typeValueList = new ArrayList<>();
        typeValueList.add(new KeyValue("3",gasVo.getData().getCO()));
        typeValueList.add(new KeyValue("14",gasVo.getData().getAtmosphericpressure()));
        typeValueList.add(new KeyValue("16",gasVo.getData().getCO2()));
        typeValueList.add(new KeyValue("10",gasVo.getData().getHumidity()));
        typeValueList.add(new KeyValue("25",gasVo.getData().getH2()));
        typeValueList.add(new KeyValue("4",gasVo.getData().getH2S()));
        typeValueList.add(new KeyValue("15",gasVo.getData().getIlluminance()));
        typeValueList.add(new KeyValue("24",gasVo.getData().getMethane()));
        typeValueList.add(new KeyValue("21",gasVo.getData().getNH3()));
        typeValueList.add(new KeyValue("18",gasVo.getData().getNO2()));
        typeValueList.add(new KeyValue("8",gasVo.getData().getNoise()));
        typeValueList.add(new KeyValue("2",gasVo.getData().getO2()));
        typeValueList.add(new KeyValue("20",gasVo.getData().getO3()));
        typeValueList.add(new KeyValue("6",gasVo.getData().getPM10()));
        typeValueList.add(new KeyValue("7",gasVo.getData().getPM25()));
        typeValueList.add(new KeyValue("17",gasVo.getData().getSO2()));
        typeValueList.add(new KeyValue("9",gasVo.getData().getTemperature()));
        typeValueList.add(new KeyValue("26",gasVo.getData().getTVOC()));
        typeValueList.add(new KeyValue("12",gasVo.getData().getWinddirection()));
        typeValueList.add(new KeyValue("13",gasVo.getData().getWindpower()));
        typeValueList.add(new KeyValue("11",gasVo.getData().getWindspeed()));
        List<Gas> gasList = new ArrayList<>();
        for (KeyValue typeValue : typeValueList) {
            Gas gas = new Gas();
            gas.setDt(DateUtils.parseDate(gasVo.getTime(),"yyyy-MM-dd HH:mm:ss"));
            gas.setOutletid(outletid);
            gas.setRummager("");
            gas.setTbm_range("");
            gas.setType(typeValue.getType());
            gas.setVal(typeValue.getVal());
            gasList.add(gas);
        }
        gasMapper.saveGasList(gasList);
    }

    @Override
    public Object saveGasIoInFO(Integer currentMonitorId, Integer currentOutletId, Iocontroller iocontroller) {
        if (iocontroller == null){
            return GlobalApiResult.failure("参数不能为空");
        }
        iocontroller.setOutletid(currentOutletId);
        if (iocontroller.getDevicetype().equals("1")){
            Iocontroller io = gasMapper.selectIoBydevicetype(iocontroller.getDevicetype(), iocontroller.getOutletid());
            if (io != null){
                return GlobalApiResult.failure("已有撤离IO控制器");
            }
        }
        String msg = "";
        if (iocontroller.getId() == null){
            IocontrollerVO iocontrollerVO = gasMapper.selectGasIoInFO(iocontroller.getDevicename(),iocontroller.getOutletid());
            if (iocontrollerVO != null){
                return GlobalApiResult.failure("已有该设备名称");
            }
            gasMapper.saveGasIoInFO(iocontroller);
            msg = "添加成功";
        }else {
            gasMapper.updateGasIoInFO(iocontroller);
            msg = "修改成功";
        }

        return GlobalApiResult.success(msg);

    }

    @Override
    public Object selectAllIoInfo(Integer currentMonitorId, Integer currentOutletId) {

        return gasMapper.selectAllIoInfo(currentOutletId);
    }

    @Override
    public Object selectOneIoInfo(Integer currentMonitorId, Integer currentOutletId, Integer id) {

        return  gasMapper.selectOneIoInfo(id,currentOutletId);
    }

    @Override
    public Object deleteOneIoInfo(Integer currentMonitorId, Integer currentOutletId, Integer id) {
        gasMapper.deleteOneIoInfo(id,currentOutletId);
        return "删除成功";
    }
}
