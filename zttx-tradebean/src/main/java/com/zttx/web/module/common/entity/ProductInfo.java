/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import java.math.BigDecimal;
import java.util.List;

import com.zttx.sdk.core.GenericEntity;

/**
 * <p>File：ProductInfo.java</p>
 * <p>Title: 商品信息表 实体对象</p>
 * <p>Description:产品信息已由商品中心实现，
 * 顾在交易平台中商品信息实体对象只用着查询，特别针对产品索引管理</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductInfo extends GenericEntity
{
    private static final long      serialVersionUID = 1L;
    
    /** 品牌商编号 */
    private String                 brandId;
    
    /** 公司名称 */
    private String                 comName;
    
    /** 法人 */
    private String                 legalName;
    
    /** 品牌编号 */
    private String                 brandsId;
    
    /** 品牌名称 */
    private String                 brandsName;
    
    /** 省级名称 */
    private String                 provinceName;
    
    /** 城市名称 */
    private String                 cityName;
    
    /** 区域名称 */
    private String                 areaName;
    
    /** 类目编号 */
    private Integer                dealNo;
    
    /** 类目名称 */
    private String                 dealName;
    
    /** 运费物流（1经销商承担，2品牌商承担）*/
    private Integer                productCarry;
    
    /** 产品类型（1现货产品，2预订产品） */
    private Integer                productCate;
    
    /** 产品推荐 */
    private Boolean                productGroom;
    
    /** 产品授信 */
    private Boolean                productCredit;
    
    /** 产品拿样 */
    private Boolean                productSample;
    
    /** 产品主图 */
    private String                 productImage;
    
    /** 搜索关键字 */
    private String                 productKeyword;
    
    /** 产品编号  */
    private String                 productNo;
    
    /** 吊牌价  */
    private BigDecimal             productPrice;
    
    /** 直供价  */
    private BigDecimal             directPrice;
    
    /** 同类产品价格 */
    private BigDecimal             similarPrice;
    
    /** 省带价 */
    private BigDecimal             provincePrice;
    
    /** 产品库存 */
    private Integer                productStore;
    
    /** 产品查看数量 */
    private Integer                viewNum;
    
    /** 产品销量 */
    private Integer                saleNum;
    
    /** 产品月销量 */
    private Integer                monthSaleNum;
    
    /** 产品收藏量 */
    private Integer                collectNum;
    
    /** 产品标题 */
    private String                 productTitle;
    
    /** 创建时间 */
    private Long                   createTime;
    
    /** 修改时间 */
    private Long                   updateTime;
    
    /** 置顶时间 */
    private Long                   topTime;
    
    /** 产品类目 */
    private List<DealDic>          dealDics;
    
    /** 产品分类 */
    private List<ProductCatalog>   categorys;
    
    /** 产品图片 */
    private List<ProductImage>     images;
    
    /**
     * 产品属性
     */
    private List<ProductAttrValue> attrs;
    
    /**
     * 最少库存值
     */
    private java.lang.Integer      minStore;
    
    /**
     * 品牌权重
     */
    private java.lang.Integer      brandsWeight;
    
    /**
     * 产品权重
     */
    private java.lang.Integer      productWeight;
    
    /**
     * 季节
     */
    private java.lang.Short        season;
    
    public Integer getBrandsWeight()
    {
        return brandsWeight;
    }
    
    public void setBrandsWeight(Integer brandsWeight)
    {
        this.brandsWeight = brandsWeight;
    }
    
    public Integer getProductWeight()
    {
        return productWeight;
    }
    
    public void setProductWeight(Integer productWeight)
    {
        this.productWeight = productWeight;
    }
    
    public Short getSeason()
    {
        return season;
    }
    
    public void setSeason(Short season)
    {
        this.season = season;
    }
    
    public String getBrandId()
    {
        return brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public String getComName()
    {
        return comName;
    }
    
    public void setComName(String comName)
    {
        this.comName = comName;
    }
    
    public String getLegalName()
    {
        return legalName;
    }
    
    public void setLegalName(String legalName)
    {
        this.legalName = legalName;
    }
    
    public String getBrandsId()
    {
        return brandsId;
    }
    
    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public String getBrandsName()
    {
        return brandsName;
    }
    
    public void setBrandsName(String brandsName)
    {
        this.brandsName = brandsName;
    }
    
    public String getProvinceName()
    {
        return provinceName;
    }
    
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }
    
    public String getCityName()
    {
        return cityName;
    }
    
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    
    public String getAreaName()
    {
        return areaName;
    }
    
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }
    
    public Integer getDealNo()
    {
        return dealNo;
    }
    
    public void setDealNo(Integer dealNo)
    {
        this.dealNo = dealNo;
    }
    
    public String getDealName()
    {
        return dealName;
    }
    
    public void setDealName(String dealName)
    {
        this.dealName = dealName;
    }
    
    public Integer getProductCarry()
    {
        return productCarry;
    }
    
    public void setProductCarry(Integer productCarry)
    {
        this.productCarry = productCarry;
    }
    
    public Integer getProductCate()
    {
        return productCate;
    }
    
    public void setProductCate(Integer productCate)
    {
        this.productCate = productCate;
    }
    
    public Boolean getProductGroom()
    {
        return productGroom;
    }
    
    public void setProductGroom(Boolean productGroom)
    {
        this.productGroom = productGroom;
    }
    
    public Boolean getProductCredit()
    {
        return productCredit;
    }
    
    public void setProductCredit(Boolean productCredit)
    {
        this.productCredit = productCredit;
    }
    
    public Boolean getProductSample()
    {
        return productSample;
    }
    
    public void setProductSample(Boolean productSample)
    {
        this.productSample = productSample;
    }
    
    public String getProductImage()
    {
        return productImage;
    }
    
    public void setProductImage(String productImage)
    {
        this.productImage = productImage;
    }
    
    public String getProductKeyword()
    {
        return productKeyword;
    }
    
    public void setProductKeyword(String productKeyword)
    {
        this.productKeyword = productKeyword;
    }
    
    public String getProductNo()
    {
        return productNo;
    }
    
    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }
    
    public BigDecimal getProductPrice()
    {
        return productPrice;
    }
    
    public void setProductPrice(BigDecimal productPrice)
    {
        this.productPrice = productPrice;
    }
    
    public BigDecimal getDirectPrice()
    {
        return directPrice;
    }
    
    public void setDirectPrice(BigDecimal directPrice)
    {
        this.directPrice = directPrice;
    }
    
    public BigDecimal getSimilarPrice()
    {
        return similarPrice;
    }
    
    public void setSimilarPrice(BigDecimal similarPrice)
    {
        this.similarPrice = similarPrice;
    }
    
    public BigDecimal getProvincePrice()
    {
        return provincePrice;
    }
    
    public void setProvincePrice(BigDecimal provincePrice)
    {
        this.provincePrice = provincePrice;
    }
    
    public Integer getProductStore()
    {
        return productStore;
    }
    
    public void setProductStore(Integer productStore)
    {
        this.productStore = productStore;
    }
    
    public Integer getSaleNum()
    {
        return saleNum;
    }
    
    public void setSaleNum(Integer saleNum)
    {
        this.saleNum = saleNum;
    }
    
    public Integer getViewNum()
    {
        return viewNum;
    }
    
    public void setViewNum(Integer viewNum)
    {
        this.viewNum = viewNum;
    }
    
    public Integer getMonthSaleNum()
    {
        return monthSaleNum;
    }
    
    public void setMonthSaleNum(Integer monthSaleNum)
    {
        this.monthSaleNum = monthSaleNum;
    }
    
    public Integer getCollectNum()
    {
        return collectNum;
    }
    
    public void setCollectNum(Integer collectNum)
    {
        this.collectNum = collectNum;
    }
    
    public String getProductTitle()
    {
        return productTitle;
    }
    
    public void setProductTitle(String productTitle)
    {
        this.productTitle = productTitle;
    }
    
    public Long getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
    
    public Long getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public Long getTopTime()
    {
        return topTime;
    }
    
    public void setTopTime(Long topTime)
    {
        this.topTime = topTime;
    }
    
    public List<DealDic> getDealDics()
    {
        return dealDics;
    }
    
    public void setDealDics(List<DealDic> dealDics)
    {
        this.dealDics = dealDics;
    }
    
    public List<ProductCatalog> getCategorys()
    {
        return categorys;
    }
    
    public void setCategorys(List<ProductCatalog> categorys)
    {
        this.categorys = categorys;
    }
    
    public List<ProductImage> getImages()
    {
        return images;
    }
    
    public void setImages(List<ProductImage> images)
    {
        this.images = images;
    }
    
    public List<ProductAttrValue> getAttrs()
    {
        return attrs;
    }
    
    public void setAttrs(List<ProductAttrValue> attrs)
    {
        this.attrs = attrs;
    }
    
    public Integer getMinStore()
    {
        return minStore;
    }
    
    public void setMinStore(Integer minStore)
    {
        this.minStore = minStore;
    }
}