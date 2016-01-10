/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商地址信息 实体对象
 * <p>File：DealerAddr.java</p>
 * <p>Title: DealerAddr</p>
 * <p>Description:DealerAddr</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerAddr extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**经销商编号*/
    private java.lang.String  dealerId;
    
    /**收货人姓名*/
    @NotBlank(message = "收货人姓名不能为空")
    private java.lang.String  dealerName;
    
    /**收货人所在地区*/
    @NotNull(message = "收货人所在地区不能为空")
    @Digits(fraction = 0, integer = 6, message = "收货人所在地区格式不正确")
    @Min(value = 0, message = "收货人所在地区格式不正确")
    private java.lang.Integer dealerAddr;
    
    /**收货人街道地址*/
    @NotBlank(message = "收货人街道地址不能为空")
    private java.lang.String  dealerAddress;
    
    /**省份名称*/
    private java.lang.String  provinceName;
    
    /**城市名称*/
    private java.lang.String  cityName;
    
    /**区域名称*/
    private java.lang.String  areaName;
    
    /**邮政编码*/
    private java.lang.String  postCode;
    
    /**手机号码*/
    private java.lang.String  dealerMobile;
    
    /**电话号码*/
    private java.lang.String  dealerTel;
    
    /**是否默认地址*/
    private java.lang.Boolean dealerDefault;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    // 省份
    private String            province;
    
    // 城市
    private String            city;
    
    // 地区
    private String            county;
    
    public java.lang.String getDealerId()
    {
        return this.dealerId;
    }
    
    public void setDealerId(java.lang.String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public java.lang.String getDealerName()
    {
        return this.dealerName;
    }
    
    public void setDealerName(java.lang.String dealerName)
    {
        this.dealerName = dealerName;
    }
    
    public java.lang.Integer getDealerAddr()
    {
        return this.dealerAddr;
    }
    
    public void setDealerAddr(java.lang.Integer dealerAddr)
    {
        this.dealerAddr = dealerAddr;
    }
    
    public java.lang.String getDealerAddress()
    {
        return this.dealerAddress;
    }
    
    public void setDealerAddress(java.lang.String dealerAddress)
    {
        this.dealerAddress = dealerAddress;
    }
    
    public java.lang.String getProvinceName()
    {
        return this.provinceName;
    }
    
    public void setProvinceName(java.lang.String provinceName)
    {
        this.provinceName = provinceName;
    }
    
    public java.lang.String getCityName()
    {
        return this.cityName;
    }
    
    public void setCityName(java.lang.String cityName)
    {
        this.cityName = cityName;
    }
    
    public java.lang.String getAreaName()
    {
        return this.areaName;
    }
    
    public void setAreaName(java.lang.String areaName)
    {
        this.areaName = areaName;
    }
    
    public java.lang.String getPostCode()
    {
        return this.postCode;
    }
    
    public void setPostCode(java.lang.String postCode)
    {
        this.postCode = postCode;
    }
    
    public java.lang.String getDealerMobile()
    {
        return this.dealerMobile;
    }
    
    public void setDealerMobile(java.lang.String dealerMobile)
    {
        this.dealerMobile = dealerMobile;
    }
    
    public java.lang.String getDealerTel()
    {
        return this.dealerTel;
    }
    
    public void setDealerTel(java.lang.String dealerTel)
    {
        this.dealerTel = dealerTel;
    }
    
    public java.lang.Boolean getDealerDefault()
    {
        return this.dealerDefault;
    }
    
    public void setDealerDefault(java.lang.Boolean dealerDefault)
    {
        this.dealerDefault = dealerDefault;
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
    
    public String getProvince()
    {
        return province;
    }
    
    public DealerAddr setProvince(String province)
    {
        this.province = province;
        return this;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public DealerAddr setCity(String city)
    {
        this.city = city;
        return this;
    }
    
    public String getCounty()
    {
        return county;
    }
    
    public DealerAddr setCounty(String county)
    {
        this.county = county;
        return this;
    }
}
