package com.khpc.cn.web.controller.register;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.core.entity.exception.HandleDataExecption;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Vinne
 * @date 2020/1/14 21:19
 * @description
 **/
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

   private static Logger logger = Logger.getLogger(RegisterController.class);

   @RequestMapping(value = "/index")
   public String registerPage(){
       return "index";
   }
}
