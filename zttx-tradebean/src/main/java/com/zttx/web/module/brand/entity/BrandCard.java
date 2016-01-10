/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商证书信息 实体对象
 * <p>File：BrandCard.java</p>
 * <p>Title: BrandCard</p>
 * <p>Description:BrandCard</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandCard extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private String brandId;
	/**证书名称*/
	private String certName;
	/**证书所在域名*/
	private String domainName;
	/**证书图片(旧）*/
	private String certPhoto;
	/**证书图片(新)*/
	private String certImage;
	/**证书描述*/
	private String certMark;
	/**建档时间*/
	private Long createTime;
	/**修改时间*/
	private Long updateTime;
	/**上传者IP*/
	private Integer createIp;

	public String getBrandId()
	{
		return this.brandId;
	}

	public void setBrandId(String brandId)
	{
		this.brandId = brandId;
	}

	public String getCertName()
	{
		return this.certName;
	}

	public void setCertName(String certName)
	{
		this.certName = certName;
	}

	public String getDomainName()
	{
		return this.domainName;
	}

	public void setDomainName(String domainName)
	{
		this.domainName = domainName;
	}

	public String getCertPhoto()
	{
		return this.certPhoto;
	}

	public void setCertPhoto(String certPhoto)
	{
		this.certPhoto = certPhoto;
	}

	public String getCertImage()
	{
		return this.certImage;
	}

	public void setCertImage(String certImage)
	{
		this.certImage = certImage;
	}

	public String getCertMark()
	{
		return this.certMark;
	}

	public void setCertMark(String certMark)
	{
		this.certMark = certMark;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}

	public Long getUpdateTime()
	{
		return this.updateTime;
	}

	public void setUpdateTime(Long updateTime)
	{
		this.updateTime = updateTime;
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

