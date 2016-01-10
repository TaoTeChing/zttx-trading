/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 页面模版定义日志 实体对象
 * <p>File：WebDefTmpLog.java</p>
 * <p>Title: WebDefTmpLog</p>
 * <p>Description:WebDefTmpLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class WebDefTmpLog extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**模块Key*/
	private java.lang.String modelKey;
	/**模块内容*/
	private java.lang.String htmlText;
	/**创建时间*/
	private java.lang.Long createTime;
	
	public java.lang.String getModelKey()
	{
		return this.modelKey;
	}
	
	public void setModelKey(java.lang.String modelKey)
	{
		this.modelKey = modelKey;
	}
	
	public java.lang.String getHtmlText()
	{
		return this.htmlText;
	}
	
	public void setHtmlText(java.lang.String htmlText)
	{
		this.htmlText = htmlText;
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

