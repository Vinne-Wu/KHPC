package com.khpc.cn.web.controller.common;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.core.entity.MsgCode;
import com.khpc.cn.core.exception.ActivationException;
import com.khpc.cn.web.controller.register.RegisterController;
import com.khpc.cn.web.model.bo.UserBo;
import com.khpc.cn.web.model.pojo.User;
import com.khpc.cn.web.service.RegisterService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;


import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vinne
 * @date 2020/1/19 14:41
 * @description  公共管理controller，主要用于登录登出，用户注册等统一管理功能
 **/
@Controller
@RequestMapping("/common")
public class ComManagerController {

    private static Logger logger = Logger.getLogger(ComManagerController.class);

    /**
     *  账户激活码
     */
    static final String ACTIVECODE = "1";

    @Autowired
    private RegisterService registerService;

    /**
     * 系统登录首页
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    /**
     *  系统注册页面
     * @return
     */
    @RequestMapping(value = "/registerPage")
    public String registerPage(){
        logger.info((new Date()).toString()+"访问注册页面");
        return "register";
    }

    /**
     *  用户注册数据处理
     * @param userBo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/registerUser")
    @ResponseBody
    public JsonResult<Integer> registerUser(@RequestBody UserBo userBo) throws Exception{
        return registerService.addRegisterUser(userBo);
    }

    /**
     *  监测邮箱是否已经注册
     * @param userBo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkRepeatEmail")
    @ResponseBody
    public JsonResult<Integer> checkRepeatEmail(@RequestBody UserBo userBo) throws Exception{
        return registerService.checkRepeatEmail(userBo);
    }

    /**
     *  返回登录页面
     * @return
     */
    @RequestMapping(value = "/returnLogin")
    public String returnLogin(){
        logger.info((new Date()).toString()+"由注册页面返回至登录页面");
        return "login";
    }

    /**
     * 校验登陆信息
     * @param userBo
     * @param req
     * @return
     */
    @RequestMapping(value = "/checkLogin")
    @ResponseBody
    public JsonResult<Map<String,Object>> checkLogin(@RequestBody UserBo userBo, HttpServletRequest req) {
        final String host = req.getRemoteHost();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userBo.getEmail(), userBo.getPassword(), host);
        token.setRememberMe(userBo.getRememberMe());
        // 回调结果集
        Map<String,Object> resultMap = new HashMap(4);
        try {
            // 登录用户主体
            Subject subject = SecurityUtils.getSubject();
            // 执行shiro认证登陆
            subject.login(token);
            // 获取当前用户信息
            final Map principal = (Map) subject.getPrincipal();
            User user = (User) principal.get("user");
            // 查询当前用户是否激活
            if(!ACTIVECODE.equals(user.getState())){
                throw  new ActivationException("待激活或者停用！");
            }
            resultMap.put("user",user);
        } catch (UnknownAccountException e) {
            return new JsonResult<>(MsgCode.ERRRO_CODE,"账户或者密码错误！",resultMap);
        }catch (ActivationException e){
            return new JsonResult<>(MsgCode.ERRRO_CODE,e.getMessage(),resultMap);
        }
        catch (AuthenticationException e) {
           return new JsonResult<>(MsgCode.ERRRO_CODE,"其他原因认证错误！",resultMap);
        }
        return new JsonResult<>(MsgCode.SCCESS_CODE,"认证成功！",resultMap);
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Integer> logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return new JsonResult<>(MsgCode.SCCESS_CODE,"退出登录!",1);
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<User> getUserInfo(){
        // 登录用户主体
        Subject subject = SecurityUtils.getSubject();
        // 获取当前用户信息
        Map principal = (Map) subject.getPrincipal();
        User user = (User) principal.get("user");
        return new JsonResult<>(MsgCode.SCCESS_CODE,"获取对象信息成功！",user);
    }

    /**
     * 权限认证错误跳转页
     * @return
     */
    @RequestMapping(value = "/unauthorRefuse")
    public String unauthorRefuse(){
        logger.info((new Date()).toString()+"访问注册页面");
        return "/error/403";
    }

    /**
     * 公共消息页
     * @return
     */
    @RequestMapping(value = "/commonPage")
    public String commonPage(){
        return "message/msgInfo";
    }
}
