package com.secusoft.ssw.service.impl.monitor;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.common.servermessage.COMMAND_STATIC;
import com.secusoft.ssw.common.servermessage.SendMessageToVMC;
import com.secusoft.ssw.mapper.monitor.*;
import com.secusoft.ssw.model.monitor.Camera;
import com.secusoft.ssw.model.monitor.PlayGroup;
import com.secusoft.ssw.model.monitor.Videodevice;
import com.secusoft.ssw.model.viewobject.Request.DeviceManage;
import com.secusoft.ssw.model.viewobject.Request.DeviceMg;
import com.secusoft.ssw.model.viewobject.Response.DeviceCameraVO;
import com.secusoft.ssw.model.viewobject.Response.DeviceManageVO;
import com.secusoft.ssw.model.viewobject.Response.DevicetypenameVO;
import com.secusoft.ssw.service.DeviceService;
import com.secusoft.ssw.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ClassName DeviceServiceImpl
 * @Author jcyao
 * @Description
 * @Date 2018/9/5 14:20
 * @Version 1.0
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private PlayGroupMapper playGroupMapper;
    @Autowired
    private VideoDeviceMapper videoDeviceMapper;
    @Autowired
    private CameraMapper cameraMapper;
    @Autowired
    private SendMessageToVMC sendMessageToVMC;
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private DeviceManageMapper deviceManageMapper;

    @Override
    @Transactional
    public void setCamera(int monitorId, int userId, int outletId, int cameraNo, int playback, int second, int enableSlicing, int slicingTime) {
        Camera camera = cameraMapper.getCameraNoByCno(cameraNo,outletId);
        if (camera.getEnableSlicing() != enableSlicing || camera.getSlicingTime() != slicingTime) {
            List<Integer> userIdList = usersMapper.getUserIdListByCNoAndAuthLevel(cameraNo,14,outletId);
            Map<String,Object> cameraMap = new HashMap<>();
            cameraMap.put("IsCapture",enableSlicing);
            cameraMap.put("DeviceId",String.valueOf(camera.getDeviceno()));
            cameraMap.put("Interval",slicingTime);
            sendMessageToVMC.sendChanges(monitorId,COMMAND_STATIC.OPERATOR_MODIFY,COMMAND_STATIC.TYPE_PERIMETERCAMERA,StringUtils.join(userIdList,","),String.valueOf(cameraNo),new JSONObject(cameraMap));
        }
        camera.setEnableSlicing(enableSlicing);
        camera.setSlicingTime(slicingTime);
        camera.setOutletid(outletId);
        cameraMapper.updateCamera(camera);
        PlayGroup playGroup = playGroupMapper.selectPlayGroupByUserIdAndOutletId(userId,outletId);
        if(playGroup!=null){
            String groupInfo = playGroup.getGroupinfo();
            if(StringUtil.isBlank(groupInfo) || "null".equals(groupInfo)){
                groupInfo = "";
            }
            boolean needUpdate = false;
            if(!StringUtil.isBlank(groupInfo) && groupInfo.contains(cameraNo+":")){
                String newGroupInfo = "";
                String[] groups = groupInfo.split(";");
                for(String group : groups){
                    String[] cameraInfo = group.split(":");
                    if(cameraInfo[0].equals(Integer.valueOf(cameraNo).toString())){
                        if(playback==1){
                            group = cameraNo+":"+second;
                        }else{
                            group = "";
                        }
                        needUpdate = true;
                    }
                    if(!StringUtil.isBlank(group)){
                        newGroupInfo += group+";";
                    }
                }
                if(newGroupInfo.length()>0){
                    groupInfo = newGroupInfo.substring(0,newGroupInfo.length()-1);
                }else{
                    groupInfo = newGroupInfo;
                }
                if(!needUpdate){
                    groupInfo += ";"+cameraNo+":"+second;
                    needUpdate = true;
                }
            }else if(playback==1){
                if(StringUtil.isBlank(groupInfo)){
                    groupInfo=cameraNo+":"+second;
                }else{
                    groupInfo += ";"+cameraNo+":"+second;
                }
                needUpdate = true;
            }
            if(needUpdate){
                playGroup.setGroupinfo(groupInfo);
                playGroup.setOutletid(outletId);
                playGroupMapper.updatePlayGroup(playGroup);
            }
        }else if(playback==1){
            playGroup = new PlayGroup();
            playGroup.setUserid(userId);
            playGroup.setOutletid(outletId);
            playGroup.setGrouptype(0);
            playGroup.setGroupinfo(cameraNo+":"+second);
            playGroupMapper.insertPlayGroup(playGroup);
        }
    }

    @Override
    public Object saveDeviceType(Integer currentMonitorId, Integer currentOutletId, DevicetypenameVO devicetypenameVO) {
        devicetypenameVO.setOutletid(currentOutletId);
        DevicetypenameVO devicetypename = deviceManageMapper.queryDeviceByType(devicetypenameVO.getDevicetype(), currentOutletId);
        if (devicetypename != null){
            return GlobalApiResult.failure("已有该类型设备，不能重复添加");
        }
        deviceManageMapper.saveDeviceType(devicetypenameVO);
        return "添加成功";
    }

    @Override
    public Object saveDevice(Integer currentMonitorId, Integer currentOutletId, DeviceManageVO deviceManageVO) {
       String msg = "";
        deviceManageVO.setOutletid(currentOutletId);
        if (deviceManageVO.getDrivername() == null){
            deviceManageVO.setDrivername("");
        }
        if (deviceManageVO.getTelephone() == null){
            deviceManageVO.setTelephone("");
        }
        if (deviceManageVO.getOperate() == null){
            deviceManageVO.setOperate("");
        }
        if (deviceManageVO.getExamineid() == null){
            deviceManageVO.setExamineid("");
        }
        if (deviceManageVO.getId() == null){
            deviceManageMapper.saveDevice(deviceManageVO);
            msg = "添加成功";
        }else {
            deviceManageMapper.updateDevice(deviceManageVO);
            msg = "修改成功";
        }

        return msg;
    }

    @Override
    public Object deleteDevice(Integer currentMonitorId, Integer currentOutletId, List<String> list) {
       if ( CollectionUtils.isEmpty(list)||list == null){
           return GlobalApiResult.failure("id不能为空");
       }
        deviceManageMapper.deleteDevice(list);
        return "删除成功";
    }

    @Override
    public Object queryAllDevice(Integer currentMonitorId, Integer currentOutletId, DeviceMg deviceMg) {
        deviceMg.setOutletid(currentOutletId);
        if (deviceMg.getCurrentPage() == null || deviceMg.getPageSize() == null){
            return GlobalApiResult.failure("请选择分页");
        }
        PageHelper.startPage(deviceMg.getCurrentPage(),deviceMg.getPageSize());
        List<DeviceManage> deviceManages  = deviceManageMapper.queryAllDevice(deviceMg);
            for (DeviceManage deviceManage : deviceManages) {
                if (deviceManage.getExaminetime() == null){
                    deviceManage.setExaminetime("");
                }
            }
        PageInfo<DeviceManage> pageInfo = new PageInfo<>(deviceManages);
        return pageInfo;
    }

    @Override
    public Object queryDeviceType(Integer currentMonitorId, Integer currentOutletId) {
        return deviceManageMapper.queryDeviceType(currentOutletId);
    }

    @Override
    public Object queryOneDevice(Integer currentMonitorId, Integer currentOutletId, Integer id) {
        if (id == null){
            return  GlobalApiResult.failure("id不能为空");
        }
        DeviceManage deviceManage = deviceManageMapper.queryOneDevice(id, currentOutletId);
        return deviceManage;
    }

    @Override
    public List<DeviceCameraVO> getDeviceCamera(int monitorId, int userId, int outletId) {
        List<DeviceCameraVO> result = new ArrayList<>();
        Map<String,String> playBackInfo = new HashMap<>();
        PlayGroup playGroup = playGroupMapper.selectPlayGroupByUserIdAndOutletId(userId,outletId);
        if(playGroup!=null && !StringUtil.isBlank(playGroup.getGroupinfo())){
            for(String group : playGroup.getGroupinfo().split(";")){
                String[] cameraInfo = group.split(":");
                if(cameraInfo!=null && cameraInfo.length==2){
                    playBackInfo.put(cameraInfo[0],cameraInfo[1]);
                }
            }
        }
        int grouptype = 0;
        if(playGroup!=null && playGroup.getGrouptype()!=0){
            grouptype = playGroup.getGrouptype();
        }
        List<Videodevice> videoDevices = videoDeviceMapper.selectVideoDeviceByOutletId(outletId);
        if(videoDevices!=null && videoDevices.size()>0){
            for(Videodevice videoDevice : videoDevices){
                DeviceCameraVO deviceCameraVO = new DeviceCameraVO();
                deviceCameraVO.setDeviceno(null);
                deviceCameraVO.setCamerano("d_"+videoDevice.getDeviceno());
                deviceCameraVO.setName(videoDevice.getDevicename());
                deviceCameraVO.setDevicetype(videoDevice.getDevicetype());
                deviceCameraVO.setType(1);
                result.add(deviceCameraVO);
                List<Camera> cameras = cameraMapper.selectCameraByDeviceNo(videoDevice.getDeviceno(),outletId);
                if(cameras!=null && cameras.size()>0){
                    for(Camera camera : cameras){
                        DeviceCameraVO deviceCameraVO_ = new DeviceCameraVO();
                        deviceCameraVO_.setDeviceno("d_"+videoDevice.getDeviceno());
                        deviceCameraVO_.setCamerano("c_"+camera.getCamerano());
                        deviceCameraVO_.setName(camera.getCameraname());
                        deviceCameraVO_.setDevicetype(videoDevice.getDevicetype());
                        deviceCameraVO_.setType(2);
                        deviceCameraVO_.setIpaddress(videoDevice.getIpaddress());
                        deviceCameraVO_.setTcpport(videoDevice.getTcpport());
                        deviceCameraVO_.setUsername(videoDevice.getUsername());
                        deviceCameraVO_.setPasswd(videoDevice.getPasswd());
                        deviceCameraVO_.setChanindex(camera.getChanindex());
                        deviceCameraVO_.setGrouptype(grouptype);
                        deviceCameraVO_.setSlicingTime(camera.getSlicingTime());
                        deviceCameraVO_.setEnableSlicing(camera.getEnableSlicing());
                        if(playBackInfo.get(""+camera.getCamerano())!=null){
                            deviceCameraVO_.setPlayback(1);
                            deviceCameraVO_.setSecond(Integer.valueOf(playBackInfo.get(""+camera.getCamerano())));
                        }else{
                            deviceCameraVO_.setPlayback(0);
                            deviceCameraVO_.setSecond(0);
                        }
                        result.add(deviceCameraVO_);
                    }
                }
            }
        }
        return result;
    }

}
