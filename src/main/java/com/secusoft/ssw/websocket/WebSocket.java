package com.secusoft.ssw.websocket;

import com.alibaba.fastjson.JSON;
import com.secusoft.ssw.common.ApplicationContextRegister;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.Gas;
//import com.secusoft.ssw.model.viewobject.Request.ShieldEs;
import com.secusoft.ssw.model.viewobject.Request.WebSocketMessageVO;
import com.secusoft.ssw.model.viewobject.Response.GasRespVO;
import com.secusoft.ssw.model.viewobject.Response.ThresholdRespVO;
import com.secusoft.ssw.model.viewobject.Response.VehicleScreenVO;
import com.secusoft.ssw.service.*;
import com.secusoft.ssw.util.JwtUtil;
import com.secusoft.ssw.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * @ClassName WebSocket
 * @Author jcyao
 * @Description websocket服务器端
 * @Date 2018/8/31 17:30
 * @Version 1.0
 */
@ServerEndpoint("/webSocketServer")
//虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean
@Component
public class WebSocket {

	private static final Logger log = LoggerFactory.getLogger(WebSocket.class);
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<String, WebSocket>();
    private static ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<String, String>();
    private static ConcurrentHashMap<String, String> userIdMap = new ConcurrentHashMap<String, String>();
    private static ConcurrentHashMap<String, String> outletIdMap = new ConcurrentHashMap<String, String>();

    public static final String TOKEN = "token";
    public static final String USERID = "userId";
    public static final String OUTLETID = "outletId";

    //与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private Session session;


