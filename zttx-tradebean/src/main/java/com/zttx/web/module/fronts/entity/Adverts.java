/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 广告 实体对象
 * <p>File：Adverts.java</p>
 * <p>Title: Adverts</p>
 * <p>Description:Adverts</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class Adverts extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**广告位编号*/
    @NotBlank(message = "广告位编号不能为空")
    private java.lang.String  advertId;
    
    // 广告位名称
    private java.lang.String  advertName;
    
    /**广告权重*/
    @NotNull(message = "广告权重不能为空")
    private java.lang.Integer imgWeight;
    
    /**广告标题*/
    @NotBlank(message = "广告标题不能为空")
    private java.lang.String  adTitle;
    
    /**链接地址*/
    @NotBlank(message = "链接地址不能为空")
    private java.lang.String  urlAddress;
    
    /**广告图片域名*/
    private java.lang.String  domainName;
    
    /**广告图片背景颜色*/
    private java.lang.String  backgroundColor;
    
    /**广告图片*/
    private java.lang.String  adLogo;
    
    /**图片/附档的alt描述*/
    private java.lang.String  altMark;
    
    /**广告开始时间*/
    @NotNull(message = "广告开始时间不能为空")
    private java.lang.Long    beginTime;
    
    /**广告结束时间*/
    @NotNull(message = "广告结束时间不能为空")
    private java.lang.Long    endTime;
    
    /**排序字段*/
    private java.lang.Integer orderId;
    
    /**创建时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**打开方式*/
    @NotBlank(message = "打开方式不能为空")
    private java.lang.String  target;
    
    // 广告使用状态
    private java.lang.Short   usedState;
    
    // 当前时间
    private java.lang.Long    currentTime;
    
    public java.lang.String getAdvertId()
    {
        return this.advertId;
    }
    
    public void setAdvertId(java.lang.String advertId)
    {
        this.advertId = advertId;
    }
    
    public java.lang.Integer getImgWeight()
    {
        return this.imgWeight;
    }
    
    public void setImgWeight(java.lang.Integer imgWeight)
    {
        this.imgWeight = imgWeight;
    }
    
    public java.lang.String getAdTitle()
    {
        return this.adTitle;
    }
    
    public void setAdTitle(java.lang.String adTitle)
    {
        this.adTitle = adTitle;
    }
    
    public java.lang.String getUrlAddress()
    {
        return this.urlAddress;
    }
    
    public void setUrlAddress(java.lang.String urlAddress)
    {
        this.urlAddress = urlAddress;
    }
    
    public java.lang.String getDomainName()
    {
        return this.domainName;
    }
    
    public void setDomainName(java.lang.String domainName)
    {
        this.domainName = domainName;
    }
    
    public String getBackgroundColor()
    {
        return backgroundColor;
    }
    
    public void setBackgroundColor(String backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }
    
    public java.lang.String getAdLogo()
    {
        return this.adLogo;
    }
    
    public void setAdLogo(java.lang.String adLogo)
    {
        this.adLogo = adLogo;
    }
    
    public java.lang.String getAltMark()
    {
        return this.altMark;
    }
    
    public void setAltMark(java.lang.String altMark)
    {
        this.altMark = altMark;
    }
    
    public java.lang.Long getBeginTime()
    {
        return this.beginTime;
    }
    
    public void setBeginTime(java.lang.Long beginTime)
    {
        this.beginTime = beginTime;
    }
    
    public java.lang.Long getEndTime()
    {
        return this.endTime;
    }
    
    public void setEndTime(java.lang.Long endTime)
    {
        this.endTime = endTime;
    }
    
    public java.lang.Integer getOrderId()
    {
        return this.orderId;
    }
    
    public void setOrderId(java.lang.Integer orderId)
    {
        this.orderId = orderId;
    }
    
    public java.lang.Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(java.lang.Long createTime)
    {
        this.createTime = createTime;
    }
    
    public java.lang.Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(java.lang.Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public java.lang.String getTarget()
    {
        return this.target;
    }
    
    public void setTarget(java.lang.String target)
    {
        this.target = target;
    }
    
    public String getAdvertName()
    {
        return advertName;
    }
    
    public Adverts setAdvertName(String advertName)
    {
        this.advertName = advertName;
        return this;
    }
    
    public Short getUsedState()
    {
        return usedState;
    }
    
    public Adverts setUsedState(Short usedState)
    {
        this.usedState = usedState;
        return this;
    }
    
    public Long getCurrentTime()
    {
        return currentTime;
    }
    
    public Adverts setCurrentTime(Long currentTime)
    {
        this.currentTime = currentTime;
        return this;
    }
}
