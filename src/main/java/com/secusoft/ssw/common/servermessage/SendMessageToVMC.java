package com.secusoft.ssw.common.servermessage;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.Socket;
import java.util.UUID;

@Service
public class SendMessageToVMC {
    private final Logger logger = LoggerFactory.getLogger(SendMessageToVMC.class);
    private static String LOGINID ="" ;

    public synchronized String sendChanges(int monitorId,String operator,String type, String userId,String id,JSONObject json){
        try{
            Socket socket = NetCommunicationLibrary.getSocket();
            if ((NetCommunicationLibrary.isServerClose(socket))||socket==null||!socket.isConnected()||socket.isClosed()) {
                LOGINID = NetCommunicationLibrary.getNetCommunicationLibrary().login(false);
            }
            json.put("CommandId","10131003");
            json.put("LoginId",LOGINID);
            json.put("Token", UUID.randomUUID());
            json.put("Monitor",String.valueOf(monitorId));
            json.put("Type",type);
            json.put("Operator",operator);
            json.put("ID",id);
            json.put("Comment","");
            json.put("UserId",userId);
            String messageJsonStr = json.toJSONString();
            String resultStr = NetCommunicationLibrary.getNetCommunicationLibrary().sendToVMC(messageJsonStr);
            if(resultStr == null) return null;
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            String resultCode = String.valueOf(jsonObject.get("ResultCode"));
            logger.info("vmcAnswer result:[{}]==========="+resultStr);
            if ("-10".equals(resultCode)) {
                LOGINID = NetCommunicationLibrary.getNetCommunicationLibrary().login(false);
            }
            if(!"0".equals(resultCode)){
                logger.info("发送消息成功,操作失败！");
            }
            return resultStr;
        }catch(Exception e){
            logger.error("发送VMCServer异常",e);
            return null;
        }
    }
}

