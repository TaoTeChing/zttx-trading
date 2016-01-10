/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.FreightCompany;

/**
 * 货运（物流）公司信息 服务接口
 * <p>File：FreightCompanyService.java </p>
 * <p>Title: FreightCompanyService </p>
 * <p>Description:FreightCompanyService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface FreightCompanyService extends GenericServiceApi<FreightCompany>{

	/**
	 * 新增或者修改货运（物流）公司信息
	 * @author 陈建平
	 * @param freightCompany
	 * @throws BusinessException
	 */
    void save(FreightCompany freightCompany) throws BusinessException;
    
    /**
     * 根据物流公司编码获取货运（物流）公司信息
     * @author 陈建平
     * @param freightCode
     * @return
     * @throws BusinessException
     */
    FreightCompany findByCode(String freightCode) throws BusinessException;
    
    /**
     * 根据物流公司名称获取货运（物流）公司信息
     * @author 陈建平
     * @param companyName
     * @return
     * @throws BusinessException
     */
    FreightCompany findByName(String companyName) throws BusinessException;
}
