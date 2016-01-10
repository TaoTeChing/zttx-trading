/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.consts.ApplicationConst;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 产品库存调整记录表（该表由调度执行生成） 实体对象
 * <p>File：StockAdjustDetail.java</p>
 * <p>Title: StockAdjustDetail</p>
 * <p>Description:StockAdjustDetail</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class StockAdjustDetail extends GenericEntity {
	
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
    /**采购单号（source=0）*/
	private Long orderNo;
	/**erp铺货变返点基础库存*/
	private Integer baseStock;
	/**发货数量*/
	private Integer sendNum;
	/**销售数量*/
	private Integer saleNum;
	/**退货数量*/
	private Integer refundNum;
	/**库存变更类型(数据来源)：0采购，1销售，2退货，3铺货变返点*/
	private Short source;
	/**记录创建时间（来源于其他数据）*/
	private Long createTime;

	/**查询参数**/
	@DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
	private Date  startTime;
	private Long  startTimeLong;
	@DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
	private Date  endTime;
	private Long endTimeLong;

	private String productTitle;
	private String productNo;

	/**插入表数据时用到的常量*/
	public static final int TYPE_SEND = 0;   //发货
	public static final int TYPE_SALE = 1;    //销售
	public static final int TYPE_REFUND = 2;  //退货
	public static final int TYPE_CREDITTOPOINT = 3;  //授信转返点


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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Long getStartTimeLong() {
		return startTimeLong;
	}

	public void setStartTimeLong(Long startTimeLong) {
		this.startTimeLong = startTimeLong;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getEndTimeLong() {
		return endTimeLong;
	}

	public void setEndTimeLong(Long endTimeLong) {
		this.endTimeLong = endTimeLong;
	}

	/**查询参数**/



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

	public Integer getBaseStock()
	{
		return this.baseStock;
	}

	public void setBaseStock(Integer baseStock)
	{
		this.baseStock = baseStock;
	}

	public Integer getSendNum()
	{
		return this.sendNum;
	}

	public void setSendNum(Integer sendNum)
	{
		this.sendNum = sendNum;
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

	public Short getSource()
	{
		return this.source;
	}

	public void setSource(Short source)
	{
		this.source = source;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}

