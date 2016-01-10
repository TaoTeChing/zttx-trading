/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商计数信息 实体对象
 * <p>File：DealerCount.java</p>
 * <p>Title: DealerCount</p>
 * <p>Description:DealerCount</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerCount extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键编号
	 */
	protected String          dealerId;
	/**已经加盟的品牌数*/
	private java.lang.Integer joinCount;
	/**申请中的品牌数*/
	private java.lang.Integer applyCount;
	/**邀请中的品牌数*/
	private java.lang.Integer inviteCount;
	/**待结算订单数量*/
	private java.lang.Integer balanceCount;
	/**待收货订单数量*/
	private java.lang.Integer waitConfirmCount;
	/**退款中的订单数*/
	private java.lang.Integer refundCount;
	/**未读系统消息*/
	private java.lang.Integer sysMessageCount;
	/**未读预警消息*/
	private java.lang.Integer warningCount;
	/**工厂店已加盟数量*/
	private java.lang.Integer factoryJoinCount;
	/**工厂店已缴押金数量*/
	private java.lang.Integer factoryHasPaid;
	/**工厂店未缴押金数量*/
	private java.lang.Integer factoryNonPaid;
	/**建档时间*/
	private java.lang.Long createtime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.Integer getJoinCount()
	{
		return this.joinCount;
	}
	
	public void setJoinCount(java.lang.Integer joinCount)
	{
		this.joinCount = joinCount;
	}
	
	public java.lang.Integer getApplyCount()
	{
		return this.applyCount;
	}
	
	public void setApplyCount(java.lang.Integer applyCount)
	{
		this.applyCount = applyCount;
	}
	
	public java.lang.Integer getInviteCount()
	{
		return this.inviteCount;
	}
	
	public void setInviteCount(java.lang.Integer inviteCount)
	{
		this.inviteCount = inviteCount;
	}
	
	public java.lang.Integer getBalanceCount()
	{
		return this.balanceCount;
	}
	
	public void setBalanceCount(java.lang.Integer balanceCount)
	{
		this.balanceCount = balanceCount;
	}
	
	public java.lang.Integer getWaitConfirmCount()
	{
		return this.waitConfirmCount;
	}
	
	public void setWaitConfirmCount(java.lang.Integer waitConfirmCount)
	{
		this.waitConfirmCount = waitConfirmCount;
	}
	
	public java.lang.Integer getRefundCount()
	{
		return this.refundCount;
	}
	
	public void setRefundCount(java.lang.Integer refundCount)
	{
		this.refundCount = refundCount;
	}
	
	public java.lang.Integer getSysMessageCount()
	{
		return this.sysMessageCount;
	}
	
	public void setSysMessageCount(java.lang.Integer sysMessageCount)
	{
		this.sysMessageCount = sysMessageCount;
	}
	
	public java.lang.Integer getWarningCount()
	{
		return this.warningCount;
	}
	
	public void setWarningCount(java.lang.Integer warningCount)
	{
		this.warningCount = warningCount;
	}
	
	public java.lang.Integer getFactoryJoinCount()
	{
		return this.factoryJoinCount;
	}
	
	public void setFactoryJoinCount(java.lang.Integer factoryJoinCount)
	{
		this.factoryJoinCount = factoryJoinCount;
	}
	
	public java.lang.Integer getFactoryHasPaid()
	{
		return this.factoryHasPaid;
	}
	
	public void setFactoryHasPaid(java.lang.Integer factoryHasPaid)
	{
		this.factoryHasPaid = factoryHasPaid;
	}
	
	public java.lang.Integer getFactoryNonPaid()
	{
		return this.factoryNonPaid;
	}
	
	public void setFactoryNonPaid(java.lang.Integer factoryNonPaid)
	{
		this.factoryNonPaid = factoryNonPaid;
	}
	
	public java.lang.Long getCreatetime()
	{
		return this.createtime;
	}
	
	public void setCreatetime(java.lang.Long createtime)
	{
		this.createtime = createtime;
	}
	
	public java.lang.Long getUpdateTime()
	{
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.Long updateTime)
	{
		this.updateTime = updateTime;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
}

