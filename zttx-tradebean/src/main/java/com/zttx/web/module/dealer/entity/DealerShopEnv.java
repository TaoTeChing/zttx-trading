/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import java.util.List;

import com.zttx.sdk.core.GenericEntity;

/**
 * 终端商店铺信息 实体对象
 * <p>File：DealerShopEnv.java</p>
 * <p>Title: DealerShopEnv</p>
 * <p>Description:DealerShopEnv</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerShopEnv extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**dealerId*/
    private java.lang.String  dealerId;
    
    /**铺店名称*/
    private java.lang.String  shopName;
    
    /**周边环境*/
    private java.lang.String  environment;
    
    /**店面数*/
    private java.lang.Integer scale;
    
    /**店铺平米数*/
    private java.lang.Double  shopMeters;
    
    /**店铺形式*/
    private java.lang.String  shape;
    
    /**经营行业*/
    private java.lang.String  trade;
    
    /**销售模式*/
    private java.lang.String  model;
    
    /**经营品牌*/
    private java.lang.String  brand;
    
    /**年销售额*/
    private java.lang.String  salesVolume;
    
    /**GPS X坐标*/
    private java.lang.Double  gPSX;
    
    /**GPS Y坐标*/
    private java.lang.Double  gPSY;
    
    /**现有品牌销售情况*/
    private java.lang.String  brandSituation;
    
    /**合作意向*/
    private java.lang.String  intention;
    
    /**开店时间*/
    private java.lang.Long    openTime;
    
    /**浏览次数*/
    private java.lang.Integer viewCount;
    
    /**区域代码*/
    private java.lang.Integer areaNo;
    
    /**城市名称*/
    private java.lang.String  cityName;
    
    /**省份名称*/
    private java.lang.String  provinceName;
    
    /**区域名称*/
    private java.lang.String  areaName;
    
    /**记录产生时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**备注*/
    private java.lang.String  briefIntroduction;
    
    /**是否显示店铺*/
    private java.lang.Boolean showed;
    
    private boolean           onlyShowPic;
    
    private boolean           payedUser;
    
    private String            mobile;
    
    private String            username;
    
    private List<String>      envTmpImgIds;
    
    private Integer           cityNo;
    
    private Integer           provinceNo;
    
    private String            openTimeStr;
    
    private List<String>      pics;
    
    private String            address;
    
    private List<DealerImage> dealerImages;
    
    private Boolean           collectedState;       // 是否已收藏
    
    private Short             userType;             // 用户类型 0 品牌商 1经销商
    
    public Short getUserType()
    {
        return userType;
    }
    
    public void setUserType(Short userType)
    {
        this.userType = userType;
    }
    
    public Boolean getCollectedState()
    {
        return collectedState;
    }
    
    public void setCollectedState(Boolean collectedState)
    {
        this.collectedState = collectedState;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public List<String> getEnvTmpImgIds()
    {
        return envTmpImgIds;
    }
    
    public void setEnvTmpImgIds(List<String> envTmpImgIds)
    {
        this.envTmpImgIds = envTmpImgIds;
    }
    
    public Integer getCityNo()
    {
        return cityNo;
    }
    
    public void setCityNo(Integer cityNo)
    {
        this.cityNo = cityNo;
    }
    
    public Integer getProvinceNo()
    {
        return provinceNo;
    }
    
    public void setProvinceNo(Integer provinceNo)
    {
        this.provinceNo = provinceNo;
    }
    
    public String getOpenTimeStr()
    {
        return openTimeStr;
    }
    
    public void setOpenTimeStr(String openTimeStr)
    {
        this.openTimeStr = openTimeStr;
    }
    
    public List<String> getPics()
    {
        return pics;
    }
    
    public void setPics(List<String> pics)
    {
        this.pics = pics;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public List<DealerImage> getDealerImages()
    {
        return dealerImages;
    }
    
    public void setDealerImages(List<DealerImage> dealerImages)
    {
        this.dealerImages = dealerImages;
    }
    
    public boolean isPayedUser()
    {
        return payedUser;
    }
    
    public void setPayedUser(boolean payedUser)
    {
        this.payedUser = payedUser;
    }
    
    public boolean isOnlyShowPic()
    {
        return onlyShowPic;
    }
    
    public void setOnlyShowPic(boolean onlyShowPic)
    {
        this.onlyShowPic = onlyShowPic;
    }
    
    public java.lang.String getDealerId()
    {
        return this.dealerId;
    }
    
    public void setDealerId(java.lang.String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public java.lang.String getShopName()
    {
        return this.shopName;
    }
    
    public void setShopName(java.lang.String shopName)
    {
        this.shopName = shopName;
    }
    
    public java.lang.String getEnvironment()
    {
        return this.environment;
    }
    
    public void setEnvironment(java.lang.String environment)
    {
        this.environment = environment;
    }
    
    public java.lang.Integer getScale()
    {
        return this.scale;
    }
    
    public void setScale(java.lang.Integer scale)
    {
        this.scale = scale;
    }
    
    public java.lang.Double getShopMeters()
    {
        return this.shopMeters;
    }
    
    public void setShopMeters(java.lang.Double shopMeters)
    {
        this.shopMeters = shopMeters;
    }
    
    public java.lang.String getShape()
    {
        return this.shape;
    }
    
    public void setShape(java.lang.String shape)
    {
        this.shape = shape;
    }
    
    public java.lang.String getTrade()
    {
        return this.trade;
    }
    
    public void setTrade(java.lang.String trade)
    {
        this.trade = trade;
    }
    
    public java.lang.String getModel()
    {
        return this.model;
    }
    
    public void setModel(java.lang.String model)
    {
        this.model = model;
    }
    
    public java.lang.String getBrand()
    {
        return this.brand;
    }
    
    public void setBrand(java.lang.String brand)
    {
        this.brand = brand;
    }
    
    public java.lang.String getSalesVolume()
    {
        return this.salesVolume;
    }
    
    public void setSalesVolume(java.lang.String salesVolume)
    {
        this.salesVolume = salesVolume;
    }
    
    public java.lang.Double getGPSX()
    {
        return this.gPSX;
    }
    
    public void setGPSX(java.lang.Double gPSX)
    {
        this.gPSX = gPSX;
    }
    
    public java.lang.Double getGPSY()
    {
        return this.gPSY;
    }
    
    public void setGPSY(java.lang.Double gPSY)
    {
        this.gPSY = gPSY;
    }
    
    public java.lang.String getBrandSituation()
    {
        return this.brandSituation;
    }
    
    public void setBrandSituation(java.lang.String brandSituation)
    {
        this.brandSituation = brandSituation;
    }
    
    public java.lang.String getIntention()
    {
        return this.intention;
    }
    
    public void setIntention(java.lang.String intention)
    {
        this.intention = intention;
    }
    
    public java.lang.Long getOpenTime()
    {
        return this.openTime;
    }
    
    public void setOpenTime(java.lang.Long openTime)
    {
        this.openTime = openTime;
    }
    
    public java.lang.Integer getViewCount()
    {
        return this.viewCount;
    }
    
    public void setViewCount(java.lang.Integer viewCount)
    {
        this.viewCount = viewCount;
    }
    
    public java.lang.Integer getAreaNo()
    {
        return this.areaNo;
    }
    
    public void setAreaNo(java.lang.Integer areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public java.lang.String getCityName()
    {
        return this.cityName;
    }
    
    public void setCityName(java.lang.String cityName)
    {
        this.cityName = cityName;
    }
    
    public java.lang.String getProvinceName()
    {
        return this.provinceName;
    }
    
    public void setProvinceName(java.lang.String provinceName)
    {
        this.provinceName = provinceName;
    }
    
    public java.lang.String getAreaName()
    {
        return this.areaName;
    }
    
    public void setAreaName(java.lang.String areaName)
    {
        this.areaName = areaName;
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
    
    public java.lang.String getBriefIntroduction()
    {
        return this.briefIntroduction;
    }
    
    public void setBriefIntroduction(java.lang.String briefIntroduction)
    {
        this.briefIntroduction = briefIntroduction;
    }
    
    public java.lang.Boolean getShowed()
    {
        return this.showed;
    }
    
    public void setShowed(java.lang.Boolean showed)
    {
        this.showed = showed;
    }
}
