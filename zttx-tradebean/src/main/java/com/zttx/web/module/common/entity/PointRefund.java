/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 产品返点退货记录表 实体对象
 * <p>File：PointRefund.java</p>
 * <p>Title: PointRefund</p>
 * <p>Description:PointRefund</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class PointRefund extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商id*/
	private String dealerId;
	/**品牌商id*/
	private String brandId;
	/**品牌id*/
	private String brandsId;
	/**产品id*/
	private String productId;
	/**产品skuId*/
	private String productSkuId;
	/**退货数量*/
	private Integer refundNum;
	/**返点价*/
	private java.math.BigDecimal pointPrice;
	/**返点比例*/
	private java.math.BigDecimal pointPercent;
	/**erp退货记录生成时间*/
	private Long erpTime;
	/**交易平台记录创建时间*/
	private Long createTime;

	public String getDealerId()
	{
		return this.dealerId;
	}

	public void setDealerId(String dealerId)
	{
		this.dealerId = dealerId;
	}

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

	public String getProductId()
	{
		return this.productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	public String getProductSkuId()
	{
		return this.productSkuId;
	}

	public void setProductSkuId(String productSkuId)
	{
		this.productSkuId = productSkuId;
	}

	public Integer getRefundNum()
	{
		return this.refundNum;
	}

	public void setRefundNum(Integer refundNum)
	{
		this.refundNum = refundNum;
	}

	public java.math.BigDecimal getPointPrice()
	{
		return this.pointPrice;
	}

	public void setPointPrice(java.math.BigDecimal pointPrice)
	{
		this.pointPrice = pointPrice;
	}

	public java.math.BigDecimal getPointPercent()
	{
		return this.pointPercent;
	}

	public void setPointPercent(java.math.BigDecimal pointPercent)
	{
		this.pointPercent = pointPercent;
	}

	public Long getErpTime()
	{
		return this.erpTime;
	}

	public void setErpTime(Long erpTime)
	{
		this.erpTime = erpTime;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}
	
}

