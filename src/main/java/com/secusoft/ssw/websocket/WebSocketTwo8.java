package com.secusoft.ssw.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.secusoft.ssw.common.ApplicationContextRegister;
import com.secusoft.ssw.common.GlobalApiResult;
//import com.secusoft.ssw.model.viewobject.Request.ShieldEs;
import com.secusoft.ssw.model.viewobject.Request.Shields8;
import com.secusoft.ssw.model.viewobject.Request.WebSocketMessageVO;
import com.secusoft.ssw.service.ProgressstatisticsService;

import com.secusoft.ssw.service.ShieldService;
//import com.secusoft.ssw.service.impl.monitor.ShieldEs8hxService;
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
@ServerEndpoint("/webSocketServerTbm8hx")
//虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean
@Component
public class WebSocketTwo8 {

	private static final Logger log = LoggerFactory.getLogger(WebSocketTwo8.class);
    private static ConcurrentHashMap<String, WebSocketTwo8> webSocketMap = new ConcurrentHashMap<String, WebSocketTwo8>();
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

                            Response response = restClient.performRequest("GET", "/shields8s/shields/_search",Collections.<String, String>emptyMap(),sort);

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
                                        String str = obj.getString("sbbh");
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
                                        "\t\"hpjsq\": \"0\",\n" +
                                        "\t\"sbcg\": \"0\",\n" +
                                        "\t\"sbqj\": \"0\",\n" +
                                        "\t\"yyyxwd\": \"0\",\n" +
                                        "\t\"yahyqwd\": \"0\",\n" +
                                        "\t\"zqdlqhswd\": \"0\",\n" +
                                        "\t\"zqdlqsll\": \"0\",\n" +
                                        "\t\"tcjsll\": \"0\",\n" +
                                        "\t\"clyxwd1\": \"0\",\n" +
                                        "\t\"clyxwd2\": \"0\",\n" +
                                        "\t\"zzcjyll\": \"0\",\n" +
                                        "\t\"xcljyll1\": \"0\",\n" +
                                        "\t\"xcljyll2\": \"0\",\n" +
                                        "\t\"xcljyll3\": \"0\",\n" +
                                        "\t\"xcljyll4\": \"0\",\n" +
                                        "\t\"xcljyll5\": \"0\",\n" +
                                        "\t\"xcljyll6\": \"0\",\n" +
                                        "\t\"xcljyll7\": \"0\",\n" +
                                        "\t\"xcljyll8\": \"0\",\n" +
                                        "\t\"zzcgdcljyll1\": \"0\",\n" +
                                        "\t\"zzcgdcljyll2\": \"0\",\n" +
                                        "\t\"xclzcgdcljyll1\": \"0\",\n" +
                                        "\t\"xclzcgdcljyll2\": \"0\",\n" +
                                        "\t\"xclzcgdcljyll3\": \"0\",\n" +
                                        "\t\"xclzcgdcljyll4\": \"0\",\n" +
                                        "\t\"zccdedwmfljl1\": \"0\",\n" +
                                        "\t\"zccdedwmfljl2\": \"0\",\n" +
                                        "\t\"zccdedwmfljl3\": \"0\",\n" +
                                        "\t\"zzcdsdwmfljl1\": \"0\",\n" +
                                        "\t\"zzcdsdwmfljl2\": \"0\",\n" +
                                        "\t\"xclqhgdjyll\": \"0\",\n" +
                                        "\t\"zzcyzjsq\": \"0\",\n" +
                                        "\t\"zqdhbwbcjsq1\": \"0\",\n" +
                                        "\t\"zqdhbwbcjsq2\": \"0\",\n" +
                                        "\t\"yzcyjsq\": \"0\",\n" +
                                        "\t\"zzcnmfyzll\": \"0\",\n" +
                                        "\t\"hbwll1\": \"0\",\n" +
                                        "\t\"hbwll2\": \"0\",\n" +
                                        "\t\"zzcdedwmfll1\": \"0\",\n" +
                                        "\t\"zzcdedwmfll2\": \"0\",\n" +
                                        "\t\"zzcdedwmfll3\": \"0\",\n" +
                                        "\t\"zzcdsdwmfll1\": \"0\",\n" +
                                        "\t\"zzcdsdwmfll2\": \"0\",\n" +
                                        "\t\"zqdep2ylw1\": \"0\",\n" +
                                        "\t\"zqdep2ylw2\": \"0\",\n" +
                                        "\t\"zqdep2ylw3\": \"0\",\n" +
                                        "\t\"zqdep2ylw4\": \"0\",\n" +
                                        "\t\"zqdep2ylw5\": \"0\",\n" +
                                        "\t\"hzjtyzll1\": \"0\",\n" +
                                        "\t\"hzjtyzll2\": \"0\",\n" +
                                        "\t\"jbjhbwnmfljl\": \"0\",\n" +
                                        "\t\"zqdhbwnmfljl\": \"0\",\n" +
                                        "\t\"dpgzzt\": \"0\",\n" +
                                        "\t\"dpyxxkztz\": \"0\",\n" +
                                        "\t\"dpsd\": \"0\",\n" +
                                        "\t\"dpyybckyl\": \"0\",\n" +
                                        "\t\"dplywd\": \"0\",\n" +
                                        "\t\"dpbybyl\": \"0\",\n" +
                                        "\t\"dpjd\": \"0\",\n" +
                                        "\t\"cwdxc11\": \"0\",\n" +
                                        "\t\"cwdxc12\": \"0\",\n" +
                                        "\t\"dpzs\": \"0\",\n" +
                                        "\t\"dpzz\": \"0\",\n" +
                                        "\t\"bplqwxhsll\": \"0\",\n" +
                                        "\t\"bplqnxhsll\": \"0\",\n" +
                                        "\t\"bplqnxhsyl1\": \"0\",\n" +
                                        "\t\"bplqnxhsyl2\": \"0\",\n" +
                                        "\t\"bplqwxhswd\": \"0\",\n" +
                                        "\t\"bplqnxhswd1\": \"0\",\n" +
                                        "\t\"bplqnxhswd2\": \"0\",\n" +
                                        "\t\"bplqnxhswd\": \"0\",\n" +
                                        "\t\"dtgzzt\": \"0\",\n" +
                                        "\t\"tjyxxkztz\": \"0\",\n" +
                                        "\t\"ztjbckyl\": \"0\",\n" +
                                        "\t\"dstjbckyl\": \"0\",\n" +
                                        "\t\"azygtjyl\": \"0\",\n" +
                                        "\t\"bzygtjyl\": \"0\",\n" +
                                        "\t\"czygtjyl\": \"0\",\n" +
                                        "\t\"dzygtjyl\": \"0\",\n" +
                                        "\t\"ezygtjyl\": \"0\",\n" +
                                        "\t\"fzygtjyl\": \"0\",\n" +
                                        "\t\"tjsdpjz\": \"0\",\n" +
                                        "\t\"grd\": \"0\",\n" +
                                        "\t\"ztjl\": \"0\",\n" +
                                        "\t\"azugxc\": \"0\",\n" +
                                        "\t\"bzugxc\": \"0\",\n" +
                                        "\t\"czugxc\": \"0\",\n" +
                                        "\t\"dzugxc\": \"0\",\n" +
                                        "\t\"ezugxc\": \"0\",\n" +
                                        "\t\"fzugxc\": \"0\",\n" +
                                        "\t\"pjtjxc\": \"0\",\n" +
                                        "\t\"tlzxx\": \"0\",\n" +
                                        "\t\"tlzzy\": \"0\",\n" +
                                        "\t\"azjjd\": \"0\",\n" +
                                        "\t\"dwmfyztsd\": \"0\",\n" +
                                        "\t\"zjbjxzsd\": \"0\",\n" +
                                        "\t\"yjbjxzsd\": \"0\",\n" +
                                        "\t\"jbqztjx\": \"0\",\n" +
                                        "\t\"jbqgzzt\": \"0\",\n" +
                                        "\t\"zjbjxzsd\": \"0\",\n" +
                                        "\t\"yjbjxzsd\": \"0\",\n" +
                                        "\t\"jbqrhyzjsq\": \"0\",\n" +
                                        "\t\"ssjyzll\": \"0\",\n" +
                                        "\t\"wdmfyl11\": \"0\",\n" +
                                        "\t\"wdmfyl12\": \"0\",\n" +
                                        "\t\"wdmfyl13\": \"0\",\n" +
                                        "\t\"wdmfyl14\": \"0\",\n" +
                                        "\t\"wdmfyl15\": \"0\",\n" +
                                        "\t\"wdmfyl16\": \"0\",\n" +
                                        "\t\"wdmfyl17\": \"0\",\n" +
                                        "\t\"wdmfyl18\": \"0\",\n" +
                                        "\t\"wdmfyl19\": \"0\",\n" +
                                        "\t\"wdmfyl110\": \"0\",\n" +
                                        "\t\"wdmfyl111\": \"0\",\n" +
                                        "\t\"wdmfyl112\": \"0\",\n" +
                                        "\t\"wdmfyl113\": \"0\",\n" +
                                        "\t\"wdmfyl114\": \"0\",\n" +
                                        "\t\"wdmfyl115\": \"0\",\n" +
                                        "\t\"wdmfyl116\": \"0\",\n" +
                                        "\t\"wdmfyl21\": \"0\",\n" +
                                        "\t\"wdmfyl22\": \"0\",\n" +
                                        "\t\"wdmfyl23\": \"0\",\n" +
                                        "\t\"wdmfyl24\": \"0\",\n" +
                                        "\t\"wdmfyl25\": \"0\",\n" +
                                        "\t\"wdmfyl26\": \"0\",\n" +
                                        "\t\"wdmfyl27\": \"0\",\n" +
                                        "\t\"wdmfyl28\": \"0\",\n" +
                                        "\t\"wdmfyl29\": \"0\",\n" +
                                        "\t\"wdmfyl210\": \"0\",\n" +
                                        "\t\"wdmfyl211\": \"0\",\n" +
                                        "\t\"wdmfyl212\": \"0\",\n" +
                                        "\t\"wdmfyl213\": \"0\",\n" +
                                        "\t\"wdmfyl214\": \"0\",\n" +
                                        "\t\"wdmfyl215\": \"0\",\n" +
                                        "\t\"wdmfyl216\": \"0\",\n" +
                                        "\t\"wdmfyl31\": \"0\",\n" +
                                        "\t\"wdmfyl32\": \"0\",\n" +
                                        "\t\"wdmfyl33\": \"0\",\n" +
                                        "\t\"wdmfyl34\": \"0\",\n" +
                                        "\t\"wdmfyl35\": \"0\",\n" +
                                        "\t\"wdmfyl36\": \"0\",\n" +
                                        "\t\"wdmfyl37\": \"0\",\n" +
                                        "\t\"wdmfyl38\": \"0\",\n" +
                                        "\t\"wdmfyl39\": \"0\",\n" +
                                        "\t\"wdmfyl310\": \"0\",\n" +
                                        "\t\"wdmfyl311\": \"0\",\n" +
                                        "\t\"wdmfyl312\": \"0\",\n" +
                                        "\t\"wdmfyl313\": \"0\",\n" +
                                        "\t\"wdmfyl314\": \"0\",\n" +
                                        "\t\"wdmfyl315\": \"0\",\n" +
                                        "\t\"wdmfyl316\": \"0\",\n" +
                                        "\t\"wdmfyl41\": \"0\",\n" +
                                        "\t\"wdmfyl42\": \"0\",\n" +
                                        "\t\"wdmfyl43\": \"0\",\n" +
                                        "\t\"wdmfyl44\": \"0\",\n" +
                                        "\t\"wdmfyl45\": \"0\",\n" +
                                        "\t\"wdmfyl46\": \"0\",\n" +
                                        "\t\"wdmfyl47\": \"0\",\n" +
                                        "\t\"wdmfyl48\": \"0\",\n" +
                                        "\t\"wdmfyl49\": \"0\",\n" +
                                        "\t\"wdmfyl410\": \"0\",\n" +
                                        "\t\"wdmfyl411\": \"0\",\n" +
                                        "\t\"wdmfyl412\": \"0\",\n" +
                                        "\t\"wdmfyl413\": \"0\",\n" +
                                        "\t\"wdmfyl414\": \"0\",\n" +
                                        "\t\"wdmfyl415\": \"0\",\n" +
                                        "\t\"wdmfyl416\": \"0\",\n" +
                                        "\t\"ssf1tdjsq\": \"0\",\n" +
                                        "\t\"ssf2tdjsq\": \"0\",\n" +
                                        "\t\"ssf3tdjsq\": \"0\",\n" +
                                        "\t\"ssf4tdjsq\": \"0\",\n" +
                                        "\t\"ssf5tdjsq\": \"0\",\n" +
                                        "\t\"ssf6tdjsq\": \"0\",\n" +
                                        "\t\"ssf7tdjsq\": \"0\",\n" +
                                        "\t\"ssf8tdjsq\": \"0\",\n" +
                                        "\t\"ssf9tdjsq\": \"0\",\n" +
                                        "\t\"ssf10tdjsq\": \"0\",\n" +
                                        "\t\"ssf11tdjsq\": \"0\",\n" +
                                        "\t\"ssf12tdjsq\": \"0\",\n" +
                                        "\t\"ssf13tdjsq\": \"0\",\n" +
                                        "\t\"ssf14tdjsq\": \"0\",\n" +
                                        "\t\"ssf15tdjsq\": \"0\",\n" +
                                        "\t\"ssf16tdjsq\": \"0\",\n" +
                                        "\t\"ssf17tdjsq\": \"0\",\n" +
                                        "\t\"ssf18tdjsq\": \"0\",\n" +
                                        "\t\"ssf19tdjsq\": \"0\",\n" +
                                        "\t\"ssf20tdjsq\": \"0\",\n" +
                                        "\t\"ssf21tdjsq\": \"0\",\n" +
                                        "\t\"ssf22tdjsq\": \"0\",\n" +
                                        "\t\"ssf23tdjsq\": \"0\",\n" +
                                        "\t\"ssf24tdjsq\": \"0\",\n" +
                                        "\t\"ssf25tdjsq\": \"0\",\n" +
                                        "\t\"ssf26tdjsq\": \"0\",\n" +
                                        "\t\"ssf27tdjsq\": \"0\",\n" +
                                        "\t\"ssf28tdjsq\": \"0\",\n" +
                                        "\t\"ssf29tdjsq\": \"0\",\n" +
                                        "\t\"ssf30tdjsq\": \"0\",\n" +
                                        "\t\"ssf31tdjsq\": \"0\",\n" +
                                        "\t\"ssf32tdjsq\": \"0\",\n" +
                                        "\t\"ssf33tdjsq\": \"0\",\n" +
                                        "\t\"ssf34tdjsq\": \"0\",\n" +
                                        "\t\"ssf35tdjsq\": \"0\",\n" +
                                        "\t\"ssf36tdjsq\": \"0\",\n" +
                                        "\t\"ssf37tdjsq\": \"0\",\n" +
                                        "\t\"ssf38tdjsq\": \"0\",\n" +
                                        "\t\"ssf39tdjsq\": \"0\",\n" +
                                        "\t\"ssf40tdjsq\": \"0\",\n" +
                                        "\t\"ssf41tdjsq\": \"0\",\n" +
                                        "\t\"ssf42tdjsq\": \"0\",\n" +
                                        "\t\"ssf43tdjsq\": \"0\",\n" +
                                        "\t\"ssf44tdjsq\": \"0\",\n" +
                                        "\t\"ssf45tdjsq\": \"0\",\n" +
                                        "\t\"ssf46tdjsq\": \"0\",\n" +
                                        "\t\"ssf47tdjsq\": \"0\",\n" +
                                        "\t\"ssf48tdjsq\": \"0\",\n" +
                                        "\t\"ssf49tdjsq\": \"0\",\n" +
                                        "\t\"ssf50tdjsq\": \"0\",\n" +
                                        "\t\"ssf51tdjsq\": \"0\",\n" +
                                        "\t\"ssf52tdjsq\": \"0\",\n" +
                                        "\t\"ssf53tdjsq\": \"0\",\n" +
                                        "\t\"ssf54tdjsq\": \"0\",\n" +
                                        "\t\"ssf55tdjsq\": \"0\",\n" +
                                        "\t\"ssf56tdjsq\": \"0\",\n" +
                                        "\t\"ssf57tdjsq\": \"0\",\n" +
                                        "\t\"ssf58tdjsq\": \"0\",\n" +
                                        "\t\"ssf59tdjsq\": \"0\",\n" +
                                        "\t\"ssf60tdjsq\": \"0\",\n" +
                                        "\t\"ssf61tdjsq\": \"0\",\n" +
                                        "\t\"ssf62tdjsq\": \"0\",\n" +
                                        "\t\"ssf63tdjsq\": \"0\",\n" +
                                        "\t\"ssf64tdjsq\": \"0\",\n" +
                                        "\t\"ssfzjsq1\": \"0\",\n" +
                                        "\t\"ssfzjsq2\": \"0\",\n" +
                                        "\t\"ssfzjsq3\": \"0\",\n" +
                                        "\t\"ssfzjsq4\": \"0\",\n" +
                                        "\t\"sbqtlyl\": \"0\",\n" +
                                        "\t\"ty1\": \"0\",\n" +
                                        "\t\"ty2\": \"0\",\n" +
                                        "\t\"ty3\": \"0\",\n" +
                                        "\t\"ty4\": \"0\",\n" +
                                        "\t\"ty5\": \"0\",\n" +
                                        "\t\"ty6\": \"0\",\n" +
                                        "\t\"typjz\": \"0\",\n" +
                                        "\t\"gzcprtywjc1\": \"0\",\n" +
                                        "\t\"gzcprtywjc2\": \"0\",\n" +
                                        "\t\"pjgljymdjc\": \"0\",\n" +
                                        "\t\"pjgljyll\": \"0\",\n" +
                                        "\t\"jjgljymdjc\": \"0\",\n" +
                                        "\t\"jjgljyll\": \"0\",\n" +
                                        "\t\"jjgljyyljc\": \"0\",\n" +
                                        "\t\"cxbdlp01\": \"0\",\n" +
                                        "\t\"cxbsdp01\": \"0\",\n" +
                                        "\t\"cxbdlp02\": \"0\",\n" +
                                        "\t\"cxbsdp02\": \"0\",\n" +
                                        "\t\"cxbdlp13\": \"0\",\n" +
                                        "\t\"cxbsdp13\": \"0\",\n" +
                                        "\t\"cxbdlp14\": \"0\",\n" +
                                        "\t\"cxbsdp14\": \"0\",\n" +
                                        "\t\"cxbdlp11\": \"0\",\n" +
                                        "\t\"cxbsdp11\": \"0\",\n" +
                                        "\t\"jbjp11jnkyljc\": \"0\",\n" +
                                        "\t\"jbjp11cnkyljc\": \"0\",\n" +
                                        "\t\"p11bplqwxhsll\": \"0\",\n" +
                                        "\t\"p11bplqnxhsll\": \"0\",\n" +
                                        "\t\"p11bplqnxhsyl\": \"0\",\n" +
                                        "\t\"p11bplqwxhswd\": \"0\",\n" +
                                        "\t\"p11bplqnxhcswd\": \"0\",\n" +
                                        "\t\"p11bplqnxhhswd\": \"0\",\n" +
                                        "\t\"jjbdlp12\": \"0\",\n" +
                                        "\t\"jjbsdp12\": \"0\",\n" +
                                        "\t\"jjbp12jnkyljc\": \"0\",\n" +
                                        "\t\"jjbp12cnkyljc\": \"0\",\n" +
                                        "\t\"p12djrzwd\": \"0\",\n" +
                                        "\t\"p12djzcwd\": \"0\",\n" +
                                        "\t\"p12bplqnxhsyl\": \"0\",\n" +
                                        "\t\"p12bplqwxhswd\": \"0\",\n" +
                                        "\t\"p12bplqnxhcswd\": \"0\",\n" +
                                        "\t\"p12bplqnxhhswd\": \"0\",\n" +
                                        "\t\"pjbdlp21\": \"0\",\n" +
                                        "\t\"pjbsdp21\": \"0\",\n" +
                                        "\t\"pjbp21jnlyljc\": \"0\",\n" +
                                        "\t\"pjbp21cnkyljc\": \"0\",\n" +
                                        "\t\"p21bplqwxhsll\": \"0\",\n" +
                                        "\t\"p21bplqnxhsll\": \"0\",\n" +
                                        "\t\"p21bplqnxhsyl\": \"0\",\n" +
                                        "\t\"p21bplqnxhswd\": \"0\",\n" +
                                        "\t\"p21bplqwxhcswd\": \"0\",\n" +
                                        "\t\"p21bplqnxhhswd\": \"0\",\n" +
                                        "\t\"jjbdlp22\": \"0\",\n" +
                                        "\t\"jjbsdp22\": \"0\",\n" +
                                        "\t\"jjbjnkyljc\": \"0\",\n" +
                                        "\t\"jjbcnkyljc\": \"0\",\n" +
                                        "\t\"p22djrzwd\": \"0\",\n" +
                                        "\t\"p22djzcwd\": \"0\",\n" +
                                        "\t\"p22bplqnxhsyl\": \"0\",\n" +
                                        "\t\"p22bplqwxhswd\": \"0\",\n" +
                                        "\t\"p22bplqnxhcswd\": \"0\",\n" +
                                        "\t\"p22bplqnxhhswd\": \"0\",\n" +
                                        "\t\"pjbdlp23\": \"0\",\n" +
                                        "\t\"pjbsdp23\": \"0\",\n" +
                                        "\t\"pjbp23jnkyljc\": \"0\",\n" +
                                        "\t\"pjbp23cnkyljc\": \"0\",\n" +
                                        "\t\"p23djrzwd\": \"0\",\n" +
                                        "\t\"p23djzcwd\": \"0\",\n" +
                                        "\t\"p23bplqnxhsyl\": \"0\",\n" +
                                        "\t\"p23bplqnxhswd\": \"0\",\n" +
                                        "\t\"p23bplqnxhcswd\": \"0\",\n" +
                                        "\t\"p23bplqnxhhswd\": \"0\",\n" +
                                        "\t\"bsqzysbckyljc\": \"0\",\n" +
                                        "\t\"jjglbsqslljc\": \"0\",\n" +
                                        "\t\"pjglbsqslljc\": \"0\",\n" +
                                        "\t\"jjglbsqhtxc\": \"0\",\n" +
                                        "\t\"pjglbsqhtxc\": \"0\",\n" +
                                        "\t\"cxbp01mfslljc\": \"0\",\n" +
                                        "\t\"p01bckll\": \"0\",\n" +
                                        "\t\"p01bckyl\": \"0\",\n" +
                                        "\t\"p02bckll\": \"0\",\n" +
                                        "\t\"p02bckyl\": \"0\",\n" +
                                        "\t\"pjbp21mfslljc\": \"0\",\n" +
                                        "\t\"fv1dztzs\": \"0\",\n" +
                                        "\t\"fv3dztzs\": \"0\",\n" +
                                        "\t\"fv4dztzs\": \"0\",\n" +
                                        "\t\"fv5dztzs\": \"0\",\n" +
                                        "\t\"fv6dztzs\": \"0\",\n" +
                                        "\t\"fv7dztzs\": \"0\",\n" +
                                        "\t\"fv8dztzs\": \"0\",\n" +
                                        "\t\"fv9dztzs\": \"0\",\n" +
                                        "\t\"fv10dztzs\": \"0\",\n" +
                                        "\t\"fv11dztzs\": \"0\",\n" +
                                        "\t\"fv12dztzs\": \"0\",\n" +
                                        "\t\"fv13dztzs\": \"0\",\n" +
                                        "\t\"fv14dztzs\": \"0\",\n" +
                                        "\t\"fv15dztzs\": \"0\",\n" +
                                        "\t\"fv16dztzs\": \"0\",\n" +
                                        "\t\"fv17dztzs\": \"0\",\n" +
                                        "\t\"fv18dztzs\": \"0\",\n" +
                                        "\t\"fv20dztzs\": \"0\",\n" +
                                        "\t\"fv21dztzs\": \"0\",\n" +
                                        "\t\"fv22dztzs\": \"0\",\n" +
                                        "\t\"fv23dztzs\": \"0\",\n" +
                                        "\t\"fv24dztzs\": \"0\",\n" +
                                        "\t\"fv25dztzs\": \"0\",\n" +
                                        "\t\"fv26dztzs\": \"0\",\n" +
                                        "\t\"fv27dztzs\": \"0\",\n" +
                                        "\t\"fv28dztzs\": \"0\",\n" +
                                        "\t\"fv29dztzs\": \"0\",\n" +
                                        "\t\"fv30dztzs\": \"0\",\n" +
                                        "\t\"fv31dztzs\": \"0\",\n" +
                                        "\t\"fv32dztzs\": \"0\",\n" +
                                        "\t\"fv33dztzs\": \"0\",\n" +
                                        "\t\"fv34dztzs\": \"0\",\n" +
                                        "\t\"fv41dztzs\": \"0\",\n" +
                                        "\t\"fv50dztzs\": \"0\",\n" +
                                        "\t\"fv51dztzs\": \"0\",\n" +
                                        "\t\"fv54dztzs\": \"0\",\n" +
                                        "\t\"fv55dztzs\": \"0\",\n" +
                                        "\t\"fv2dztzs\": \"0\",\n" +
                                        "\t\"fv61dztzs\": \"0\",\n" +
                                        "\t\"fv62dztzs\": \"0\",\n" +
                                        "\t\"fv70dztzs\": \"0\",\n" +
                                        "\t\"kwcyl1\": \"0\",\n" +
                                        "\t\"kwcyl2\": \"0\",\n" +
                                        "\t\"kwcyl3\": \"0\",\n" +
                                        "\t\"kwcyl4\": \"0\",\n" +
                                        "\t\"kwcyl5\": \"0\",\n" +
                                        "\t\"gzcyl1\": \"0\",\n" +
                                        "\t\"gzcyl2\": \"0\",\n" +
                                        "\t\"v60kd\": \"0\",\n" +
                                        "\t\"v61kd\": \"0\",\n" +
                                        "\t\"v62kd\": \"0\",\n" +
                                        "\t\"gzcprtyw\": \"0\",\n" +
                                        "\t\"xzl\": \"0\",\n" +
                                        "\t\"njmll\": \"0\",\n" +
                                        "\t\"njmygxc\": \"0\",\n" +
                                        "\t\"qscxbjkyl1\": \"0\",\n" +
                                        "\t\"qscxbjkyl2\": \"0\",\n" +
                                        "\t\"qscxbjkyl3\": \"0\",\n" +
                                        "\t\"qscxbjkyl4\": \"0\",\n" +
                                        "\t\"qscxbckyl1\": \"0\",\n" +
                                        "\t\"qscxbckyl2\": \"0\",\n" +
                                        "\t\"qscxbckyl3\": \"0\",\n" +
                                        "\t\"qscxbckyl4\": \"0\",\n" +
                                        "\t\"sjzrk1yl\": \"0\",\n" +
                                        "\t\"sjzrk2yl\": \"0\",\n" +
                                        "\t\"sjzrk3yl\": \"0\",\n" +
                                        "\t\"sjzrk4yl\": \"0\",\n" +
                                        "\t\"sjzrk5yl\": \"0\",\n" +
                                        "\t\"sjzrk6yl\": \"0\",\n" +
                                        "\t\"sjzrk7yl\": \"0\",\n" +
                                        "\t\"sjzrk8yl\": \"0\",\n" +
                                        "\t\"zjkjsq1\": \"0\",\n" +
                                        "\t\"zjkjsq2\": \"0\",\n" +
                                        "\t\"zjkjsq3\": \"0\",\n" +
                                        "\t\"zjkjsq4\": \"0\",\n" +
                                        "\t\"zjkjsq5\": \"0\",\n" +
                                        "\t\"zjkjsq6\": \"0\",\n" +
                                        "\t\"zjkjsq7\": \"0\",\n" +
                                        "\t\"zjkjsq8\": \"0\",\n" +
                                        "\t\"zjjbgyt\": \"0\",\n" +
                                        "\t\"aztjlxdyzxtjlc\": \"0\",\n" +
                                        "\t\"bztjlxdyzxtjlc\": \"0\",\n" +
                                        "\t\"cztjlxdyzxtjlc\": \"0\",\n" +
                                        "\t\"dztjlxdyzxtjlc\": \"0\",\n" +
                                        "\t\"eztjlxdyzxtjlc\": \"0\",\n" +
                                        "\t\"fztjlxdyzxtjlc\": \"0\",\n" +
                                        "\t\"yqnd\": \"0\",\n" +
                                        "\t\"yyhtnd\": \"0\",\n" +
                                        "\t\"jwnd\": \"0\",\n" +
                                        "\t\"lhqnd\": \"0\",\n" +
                                        "\t\"eyhtnd\": \"0\",\n" +
                                        "\t\"rcyyhtnd\": \"0\",\n" +
                                        "\t\"rcyqnd\": \"0\",\n" +
                                        "\t\"rcjwnd\": \"0\",\n" +
                                        "\t\"rclhqnd\": \"0\",\n" +
                                        "\t\"fxdjd1\": \"0\",\n" +
                                        "\t\"fxdjd2\": \"0\",\n" +
                                        "\t\"jjmfyzll\": \"0\",\n" +
                                        "\t\"wpjyzll\": \"0\",\n" +
                                        "\t\"zzcdedwmffzmcs1\": \"0\",\n" +
                                        "\t\"zzcdedwmffzmcs2\": \"0\",\n" +
                                        "\t\"zzcdedwmffzmcs3\": \"0\",\n" +
                                        "\t\"zzcdsdwmffzmcs1\": \"0\",\n" +
                                        "\t\"zzcdsdwmffzmcs2\": \"0\",\n" +
                                        "\t\"njbll\": \"0\",\n" +
                                        "\t\"sblbll\": \"0\",\n" +
                                        "\t\"zqdhbwwmffzmcs1\": \"0\",\n" +
                                        "\t\"zqdhbwwmffzmcs2\": \"0\",\n" +
                                        "\t\"zqdhbwwmffzmcs3\": \"0\",\n" +
                                        "\t\"jbqhbwmffzmcs\": \"0\",\n" +
                                        "\t\"rcyl\": \"0\",\n" +
                                        "\t\"zjbbckyljc\": \"0\",\n" +
                                        "\t\"yjbbckyljc\": \"0\",\n" +
                                        "\t\"zqdlqswdjc\": \"0\",\n" +
                                        "\t\"csxjkyl\": \"0\",\n" +
                                        "\t\"yqmfyl\": \"0\",\n" +
                                        "\t\"lqsjsll\": \"0\",\n" +
                                        "\t\"hptxzll\": \"0\",\n" +
                                        "\t\"clyyqmfyl\": \"0\",\n" +
                                        "\t\"jbqyqmfyl\": \"0\",\n" +
                                        "\t\"hptytlygtl\": \"0\",\n" +
                                        "\t\"hptztlygyl\": \"0\",\n" +
                                        "\t\"zzcdedwmfep2yzyl\": \"0\",\n" +
                                        "\t\"zzchbwyzyl\": \"0\",\n" +
                                        "\t\"cwdxc1\": \"0\",\n" +
                                        "\t\"cwdxc2\": \"0\",\n" +
                                        "\t\"jbjmdxlywd\": \"0\",\n" +
                                        "\t\"zzcdsdwmfep2yzyl\": \"0\",\n" +
                                        "\t\"njmzygyl\": \"0\",\n" +
                                        "\t\"njmyygyl\": \"0\",\n" +
                                        "\t\"njm1ygxc\": \"0\",\n" +
                                        "\t\"njm2ygxc\": \"0\",\n" +
                                        "\t\"njmcxxc\": \"0\",\n" +
                                        "\t\"zkbdjwd\": \"0\",\n" +
                                        "\t\"dpmsjc1\": \"0\",\n" +
                                        "\t\"dpmsjc2\": \"0\",\n" +
                                        "\t\"dpmsjc3\": \"0\",\n" +
                                        "\t\"dpmsjc4\": \"0\",\n" +
                                        "\t\"dpmsjc5\": \"0\",\n" +
                                        "\t\"dpmsjc6\": \"0\",\n" +
                                        "\t\"dpmsjc7\": \"0\",\n" +
                                        "\t\"dpmsjc8\": \"0\",\n" +
                                        "\t\"dpmsjc9\": \"0\",\n" +
                                        "\t\"dpmsjc10\": \"0\",\n" +
                                        "\t\"dpmsjc11\": \"0\",\n" +
                                        "\t\"dpmsjc12\": \"0\",\n" +
                                        "\t\"wsbdl\": \"0\",\n" +
                                        "\t\"wsbsdfk\": \"0\",\n" +
                                        "\t\"clydl1\": \"0\",\n" +
                                        "\t\"zjjbdl\": \"0\",\n" +
                                        "\t\"yjjbdl\": \"0\",\n" +
                                        "\t\"tjbdl\": \"0\",\n" +
                                        "\t\"pzbdl\": \"0\",\n" +
                                        "\t\"fzbdl\": \"0\",\n" +
                                        "\t\"zjbdl\": \"0\",\n" +
                                        "\t\"fjdl\": \"0\",\n" +
                                        "\t\"zssjdl\": \"0\",\n" +
                                        "\t\"yssjdl\": \"0\",\n" +
                                        "\t\"lqshsbdl\": \"0\",\n" +
                                        "\t\"qscxbdl1\": \"0\",\n" +
                                        "\t\"qscxbdl2\": \"0\",\n" +
                                        "\t\"qscxbdl3\": \"0\",\n" +
                                        "\t\"qscxbdl4\": \"0\",\n" +
                                        "\t\"fdzcbkyl\": \"0\",\n" +
                                        "\t\"dmqsbjkyl1\": \"0\",\n" +
                                        "\t\"dmqsbjkyl2\": \"0\",\n" +
                                        "\t\"dmqsbckyl\": \"0\",\n" +
                                        "\t\"tjlljssd\": \"0\",\n" +
                                        "\t\"zjgzz1\": \"0\",\n" +
                                        "\t\"zjgzz2\": \"0\",\n" +
                                        "\t\"zjgzz3\": \"0\",\n" +
                                        "\t\"wxhjsll\": \"0\",\n" +
                                        "\t\"cxssdjsyl\": \"0\",\n" +
                                        "\t\"hsbxkyl\": \"0\",\n" +
                                        "\t\"zjbqakyl\": \"0\",\n" +
                                        "\t\"zjbqbkyl\": \"0\",\n" +
                                        "\t\"yjbqakyl\": \"0\",\n" +
                                        "\t\"yjbqbkyl\": \"0\",\n" +
                                        "\t\"ryhtnd\": \"0\",\n" +
                                        "\t\"dwjxcl1\": \"0\",\n" +
                                        "\t\"dwjxcl2\": \"0\",\n" +
                                        "\t\"dwjxcl3\": \"0\",\n" +
                                        "\t\"dwjxcl4\": \"0\",\n" +
                                        "\t\"dwjxcl5\": \"0\",\n" +
                                        "\t\"yyyweddl\": \"0\",\n" +
                                        "\t\"yyywrddj4\": \"0\",\n" +
                                        "\t\"yyywrddj6\": \"0\",\n" +
                                        "\t\"yyywrddj14\": \"0\",\n" +
                                        "\t\"yyywrddj21\": \"0\",\n" +
                                        "\t\"sbbh\": \"0\",\n" +
                                        "\t\"bpslxtnxhswd\": \"0\",\n" +
                                        "\t\"bpslxtnxhsjsyl\": \"0\",\n" +
                                        "\t\"bpslxtnxhcsyl\": \"0\",\n" +
                                        "\t\"bpslxtnxhjswd\": \"0\",\n" +
                                        "\t\"bpslxtnxhjsyl\": \"0\",\n" +
                                        "\t\"bpslxtlysjswd\": \"0\",\n" +
                                        "\t\"bpslxtlysjsyl\": \"0\",\n" +
                                        "\t\"qdcyl\": \"0\",\n" +
                                        "\t\"qyyl\": \"0\",\n" +
                                        "\t\"p2qyl\": \"0\",\n" +
                                        "\t\"p3qyl\": \"0\",\n" +
                                        "\t\"p4qyl\": \"0\",\n" +
                                        "\t\"p21byqjyz\": \"0\",\n" +
                                        "\t\"jbqmfhbwll\": \"0\",\n" +
                                        "\t\"hbwcyjsq\": \"0\",\n" +
                                        "\t\"zzcyzzrcs1\": \"0\",\n" +
                                        "\t\"zzcyzzrcs2\": \"0\",\n" +
                                        "\t\"zzcyzzrcs3\": \"0\",\n" +
                                        "\t\"zzcyzzrcs4\": \"0\",\n" +
                                        "\t\"zzcyzzrcs5\": \"0\",\n" +
                                        "\t\"ygtjl1\": \"0\",\n" +
                                        "\t\"ygtjl2\": \"0\",\n" +
                                        "\t\"ygtjl3\": \"0\",\n" +
                                        "\t\"ygtjl4\": \"0\",\n" +
                                        "\t\"ygtjl5\": \"0\",\n" +
                                        "\t\"ygtjl6\": \"0\",\n" +
                                        "\t\"dwmfzzrcs1\": \"0\",\n" +
                                        "\t\"dwmfzzrcs2\": \"0\",\n" +
                                        "\t\"dwmfzzrcs3\": \"0\",\n" +
                                        "\t\"dwmfzzrcs4\": \"0\",\n" +
                                        "\t\"dwmfjs11\": \"0\",\n" +
                                        "\t\"dwmfjs12\": \"0\",\n" +
                                        "\t\"dwmfjs13\": \"0\",\n" +
                                        "\t\"dwmfjs14\": \"0\",\n" +
                                        "\t\"dwmfjs15\": \"0\",\n" +
                                        "\t\"dwmfjs16\": \"0\",\n" +
                                        "\t\"dwmfjs17\": \"0\",\n" +
                                        "\t\"dwmfjs18\": \"0\",\n" +
                                        "\t\"dwmfjs19\": \"0\",\n" +
                                        "\t\"dwmfjs110\": \"0\",\n" +
                                        "\t\"dwmfjs111\": \"0\",\n" +
                                        "\t\"dwmfjs112\": \"0\",\n" +
                                        "\t\"dwmfjs113\": \"0\",\n" +
                                        "\t\"dwmfjs114\": \"0\",\n" +
                                        "\t\"dwmfjs115\": \"0\",\n" +
                                        "\t\"dwmfjs116\": \"0\",\n" +
                                        "\t\"dwmfjs21\": \"0\",\n" +
                                        "\t\"dwmfjs22\": \"0\",\n" +
                                        "\t\"dwmfjs23\": \"0\",\n" +
                                        "\t\"dwmfjs24\": \"0\",\n" +
                                        "\t\"dwmfjs25\": \"0\",\n" +
                                        "\t\"dwmfjs26\": \"0\",\n" +
                                        "\t\"dwmfjs27\": \"0\",\n" +
                                        "\t\"dwmfjs28\": \"0\",\n" +
                                        "\t\"dwmfjs29\": \"0\",\n" +
                                        "\t\"dwmfjs210\": \"0\",\n" +
                                        "\t\"dwmfjs211\": \"0\",\n" +
                                        "\t\"dwmfjs212\": \"0\",\n" +
                                        "\t\"dwmfjs213\": \"0\",\n" +
                                        "\t\"dwmfjs214\": \"0\",\n" +
                                        "\t\"dwmfjs215\": \"0\",\n" +
                                        "\t\"dwmfjs216\": \"0\",\n" +
                                        "\t\"dwmfjs31\": \"0\",\n" +
                                        "\t\"dwmfjs32\": \"0\",\n" +
                                        "\t\"dwmfjs33\": \"0\",\n" +
                                        "\t\"dwmfjs34\": \"0\",\n" +
                                        "\t\"dwmfjs35\": \"0\",\n" +
                                        "\t\"dwmfjs36\": \"0\",\n" +
                                        "\t\"dwmfjs37\": \"0\",\n" +
                                        "\t\"dwmfjs38\": \"0\",\n" +
                                        "\t\"dwmfjs39\": \"0\",\n" +
                                        "\t\"dwmfjs310\": \"0\",\n" +
                                        "\t\"dwmfjs311\": \"0\",\n" +
                                        "\t\"dwmfjs312\": \"0\",\n" +
                                        "\t\"dwmfjs313\": \"0\",\n" +
                                        "\t\"dwmfjs314\": \"0\",\n" +
                                        "\t\"dwmfjs315\": \"0\",\n" +
                                        "\t\"dwmfjs316\": \"0\",\n" +
                                        "\t\"dwmfjs41\": \"0\",\n" +
                                        "\t\"dwmfjs42\": \"0\",\n" +
                                        "\t\"dwmfjs43\": \"0\",\n" +
                                        "\t\"dwmfjs44\": \"0\",\n" +
                                        "\t\"dwmfjs45\": \"0\",\n" +
                                        "\t\"dwmfjs46\": \"0\",\n" +
                                        "\t\"dwmfjs47\": \"0\",\n" +
                                        "\t\"dwmfjs48\": \"0\",\n" +
                                        "\t\"dwmfjs49\": \"0\",\n" +
                                        "\t\"dwmfjs410\": \"0\",\n" +
                                        "\t\"dwmfjs411\": \"0\",\n" +
                                        "\t\"dwmfjs412\": \"0\",\n" +
                                        "\t\"dwmfjs413\": \"0\",\n" +
                                        "\t\"dwmfjs414\": \"0\",\n" +
                                        "\t\"dwmfjs415\": \"0\",\n" +
                                        "\t\"dwmfjs416\": \"0\",\n" +
                                        "\t\"tdzjcs1\": \"0\",\n" +
                                        "\t\"tdzjcs2\": \"0\",\n" +
                                        "\t\"tdzjcs3\": \"0\",\n" +
                                        "\t\"tdzjcs4\": \"0\",\n" +
                                        "\t\"tdzjcs5\": \"0\",\n" +
                                        "\t\"tdzjcs6\": \"0\",\n" +
                                        "\t\"tdzjcs7\": \"0\",\n" +
                                        "\t\"tdzjcs8\": \"0\",\n" +
                                        "\t\"hzjtyzzrcs1\": \"0\",\n" +
                                        "\t\"hzjtyzzrcs2\": \"0\",\n" +
                                        "\t\"xzl1\": \"0\",\n" +
                                        "\t\"datetime\": \"2019-04-29 11:21:01\",\n" +
                                        "\t\"id\": \"7b2f0699-52b4-42b2-a75b-6ccb8cb64fd111111\",\n" +
                                        "\t\"outletid\": 2,\n" +
                                        "\t\"shieldno\": \"ICONICS.SimulatorOPCDA.1\"\n" +
                                        "}";
                                Shields8 shield=JSON.parseObject(str1, Shields8.class);
                                List<Shields8> list=new ArrayList<>();
                                list.add(shield);
                                map1.put("es",shield);
 //                               map1.put("mysql",last7days);
                                esList.add(map1);
   //                             break;
                            }

     //                   }
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
                WebSocketTwo8 webSocket = webSocketMap.get(sessionId);
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
