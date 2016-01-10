/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 上传图片附件类型大小 实体对象
 * <p>File：UploadAttSize.java</p>
 * <p>Title: UploadAttSize</p>
 * <p>Description:UploadAttSize</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class UploadAttSize extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**附件类型*/
	private java.lang.String attCateId;
	/**类型Key*/
	private java.lang.String cateKey;
	/**高度*/
	private java.lang.Integer height;
	/**宽度*/
	private java.lang.Integer width;
	/**创建时间*/
	private java.lang.Long createTime;
	/**更新时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getAttCateId()
	{
		return this.attCateId;
	}
	
	public void setAttCateId(java.lang.String attCateId)
	{
		this.attCateId = attCateId;
	}
	
	public java.lang.String getCateKey()
	{
		return this.cateKey;
	}
	
	public void setCateKey(java.lang.String cateKey)
	{
		this.cateKey = cateKey;
	}
	
	public java.lang.Integer getHeight()
	{
		return this.height;
	}
	
	public void setHeight(java.lang.Integer height)
	{
		this.height = height;
	}
	
	public java.lang.Integer getWidth()
	{
		return this.width;
	}
	
	public void setWidth(java.lang.Integer width)
	{
		this.width = width;
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

