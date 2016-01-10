/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;
import com.zttx.sdk.core.GenericEntity;

import java.math.BigDecimal;

/**
 * 经销商加盟信息 实体对象
 * <p>File：DealerJoin.java</p>
 * <p>Title: DealerJoin</p>
 * <p>Description:DealerJoin</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerJoin extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**品牌logo*/
	private java.lang.String logoName;
	/**logo域名*/
	private java.lang.String domainName;
	/**levelId*/
	private java.lang.String levelId;
	/**经销商区域编码*/
	private java.lang.Integer areaNo;
	/**加盟来源（1：平台线上申请加盟，2：平台线上邀请加盟，3：平台线下加盟，4：关系户加盟...）*/
	private java.lang.Short joinSource;
	/**加盟方式：0 现款，1受信*/
	private java.lang.Short joinForm;
	/**合作状态（1：正在合作，2：品牌商中止合作，3：经销商中止合作）*/
	private java.lang.Short joinState;
	/**实时受信金额*/
	private java.math.BigDecimal creditCurrent;
	/**当期应付款摊到授信额度中的金额*/
	private  java.math.BigDecimal clearingAmount;
	/**(加盟折扣数： 默认为1,小数为打折)*/
	private java.math.BigDecimal discount;
	/**受信金额*/
	private java.math.BigDecimal creditAmount;
	/**申请时间*/
	private java.lang.Long applyTime;
	/**加入时间*/
	private java.lang.Long joinTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**开始日期*/
	private java.lang.Long startTime;
	/**结束日期*/
	private java.lang.Long endTime;
	/**中止合作原因*/
	private java.lang.String endMark;
	/**累记进货次数*/
	private java.lang.Integer orderTime;
	/**累记进货数量*/
	private java.lang.Integer orderNum;
	/**累记进货金额*/
	private java.math.BigDecimal orderMoney;
	/**最后进货时间*/
	private java.lang.Long lastOrder;
	/**标志该经销商是否已加入品牌商的经销网络*/
	private java.lang.Boolean joinNet;
	/**是否已支付押金*/
	private java.lang.Boolean isPaid;
	/**授信押金百分比（押金 = 授信金额*授信押金百分比）*/
	private java.math.BigDecimal creditPaidPercent;
	/**已付押金金额*/
	private java.math.BigDecimal paidAmount;
	/**押金支付时间*/
	private java.lang.Long paidTime;
	/**品牌商给终端商设定的 押金额度*/
	private java.math.BigDecimal depositTotalAmount;
	/**平台给品牌已支付的押金*/
	private java.math.BigDecimal depositClearingAmount;
	/**已退押金金额*/
	private java.math.BigDecimal refundAmount;
	//是否返点
	private Boolean point;
	/**
	 *  经销商经营品类实体对象
	 */

	 /*========================================= 临时字段 [@author易永耀] begin================================================*/

	private String  brandsName;

	private String dealerName;
	   /*========================================= 临时字段 end ================================================*/

	 /*========================================= 临时字段get/set [@author易永耀] begin================================================*/

	public String getBrandsName() {
		return brandsName;
	}

	public void setBrandsName(String brandsName) {
		this.brandsName = brandsName;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	/*========================================= 临时字段get/set end ================================================*/

	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}

	public Boolean getPoint()
    {
        return point;
    }

    public void setPoint(Boolean point)
    {
        this.point = point;
    }

    public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
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

	public java.lang.String getLogoName()
	{
		return this.logoName;
	}

	public void setLogoName(java.lang.String logoName)
	{
		this.logoName = logoName;
	}

	public java.lang.String getDomainName()
	{
		return this.domainName;
	}

	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}

	public java.lang.String getLevelId()
	{
		return this.levelId;
	}

	public void setLevelId(java.lang.String levelId)
	{
		this.levelId = levelId;
	}

	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}

	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
	}

	public java.lang.Short getJoinSource()
	{
		return this.joinSource;
	}

	public void setJoinSource(java.lang.Short joinSource)
	{
		this.joinSource = joinSource;
	}

	public java.lang.Short getJoinForm()
	{
		return this.joinForm;
	}

	public void setJoinForm(java.lang.Short joinForm)
	{
		this.joinForm = joinForm;
	}

	public java.lang.Short getJoinState()
	{
		return this.joinState;
	}

	public void setJoinState(java.lang.Short joinState)
	{
		this.joinState = joinState;
	}

	public java.math.BigDecimal getCreditCurrent()
	{
		return this.creditCurrent;
	}

	public void setCreditCurrent(java.math.BigDecimal creditCurrent)
	{
		this.creditCurrent = creditCurrent;
	}

	public java.math.BigDecimal getDiscount()
	{
		return this.discount;
	}

	public void setDiscount(java.math.BigDecimal discount)
	{
		this.discount = discount;
	}

	public java.math.BigDecimal getCreditAmount()
	{
		return this.creditAmount;
	}

	public void setCreditAmount(java.math.BigDecimal creditAmount)
	{
		this.creditAmount = creditAmount;
	}

	public java.lang.Long getApplyTime()
	{
		return this.applyTime;
	}

	public void setApplyTime(java.lang.Long applyTime)
	{
		this.applyTime = applyTime;
	}

	public java.lang.Long getJoinTime()
	{
		return this.joinTime;
	}

	public void setJoinTime(java.lang.Long joinTime)
	{
		this.joinTime = joinTime;
	}

	public java.lang.Long getUpdateTime()
	{
		return this.updateTime;
	}

	public void setUpdateTime(java.lang.Long updateTime)
	{
		this.updateTime = updateTime;
	}

	public java.lang.Long getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(java.lang.Long startTime)
	{
		this.startTime = startTime;
	}

	public java.lang.Long getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(java.lang.Long endTime)
	{
		this.endTime = endTime;
	}

	public java.lang.String getEndMark()
	{
		return this.endMark;
	}

	public void setEndMark(java.lang.String endMark)
	{
		this.endMark = endMark;
	}

	public java.lang.Integer getOrderTime()
	{
		return this.orderTime;
	}

	public void setOrderTime(java.lang.Integer orderTime)
	{
		this.orderTime = orderTime;
	}

	public java.lang.Integer getOrderNum()
	{
		return this.orderNum;
	}

	public void setOrderNum(java.lang.Integer orderNum)
	{
		this.orderNum = orderNum;
	}

	public java.math.BigDecimal getOrderMoney()
	{
		return this.orderMoney;
	}

	public void setOrderMoney(java.math.BigDecimal orderMoney)
	{
		this.orderMoney = orderMoney;
	}

	public java.lang.Long getLastOrder()
	{
		return this.lastOrder;
	}

	public void setLastOrder(java.lang.Long lastOrder)
	{
		this.lastOrder = lastOrder;
	}

	public java.lang.Boolean getJoinNet()
	{
		return this.joinNet;
	}

	public void setJoinNet(java.lang.Boolean joinNet)
	{
		this.joinNet = joinNet;
	}

	public java.lang.Boolean getIsPaid()
	{
		return this.isPaid;
	}

	public void setIsPaid(java.lang.Boolean isPaid)
	{
		this.isPaid = isPaid;
	}

	public java.math.BigDecimal getCreditPaidPercent()
	{
		return this.creditPaidPercent;
	}

	public void setCreditPaidPercent(java.math.BigDecimal creditPaidPercent)
	{
		this.creditPaidPercent = creditPaidPercent;
	}

	public java.math.BigDecimal getPaidAmount()
	{
		return this.paidAmount;
	}

	public void setPaidAmount(java.math.BigDecimal paidAmount)
	{
		this.paidAmount = paidAmount;
	}

	public java.lang.Long getPaidTime()
	{
		return this.paidTime;
	}

	public void setPaidTime(java.lang.Long paidTime)
	{
		this.paidTime = paidTime;
	}

	public java.math.BigDecimal getDepositTotalAmount()
	{
		return this.depositTotalAmount;
	}

	public void setDepositTotalAmount(java.math.BigDecimal depositTotalAmount)
	{
		this.depositTotalAmount = depositTotalAmount;
	}

	public java.math.BigDecimal getDepositClearingAmount()
	{
		return this.depositClearingAmount;
	}

	public void setDepositClearingAmount(java.math.BigDecimal depositClearingAmount)
	{
		this.depositClearingAmount = depositClearingAmount;
	}

	public java.math.BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(java.math.BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}


	public BigDecimal getClearingAmount() {
		return clearingAmount;
	}

	public void setClearingAmount(BigDecimal clearingAmount) {
		this.clearingAmount = clearingAmount;
	}
}

