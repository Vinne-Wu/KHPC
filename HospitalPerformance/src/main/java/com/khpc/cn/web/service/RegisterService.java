package com.khpc.cn.web.service;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.web.model.bo.UserBo;

/**
 * @author Vinne
 * @date 2020/1/20 16:00
 * @description  用户注册处理接口
 **/
public interface RegisterService {

    /**
     *  注册用户
     * @param bo
     * @return
     */
    JsonResult<Integer> addRegisterUser(UserBo bo) throws Exception;

    /**
     * 监测邮箱是否重复注册
     * @param bo
     * @return
     */
    JsonResult<Integer> checkRepeatEmail(UserBo bo) throws Exception;
}
