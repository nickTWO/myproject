package com.hyt.myproject.common.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
    * @param
    * @Description: 只针对ResponseBody的
    * @autohr: hyt
    * @datatime: 2018/6/28 17:23
    * @return
    */
@RestControllerAdvice(basePackages = {"com.hyt.myproject.controller"})
public class RestTokenAdvice implements ResponseBodyAdvice<Object> {
    private static Map<String, Integer> methods = new HashMap(8);

    static {
//        methods.put("loginSubmit", 1);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 获取当前处理请求的controller的方法
        String methodName = methodParameter.getMethod().getName();
        System.out.println("methodName->"+methodName);
        return (methods.get(methodName) == null || methods.get(methodName) != 1);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        System.out.println("body->"+body);
        System.out.println(methods);
        return body;
    }
}
