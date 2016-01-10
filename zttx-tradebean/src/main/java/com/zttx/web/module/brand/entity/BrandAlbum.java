/*
 * @(#)BrandAlbum.java 2015-8-26 下午1:41:36
 * Copyright 2015 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * <p>File：BrandAlbum.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-26 下午1:41:36</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
public class BrandAlbum extends GenericEntity
{
    private static final long serialVersionUID = 7950359325263004181L;

    private String            brandId;

    private String            brandsId;

    private String            domainName;

    private String            photoName;

    private String            imageName;

    private Long              createTime;

    private Long              updateTime;

    private Integer           createIp;

    private String changeImagePath;
    
    public String getChangeImagePath()
    {
        return changeImagePath;
    }

    public void setChangeImagePath(String changeImagePath)
    {
        this.changeImagePath = changeImagePath;
    }

    public String getBrandId()
    {
        return brandId;
    }

    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }

    public String getBrandsId()
    {
        return brandsId;
    }

    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }

    public String getDomainName()
    {
        return domainName;
    }

    public void setDomainName(String domainName)
    {
        this.domainName = domainName;
    }

    public String getPhotoName()
    {
        return photoName;
    }

    public void setPhotoName(String photoName)
    {
        this.photoName = photoName;
    }

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
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

    public Integer getCreateIp()
    {
        return createIp;
    }

    public void setCreateIp(Integer createIp)
    {
        this.createIp = createIp;
    }
}
