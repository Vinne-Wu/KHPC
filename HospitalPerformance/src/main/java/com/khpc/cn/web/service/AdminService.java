package com.khpc.cn.web.service;

import com.khpc.cn.core.entity.JsonResult;

import java.util.Map;

/**
 * @author Vinne
 * @date 2020/5/7 22:32
 * @description
 **/
public interface AdminService {

    /**
     *  获取所有用户信息
     * @return
     */
    JsonResult<Map<String,Object>> getAccountInfo();


}
