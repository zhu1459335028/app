package com.secusoft.ssw.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.secusoft.ssw.common.ApplicationContextRegister;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.viewobject.Request.Shields8;
import com.secusoft.ssw.model.viewobject.Request.Tbm8;
import com.secusoft.ssw.model.viewobject.Request.WebSocketMessageVO;
import com.secusoft.ssw.service.ProgressstatisticsService;
import com.secusoft.ssw.service.ShieldService;
import com.secusoft.ssw.util.JwtUtil;
import com.secusoft.ssw.util.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//import com.secusoft.ssw.model.viewobject.Request.ShieldEs;
//import com.secusoft.ssw.service.impl.monitor.ShieldEs8hxService;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * @ClassName WebSocket
 * @Author jcyao
 * @Description websocket服务器端
 * @Date 2018/8/31 17:30
 * @Version 1.0
 */
@ServerEndpoint("/WebSocketToTunnel8")
//虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean
@Component
public class WebSocketToTunnel8 {

	private static final Logger log = LoggerFactory.getLogger(WebSocketToTunnel8.class);
    private static ConcurrentHashMap<String, WebSocketToTunnel8> webSocketMap = new ConcurrentHashMap<String, WebSocketToTunnel8>();
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
                        ShieldService shieldService=ApplicationContextRegister.getApplicationContext().getBean(ShieldService.class);
                        ProgressstatisticsService progressstatisticsService=ApplicationContextRegister.getApplicationContext().getBean(ProgressstatisticsService.class);

                        log.info("webSocket初始化盾构数据开始:");
                        //获取所有的盾构的惟一标识
                        WebSocketMessageVO shieldMessage = new WebSocketMessageVO();
                        List<String> shieldids=shieldService.queryAllid(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]));
                        List<Object> esList=new ArrayList<>();
                        try{
                            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                            credentialsProvider.setCredentials(AuthScope.ANY,
                                    new UsernamePasswordCredentials("elastic", "secusoftofzhrmghg2019@123"));
                            RestClient restClient = RestClient.builder(new HttpHost("es-cn-mp916vnnf000j15g8.public.elasticsearch.aliyuncs.com", 9200))
                                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                                        @Override
                                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                                        }
                                    }).build();
                            Map<String,Object> map1=new HashMap<>();
                            //index a document
                            HttpEntity sort = new NStringEntity("{\n" +
                                    "  \n" +
                                    "    \"sort\": [\n" +
                                    "        { \"datetime.keyword\":   { \"order\": \"desc\" }}\n" +
                                    "\n" +
                                    "    ]\n" +
                                    "}", ContentType.APPLICATION_JSON);

                            Response response = restClient.performRequest("GET", "/tunnel8/tbm/_search",Collections.<String, String>emptyMap(),sort);

                            String shields = EntityUtils.toString(response.getEntity());
                            JSONObject jsonObj = JSON.parseObject(shields);
                            JSONObject hits = jsonObj.getJSONObject("hits");
                            JSONArray arr = hits.getJSONArray("hits");
                            if(Objects.nonNull(arr)){
                                for (int i = 0;i < arr.size();i++) {
                                    if (i == 0){
                                        String _id = arr.getJSONObject(i).getString("_id");
                                        JSONObject obj = arr.getJSONObject(i).getJSONObject("_source");
                                        System.out.println("id======================"+_id+",_source===============" + obj);
                                        map1.put("es",obj);
                                        String str = obj.getString("shieldno");
                                        System.out.println("==========="+str);
                                        Map<String,Object> last7days= progressstatisticsService.queryatestWeek(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]),str,map1);
                                        map1.put("mysql",last7days);
                                    }
                                }
                            }
                            esList.add(map1);
                        } catch (Exception e){
                                System.out.println("连接异常");
                                Map<String,Object> map1=new HashMap<>();
 //                               Map<String,Object> last7days= progressstatisticsService.queryatestWeek(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]),str,map1);
                                String str1="{\n" +
                                        "\t\"tbm1\": \"0\",\n" +
                                        "\t\"tbm2\": \"0\",\n" +
                                        "\t\"tbm3\": \"0\",\n" +
                                        "\t\"tbm4\": \"0\",\n" +
                                        "\t\"tbm5\": \"0\",\n" +
                                        "\t\"tbm6\": \"0\",\n" +
                                        "\t\"tbm7\": \"0\",\n" +
                                        "\t\"tbm8\": \"0\",\n" +
                                        "\t\"tbm9\": \"0\",\n" +
                                        "\t\"tbm10\": \"0\",\n" +
                                        "\t\"tbm11\": \"0\",\n" +
                                        "\t\"tbm12\": \"0\",\n" +
                                        "\t\"tbm13\": \"0\",\n" +
                                        "\t\"tbm14\": \"0\",\n" +
                                        "\t\"tbm15\": \"0\",\n" +
                                        "\t\"tbm16\": \"0\",\n" +
                                        "\t\"datetime\": \"2019-04-29 11:21:01\",\n" +
                                        "\t\"id\": \"7b2f0699-52b4-42b2-a75b-6ccb8cb64fd111111\",\n" +
                                        "\t\"outletid\": 2,\n" +
                                        "\t\"shieldno\": \"ICONICS.SimulatorOPCDA.1\"\n" +
                                        "}";
                                Tbm8 tbm8=JSON.parseObject(str1, Tbm8.class);
                                List<Tbm8> list=new ArrayList<>();
                                list.add(tbm8);
                                map1.put("es",tbm8);
//                                map1.put("mysql",last7days);
                                esList.add(map1);
 //                               break;
                            }

  //                      }
                        shieldMessage.setData(esList);
                        shieldMessage.setType(00);
                        log.info("WebSocketToTunnel8初始化盾构数据结束:"+JSON.toJSONString(shieldMessage.getData()));
                        session.getBasicRemote().sendText(JSON.toJSONString(shieldMessage));

                    } catch (IOException e) {
                        log.error("webSocket2初始化盾构数据结束sessionId:"+session.getId(),e);
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
        log.info("WebSocket2调用sendMessage方法:");
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
                WebSocketToTunnel8 webSocket = webSocketMap.get(sessionId);
                if(webSocket!=null){
                    try {
                        log.info("WebSocket2开始发送消息=:");
                        webSocket.sendMessage(JSON.toJSONString(message));
                        log.info("WebSocket2发送消息成功=:"+JSON.toJSONString(message));
                    } catch (Exception e) {
                        log.error("WebSocket2发送消息异常，sessionId:"+sessionId,e);
                    }
                }
            }
            return true;
        }
        return false;
    }

}
