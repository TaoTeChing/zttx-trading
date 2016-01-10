/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商经营品类l 实体对象
 * <p>File：DealerClass.java</p>
 * <p>Title: DealerClass</p>
 * <p>Description:DealerClass</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerClass extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
    
    // Constructors
    /**
     * default constructor
     */
    public DealerClass()
    {
    }
    
    /**
     * full constructor
     */
    public DealerClass(String refrenceId, String dealerId, Integer dealNo, Long createTime, Integer createIp, Boolean delFlag)
    {
        this.refrenceId = refrenceId;
        this.dealerId = dealerId;
        this.dealNo = dealNo;
        this.createTime = createTime;
        this.createIp = createIp;
        this.delFlag = delFlag;
    }
    
    /**
     * @param dealerId
     * @param dealNo
     * @param createIp
     */
    public DealerClass(String dealerId, Integer dealNo, Integer createIp)
    {
        super();
        this.dealerId = dealerId;
        this.dealNo = dealNo;
        this.createIp = createIp;
    }
	
	/**经销商编号*/
	private java.lang.String dealerId;
	/**品类编码*/
	private java.lang.Integer dealNo;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**建档者IP*/
	private java.lang.Integer createIp;
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.Integer getDealNo()
	{
		return this.dealNo;
	}
	
	public void setDealNo(java.lang.Integer dealNo)
	{
		this.dealNo = dealNo;
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

