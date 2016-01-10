/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.module.brand.entity.BrandsDomain;
import com.zttx.web.module.brand.mapper.BrandsDomainMapper;

/**
 * 品牌域名 服务实现类
 * <p>File：BrandsDomain.java </p>
 * <p>Title: BrandsDomain </p>
 * <p>Description:BrandsDomain </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandsDomainServiceImpl extends GenericServiceApiImpl<BrandsDomain> implements BrandsDomainService
{

    private BrandsDomainMapper brandsDomainMapper;

    @Autowired(required = true)
    public BrandsDomainServiceImpl(BrandsDomainMapper brandsDomainMapper)
    {
        super(brandsDomainMapper);
        this.brandsDomainMapper = brandsDomainMapper;
    }

    /**查找指定品牌商下的 所有域名对象
     * @param brandId
     * @return
     */
    @Override
    public List<Map<String,Object>> getBrandsDomainsByBrandId(String brandId){
    	BrandsDomain filter = new BrandsDomain();
    	filter.setBrandId(brandId);
    	return brandsDomainMapper.getBrandsDomainsByBrandId(filter);
    }
    
    /**
     * 更新指定 品牌的域名值 
     * @param brandsDomain
     * @return
     */
    @Override
    public boolean updateBrandsDomainValue(BrandsDomain brandsDomain){
    	if (!isExistDomains(brandsDomain.getRefrenceId(), brandsDomain.getDomain())){
			BrandsDomain temp = brandsDomainMapper.selectByPrimaryKey(brandsDomain.getRefrenceId());
			if(temp!=null){
				temp.setUpdateTime(CalendarUtils.getCurrentLong());
				temp.setDomain(brandsDomain.getDomain());
        		brandsDomainMapper.updateByPrimaryKeySelective(temp);
			}else{
				brandsDomain.setCreateTime(CalendarUtils.getCurrentLong());
    			brandsDomainMapper.insert(brandsDomain);
			}
    		return true;
    	}
    	return false;
    }
    
    /**
     * 判断域名是否存在
     * @param brandsId 品牌编号
     * @param domain
     * @return
     */
    @Override
    public boolean isExistDomains(String brandsId,String domain){
    	List<BrandsDomain> list = brandsDomainMapper.isExistDomains(brandsId,domain);
    	if (CollectionUtils.isEmpty(list)) { return false; }
        return true;
    }

    /**
     * 根据品牌二级域名前缀 查询 BrandsDomain 记录
     * @param domain 二级域名前缀
     * @return
     */
    @Override
    public BrandsDomain findByDomain(String domain)
    {
        if (StringUtils.isNotBlank(domain)){
            BrandsDomain params = new BrandsDomain();
            params.setDomain(domain);
            List<BrandsDomain> result = brandsDomainMapper.findList(params);
            if (CollectionUtils.isNotEmpty(result)){
                return result.get(0);
            }
        }
        return null;
    }

    @Override
    public BrandsDomain selectByBrandesId(String brandesId)
    {        
    	BrandsDomain params = new BrandsDomain();
        params.setBrandsId(brandesId);
        List<BrandsDomain> result = brandsDomainMapper.findList(params);
        if (CollectionUtils.isNotEmpty(result)){
            return result.get(0);
        }
        return null;
    }
}
