/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

import java.math.BigDecimal;

/**
 * 经销商订单项信息 实体对象
 * <p>File：DealerOrders.java</p>
 * <p>Title: DealerOrders</p>
 * <p>Description:DealerOrders</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerOrders extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**订单编号*/
	private java.lang.String orderId;
	/**经销商编号*/
	private java.lang.String dealerId;
	/**产品线编号*/
	private java.lang.String lineId;
	/**折扣值*/
	private java.math.BigDecimal agio;
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**商品编号*/
	private java.lang.String productId;
	/**产品标题*/
	private java.lang.String productTitle;
	/**产品货号*/
	private java.lang.String productNo;
	/**产品主图*/
	private java.lang.String productImage;
	/**SKU主键*/
	private java.lang.String productSkuId;
	/**SKU 编码*/
	private java.lang.String productSkuCode;
	/**SKU 名称*/
	private java.lang.String productSkuName;
	/**操作时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**属性值*/
	private java.lang.String productAttrbute;
	/**商品单价*/
	private java.math.BigDecimal price;
    
    /**授信订单调价后保存的调价前的单价**/
    private java.math.BigDecimal oldPrice;
    
    /**todo 品牌商修改金额后的订单项总金额,不是修改后的单价;字段名称有歧义,后续会修改字段名称为adjustAmount**/
    private java.math.BigDecimal adjustPrice;
	/**购买数量*/
	private java.lang.Integer quantity;
	/**已发货数量*/
	private java.lang.Integer shipCount;
	/**授信折扣*/
	private java.math.BigDecimal discount;
	/**总授信折扣价*/
	private java.math.BigDecimal discountPrice;
	/**itemState（订单项状态）
            0：订单项已关闭
            1：正常状态
            2：退款中*/
	private java.lang.Short itemState;
	/**经销商同步时间*/
	private java.lang.Long synchTime;
	/**总金额*/
	private java.math.BigDecimal totalAmount;
	/**扣点比例*/
	private java.math.BigDecimal point;
	/**扣点金额*/
	private java.math.BigDecimal pointAmount;

	 /*========================================= 临时字段 [@author易永耀] begin================================================*/
	  private  BigDecimal  realProductSkuPrice;   //给erp sku 真实单价 com.zttx.web.dubbo.service.DealerOrderDubboServiceImpl.search()

	  private BigDecimal   realProductSkuTotalPrice; // 给erp  sku 购买总数量的真实价格   com.zttx.web.dubbo.service.DealerOrderDubboServiceImpl.search()
	  
	/**返点比例*/
    private java.math.BigDecimal pointPercent;

	/**订单号*/
	private Long   orderNo;
    /**每一次的发货量*/
	private int sendNumEvery;

	public int getSendNumEvery() {
		return sendNumEvery;
	}

	public void setSendNumEvery(int sendNumEvery) {
		this.sendNumEvery = sendNumEvery;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getRealProductSkuTotalPrice() {
		return realProductSkuTotalPrice;
	}

	public void setRealProductSkuTotalPrice(BigDecimal realProductSkuTotalPrice) {
		this.realProductSkuTotalPrice = realProductSkuTotalPrice;
	}

	public BigDecimal getRealProductSkuPrice() {
		return realProductSkuPrice;
	}

	public void setRealProductSkuPrice(BigDecimal realProductSkuPrice) {
		this.realProductSkuPrice = realProductSkuPrice;
	}



	/*========================================= 临时字段 end ================================================*/
	public java.lang.String getOrderId()
	{
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String orderId)
	{
		this.orderId = orderId;
	}
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getLineId()
	{
		return this.lineId;
	}
	
	public void setLineId(java.lang.String lineId)
	{
		this.lineId = lineId;
	}
	
	public java.math.BigDecimal getAgio()
	{
		return this.agio;
	}
	
	public void setAgio(java.math.BigDecimal agio)
	{
		this.agio = agio;
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
	
	public java.lang.String getProductId()
	{
		return this.productId;
	}
	
	public void setProductId(java.lang.String productId)
	{
		this.productId = productId;
	}
	
	public java.lang.String getProductTitle()
	{
		return this.productTitle;
	}
	
	public void setProductTitle(java.lang.String productTitle)
	{
		this.productTitle = productTitle;
	}
	
	public java.lang.String getProductNo()
	{
		return this.productNo;
	}
	
	public void setProductNo(java.lang.String productNo)
	{
		this.productNo = productNo;
	}
	
	public java.lang.String getProductImage()
	{
		return this.productImage;
	}
	
	public void setProductImage(java.lang.String productImage)
	{
		this.productImage = productImage;
	}
	
	public java.lang.String getProductSkuId()
	{
		return this.productSkuId;
	}
	
	public void setProductSkuId(java.lang.String productSkuId)
	{
		this.productSkuId = productSkuId;
	}
	
	public java.lang.String getProductSkuCode()
	{
		return this.productSkuCode;
	}
	
	public void setProductSkuCode(java.lang.String productSkuCode)
	{
		this.productSkuCode = productSkuCode;
	}
	
	public java.lang.String getProductSkuName()
	{
		return this.productSkuName;
	}
	
	public void setProductSkuName(java.lang.String productSkuName)
	{
		this.productSkuName = productSkuName;
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
	
	public java.lang.String getProductAttrbute()
	{
		return this.productAttrbute;
	}
	
	public void setProductAttrbute(java.lang.String productAttrbute)
	{
		this.productAttrbute = productAttrbute;
	}
	
	public java.math.BigDecimal getPrice()
	{
		return this.price;
	}
	
	public void setPrice(java.math.BigDecimal price)
	{
		this.price = price;
	}
	
	public java.lang.Integer getQuantity()
	{
		return this.quantity;
	}
	
	public void setQuantity(java.lang.Integer quantity)
	{
		this.quantity = quantity;
	}
	
	public java.lang.Integer getShipCount()
	{
		return this.shipCount;
	}
	
	public void setShipCount(java.lang.Integer shipCount)
	{
		this.shipCount = shipCount;
	}
	
	public java.math.BigDecimal getDiscount()
	{
		return this.discount;
	}
	
	public void setDiscount(java.math.BigDecimal discount)
	{
		this.discount = discount;
	}
	
	public java.lang.Short getItemState()
	{
		return this.itemState;
	}
	
	public void setItemState(java.lang.Short itemState)
	{
		this.itemState = itemState;
	}
	
	public java.lang.Long getSynchTime()
	{
		return this.synchTime;
	}
	
	public void setSynchTime(java.lang.Long synchTime)
	{
		this.synchTime = synchTime;
	}
	
	public java.math.BigDecimal getTotalAmount()
	{
		return this.totalAmount;
	}
	
	public void setTotalAmount(java.math.BigDecimal totalAmount)
	{
		this.totalAmount = totalAmount;
	}
	
	public java.math.BigDecimal getPoint()
	{
		return this.point;
	}
	
	public void setPoint(java.math.BigDecimal point)
	{
		this.point = point;
	}
	
	public java.math.BigDecimal getPointAmount()
	{
		return this.pointAmount;
	}
	
	public void setPointAmount(java.math.BigDecimal pointAmount)
	{
		this.pointAmount = pointAmount;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
    
    public BigDecimal getAdjustPrice()
    {
        return adjustPrice;
    }
    
    public void setAdjustPrice(BigDecimal adjustPrice)
    {
        this.adjustPrice = adjustPrice;
    }
    
    public BigDecimal getOldPrice()
    {
        return oldPrice;
    }
    
    public void setOldPrice(BigDecimal oldPrice)
    {
        this.oldPrice = oldPrice;
    }
    
	public java.math.BigDecimal getPointPercent() {
		return pointPercent;
	}

	public void setPointPercent(java.math.BigDecimal pointPercent) {
		this.pointPercent = pointPercent;
	}
}

