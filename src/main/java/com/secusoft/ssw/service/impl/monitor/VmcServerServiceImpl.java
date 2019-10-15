package com.secusoft.ssw.service.impl.monitor;

import com.secusoft.ssw.mapper.monitor.*;
import com.secusoft.ssw.model.monitor.Employee;
import com.secusoft.ssw.model.monitor.Gas;
import com.secusoft.ssw.model.monitor.GasVos;
import com.secusoft.ssw.model.monitor.KeyValue;
import com.secusoft.ssw.model.viewobject.Request.GasVo;
import com.secusoft.ssw.model.viewobject.Response.ComeOnAndOutVO;
import com.secusoft.ssw.service.VmcServerService;
import com.secusoft.ssw.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName VmcServerServiceImpl
 * @Author jcyao
 * @Description
 * @Date 2018/9/14 16:28
 * @Version 1.0
 */
@Service
public class VmcServerServiceImpl implements VmcServerService {

    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private AccessDeviceMapper accessDeviceMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private CheckingMapper checkingMapper;
    @Autowired
    private GasMapper gasMapper;
    @Override
    public String getEmployeeIdByCardId(int monitorId, int outletId, String cardId) {
        Employee employee = employeeMapper.selectEmployeeByCardIdAndOutletId(cardId,outletId);
        if(employee!=null)
            return employee.getId();
        return null;
    }

    @Override
    public boolean isJobTypeByEmployeeId(int monitorId, String employeeId) {
        Integer type = jobMapper.selectJobTypeByEmployeeId(employeeId);
        if(type!=null && type==1){
            return true;
        }
        return false;
    }

    @Override
    public Integer getAccessDeviceTypeByDeviceId(int monitorId, int outletId, String accessDeviceId) {
        return accessDeviceMapper.selectAccessDeviceTypeByAccessDeviceId(outletId,accessDeviceId);
    }

    @Override
    public String getCheckingImageUrlByCheckId(int monitorId, int outletId, int checkId) {
        return checkingMapper.selectImageUrlById("checking_"+outletId,checkId,outletId);
    }

    @Override
    public Integer getJobTypeByEmployeeId(int monitorId, String employeeId) {
        Integer type = jobMapper.selectJobTypeByEmployeeId(employeeId);
        if(type==null){
            return 0;
        }
        return type;
    }

    @Override
    public ComeOnAndOutVO comeOnAndOut(int monitorId, int outletId) {
        ComeOnAndOutVO comeOnAndOutVO = new ComeOnAndOutVO();
        comeOnAndOutVO.setOnCount(checkingMapper.countByOutletIdAndType("checking_"+outletId,outletId,1));
        comeOnAndOutVO.setOutCount(checkingMapper.countByOutletIdAndType("checking_"+outletId,outletId,2));
        return comeOnAndOutVO;
    }



    @Override
    public void insertGaswdz(int monitorId, int outletid, GasVo gasVos) {
        List<Gas> gasList = new ArrayList<>();
        List<KeyValue> typeValueList = new ArrayList<>();
        typeValueList.add(new KeyValue("6",gasVos.getPm10()));
        typeValueList.add(new KeyValue("7",gasVos.getPm25()));
        typeValueList.add(new KeyValue("8",gasVos.getNoise()));
        typeValueList.add(new KeyValue("9",gasVos.getTemperature()));
        typeValueList.add(new KeyValue("10",gasVos.getHumidity()));
        typeValueList.add(new KeyValue("11",gasVos.getWindspeed()));
        typeValueList.add(new KeyValue("12",gasVos.getWinddirection()));
        typeValueList.add(new KeyValue("13",gasVos.getTsp()));
        for (KeyValue typeValue : typeValueList) {
            Gas gas = new Gas();
            //gas.setDtStr(DateUtils.getDate2String(DateUtils.parseDate(str,"yyyy-MM-dd"),"yyyy-MM-dd"));
            gas.setDt(DateUtils.parseDate(gasVos.getTime(),"yyyy-MM-dd HH:mm:ss"));
            gas.setDevice_id(gasVos.getDevice_id());
            gas.setOutletid(outletid);
            gas.setTbm_range("");
            gas.setType(typeValue.getType());
            gas.setVal(typeValue.getVal());
            gas.setRummager("WHJXCTKJ33725122468B");
            gasList.add(gas);
        }
        gasMapper.saveGasList(gasList);

    }
    @Override
    public void insertGaswdzhbx(int monitorId, int outletid, GasVo gasVos) {
        List<Gas> gasList = new ArrayList<>();
        List<KeyValue> typeValueList = new ArrayList<>();
        typeValueList.add(new KeyValue("7",gasVos.getPm25()));
        typeValueList.add(new KeyValue("9",gasVos.getTemperature()));
        typeValueList.add(new KeyValue("10",gasVos.getHumidity()));
        typeValueList.add(new KeyValue("11",gasVos.getWindspeed()));
        typeValueList.add(new KeyValue("12",gasVos.getWinddirection()));
        typeValueList.add(new KeyValue("6",gasVos.getPressure()));
        for (KeyValue typeValue : typeValueList) {
            Gas gas = new Gas();
            gas.setDt(DateUtils.parseDate(gasVos.getTime(),"yyyy-MM-dd HH:mm:ss"));
            gas.setDevice_id(gasVos.getDevice_id());
            gas.setOutletid(outletid);
            gas.setTbm_range("");
            gas.setType(typeValue.getType());
            gas.setVal(typeValue.getVal());
            gas.setRummager("WHJXCTKJ33725122468B");
            gasList.add(gas);
        }
        gasMapper.saveGasList(gasList);


    }
}
