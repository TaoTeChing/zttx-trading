/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 产品修改审核日志 实体对象
 * <p>File：ProductEditAuditLog.java</p>
 * <p>Title: ProductEditAuditLog</p>
 * <p>Description:ProductEditAuditLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductEditAuditLog extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**产品ID*/
	private java.lang.String productId;
	/**操作人ID*/
	private java.lang.String operateId;
	/**日志内容*/
	private java.lang.String content;
	/**建档时间*/
	private java.lang.Long createTime;
	
	public java.lang.String getProductId()
	{
		return this.productId;
	}
	
	public void setProductId(java.lang.String productId)
	{
		this.productId = productId;
	}
	
	public java.lang.String getOperateId()
	{
		return this.operateId;
	}
	
	public void setOperateId(java.lang.String operateId)
	{
		this.operateId = operateId;
	}
	
	public java.lang.String getContent()
	{
		return this.content;
	}
	
	public void setContent(java.lang.String content)
	{
		this.content = content;
	}
	
	public java.lang.Long getCreateTime()
	{
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime)
	{
		this.createTime = createTime;
	}
	
}

