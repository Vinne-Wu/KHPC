package com.khpc.cn.web.model.bo;

import java.io.Serializable;

/**
 * @author Vinne
 * @date 2020/1/20 15:31
 * @description  注册用户传参对象
 **/
public class UserBo implements Serializable {


    private static final long serialVersionUID = 889715387576375277L;

    private String name;

    private String password;

    private String phoneNum;

    private String email;

    private Boolean rememberMe;

    /**
     *  参数意义同pojo对象
     */
    private String role;

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

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
