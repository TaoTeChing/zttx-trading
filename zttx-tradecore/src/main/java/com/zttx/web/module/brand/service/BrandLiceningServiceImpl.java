/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandLicening;
import com.zttx.web.module.brand.mapper.BrandLiceningMapper;

/**
 * 品牌授权证书 服务实现类
 * <p>File：BrandLicening.java </p>
 * <p>Title: BrandLicening </p>
 * <p>Description:BrandLicening </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandLiceningServiceImpl extends GenericServiceApiImpl<BrandLicening> implements BrandLiceningService
{

    private BrandLiceningMapper brandLiceningMapper;

    @Autowired(required = true)
    public BrandLiceningServiceImpl(BrandLiceningMapper brandLiceningMapper)
    {
        super(brandLiceningMapper);
        this.brandLiceningMapper = brandLiceningMapper;
    }
    
    /**
	 * 根据品牌商编号和品牌编号批量修改删除标志为删除状态
	 * @author 陈建平
	 * @param brandId
	 * @param brandsId
	 */
    @Override
    public void updateDelState(String brandId, String brandsId){
    	brandLiceningMapper.updateDelState(true,brandId, brandsId);
    }
    
    /**
     * 根据品牌商编号、品牌编号、删除标志
     * @author 陈建平
     * @param brandId
     * @param brandsId
     * @param delState
     * @return
     */
    @Override
    public List<BrandLicening> findByBrandsId(String brandId, String brandsId, Boolean delState){
    	return brandLiceningMapper.findByBrandsId(brandId, brandsId, delState);
    }
}
