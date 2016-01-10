/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 支付密码修改历史 实体对象
 * <p>File：BrandPaylog.java</p>
 * <p>Title: BrandPaylog</p>
 * <p>Description:BrandPaylog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandPaylog extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private String brandId;
	/**原密码*/
	private String oldWord;
	/**新密码*/
	private String newWord;
	/**旧盐值*/
	private String oldSalt;
	/**新盐值*/
	private String newSalt;
	/**修改时间*/
	private Long createTime;
	/**修改类型*/
	private Short createType;
	/**修改者IP*/
	private Integer createIp;

	public String getBrandId()
	{
		return this.brandId;
	}

	public void setBrandId(String brandId)
	{
		this.brandId = brandId;
	}

	public String getOldWord()
	{
		return this.oldWord;
	}

	public void setOldWord(String oldWord)
	{
		this.oldWord = oldWord;
	}

	public String getNewWord()
	{
		return this.newWord;
	}

	public void setNewWord(String newWord)
	{
		this.newWord = newWord;
	}

	public String getOldSalt()
	{
		return this.oldSalt;
	}

	public void setOldSalt(String oldSalt)
	{
		this.oldSalt = oldSalt;
	}

	public String getNewSalt()
	{
		return this.newSalt;
	}

	public void setNewSalt(String newSalt)
	{
		this.newSalt = newSalt;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}

	public Short getCreateType()
	{
		return this.createType;
	}

	public void setCreateType(Short createType)
	{
		this.createType = createType;
	}

	public Integer getCreateIp()
	{
		return this.createIp;
	}

	public void setCreateIp(Integer createIp)
	{
		this.createIp = createIp;
	}
	
}

