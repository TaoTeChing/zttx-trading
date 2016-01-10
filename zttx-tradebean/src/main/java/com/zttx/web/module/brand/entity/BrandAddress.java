/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商发货地址信息 实体对象
 * <p>File：BrandAddress.java</p>
 * <p>Title: BrandAddress</p>
 * <p>Description:BrandAddress</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandAddress extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**仓库名称*/
	private java.lang.String storeId;
	/**发货人员*/
	private java.lang.String userName;
	/**所在地区*/
	private java.lang.Integer areaNo;
	/**街道地址*/
	private java.lang.String street;
	/**联系电话*/
	private java.lang.String userTel;
	/**手机号码*/
	private java.lang.String userMobile;
	/**备注说明*/
	private java.lang.String addressMark;
	/**是否默认*/
	private java.lang.Boolean sendDefault;
	/**默认退货地址*/
	private java.lang.Boolean returnDefault;
	/**zipCode*/
	private java.lang.String zipCode;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
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
	
	public java.lang.String getStoreId()
	{
		return this.storeId;
	}
	
	public void setStoreId(java.lang.String storeId)
	{
		this.storeId = storeId;
	}
	
	public java.lang.String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(java.lang.String userName)
	{
		this.userName = userName;
	}
	
	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}
	
	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
	}
	
	public java.lang.String getStreet()
	{
		return this.street;
	}
	
	public void setStreet(java.lang.String street)
	{
		this.street = street;
	}
	
	public java.lang.String getUserTel()
	{
		return this.userTel;
	}
	
	public void setUserTel(java.lang.String userTel)
	{
		this.userTel = userTel;
	}
	
	public java.lang.String getUserMobile()
	{
		return this.userMobile;
	}
	
	public void setUserMobile(java.lang.String userMobile)
	{
		this.userMobile = userMobile;
	}
	
	public java.lang.String getAddressMark()
	{
		return this.addressMark;
	}
	
	public void setAddressMark(java.lang.String addressMark)
	{
		this.addressMark = addressMark;
	}
	
	public java.lang.Boolean getSendDefault()
	{
		return this.sendDefault;
	}
	
	public void setSendDefault(java.lang.Boolean sendDefault)
	{
		this.sendDefault = sendDefault;
	}
	
	public java.lang.Boolean getReturnDefault()
	{
		return this.returnDefault;
	}
	
	public void setReturnDefault(java.lang.Boolean returnDefault)
	{
		this.returnDefault = returnDefault;
	}
	
	public java.lang.String getZipCode()
	{
		return this.zipCode;
	}
	
	public void setZipCode(java.lang.String zipCode)
	{
		this.zipCode = zipCode;
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

