/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌权重信息 实体对象
 * <p>File：BrandesWeightInfo.java</p>
 * <p>Title: BrandesWeightInfo</p>
 * <p>Description:BrandesWeightInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 章旭楠
 * @version 1.0
 */
public class BrandesWeightInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**品牌商编号*/
    @NotBlank(message = "品牌商编号不能为空")
    private String            brandId;
    private String            brandName;//查询条件
    
    /**品牌编号*/
    @NotBlank(message = "品牌编号不能为空")
    private String            brandesId;
    private String            brandesName;//查询条件
    
    /**权重值*/
    @NotNull(message = "权重值不能为空")
    @Range(min = 0, max = 100, message = "权重设置范围只能是[0～100]")
    private Integer           weight;
    
    /**建档时间*/
    private Long              createTime;
    
    /**修改时间*/
    private Long              updateTime;
    
    public String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public String getBrandesId()
    {
        return this.brandesId;
    }
    
    public void setBrandesId(String brandesId)
    {
        this.brandesId = brandesId;
    }
    
    public Integer getWeight()
    {
        return this.weight;
    }
    
    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }
    
    public Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
    
    public Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(Long updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandesName() {
        return brandesName;
    }

    public void setBrandesName(String brandesName) {
        this.brandesName = brandesName;
    }
}
