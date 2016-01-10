/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 产品分类信息 实体对象
 * <p>File：ProductCatalog.java</p>
 * <p>Title: ProductCatalog</p>
 * <p>Description:ProductCatalog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductCatalog extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private String brandId;
	/**品牌编号*/
	private String brandsId;
	/**分类名称*/
	private String cateName;
	/**排序编号*/
	private Integer cateOrder;
	/**上层分类*/
	private String parentId;
	/**分类级别*/
	private Short cateLevel;
	/**图标域名*/
	private String domainName;
	/**分类图标*/
	private String cateIcon;
	/**产品数量*/
	private Integer productNum;
	/**建档时间*/
	private Long createTime;
	/**最后修改时间*/
	private Long updateTime;

	public String getBrandId()
	{
		return this.brandId;
	}

	public void setBrandId(String brandId)
	{
		this.brandId = brandId;
	}

	public String getBrandsId()
	{
		return this.brandsId;
	}

	public void setBrandsId(String brandsId)
	{
		this.brandsId = brandsId;
	}

	public String getCateName()
	{
		return this.cateName;
	}

	public void setCateName(String cateName)
	{
		this.cateName = cateName;
	}

	public Integer getCateOrder()
	{
		return this.cateOrder;
	}

	public void setCateOrder(Integer cateOrder)
	{
		this.cateOrder = cateOrder;
	}

	public String getParentId()
	{
		return this.parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public Short getCateLevel()
	{
		return this.cateLevel;
	}

	public void setCateLevel(Short cateLevel)
	{
		this.cateLevel = cateLevel;
	}

	public String getDomainName()
	{
		return this.domainName;
	}

	public void setDomainName(String domainName)
	{
		this.domainName = domainName;
	}

	public String getCateIcon()
	{
		return this.cateIcon;
	}

	public void setCateIcon(String cateIcon)
	{
		this.cateIcon = cateIcon;
	}

	public Integer getProductNum()
	{
		return this.productNum;
	}

	public void setProductNum(Integer productNum)
	{
		this.productNum = productNum;
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

