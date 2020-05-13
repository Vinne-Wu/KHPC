package com.khpc.cn.web.model.pojo;

import java.math.BigDecimal;

/**
 * @author Vinne
 * @date 2020/1/19 22:30
 * @description 绩效指标模型
 **/
public class JxIndex {

    /**
     *  方案Id
     */
    private String planId;

    /**
     *  指标名称
     */
    private String indexName;

    /**
     *  指标编码
     */
    private String indexCode;

    /**
     *  指标类别  D:医生 N:护士
     */
    private String indexCategory;

    /**
     *  指标类型 1：硬性指标  -1：非硬性指标
     */
    private String indexType;

    /**
     *  标化系数
     */
    private Integer ratio;

    /**
     *  工作标量
     */
    private Integer scalar;

    /**
     *  动态绩效因子（暂时不纳入计算）
     */
    private String factorNum;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getIndexCategory() {
        return indexCategory;
    }

    public void setIndexCategory(String indexCategory) {
        this.indexCategory = indexCategory;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public Integer getScalar() {
        return scalar;
    }

    public void setScalar(Integer scalar) {
        this.scalar = scalar;
    }

    public String getFactorNum() {
        return factorNum;
    }

    public void setFactorNum(String factorNum) {
        this.factorNum = factorNum;
    }
}
