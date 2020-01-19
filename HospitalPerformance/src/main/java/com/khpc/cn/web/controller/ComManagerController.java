package com.khpc.cn.web.controller;

import com.khpc.cn.web.model.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;


import org.apache.shiro.subject.Subject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Vinne
 * @date 2020/1/19 14:41
 * @description  公共管理controller，主要用于登录，登出等统一管理功能
 **/
@Controller
@RequestMapping("/api/sys")
public class ComManagerController {
    /**
     * 登陆
     *
     * @param loginId  登录账号
     * @param password 密码
     */

    @RequestMapping(value = "/login")
    public Object login(String loginId, String password, HttpServletRequest req) {
        final String host = req.getRemoteHost();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(loginId, password, host);
        try {
            // 从SecurityUtils里边创建一个 subject
            Subject subject = SecurityUtils.getSubject();
            // 执行认证登陆
            subject.login(token);
            //set session attribute
            final Map principal = (Map) subject.getPrincipal();
            User user = (User) principal.get("user");
            // loginService.buildSessionAttr方法生成了包含 List<String> permissions，key为"permissions";

         //   final Map sessionAttrs = loginService.buildSessionAttr(user);

        //    principal.putAll(sessionAttrs);

        } catch (UnknownAccountException e) {
            final String message = e.getMessage();

            System.out.println("异常！");

        } catch (AuthenticationException e) {

            System.out.println("异常！");
        }
        return null;
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return null;
    }


    /**
     * 当前session属性
     *
     * @param attr
     * @return
     */
//    @RequestMapping(value = "/current")
//    public RespObject getCurrentAttr(String attr) {
//        Map Object = service.readSessionAttr();
//        if (Strings.isEmpty(attr)) {
//            return RespObject.success(sessionAttrs);
//        } else {
//            return RespObject.success(sessionAttrs.get(attr));
//        }
//    }
}
