/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 退款留言 实体对象
 * <p>File：DealerRefReply.java</p>
 * <p>Title: DealerRefReply</p>
 * <p>Description:DealerRefReply</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerRefReply extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**退款编号*/
	private java.lang.String refundId;
	/**用户编号*/
	private java.lang.String userId;
	/**用户/经销商/品牌商名称*/
	private java.lang.String userName;
	/**附件域名*/
	private java.lang.String replyContent;
	/**建档时间*/
	private java.lang.Long createTime;
	
	public java.lang.String getRefundId()
	{
		return this.refundId;
	}
	
	public void setRefundId(java.lang.String refundId)
	{
		this.refundId = refundId;
	}
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
	}
	
	public java.lang.String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(java.lang.String userName)
	{
		this.userName = userName;
	}
	
	public java.lang.String getReplyContent()
	{
		return this.replyContent;
	}
	
	public void setReplyContent(java.lang.String replyContent)
	{
		this.replyContent = replyContent;
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

