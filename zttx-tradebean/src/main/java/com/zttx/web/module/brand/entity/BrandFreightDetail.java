/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

import java.beans.Transient;

/**
 * 运费明细表 实体对象
 * <p>File：BrandFreightDetail.java</p>
 * <p>Title: BrandFreightDetail</p>
 * <p>Description:BrandFreightDetail</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class BrandFreightDetail extends GenericEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 运费模板ID
     */
    private java.lang.String templateId;
    /**
     * 运费设置ID
     */
    private java.lang.String settingsId;
    /**
     * 运送方式
     */
    private java.lang.Integer carryType;
    /**
     * 首重
     */
    private java.math.BigDecimal firstWeight;
    /**
     * 首重价格
     */
    private java.math.BigDecimal firstPrice;
    /**
     * 续重
     */
    private java.math.BigDecimal extendWeight;
    /**
     * 续重价格
     */
    private java.math.BigDecimal extendPrice;
    /**
     * 是否默认（0：否，1：是）
     */
    private java.lang.Short isDefault;
    /**
     * 创建时间
     */
    private java.lang.Long createTime;
    /**
     * 修改时间
     */
    private java.lang.Long updateTime;


    /**
     * ---------- 非数据库字段 ------------ *
     */
    private String areaNos;      // 多个区域编号
    private String arriveAddress;      // 多个区域编号


    public java.lang.String getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(java.lang.String templateId) {
        this.templateId = templateId;
    }

    public java.lang.String getSettingsId() {
        return this.settingsId;
    }

    public void setSettingsId(java.lang.String settingsId) {
        this.settingsId = settingsId;
    }

    public java.lang.Integer getCarryType() {
        return this.carryType;
    }

    public void setCarryType(java.lang.Integer carryType) {
        this.carryType = carryType;
    }

    public java.math.BigDecimal getFirstWeight() {
        return this.firstWeight;
    }

    public void setFirstWeight(java.math.BigDecimal firstWeight) {
        this.firstWeight = firstWeight;
    }

    public java.math.BigDecimal getFirstPrice() {
        return this.firstPrice;
    }

    public void setFirstPrice(java.math.BigDecimal firstPrice) {
        this.firstPrice = firstPrice;
    }

    public java.math.BigDecimal getExtendWeight() {
        return this.extendWeight;
    }

    public void setExtendWeight(java.math.BigDecimal extendWeight) {
        this.extendWeight = extendWeight;
    }

    public java.math.BigDecimal getExtendPrice() {
        return this.extendPrice;
    }

    public void setExtendPrice(java.math.BigDecimal extendPrice) {
        this.extendPrice = extendPrice;
    }

    public java.lang.Short getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(java.lang.Short isDefault) {
        this.isDefault = isDefault;
    }

    public java.lang.Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.lang.Long createTime) {
        this.createTime = createTime;
    }

    public java.lang.Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.lang.Long updateTime) {
        this.updateTime = updateTime;
    }

    @Transient
    public String getAreaNos() {
        return areaNos;
    }

    public void setAreaNos(String areaNos) {
        this.areaNos = areaNos;
    }

    @Transient
    public String getArriveAddress() {
        return arriveAddress;
    }

    public void setArriveAddress(String arriveAddress) {
        this.arriveAddress = arriveAddress;
    }
}

