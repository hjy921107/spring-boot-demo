package com.jyhuang.chapter06;

import com.jyhuang.chapter06.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 此处只能实现 WebMvcConfigurer 或者继承 WebMvcConfigurerAdapter(已过时)
 * 继承 WebMvcConfigurationSupport 虽能实现拦截，但会出现无法解析视图的问题，
 * 即：return "index"; 程序无法找到对应的 index.jsp
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }
}
