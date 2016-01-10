/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商基础信息 实体对象
 * <p>File：DealerInfo.java</p>
 * <p>Title: DealerInfo</p>
 * <p>Description:DealerInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerInfo extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**公司/店铺名称*/
	private java.lang.String dealerName;
	/**经销商地址*/
	private java.lang.String dealerAddress;
	/**经销商网址*/
	private java.lang.String dealerWeb;
	/**网上商铺*/
	private java.lang.String dealerShop;
	/**domainName*/
	private java.lang.String domainName;
	/**经销商图标*/
	private java.lang.String dealerLogo;
	/**headImage*/
	private java.lang.String headImage;
	/**分店数量*/
	private java.lang.Integer shopNum;
	/**法人代表(正)*/
	private java.lang.String legalImgz;
	/**法人代表(反)*/
	private java.lang.String legalImgf;
	/**月销售额*/
	private java.math.BigDecimal monNum;
	/**员工数量*/
	private java.lang.Integer empNum;
	/**经营品牌*/
	private java.lang.String brandName;
	/**成立时间*/
	private java.lang.Long foundTime;
	/**营业执照*/
	private java.lang.String busImg;
	/**其他证书*/
	private java.lang.String otherImg;
	/**经销商介绍*/
	private java.lang.String dealerMark;
	/**联系人员*/
	private java.lang.String dealerUser;
	/**联系人性别*/
	private java.lang.Short dealerGender;
	/**电话号码*/
	private java.lang.String dealerTel;
	/**传真号码*/
	private java.lang.String dealerFax;
	/**省份名称*/
	private java.lang.String provinceName;
	/**市名称*/
	private java.lang.String cityName;
	/**县区名称*/
	private java.lang.String areaName;
	/**areaNo*/
	private java.lang.Integer areaNo;
	/**身份证号*/
	private java.lang.String cardId;
	/**营业执照编号*/
	private java.lang.String comNo;
	/**服务开始时间*/
	private java.lang.Long beginTime;
	/**服务结束时间*/
	private java.lang.Long endTime;
	/**百度地图经度*/
	private java.lang.String gpsX;
	/**百度地图纬度*/
	private java.lang.String gpsY;
	/**店铺等级
(CRM客服给店铺评定的等级)1,2,3,4,5*/
	private java.lang.Short dealerLevel;
	/**审核状态（0：未审核，1：审核通过，2：审核不通过）*/
	private java.lang.Short checkState;
	/**收货是否需要短信验证*/
	private java.lang.Boolean rcvSmsVerify;
	/**创建时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getDealerName()
	{
		return this.dealerName;
	}
	
	public void setDealerName(java.lang.String dealerName)
	{
		this.dealerName = dealerName;
	}
	
	public java.lang.String getDealerAddress()
	{
		return this.dealerAddress;
	}
	
	public void setDealerAddress(java.lang.String dealerAddress)
	{
		this.dealerAddress = dealerAddress;
	}
	
	public java.lang.String getDealerWeb()
	{
		return this.dealerWeb;
	}
	
	public void setDealerWeb(java.lang.String dealerWeb)
	{
		this.dealerWeb = dealerWeb;
	}
	
	public java.lang.String getDealerShop()
	{
		return this.dealerShop;
	}
	
	public void setDealerShop(java.lang.String dealerShop)
	{
		this.dealerShop = dealerShop;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getDealerLogo()
	{
		return this.dealerLogo;
	}
	
	public void setDealerLogo(java.lang.String dealerLogo)
	{
		this.dealerLogo = dealerLogo;
	}
	
	public java.lang.String getHeadImage()
	{
		return this.headImage;
	}
	
	public void setHeadImage(java.lang.String headImage)
	{
		this.headImage = headImage;
	}
	
	public java.lang.Integer getShopNum()
	{
		return this.shopNum;
	}
	
	public void setShopNum(java.lang.Integer shopNum)
	{
		this.shopNum = shopNum;
	}
	
	public java.lang.String getLegalImgz()
	{
		return this.legalImgz;
	}
	
	public void setLegalImgz(java.lang.String legalImgz)
	{
		this.legalImgz = legalImgz;
	}
	
	public java.lang.String getLegalImgf()
	{
		return this.legalImgf;
	}
	
	public void setLegalImgf(java.lang.String legalImgf)
	{
		this.legalImgf = legalImgf;
	}
	
	public java.math.BigDecimal getMonNum()
	{
		return this.monNum;
	}
	
	public void setMonNum(java.math.BigDecimal monNum)
	{
		this.monNum = monNum;
	}
	
	public java.lang.Integer getEmpNum()
	{
		return this.empNum;
	}
	
	public void setEmpNum(java.lang.Integer empNum)
	{
		this.empNum = empNum;
	}
	
	public java.lang.String getBrandName()
	{
		return this.brandName;
	}
	
	public void setBrandName(java.lang.String brandName)
	{
		this.brandName = brandName;
	}
	
	public java.lang.Long getFoundTime()
	{
		return this.foundTime;
	}
	
	public void setFoundTime(java.lang.Long foundTime)
	{
		this.foundTime = foundTime;
	}
	
	public java.lang.String getBusImg()
	{
		return this.busImg;
	}
	
	public void setBusImg(java.lang.String busImg)
	{
		this.busImg = busImg;
	}
	
	public java.lang.String getOtherImg()
	{
		return this.otherImg;
	}
	
	public void setOtherImg(java.lang.String otherImg)
	{
		this.otherImg = otherImg;
	}
	
	public java.lang.String getDealerMark()
	{
		return this.dealerMark;
	}
	
	public void setDealerMark(java.lang.String dealerMark)
	{
		this.dealerMark = dealerMark;
	}
	
	public java.lang.String getDealerUser()
	{
		return this.dealerUser;
	}
	
	public void setDealerUser(java.lang.String dealerUser)
	{
		this.dealerUser = dealerUser;
	}
	
	public java.lang.Short getDealerGender()
	{
		return this.dealerGender;
	}
	
	public void setDealerGender(java.lang.Short dealerGender)
	{
		this.dealerGender = dealerGender;
	}
	
	public java.lang.String getDealerTel()
	{
		return this.dealerTel;
	}
	
	public void setDealerTel(java.lang.String dealerTel)
	{
		this.dealerTel = dealerTel;
	}
	
	public java.lang.String getDealerFax()
	{
		return this.dealerFax;
	}
	
	public void setDealerFax(java.lang.String dealerFax)
	{
		this.dealerFax = dealerFax;
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
	
	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}
	
	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
	}
	
	public java.lang.String getCardId()
	{
		return this.cardId;
	}
	
	public void setCardId(java.lang.String cardId)
	{
		this.cardId = cardId;
	}
	
	public java.lang.String getComNo()
	{
		return this.comNo;
	}
	
	public void setComNo(java.lang.String comNo)
	{
		this.comNo = comNo;
	}
	
	public java.lang.Long getBeginTime()
	{
		return this.beginTime;
	}
	
	public void setBeginTime(java.lang.Long beginTime)
	{
		this.beginTime = beginTime;
	}
	
	public java.lang.Long getEndTime()
	{
		return this.endTime;
	}
	
	public void setEndTime(java.lang.Long endTime)
	{
		this.endTime = endTime;
	}
	
	public java.lang.String getGpsX()
	{
		return this.gpsX;
	}
	
	public void setGpsX(java.lang.String gpsX)
	{
		this.gpsX = gpsX;
	}
	
	public java.lang.String getGpsY()
	{
		return this.gpsY;
	}
	
	public void setGpsY(java.lang.String gpsY)
	{
		this.gpsY = gpsY;
	}
	
	public java.lang.Short getDealerLevel()
	{
		return this.dealerLevel;
	}
	
	public void setDealerLevel(java.lang.Short dealerLevel)
	{
		this.dealerLevel = dealerLevel;
	}
	
	public java.lang.Short getCheckState()
	{
		return this.checkState;
	}
	
	public void setCheckState(java.lang.Short checkState)
	{
		this.checkState = checkState;
	}
	
	public java.lang.Boolean getRcvSmsVerify()
	{
		return this.rcvSmsVerify;
	}
	
	public void setRcvSmsVerify(java.lang.Boolean rcvSmsVerify)
	{
		this.rcvSmsVerify = rcvSmsVerify;
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

