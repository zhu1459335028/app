package com.secusoft.ssw.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
 * @author yjc
 * 2017年4月14日
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
	
	@Autowired
	private DataRepositoryInterceptor dataRepositoryInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dataRepositoryInterceptor)
        .addPathPatterns("/homePage/**","/report/**","/device/**","/peopleManage/**","/dataCount/**","/threshold/**","/settlement/**","/perimeterPic/**","/gas/**","/vehicle/**","/waterlevel/**","/watermonitor/**","/shield/**")
        .excludePathPatterns("/login","/vmcServer/**");
        super.addInterceptors(registry);
    }

}
