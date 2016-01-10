/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.utils.CalendarUtils;

/**
 * 经销商订单信息 实体对象
 * <p>File：DealerOrder.java</p>
 * <p>Title: DealerOrder</p>
 * <p>Description:DealerOrder</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerOrder extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**订单编号*/
	private java.lang.Long orderId;
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**产品体积*/
	private java.math.BigDecimal productBulk;
	/**产品重量*/
	private java.math.BigDecimal productWeight;
	/**产品价格*/
	private java.math.BigDecimal productPrice;
    
    /**付款时订单即时总货款,调价时不改变**/
    private java.math.BigDecimal      productPriceWhenPay;
	/**总购买数量*/
	private java.lang.Integer productCount;
	/**是否是预付订单*/
	private java.lang.Boolean isAdvancePayment;
	/**订单支付百分比*/
	private java.lang.Integer orderMoney;
	/**已经发货数量*/
	private java.lang.Integer shipCount;
	/**receiveCount*/
	private java.lang.Integer receiveCount;
	/**优惠价格*/
	private java.math.BigDecimal adjustPrice;
	/**运费*/
	private java.math.BigDecimal freight;
	/**调整后的运费*/
	private java.math.BigDecimal adjustFreight;
	/**运费支付状态0：未支付1：已支付*/
	private java.lang.Short frePayState;
	/**支付金额*/
	private java.math.BigDecimal payment;
	/**支付状态0：未支付1：部分支付2：全部支付*/
	private java.lang.Short payState;
	/**收货人姓名*/
	private java.lang.String shipName;
	/**收货人所在地区*/
	private java.lang.Integer areaNo;
	/**收货人所在地区*/
	private java.lang.String dealerAddrProvince;
	/**收货人所在地区*/
	private java.lang.String dealerAddrCity;
	/**收货人所在地区*/
	private java.lang.String dealerAddrArea;
	/**收货人街道地址*/
	private java.lang.String dealerAddress;
	/**邮政编码*/
	private java.lang.String postCode;
	/**手机号码*/
	private java.lang.String dealerMobile;
	/**电话号码*/
	private java.lang.String dealerTel;
	/**remark*/
	private java.lang.String remark;
	/**星级,1-5*/
	private java.lang.Short levelMark;
	/**品牌商备注*/
	private java.lang.String brandRemark;
	/**操作时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**下一个操作时间*/
	private java.lang.Long outActTime;
	/**1等待付款
2 等待发货
3:部分发货
4 等待确认收货
9 交易成功
10 交易关闭*/
	private java.lang.Short orderStatus;
	/**退款状态  1：申请退款等待处理2：同意退货等待发货 3：退货已发货  4：拒绝退款5：拒绝退货   6：退款关闭 7: 取消退款9：同意退款 10：同意退货退款
      */
	private java.lang.Short refundStatus;
	/**客服介入处理状态1：客服介入处理中，2：纠纷处理中，3：处理完毕,4:纠纷关闭*/
	private java.lang.Short serProStatus;
	/**投诉状态（0：等待处理，1：客服介入，2：处理完成，3：经销商撤消投诉）*/
	private java.lang.Short complaintState;
	/**冗余的订单明细信息，使用json保存最新十笔订单明细*/
	private java.lang.String orderItem;
	/**订单来源（0：交易平台，1：支撑系统）*/
	private java.lang.Short sourceState;
	/**来源ID*/
	private java.lang.String sourceId;
	/**订单活动*/
	private java.lang.String activitiesNo;
	/**最低扣点比例*/
	private java.math.BigDecimal minPoint;
	/**订单类型：1、现款订单  2、授信订单 3、返点订单*/
	private java.lang.Short dealerOrderType;
	/**已扣的佣金*/
	private java.math.BigDecimal pointBalanceAmount;
	/**运费支付方式：1、快递 2、物流 3、快递到付 4、物流到付 60、包邮*/
	private java.lang.Short frePayType;
	/**结算状态*/
	private java.lang.Boolean clearingStatus;
	/**结算金额*/
	private java.math.BigDecimal clearingAmount;
	/**抵扣金额*/
	private java.math.BigDecimal offSetAmount;
    
    /**未发货产品货款**/
    private java.math.BigDecimal noSendGoodsAmount;
	/**拿样订单*/
	private  Boolean    isSampleOrder;




	 /*========================================= 临时字段 [@author易永耀] begin================================================*/
    private Integer orderMuiltStatus; //订单过滤： 0 所有订单 1  2 3 4 5 6 7 8 9

	private String productTitle;
	@DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
	private Date      startTime;
	@DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
	private Date      endTime;

	private String brandsName;

	private BigDecimal discount; //授信订单的折扣

	private List<DealerOrders> ordersList = Lists.newArrayList();

	private String type;

	private String brandName;

	private String dealerName;

	private BigDecimal creditCurrent; //实时授信金额

	private BigDecimal  payCash ;   //授信额度不足，应该付的现金

	private Boolean isPaid;    //是否授信额度不足
	   /*========================================= 临时字段 end ================================================*/

	private OrderShipRecord orderShipRecord;
	
	private Boolean orderpaystatus;

	private DealerComplaint dealerComplaint;
	
	private Boolean complainable;

	//产品
	private List<Map<String, Object>> items;

	
	private String productName;
	
	private String productNo;
	
	private Long startTimeLong;
	
	private Long endTimeLong;

    public BigDecimal getProductPriceWhenPay()
    {
        return productPriceWhenPay;
    }
    
    public void setProductPriceWhenPay(BigDecimal productPriceWhenPay)
    {
        this.productPriceWhenPay = productPriceWhenPay;
    }
    
	public BigDecimal getCreditCurrent() {
		return creditCurrent;
	}

	public void setCreditCurrent(BigDecimal creditCurrent) {
		this.creditCurrent = creditCurrent;
	}

	public BigDecimal getPayCash() {
		return payCash;
	}

	public void setPayCash(BigDecimal payCash) {
		this.payCash = payCash;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Long getStartTimeLong()
    {
        return startTimeLong;
    }

    public void setStartTimeLong(Long startTimeLong)
    {
        this.startTimeLong = startTimeLong;
    }

    public Long getEndTimeLong()
    {
        return endTimeLong;
    }

    public void setEndTimeLong(Long endTimeLong)
    {
        this.endTimeLong = endTimeLong;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductNo()
    {
        return productNo;
    }

    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }

    public List<Map<String, Object>> getItems()
    {
        return items;
    }

    public void setItems(List<Map<String, Object>> items)
    {
        this.items = items;
    }

    public Boolean getComplainable()
    {
        return complainable;
    }

    public void setComplainable(Boolean complainable)
    {
        this.complainable = complainable;
    }

    public DealerComplaint getDealerComplaint()
    {
        return dealerComplaint;
    }

    public void setDealerComplaint(DealerComplaint dealerComplaint)
    {
        this.dealerComplaint = dealerComplaint;
    }

    public Boolean getOrderpaystatus()
    {
        return orderpaystatus;
    }

    public void setOrderpaystatus(Boolean orderpaystatus)
    {
        this.orderpaystatus = orderpaystatus;
    }

    public OrderShipRecord getOrderShipRecord()
    {
        return orderShipRecord;
    }

    public void setOrderShipRecord(OrderShipRecord orderShipRecord)
    {
        this.orderShipRecord = orderShipRecord;
    }

    public List<DealerOrders> getOrdersList()
    {
        return ordersList;
    }

    public void setOrdersList(List<DealerOrders> ordersList)
    {
        this.ordersList = ordersList;
    }

    public java.lang.Long getOrderId()
	{
		return this.orderId;
	}
	
	public void setOrderId(java.lang.Long orderId)
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
	
	public java.math.BigDecimal getProductBulk()
	{
		return this.productBulk;
	}
	
	public void setProductBulk(java.math.BigDecimal productBulk)
	{
		this.productBulk = productBulk;
	}
	
	public java.math.BigDecimal getProductWeight()
	{
		return this.productWeight;
	}
	
	public void setProductWeight(java.math.BigDecimal productWeight)
	{
		this.productWeight = productWeight;
	}
	
	public java.math.BigDecimal getProductPrice()
	{
		return this.productPrice;
	}
	
	public void setProductPrice(java.math.BigDecimal productPrice)
	{
		this.productPrice = productPrice;
	}
	
	public java.lang.Integer getProductCount()
	{
		return this.productCount;
	}
	
	public void setProductCount(java.lang.Integer productCount)
	{
		this.productCount = productCount;
	}
	
	public java.lang.Boolean getIsAdvancePayment()
	{
		return this.isAdvancePayment;
	}
	
	public void setIsAdvancePayment(java.lang.Boolean isAdvancePayment)
	{
		this.isAdvancePayment = isAdvancePayment;
	}
	
	public java.lang.Integer getOrderMoney()
	{
		return this.orderMoney;
	}
	
	public void setOrderMoney(java.lang.Integer orderMoney)
	{
		this.orderMoney = orderMoney;
	}
	
	public java.lang.Integer getShipCount()
	{
		return this.shipCount;
	}
	
	public void setShipCount(java.lang.Integer shipCount)
	{
		this.shipCount = shipCount;
	}
	
	public java.lang.Integer getReceiveCount()
	{
		return this.receiveCount;
	}
	
	public void setReceiveCount(java.lang.Integer receiveCount)
	{
		this.receiveCount = receiveCount;
	}
	
	public java.math.BigDecimal getAdjustPrice()
	{
		return this.adjustPrice;
	}
	
	public void setAdjustPrice(java.math.BigDecimal adjustPrice)
	{
		this.adjustPrice = adjustPrice;
	}
	
	public java.math.BigDecimal getFreight()
	{
		return this.freight;
	}
	
	public void setFreight(java.math.BigDecimal freight)
	{
		this.freight = freight;
	}
	
	public java.math.BigDecimal getAdjustFreight()
	{
		return this.adjustFreight;
	}
	
	public void setAdjustFreight(java.math.BigDecimal adjustFreight)
	{
		this.adjustFreight = adjustFreight;
	}
	
	public java.lang.Short getFrePayState()
	{
		return this.frePayState;
	}
	
	public void setFrePayState(java.lang.Short frePayState)
	{
		this.frePayState = frePayState;
	}
	
	public java.math.BigDecimal getPayment()
	{
		return this.payment;
	}
	
	public void setPayment(java.math.BigDecimal payment)
	{
		this.payment = payment;
	}
	
	public java.lang.Short getPayState()
	{
		return this.payState;
	}
	
	public void setPayState(java.lang.Short payState)
	{
		this.payState = payState;
	}
	
	public java.lang.String getShipName()
	{
		return this.shipName;
	}
	
	public void setShipName(java.lang.String shipName)
	{
		this.shipName = shipName;
	}
	
	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}
	
	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
	}
	
	public java.lang.String getDealerAddrProvince()
	{
		return this.dealerAddrProvince;
	}
	
	public void setDealerAddrProvince(java.lang.String dealerAddrProvince)
	{
		this.dealerAddrProvince = dealerAddrProvince;
	}
	
	public java.lang.String getDealerAddrCity()
	{
		return this.dealerAddrCity;
	}
	
	public void setDealerAddrCity(java.lang.String dealerAddrCity)
	{
		this.dealerAddrCity = dealerAddrCity;
	}
	
	public java.lang.String getDealerAddrArea()
	{
		return this.dealerAddrArea;
	}
	
	public void setDealerAddrArea(java.lang.String dealerAddrArea)
	{
		this.dealerAddrArea = dealerAddrArea;
	}
	
	public java.lang.String getDealerAddress()
	{
		return this.dealerAddress;
	}
	
	public void setDealerAddress(java.lang.String dealerAddress)
	{
		this.dealerAddress = dealerAddress;
	}
	
	public java.lang.String getPostCode()
	{
		return this.postCode;
	}
	
	public void setPostCode(java.lang.String postCode)
	{
		this.postCode = postCode;
	}
	
	public java.lang.String getDealerMobile()
	{
		return this.dealerMobile;
	}
	
	public void setDealerMobile(java.lang.String dealerMobile)
	{
		this.dealerMobile = dealerMobile;
	}
	
	public java.lang.String getDealerTel()
	{
		return this.dealerTel;
	}
	
	public void setDealerTel(java.lang.String dealerTel)
	{
		this.dealerTel = dealerTel;
	}
	
	public java.lang.String getRemark()
	{
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark)
	{
		this.remark = remark;
	}
	
	public java.lang.Short getLevelMark()
	{
		return this.levelMark;
	}
	
	public void setLevelMark(java.lang.Short levelMark)
	{
		this.levelMark = levelMark;
	}
	
	public java.lang.String getBrandRemark()
	{
		return this.brandRemark;
	}
	
	public void setBrandRemark(java.lang.String brandRemark)
	{
		this.brandRemark = brandRemark;
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
	
	public java.lang.Long getOutActTime()
	{
		return this.outActTime;
	}
	
	public void setOutActTime(java.lang.Long outActTime)
	{
		this.outActTime = outActTime;
	}
	
	public java.lang.Short getOrderStatus()
	{
		return this.orderStatus;
	}
	
	public void setOrderStatus(java.lang.Short orderStatus)
	{
		this.orderStatus = orderStatus;
	}
	
	public java.lang.Short getRefundStatus()
	{
		return this.refundStatus;
	}
	
	public void setRefundStatus(java.lang.Short refundStatus)
	{
		this.refundStatus = refundStatus;
	}
	
	public java.lang.Short getSerProStatus()
	{
		return this.serProStatus;
	}
	
	public void setSerProStatus(java.lang.Short serProStatus)
	{
		this.serProStatus = serProStatus;
	}
	
	public java.lang.Short getComplaintState()
	{
		return this.complaintState;
	}
	
	public void setComplaintState(java.lang.Short complaintState)
	{
		this.complaintState = complaintState;
	}
	
	public java.lang.String getOrderItem()
	{
		return this.orderItem;
	}
	
	public void setOrderItem(java.lang.String orderItem)
	{
		this.orderItem = orderItem;
	}
	
	public java.lang.Short getSourceState()
	{
		return this.sourceState;
	}
	
	public void setSourceState(java.lang.Short sourceState)
	{
		this.sourceState = sourceState;
	}
	
	public java.lang.String getSourceId()
	{
		return this.sourceId;
	}
	
	public void setSourceId(java.lang.String sourceId)
	{
		this.sourceId = sourceId;
	}
	
	public java.lang.String getActivitiesNo()
	{
		return this.activitiesNo;
	}
	
	public void setActivitiesNo(java.lang.String activitiesNo)
	{
		this.activitiesNo = activitiesNo;
	}
	
	public java.math.BigDecimal getMinPoint()
	{
		return this.minPoint;
	}
	
	public void setMinPoint(java.math.BigDecimal minPoint)
	{
		this.minPoint = minPoint;
	}
	
	public java.lang.Short getDealerOrderType()
	{
		return this.dealerOrderType;
	}
	
	public void setDealerOrderType(java.lang.Short dealerOrderType)
	{
		this.dealerOrderType = dealerOrderType;
	}
	
	public java.math.BigDecimal getPointBalanceAmount()
	{
		return this.pointBalanceAmount;
	}
	
	public void setPointBalanceAmount(java.math.BigDecimal pointBalanceAmount)
	{
		this.pointBalanceAmount = pointBalanceAmount;
	}
	
	public java.lang.Short getFrePayType()
	{
		return this.frePayType;
	}
	
	public void setFrePayType(java.lang.Short frePayType)
	{
		this.frePayType = frePayType;
	}
	
	public java.lang.Boolean getClearingStatus()
	{
		return this.clearingStatus;
	}
	
	public void setClearingStatus(java.lang.Boolean clearingStatus)
	{
		this.clearingStatus = clearingStatus;
	}
	
	public java.math.BigDecimal getClearingAmount()
	{
		return this.clearingAmount;
	}
	
	public void setClearingAmount(java.math.BigDecimal clearingAmount)
	{
		this.clearingAmount = clearingAmount;
	}
	
	public java.math.BigDecimal getOffSetAmount()
	{
		return this.offSetAmount;
	}
	
	public void setOffSetAmount(java.math.BigDecimal offSetAmount)
	{
		this.offSetAmount = offSetAmount;
	}

    public BigDecimal getNoSendGoodsAmount()
    {
        return noSendGoodsAmount;
    }
    
    public void setNoSendGoodsAmount(BigDecimal noSendGoodsAmount)
    {
        this.noSendGoodsAmount = noSendGoodsAmount;
    }

	public Boolean getIsSampleOrder() {
		return isSampleOrder;
	}

	public void setIsSampleOrder(Boolean isSampleOrder) {
		this.isSampleOrder = isSampleOrder;
	}


	/*========================================= 临时字段get/set [@author易永耀] begin================================================*/

	public Integer getOrderMuiltStatus() {
		return orderMuiltStatus;
	}

	public void setOrderMuiltStatus(Integer orderMuiltStatus) {
		this.orderMuiltStatus = orderMuiltStatus;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getBrandsName() {
		return brandsName;
	}

	public void setBrandsName(String brandsName) {
		this.brandsName = brandsName;
	}

	public String getStartTimeStr()
	{
		return this.startTime == null ? "" : CalendarUtils.getDate(this.startTime, ApplicationConst.DATE_FORMAT_YMD);
	}

	public String getEndTimeStr()
	{
		return this.endTime == null ? "" : String.valueOf(CalendarUtils.addDay(this.endTime, 1));
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
    /*========================================= 临时字段get/set end ================================================*/
}