    /** 
     * 连接建立成功调用的方法 
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据 
     */  
    @OnOpen  
    public void onOpen(Session session){  
        this.session = session;  
        log.info("连接建立成功，sessionId:" + session.getId());
        webSocketMap.put(session.getId(), this);
        if(!StringUtil.isBlank(session.getQueryString()) && session.getQueryString().startsWith("token=")){
        	String token = session.getQueryString().substring(6);
        	if(!StringUtil.isBlank(token)){
        	    //验证token，如若验证失败会抛出异常，触发onError
                String subject = JwtUtil.getSubject(token);
                String[] strs = subject.split(",");
                if(strs!=null && strs.length==3){
                    String tokenStr = strs[0]+"_"+strs[1]+"_"+strs[2];
                    String userId = strs[0]+"_"+strs[1];
                    String outletId = strs[0]+"_"+strs[2];
                    tokenMap.put(tokenStr, getMapValue(tokenMap,tokenStr)+session.getId());
                    userIdMap.put(userId, getMapValue(userIdMap,userId)+session.getId());
                    outletIdMap.put(outletId, getMapValue(outletIdMap,outletId)+session.getId());
                    try {
                        PerimeterManagementService perimeterManagementService = ApplicationContextRegister.getApplicationContext().getBean(PerimeterManagementService.class);
                        VehicleService vehicleService = ApplicationContextRegister.getApplicationContext().getBean(VehicleService.class);
                        WaterLevelService waterLevelService=ApplicationContextRegister.getApplicationContext().getBean(WaterLevelService.class);

                        HomePageService homePageService=ApplicationContextRegister.getApplicationContext().getBean(HomePageService.class);


                        WebSocketMessageVO webSocketMessage = new WebSocketMessageVO();
                        webSocketMessage.setType(6);
                        List<Map<String, Object>> dateList = perimeterManagementService.getCameraNoAndImageUrlByOid(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]));
                        Map<String,Object> map = new HashMap<>();
                        map.put("data",dateList);
                        webSocketMessage.setData(map);
                        session.getBasicRemote().sendText(JSON.toJSONString(webSocketMessage));
                        //车辆管理
                        log.info("webSocket初始化车辆信息开始=================");
                        WebSocketMessageVO webSocketMessage2 = new WebSocketMessageVO();
                        webSocketMessage2.setType(8);
                        log.info("webSocket初始化查询车辆信息开始：");
                        VehicleScreenVO vehicleScreenVO=vehicleService.queryNewVehicleScreenImage(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]));


                        log.info("webSocket初始化查询车辆信息为："+JSON.toJSONString(vehicleScreenVO));
                        webSocketMessage2.setData(vehicleScreenVO);
                        session.getBasicRemote().sendText(JSON.toJSONString(webSocketMessage2));
                        log.info("webSocket初始化车辆信息完成:"+JSON.toJSONString(webSocketMessage2));
                        //水位

//                        log.info("webSocket初始化水位信息开始:"+JSON.toJSONString(webSocketMessage2));
//                        Map<String,Object> map2=waterLevelService.queryWaterScreen(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]));
//                        WebSocketMessageVO webSocketMessage3 = new WebSocketMessageVO();
//                        webSocketMessage3.setType(9);
//                        webSocketMessage3.setData(map2);
//                        session.getBasicRemote().sendText(JSON.toJSONString(webSocketMessage3));
//                        log.info("webSocket初始化水位信息完成:"+JSON.toJSONString(webSocketMessage2));
                        log.info("webSocket初始化首页基坑开始：");
                        Map<String,Object> homemap=homePageService.foundManage(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]));
                        WebSocketMessageVO homepage = new WebSocketMessageVO();
                        homepage.setType(12);
                        homepage.setData(homemap);
                        session.getBasicRemote().sendText(JSON.toJSONString(homepage));
                        log.info("webSocket初始化首页基坑结束：");


                        log.info("webSocket初始化首页人员管理开始：");
                        Map<String,Object> permap=homePageService.personManage(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]));
                        WebSocketMessageVO personSocket = new WebSocketMessageVO();
                        personSocket.setType(13);
                        personSocket.setData(permap);
                        session.getBasicRemote().sendText(JSON.toJSONString(personSocket));
                        log.info("webSocket初始化首页人员管理结束：");
                        System.out.println(JSON.toJSONString(personSocket));


                        log.info("webSocket初始化气体开始：");
                        WebSocketMessageVO gasSocket = new WebSocketMessageVO();
                        Map<String, Object> list=homePageService.homePageGasPopupWindowInit(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]));
                        String pm25 = homePageService.queryPm25(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]));
                        ThresholdRespVO thresholdResp = homePageService.queryPm25ValueColer(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]));
                        Integer reportstatus=0;
                        List<BigDecimal> listthres=new ArrayList<>();
                        if (thresholdResp != null && StringUtils.isNotBlank(thresholdResp.getMax_val())){
                            String str = thresholdResp.getMax_val();
                            String color = str.substring(str.indexOf("#"),str.length()-2);
                            list.put("color",color);
                            lists(thresholdResp.getMax_val(),listthres);
                            BigDecimal szpm25 = listthres.get(0);
                            if (!StringUtil.isBlank(pm25)){
                                BigDecimal pm25va  = new  BigDecimal(pm25);
                                //如果传过来的值比设置的阈值大则报警
                                if (pm25va .compareTo(szpm25) > 0){
                                    reportstatus = 1;
                                }  else {
                                    // 否则不报警
                                    reportstatus = 0;
                                }
                            }
                        }
                        list.put("reportStatus",reportstatus);
                        gasSocket.setType(14);
                        gasSocket.setData(list);
                        session.getBasicRemote().sendText(JSON.toJSONString(gasSocket));
                        log.info("webSocket初始化气体结束：");

                    } catch (IOException | ParseException e) {
                        log.error("首页周界管理消息发送失败，sessionId:"+session.getId(),e);
                    }
                }
        	}
        }
    }  
  
    /** 
     * 连接关闭调用的方法 
     */  
    @OnClose  
    public void onClose(Session session){  
    	log.info("连接关闭，sessionId:" + session.getId());
    	webSocketMap.remove(session.getId());
    }  
  
    /** 
     * 收到客户端消息后调用的方法 
     * @param message 客户端发送过来的消息 
     * @param session 可选的参数 
     */  
    @OnMessage  
    public void onMessage(String message, Session session) { 
    	if(!StringUtil.isBlank(message)){
    		log.info("收到来自("+session.getId()+")客户端的消息:" + message);
			try {
				session.getBasicRemote().sendText(JSON.toJSONString(GlobalApiResult.success("收到消息")));
			} catch (IOException e) {
				log.error("回复客户端消息失败，sessionId:"+session.getId(),e);
			}
		}
    }  
  
    /** 
     * 发生错误时调用 
     * @param session 
     * @param error 
     */  
    @OnError  
    public void onError(Session session, Throwable error){  
    	log.error("WebSocket发生错误，sessionId:"+session.getId(),error);
        webSocketMap.remove(session.getId());
    }
  
    public void sendMessage(String message) throws IOException{  
        this.session.getBasicRemote().sendText(message);  
    }
    
    public void sendMessage(Object message) throws IOException, EncodeException{  
        this.session.getBasicRemote().sendObject(message);  
    }
    
    public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	private String getMapValue(Map<String,String> map, String key){
        String value = map.get(key);
        if(StringUtil.isBlank(value)){
            return "";
        }else{
            return value+";";
        }
    }

    public static boolean sendMessage(String type,String key,WebSocketMessageVO message){
        log.info("WebSocket调用sendMessage方法:");
	    Map<String,String> map;
	    switch (type){
            case TOKEN:
                map = tokenMap;
                break;
            case USERID:
                map = userIdMap;
                break;
            case OUTLETID:
                map = outletIdMap;
                break;
            default:return false;
        }
        String sessionIds = map.get(key);
	    if(!StringUtil.isBlank(sessionIds)){
	        String[] sessionIdArray = sessionIds.split(";");
	        for(String sessionId : sessionIdArray){
                WebSocket webSocket = webSocketMap.get(sessionId);
                if(webSocket!=null){
                    try {
                        log.info("WebSocket开始发送消息=:");
                        webSocket.sendMessage(JSON.toJSONString(message));
                        log.info("WebSocket发送消息成功=:"+JSON.toJSONString(message));
                    } catch (Exception e) {
                        log.error("WebSocket发送消息异常，sessionId:"+sessionId,e);
                    }
                }
            }
            return true;
        }
        return false;
    }


    private List<BigDecimal> lists(String str,List<BigDecimal> list){
        Map maps = (Map) JSON.parse(str);
        for (Object map : maps.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
            list.add(new BigDecimal(((Map.Entry)map).getKey()+""));
        }
        return list;
    }

}
