package com.khpc.cn.web.service;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.web.model.bo.UserUpdateBo;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 根据检索字段查询用户信息
     * @param searchName
     * @return
     */
    JsonResult<Map<String,Object>> searchUserByName(String searchName);

    /**
     * 激活用户
     * @param updateBo
     * @return
     */
    JsonResult<Map<String,Object>> activeUser(UserUpdateBo updateBo);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    JsonResult<Map<String,Object>> searchUserById(String id);

    /**
     * 停用或者启用
     * @return
     */
    JsonResult<Map<String,Object>> stopOrStartOperate(String id,String state);

    /**
     *  检索方案
     * @param planName
     * @param khnf
     * @param khyf
     * @return
     */
    JsonResult<Map<String,Object>> searchPlans(String planName,String khnf,String khyf);

    /**
     *  通过或者拒绝方案申请
     * @param planName
     * @param khnf
     * @param khyf
     * @param code
     * @return
     */
    JsonResult<Map<String,Object>> passOrRefuse(String planName,String departName,String khnf,String khyf,String code);
}
