/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.module.brand.entity.BrandRecruit;
import com.zttx.web.module.brand.mapper.BrandRecruitMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌招募书 服务实现类
 * <p>File：BrandRecruit.java </p>
 * <p>Title: BrandRecruit </p>
 * <p>Description:BrandRecruit </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandRecruitServiceImpl extends GenericServiceApiImpl<BrandRecruit> implements BrandRecruitService
{

	@Autowired
	private BrandesInfoService brandesInfoService;
	
    private BrandRecruitMapper brandRecruitMapper;

    @Autowired(required = true)
    public BrandRecruitServiceImpl(BrandRecruitMapper brandRecruitMapper)
    {
        super(brandRecruitMapper);
        this.brandRecruitMapper = brandRecruitMapper;
    }

    @Override
    public BrandRecruit findByBrandIdAndBrandesid(String brandId, String brandesId)
    {
        return brandRecruitMapper.findByBrandIdAndBrandesid(brandId,brandesId);
    }

    @Override
    public void saveOrUpdate(BrandRecruit brandRecruit) throws BusinessException
    {
    	Short[] brandStates = {BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED, BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED};
        // 判断当前状态是不是通进行操作
    	brandesInfoService.validatorState(brandRecruit.getBrandId(), brandRecruit.getRefrenceId(), brandStates);
        BrandRecruit recruit = brandRecruitMapper.selectByPrimaryKey(brandRecruit.getRefrenceId());
        if (null == recruit)
        {
            brandRecruit.setCreateTime(CalendarUtils.getCurrentLong());
            brandRecruitMapper.insert(brandRecruit);
        }
        else
        {
            recruit.setUpdateTime(CalendarUtils.getCurrentLong());
            recruit.setUserId(brandRecruit.getUserId());
            recruit.setRecruitTitle(brandRecruit.getRecruitTitle());
            recruit.setDealBrand(brandRecruit.getDealBrand());
            recruit.setDealExper(brandRecruit.getDealExper());
            recruit.setDealShop(brandRecruit.getDealShop());
            recruit.setDealYear(brandRecruit.getDealYear());
            recruit.setRecruitState(brandRecruit.getRecruitState());
            recruit.setRecruitText(brandRecruit.getRecruitText());
            brandRecruitMapper.updateByPrimaryKey(recruit);
        }
    }



}
