package com.hyt.myproject.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @param
 * @Description:
 * @author: cnc
 * @Date: Created in 11:01 2018/6/28
 * @return
 */
@Configuration
public class AuthorizationConfiguration extends WebMvcConfigurationSupport {


    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    private String[] excludePathPatterns = new String[] {
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
    }
    /**
        * @param
        * @Description: 加载静态资源
        * @autohr: hyt
        * @datatime: 2018/7/19 10:43
        * @return
        */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    /**
     * 页面跳转addViewControllers
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
        super.addViewControllers(registry);
    }
}