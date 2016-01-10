/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.module.brand.entity.BrandBuySerLog;

/**
 * 支付记录表 实体对象
 * <p>File：DealerOrderPay.java</p>
 * <p>Title: DealerOrderPay</p>
 * <p>Description:DealerOrderPay</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerOrderPay extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**订单ID*/
	private java.lang.String orderId;
	/**订单号*/
	private java.lang.Long orderNo;
	/**支付ID*/
	private java.lang.Long payId;
	/**付款金额*/
	private java.math.BigDecimal payAmount;
	/**创建时间*/
	private java.lang.Long createTime;
	/**支付状态(0:未完成,1:已完成,2:已关闭,3:已退款)*/
	private java.lang.Short state;
	/**订单类型（0：订单，1：终端商购买服务，2：品牌商购买服务）*/
	private java.lang.Short orderType;
	/**佣金*/
	private java.math.BigDecimal pointBalance;
	/**未支付金额*/
	private java.math.BigDecimal unpayAmount;
	/**支付时间*/
	private java.lang.Long payTime;
	/**修改时间*/
	private java.lang.Long updateTime;
    
	private DealerOrder          dealerOrder;
    
    private DealerBuySerLog      dealerBuySerLog;
    
    private BrandBuySerLog       brandBuySerLog;
    
    public DealerBuySerLog getDealerBuySerLog()
    {
        return dealerBuySerLog;
    }
    
    public void setDealerBuySerLog(DealerBuySerLog dealerBuySerLog)
    {
        this.dealerBuySerLog = dealerBuySerLog;
    }
    
    public BrandBuySerLog getBrandBuySerLog()
    {
        return brandBuySerLog;
    }
    
    public void setBrandBuySerLog(BrandBuySerLog brandBuySerLog)
    {
        this.brandBuySerLog = brandBuySerLog;
    }
    
    public DealerOrder getDealerOrder()
    {
        return dealerOrder;
    }
    
    public void setDealerOrder(DealerOrder dealerOrder)
    {
        this.dealerOrder = dealerOrder;
    }
    
	public java.lang.String getOrderId()
	{
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String orderId)
	{
		this.orderId = orderId;
	}
	
	public java.lang.Long getOrderNo()
	{
		return this.orderNo;
	}
	
	public void setOrderNo(java.lang.Long orderNo)
	{
		this.orderNo = orderNo;
	}
	
	public java.lang.Long getPayId()
	{
		return this.payId;
	}
	
	public void setPayId(java.lang.Long payId)
	{
		this.payId = payId;
	}
	
	public java.math.BigDecimal getPayAmount()
	{
		return this.payAmount;
	}
	
	public void setPayAmount(java.math.BigDecimal payAmount)
	{
		this.payAmount = payAmount;
	}
	
	public java.lang.Long getCreateTime()
	{
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime)
	{
		this.createTime = createTime;
	}
	
	public java.lang.Short getState()
	{
		return this.state;
	}
	
	public void setState(java.lang.Short state)
	{
		this.state = state;
	}
	
	public java.lang.Short getOrderType()
	{
		return this.orderType;
	}
	
	public void setOrderType(java.lang.Short orderType)
	{
		this.orderType = orderType;
	}
	
	public java.math.BigDecimal getPointBalance()
	{
		return this.pointBalance;
	}
	
	public void setPointBalance(java.math.BigDecimal pointBalance)
	{
		this.pointBalance = pointBalance;
	}
	
	public java.math.BigDecimal getUnpayAmount()
	{
		return this.unpayAmount;
	}
	
	public void setUnpayAmount(java.math.BigDecimal unpayAmount)
	{
		this.unpayAmount = unpayAmount;
	}
	
	public java.lang.Long getPayTime()
	{
		return this.payTime;
	}
	
	public void setPayTime(java.lang.Long payTime)
	{
		this.payTime = payTime;
	}
    public java.lang.Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.lang.Long updateTime) {
		this.updateTime = updateTime;
	}
}

