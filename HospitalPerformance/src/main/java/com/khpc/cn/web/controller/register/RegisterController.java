package com.khpc.cn.web.controller.register;

import com.khpc.cn.core.entity.JsonResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
   private MongoTemplate mongoTemplate;

    /**
     *  首页
     * @return
     */
   @RequestMapping(value = "/index")
   public String registerPage(){
       return "index";
   }


}
