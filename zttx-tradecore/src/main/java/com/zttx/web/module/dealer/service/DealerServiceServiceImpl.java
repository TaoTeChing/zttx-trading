/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.module.dealer.entity.DealerService;
import com.zttx.web.module.dealer.mapper.DealerServiceMapper;

/**
 * 经销商商客服信息 服务实现类
 * <p>File：DealerService.java </p>
 * <p>Title: DealerService </p>
 * <p>Description:DealerService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerServiceServiceImpl extends GenericServiceApiImpl<DealerService> implements DealerServiceService
{

    private DealerServiceMapper dealerServiceMapper;

    @Autowired(required = true)
    public DealerServiceServiceImpl(DealerServiceMapper dealerServiceMapper)
    {
        super(dealerServiceMapper);
        this.dealerServiceMapper = dealerServiceMapper;
    }

    /**
	 * 根据经销商商编号获取客服信息
	 * @author 陈建平
	 * @param brandId
	 * @return
	 */
    @Override
    public DealerService getByDealerId(String dealerId)
    {
    	DealerService filter = new DealerService();
        filter.setDealerId(dealerId);
        List<DealerService> list = dealerServiceMapper.findList(filter);
        if(null!=list && list.size()>0){
        	return list.get(0);
        }
        return null;
    }
    
    /**
     * 根据客服ID修改所有该客服信息
     * @author 陈建平
     * @param brandService
     */
    @Override
    public void updateDealerServiceByUserId(DealerService dealerService){
    	dealerServiceMapper.updateDealerServiceByUserId(dealerService);
    }
}
