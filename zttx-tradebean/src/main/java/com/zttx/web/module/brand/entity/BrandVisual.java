/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌视觉信息 实体对象
 * <p>File：BrandVisual.java</p>
 * <p>Title: BrandVisual</p>
 * <p>Description:BrandVisual</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandVisual extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**服务器域名*/
	private java.lang.String domainName;
	/**视频原文件*/
	private java.lang.String vedioDoc;
	/**视频文件*/
	private java.lang.String vedioFile;
	/**3D原文件*/
	private java.lang.String threeDoc;
	/**3D展厅*/
	private java.lang.String threeFile;
	/**描述内容*/
	private java.lang.String brandMark;
	/**上传时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**上传者IP*/
	private java.lang.Integer createIp;
	
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
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getVedioDoc()
	{
		return this.vedioDoc;
	}
	
	public void setVedioDoc(java.lang.String vedioDoc)
	{
		this.vedioDoc = vedioDoc;
	}
	
	public java.lang.String getVedioFile()
	{
		return this.vedioFile;
	}
	
	public void setVedioFile(java.lang.String vedioFile)
	{
		this.vedioFile = vedioFile;
	}
	
	public java.lang.String getThreeDoc()
	{
		return this.threeDoc;
	}
	
	public void setThreeDoc(java.lang.String threeDoc)
	{
		this.threeDoc = threeDoc;
	}
	
	public java.lang.String getThreeFile()
	{
		return this.threeFile;
	}
	
	public void setThreeFile(java.lang.String threeFile)
	{
		this.threeFile = threeFile;
	}
	
	public java.lang.String getBrandMark()
	{
		return this.brandMark;
	}
	
	public void setBrandMark(java.lang.String brandMark)
	{
		this.brandMark = brandMark;
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
	
	public java.lang.Integer getCreateIp()
	{
		return this.createIp;
	}
	
	public void setCreateIp(java.lang.Integer createIp)
	{
		this.createIp = createIp;
	}
	
}

