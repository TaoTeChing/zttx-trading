/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.FreightCompany;
import com.zttx.web.module.common.mapper.FreightCompanyMapper;

/**
 * 货运（物流）公司信息 服务实现类
 * <p>File：FreightCompany.java </p>
 * <p>Title: FreightCompany </p>
 * <p>Description:FreightCompany </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class FreightCompanyServiceImpl extends GenericServiceApiImpl<FreightCompany> implements FreightCompanyService
{

    private FreightCompanyMapper freightCompanyMapper;

    @Autowired(required = true)
    public FreightCompanyServiceImpl(FreightCompanyMapper freightCompanyMapper)
    {
        super(freightCompanyMapper);
        this.freightCompanyMapper = freightCompanyMapper;
    }
    
    /**
	 * 新增或者修改货运（物流）公司信息
	 * @author 陈建平
	 * @param freightCompany
	 * @throws BusinessException
	 */
    @Override
    public void save(FreightCompany freightCompany) throws BusinessException{
    	if (freightCompany == null) throw new BusinessException(CommonConst.FAIL);
        FreightCompany entity = null;
        if (freightCompany.getRefrenceId() != null)
        {
            entity = findByCode(freightCompany.getFreightCode());
            if (entity != null && !entity.getRefrenceId().equals(freightCompany.getRefrenceId())) { 
            	throw new BusinessException(CommonConst.FREIGHTCOMPANY_INSERT_ERROR1); 
            }
            entity = findByName(freightCompany.getCompanyName());
            if (entity != null && !entity.getRefrenceId().equals(freightCompany.getRefrenceId())) { 
            	throw new BusinessException(CommonConst.FREIGHTCOMPANY_INSERT_ERROR2); 
            }
            freightCompanyMapper.updateByPrimaryKey(freightCompany);
        }
        else
        {
            if (findByCode(freightCompany.getFreightCode()) != null) { 
            	throw new BusinessException(CommonConst.FREIGHTCOMPANY_INSERT_ERROR1); 
            }
            if (findByName(freightCompany.getCompanyName()) != null) { 
            	throw new BusinessException(CommonConst.FREIGHTCOMPANY_INSERT_ERROR2); 
            }
            freightCompany.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            freightCompanyMapper.insert(freightCompany);
        }
    }
    
    /**
     * 根据物流公司编码获取货运（物流）公司信息
     * @author 陈建平
     * @param freightCode
     * @return
     * @throws BusinessException
     */
    @Override
    public FreightCompany findByCode(String freightCode) throws BusinessException
    {
    	if(StringUtils.isNotBlank(freightCode)){
        	FreightCompany filter = new FreightCompany();
        	filter.setCompanyName(freightCode);
        	List<FreightCompany> list = freightCompanyMapper.findFreightCompany(filter);
        	if(null != list && list.size()>0){
        		return list.get(0);
        	}
        }
        return null;
    }
    
    /**
     * 根据物流公司名称获取货运（物流）公司信息
     * @author 陈建平
     * @param companyName
     * @return
     * @throws BusinessException
     */
    @Override
    public FreightCompany findByName(String companyName) throws BusinessException
    {
        if(StringUtils.isNotBlank(companyName)){
        	FreightCompany filter = new FreightCompany();
        	filter.setCompanyName(companyName);
        	List<FreightCompany> list = freightCompanyMapper.findFreightCompany(filter);
        	if(null != list && list.size()>0){
        		return list.get(0);
        	}
        }
        return null;
    }
}
