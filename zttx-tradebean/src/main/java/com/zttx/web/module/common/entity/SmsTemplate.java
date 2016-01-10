/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 短信模板 实体对象
 * <p>File：SmsTemplate.java</p>
 * <p>Title: SmsTemplate</p>
 * <p>Description:SmsTemplate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class SmsTemplate extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**短信模块Key*/
	@NotBlank(message = "短信模板KEY不能为空")
	private java.lang.String smsKey;
	/**短信名称*/
	@NotBlank(message = "短信名称不能为空")
	private java.lang.String templateName;
	/**短信内容*/
	@NotBlank(message = "短信内容不能为空")
	private java.lang.String content;
	/**占位符*/
	private java.lang.String place;
	/**描述*/
	private java.lang.String remark;
	/**创建时间*/
	private java.lang.Long createTime;
	/**更新时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getSmsKey()
	{
		return this.smsKey;
	}
	
	public void setSmsKey(java.lang.String smsKey)
	{
		this.smsKey = smsKey;
	}
	
	public java.lang.String getTemplateName()
	{
		return this.templateName;
	}
	
	public void setTemplateName(java.lang.String templateName)
	{
		this.templateName = templateName;
	}
	
	public java.lang.String getContent()
	{
		return this.content;
	}
	
	public void setContent(java.lang.String content)
	{
		this.content = content;
	}
	
	public java.lang.String getPlace()
	{
		return this.place;
	}
	
	public void setPlace(java.lang.String place)
	{
		this.place = place;
	}
	
	public java.lang.String getRemark()
	{
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark)
	{
		this.remark = remark;
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

