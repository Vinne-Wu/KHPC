package com.khpc.cn.web.controller.admin;

import com.khpc.cn.web.controller.person.PersonController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author Vinne
 * @date 2020/1/16 23:32
 * @description  管理员功能处理类
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static Logger logger = Logger.getLogger(AdminController.class);

    /**
     * 管理员首页
     * @return
     */
    @RequestMapping(value = "/adminIndex")
    public String adminIndex(){
        logger.info((new Date()).toString()+"管理员首页");
        return "admin/adminIndex";
    }
}
