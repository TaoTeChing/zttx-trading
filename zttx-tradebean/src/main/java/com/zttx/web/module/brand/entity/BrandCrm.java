/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商更新信息表CRM 实体对象
 * <p>File：BrandCrm.java</p>
 * <p>Title: BrandCrm</p>
 * <p>Description:BrandCrm</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandCrm extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**json组合*/
	private String json;
	/**组合类型
            1:branduserm
            2:brandInfo
            3:brandcontact
            4:dealeruserm
            5:dealerinfo
            6:brandesInfo*/
	private Short jsonType;
	/**生成时间*/
	private Long createTime;
	/**1：未获取
            2：已获取*/
	private Short brandState;

	public String getJson()
	{
		return this.json;
	}

	public void setJson(String json)
	{
		this.json = json;
	}

	public Short getJsonType()
	{
		return this.jsonType;
	}

	public void setJsonType(Short jsonType)
	{
		this.jsonType = jsonType;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}

	public Short getBrandState()
	{
		return this.brandState;
	}

	public void setBrandState(Short brandState)
	{
		this.brandState = brandState;
	}
	
}

