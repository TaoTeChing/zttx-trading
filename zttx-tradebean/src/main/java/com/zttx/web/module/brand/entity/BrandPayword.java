/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商支付密码 实体对象
 * <p>File：BrandPayword.java</p>
 * <p>Title: BrandPayword</p>
 * <p>Description:BrandPayword</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandPayword extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**手机号码*/
	private String dealerTel;
	/**支付密码*/
	private String payWord;
	/**密码盐值*/
	private String paySalt;
	/**建档时间*/
	private Long createTime;
	/**建档者IP*/
	private Integer createIp;
	/**迁移标记*/
	private Integer flag;

	public String getDealerTel()
	{
		return this.dealerTel;
	}

	public void setDealerTel(String dealerTel)
	{
		this.dealerTel = dealerTel;
	}

	public String getPayWord()
	{
		return this.payWord;
	}

	public void setPayWord(String payWord)
	{
		this.payWord = payWord;
	}

	public String getPaySalt()
	{
		return this.paySalt;
	}

	public void setPaySalt(String paySalt)
	{
		this.paySalt = paySalt;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}

	public Integer getCreateIp()
	{
		return this.createIp;
	}

	public void setCreateIp(Integer createIp)
	{
		this.createIp = createIp;
	}

	public Integer getFlag()
	{
		return this.flag;
	}

	public void setFlag(Integer flag)
	{
		this.flag = flag;
	}
	
}

