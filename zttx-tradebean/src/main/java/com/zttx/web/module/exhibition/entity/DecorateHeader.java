/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 展厅头部装修 实体对象
 * <p>File：DecorateHeader.java</p>
 * <p>Title: DecorateHeader</p>
 * <p>Description:DecorateHeader</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DecorateHeader extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**显示类型*/
	private java.lang.Integer showCate;
	/**企业名称*/
	private java.lang.String comName;
	/**是否显示企业名*/
	private java.lang.Boolean showName;
	/**图片所在服务器的域名*/
	private java.lang.String domainName;
	/**logo图片地址，名称*/
	private java.lang.String logoName;
	/**是否显示Logo*/
	private java.lang.Boolean showLogo;
	/**主营业务*/
	private java.lang.String mainDeal;
	/**公司名字体*/
	private java.lang.String nameFont;
	/**公司名字体大小*/
	private java.lang.String nameSize;
	/**公司名字体颜色*/
	private java.lang.String nameColor;
	/**主营业务字体*/
	private java.lang.String dealFont;
	/**主营业务字体大小*/
	private java.lang.String dealSize;
	/**主营业务字体颜色*/
	private java.lang.String dealColor;
	/**通栏外背景地址*/
	private java.lang.String outBackUrl;
	/**通栏内背景地址*/
	private java.lang.String inBackUrl;
	/**导航背景颜色*/
	private java.lang.String navDefaultColor;
	/**导航默认背景图*/
	private java.lang.String navDefaultUrl;
	/**导航变化背景色*/
	private java.lang.String navChangeColor;
	/**导航变化背景图*/
	private java.lang.String navChangeUrl;
	/**导航选中背景色*/
	private java.lang.String navSelectColor;
	/**导航文字默认色*/
	private java.lang.String navDefaultFont;
	/**导航文字变化色*/
	private java.lang.String navChangeFont;
	/**自定义头部说明*/
	private java.lang.String headerText;
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
	
	
	public java.lang.Integer getShowCate()
    {
        return showCate;
    }

    public void setShowCate(java.lang.Integer showCate)
    {
        this.showCate = showCate;
    }

    public java.lang.String getComName()
	{
		return this.comName;
	}
	
	public void setComName(java.lang.String comName)
	{
		this.comName = comName;
	}
	
	public java.lang.Boolean getShowName()
	{
		return this.showName;
	}
	
	public void setShowName(java.lang.Boolean showName)
	{
		this.showName = showName;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getLogoName()
	{
		return this.logoName;
	}
	
	public void setLogoName(java.lang.String logoName)
	{
		this.logoName = logoName;
	}
	
	public java.lang.Boolean getShowLogo()
	{
		return this.showLogo;
	}
	
	public void setShowLogo(java.lang.Boolean showLogo)
	{
		this.showLogo = showLogo;
	}
	
	public java.lang.String getMainDeal()
	{
		return this.mainDeal;
	}
	
	public void setMainDeal(java.lang.String mainDeal)
	{
		this.mainDeal = mainDeal;
	}
	
	public java.lang.String getNameFont()
	{
		return this.nameFont;
	}
	
	public void setNameFont(java.lang.String nameFont)
	{
		this.nameFont = nameFont;
	}
	
	public java.lang.String getNameSize()
	{
		return this.nameSize;
	}
	
	public void setNameSize(java.lang.String nameSize)
	{
		this.nameSize = nameSize;
	}
	
	public java.lang.String getNameColor()
	{
		return this.nameColor;
	}
	
	public void setNameColor(java.lang.String nameColor)
	{
		this.nameColor = nameColor;
	}
	
	public java.lang.String getDealFont()
	{
		return this.dealFont;
	}
	
	public void setDealFont(java.lang.String dealFont)
	{
		this.dealFont = dealFont;
	}
	
	public java.lang.String getDealSize()
	{
		return this.dealSize;
	}
	
	public void setDealSize(java.lang.String dealSize)
	{
		this.dealSize = dealSize;
	}
	
	public java.lang.String getDealColor()
	{
		return this.dealColor;
	}
	
	public void setDealColor(java.lang.String dealColor)
	{
		this.dealColor = dealColor;
	}
	
	public java.lang.String getOutBackUrl()
	{
		return this.outBackUrl;
	}
	
	public void setOutBackUrl(java.lang.String outBackUrl)
	{
		this.outBackUrl = outBackUrl;
	}
	
	public java.lang.String getInBackUrl()
	{
		return this.inBackUrl;
	}
	
	public void setInBackUrl(java.lang.String inBackUrl)
	{
		this.inBackUrl = inBackUrl;
	}
	
	public java.lang.String getNavDefaultColor()
	{
		return this.navDefaultColor;
	}
	
	public void setNavDefaultColor(java.lang.String navDefaultColor)
	{
		this.navDefaultColor = navDefaultColor;
	}
	
	public java.lang.String getNavDefaultUrl()
	{
		return this.navDefaultUrl;
	}
	
	public void setNavDefaultUrl(java.lang.String navDefaultUrl)
	{
		this.navDefaultUrl = navDefaultUrl;
	}
	
	public java.lang.String getNavChangeColor()
	{
		return this.navChangeColor;
	}
	
	public void setNavChangeColor(java.lang.String navChangeColor)
	{
		this.navChangeColor = navChangeColor;
	}
	
	public java.lang.String getNavChangeUrl()
	{
		return this.navChangeUrl;
	}
	
	public void setNavChangeUrl(java.lang.String navChangeUrl)
	{
		this.navChangeUrl = navChangeUrl;
	}
	
	public java.lang.String getNavSelectColor()
	{
		return this.navSelectColor;
	}
	
	public void setNavSelectColor(java.lang.String navSelectColor)
	{
		this.navSelectColor = navSelectColor;
	}
	
	public java.lang.String getNavDefaultFont()
	{
		return this.navDefaultFont;
	}
	
	public void setNavDefaultFont(java.lang.String navDefaultFont)
	{
		this.navDefaultFont = navDefaultFont;
	}
	
	public java.lang.String getNavChangeFont()
	{
		return this.navChangeFont;
	}
	
	public void setNavChangeFont(java.lang.String navChangeFont)
	{
		this.navChangeFont = navChangeFont;
	}
	
	public java.lang.String getHeaderText()
	{
		return this.headerText;
	}
	
	public void setHeaderText(java.lang.String headerText)
	{
		this.headerText = headerText;
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

