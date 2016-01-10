/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 物流详细信息 实体对象
 * <p>File：LogisticsInfo.java</p>
 * <p>Title: LogisticsInfo</p>
 * <p>Description:LogisticsInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class LogisticsInfo extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**物流编号*/
	private java.lang.String nu;
	/**物流公司*/
	private java.lang.String com;
	/**查询结果：0：物流单暂无结果，1：查询成功，2：接口出现异常
*/
	private java.lang.Integer status;
	/**快递单当前的状态 ：0：在途，即货物处于运输过程中；1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息；2：疑难，货物寄送过程出了问题；3：签收，收件人已签收；4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收；5：派件，即快递正在进行同城派件；6：退回，货物正处于退回发件人的途中；*/
	private java.lang.Integer state;
	/**物流信息 以JSON格式存储
*/
	private java.lang.String data;
	/**创建时间
*/
	private java.lang.Long createTime;
	/**最后修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getNu()
	{
		return this.nu;
	}
	
	public void setNu(java.lang.String nu)
	{
		this.nu = nu;
	}
	
	public java.lang.String getCom()
	{
		return this.com;
	}
	
	public void setCom(java.lang.String com)
	{
		this.com = com;
	}
	
	public java.lang.Integer getStatus()
	{
		return this.status;
	}
	
	public void setStatus(java.lang.Integer status)
	{
		this.status = status;
	}
	
	public java.lang.Integer getState()
	{
		return this.state;
	}
	
	public void setState(java.lang.Integer state)
	{
		this.state = state;
	}
	
	public java.lang.String getData()
	{
		return this.data;
	}
	
	public void setData(java.lang.String data)
	{
		this.data = data;
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
	
}

