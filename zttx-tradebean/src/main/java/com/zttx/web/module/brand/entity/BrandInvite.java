/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商邀请经销商加盟 实体对象
 * <p>File：BrandInvite.java</p>
 * <p>Title: BrandInvite</p>
 * <p>Description:BrandInvite</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandInvite extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**操作类型（1：经销商申请，2：品牌商邀请）*/
	private java.lang.Short opratorCata;
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**经销商名称*/
	private java.lang.String dealerName;
	/**品牌商名称*/
	private java.lang.String brandName;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**品牌名称*/
	private java.lang.String brandsName;
	/**品牌logo域名*/
	private java.lang.String domainName;
	/**品牌logo*/
	private java.lang.String brandsLogo;
	/**品牌商区域编码*/
	private java.lang.Integer areaNo;
	/**品牌商所在省份*/
	private java.lang.String provinceName;
	/**品牌商所在城市*/
	private java.lang.String cityName;
	/**品牌商所在县区*/
	private java.lang.String areaName;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**邀请时间*/
	private java.lang.Long inviteTime;
	/**邀请内容*/
	private java.lang.String inviteText;
	/**inviteState（邀请状态）0：已邀请未回复，1：已邀请已同意，2：已邀请已拒绝，3：已删除*/
	private java.lang.Integer inviteState;
	/**0：未审核，1：审核通过，2：审核不通过，3：邀请加盟，4：经销商中止合作，5:撤消申请，6：品牌商中止合作）*/
	private java.lang.Short applyState;
	/**经销商撤消申请时间*/
	private java.lang.Long undoTime;
	/**品牌商审核时间*/
	private java.lang.Long auditTime;
	/**审核不通过原因*/
	private java.lang.String auditMark;
	/**支撑系统是否已读：0、未读　1、已读*/
	private java.lang.Short readState;
	/**来源类型： 0、交易平台 1、支撑系统*/
	private java.lang.Short sourceType;
	
	public java.lang.Short getOpratorCata()
	{
		return this.opratorCata;
	}
	
	public void setOpratorCata(java.lang.Short opratorCata)
	{
		this.opratorCata = opratorCata;
	}
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getDealerName()
	{
		return this.dealerName;
	}
	
	public void setDealerName(java.lang.String dealerName)
	{
		this.dealerName = dealerName;
	}
	
	public java.lang.String getBrandName()
	{
		return this.brandName;
	}
	
	public void setBrandName(java.lang.String brandName)
	{
		this.brandName = brandName;
	}
	
	public java.lang.String getBrandsId()
	{
		return this.brandsId;
	}
	
	public void setBrandsId(java.lang.String brandsId)
	{
		this.brandsId = brandsId;
	}
	
	public java.lang.String getBrandsName()
	{
		return this.brandsName;
	}
	
	public void setBrandsName(java.lang.String brandsName)
	{
		this.brandsName = brandsName;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getBrandsLogo()
	{
		return this.brandsLogo;
	}
	
	public void setBrandsLogo(java.lang.String brandsLogo)
	{
		this.brandsLogo = brandsLogo;
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
	
	public java.lang.Long getInviteTime()
	{
		return this.inviteTime;
	}
	
	public void setInviteTime(java.lang.Long inviteTime)
	{
		this.inviteTime = inviteTime;
	}
	
	public java.lang.String getInviteText()
	{
		return this.inviteText;
	}
	
	public void setInviteText(java.lang.String inviteText)
	{
		this.inviteText = inviteText;
	}
	
	public java.lang.Integer getInviteState()
	{
		return this.inviteState;
	}
	
	public void setInviteState(java.lang.Integer inviteState)
	{
		this.inviteState = inviteState;
	}
	
	public java.lang.Short getApplyState()
	{
		return this.applyState;
	}
	
	public void setApplyState(java.lang.Short applyState)
	{
		this.applyState = applyState;
	}
	
	public java.lang.Long getUndoTime()
	{
		return this.undoTime;
	}
	
	public void setUndoTime(java.lang.Long undoTime)
	{
		this.undoTime = undoTime;
	}
	
	public java.lang.Long getAuditTime()
	{
		return this.auditTime;
	}
	
	public void setAuditTime(java.lang.Long auditTime)
	{
		this.auditTime = auditTime;
	}
	
	public java.lang.String getAuditMark()
	{
		return this.auditMark;
	}
	
	public void setAuditMark(java.lang.String auditMark)
	{
		this.auditMark = auditMark;
	}
	
	public java.lang.Short getReadState()
	{
		return this.readState;
	}
	
	public void setReadState(java.lang.Short readState)
	{
		this.readState = readState;
	}
	
	public java.lang.Short getSourceType()
	{
		return this.sourceType;
	}
	
	public void setSourceType(java.lang.Short sourceType)
	{
		this.sourceType = sourceType;
	}
	
}

