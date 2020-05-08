package com.khpc.cn.web.model.pojo;

import java.io.Serializable;
import java.util.Date;

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

    /**
     * 工作时间
     */
    private String workTime;

    /**
     * 学历  1.大专 2.本科 3.硕士
     */
    private String education;

    /**
     *  职称
     */
    private String academicTitle;

    /**
     *  员工编码
     */
    private String identityCode;

    /**
     *  岗位
     */
    private String workPost;

    /**
     * 科室
     */
    private String department;

    /**
     *  账户状态   1：正常启用 0：待激活 -1：停用
     */
    private String state;

    /**
     *  账户更新时间
     */
    private Date updateTime;

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
