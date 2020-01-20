package com.khpc.cn.web.service.impl;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.core.entity.MessageCode;
import com.khpc.cn.core.mongo.MongoCore;
import com.khpc.cn.core.util.Md5SecurityUtil;
import com.khpc.cn.web.model.bo.UserBo;
import com.khpc.cn.web.model.pojo.User;
import com.khpc.cn.web.service.RegisterService;
import org.springframework.stereotype.Service;

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
        user.setRoleId(bo.getRoleId());
        try {
            MongoCore.addOne(user,"user");
            return new JsonResult<>(MessageCode.SCCESS_CODE,"添加成功！",1);
        }catch (Exception e){
            return new JsonResult<>(MessageCode.ERRRO_CODE,"添加失败！",0);
        }
    }
}
