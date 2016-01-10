/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商消息阅读 实体对象
 * <p>File：BrandRead.java</p>
 * <p>Title: BrandRead</p>
 * <p>Description:BrandRead</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandRead extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**消息编号*/
	private java.lang.String msgId;
	/**阅读时间*/
	private java.lang.Long readTime;
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
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

