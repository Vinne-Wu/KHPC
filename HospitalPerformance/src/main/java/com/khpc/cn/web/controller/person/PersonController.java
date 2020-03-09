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
        logger.info((new Date()).toString()+"普通首页");
        return "person/personIndex";
    }

    /*********************************  辅助方法：页面布局路径 *********************************/

    /**
     *   左侧功能区模块页面
     */
    @RequestMapping("/skipNav")
    public String skipNav(){
        return "/person/nav";
    }

    /*************************************  具体跳转页面  *************************************/
    /**
     *   首页模板页面
     */
    @RequestMapping("/skipIndex")
    public String skipIndex(){
        return "/person/index";
    }

    /**
     *   表单模板页面
     */
    @RequestMapping("/skipForm")
    public String skipForm(){
        return "/person/form";
    }

    /**
     *   表格模板页面
     */
    @RequestMapping("/skipTable")
    public String skipTable(){
        return "/person/table";
    }

    /**
     * 个人信息页面
     */
    @RequestMapping("/skipUserInfo")
    public String skipUserInfo(){
        return "/person/userInfo";
    }

    /**
     *   修改密码页面
     */
    @RequestMapping("/skipPsd")
    public String skipPsd(){
        return "/person/updatePsd";
    }

    /**
     *   考核数据页面
     */
    @RequestMapping("/skipAsd")
    public String skipAsd(){
        return "/person/assessData";
    }

    /**
     *   奖金分配页面
     */
    @RequestMapping("/skipBos")
    public String skipBos(){
        return "/person/bonusShares";
    }

    /**
     *  数据统计页面
     */
    @RequestMapping("/skipDas")
    public String skipDas(){
        return "/person/dataStatistics";
    }

}
