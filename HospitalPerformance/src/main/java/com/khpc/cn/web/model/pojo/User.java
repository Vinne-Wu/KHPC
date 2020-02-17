package com.khpc.cn.web.model.pojo;

import java.io.Serializable;

/**
 * @author Vinne
 * @date 2020/1/19 0:41
 * @description
 **/
public class User implements Serializable {

    private static final long serialVersionUID = -2423356247995006863L;

    /**
     *  用户名称（昵称）
     */
    private String name;

    /**
     *  密码
     */
    private String password;

    /**
     *  电话号码
     */
    private String phoneNum;

    /**
     *  用户邮箱
     */
    private String email;

    /**
     *  用户角色标志位 1.person 普通用户  2.register 数据填报员  3.admin 系统管理员
     */
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
