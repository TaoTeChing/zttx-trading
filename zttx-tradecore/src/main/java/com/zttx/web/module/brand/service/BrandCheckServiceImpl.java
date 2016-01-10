/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandCheck;
import com.zttx.web.module.brand.mapper.BrandCheckMapper;
import com.zttx.web.module.dealer.model.DealerJoinModel;
import com.zttx.web.utils.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌商审核经销商加盟申请日志 服务实现类
 * <p>File：BrandCheck.java </p>
 * <p>Title: BrandCheck </p>
 * <p>Description:BrandCheck </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandCheckServiceImpl extends GenericServiceApiImpl<BrandCheck> implements BrandCheckService
{

    private BrandCheckMapper brandCheckMapper;

    @Autowired(required = true)
    public BrandCheckServiceImpl(BrandCheckMapper brandCheckMapper)
    {
        super(brandCheckMapper);
        this.brandCheckMapper = brandCheckMapper;
    }

    /**
     * 保存品牌验证信息
     *
     * @param dealerJoin
     * @return
     */
    @Override
    public BrandCheck insertBrandCheck(DealerJoinModel dealerJoin) {
        BrandCheck brandCheck = new BrandCheck();
        brandCheck.setBrandId(dealerJoin.getBrandId());
        brandCheck.setBrandsId(dealerJoin.getBrandsId());
        brandCheck.setCheckState(dealerJoin.getCheckState());
        brandCheck.setCheckTime(dealerJoin.getJoinTime());
        brandCheck.setDealerId(dealerJoin.getDealerId());
        brandCheck.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        brandCheck.setCreateTime(CalendarUtils.getCurrentLong());
        brandCheck.setDelFlag(false);
        brandCheck.setUpdateTime(CalendarUtils.getCurrentLong());
        brandCheckMapper.insert(brandCheck);
        return brandCheck;
    }
}
