package com.khpc.cn.core.shiro;

import com.khpc.cn.core.mongo.MongoCore;
import com.khpc.cn.core.util.Md5SecurityUtil;
import com.khpc.cn.web.model.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.mongodb.core.query.Criteria;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vinne
 * @date 2020/1/18 23:59
 * @description
 **/
public class ShiroRealm extends AuthorizingRealm {
    /**
     * 设置realm的名称
     */
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    /**
     *  认证的方法，登录时执行
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        // token是用户输入的用户名和密码
        // 第一步从token中取出用户名，这里用用户邮箱作为唯一认证名
        final String loginId = (String) token.getPrincipal();
        String password = null;
        final Object credentials = token.getCredentials();
        if (credentials instanceof char[]) {
            password = new String((char[]) credentials);
        }
        // 第二步：根据用户输入从数据库查询用户信息
        Criteria criteria = new Criteria();
        try {
            criteria.and("email").is(loginId).and("password").is(Md5SecurityUtil.EncoderByMd5(password,"utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User user =(User) MongoCore.selectOne(criteria,User.class,"user");
        if (user == null) {
            throw new UnknownAccountException("账号或密码错误");
        }
        final HashMap<String, Object> principal = new HashMap<>(4);
        principal.put("user", user);
        return new SimpleAuthenticationInfo(principal, user.getPassword(), this.getName());
    }

    /**
     * 授权的方法，每次访问需要权限的接口都会执行
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //从principals获取主身份信息，将getPrimaryPrincipal方法返回值转为真实身份类型（在上边doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中的身份类型）
        Map principal = (Map) principals.getPrimaryPrincipal();
        User user = (User) principal.get("user");
        //返回授权信息(暂不考虑权限分配的问题，只关注角色信息)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //这里添加用户所拥有的角色
        simpleAuthorizationInfo.addRole(user.getRole());
        return simpleAuthorizationInfo;
    }
}
