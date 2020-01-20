package com.khpc.cn.web.model.bo;

/**
 * @author Vinne
 * @date 2020/1/20 15:31
 * @description  注册用户传参对象
 **/
public class UserBo {

    private String name;

    private String password;

    private String phoneNum;

    private String email;

    /**
     *  参数意义同pojo对象
     */
    private String roleId;

    public UserBo() {
    }

    public UserBo(String name, String password, String phoneNum, String email, String roleId) {
        this.name = name;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.roleId = roleId;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
