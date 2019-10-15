package com.secusoft.ssw.service.impl.monitor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.mapper.monitor.VehicleMapper;
import com.secusoft.ssw.mapper.monitor.VehicleRecordMapper;
import com.secusoft.ssw.model.monitor.VehicleRecord;
import com.secusoft.ssw.model.viewobject.Request.VehicleRecordVo;
import com.secusoft.ssw.model.viewobject.Response.VehicleRecordReVO;
import com.secusoft.ssw.model.viewobject.Response.VehicleRecordVO;
import com.secusoft.ssw.model.viewobject.Response.VehiclerecordDetailoRespVO;
import com.secusoft.ssw.service.VehicleRecordService;
import com.secusoft.ssw.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleRecordServiceImpl implements VehicleRecordService {

    @Autowired
    VehicleMapper vehicleMapper;

    @Autowired
    VehicleRecordMapper vehicleRecordMapper;



    @Override
    public GlobalApiResult<Object> getVehicleRecordPage(Integer currentMonitorId,Integer outletid, VehicleRecordVo vehicleRecordVo) {
        PageHelper.startPage(vehicleRecordVo.getCurrentPage(),vehicleRecordVo.getPageSize());
        vehicleRecordVo.setOutletid(outletid);
        List<VehicleRecordVO> list=new ArrayList<>();
        Integer count=null;
        System.out.println(vehicleRecordVo.getCompany());
        if((StringUtils.isNotBlank(vehicleRecordVo.getCompany())&&vehicleRecordVo.getCompany().equals("未登记"))||(StringUtils.isNotBlank(vehicleRecordVo.getType())&&vehicleRecordVo.getType().equals("未知"))){

            if(StringUtils.isNotBlank(vehicleRecordVo.getCompany())&&vehicleRecordVo.getCompany().equals("未登记")){
                //List<String> listid=vehicleMapper.queryVehicleList();
                list =vehicleRecordMapper.queryVehicleRecordListByIds(vehicleRecordVo);
                count=vehicleRecordMapper.queryVehicleRecordCount2(vehicleRecordVo);
            }
            if(StringUtils.isNotBlank(vehicleRecordVo.getType())&&vehicleRecordVo.getType().equals("未知")){
                list =vehicleRecordMapper.queryVehicleRecordListByIds(vehicleRecordVo);
                count=vehicleRecordMapper.queryVehicleRecordCount2(vehicleRecordVo);
            }
        }else if((StringUtils.isNotBlank(vehicleRecordVo.getCompany())&&vehicleRecordVo.getCompany().equals("未登记"))&&(StringUtils.isNotBlank(vehicleRecordVo.getType())&&vehicleRecordVo.getType().equals("未知"))){
             list =vehicleRecordMapper.queryVehicleRecordListByIds(vehicleRecordVo);
              count=vehicleRecordMapper.queryVehicleRecordCount2(vehicleRecordVo);

        } else{
//         list=vehicleRecordMapper.queryVehicleRecordList(vehicleRecordVo);
//         count=vehicleRecordMapper.queryVehicleRecordCount(vehicleRecordVo);
            list = vehicleRecordMapper.queryVehicleRecordListAll(vehicleRecordVo);
            count = vehicleRecordMapper.queryVehicleRecordCounts(vehicleRecordVo);

        }


        List<VehicleRecordReVO> respList=new ArrayList<>();
        for(VehicleRecordVO vehicleRecordVO:list){
            Integer entryouttype;
              if(vehicleRecordVO.getEntryouttype()==0){
                  entryouttype=1;
              }else{
                  entryouttype=0;
              }
              //通过uuid与进出类型查询另一条数据
              VehicleRecord vehicleRecord=vehicleRecordMapper.queryConditionList(vehicleRecordVO.getUuid(),entryouttype,vehicleRecordVo.getOutletid());
              if (vehicleRecord == null){
                  continue;
              }
              //进数据组装
              VehicleRecordReVO respvo=new VehicleRecordReVO();
              respvo.setVehicleType(vehicleRecordVO.getVehicletype());
              respvo.setCompany(vehicleRecordVO.getCompany());
              respvo.setPlate_number(vehicleRecordVO.getPlate_number());
              if(vehicleRecordVO.getEntryouttype()==0){//进场
                  if(vehicleRecordVO.getHead_imagetime()!=null){
                      respvo.setEntry_imageurl(vehicleRecordVO.getHead_imageurl());
                      respvo.setEntry_imagetime(vehicleRecordVO.getHead_imagetime());
                  }
                  if(vehicleRecord.getHead_imagetime()!=null){
                      respvo.setOut_imageurl(vehicleRecord.getHead_imageurl());
                      respvo.setOut_imagetime(DateUtils.getDate2String(vehicleRecord.getHead_imagetime(),"yyyy-MM-dd HH:mm:ss"));
                  }

              }else{
                  if(vehicleRecordVO.getHead_imagetime()!=null){//出场图片
                      respvo.setOut_imageurl(vehicleRecordVO.getHead_imageurl());
                      respvo.setOut_imagetime(vehicleRecordVO.getHead_imagetime());
                  }
                  if(vehicleRecord!=null){
                      if(vehicleRecord.getHead_imagetime()!=null){
                          respvo.setEntry_imageurl(vehicleRecord.getHead_imageurl());
                          respvo.setEntry_imagetime(DateUtils.getDate2String(vehicleRecord.getHead_imagetime(),"yyyy-MM-dd HH:mm:ss"));
                      }
                  }
              }
            if(vehicleRecordVO.getEntryouttype()==0){
                respvo.setEntryId(vehicleRecordVO.getId());
                respvo.setOutId(vehicleRecord.getId());
            }else if(vehicleRecordVO.getEntryouttype()==1){
                respvo.setOutId(vehicleRecordVO.getId());
                respvo.setEntryId(vehicleRecord.getId());
            }else if(vehicleRecord.getType()==1){
                respvo.setOutId(vehicleRecord.getId());
                respvo.setEntryId(vehicleRecordVO.getId());
            }else if(vehicleRecord.getType()==0){
                respvo.setOutId(vehicleRecordVO.getId());
                respvo.setEntryId(vehicleRecord.getId());
            }
              //判断车辆状态--
              if(vehicleRecordVO.getEntryouttype()==1&&vehicleRecord.getType()==1){
                   respvo.setVehicleStatus("异常");
              }else if(vehicleRecordVO.getEntryouttype()==0&&vehicleRecord.getType()==0){
                  respvo.setVehicleStatus("异常");
              }else if(vehicleRecordVO.getEntryouttype()==0&&vehicleRecord.getType()==1&& vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getHead_imagetime()!=null&&vehicleRecord.getId()>vehicleRecordVO.getId()){
                  respvo.setVehicleStatus("离场");
              }else if(vehicleRecordVO.getEntryouttype()==0 &&vehicleRecord.getType()==1&& vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getHead_imagetime()!=null&&vehicleRecordVO.getId()<vehicleRecord.getId()){
                  respvo.setVehicleStatus("场内");
              }else if(vehicleRecordVO.getEntryouttype()==0&& vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getHead_imagetime()!=null&&vehicleRecord.getId()<vehicleRecordVO.getId()){
                  respvo.setVehicleStatus("离场");
              }else if(vehicleRecordVO.getEntryouttype()==1&& vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getHead_imagetime()!=null&&vehicleRecordVO.getId()<vehicleRecord.getId()){
                  respvo.setVehicleStatus("场内");
              }else if(vehicleRecord.getType()==0&&vehicleRecordVO.getEntryouttype()==1 && vehicleRecordVO.getHead_imagetime()!=null &&vehicleRecord.getId()<vehicleRecordVO.getId()){
                  respvo.setVehicleStatus("离场");
              }else if(vehicleRecord.getType()==0&&vehicleRecordVO.getEntryouttype()==1 && vehicleRecordVO.getHead_imagetime()!=null && vehicleRecord.getHead_imagetime()==null &&vehicleRecord.getId()>vehicleRecordVO.getId()){
                  respvo.setVehicleStatus("异常");
              }else if(vehicleRecord.getType()==1&&vehicleRecordVO.getEntryouttype()==0 && vehicleRecord.getHead_imagetime()!=null &&vehicleRecord.getId()<vehicleRecordVO.getId()){
                  respvo.setVehicleStatus("场内");
              }else if(vehicleRecord.getType()==1&&vehicleRecordVO.getEntryouttype()==0 && vehicleRecord.getHead_imagetime()!=null &&vehicleRecord.getId()>vehicleRecordVO.getId()){
                      respvo.setVehicleStatus("异常");
              }else if(vehicleRecord.getType()==0&&vehicleRecordVO.getEntryouttype()==1 && vehicleRecordVO.getHead_imagetime()==null &&vehicleRecord.getId()<vehicleRecordVO.getId()){
                  respvo.setVehicleStatus("离场");
              }else if(vehicleRecord.getType()==0&&vehicleRecordVO.getEntryouttype()==1 && vehicleRecordVO.getHead_imagetime()==null &&vehicleRecord.getId()>vehicleRecordVO.getId()){
                  respvo.setVehicleStatus("场内");
              }else if(vehicleRecord.getType()==1&&vehicleRecordVO.getEntryouttype()==0 && vehicleRecord.getHead_imagetime()==null &&vehicleRecord.getId()<vehicleRecordVO.getId()){
                  respvo.setVehicleStatus("离场");
              }else if(vehicleRecord.getType()==1&&vehicleRecordVO.getEntryouttype()==0 && vehicleRecord.getHead_imagetime()==null &&vehicleRecord.getId()>vehicleRecordVO.getId()){
                  respvo.setVehicleStatus("场内");
              }


            if(vehicleRecordVO.getPatch().equals("1")||vehicleRecord.getPatch().equals("1")){
                respvo.setVehicleStatus("离场");
            }

              //详情照片
              List<VehiclerecordDetailoRespVO> detailoRespVOS=new ArrayList<>();
              if(vehicleRecordVO.getHead_imagetime()!=null&&vehicleRecordVO.getEntryouttype()==0){
                  VehiclerecordDetailoRespVO  vehiclerecordDetailoRespVO1=getVehicleRecordDetailData(vehicleRecordVO.getBody_imageurl(),vehicleRecordVO.getCamera_body(),0,"俯拍全身",vehicleRecordVO.getBody_imagetime());
                  VehiclerecordDetailoRespVO  vehiclerecordDetailoRespVO2=getVehicleRecordDetailData(vehicleRecordVO.getHead_imageurl(),vehicleRecordVO.getCamera_head(),0,"前脸照片",vehicleRecordVO.getHead_imagetime());

                  detailoRespVOS.add(vehiclerecordDetailoRespVO1);
                  detailoRespVOS.add(vehiclerecordDetailoRespVO2);
              }
              if(vehicleRecordVO.getHead_imagetime()!=null&&vehicleRecordVO.getEntryouttype()==1){
                VehiclerecordDetailoRespVO   vehiclerecordDetailoRespVO1=getVehicleRecordDetailData(vehicleRecordVO.getBody_imageurl(),vehicleRecordVO.getCamera_body(),1,"俯拍全身",vehicleRecordVO.getBody_imagetime());
                VehiclerecordDetailoRespVO   vehiclerecordDetailoRespVO2=getVehicleRecordDetailData(vehicleRecordVO.getHead_imageurl(),vehicleRecordVO.getCamera_head(),1,"前脸照片",vehicleRecordVO.getHead_imagetime());
                  detailoRespVOS.add(vehiclerecordDetailoRespVO1);
                  detailoRespVOS.add(vehiclerecordDetailoRespVO2);
              }
            if(vehicleRecord!=null){
              if(vehicleRecord.getHead_imagetime()!=null&&vehicleRecord.getType()==0){
                  VehiclerecordDetailoRespVO  vehiclerecordDetailoRespVO3=getVehicleRecordDetailData(vehicleRecord.getBody_imageurl(),vehicleRecord.getCamera_body(),0,"俯拍全身",DateUtils.getDate2String(vehicleRecord.getBody_imagetime(),"yyyy-MM-dd HH:mm:ss"));
                  VehiclerecordDetailoRespVO  vehiclerecordDetailoRespVO4=getVehicleRecordDetailData(vehicleRecord.getHead_imageurl(),vehicleRecord.getCamera_head(),0,"前脸照片",DateUtils.getDate2String(vehicleRecord.getHead_imagetime(),"yyyy-MM-dd HH:mm:ss"));

                  detailoRespVOS.add(vehiclerecordDetailoRespVO3);
                  detailoRespVOS.add(vehiclerecordDetailoRespVO4);
              }
              if(vehicleRecord.getHead_imagetime()!=null&&vehicleRecord.getType()==1){
                  VehiclerecordDetailoRespVO  vehiclerecordDetailoRespVO3=getVehicleRecordDetailData(vehicleRecord.getBody_imageurl(),vehicleRecord.getCamera_body(),1,"俯拍全身",DateUtils.getDate2String(vehicleRecord.getBody_imagetime(),"yyyy-MM-dd HH:mm:ss"));
                  VehiclerecordDetailoRespVO  vehiclerecordDetailoRespVO4=getVehicleRecordDetailData(vehicleRecord.getHead_imageurl(),vehicleRecord.getCamera_head(),1,"前脸照片",DateUtils.getDate2String(vehicleRecord.getHead_imagetime(),"yyyy-MM-dd HH:mm:ss"));
                  detailoRespVOS.add(vehiclerecordDetailoRespVO3);
                  detailoRespVOS.add(vehiclerecordDetailoRespVO4);
              }
            }
            respvo.setDetailoRespVos(detailoRespVOS);
            respList.add(respvo);
        }
        PageInfo<List<VehicleRecordReVO>> pageInfo = new PageInfo(respList);
        pageInfo.setTotal(count);
        return GlobalApiResult.success(pageInfo);
    }

    /**
     * 组装详情照片
     * @param vehicleRecordVO
     * @return
     */
    private VehiclerecordDetailoRespVO getVehicleRecordDetailData(String url,String source,int type,String imagetype,String imagetime ){
        VehiclerecordDetailoRespVO detailoRespVO=new VehiclerecordDetailoRespVO();
        detailoRespVO.setUrl(url);
        detailoRespVO.setSource(source);
        detailoRespVO.setType(type);
        detailoRespVO.setImagetype(imagetype);
        detailoRespVO.setImagetime(imagetime);
        return detailoRespVO;
    }


    @Transactional
    @Override
    public GlobalApiResult<Object> updateVehicle(Integer currentMonitorId,Integer outletid, int id, String bltime) {
        Integer pid=vehicleRecordMapper.updateVehicleRecord(id,bltime,outletid);
        return GlobalApiResult.success("更新成功");
    }
}


