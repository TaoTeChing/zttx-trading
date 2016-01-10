/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 转账记录表 实体对象
 * <p>File：DepositBack.java</p>
 * <p>Title: DepositBack</p>
 * <p>Description:DepositBack</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DepositBack extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**流水号*/
	private java.lang.String exId;
	/**支付平台转账记录ID*/
	private java.lang.Long billId;
	/**品牌商id*/
	private java.lang.String brandId;
	/**品牌商名称*/
	private java.lang.String brandName;
	/**品牌id*/
	private java.lang.String brandsId;
	/**品牌名称*/
	private java.lang.String brandsName;
	/**店铺id*/
	private java.lang.String dealerId;
	/**店铺名称*/
	private java.lang.String dealerName;
	/**终端商已缴纳押金*/
	private java.math.BigDecimal paidAmount;
	/**品牌商退回押金*/
	private java.math.BigDecimal backAmount;
	/**退回时间*/
	private java.lang.Long depositBackTime;
	/**状态 0冻结 1已解冻*/
	private java.lang.Short status = 0;
	/**加盟合作id*/
	private java.lang.String dealerjoinId;
	/**转账类型（0：退押金）*/
	private java.lang.Short type;
	/**操作人ID*/
	private java.lang.String operateUserId;
	/**操作人*/
	private java.lang.String operateUserName;
	/**操作时间*/
	private java.lang.Long operateTime;
	
	public java.lang.String getExId()
	{
		return this.exId;
	}
	
	public void setExId(java.lang.String exId)
	{
		this.exId = exId;
	}
	
	public java.lang.Long getBillId()
	{
		return this.billId;
	}
	
	public void setBillId(java.lang.Long billId)
	{
		this.billId = billId;
	}
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
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
	
	public java.math.BigDecimal getPaidAmount()
	{
		return this.paidAmount;
	}
	
	public void setPaidAmount(java.math.BigDecimal paidAmount)
	{
		this.paidAmount = paidAmount;
	}
	
	public java.math.BigDecimal getBackAmount()
	{
		return this.backAmount;
	}
	
	public void setBackAmount(java.math.BigDecimal backAmount)
	{
		this.backAmount = backAmount;
	}
	
	public java.lang.Long getDepositBackTime()
	{
		return this.depositBackTime;
	}
	
	public void setDepositBackTime(java.lang.Long depositBackTime)
	{
		this.depositBackTime = depositBackTime;
	}
	
	public java.lang.Short getStatus()
	{
		return this.status;
	}
	
	public void setStatus(java.lang.Short status)
	{
		this.status = status;
	}
	
	public java.lang.String getDealerjoinId()
	{
		return this.dealerjoinId;
	}
	
	public void setDealerjoinId(java.lang.String dealerjoinId)
	{
		this.dealerjoinId = dealerjoinId;
	}
	
	public java.lang.Short getType()
	{
		return this.type;
	}
	
	public void setType(java.lang.Short type)
	{
		this.type = type;
	}
	
	public java.lang.String getOperateUserId()
	{
		return this.operateUserId;
	}
	
	public void setOperateUserId(java.lang.String operateUserId)
	{
		this.operateUserId = operateUserId;
	}
	
	public java.lang.String getOperateUserName()
	{
		return this.operateUserName;
	}
	
	public void setOperateUserName(java.lang.String operateUserName)
	{
		this.operateUserName = operateUserName;
	}
	
	public java.lang.Long getOperateTime()
	{
		return this.operateTime;
	}
	
	public void setOperateTime(java.lang.Long operateTime)
	{
		this.operateTime = operateTime;
	}
	
}

