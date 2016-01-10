/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 网站服务项目 实体对象
 * <p>File：WebServiceItems.java</p>
 * <p>Title: WebServiceItems</p>
 * <p>Description:WebServiceItems</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class WebServiceItems extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**服务名称*/
    @NotBlank(message = "服务名称不能为空")
    @Length(max = 64, message = "服务名称长度不能超过64位")
    private java.lang.String     serviceName;
    
    /**服务图片*/
    private java.lang.String     servicePhoto;
    
    /**推荐指数*/
    @NotNull(message = "推荐指数不能为空")
    @Digits(fraction = 0, integer = 11, message = "推荐指数不能超过11位")
    private java.lang.Integer    commentNum;
    
    /**服务类别*/
    private java.lang.Short      servicerCate;
    
    /**购买人数*/
    private java.lang.Integer    buyNum;
    
    /**浏览次数*/
    private java.lang.Integer    viewNum;
    
    /**1：续期
            2：增加数量*/
    private java.lang.Short      chargType;
    
    /**服务单价*/
    private java.math.BigDecimal price;
    
    /**服务起买数*/
    private java.lang.Integer    minBuyNum;
    
    /**服务金额*/
    private java.math.BigDecimal servicePrice;
    
    /**服务方编号*/
    private java.lang.String     comId;
    
    /**服务简介*/
    private java.lang.String     subMark;
    
    /**服务详细*/
    private java.lang.String     serviceMark;
    
    /**1：品牌商
            2：:终端商
            */
    private java.lang.Short      serviceType;
    
    /**创建时间*/
    private java.lang.Long       createTime;
    
    /**修改时间*/
    private java.lang.Long       updateTime;
    
    private String               servicerCateName;     // 服务类别名称 用于显示
    
    private String               orderParams;
    
    private String               orderType;
    
    public java.lang.String getServiceName()
    {
        return this.serviceName;
    }
    
    public void setServiceName(java.lang.String serviceName)
    {
        this.serviceName = serviceName;
    }
    
    public java.lang.String getServicePhoto()
    {
        return this.servicePhoto;
    }
    
    public void setServicePhoto(java.lang.String servicePhoto)
    {
        this.servicePhoto = servicePhoto;
    }
    
    public java.lang.Integer getCommentNum()
    {
        return this.commentNum;
    }
    
    public void setCommentNum(java.lang.Integer commentNum)
    {
        this.commentNum = commentNum;
    }
    
    public java.lang.Short getServicerCate()
    {
        return this.servicerCate;
    }
    
    public void setServicerCate(java.lang.Short servicerCate)
    {
        this.servicerCate = servicerCate;
    }
    
    public java.lang.Integer getBuyNum()
    {
        return this.buyNum;
    }
    
    public void setBuyNum(java.lang.Integer buyNum)
    {
        this.buyNum = buyNum;
    }
    
    public java.lang.Integer getViewNum()
    {
        return this.viewNum;
    }
    
    public void setViewNum(java.lang.Integer viewNum)
    {
        this.viewNum = viewNum;
    }
    
    public java.lang.Short getChargType()
    {
        return this.chargType;
    }
    
    public void setChargType(java.lang.Short chargType)
    {
        this.chargType = chargType;
    }
    
    public java.math.BigDecimal getPrice()
    {
        return this.price;
    }
    
    public void setPrice(java.math.BigDecimal price)
    {
        this.price = price;
    }
    
    public java.lang.Integer getMinBuyNum()
    {
        return this.minBuyNum;
    }
    
    public void setMinBuyNum(java.lang.Integer minBuyNum)
    {
        this.minBuyNum = minBuyNum;
    }
    
    public java.math.BigDecimal getServicePrice()
    {
        return this.servicePrice;
    }
    
    public void setServicePrice(java.math.BigDecimal servicePrice)
    {
        this.servicePrice = servicePrice;
    }
    
    public java.lang.String getComId()
    {
        return this.comId;
    }
    
    public void setComId(java.lang.String comId)
    {
        this.comId = comId;
    }
    
    public java.lang.String getSubMark()
    {
        return this.subMark;
    }
    
    public void setSubMark(java.lang.String subMark)
    {
        this.subMark = subMark;
    }
    
    public java.lang.String getServiceMark()
    {
        return this.serviceMark;
    }
    
    public void setServiceMark(java.lang.String serviceMark)
    {
        this.serviceMark = serviceMark;
    }
    
    public java.lang.Short getServiceType()
    {
        return this.serviceType;
    }
    
    public void setServiceType(java.lang.Short serviceType)
    {
        this.serviceType = serviceType;
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
    
    public String getServicerCateName()
    {
        return servicerCateName;
    }
    
    public WebServiceItems setServicerCateName(String servicerCateName)
    {
        this.servicerCateName = servicerCateName;
        return this;
    }
    
    public String getOrderParams()
    {
        return orderParams;
    }
    
    public WebServiceItems setOrderParams(String orderParams)
    {
        this.orderParams = orderParams;
        return this;
    }
    
    public String getOrderType()
    {
        return orderType;
    }
    
    public WebServiceItems setOrderType(String orderType)
    {
        this.orderType = orderType;
        return this;
    }
}
