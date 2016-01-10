/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 扣点表 实体对象
 * <p>File：BrandPointBalance.java</p>
 * <p>Title: BrandPointBalance</p>
 * <p>Description:BrandPointBalance</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandPointBalance extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌编号*/
	private java.lang.String brandsId;
	/**加盟方式：1 现款，2受信 ，3返点*/
	private java.lang.Short joinForm;
	/**扣点*/
	private java.math.BigDecimal point;
	/**创建时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getBrandsId()
	{
		return this.brandsId;
	}
	
	public void setBrandsId(java.lang.String brandsId)
	{
		this.brandsId = brandsId;
	}
	
	public java.lang.Short getJoinForm() {
		return joinForm;
	}

	public void setJoinForm(java.lang.Short joinForm) {
		this.joinForm = joinForm;
	}
	
	public java.math.BigDecimal getPoint()
	{
		return this.point;
	}
	
	public void setPoint(java.math.BigDecimal point)
	{
		this.point = point;
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

