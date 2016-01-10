/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销店铺 临时图片 实体对象
 * <p>File：DealerShopEnvImgTemp.java</p>
 * <p>Title: DealerShopEnvImgTemp</p>
 * <p>Description:DealerShopEnvImgTemp</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerShopEnvImgTemp extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**shopEnvId*/
    private String            shopEnvId;
    
    /**imagePath*/
    private String            imagePath;
    
    /**createTime*/
    private Long              createTime;
    
    /**updateTime*/
    private Long              updateTime;
    
    public String getShopEnvId()
    {
        return this.shopEnvId;
    }
    
    public void setShopEnvId(String shopEnvId)
    {
        this.shopEnvId = shopEnvId;
    }
    
    public String getImagePath()
    {
        return this.imagePath;
    }
    
    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
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
}
