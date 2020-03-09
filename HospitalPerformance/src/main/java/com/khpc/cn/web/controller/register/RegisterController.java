package com.khpc.cn.web.controller.register;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return "register/registerIndex";
    }

    /*********************************  辅助方法：页面布局路径 *********************************/

    /**
     *   左侧功能区模块页面
     */
    @RequestMapping("/skipNav")
    public String skipNav(){
        return "/register/nav";
    }

    /*************************************  具体跳转页面  *************************************/
    /**
     *   首页模板页面
     */
    @RequestMapping("/skipIndex")
    public String skipIndex(){
        return "/register/index";
    }

    /**
     *   表单模板页面
     */
    @RequestMapping("/skipForm")
    public String skipForm(){
        return "/register/form";
    }

    /**
     *   表格模板页面
     */
    @RequestMapping("/skipTable")
    public String skipTable(){
        return "/register/table";
    }

    /**
     * 个人信息页面
     */
    @RequestMapping("/skipUserInfo")
    public String skipUserInfo(){
        return "/register/userInfo";
    }

    /**
     *   修改密码页面
     */
    @RequestMapping("/skipPsd")
    public String skipPsd(){
        return "/register/modifyPsd";
    }

    /**
     *   指标方案页面
     */
    @RequestMapping("/skipIndexP")
    public String skipIndexP(){
        return "/register/indexProject";
    }


    /**
     *   每日考核
     */
    @RequestMapping("/skipDailyAsm")
    public String skipDailyAsm(){
        return "/register/dailyAsm";
    }

    /**
     *  数据汇总
     */
    @RequestMapping("/skipSum")
    public String skipSum(){
        return "/register/sumData";
    }

    /**
     *  数据统计
     */
    @RequestMapping("/skipDas")
    public String skipDas(){
        return "/register/dataStatistics";
    }
}
