/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.FreightCompany;

/**
 * 货运（物流）公司信息 持久层接口
 * <p>File：FreightCompanyDao.java </p>
 * <p>Title: FreightCompanyDao </p>
 * <p>Description:FreightCompanyDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface FreightCompanyMapper extends GenericMapper<FreightCompany>
{

	/**
	 * 获取货运（物流）公司信息 
	 * @author 陈建平
	 * @param filter
	 * @return
	 */
	List<FreightCompany> findFreightCompany(FreightCompany filter);
}
