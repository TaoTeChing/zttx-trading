/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 交易明细表 实体对象
 * <p>File：TradeDetails.java</p>
 * <p>Title: TradeDetails</p>
 * <p>Description:TradeDetails</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class TradeDetails extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**订单编号*/
	private String orderId;
	/**交易流水号*/
	private String serialNumber;
	/**出款方编号*/
	private String sendUserId;
	/**出款方名称*/
	private String sendName;
	/**收款方编号*/
	private String recUserId;
	/**收款方名称*/
	private String recName;
	/**出款金额*/
	private java.math.BigDecimal balance;
	/**交易内容(如：订单商品标题）*/
	private String title;
	/**交易类型
            1：订单支付
            2：短信支付*/
	private Short tradeType;
	/**支付状态
0：未支付
1：部分支付
2：全部支付*/
	private Short payState;
	/**交易状态
            1等待付款
            2 等待发货
            3:部分发货
            4 等待确认收货
            9 交易成功
            10 交易关闭*/
	private Short tradeState;
	/**交易时间*/
	private Long createTime;
	/**修改时间*/
	private Long updatetime;
	/**pointBalance*/
	private java.math.BigDecimal pointBalance;
	/**迁移标记*/
	private Integer flag;

	public String getOrderId()
	{
		return this.orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public String getSerialNumber()
	{
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
	}

	public String getSendUserId()
	{
		return this.sendUserId;
	}

	public void setSendUserId(String sendUserId)
	{
		this.sendUserId = sendUserId;
	}

	public String getSendName()
	{
		return this.sendName;
	}

	public void setSendName(String sendName)
	{
		this.sendName = sendName;
	}

	public String getRecUserId()
	{
		return this.recUserId;
	}

	public void setRecUserId(String recUserId)
	{
		this.recUserId = recUserId;
	}

	public String getRecName()
	{
		return this.recName;
	}

	public void setRecName(String recName)
	{
		this.recName = recName;
	}

	public java.math.BigDecimal getBalance()
	{
		return this.balance;
	}

	public void setBalance(java.math.BigDecimal balance)
	{
		this.balance = balance;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Short getTradeType()
	{
		return this.tradeType;
	}

	public void setTradeType(Short tradeType)
	{
		this.tradeType = tradeType;
	}

	public Short getPayState()
	{
		return this.payState;
	}

	public void setPayState(Short payState)
	{
		this.payState = payState;
	}

	public Short getTradeState()
	{
		return this.tradeState;
	}

	public void setTradeState(Short tradeState)
	{
		this.tradeState = tradeState;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}

	public Long getUpdatetime()
	{
		return this.updatetime;
	}

	public void setUpdatetime(Long updatetime)
	{
		this.updatetime = updatetime;
	}

	public java.math.BigDecimal getPointBalance()
	{
		return this.pointBalance;
	}

	public void setPointBalance(java.math.BigDecimal pointBalance)
	{
		this.pointBalance = pointBalance;
	}

	public Integer getFlag()
	{
		return this.flag;
	}

	public void setFlag(Integer flag)
	{
		this.flag = flag;
	}
	
}

