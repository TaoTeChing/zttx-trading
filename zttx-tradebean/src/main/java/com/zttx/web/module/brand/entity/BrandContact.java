/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import java.beans.Transient;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商联系方式 实体对象
 * <p>File：BrandContact.java</p>
 * <p>Title: BrandContact</p>
 * <p>Description:BrandContact</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandContact extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**联系人员*/
	private java.lang.String userName;
	/**头像*/
	private java.lang.String userPhoto;
	/**邮件地址*/
	private java.lang.String userMail;
	/**性别*/
	private java.lang.Short userGender;
	/**职务*/
	private java.lang.String userJob;
	/**业务qq*/
	private java.lang.String userIm;
	/**手机号码*/
	private java.lang.String userMobile;
	/**联系电话*/
	private java.lang.String userTelphone;
	/**传真号码*/
	private java.lang.String userFax;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
    
    // @Transient 账号名
    private String            accountName;
    
    // @Transient 0：未认证 1：已认证
    private Boolean           mailVerify;
	
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
	
	public java.lang.String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(java.lang.String userName)
	{
		this.userName = userName;
	}
	
	public java.lang.String getUserPhoto()
	{
		return this.userPhoto;
	}
	
	public void setUserPhoto(java.lang.String userPhoto)
	{
		this.userPhoto = userPhoto;
	}
	
	public java.lang.String getUserMail()
	{
		return this.userMail;
	}
	
	public void setUserMail(java.lang.String userMail)
	{
		this.userMail = userMail;
	}
	
	public java.lang.Short getUserGender()
	{
		return this.userGender;
	}
	
	public void setUserGender(java.lang.Short userGender)
	{
		this.userGender = userGender;
	}
	
	public java.lang.String getUserJob()
	{
		return this.userJob;
	}
	
	public void setUserJob(java.lang.String userJob)
	{
		this.userJob = userJob;
	}
	
	public java.lang.String getUserIm()
	{
		return this.userIm;
	}
	
	public void setUserIm(java.lang.String userIm)
	{
		this.userIm = userIm;
	}
	
	public java.lang.String getUserMobile()
	{
		return this.userMobile;
	}
	
	public void setUserMobile(java.lang.String userMobile)
	{
		this.userMobile = userMobile;
	}
	
	public java.lang.String getUserTelphone()
	{
		return this.userTelphone;
	}
	
	public void setUserTelphone(java.lang.String userTelphone)
	{
		this.userTelphone = userTelphone;
	}
	
	public java.lang.String getUserFax()
	{
		return this.userFax;
	}
	
	public void setUserFax(java.lang.String userFax)
	{
		this.userFax = userFax;
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
    
    @Transient
    public String getAccountName()
    {
        return accountName;
    }
    
    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }
    
    @Transient
    public Boolean getMailVerify()
    {
        return mailVerify;
    }
    
    public void setMailVerify(Boolean mailVerify)
    {
        this.mailVerify = mailVerify;
    }
}

