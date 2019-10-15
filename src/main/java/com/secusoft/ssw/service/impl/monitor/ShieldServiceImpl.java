package com.secusoft.ssw.service.impl.monitor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.ShieldMapper;
import com.secusoft.ssw.model.monitor.Shield;
import com.secusoft.ssw.model.monitor.ShieldNuberAdcan;
import com.secusoft.ssw.model.monitor.Shieldadvance;
import com.secusoft.ssw.model.monitor.Shieldpoint;
import com.secusoft.ssw.model.viewobject.Request.Advance;
import com.secusoft.ssw.model.viewobject.Request.ShieldadvanceVO;
import com.secusoft.ssw.model.viewobject.Request.ShieldpointVO;
import com.secusoft.ssw.model.viewobject.Response.DangerousVO;
import com.secusoft.ssw.service.ShieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

@Service
public class ShieldServiceImpl implements ShieldService {


    @Autowired
    ShieldMapper shieldMapper;

    @Override
    public List<String> queryAllid(Integer monitorId, Integer outletId) {
        //
        List<String> list= shieldMapper.queryAllid(outletId);
        return list;
    }

    @Override
    public Object saveOrUpdateShieldadvance(Integer currentMonitorId, Integer currentOutletId, ShieldadvanceVO shieldadvanceVO) {
        shieldadvanceVO.setOutletid(currentOutletId);
        String msg = "";
        Shieldadvance shieldadvance = shieldMapper.queryShieldadvanceBySegmentno(shieldadvanceVO.getSegmentno(), shieldadvanceVO.getOutletid());
        if (shieldadvanceVO.getId() == null){
            if (shieldadvanceVO.getSegmentno() == null){
                return GlobalApiResult.failure("添加失败，管片环号不能为空");
            }

            if (shieldadvance != null){
                return GlobalApiResult.failure("添加失败，不能重复添加管片环号");
            }
            shieldMapper.saveShieldadvance(shieldadvanceVO);
            msg = "添加成功";
        }else {
            if (shieldadvance != null){
                return GlobalApiResult.failure("修改失败，已有该管片环号！");
            }
            shieldMapper.updateShieldadvance(shieldadvanceVO);
            msg = "修改成功";
        }
        return msg;
    }

    @Override
    public Object queryAllShieldadvance(Integer currentMonitorId, Integer currentOutletId, Advance advance) {
        advance.setOutletid(currentOutletId);
        if (advance.getCurrentPage() == null || advance.getPageSize() == null){
            return GlobalApiResult.failure("请选择分页");
        }
        List<Shieldadvance> shieldadvances = null;
        PageHelper.startPage(advance.getCurrentPage(),advance.getPageSize());
        if (advance.getTunnellingdate() != "0"){
            shieldadvances  = shieldMapper.queryAllShieldadvance(advance);
        }else {
            shieldadvances  = shieldMapper.selectAllShieldadvance(advance);
        }
        PageInfo<Shieldadvance> pageInfo = new PageInfo<>(shieldadvances);
        return pageInfo;
    }

    @Override
    public Object queryShieldadvance(Integer currentMonitorId, Integer currentOutletId, Integer segmentno) {
        if (segmentno == null){
            return GlobalApiResult.failure("管片环号不能为空");
        }
        return shieldMapper.selectShieldadvance(segmentno,currentOutletId);
    }

    @Override
    public Object deleteShieldadvance(Integer currentMonitorId, Integer currentOutletId, List<String> list) {
        if ( CollectionUtils.isEmpty(list)||list == null){
            return GlobalApiResult.failure("id不能为空");
        }
        shieldMapper.deleteShieldadvance(list);
        return "删除成功";
    }

    @Override
    public Object saveOrUpdateShieldpoint(Integer currentMonitorId, Integer currentOutletId, ShieldpointVO shieldpointVO) {

        String msg = "";
        if (shieldpointVO.getId() == null){
            Shieldpoint shieldpoint = shieldMapper.queryShieldpoint(shieldpointVO.getNumber(), currentOutletId);
            if (shieldpoint!= null){
                return GlobalApiResult.failure("不能重复添加该点位环数");
            }
            shieldMapper.saveShieldpoin(shieldpointVO,currentOutletId);
            msg = "添加成功";
        }else {
            shieldMapper.updateShieldpoin(shieldpointVO,currentOutletId);
            msg = "修改成功";
        }
        ShieldNuberAdcan info = shieldMapper.selectAdvancefxByOutletId(currentOutletId);
        info.setMsg(msg);
        return GlobalApiResult.success(info);
    }

    @Override
    public Object selectShieldpoint(Integer currentMonitorId, Integer currentOutletId, float number) {
        return shieldMapper.selectShieldpointByNuber(number,currentOutletId);
    }

    @Override
    public Object selectAllShieldpoint(Integer currentMonitorId, Integer currentOutletId) {
        List<Shieldpoint> shieldpoints = shieldMapper.selectAllShieldpoint(currentOutletId);
        ShieldNuberAdcan info = shieldMapper.selectAdvancefxByOutletId(currentOutletId);
        HashMap map = new HashMap();
        map.put("shieldpoints",shieldpoints);
        map.put("info",info);

        return  map;
    }

    @Override
    public Object saveOrUpdateDangerous(Integer currentMonitorId, Integer currentOutletId, DangerousVO dangerousVO) {
      String msg = "";
        Shield shield = shieldMapper.queryShieldByShieldName(currentOutletId, dangerousVO.getShieldno());
        if (dangerousVO.getId() == null){
            if (shield != null){
                shieldMapper.saveDangerous(shield.getId(),dangerousVO.getPointname(),dangerousVO.getRemarks(),currentOutletId);
                msg = "保存成功";
            }
        }else {
            shieldMapper.UpdateDangerous(dangerousVO.getId(),dangerousVO.getPointname(),dangerousVO.getRemarks(),currentOutletId);
            msg = "修改成功";
        }
       return msg;
    }

    @Override
    public Object selectDangerous(Integer currentMonitorId, Integer currentOutletId, Integer id) {
        if (id == null){
            return GlobalApiResult.failure("参数不能为空");
        }
        return   shieldMapper.selectDangerous(id,currentOutletId);
    }


}
