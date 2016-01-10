package com.zttx.web.module.dealer.entity;
import com.zttx.sdk.core.GenericEntity;
/**
 * DealerClearingPay 实体对象
 * <p>File：DealerClearingPay.java</p>
 * <p>Title: DealerClearingPay</p>
 * <p>Description:DealerClearingPay</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerClearingPay extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/**主键*/
	private java.lang.String refrenceId;
	/**经销商Id*/
	private java.lang.String dealerId;
	/**品牌商Id*/
	private java.lang.String brandId;
	/**当期支付金额*/
	private java.math.BigDecimal payClearingAmount;
	/**创建时间*/
	private java.lang.Long createTime;

	public java.lang.String getRefrenceId()
	{
		return this.refrenceId;
	}

	public void setRefrenceId(java.lang.String refrenceId)
	{
		this.refrenceId = refrenceId;
	}

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

	public java.math.BigDecimal getPayClearingAmount()
	{
		return this.payClearingAmount;
	}

	public void setPayClearingAmount(java.math.BigDecimal payClearingAmount)
	{
		this.payClearingAmount = payClearingAmount;
	}

	public java.lang.Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(java.lang.Long createTime)
	{
		this.createTime = createTime;
	}


}

