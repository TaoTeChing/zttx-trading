/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.entity;

import com.zttx.web.module.exhibition.model.DecorateConfigModel;

/**
 * 展厅自定义模块配置 实体对象
 * <p>File：DecorateConfig.java</p>
 * <p>Title: DecorateConfig</p>
 * <p>Description:DecorateConfig</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DecorateConfig  extends DecorateConfigModel
{
    private static final long serialVersionUID = 1L;
    
    /**品牌商主帐号编号*/
    private java.lang.String  brandId;
    
    /**品牌编号*/
    private java.lang.String  brandsId;
    
    /**标题*/
    private java.lang.String  title;
    
    /**是否显示标题*/
    private java.lang.Boolean showTitle;
    
    /**模块类型
	 * 0：自定义非固定模块
	 * 1：品牌介绍固定模块
	 */
    private java.lang.Short   configType;
    
    /**模块编号*/
    private java.lang.Short   tagId;
    
    /**是否显示*/
    private java.lang.Boolean showFlag;
    
    /**显示类型
	 * 1:自定义html内容
	 * 2:图片滚动模块
	 */
    private java.lang.Integer showType;
    
    /**显示内容*/
    private java.lang.String  showText;
    
    /**排序编号*/
    private java.lang.Short   showOrder;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**最后修改时间*/
    private java.lang.Long    updateTime;
    
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
    
    public java.lang.String getTitle()
    {
        return this.title;
    }
    
    public void setTitle(java.lang.String title)
    {
        this.title = title;
    }
    
    public java.lang.Boolean getShowTitle()
    {
        return this.showTitle;
    }
    
    public void setShowTitle(java.lang.Boolean showTitle)
    {
        this.showTitle = showTitle;
    }
    
    public java.lang.Short getConfigType()
    {
        return this.configType;
    }
    
    public void setConfigType(java.lang.Short configType)
    {
        this.configType = configType;
    }
    
    public java.lang.Short getTagId()
    {
        return this.tagId;
    }
    
    public void setTagId(java.lang.Short tagId)
    {
        this.tagId = tagId;
    }
    
    public java.lang.Boolean getShowFlag()
    {
        return this.showFlag;
    }
    
    public void setShowFlag(java.lang.Boolean showFlag)
    {
        this.showFlag = showFlag;
    }
    
    public java.lang.Integer getShowType()
    {
        return this.showType;
    }
    
    public void setShowType(java.lang.Integer showType)
    {
        this.showType = showType;
    }
    
    public java.lang.String getShowText()
    {
        return this.showText;
    }
    
    public void setShowText(java.lang.String showText)
    {
        this.showText = showText;
    }
    
    public java.lang.Short getShowOrder()
    {
        return this.showOrder;
    }
    
    public void setShowOrder(java.lang.Short showOrder)
    {
        this.showOrder = showOrder;
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
