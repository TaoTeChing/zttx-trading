/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.entity;

import java.util.List;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.module.exhibition.model.MenuJsonModel;

/**
 * 展厅装修菜单 实体对象
 * <p>File：DecorateMenu.java</p>
 * <p>Title: DecorateMenu</p>
 * <p>Description:DecorateMenu</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DecorateMenu extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**菜单json组合*/
	private java.lang.String menuValue;
	/**建档时间*/
	private java.lang.Long createTime;
	/**最后修改时间*/
	private java.lang.Long updateTime;
	
	private List<MenuJsonModel> menus;
	
	public DecorateMenu(String brandId, String brandsId, Boolean delFlag, String menuValue)
    {
        this.brandId = brandId;
        this.brandsId = brandsId;
        this.delFlag = delFlag;
        this.menuValue = menuValue;
    }
	
	public DecorateMenu(){}
	
	public List<MenuJsonModel> getMenus()
    {
        return menus;
    }

    public void setMenus(List<MenuJsonModel> menus)
    {
        this.menus = menus;
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
	
	public java.lang.String getMenuValue()
	{
		return this.menuValue;
	}
	
	public void setMenuValue(java.lang.String menuValue)
	{
		this.menuValue = menuValue;
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

