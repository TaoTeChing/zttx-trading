/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商客服信息 实体对象
 * <p>File：BrandService.java</p>
 * <p>Title: BrandService</p>
 * <p>Description:BrandService</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandService extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**客服名字*/
	private java.lang.String serviceName;
	/**客服编号(对应CRM客服编号）*/
	private java.lang.String userId;
	/**客服照片*/
	private java.lang.String serviceImage;
	/**客服域名*/
	private java.lang.String domainName;
	/**客服性别*/
	private java.lang.Short userGender;
	/**工号*/
	private java.lang.String jobNum;
	/**客服电话*/
	private java.lang.String serviceTel;
	/**客服手机*/
	private java.lang.String serviceMobile;
	/**以后扩展用，默认为1*/
	private java.lang.Short serviceCate;
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
	
	public java.lang.String getServiceName()
	{
		return this.serviceName;
	}
	
	public void setServiceName(java.lang.String serviceName)
	{
		this.serviceName = serviceName;
	}
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
	}
	
	public java.lang.String getServiceImage()
	{
		return this.serviceImage;
	}
	
	public void setServiceImage(java.lang.String serviceImage)
	{
		this.serviceImage = serviceImage;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.Short getUserGender()
	{
		return this.userGender;
	}
	
	public void setUserGender(java.lang.Short userGender)
	{
		this.userGender = userGender;
	}
	
	public java.lang.String getJobNum()
	{
		return this.jobNum;
	}
	
	public void setJobNum(java.lang.String jobNum)
	{
		this.jobNum = jobNum;
	}
	
	public java.lang.String getServiceTel()
	{
		return this.serviceTel;
	}
	
	public void setServiceTel(java.lang.String serviceTel)
	{
		this.serviceTel = serviceTel;
	}
	
	public java.lang.String getServiceMobile()
	{
		return this.serviceMobile;
	}
	
	public void setServiceMobile(java.lang.String serviceMobile)
	{
		this.serviceMobile = serviceMobile;
	}
	
	public java.lang.Short getServiceCate()
	{
		return this.serviceCate;
	}
	
	public void setServiceCate(java.lang.Short serviceCate)
	{
		this.serviceCate = serviceCate;
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

