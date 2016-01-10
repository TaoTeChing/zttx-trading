package com.zttx.web.module.common.model;

import com.zttx.sdk.core.GenericEntity;

/**
 * 转账搜索bean
 * <p>File：TransferModel.java</p>
 * <p>Title: TransferModel</p>
 * <p>Description:TransferModel</p>
 * <p>Copyright: Copyright (c) Jul 13, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public class TransferSearchModel extends GenericEntity
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String dealerName;
	private String userMobile;
	private String comName;
	private String brandsName;
	private String brandId;
	private String dealerId;
	
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getBrandsName() {
		return brandsName;
	}
	public void setBrandsName(String brandsName) {
		this.brandsName = brandsName;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
}
