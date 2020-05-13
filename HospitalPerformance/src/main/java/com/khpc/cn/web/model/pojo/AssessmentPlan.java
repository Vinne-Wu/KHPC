package com.khpc.cn.web.model.pojo;

/**
 * @author Vinne
 * @date 2020/1/19 22:08
 * @description 绩效考核方案模型
 **/
public class AssessmentPlan {
    /**
     *  方案名称
     */
    private String planName;

    /**
     *  考核年份
     */
    private String khnf;

    /**
     *  考核月份
     */
    private String khyf;

    /**
     *  部门（科室）名称
     */
    private String departName;

    /**
     *  方案审核状态  1:审核通过 -1：审核未通过
     */
    private String state;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getKhnf() {
        return khnf;
    }

    public void setKhnf(String khnf) {
        this.khnf = khnf;
    }

    public String getKhyf() {
        return khyf;
    }

    public void setKhyf(String khyf) {
        this.khyf = khyf;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
