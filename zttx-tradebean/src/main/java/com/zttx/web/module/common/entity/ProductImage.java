/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商产品图片信息 实体对象
 * <p>File：ProductImage.java</p>
 * <p>Title: ProductImage</p>
 * <p>Description:ProductImage</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductImage extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**产品编号*/
	private String productId;
	/**domainName*/
	private String domainName;
	/**图片原名称*/
	private String photoName;
	/**图片新名称*/
	private String imageName;
	/**imageMark*/
	private String imageMark;
	/**上传时间*/
	private Long createTime;
	/**排序编号*/
	private Integer orderId;
	/**是否是主图*/
	private Boolean isMain;
	/**上传者IP*/
	private Integer createIp;
	/**商品​颜色属性项ID*/
	private String attributeItemId;

	public String getProductId()
	{
		return this.productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	public String getDomainName()
	{
		return this.domainName;
	}

	public void setDomainName(String domainName)
	{
		this.domainName = domainName;
	}

	public String getPhotoName()
	{
		return this.photoName;
	}

	public void setPhotoName(String photoName)
	{
		this.photoName = photoName;
	}

	public String getImageName()
	{
		return this.imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	public String getImageMark()
	{
		return this.imageMark;
	}

	public void setImageMark(String imageMark)
	{
		this.imageMark = imageMark;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}

	public Integer getOrderId()
	{
		return this.orderId;
	}

	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public Boolean getIsMain()
	{
		return this.isMain;
	}

	public void setIsMain(Boolean isMain)
	{
		this.isMain = isMain;
	}

	public Integer getCreateIp()
	{
		return this.createIp;
	}

	public void setCreateIp(Integer createIp)
	{
		this.createIp = createIp;
	}

	public String getAttributeItemId()
	{
		return this.attributeItemId;
	}

	public void setAttributeItemId(String attributeItemId)
	{
		this.attributeItemId = attributeItemId;
	}
	
}

