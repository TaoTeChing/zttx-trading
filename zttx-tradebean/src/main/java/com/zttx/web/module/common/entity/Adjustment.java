/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import java.util.List;

import com.zttx.sdk.core.GenericEntity;

/**
 * 调价信息主表 实体对象
 * <p>File：Adjustment.java</p>
 * <p>Title: Adjustment</p>
 * <p>Description:Adjustment</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class Adjustment extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商Id*/
	private java.lang.String brandId;
	/**经销商Id*/
	private java.lang.String dealerId;
	/**批量调价价格总计*/
	private java.math.BigDecimal adjustAllPrice;
	/**调价创建时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	//价格调整子记录
	private List<Adjustments> adjustmentsList;

	public List<Adjustments> getAdjustmentsList()
    {
        return adjustmentsList;
    }

    public void setAdjustmentsList(List<Adjustments> adjustmentsList)
    {
        this.adjustmentsList = adjustmentsList;
    }

    public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.math.BigDecimal getAdjustAllPrice()
	{
		return this.adjustAllPrice;
	}
	
	public void setAdjustAllPrice(java.math.BigDecimal adjustAllPrice)
	{
		this.adjustAllPrice = adjustAllPrice;
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

