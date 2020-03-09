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
 * @description   注册员功能处理类
 **/
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

   private static Logger logger = Logger.getLogger(RegisterController.class);

    /**
     *  填报员首页
     * @return
     */
    @RequestMapping(value = "/registerIndex")
    public String registerIndex(){
        logger.info((new Date()).toString()+"注册员首页");
        return "";
    }
}
