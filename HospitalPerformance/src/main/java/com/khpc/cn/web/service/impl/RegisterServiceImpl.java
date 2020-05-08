package com.khpc.cn.web.service.impl;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.core.entity.MsgCode;
import com.khpc.cn.core.mongo.MongoCore;
import com.khpc.cn.core.util.Md5SecurityUtil;
import com.khpc.cn.web.model.bo.UserBo;
import com.khpc.cn.web.model.pojo.User;
import com.khpc.cn.web.service.RegisterService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
