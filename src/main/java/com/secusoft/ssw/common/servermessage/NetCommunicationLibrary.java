package com.secusoft.ssw.common.servermessage;


import com.alibaba.fastjson.JSONObject;
import com.secusoft.ssw.util.DES;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Properties;


/**
 * Created by Administration on 2017/12/24.
 * 网络通信库
 */
public class NetCommunicationLibrary {

    private final static Logger logger = LoggerFactory.getLogger(NetCommunicationLibrary.class);
    private static NetCommunicationLibrary netCommunicationLibrary;
    private static Socket socket;
    private static final String vmcHost;
    private static final int vmcPort;
    private static final int vmcConnectTimeOut;
    private static final int vmcReadTimeOut;
    private static final String vmcUsername;
    private static final String vmcPassword;
    private static final String desKey;

    static {
        String separator = System.getProperty("file.separator");
        String configPath = System.getProperty("user.dir")+separator+"config"+separator+"application.properties";
        Properties properties = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(
                    new File(configPath)));
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        vmcHost = properties.getProperty("vmc.vmcHost");
        vmcPort = Integer.parseInt(properties.getProperty("vmc.vmcPort"));
        vmcConnectTimeOut = Integer.parseInt(properties.getProperty("vmc.vmcConnectTimeOut"));
        vmcReadTimeOut = Integer.parseInt(properties.getProperty("vmc.vmcReadTimeOut"));
        vmcUsername = properties.getProperty("vmc.loginName");
        vmcPassword = properties.getProperty("vmc.passWord");
        desKey = properties.getProperty("vmc.desKey");
    }

    /**
     * 以单例模式获取网络通信库实例
     * @return  网络通信库实例
     */
    public static NetCommunicationLibrary getNetCommunicationLibrary() {
        if (netCommunicationLibrary == null) {
            netCommunicationLibrary = new NetCommunicationLibrary();
        }
        return netCommunicationLibrary;
    }

    public static Socket getSocket() {
        return socket;
    }

    /**
     * 数据交互
     * @param encodeRequestBody  加密后的请求body
     * @return  server返回的加密后的应答body
     */
    public synchronized String transferData(String encodeRequestBody) {
        try {
            //如果之前tcp连接未建立或者连接断开了，则再次建立tcp连接
            if (isServerClose(socket)||socket==null||!socket.isConnected()||socket.isClosed()) {
                socket = new Socket();
                socket.connect(new InetSocketAddress(vmcHost,vmcPort), vmcConnectTimeOut);
                socket.setSoTimeout(vmcReadTimeOut);
            }
            if (socket!=null&&socket.isConnected() && !socket.isClosed() && !isServerClose(socket)) {
                OutputStream os = socket.getOutputStream();
                InputStream is = socket.getInputStream();
                StringBuffer sb = new StringBuffer();
                //包头
                sb.append("POST / HTTP/1.1\r\n");
                sb.append("Accept:Application/json\r\n");
                sb.append("Connection:Keep-Alive\r\n");
                sb.append("content-type:Application/json;charset=utf-8\r\n");
                sb.append("Cache-Control:no-cache\r\n");
                sb.append("Content-Length:" + encodeRequestBody.length() + "\r\n\r\n");
                //包体
                sb.append(encodeRequestBody);
                //发送数据
                os.write(sb.toString().getBytes());
                os.flush();
                //接收数据
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int temp;
                boolean bl = true;
                //用于检测socket网络远端是否断开连接
                temp = is.read();
                if (temp == -1) {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(vmcHost,vmcPort), vmcConnectTimeOut);
                    socket.setSoTimeout(vmcReadTimeOut);
                    os = socket.getOutputStream();
                    is = socket.getInputStream();
                    //发送数据
                    os.write(sb.toString().getBytes());
                    os.flush();
                } else {
                    byteArrayOutputStream.write(temp);
                }
                while (bl) {
                    temp = is.read();
                    byteArrayOutputStream.write(temp);
                    String responsePackage = new String(byteArrayOutputStream.toByteArray());
                    if (responsePackage.endsWith("\r\n\r\n")) {
                        bl = false;
                    }
                }
                String responseHead = new String(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
                logger.info("ResponseHead[{}]", responseHead);
                int contentLength = Integer.parseInt(responseHead.substring(responseHead.indexOf("Content-Length:") + 15, responseHead.indexOf("\r\nConnection:keep-alive")));
                byte[] buffer = new byte[contentLength];
                int readLength = 0;
                while (readLength < contentLength) {
                    temp = is.read(buffer);
                    byteArrayOutputStream.write(buffer, 0, temp);
                    readLength += temp;
                }
                String responseBody = new String(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
                byteArrayOutputStream.close();
                logger.info("ResponseBody[{}]", responseBody);
                return responseBody;
            }
        } catch (SocketTimeoutException e) {
            logger.error("发送VMCServer异常",e);
        } catch (SocketException e) {
            logger.error("发送VMCServer异常",e);
        } catch (IOException e) {
            logger.error("发送VMCServer异常",e);
        }
        logger.error("获取vmcServer返回失败");
        return null;
    }

    /**
     * 判断socket网络远端是否断开连接
     * @param socket
     * @return  true:断开  false:未断开
     */
    public static boolean isServerClose(Socket socket) {
        try {
            socket.sendUrgentData(0);  //发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public String login(Boolean reLogin){
        JSONObject json = new JSONObject();
        if(reLogin){
            json.put("CommandId","10131007");
        }else {
            json.put("CommandId","10131005");
        }
        json.put("UserName",vmcUsername);
        json.put("UserPasswd",vmcPassword);
        json.put("Token", String.valueOf(System.currentTimeMillis()));
        json.put("Type","1");
        String LoginJsonStr = json.toString();
        logger.info("登录vmcServer======================");
        String resultJsonStr = sendToVMC(LoginJsonStr);
        if(resultJsonStr==null){
            return null;
        }
        JSONObject resultJson = JSONObject.parseObject(resultJsonStr);
        String resultCode = resultJson.get("ResultCode").toString();
        if(!"0".equals(resultCode)){
            return null;
        }
        String loginID = resultJson.get("LoginId").toString();
        return loginID;
    }

    public String logout(String loginID){
        JSONObject logoutjson = new JSONObject();
        logoutjson.put("CommandId","10131006");
        logoutjson.put("LoginId",loginID);
        logoutjson.put("Token", String.valueOf(System.currentTimeMillis()));
        logoutjson.put("Type","1");
        String LogoutJsonStr = logoutjson.toString();
        System.out.println("=======================logout=======================");
        String resultJsonStr = sendToVMC(LogoutJsonStr);
        if(resultJsonStr==null){
            return null;
        }
        return resultJsonStr;
    }

    public String sendToVMC(String jsonStr){
        logger.info("send to Vmc data :[{}]============",jsonStr);
        /**
         * 加密：DES加密--->Base64加密
         */
        byte[] key = desKey.getBytes();
        byte[] finalKey = new byte[8];
        for (int i = 0; i < 8; i++) {
            finalKey[i] = key[i];
        }
        byte[] desRequestBody = DES.encryptDES(jsonStr.getBytes(), finalKey);
        String base64RequestBody = new String(Base64.encodeBase64(desRequestBody));
        String resultStr = transferData(base64RequestBody);
        if(resultStr==null){
            return null;
        }
        byte[] desResult = Base64.decodeBase64(resultStr);
        byte[] result = DES.decryptDES(desResult,finalKey);
        String resultJsonStr = new String(result);
        logger.info("VMC SERVER ANSWER==[{}]=============",resultJsonStr);
        return resultJsonStr;
    }
}
