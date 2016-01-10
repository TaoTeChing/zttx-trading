/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商反馈信息 实体对象
 * <p>File：DealerFeed.java</p>
 * <p>Title: DealerFeed</p>
 * <p>Description:DealerFeed</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerFeed extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商编号*/
	private java.lang.String dealerId;
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**反馈内容*/
	private java.lang.String feedText;
	/**反馈时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**品牌商回复内容*/
	private java.lang.String replyText;
	/**品牌商回复时间*/
	private java.lang.Long replyTime;
	/**回复人员编号（品牌商编号）*/
	private java.lang.String replyId;
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getBrandsId()
	{
		return this.brandsId;
	}
	
	public void setBrandsId(java.lang.String brandsId)
	{
		this.brandsId = brandsId;
	}
	
	public java.lang.String getFeedText()
	{
		return this.feedText;
	}
	
	public void setFeedText(java.lang.String feedText)
	{
		this.feedText = feedText;
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
	
	public java.lang.String getReplyText()
	{
		return this.replyText;
	}
	
	public void setReplyText(java.lang.String replyText)
	{
		this.replyText = replyText;
	}
	
	public java.lang.Long getReplyTime()
	{
		return this.replyTime;
	}
	
	public void setReplyTime(java.lang.Long replyTime)
	{
		this.replyTime = replyTime;
	}
	
	public java.lang.String getReplyId()
	{
		return this.replyId;
	}
	
	public void setReplyId(java.lang.String replyId)
	{
		this.replyId = replyId;
	}
	
}

