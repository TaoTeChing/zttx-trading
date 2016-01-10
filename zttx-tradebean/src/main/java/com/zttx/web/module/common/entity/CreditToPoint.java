/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * erp铺货变返点sku详细信息表 实体对象
 * <p>File：CreditToPoint.java</p>
 * <p>Title: CreditToPoint</p>
 * <p>Description:CreditToPoint</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class CreditToPoint extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商id*/
	private String brandId;
	/**经销商id*/
	private String dealerId;
	/**品牌id*/
	private String brandsId;
	/**产品id*/
	private String productId;
	/**产品skuid*/
	private String productSkuId;
	/**sku成本价*/
	private java.math.BigDecimal costPirce;
	/**erp基础库存量*/
	private Integer baseStock;
	/**成本合计*/
	private java.math.BigDecimal sumCost;
	/**返点价*/
	private java.math.BigDecimal pointPrice;
	/**返点比例*/
	private java.math.BigDecimal pointPercent;
	/**erp交易类型变更时间*/
	private Long erpTime;
	/**交易平台记录创建时间*/
	private Long createTime;


	/**查询参数**/
	private String brandsName;
	private String productTitle;
	private String productNo;

	public String getBrandsName() {
		return brandsName;
	}

	public void setBrandsName(String brandsName) {
		this.brandsName = brandsName;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	/**查询参数**/



	public String getBrandId()
	{
		return this.brandId;
	}

	public void setBrandId(String brandId)
	{
		this.brandId = brandId;
	}

	public String getDealerId()
	{
		return this.dealerId;
	}

	public void setDealerId(String dealerId)
	{
		this.dealerId = dealerId;
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

	public java.math.BigDecimal getCostPirce()
	{
		return this.costPirce;
	}

	public void setCostPirce(java.math.BigDecimal costPirce)
	{
		this.costPirce = costPirce;
	}

	public Integer getBaseStock()
	{
		return this.baseStock;
	}

	public void setBaseStock(Integer baseStock)
	{
		this.baseStock = baseStock;
	}

	public java.math.BigDecimal getSumCost()
	{
		return this.sumCost;
	}

	public void setSumCost(java.math.BigDecimal sumCost)
	{
		this.sumCost = sumCost;
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

