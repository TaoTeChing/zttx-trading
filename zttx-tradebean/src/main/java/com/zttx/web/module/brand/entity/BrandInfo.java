/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商基本信息 实体对象
 * <p>File：BrandInfo.java</p>
 * <p>Title: BrandInfo</p>
 * <p>Description:BrandInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandInfo extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**公司名称*/
	private java.lang.String comName;
	/**企业类型*/
	private java.lang.Short comType;
	/**经营类型*/
	private java.lang.Short dealType;
	/**图片域名*/
	private java.lang.String domainName;
	/**执照图片原名(正)*/
	private java.lang.String brandPhoto;
	/**执照图片新名(正)*/
	private java.lang.String brandImage;
	/**身份证正*/
	private java.lang.String userPhoto;
	/**身份证反*/
	private java.lang.String userImage;
	/**营业执照编号*/
	private java.lang.String comNum;
	/**身份证编号*/
	private java.lang.String idNum;
	/**法人代表*/
	private java.lang.String legalName;
	/**注册资金*/
	private java.math.BigDecimal regMoney;
	/**企业地址*/
	private java.lang.Integer areaNo;
	/**省份名称*/
	private java.lang.String provinceName;
	/**城市名称*/
	private java.lang.String cityName;
	/**区域名称*/
	private java.lang.String areaName;
	/**详细地址*/
	private java.lang.String comAddress;
	/**企业网址*/
	private java.lang.String comWeb;
	/**企业介绍*/
	private java.lang.String comMark;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**审核状态（0：未审核，1：审核通过，2：审核不通过）*/
	private java.lang.Short checkState;
	/**公司规模*/
	private java.lang.Short emploeeNum;
	/**年营业额*/
	private java.lang.Short moneyNum;
	/**条形码助记码*/
	private java.lang.String barCodeNum;
	
	public java.lang.String getComName()
	{
		return this.comName;
	}
	
	public void setComName(java.lang.String comName)
	{
		this.comName = comName;
	}
	
	public java.lang.Short getComType()
	{
		return this.comType;
	}
	
	public void setComType(java.lang.Short comType)
	{
		this.comType = comType;
	}
	
	public java.lang.Short getDealType()
	{
		return this.dealType;
	}
	
	public void setDealType(java.lang.Short dealType)
	{
		this.dealType = dealType;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getBrandPhoto()
	{
		return this.brandPhoto;
	}
	
	public void setBrandPhoto(java.lang.String brandPhoto)
	{
		this.brandPhoto = brandPhoto;
	}
	
	public java.lang.String getBrandImage()
	{
		return this.brandImage;
	}
	
	public void setBrandImage(java.lang.String brandImage)
	{
		this.brandImage = brandImage;
	}
	
	public java.lang.String getUserPhoto()
	{
		return this.userPhoto;
	}
	
	public void setUserPhoto(java.lang.String userPhoto)
	{
		this.userPhoto = userPhoto;
	}
	
	public java.lang.String getUserImage()
	{
		return this.userImage;
	}
	
	public void setUserImage(java.lang.String userImage)
	{
		this.userImage = userImage;
	}
	
	public java.lang.String getComNum()
	{
		return this.comNum;
	}
	
	public void setComNum(java.lang.String comNum)
	{
		this.comNum = comNum;
	}
	
	public java.lang.String getIdNum()
	{
		return this.idNum;
	}
	
	public void setIdNum(java.lang.String idNum)
	{
		this.idNum = idNum;
	}
	
	public java.lang.String getLegalName()
	{
		return this.legalName;
	}
	
	public void setLegalName(java.lang.String legalName)
	{
		this.legalName = legalName;
	}
	
	public java.math.BigDecimal getRegMoney()
	{
		return this.regMoney;
	}
	
	public void setRegMoney(java.math.BigDecimal regMoney)
	{
		this.regMoney = regMoney;
	}
	
	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}
	
	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
	}
	
	public java.lang.String getProvinceName()
	{
		return this.provinceName;
	}
	
	public void setProvinceName(java.lang.String provinceName)
	{
		this.provinceName = provinceName;
	}
	
	public java.lang.String getCityName()
	{
		return this.cityName;
	}
	
	public void setCityName(java.lang.String cityName)
	{
		this.cityName = cityName;
	}
	
	public java.lang.String getAreaName()
	{
		return this.areaName;
	}
	
	public void setAreaName(java.lang.String areaName)
	{
		this.areaName = areaName;
	}
	
	public java.lang.String getComAddress()
	{
		return this.comAddress;
	}
	
	public void setComAddress(java.lang.String comAddress)
	{
		this.comAddress = comAddress;
	}
	
	public java.lang.String getComWeb()
	{
		return this.comWeb;
	}
	
	public void setComWeb(java.lang.String comWeb)
	{
		this.comWeb = comWeb;
	}
	
	public java.lang.String getComMark()
	{
		return this.comMark;
	}
	
	public void setComMark(java.lang.String comMark)
	{
		this.comMark = comMark;
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
	
	public java.lang.Short getCheckState()
	{
		return this.checkState;
	}
	
	public void setCheckState(java.lang.Short checkState)
	{
		this.checkState = checkState;
	}
	
	public java.lang.Short getEmploeeNum()
	{
		return this.emploeeNum;
	}
	
	public void setEmploeeNum(java.lang.Short emploeeNum)
	{
		this.emploeeNum = emploeeNum;
	}
	
	public java.lang.Short getMoneyNum()
	{
		return this.moneyNum;
	}
	
	public void setMoneyNum(java.lang.Short moneyNum)
	{
		this.moneyNum = moneyNum;
	}
	
	public java.lang.String getBarCodeNum()
	{
		return this.barCodeNum;
	}
	
	public void setBarCodeNum(java.lang.String barCodeNum)
	{
		this.barCodeNum = barCodeNum;
	}
	
}

