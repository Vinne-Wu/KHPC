package com.khpc.cn.web.service.impl;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.core.entity.MsgCode;
import com.khpc.cn.core.mongo.MongoCore;
import com.khpc.cn.core.util.Md5SecurityUtil;
import com.khpc.cn.web.model.bo.UserBo;
import com.khpc.cn.web.model.pojo.AssessmentPlan;
import com.khpc.cn.web.model.pojo.JxIndex;
import com.khpc.cn.web.model.pojo.User;
import com.khpc.cn.web.service.RegisterService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vinne
 * @date 2020/1/20 16:10
 * @description  用户注册处理接口实现类
 **/
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

    @Override
    public JsonResult<Integer> addRegisterUser(UserBo bo) throws Exception{
        User user = new User();
        user.setEmail(bo.getEmail());
        user.setName(bo.getName());
        user.setPassword(Md5SecurityUtil.EncoderByMd5(bo.getPassword(),"utf-8"));
        user.setPhoneNum(bo.getPhoneNum());
        user.setRole(bo.getRole());
        user.setState("0");
        // 时间设置
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        user.setWorkTime(dateString);
        user.setEducation("2");
        user.setAcademicTitle("2");
        user.setWorkPost("nurse");
        user.setDepartment("surgery");
        user.setSex("男");
        user.setAge(25);
        user.setUpdateTime(new Date());
        try {
            MongoCore.addOne(user,"user");
            return new JsonResult<>(MsgCode.SCCESS_CODE,"添加成功！",1);
        }catch (Exception e){
            return new JsonResult<>(MsgCode.ERRRO_CODE,"添加失败！",0);
        }
    }

    @Override
    public JsonResult<Integer> checkRepeatEmail(UserBo bo) throws Exception {
        Criteria criteria = new Criteria();
        criteria.and("email").is(bo.getEmail());
        User result = (User) MongoCore.selectOne(criteria,User.class,"user");
        if(result == null){
            return  new JsonResult<>(MsgCode.SCCESS_CODE,"查询成功！",0);
        }else {
            return  new JsonResult<>(MsgCode.SCCESS_CODE,"邮箱邮件注册！",1);
        }
    }

    @Override
    public JsonResult<Map<String, Object>> addJxPlan(AssessmentPlan plan) {
        // 回调结果
        Map<String,Object> resultMap = new HashMap<>(4);
        // 查询是否已经存在
        Criteria criteria = new Criteria();
        criteria.and("planName").is(plan.getPlanName())
                .and("khnf").is(plan.getKhnf())
                .and("khyf").is(plan.getKhyf())
                .and("departName").is(plan.getDepartName());
        Map<String,Object> plan2 = (Map<String, Object>) MongoCore.selectOne(criteria,Map.class,"assessmentplan");
        // 判断方案是否已经存在
        if(plan2 != null){
            resultMap.put("planId",(plan2.get("_id")).toString());
            return new JsonResult<>(MsgCode.SCCESS_CODE,"方案已经存在！",resultMap);
        }
        // 新增方案
        MongoCore.addOne(plan,"assessmentplan");
        // 查询方案ID
        Map<String,Object> plan1 = (Map<String, Object>) MongoCore.selectOne(criteria,Map.class,"assessmentplan");
        resultMap.put("plan",plan1);
        String planId =(plan1.get("_id")).toString();
        resultMap.put("planId",planId);
        return new JsonResult<>(MsgCode.SCCESS_CODE,"新增成功！",resultMap);
    }

    @Override
    public JsonResult<Map<String, Object>> addJxIndex(JxIndex jxIndex) {
        // 新增指标
        MongoCore.addOne(jxIndex,"index");
        return new JsonResult<>(MsgCode.SCCESS_CODE,"新增指标成功",null);
    }

    @Override
    public JsonResult<Map<String, Object>> searchIndexs(String planId) {
        // 查询数据
        Criteria criteria = new Criteria();
        criteria.and("planId").is(planId);
        List<JxIndex> jxIndexList = MongoCore.selectList(criteria,JxIndex.class,"index");
        // 回调结果
        Map<String,Object> resultMap = new HashMap<>(4);
        resultMap.put("list",jxIndexList);
        return new JsonResult<>(MsgCode.SCCESS_CODE,"查询成功!",resultMap);
    }

    @Override
    public JsonResult<Map<String, Object>> removeIndex(String planId, String indexCode) {
        Criteria criteria = new Criteria();
        criteria.and("planId").is(planId).and("indexCode").is(indexCode);
        MongoCore.delete(criteria,"index");
        return new JsonResult<>(MsgCode.SCCESS_CODE,"删除成功！",null);
    }
}
