/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 终端商微店信息 实体对象
 * <p>File：DealerShopInfo.java</p>
 * <p>Title: DealerShopInfo</p>
 * <p>Description:DealerShopInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerShopInfo extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商编号*/
	private java.lang.String dealerId;
	/**微逛号*/
	private java.lang.String userCode;
	/**微店编号*/
	private java.lang.String shopId;
	/**创建时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**状态*/
	private java.lang.Short shopState;
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getUserCode()
	{
		return this.userCode;
	}
	
	public void setUserCode(java.lang.String userCode)
	{
		this.userCode = userCode;
	}
	
	public java.lang.String getShopId()
	{
		return this.shopId;
	}
	
	public void setShopId(java.lang.String shopId)
	{
		this.shopId = shopId;
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
	
	public java.lang.Short getShopState()
	{
		return this.shopState;
	}
	
	public void setShopState(java.lang.Short shopState)
	{
		this.shopState = shopState;
	}
	
}

