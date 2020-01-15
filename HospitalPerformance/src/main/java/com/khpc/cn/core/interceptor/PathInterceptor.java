package com.khpc.cn.core.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PathInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("===========进入拦截器前===========");

        System.out.println("当前请求地址为："+request.getServletPath());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("===========进入拦截器===========");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object o, Exception e) throws Exception {
        System.out.println("===========进入拦截器后===========");
    }
}
