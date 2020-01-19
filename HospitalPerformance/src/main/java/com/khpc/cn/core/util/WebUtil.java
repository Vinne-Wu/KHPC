package com.khpc.cn.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vinne
 * @date 2020/1/19 14:32
 * @description 常用web工具类
 **/
public class WebUtil {

    /**
     *  判断当前请求是否是一个ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
