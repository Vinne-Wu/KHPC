package com.khpc.cn.web.controller.register;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.web.model.bo.UserBo;
import com.khpc.cn.web.service.RegisterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;


/**
 * @author Vinne
 * @date 2020/1/14 21:19
 * @description
 **/
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

   private static Logger logger = Logger.getLogger(RegisterController.class);

   @Autowired
   private RegisterService registerService;

    /**
     *  首页
     * @return
     */
   @RequestMapping(value = "/registerPage")
   public String registerPage(){
       logger.info((new Date()).toString()+"访问注册页面");
       return "register";
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

    @RequestMapping(value = "/registerUser")
    @ResponseBody
    public JsonResult<Integer> registerUser(@RequestBody UserBo userBo) throws Exception{
        return registerService.addRegisterUser(userBo);
    }

}
