/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 订单操作历史记录 实体对象
 * <p>File：DealerOrderAction.java</p>
 * <p>Title: DealerOrderAction</p>
 * <p>Description:DealerOrderAction</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerOrderAction extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**订单编号*/
	private java.lang.String orderId;
	/**经销商/品牌商编号*/
	private java.lang.String userId;
	/**经销商/店铺名称*/
	private java.lang.String userName;
	/**操作编码
            createorder:创建订单，
            orderpay:付款，
            shipgoods：发货，
            applyrefund： 申请退款
            confrimgoods：确认收货
            agreerefund: 同意退款
            */
	private java.lang.String actCode;
	/**操作名*/
	private java.lang.String actName;
	/**操作内容*/
	private java.lang.String actContent;
	/**操作时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getOrderId()
	{
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String orderId)
	{
		this.orderId = orderId;
	}
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
	}
	
	public java.lang.String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(java.lang.String userName)
	{
		this.userName = userName;
	}
	
	public java.lang.String getActCode()
	{
		return this.actCode;
	}
	
	public void setActCode(java.lang.String actCode)
	{
		this.actCode = actCode;
	}
	
	public java.lang.String getActName()
	{
		return this.actName;
	}
	
	public void setActName(java.lang.String actName)
	{
		this.actName = actName;
	}
	
	public java.lang.String getActContent()
	{
		return this.actContent;
	}
	
	public void setActContent(java.lang.String actContent)
	{
		this.actContent = actContent;
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
	
}

