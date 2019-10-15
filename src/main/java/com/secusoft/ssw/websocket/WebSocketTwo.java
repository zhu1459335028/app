package com.secusoft.ssw.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.secusoft.ssw.common.ApplicationContextRegister;
import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.model.monitor.Shield;
import com.secusoft.ssw.model.viewobject.Request.ShieldEs;
import com.secusoft.ssw.model.viewobject.Request.WebSocketMessageVO;
import com.secusoft.ssw.service.ProgressstatisticsService;
//import com.secusoft.ssw.service.ShieldEsService;
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
@ServerEndpoint("/webSocketServerTbm")
//虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean
@Component
public class WebSocketTwo {

    private static final Logger log = LoggerFactory.getLogger(WebSocketTwo.class);
    private static ConcurrentHashMap<String, WebSocketTwo> webSocketMap = new ConcurrentHashMap<String, WebSocketTwo>();
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

                            Response response = restClient.performRequest("GET", "/shields7s/shield7/_search",Collections.<String, String>emptyMap(),sort);

                            String shields = EntityUtils.toString(response.getEntity());
                            JSONObject jsonObj = JSON.parseObject(shields);
                            JSONObject hits = jsonObj.getJSONObject("hits");
                            JSONArray arr = hits.getJSONArray("hits");
                            if(Objects.nonNull(arr)){
                                for (int i = 0;i < arr.size();i++) {
                                    if (i == 0){
                                        JSONObject obj = arr.getJSONObject(i).getJSONObject("_source");
                                        map1.put("es",obj);
                                        String str = obj.getString("shieldno");
                                        System.out.println("==========="+str);
                                        Map<String,Object> last7days= progressstatisticsService.queryatestWeek(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]),str,map1);
                                        map1.put("mysql",last7days);
                                        break;
                                    }
                                }
                            }
                            esList.add(map1);
                        } catch (Exception e){
                            System.out.println("连接异常");
                            Map<String,Object> map1=new HashMap<>();
                            Map<String,Object> last7days= progressstatisticsService.queryatestWeek(Integer.valueOf(strs[0]),Integer.valueOf(strs[2]),"ICONICS.SimulatorOPCDA.1",map1);
                            String str1="{\n" +
                                    "\t\"db40_100\": \"0\",\n" +
                                    "\t\"db40_1018\": \"0\",\n" +
                                    "\t\"db40_104\": \"0\",\n" +
                                    "\t\"db40_108\": \"0\",\n" +
                                    "\t\"db40_1084\": \"0\",\n" +
                                    "\t\"db40_1096\": \"0\",\n" +
                                    "\t\"db40_1100\": \"0\",\n" +
                                    "\t\"db40_1104\": \"0\",\n" +
                                    "\t\"db40_1108\": \"0\",\n" +
                                    "\t\"db40_1112\": \"0\",\n" +
                                    "\t\"db40_116\": \"0\",\n" +
                                    "\t\"db40_1166\": \"0\",\n" +
                                    "\t\"db40_1170\": \"0\",\n" +
                                    "\t\"db40_1174\": \"0\",\n" +
                                    "\t\"db40_1178\": \"0\",\n" +
                                    "\t\"db40_12\": \"0\",\n" +
                                    "\t\"db40_120\": \"0\",\n" +
                                    "\t\"db40_1228\": \"0\",\n" +
                                    "\t\"db40_1232\": \"0\",\n" +
                                    "\t\"db40_124\": \"0\",\n" +
                                    "\t\"db40_1240\": \"0\",\n" +
                                    "\t\"db40_1250\": \"0\",\n" +
                                    "\t\"db40_1258\": \"0\",\n" +
                                    "\t\"db40_1262\": \"0\",\n" +
                                    "\t\"db40_1266\": \"0\",\n" +
                                    "\t\"db40_1270\": \"0\",\n" +
                                    "\t\"db40_1274\": \"0\",\n" +
                                    "\t\"db40_1278\": \"0\",\n" +
                                    "\t\"db40_128\": \"0\",\n" +
                                    "\t\"db40_1282\": \"0\",\n" +
                                    "\t\"db40_1286\": \"0\",\n" +
                                    "\t\"db40_1290\": \"0\",\n" +
                                    "\t\"db40_1294\": \"0\",\n" +
                                    "\t\"db40_1322\": \"0\",\n" +
                                    "\t\"db40_1326\": \"0\",\n" +
                                    "\t\"db40_1330\": \"0\",\n" +
                                    "\t\"db40_1334\": \"0\",\n" +
                                    "\t\"db40_1338\": \"0\",\n" +
                                    "\t\"db40_1342\": \"0\",\n" +
                                    "\t\"db40_1346\": \"0\",\n" +
                                    "\t\"db40_1350\": \"0\",\n" +
                                    "\t\"db40_1354\": \"0\",\n" +
                                    "\t\"db40_1358\": \"0\",\n" +
                                    "\t\"db40_1380\": \"0\",\n" +
                                    "\t\"db40_1388\": \"0\",\n" +
                                    "\t\"db40_1392\": \"0\",\n" +
                                    "\t\"db40_1396\": \"0\",\n" +
                                    "\t\"db40_140\": \"0\",\n" +
                                    "\t\"db40_144\": \"0\",\n" +
                                    "\t\"db40_148\": \"0\",\n" +
                                    "\t\"db40_152\": \"0\",\n" +
                                    "\t\"db40_1544\": \"0\",\n" +
                                    "\t\"db40_1548\": \"0\",\n" +
                                    "\t\"db40_1552\": \"0\",\n" +
                                    "\t\"db40_1556\": \"0\",\n" +
                                    "\t\"db40_156\": \"0.5\",\n" +
                                    "\t\"db40_1560\": \"0\",\n" +
                                    "\t\"db40_160\": \"0\",\n" +
                                    "\t\"db40_164\": \"0\",\n" +
                                    "\t\"db40_168\": \"0\",\n" +
                                    "\t\"db40_172\": \"0\",\n" +
                                    "\t\"db40_176\": \"0\",\n" +
                                    "\t\"db40_180\": \"0\",\n" +
                                    "\t\"db40_184\": \"0\",\n" +
                                    "\t\"db40_188\": \"0\",\n" +
                                    "\t\"db40_192\": \"0\",\n" +
                                    "\t\"db40_196\": \"0\",\n" +
                                    "\t\"db40_20\": \"0\",\n" +
                                    "\t\"db40_200\": \"0\",\n" +
                                    "\t\"db40_208\": \"0\",\n" +
                                    "\t\"db40_212\": \"0\",\n" +
                                    "\t\"db40_216\": \"0\",\n" +
                                    "\t\"db40_224\": \"0\",\n" +
                                    "\t\"db40_228\": \"0\",\n" +
                                    "\t\"db40_232\": \"0\",\n" +
                                    "\t\"db40_236\": \"0\",\n" +
                                    "\t\"db40_24\": \"0\",\n" +
                                    "\t\"db40_240\": \"0\",\n" +
                                    "\t\"db40_244\": \"0\",\n" +
                                    "\t\"db40_248\": \"0\",\n" +
                                    "\t\"db40_256\": \"0\",\n" +
                                    "\t\"db40_260\": \"0\",\n" +
                                    "\t\"db40_276\": \"0\",\n" +
                                    "\t\"db40_296\": \"0\",\n" +
                                    "\t\"db40_308\": \"0\",\n" +
                                    "\t\"db40_32\": \"0\",\n" +
                                    "\t\"db40_320\": \"0\",\n" +
                                    "\t\"db40_336\": \"0\",\n" +
                                    "\t\"db40_340\": \"0\",\n" +
                                    "\t\"db40_344\": \"0\",\n" +
                                    "\t\"db40_352\": \"0\",\n" +
                                    "\t\"db40_372\": \"0\",\n" +
                                    "\t\"db40_374\": \"0\",\n" +
                                    "\t\"db40_4\": \"0\",\n" +
                                    "\t\"db40_420\": \"0\",\n" +
                                    "\t\"db40_44\": \"0\",\n" +
                                    "\t\"db40_458\": \"0\",\n" +
                                    "\t\"db40_460\": \"0\",\n" +
                                    "\t\"db40_464\": \"0\",\n" +
                                    "\t\"db40_466\": \"0\",\n" +
                                    "\t\"db40_512\": \"0\",\n" +
                                    "\t\"db40_516\": \"0\",\n" +
                                    "\t\"db40_52\": \"0\",\n" +
                                    "\t\"db40_520\": \"0\",\n" +
                                    "\t\"db40_524\": \"0\",\n" +
                                    "\t\"db40_528\": \"0\",\n" +
                                    "\t\"db40_532\": \"0\",\n" +
                                    "\t\"db40_536\": \"0\",\n" +
                                    "\t\"db40_540\": \"0\",\n" +
                                    "\t\"db40_544\": \"0\",\n" +
                                    "\t\"db40_548\": \"0\",\n" +
                                    "\t\"db40_552\": \"0\",\n" +
                                    "\t\"db40_572\": \"0\",\n" +
                                    "\t\"db40_576\": \"0\",\n" +
                                    "\t\"db40_580\": \"0\",\n" +
                                    "\t\"db40_584\": \"0\",\n" +
                                    "\t\"db40_588\": \"0\",\n" +
                                    "\t\"db40_592\": \"0\",\n" +
                                    "\t\"db40_596\": \"0\",\n" +
                                    "\t\"db40_60\": \"0\",\n" +
                                    "\t\"db40_600\": \"0\",\n" +
                                    "\t\"db40_604\": \"0\",\n" +
                                    "\t\"db40_608\": \"0\",\n" +
                                    "\t\"db40_612\": \"0\",\n" +
                                    "\t\"db40_616\": \"0\",\n" +
                                    "\t\"db40_620\": \"0.10828\",\n" +
                                    "\t\"db40_624\": \"0\",\n" +
                                    "\t\"db40_628\": \"0\",\n" +
                                    "\t\"db40_632\": \"0\",\n" +
                                    "\t\"db40_636\": \"0\",\n" +
                                    "\t\"db40_640\": \"0\",\n" +
                                    "\t\"db40_644\": \"0\",\n" +
                                    "\t\"db40_648\": \"0\",\n" +
                                    "\t\"db40_652\": \"0\",\n" +
                                    "\t\"db40_656\": \"0\",\n" +
                                    "\t\"db40_660\": \"0\",\n" +
                                    "\t\"db40_664\": \"0\",\n" +
                                    "\t\"db40_668\": \"0\",\n" +
                                    "\t\"db40_676\": \"0\",\n" +
                                    "\t\"db40_68\": \"0\",\n" +
                                    "\t\"db40_72\": \"0\",\n" +
                                    "\t\"db40_728\": \"0\",\n" +
                                    "\t\"db40_732\": \"0\",\n" +
                                    "\t\"db40_736\": \"0\",\n" +
                                    "\t\"db40_744\": \"0\",\n" +
                                    "\t\"db40_748\": \"0\",\n" +
                                    "\t\"db40_752\": \"0\",\n" +
                                    "\t\"db40_772\": \"0\",\n" +
                                    "\t\"db40_778\": \"0\",\n" +
                                    "\t\"db40_794\": \"0\",\n" +
                                    "\t\"db40_798\": \"0\",\n" +
                                    "\t\"db40_8\": \"0\",\n" +
                                    "\t\"db40_80\": \"0\",\n" +
                                    "\t\"db40_802\": \"0\",\n" +
                                    "\t\"db40_84\": \"0\",\n" +
                                    "\t\"db40_854\": \"0\",\n" +
                                    "\t\"db40_858\": \"0\",\n" +
                                    "\t\"db40_862\": \"0\",\n" +
                                    "\t\"db40_878\": \"0\",\n" +
                                    "\t\"db40_88\": \"0\",\n" +
                                    "\t\"db40_882\": \"0\",\n" +
                                    "\t\"db40_886\": \"0\",\n" +
                                    "\t\"db40_902\": \"0\",\n" +
                                    "\t\"db40_906\": \"0\",\n" +
                                    "\t\"db40_910\": \"0\",\n" +
                                    "\t\"db40_92\": \"0\",\n" +
                                    "\t\"db40_928\": \"0\",\n" +
                                    "\t\"db40_932\": \"0\",\n" +
                                    "\t\"db40_936\": \"0\",\n" +
                                    "\t\"db40_952\": \"0\",\n" +
                                    "\t\"db40_956\": \"0\",\n" +
                                    "\t\"db40_96\": \"0\",\n" +
                                    "\t\"db40_960\": \"0\",\n" +
                                    "\t\"db40_992\": \"0\",\n" +
                                    "\t\"db43_148\": \"0\",\n" +
                                    "\t\"db43_180\": \"0\",\n" +
                                    "\t\"db43_184\": \"0\",\n" +
                                    "\t\"db43_188\": \"0\",\n" +
                                    "\t\"db43_196\": \"0\",\n" +
                                    "\t\"db43_200\": \"0\",\n" +
                                    "\t\"db43_204\": \"0\",\n" +
                                    "\t\"db43_256\": \"0\",\n" +
                                    "\t\"db43_260\": \"0\",\n" +
                                    "\t\"db43_464\": \"0\",\n" +
                                    "\t\"db43_468\": \"0\",\n" +
                                    "\t\"db43_486\": \"0\",\n" +
                                    "\t\"db43_494\": \"0\",\n" +
                                    "\t\"db43_498\": \"0\",\n" +
                                    "\t\"db43_502\": \"0\",\n" +
                                    "\t\"db43_506\": \"0\",\n" +
                                    "\t\"db43_520\": \"0\",\n" +
                                    "\t\"db43_524\": \"0\",\n" +
                                    "\t\"db43_528\": \"0\",\n" +
                                    "\t\"db43_544\": \"0\",\n" +
                                    "\t\"db43_548\": \"0\",\n" +
                                    "\t\"db43_552\": \"0\",\n" +
                                    "\t\"db43_602\": \"0\",\n" +
                                    "\t\"db43_628\": \"0\",\n" +
                                    "\t\"db43_72\": \"0\",\n" +
                                    "\t\"db46_36\": \"0\",\n" +
                                    "\t\"db46_40\": \"0\",\n" +
                                    "\t\"db46_44\": \"0\",\n" +
                                    "\t\"db46_48\": \"0\",\n" +
                                    "\t\"db47_0\": \"0\",\n" +
                                    "\t\"db47_16\": \"0\",\n" +
                                    "\t\"db47_256\": \"0\",\n" +
                                    "\t\"db47_4\": \"0\",\n" +
                                    "\t\"db47_52\": \"0\",\n" +
                                    "\t\"db47_56\": \"0\",\n" +
                                    "\t\"db47_60\": \"0\",\n" +
                                    "\t\"db47_64\": \"0\",\n" +
                                    "\t\"db47_76\": \"0\",\n" +
                                    "\t\"db47_80\": \"0\",\n" +
                                    "\t\"db49_100\": \"0\",\n" +
                                    "\t\"db49_116\": \"0\",\n" +
                                    "\t\"db49_120\": \"0\",\n" +
                                    "\t\"db49_124\": \"0\",\n" +
                                    "\t\"db49_128\": \"0\",\n" +
                                    "\t\"db49_132\": \"0\",\n" +
                                    "\t\"db49_136\": \"0\",\n" +
                                    "\t\"db49_156\": \"0\",\n" +
                                    "\t\"db49_160\": \"0\",\n" +
                                    "\t\"db49_180\": \"0\",\n" +
                                    "\t\"db49_184\": \"0\",\n" +
                                    "\t\"db49_204\": \"0\",\n" +
                                    "\t\"db49_208\": \"0\",\n" +
                                    "\t\"db49_212\": \"0\",\n" +
                                    "\t\"db49_216\": \"0\",\n" +
                                    "\t\"db49_240\": \"0\",\n" +
                                    "\t\"db49_244\": \"0\",\n" +
                                    "\t\"db49_280\": \"0\",\n" +
                                    "\t\"db49_284\": \"0\",\n" +
                                    "\t\"db49_288\": \"0\",\n" +
                                    "\t\"db49_292\": \"0\",\n" +
                                    "\t\"db49_296\": \"0\",\n" +
                                    "\t\"db49_300\": \"0\",\n" +
                                    "\t\"db49_328\": \"0\",\n" +
                                    "\t\"db49_332\": \"0\",\n" +
                                    "\t\"db49_80\": \"0\",\n" +
                                    "\t\"db49_84\": \"0\",\n" +
                                    "\t\"db49_88\": \"0\",\n" +
                                    "\t\"db49_92\": \"0\",\n" +
                                    "\t\"db49_96\": \"0\",\n" +
                                    "\t\"db90_0\": \"0\",\n" +
                                    "\t\"db90_12\": \"0\",\n" +
                                    "\t\"db90_16\": \"0\",\n" +
                                    "\t\"db90_20\": \"0\",\n" +
                                    "\t\"db90_32\": \"0\",\n" +
                                    "\t\"db90_4\": \"0\",\n" +
                                    "\t\"db90_400\": \"0\",\n" +
                                    "\t\"db90_404\": \"0\",\n" +
                                    "\t\"db90_408\": \"0\",\n" +
                                    "\t\"db90_412\": \"0\",\n" +
                                    "\t\"db90_416\": \"0\",\n" +
                                    "\t\"db90_420\": \"0\",\n" +
                                    "\t\"db90_432\": \"0\",\n" +
                                    "\t\"db90_8\": \"0\",\n" +
                                    "\t\"m58_3\": \"0\",\n" +
                                    "\t\"m58_4\": \"1\",\n" +
                                    "\t\"datetime\": \"2019-04-29 11:21:01\",\n" +
                                    "\t\"id\": \"7b2f0699-52b4-42b2-a75b-6ccb8cb64fd111111\",\n" +
                                    "\t\"db49_404\": \"1672\",\n" +
                                    "\t\"outletid\": 2,\n" +
                                    "\t\"shieldno\": \"ICONICS.SimulatorOPCDA.1\"\n" +
                                    "}";
                            ShieldEs shield=JSON.parseObject(str1, ShieldEs.class);
                            List<ShieldEs> list=new ArrayList<>();
                            list.add(shield);
                            map1.put("es",shield);
                            map1.put("mysql",last7days);
                            esList.add(map1);
//                                break;
                        }

                        //                      }
                        shieldMessage.setData(esList);
                        shieldMessage.setType(00);
                        log.info("webSocket2初始化盾构数据结束:"+JSON.toJSONString(shieldMessage.getData()));
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
                WebSocketTwo webSocket = webSocketMap.get(sessionId);
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
