/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

import java.math.BigDecimal;

/**
 * 返点财务帐销售明细表 实体对象
 * <p>File：PointSaleDetail.java</p>
 * <p>Title: PointSaleDetail</p>
 * <p>Description:PointSaleDetail</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class PointSaleDetail extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商Id*/
	private String dealerId;
	/**品牌商Id*/
	private String brandId;
	/**品牌Id*/
	private String brandsId;
	/**产品Id*/
	private String productId;
	/**产品skuId*/
	private String productSkuId;
	/**销售数量*/
	private Integer saleNum;
	/**退货数量*/
	private Integer refundNum;
	/**销售金额*/
	private java.math.BigDecimal salePrice;
	/**退货金额*/
	private java.math.BigDecimal refundPrice;
	/**盘亏数量*/
	private Integer lossNum;
	/**盘亏成本*/
	private java.math.BigDecimal lossCost;
	/**销售成本*/
	private java.math.BigDecimal saleCost;
	/**退款成本*/
	private java.math.BigDecimal refundCost;
	/**合计成本*/
	private java.math.BigDecimal countCost;
	/**erp记录生成时间*/
	private Long erpTime;
	/**交易平台创建时间*/
	private Long createTime;

	/**查询调价**/
	private String productTitle;

	private String productNo;
	/**查询调价**/

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

	public Integer getSaleNum()
	{
		return this.saleNum;
	}

	public void setSaleNum(Integer saleNum)
	{
		this.saleNum = saleNum;
	}

	public Integer getRefundNum()
	{
		return this.refundNum;
	}

	public void setRefundNum(Integer refundNum)
	{
		this.refundNum = refundNum;
	}

	public java.math.BigDecimal getSalePrice()
	{
		return this.salePrice;
	}

	public void setSalePrice(java.math.BigDecimal salePrice)
	{
		this.salePrice = salePrice;
	}

	public java.math.BigDecimal getRefundPrice()
	{
		return this.refundPrice;
	}

	public void setRefundPrice(java.math.BigDecimal refundPrice)
	{
		this.refundPrice = refundPrice;
	}

	public Integer getLossNum()
	{
		return this.lossNum;
	}

	public void setLossNum(Integer lossNum)
	{
		this.lossNum = lossNum;
	}

	public java.math.BigDecimal getLossCost()
	{
		return this.lossCost;
	}

	public void setLossCost(java.math.BigDecimal lossCost)
	{
		this.lossCost = lossCost;
	}

	public java.math.BigDecimal getSaleCost()
	{
		return this.saleCost;
	}

	public void setSaleCost(java.math.BigDecimal saleCost)
	{
		this.saleCost = saleCost;
	}

	public java.math.BigDecimal getRefundCost()
	{
		return this.refundCost;
	}

	public void setRefundCost(java.math.BigDecimal refundCost)
	{
		this.refundCost = refundCost;
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

	public BigDecimal getCountCost() {
		return countCost;
	}

	public void setCountCost(BigDecimal countCost) {
		this.countCost = countCost;
	}
}

