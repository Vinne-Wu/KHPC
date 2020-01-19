package com.khpc.cn.core.filter;

import com.alibaba.fastjson.JSONArray;
import com.khpc.cn.core.util.WebUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ShiroLoginFilter extends FormAuthenticationFilter {

    /**
     * 如果isAccessAllowed返回false 则执行onAccessDenied
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean isAllowed = false;
        //前端(某些框架)测试接口(OPTIONS)直接放行
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                isAllowed = true;
            }
        }
        isAllowed = super.isAccessAllowed(request, response, mappedValue);
        if (isAllowed) {
            //登录状态，作一些日志记录
        }
        return isAllowed;
    }

    /**
     * 未登录时的处理
     *
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        final HttpServletRequest request2 = (HttpServletRequest) request;
        final HttpServletResponse response2 = (HttpServletResponse) response;

        //ajax访问接口返回数据结构
        if (WebUtil.isAjax(request2)) {// ajax接口
            //这里是个坑，如果不设置的接受的访问源，那么前端都会报跨域错误，因为这里还没到corsConfig里面
            response2.setHeader("Access-Control-Allow-Origin", request2.getHeader("Origin"));
            response2.setHeader("Access-Control-Allow-Credentials", "true");
            response2.setCharacterEncoding("UTF-8");
            response2.setContentType("application/json");

            Map responseData = new HashMap();
            responseData.put("state", "unauthorized");
            responseData.put("code", 401);
            responseData.put("msg", "用户未登录");

            JSONArray jArray = new JSONArray();
            jArray.add(responseData);
            String result = jArray.toString();
            PrintWriter out;
            try {
                out = response2.getWriter();
                out.print(result);
                out.flush();
            } catch (IOException e) {
                System.out.println("返回异常失败!");
            }
        } else {
            //其他情况
            //shiro处理
            super.onAccessDenied(request, response);

            //其他处理方式

            // 页面，直接跳转登录页面
            //redirect("login.html", request2, response2);

            //web.xml处理
            //response2.setStatus(401);// 客户试图未经授权访问受密码保护的页面。
        }
        return false;
    }

}
