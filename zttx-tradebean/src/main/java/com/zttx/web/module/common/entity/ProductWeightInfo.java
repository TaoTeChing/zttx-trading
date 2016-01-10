/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zttx.sdk.core.GenericEntity;

/**
 * ProductWeightInfo 实体对象
 * <p>File：ProductWeightInfo.java</p>
 * <p>Title: ProductWeightInfo</p>
 * <p>Description:ProductWeightInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductWeightInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**产品id */
    private java.lang.String  refrenceId;
    
    /**品牌商id */
    @NotBlank(message = "品牌商编号不能为空")
    private java.lang.String  brandId;
    
    /**品牌id */
    @NotBlank(message = "品牌编号不能为空")
    private java.lang.String  brandsId;
    
    /**权重1-100 */
    @NotNull(message = "权重值不能为空")
    @Range(min = 0, max = 100, message = "权重设置范围只能是[0～100]")
    private java.lang.Integer weight;
    
    /**季节，1，2，3，4 */
    @NotNull(message = "季节不能为空")
    @Range(min = 0, max = 4, message = "季节范围只能是[0～4]")
    private java.lang.Short   season;
    
    /**创建时间 */
    private java.lang.Long    createTime;
    
    /**最后更新时间 */
    private java.lang.Long    updateTime;
    
    //查询返回结果和查询参数
    private String productTitle;//产品标题
    
    private String productNo;//产品货号
    
    private String brandName;//品牌商名称
    
    private String brandsName;//品牌名称
    
    public String getProductTitle()
    {
        return productTitle;
    }

    public void setProductTitle(String productTitle)
    {
        this.productTitle = productTitle;
    }

    public String getProductNo()
    {
        return productNo;
    }

    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }

    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public String getBrandsName()
    {
        return brandsName;
    }

    public void setBrandsName(String brandsName)
    {
        this.brandsName = brandsName;
    }

    public java.lang.String getRefrenceId()
    {
        return this.refrenceId;
    }
    
    public void setRefrenceId(java.lang.String refrenceId)
    {
        this.refrenceId = refrenceId;
    }
    
    public java.lang.String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }
    
    public java.lang.String getBrandsId()
    {
        return this.brandsId;
    }
    
    public void setBrandsId(java.lang.String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public java.lang.Integer getWeight()
    {
        return this.weight;
    }
    
    public void setWeight(java.lang.Integer weight)
    {
        this.weight = weight;
    }
    
    public java.lang.Short getSeason()
    {
        return this.season;
    }
    
    public void setSeason(java.lang.Short season)
    {
        this.season = season;
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
}
