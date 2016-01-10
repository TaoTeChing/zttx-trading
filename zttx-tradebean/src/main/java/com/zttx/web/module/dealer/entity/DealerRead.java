/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商消息阅读 实体对象
 * <p>File：DealerRead.java</p>
 * <p>Title: DealerRead</p>
 * <p>Description:DealerRead</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerRead extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商编号*/
	private java.lang.String dealerId;
	/**消息编号*/
	private java.lang.String msgId;
	/**阅读时间*/
	private java.lang.Long readTime;
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getMsgId()
	{
		return this.msgId;
	}
	
	public void setMsgId(java.lang.String msgId)
	{
		this.msgId = msgId;
	}
	
	public java.lang.Long getReadTime()
	{
		return this.readTime;
	}
	
	public void setReadTime(java.lang.Long readTime)
	{
		this.readTime = readTime;
	}
	
}

