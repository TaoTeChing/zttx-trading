/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import java.util.List;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.module.common.entity.ProductInfo;

/**
 * 品牌商品牌信息 实体对象
 * <p>File：BrandesInfo.java</p>
 * <p>Title: BrandesInfo</p>
 * <p>Description:BrandesInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandesInfo extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**品牌商编号*/
    private java.lang.String     brandId;
    
    /** 品牌名称*/
    private java.lang.String     brandsName;
    
    /**品牌类型1：自有品牌2：授权品牌*/
    private java.lang.Short      brandType;
    
    /**logo域名*/
    private java.lang.String     logoDomin;
    
    /**品牌logo*/
    private java.lang.String     brandLogo;
    
    /**品牌产品形象图*/
    private java.lang.String     proLogo;
    
    /**品牌持有人*/
    private java.lang.Short      brandHold;
    
    /**持有人名称*/
    private java.lang.String     holdName;
    
    /**品牌文字介绍*/
    private java.lang.String     brandSubMark;
    
    /**品牌介绍*/
    private java.lang.String     brandMark;
    
    /**品牌状态0:未审核1：已审核通过2：已经合作3：过期*/
    private java.lang.Short      brandState;
    
    /**服务开始时间*/
    private java.lang.Long       beginTime;
    
    /**服务结束时间*/
    private java.lang.Long       endTime;
    
    /**保证金金额*/
    private java.math.BigDecimal ensureMoney;
    
    /**建档时间*/
    private java.lang.Long       createTime;
    
    /**修改时间*/
    private java.lang.Long       updateTime;
    
    /**建档者IP*/
    private java.lang.Integer    createIp;
    
    /**是否显示 0:不显示,1显示*/
    private java.lang.Boolean    showed;
    
    /**推荐图片*/
    private java.lang.String     recommendImage;
    
    /**满足条件数量*/
    private java.lang.Integer    meetCount;
    
    /**是否为工厂店品牌*/
    private java.lang.Boolean    factoryStore;
    
    /**活动押金*/
    private java.math.BigDecimal deposit;
    
    /**条形码助记码*/
    private java.lang.String     barCodeNum;
    
    /**用户认证*/
    private java.lang.String     userAuth;
    
    /**
     * 品牌商信息
     */
    private BrandInfo            brandInfo;
    
    /**品类编号*/
    private java.lang.Integer    mainId;
    
    /**品类名称*/
    private java.lang.String     mainName;
    
    /**品牌统计 */
    private BrandsCount          brandsCount;
    
    /**
     * 品牌商主营品类
     */
    private List<BrandCatelog>   catelogList;
    
    /**
     * 品牌主营类目
     */
    private List<BrandDeal>      dealList;
    
    /**
     * 产品列表
     */
    private List<ProductInfo>    productList;
    
    /** 统计数量 */
    private String               count            = "0";
    
    /**
     * 品牌权重
     */
    private int                  brandsWeight;
    
    public int getBrandsWeight()
    {
        return brandsWeight;
    }
    
    public void setBrandsWeight(int brandsWeight)
    {
        this.brandsWeight = brandsWeight;
    }
    
    public java.lang.String getUserAuth()
    {
        return userAuth;
    }
    
    public void setUserAuth(java.lang.String userAuth)
    {
        this.userAuth = userAuth;
    }
    
    public java.lang.String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }
    
    public java.lang.String getBrandsName()
    {
        return this.brandsName;
    }
    
    public void setBrandsName(java.lang.String brandsName)
    {
        this.brandsName = brandsName;
    }
    
    public java.lang.Short getBrandType()
    {
        return this.brandType;
    }
    
    public void setBrandType(java.lang.Short brandType)
    {
        this.brandType = brandType;
    }
    
    public java.lang.String getLogoDomin()
    {
        return this.logoDomin;
    }
    
    public void setLogoDomin(java.lang.String logoDomin)
    {
        this.logoDomin = logoDomin;
    }
    
    public java.lang.String getBrandLogo()
    {
        return this.brandLogo;
    }
    
    public void setBrandLogo(java.lang.String brandLogo)
    {
        this.brandLogo = brandLogo;
    }
    
    public java.lang.String getProLogo()
    {
        return this.proLogo;
    }
    
    public void setProLogo(java.lang.String proLogo)
    {
        this.proLogo = proLogo;
    }
    
    public java.lang.Short getBrandHold()
    {
        return this.brandHold;
    }
    
    public void setBrandHold(java.lang.Short brandHold)
    {
        this.brandHold = brandHold;
    }
    
    public java.lang.String getHoldName()
    {
        return this.holdName;
    }
    
    public void setHoldName(java.lang.String holdName)
    {
        this.holdName = holdName;
    }
    
    public java.lang.String getBrandSubMark()
    {
        return this.brandSubMark;
    }
    
    public void setBrandSubMark(java.lang.String brandSubMark)
    {
        this.brandSubMark = brandSubMark;
    }
    
    public java.lang.String getBrandMark()
    {
        return this.brandMark;
    }
    
    public void setBrandMark(java.lang.String brandMark)
    {
        this.brandMark = brandMark;
    }
    
    public java.lang.Short getBrandState()
    {
        return this.brandState;
    }
    
    public void setBrandState(java.lang.Short brandState)
    {
        this.brandState = brandState;
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
    
    public java.math.BigDecimal getEnsureMoney()
    {
        return this.ensureMoney;
    }
    
    public void setEnsureMoney(java.math.BigDecimal ensureMoney)
    {
        this.ensureMoney = ensureMoney;
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
    
    public java.lang.Integer getCreateIp()
    {
        return this.createIp;
    }
    
    public void setCreateIp(java.lang.Integer createIp)
    {
        this.createIp = createIp;
    }
    
    public java.lang.Boolean getShowed()
    {
        return this.showed;
    }
    
    public void setShowed(java.lang.Boolean showed)
    {
        this.showed = showed;
    }
    
    public java.lang.String getRecommendImage()
    {
        return this.recommendImage;
    }
    
    public void setRecommendImage(java.lang.String recommendImage)
    {
        this.recommendImage = recommendImage;
    }
    
    public java.lang.Integer getMeetCount()
    {
        return this.meetCount;
    }
    
    public void setMeetCount(java.lang.Integer meetCount)
    {
        this.meetCount = meetCount;
    }
    
    public java.lang.Boolean getFactoryStore()
    {
        return this.factoryStore;
    }
    
    public void setFactoryStore(java.lang.Boolean factoryStore)
    {
        this.factoryStore = factoryStore;
    }
    
    public java.math.BigDecimal getDeposit()
    {
        return this.deposit;
    }
    
    public void setDeposit(java.math.BigDecimal deposit)
    {
        this.deposit = deposit;
    }
    
    public java.lang.String getBarCodeNum()
    {
        return this.barCodeNum;
    }
    
    public void setBarCodeNum(java.lang.String barCodeNum)
    {
        this.barCodeNum = barCodeNum;
    }
    
    public BrandInfo getBrandInfo()
    {
        return brandInfo;
    }
    
    public void setBrandInfo(BrandInfo brandInfo)
    {
        this.brandInfo = brandInfo;
    }
    
    public String getCount()
    {
        return count;
    }
    
    public void setCount(String count)
    {
        this.count = count;
    }
    
    public Integer getMainId()
    {
        return mainId;
    }
    
    public void setMainId(Integer mainId)
    {
        this.mainId = mainId;
    }
    
    public String getMainName()
    {
        return mainName;
    }
    
    public void setMainName(String mainName)
    {
        this.mainName = mainName;
    }
    
    public List<BrandCatelog> getCatelogList()
    {
        return catelogList;
    }
    
    public void setCatelogList(List<BrandCatelog> catelogList)
    {
        this.catelogList = catelogList;
    }
    
    public List<BrandDeal> getDealList()
    {
        return dealList;
    }
    
    public void setDealList(List<BrandDeal> dealList)
    {
        this.dealList = dealList;
    }
    
    public List<ProductInfo> getProductList()
    {
        return productList;
    }
    
    public void setProductList(List<ProductInfo> productList)
    {
        this.productList = productList;
    }
    
    public BrandsCount getBrandsCount()
    {
        return brandsCount;
    }
    
    public void setBrandsCount(BrandsCount brandsCount)
    {
        this.brandsCount = brandsCount;
    }
}
