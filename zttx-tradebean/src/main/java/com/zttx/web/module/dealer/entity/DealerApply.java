/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商加盟申请 实体对象
 * <p>File：DealerApply.java</p>
 * <p>Title: DealerApply</p>
 * <p>Description:DealerApply</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerApply extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商编号*/
	private java.lang.String dealerId;
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**areaNo*/
	private java.lang.Integer areaNo;
	/**areaName*/
	private java.lang.String areaName;
	/**brandName*/
	private java.lang.String brandName;
	/**brandsName*/
	private java.lang.String brandsName;
	/**申请人*/
	private java.lang.String applyUser;
	/**留言内容*/
	private java.lang.String applyText;
	/**申请时间*/
	private java.lang.Long applyTime;
	/**auditState（品牌商审核状态）0：未审核，1：审核通过，2：审核不通过，3：邀请加盟，4：经销商中止合作,5:撤消申请*/
	private java.lang.Short auditState;
	/**撤销时间*/
	private java.lang.Long undoTime;
	/**品牌商审核时间*/
	private java.lang.Long auditTime;
	/**auditMark*/
	private java.lang.String auditMark;


	 /*========================================= 临时字段 [@author易永耀] begin================================================*/
	  private String productId ;
	   /*========================================= 临时字段 end ================================================*/
	 /*========================================= 临时字段get/set [@author易永耀] begin================================================*/

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	/*========================================= 临时字段get/set end ================================================*/
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
	
	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}
	
	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
	}
	
	public java.lang.String getAreaName()
	{
		return this.areaName;
	}
	
	public void setAreaName(java.lang.String areaName)
	{
		this.areaName = areaName;
	}
	
	public java.lang.String getBrandName()
	{
		return this.brandName;
	}
	
	public void setBrandName(java.lang.String brandName)
	{
		this.brandName = brandName;
	}
	
	public java.lang.String getBrandsName()
	{
		return this.brandsName;
	}
	
	public void setBrandsName(java.lang.String brandsName)
	{
		this.brandsName = brandsName;
	}
	
	public java.lang.String getApplyUser()
	{
		return this.applyUser;
	}
	
	public void setApplyUser(java.lang.String applyUser)
	{
		this.applyUser = applyUser;
	}
	
	public java.lang.String getApplyText()
	{
		return this.applyText;
	}
	
	public void setApplyText(java.lang.String applyText)
	{
		this.applyText = applyText;
	}
	
	public java.lang.Long getApplyTime()
	{
		return this.applyTime;
	}
	
	public void setApplyTime(java.lang.Long applyTime)
	{
		this.applyTime = applyTime;
	}
	
	public java.lang.Short getAuditState()
	{
		return this.auditState;
	}
	
	public void setAuditState(java.lang.Short auditState)
	{
		this.auditState = auditState;
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
	
}

