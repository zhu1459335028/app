package com.secusoft.ssw.service.impl.monitor;

import com.secusoft.ssw.mapper.monitor.*;
import com.secusoft.ssw.model.monitor.Progressstatistics;
import com.secusoft.ssw.model.monitor.Shield;
import com.secusoft.ssw.model.monitor.Targetgoals;
import com.secusoft.ssw.model.viewobject.Response.*;
import com.secusoft.ssw.service.ProgressstatisticsService;
import com.secusoft.ssw.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ProgressstatisticsServiceImpl implements ProgressstatisticsService {

    @Autowired
    ProgressstatisticsMapper progressstatisticsMapper;

    @Autowired
    ShieldMapper shieldMapper;


    @Override
    public void saveProgressstatistics(int i, Integer outletid, String hpjsq,String shileldname) {
        //
        Shield shield=shieldMapper.queryShieldByShieldName(outletid,shileldname);
        if(shield==null){
            //获取最大的max
            Integer max=shieldMapper.queryMaxId(outletid);
            Shield insert=new Shield();
            insert.setShieldno(shileldname);
            insert.setShieldname("盾构"+max);
            insert.setTotalmileage(2000f);
            insert.setTotalring(4000f);
            insert.setEachringmileage(1.2f);
            insert.setOutletid(outletid);
            shieldMapper.save(insert);
            shield=shieldMapper.queryShieldByShieldName(outletid,shileldname);
        }

        //根据盾构id查询推进表得到当前推进数据
        Progressstatistics progress= progressstatisticsMapper.queryProgressByShieldByid(shield.getId(),outletid);
        if(progress==null){
            Progressstatistics p=new Progressstatistics();
            p.setHistorynumber(Float.valueOf(hpjsq));
            p.setCurrentnumber(0.0f);
            p.setCurrentmileage(0.0f);
            p.setEverynumber(0.0f);
            p.setDate(new Date());
            p.setOutletid(outletid);
            p.setShieldid(shield.getId());
            progressstatisticsMapper.save(p);
            progress= progressstatisticsMapper.queryProgressByShieldByid(shield.getId(),outletid);
        }
        String historydate=DateUtils.getDate2String(progress.getDate(),"yyyy-MM-dd");
        String nowdate=DateUtils.getDate2String(new Date(),"yyyy-MM-dd");

//        //当前环数-历史当前环数==最新的当前环数
//        float currentnumber=Float.valueOf(hpjsq)-progress.getHistorynumber()+progress.getCurrentnumber();
        //当前环数-历史当前环数==最新的当前环数
        float currentnumber=Float.valueOf(hpjsq);
        //昨天环数
//        Integer yesterdaynumber=progressstatisticsMapper.queryYesterdaynumber(nowdate,shield.getId(),outletid);
//        if(yesterdaynumber==null){
//            yesterdaynumber=0;
//        }
        //每天环数
        float everynumber=Float.valueOf(hpjsq);
        //当前里程=当前环数*每环里程数
        float currentmileage=currentnumber*shield.getEachringmileage();
        if(historydate.equals(nowdate)){
            //更新
            progressstatisticsMapper.update(outletid,currentnumber,currentmileage,everynumber,progress.getId(),Float.valueOf(hpjsq));
        }
        if(!historydate.equals(nowdate)){
            Progressstatistics p=new Progressstatistics();
            p.setCurrentnumber(currentnumber);
            p.setCurrentmileage(currentmileage);
            p.setHistorynumber(Float.valueOf(hpjsq));
            p.setEverynumber(everynumber);
            p.setDate(new Date());
            p.setOutletid(outletid);
            p.setShieldid(shield.getId());
            progressstatisticsMapper.save(p);
        }
    }

    @Autowired
    DangerpointMapper dangerpointMapper;

    @Autowired
    TargetgoalsMapper targetgoalsMapper;

    @Autowired
    ShieldimageMapper shieldimageMapper;
    @Autowired
    private CameraMapper cameraMapper;

    @Override
    public Map<String,Object> queryatestWeek(int i, Integer outletid, String shieldname,Map<String,Object> outputmap) {
        //得到最近7天的日期 yyyy-mm-dd
        List<String> list=DateUtils.getRecent7Day(7);
        Collections.sort(list);
        //shieldname 得到id
        Shield shield=shieldMapper.queryByShieldName(shieldname,outletid);
        List<String> x=new ArrayList<>();
        List<String> y=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        for(String str:list){
            //根据日期和盾构id查询最近7天每天的环数
            Progressstatistics pro=progressstatisticsMapper.queryByidanddate(shield.getId(),str,outletid);
            x.add(str);//日期
            if(pro==null){
                y.add(0+"");
            }else{
                y.add(pro.getEverynumber()+"");
            }
        }
        Progressstatistics pro=progressstatisticsMapper.queryProgressByShieldByid(shield.getId(),outletid);
        map.put("x",x);
        map.put("y",y);
        if(shield==null){
            map.put("totalmileage",0);//总的里程数
            map.put("totalring",0);//总的环数
        }else{
            map.put("totalmileage",shield.getTotalmileage());//总的里程数
            map.put("totalring",shield.getTotalring());//总的环数
            map.put("eachringmileage",shield.getEachringmileage());
            outputmap.put("shieldname",shield.getShieldname());
            outputmap.put("shieldno",shield.getShieldno());
        }
        if(pro==null){
            map.put("currentnumber",0);//当前环数
            map.put("currentmileage",0);//当前里程
        }else{
            map.put("currentnumber",pro.getHistorynumber());//当前环数
            map.put("currentmileage",pro.getHistorynumber()*shield.getEachringmileage());//当前里程
        }
        //百分比
        BigDecimal curr=new BigDecimal(map.get("currentnumber").toString());
        BigDecimal sum=new BigDecimal(map.get("totalring").toString());
        BigDecimal divide = curr.divide(sum, 2, RoundingMode.HALF_UP);
        map.put("percentage",divide.multiply(new BigDecimal(100))+"%");
        //
        List<MilesEveryDayVO> list1=progressstatisticsMapper.queryMailesEveryDayNumber(shield.getId(),outletid);
        List<Object> totaldatenumber=new ArrayList<>();
        Map<String,Object> map1=new HashMap<>();
        for(MilesEveryDayVO milesEveryDayVO:list1){
            map1.put(milesEveryDayVO.getDate(),milesEveryDayVO.getNumber());
        }
        map.put("totaldatenumber",map1);
        //得到危险点
        List<DangerpointVO> dangerlist=dangerpointMapper.queryDangerListByShielId(shield.getId(),outletid);
        Float number=(shield.getStartpoint() == null) ? 0f : shield.getStartpoint();
        for(DangerpointVO dangerpointVO:dangerlist){
            dangerpointVO.setStartspacing(dangerpointVO.getStartspacing()+number);
        }
        //得到阶段目标
        List<TargetgoalsVO> targetlist=targetgoalsMapper.queryTargetListByShielId(shield.getId(),outletid);
        for(TargetgoalsVO targetgoalsVO:targetlist){
            targetgoalsVO.setTargetmileage(targetgoalsVO.getTargetmileage()+number);
        }
        //提到图片
        map.put("dangerlist",dangerlist);
        map.put("targetlist",targetlist);
        //获取图片地址：
        //String imageUrl=shieldimageMapper.queryShieldimageUrl(shield.getId(),outletid);
        List<ShieldImageVo> imageUrl=shieldimageMapper.queryShieldimageUrlList(shield.getId(),outletid);
        map.put("shieldImageUrl",imageUrl);
        //获取视频
        String ids=shield.getCamerano();
        List<ShieldCameraVO> cameraVOS=null;
        if(ids!=null){
            cameraVOS=cameraMapper.queryCameraByCameraNO(Arrays.asList(ids.split(",")),outletid);
        }
        map.put("videoinfo",cameraVOS);
        map.put("advancefx",shield.getAdvancefx());

        return map;
    }
}
