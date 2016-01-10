/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 产品所属分类 实体对象
 * <p>File：ProductCate.java</p>
 * <p>Title: ProductCate</p>
 * <p>Description:ProductCate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductCate extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**分类编号*/
	private String cateId;
	/**产品编号*/
	private String productId;
	/**建档时间*/
	private Long createTime;
	/**最后修改时间*/
	private Long updateTime;

	public String getCateId()
	{
		return this.cateId;
	}

	public void setCateId(String cateId)
	{
		this.cateId = cateId;
	}

	public String getProductId()
	{
		return this.productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
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

