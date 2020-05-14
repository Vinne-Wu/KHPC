package com.khpc.cn.web.service;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.web.model.bo.UserBo;
import com.khpc.cn.web.model.pojo.AssessmentPlan;
import com.khpc.cn.web.model.pojo.JxIndex;

import java.util.Map;

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

    /**
     * 新增绩效方案
     * @param plan
     * @return
     */
    JsonResult<Map<String,Object>> addJxPlan(AssessmentPlan plan);

    /**
     * 新增绩效指标
     * @param jxIndex
     * @return
     */
    JsonResult<Map<String,Object>> addJxIndex(JxIndex jxIndex);

    /**
     *  查询方案下的所有指标
     * @param planId
     * @return
     */
    JsonResult<Map<String,Object>> searchIndexs(String planId);

    /**
     * 删除指标
     * @param planId
     * @param indexCode
     * @return
     */
    JsonResult<Map<String,Object>> removeIndex(String planId,String indexCode);

    /**
     * 查询考核方案
     * @param departName
     * @param khyf
     * @param khnf
     * @return
     */
    JsonResult<Map<String,Object>> searchKHFA(String departName,String khyf,String khnf);
}
