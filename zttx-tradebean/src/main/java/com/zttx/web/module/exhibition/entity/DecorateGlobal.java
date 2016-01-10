/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 展厅装修全局配置 实体对象
 * <p>File：DecorateGlobal.java</p>
 * <p>Title: DecorateGlobal</p>
 * <p>Description:DecorateGlobal</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DecorateGlobal extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**字体颜色*/
	private java.lang.String fontCcolor;
	/**全局链接字体颜色*/
	private java.lang.String urlFontColor;
	/**链接变化颜色*/
	private java.lang.String urlChangeColor;
	/**背景色*/
	private java.lang.String backColor;
	/**是否显示背景色*/
	private java.lang.Boolean showBackColor;
	/**全局背景图片地址*/
	private java.lang.String backUrl;
	/**是否显示背景图*/
	private java.lang.Boolean showBackUrl;
	/**背景平铺方式*/
	private java.lang.String backRepeat;
	/**背景所在位置*/
	private java.lang.String backPosition;
	/**样式名称*/
	private java.lang.String skinName;
	/**建档日期*/
	private java.lang.Long createTime;
	/**最后修改日期*/
	private java.lang.Long updateTime;
	
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
	
	public java.lang.String getFontCcolor()
	{
		return this.fontCcolor;
	}
	
	public void setFontCcolor(java.lang.String fontCcolor)
	{
		this.fontCcolor = fontCcolor;
	}
	
	public java.lang.String getUrlFontColor()
	{
		return this.urlFontColor;
	}
	
	public void setUrlFontColor(java.lang.String urlFontColor)
	{
		this.urlFontColor = urlFontColor;
	}
	
	public java.lang.String getUrlChangeColor()
	{
		return this.urlChangeColor;
	}
	
	public void setUrlChangeColor(java.lang.String urlChangeColor)
	{
		this.urlChangeColor = urlChangeColor;
	}
	
	public java.lang.String getBackColor()
	{
		return this.backColor;
	}
	
	public void setBackColor(java.lang.String backColor)
	{
		this.backColor = backColor;
	}
	
	public java.lang.Boolean getShowBackColor()
	{
		return this.showBackColor;
	}
	
	public void setShowBackColor(java.lang.Boolean showBackColor)
	{
		this.showBackColor = showBackColor;
	}
	
	public java.lang.String getBackUrl()
	{
		return this.backUrl;
	}
	
	public void setBackUrl(java.lang.String backUrl)
	{
		this.backUrl = backUrl;
	}
	
	public java.lang.Boolean getShowBackUrl()
	{
		return this.showBackUrl;
	}
	
	public void setShowBackUrl(java.lang.Boolean showBackUrl)
	{
		this.showBackUrl = showBackUrl;
	}
	
	public java.lang.String getBackRepeat()
	{
		return this.backRepeat;
	}
	
	public void setBackRepeat(java.lang.String backRepeat)
	{
		this.backRepeat = backRepeat;
	}
	
	public java.lang.String getBackPosition()
	{
		return this.backPosition;
	}
	
	public void setBackPosition(java.lang.String backPosition)
	{
		this.backPosition = backPosition;
	}
	
	public java.lang.String getSkinName()
	{
		return this.skinName;
	}
	
	public void setSkinName(java.lang.String skinName)
	{
		this.skinName = skinName;
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

