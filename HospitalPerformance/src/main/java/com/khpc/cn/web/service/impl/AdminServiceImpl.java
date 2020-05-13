package com.khpc.cn.web.service.impl;

import com.khpc.cn.core.entity.JsonResult;
import com.khpc.cn.core.entity.MsgCode;
import com.khpc.cn.core.mongo.MongoCore;
import com.khpc.cn.web.model.bo.UserUpdateBo;
import com.khpc.cn.web.model.pojo.User;
import com.khpc.cn.web.service.AdminService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    public JsonResult<Map<String, Object>> activeUser(UserUpdateBo updateBo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(updateBo.getId()));
        Update update = new Update();
        update.set("role",updateBo.getRole());
        update.set("email",updateBo.getEmail());
        update.set("name",updateBo.getName());
        update.set("age",updateBo.getAge());
        update.set("sex",updateBo.getSex());
        update.set("phoneNum",updateBo.getPhoneNum());
        update.set("education",updateBo.getEducation());
        update.set("academicTitle",updateBo.getAcademicTitle());
        update.set("department",updateBo.getDepartment());
        update.set("workPost",updateBo.getWorkPost());
        update.set("workTime",updateBo.getWorkTime());
        // 激活
        update.set("state", "1");
        update.set("updateTime",new Date());
        MongoCore.updateData(query,update,"user");
        return new JsonResult<>(MsgCode.SCCESS_CODE,"激活成功!",null);
    }

    @Override
    public JsonResult<Map<String, Object>> searchUserById(String id) {
        // 回调结果
        Map<String,Object> resultMap = new HashMap<>(4);
        // 数据查询
        Criteria criteria = new Criteria();
        User user = (User) MongoCore.selectOne(criteria.where("_id").is(id),User.class,"user");
        resultMap.put("user",user);
        return new JsonResult<>(MsgCode.SCCESS_CODE,"查询成功!",resultMap);
    }
}
