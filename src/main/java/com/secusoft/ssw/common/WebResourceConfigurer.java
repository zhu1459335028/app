package com.secusoft.ssw.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName WebResourceConfigurer
 * @Author jcyao
 * @Description 配置静态资源
 * @Date 2018/9/20 10:00
 * @Version 1.0
 */
@Configuration
public class WebResourceConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ssw/**").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ParamIntereptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}