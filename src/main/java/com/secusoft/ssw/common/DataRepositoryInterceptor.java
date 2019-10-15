package com.secusoft.ssw.common;

import com.secusoft.ssw.constant.RequestAttributesConstant;
import com.secusoft.ssw.common.exception.ServiceException;
import com.secusoft.ssw.util.JwtUtil;
import com.secusoft.ssw.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @author yjc
 * 2017年4月14日
 */
@Component
public class DataRepositoryInterceptor implements HandlerInterceptor {
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		String token = arg0.getHeader("token");
		if(!StringUtil.isBlank(token)){
			String subject = JwtUtil.getSubject(token);
			String[] strs = subject.split(",");
			if(strs!=null && strs.length==3){
				arg0.setAttribute(RequestAttributesConstant.monitorId,strs[0]);
				arg0.setAttribute(RequestAttributesConstant.userId,strs[1]);
				arg0.setAttribute(RequestAttributesConstant.outletId,strs[2]);
				return true;
			}
		}
		throw new ServiceException(400,"身份认证失败，请重新登录");
	}

}
