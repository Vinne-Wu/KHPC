package com.khpc.cn.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.khpc.cn.core.entity.JsonResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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

   @RequestMapping(value = "/index")
   public String registerPage(){
       return "index";
   }

   /**  测试方法  **/
   @RequestMapping(value = "/testJson",method = RequestMethod.GET)
   @ResponseBody
   public JsonResult<Integer> testJson(){

       logger.info("json方法!");

       return  new JsonResult<>(10,"测试成功！",22);
   }

}
