package com.khpc.cn.web.controller;

import com.khpc.cn.core.entity.JsonResult;
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

   @RequestMapping(value = "/index")
   public String registerPage(){
       return "index";
   }

   /**  测试方法  **/
   @RequestMapping(value = "/testJson",method = RequestMethod.GET)
   @ResponseBody
   public JsonResult<Integer> testJson(){
       return  new JsonResult<>(10,"测试成功！",22);
   }

}
