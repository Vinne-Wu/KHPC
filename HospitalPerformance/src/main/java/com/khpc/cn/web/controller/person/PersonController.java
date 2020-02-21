package com.khpc.cn.web.controller.person;

import com.khpc.cn.web.controller.register.RegisterController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author Vinne
 * @date 2020/2/17 18:51
 * @description  普通用户功能处理类
 **/
@Controller
@RequestMapping("/person")
public class PersonController {

    private static Logger logger = Logger.getLogger(PersonController.class);

    /**
     *  一般用户首页
     * @return
     */
    @RequestMapping(value = "/personIndex")
    public String personIndex(){
        logger.info((new Date()).toString()+"一般用户首页");
        return "person/personIndex";
    }


    /*********************************  辅助方法：页面布局路径 *********************************/

    /**
     *   左边功能列
     */
    @RequestMapping("/skipNav")
    public String skipNav(){
        return "/person/nav";
    }

    /**
     *   选中页1
     */
    @RequestMapping("/skiIndex")
    public String skiIndex(){
        return "/person/index";
    }
}
