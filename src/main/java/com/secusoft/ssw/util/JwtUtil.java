package com.secusoft.ssw.util;

import com.secusoft.ssw.common.exception.ServiceException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt产生和验证token
 * @author yjc
 * 2017年4月14日
 */
public class JwtUtil {

    //token有效时间为12个小时
	public static Long liMillis = 43200000l;
	
	public static final String signingKey = "secusoft_ssw";
	
    public static String generateToken(String subject) {
    	if(liMillis!=null && liMillis>0){
    		return generateTokenWithDeadTime(subject, liMillis);
    	}else{
    		long nowMillis = System.currentTimeMillis();
    		Date now = new Date(nowMillis);
    		
    		JwtBuilder builder = Jwts.builder()
    				.setSubject(subject)
    				.setIssuedAt(now)
    				.signWith(SignatureAlgorithm.HS256, signingKey);
    		
    		return builder.compact();
    	}
    }
    
    public static String generateTokenWithDeadTime(String subject,Long liMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date deadTime = new Date(nowMillis+liMillis);

        JwtBuilder builder = Jwts.builder()
                .setExpiration(deadTime).setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        return builder.compact();
    }

    public static String getSubject(String token){
    	String subject = null;
        if(!StringUtil.isBlank(token)){
            try{
                subject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
            }catch(Exception e){
                //token验证失败
            }
        }
        if(StringUtil.isBlank(subject)){
            throw new ServiceException(400,"身份认证失败，请重新登录");
        }else{
            return subject;
        }
    }

}
