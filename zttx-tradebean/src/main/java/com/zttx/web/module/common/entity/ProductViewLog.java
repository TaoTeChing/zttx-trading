/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 商品浏览历史记录 实体对象
 * <p>File：ProductViewLog.java</p>
 * <p>Title: ProductViewLog</p>
 * <p>Description:ProductViewLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductViewLog extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**公告标题*/
	private String productId;
	/**商品标题*/
	private String productTitle;
	/**用户编号*/
	private String userId;
	/**用户名称/经销商，品牌商名称*/
	private String userName;
	/**商品图片*/
	private String productImage;
	/**商品价格*/
	private java.math.BigDecimal productPrice;
	/**1:品牌商
            2：经销商*/
	private Short userCate;
	/**浏览次数*/
	private Integer viewNum;
	/**建档时间*/
	private Long createTime;

	public String getProductId()
	{
		return this.productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	public String getProductTitle()
	{
		return this.productTitle;
	}

	public void setProductTitle(String productTitle)
	{
		this.productTitle = productTitle;
	}

	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getProductImage()
	{
		return this.productImage;
	}

	public void setProductImage(String productImage)
	{
		this.productImage = productImage;
	}

	public java.math.BigDecimal getProductPrice()
	{
		return this.productPrice;
	}

	public void setProductPrice(java.math.BigDecimal productPrice)
	{
		this.productPrice = productPrice;
	}

	public Short getUserCate()
	{
		return this.userCate;
	}

	public void setUserCate(Short userCate)
	{
		this.userCate = userCate;
	}

	public Integer getViewNum()
	{
		return this.viewNum;
	}

	public void setViewNum(Integer viewNum)
	{
		this.viewNum = viewNum;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}
	
}

