/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandDocopen;
import com.zttx.web.module.brand.mapper.BrandDocopenMapper;

/**
 * 品牌商资料公开 服务实现类
 * <p>File：BrandDocopen.java </p>
 * <p>Title: BrandDocopen </p>
 * <p>Description:BrandDocopen </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandDocopenServiceImpl extends GenericServiceApiImpl<BrandDocopen> implements BrandDocopenService
{

    private BrandDocopenMapper brandDocopenMapper;

    @Autowired(required = true)
    public BrandDocopenServiceImpl(BrandDocopenMapper brandDocopenMapper)
    {
        super(brandDocopenMapper);
        this.brandDocopenMapper = brandDocopenMapper;
    }
    
    /**
     * 删除品牌商资料和经销商关联数据（物理删除）
     * @param brandId       品牌部编号
     * @param docId         资料编号
     * @return
     */
    @Override
    public void deleteDocopen(String brandId, String docId){
    	brandDocopenMapper.deleteDocopen(brandId, docId);
    }
    
    /**
     * 批量删除
     * @author 陈建平
     * @param list
     */
    @Override
    public void deleteBatch(List<String> list){
    	if(null != list&&list.size()>0){
    		brandDocopenMapper.deleteBatch(list);
    	}
    }
    
    /**
     * 根据品牌商编号，品牌编号和资料编号 获取允许查看的资料的终端商ID
     * @author 陈建平
     * @param brandId 品牌商ID
     * @param brandsId 品牌ID
     * @param docId 资料ID
     * @return
     */
    @Override
    public List<String> getDocDealerIdList(String brandId,String brandsId,String docId){
    	return brandDocopenMapper.getDocDealerIdList(brandId, brandsId, docId);
    }
}
