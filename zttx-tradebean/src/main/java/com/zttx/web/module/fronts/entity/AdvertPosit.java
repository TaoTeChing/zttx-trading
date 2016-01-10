/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 广告位置管理 实体对象
 * <p>File：AdvertPosit.java</p>
 * <p>Title: AdvertPosit</p>
 * <p>Description:AdvertPosit</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class AdvertPosit extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**广告位名称*/
    @NotBlank(message = "广告位名称不能为空")
    private java.lang.String  advertName;
    
    /**广告描述*/
    @NotBlank(message = "广告描述不能为空")
    private java.lang.String  advertDesc;
    
    /**广告位key*/
    @NotBlank(message = "广告位key不能为空")
    private java.lang.String  advertKey;
    
    /**显示数量*/
    @NotNull(message = "显示数量不能为空")
    @Min(value = 1, message = "显示数量不符合规定")
    private java.lang.Integer viewNum;
    
    /**广告类别（1：图片广告，2：标题广告）*/
    @NotNull(message = "广告类别不能为空")
    private java.lang.Short   advertCate;
    
    /**图片宽*/
    @NotNull(message = "图片宽不能为空")
    @Min(value = 0, message = "图片宽不符合规定")
    private java.lang.Integer imgWidth;
    
    /**图片高*/
    @NotNull(message = "图片高不能为空")
    @Min(value = 0, message = "图片高不符合规定")
    private java.lang.Integer imgHeight;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /** 广告 */
    private List<Adverts>     subList;
    
    public java.lang.String getAdvertName()
    {
        return this.advertName;
    }
    
    public void setAdvertName(java.lang.String advertName)
    {
        this.advertName = advertName;
    }
    
    public java.lang.String getAdvertDesc()
    {
        return this.advertDesc;
    }
    
    public void setAdvertDesc(java.lang.String advertDesc)
    {
        this.advertDesc = advertDesc;
    }
    
    public java.lang.String getAdvertKey()
    {
        return this.advertKey;
    }
    
    public void setAdvertKey(java.lang.String advertKey)
    {
        this.advertKey = advertKey;
    }
    
    public java.lang.Integer getViewNum()
    {
        return this.viewNum;
    }
    
    public void setViewNum(java.lang.Integer viewNum)
    {
        this.viewNum = viewNum;
    }
    
    public java.lang.Short getAdvertCate()
    {
        return this.advertCate;
    }
    
    public void setAdvertCate(java.lang.Short advertCate)
    {
        this.advertCate = advertCate;
    }
    
    public java.lang.Integer getImgWidth()
    {
        return this.imgWidth;
    }
    
    public void setImgWidth(java.lang.Integer imgWidth)
    {
        this.imgWidth = imgWidth;
    }
    
    public java.lang.Integer getImgHeight()
    {
        return this.imgHeight;
    }
    
    public void setImgHeight(java.lang.Integer imgHeight)
    {
        this.imgHeight = imgHeight;
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
    
    public List<Adverts> getSubList()
    {
        return subList;
    }
    
    public void setSubList(List<Adverts> subList)
    {
        this.subList = subList;
    }
}
