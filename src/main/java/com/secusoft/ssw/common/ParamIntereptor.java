package com.secusoft.ssw.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class ParamIntereptor implements HandlerInterceptor {
  //  Logger log = Logger.getLogger(ParamIntereptor.class);

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        long b = System.currentTimeMillis();
//        Iterator<?> iter = request.getParameterMap().keySet().iterator();
//        String key,value;
//        String[] values;
        StringBuffer sb = new StringBuffer();
//        try{
//            while (iter.hasNext()) {
//                key = (String) iter.next();
//                values = (String[])request.getParameterMap().get(key);
//                for(int i = 0; i < values.length; i ++){
//                    value = values[i];
//                    if(value.toLowerCase().contains("select")){
//                        log.info("非法参数，返回首页");
//                    }
//                    value = new String(value.getBytes("GBK"), "UTF-8");
//                    sb.append(key).append("=").append(value).append(",");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        long begin_nao_time = (Long) request.getAttribute("begin_nao_time");
        String real_ip = (String) request.getAttribute("p_real_ip");
        long interval = System.nanoTime() - begin_nao_time;

       String result = null;
       long e = System.currentTimeMillis();
        System.out.println("【请求结束】地址:[" + request.getRequestURL()+"]SID=["+request.getSession().getId()+"]result=["+result+"]请求时间="+interval+"[请求IP]:"+real_ip+"[请求参数]=[" + sb.toString()+"]");

    }

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        long b = System.currentTimeMillis();
        Map<String,String[]> pramMap = request.getParameterMap();
        StringBuffer sb = new StringBuffer();
        try{
            for(Map.Entry<String, String[]> entry:pramMap.entrySet()){
                sb.append(entry.getKey()+"="+request.getParameter(entry.getKey()) + ",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = null;
        long e = System.currentTimeMillis();
        if(StringUtils.isNotBlank(sb.toString())){
            result=sb.substring(0,sb.length()-1);
        }

        long begin_nao_time = System.nanoTime();
        String realIp = getRealIpAddr(request);
        request.setAttribute("p_real_ip", realIp);
        request.setAttribute("begin_nao_time", begin_nao_time);
        System.out.println("【请求开始】地址:[" + request.getRequestURL()+"]SID=["+request.getSession().getId()+"]time="+(e-b)+"请求参数=["+result+"]");
        return true;
    }

    private String getRealIpAddr(HttpServletRequest request) {
                 String ip = request.getHeader("x-forwarded-for");
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                         ip = request.getHeader("Proxy-Client-IP");
                     }
                 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                          ip = request.getHeader("WL-Proxy-Client-IP");
                     }
                  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                          ip = request.getHeader("HTTP_CLIENT_IP");
                       }
                   if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                        }
                   if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                           ip = request.getRemoteAddr();
                       }
                  return ip;

    }
}