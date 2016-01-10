/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商等级 实体对象
 * <p>File：BrandLevel.java</p>
 * <p>Title: BrandLevel</p>
 * <p>Description:BrandLevel</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandLevel extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**等级名称*/
	private java.lang.String levelName;
	/**等级描述*/
	private java.lang.String levelMark;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	private BrandesInfo brandesInfo;
	
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
	
	public java.lang.String getLevelName()
	{
		return this.levelName;
	}
	
	public void setLevelName(java.lang.String levelName)
	{
		this.levelName = levelName;
	}
	
	public java.lang.String getLevelMark()
	{
		return this.levelMark;
	}
	
	public void setLevelMark(java.lang.String levelMark)
	{
		this.levelMark = levelMark;
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

    public BrandesInfo getBrandesInfo()
    {
        return brandesInfo;
    }

    public void setBrandesInfo(BrandesInfo brandesInfo)
    {
        this.brandesInfo = brandesInfo;
    }
}

