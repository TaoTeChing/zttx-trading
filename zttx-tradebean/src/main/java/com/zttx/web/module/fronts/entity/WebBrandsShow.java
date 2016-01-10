/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 首页感兴趣品牌展示 实体对象
 * <p>File：WebBrandsShow.java</p>
 * <p>Title: WebBrandsShow</p>
 * <p>Description:WebBrandsShow</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class WebBrandsShow extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**品牌编号*/
    @NotBlank(message = "品牌编号不能为空")
    private String            brandsId;
    
    private String            brandsName;           // 品牌名称 查询显示用
    
    /**显示类型：1:感兴趣的品牌
            */
    @NotNull(message = "显示类型不能为空")
    private Short             showType;
    
    /**排序编号*/
    @NotNull(message = "排序编号不能为空")
    private Integer           orderId;
    
    /**image*/
    private String            image;
    
    /**建档时间*/
    private Long              createTime;
    
    /**修改时间*/
    private Long              updateTime;
    
    public String getBrandsId()
    {
        return this.brandsId;
    }
    
    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public Short getShowType()
    {
        return this.showType;
    }
    
    public void setShowType(Short showType)
    {
        this.showType = showType;
    }
    
    public Integer getOrderId()
    {
        return this.orderId;
    }
    
    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }
    
    public String getImage()
    {
        return this.image;
    }
    
    public void setImage(String image)
    {
        this.image = image;
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
    
    public String getBrandsName()
    {
        return brandsName;
    }
    
    public void setBrandsName(String brandsName)
    {
        this.brandsName = brandsName;
    }
}
