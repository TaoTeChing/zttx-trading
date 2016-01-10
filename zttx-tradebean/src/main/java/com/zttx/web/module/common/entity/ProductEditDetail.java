/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 产品修改详情 实体对象
 * <p>File：ProductEditDetail.java</p>
 * <p>Title: ProductEditDetail</p>
 * <p>Description:ProductEditDetail</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductEditDetail extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**产品ID*/
	private java.lang.String productId;
	/**变更类型（0：产品货号，1：颜色，2：尺码）*/
	private java.lang.Short changeType;
	/**原始值*/
	private java.lang.String oldValue;
	/**变更值*/
	private java.lang.String newValue;
	/**审核状态（0：未审，1：通过，2：拒绝）*/
	private java.lang.Short state;
	/**审核结果*/
	private java.lang.String checkResult;
	/**生效时间*/
	private java.lang.Long applyTime;
	/**属性ID（变更颜色，尺码时用到）*/
	private java.lang.String vid;
	/**图标（变更颜色时用到）*/
	private java.lang.String attributeIcon;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getProductId()
	{
		return this.productId;
	}
	
	public void setProductId(java.lang.String productId)
	{
		this.productId = productId;
	}
	
	public java.lang.Short getChangeType()
	{
		return this.changeType;
	}
	
	public void setChangeType(java.lang.Short changeType)
	{
		this.changeType = changeType;
	}
	
	public java.lang.String getOldValue()
	{
		return this.oldValue;
	}
	
	public void setOldValue(java.lang.String oldValue)
	{
		this.oldValue = oldValue;
	}
	
	public java.lang.String getNewValue()
	{
		return this.newValue;
	}
	
	public void setNewValue(java.lang.String newValue)
	{
		this.newValue = newValue;
	}
	
	public java.lang.Short getState()
	{
		return this.state;
	}
	
	public void setState(java.lang.Short state)
	{
		this.state = state;
	}
	
	public java.lang.String getCheckResult()
	{
		return this.checkResult;
	}
	
	public void setCheckResult(java.lang.String checkResult)
	{
		this.checkResult = checkResult;
	}
	
	public java.lang.Long getApplyTime()
	{
		return this.applyTime;
	}
	
	public void setApplyTime(java.lang.Long applyTime)
	{
		this.applyTime = applyTime;
	}
	
	public java.lang.String getVid()
	{
		return this.vid;
	}
	
	public void setVid(java.lang.String vid)
	{
		this.vid = vid;
	}
	
	public java.lang.String getAttributeIcon()
	{
		return this.attributeIcon;
	}
	
	public void setAttributeIcon(java.lang.String attributeIcon)
	{
		this.attributeIcon = attributeIcon;
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

