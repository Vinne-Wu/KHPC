package com.khpc.cn.web.service.impl;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.core.entity.MsgCode;
import com.khpc.cn.core.mongo.MongoCore;
import com.khpc.cn.web.model.pojo.User;
import com.khpc.cn.web.service.AdminService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Vinne
 * @date 2020/5/7 22:32
 * @description
 **/
@Service("adminService")
public class AdminServiceImpl implements AdminService {


    @Override
    public JsonResult<Map<String, Object>> getAccountInfo() {
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("role").is("person"),Criteria.where("role").is("register"));
        List<User> userList = MongoCore.selectList(criteria, User.class,"user");
        Map<String,Object>  map = new HashMap<>(4);
        map.put("userList",userList);
        return new JsonResult<>(MsgCode.SCCESS_CODE,"查询成功",map);
    }

    @Override
    public JsonResult<Map<String, Object>> searchUserByName(String searchName) {
        // 模糊查询规则
        Pattern pattern = Pattern.compile("^.*"+searchName+".*$", Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("name").regex(pattern),Criteria.where("email").regex(pattern));
        List<User> userList = MongoCore.selectList(criteria, User.class,"user");
        Map<String,Object>  map = new HashMap<>(4);
        map.put("list",userList);
        return new JsonResult<>(MsgCode.SCCESS_CODE,"查询成功",map);
    }

    @Override
    public JsonResult<Map<String, Object>> activeUser(HttpServletRequest request) {

        return null;
    }
}
