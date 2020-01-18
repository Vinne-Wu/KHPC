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
import org.springframework.util.DigestUtils;

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
public class MyRealm extends AuthorizingRealm {
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
        // 第一步从token中取出用户名
        final String loginId = (String) token.getPrincipal();
        String password = null;
        final Object credentials = token.getCredentials();
        if (credentials instanceof char[]) {
            password = new String((char[]) credentials);
        }

        // 第二步：根据用户输入从数据库查询用户信息
        Criteria criteria = new Criteria();
        try {
            criteria.and("email").is(loginId).and("password").is(Md5SecurityUtil.EncoderByMd5(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User user =(User) MongoCore.selectOne(criteria,User.class,"Users");
        if (user == null) {
            throw new UnknownAccountException("账号或密码错误");
        }
        // 从数据库查询到密码
//        //配合shiro配置的mc5加密(应该可以配置为不加密)
////        if (password != null) {
////            password = DigestUtils.md5Hex(password);
////        }
        //加密的盐
        //String salt = user.getSalt();

        final HashMap<String, Object> principal = new HashMap<>();
        principal.put("user", user);
        return new SimpleAuthenticationInfo(principal, password, this.getName());
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
        List<String> permissions = (List<String>) principal.get("permissions");
        //查到权限数据，返回授权信息(要包括上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //这里添加用户有的权限列表
        simpleAuthorizationInfo.addStringPermissions(permissions);
        //这里添加用户所拥有的角色
        simpleAuthorizationInfo.addRole(user.getRoleId());
        return simpleAuthorizationInfo;
    }
}
