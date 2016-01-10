/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 上传图片附件类型 实体对象
 * <p>File：UploadAttCate.java</p>
 * <p>Title: UploadAttCate</p>
 * <p>Description:UploadAttCate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class UploadAttCate extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**类型*/
	private java.lang.String cateKey;
	/**模块Key*/
	private java.lang.String attName;
	/**创建时间*/
	private java.lang.Long createTime;
	/**更新时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getCateKey()
	{
		return this.cateKey;
	}
	
	public void setCateKey(java.lang.String cateKey)
	{
		this.cateKey = cateKey;
	}
	
	public java.lang.String getAttName()
	{
		return this.attName;
	}
	
	public void setAttName(java.lang.String attName)
	{
		this.attName = attName;
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

