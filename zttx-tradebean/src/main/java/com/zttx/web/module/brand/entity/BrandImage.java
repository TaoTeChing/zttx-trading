/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.sdk.utils.CalendarUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 品牌商图片管理 实体对象
 * <p>File：BrandImage.java</p>
 * <p>Title: BrandImage</p>
 * <p>Description:BrandImage</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandImage extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**品牌商编号*/
    private java.lang.String  brandId;
    
    /**类别编号*/
    private java.lang.String  cateId;
    
    /**图片域名*/
    private java.lang.String  domainName;
    
    /**图片原名称*/
    private java.lang.String  photoName;
    
    /**图片新名称*/
    private java.lang.String  imageName;
    
    /**图片说明*/
    private java.lang.String  imageMark;
    
    /**图片大小 单位KB*/
    private java.lang.Integer imageSize;
    
    /**上传时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**上传者IP*/
    private java.lang.Integer createIp;
    
    private Long              startTime;            // 上传时间start
    
    private Long              endTime;              // 上传时间end
    
    private String            cateName;             // 类型名称
    
    public java.lang.String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }
    
    public java.lang.String getCateId()
    {
        return this.cateId;
    }
    
    public void setCateId(java.lang.String cateId)
    {
        this.cateId = cateId;
    }
    
    public java.lang.String getDomainName()
    {
        return this.domainName;
    }
    
    public void setDomainName(java.lang.String domainName)
    {
        this.domainName = domainName;
    }
    
    public java.lang.String getPhotoName()
    {
        return this.photoName;
    }
    
    public void setPhotoName(java.lang.String photoName)
    {
        this.photoName = photoName;
    }
    
    public java.lang.String getImageName()
    {
        return this.imageName;
    }
    
    public void setImageName(java.lang.String imageName)
    {
        this.imageName = imageName;
    }
    
    public java.lang.String getImageMark()
    {
        return this.imageMark;
    }
    
    public void setImageMark(java.lang.String imageMark)
    {
        this.imageMark = imageMark;
    }
    
    public java.lang.Integer getImageSize()
    {
        return this.imageSize;
    }
    
    public void setImageSize(java.lang.Integer imageSize)
    {
        this.imageSize = imageSize;
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
    
    public Long getStartTime()
    {
        return startTime;
    }
    
    public BrandImage setStartTime(Long startTime)
    {
        this.startTime = startTime;
        return this;
    }
    
    public Long getEndTime()
    {
        return endTime;
    }
    
    public BrandImage setEndTime(Long endTime)
    {
        this.endTime = endTime;
        return this;
    }
    
    public String getCateName()
    {
        return cateName;
    }
    
    public BrandImage setCateName(String cateName)
    {
        this.cateName = cateName;
        return this;
    }
    
    public void setStartTimeStr(String startTimeStr)
    {
        if (StringUtils.isNotBlank(startTimeStr))
        {
            this.startTime = CalendarUtils.getLongFromTime(startTimeStr);
        }
    }
    
    public void setEndTimeStr(String endTimeStr)
    {
        if (StringUtils.isNotBlank(endTimeStr))
        {
            this.endTime = CalendarUtils.addDay(CalendarUtils.getLongFromTime(endTimeStr), 1) - 1;// 必须-1
        }
    }
}
