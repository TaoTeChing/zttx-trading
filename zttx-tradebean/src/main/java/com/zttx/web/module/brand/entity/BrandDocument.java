/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商资料信息 实体对象
 * <p>File：BrandDocument.java</p>
 * <p>Title: BrandDocument</p>
 * <p>Description:BrandDocument</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandDocument extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**资料类别(对应BrandDoccate的主键编号）*/
	private java.lang.String cateId;
	/**资料名称*/
	private java.lang.String docName;
	/**文档域名*/
	private java.lang.String domainName;
	/**资料文档(旧名)*/
	private java.lang.String docoFile;
	/**资料文档(新名)*/
	private java.lang.String docnFile;
	/**网盘地址*/
	private java.lang.String webAddress;
	/**资料密码*/
	private java.lang.String docPass;
	/**内容描述*/
	private java.lang.String docMark;
	/**完全公开*/
	private java.lang.Boolean docOpen;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**上传者IP地址*/
	private java.lang.Integer createIp;
	/**下载次数*/
	private java.lang.Integer downNum;
	
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
	
	public java.lang.String getCateId()
	{
		return this.cateId;
	}
	
	public void setCateId(java.lang.String cateId)
	{
		this.cateId = cateId;
	}
	
	public java.lang.String getDocName()
	{
		return this.docName;
	}
	
	public void setDocName(java.lang.String docName)
	{
		this.docName = docName;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getDocoFile()
	{
		return this.docoFile;
	}
	
	public void setDocoFile(java.lang.String docoFile)
	{
		this.docoFile = docoFile;
	}
	
	public java.lang.String getDocnFile()
	{
		return this.docnFile;
	}
	
	public void setDocnFile(java.lang.String docnFile)
	{
		this.docnFile = docnFile;
	}
	
	public java.lang.String getWebAddress()
	{
		return this.webAddress;
	}
	
	public void setWebAddress(java.lang.String webAddress)
	{
		this.webAddress = webAddress;
	}
	
	public java.lang.String getDocPass()
	{
		return this.docPass;
	}
	
	public void setDocPass(java.lang.String docPass)
	{
		this.docPass = docPass;
	}
	
	public java.lang.String getDocMark()
	{
		return this.docMark;
	}
	
	public void setDocMark(java.lang.String docMark)
	{
		this.docMark = docMark;
	}
	
	public java.lang.Boolean getDocOpen()
	{
		return this.docOpen;
	}
	
	public void setDocOpen(java.lang.Boolean docOpen)
	{
		this.docOpen = docOpen;
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
	
	public java.lang.Integer getDownNum()
	{
		return this.downNum;
	}
	
	public void setDownNum(java.lang.Integer downNum)
	{
		this.downNum = downNum;
	}
	
}

