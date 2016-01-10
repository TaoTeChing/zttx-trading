/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.consts.TagCommonConst;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandRecruit;
import com.zttx.web.module.brand.entity.BrandVisited;
import com.zttx.web.module.brand.mapper.BrandRecruitMapper;
import com.zttx.web.module.brand.mapper.BrandViewContactMapper;
import com.zttx.web.module.brand.mapper.BrandVisitedMapper;
import com.zttx.web.module.brand.model.BrandVisitedModel;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.GlobalStaticTextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 品牌商浏览记录 服务实现类
 * <p>File：BrandVisited.java </p>
 * <p>Title: BrandVisited </p>
 * <p>Description:BrandVisited </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandVisitedServiceImpl extends GenericServiceApiImpl<BrandVisited> implements BrandVisitedService
{
    private BrandVisitedMapper     brandVisitedMapper;
    
    @Autowired
    private BrandViewContactMapper brandViewContactMapper;
    
    @Autowired
    private DealerInfoMapper       dealerInfoMapper;
    @Autowired
    private BrandRecruitMapper brandRecruitMapper;
    
    @Autowired
    private BrandInfoService       brandInfoService;
    
    @Autowired
    private DealerInfoService      dealerInfoService;
    
    @Autowired
    private DataDictValueService   dataDictValueService;
    
    @Autowired(required = true)
    public BrandVisitedServiceImpl(BrandVisitedMapper brandVisitedMapper)
    {
        super(brandVisitedMapper);
        this.brandVisitedMapper = brandVisitedMapper;
    }
    
    @Override
    public PaginateResult<Map<String, Object>> getBrandVisitedPage(Pagination pagination, BrandVisitedModel brandVisitedModel)
    {
        brandVisitedModel.setPage(pagination);
        List<Map<String, Object>> mapList = brandVisitedMapper.getBrandVisitedPage(brandVisitedModel);
        if (null != mapList && !mapList.isEmpty())
        {
            for (Map<String, Object> item : mapList)
            {
                item.put("emploeeNumName", dataDictValueService.findDictValueName(DataDictConstant.BRAND_COMPANY_SCOPE, "" + item.get("emploeeNum")));
                item.put("moneyNumName", dataDictValueService.findDictValueName(DataDictConstant.BRAND_TURNOVER, "" + item.get("moneyNum")));
            }
        }
        PaginateResult<Map<String, Object>> mapPaginateResult = new PaginateResult<>(pagination, mapList);
        return mapPaginateResult;
    }
    
    /**
     * 我浏览过的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> search(Pagination pagination, BrandVisited info)
    {
        PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<Map<String, Object>>();
        List<Map<String, Object>> list = brandVisitedMapper.search(pagination, info);
        if (null != list && !list.isEmpty())
        {
            for (Map<String, Object> item : list)
            {
                item.put("areaName", TagCommonConst.getFullArea(item.get("province"), item.get("city"), item.get("area"), "/"));
                int isExist = brandViewContactMapper.isExist(info.getBrandId(), item.get("id").toString(), null);
                if (isExist > 0)
                {
                    item.put("isExist", true);
                }
                else
                {
                    item.put("isExist", false);
                }
                String brandName = dealerInfoMapper.searchBrandsNameList((String) item.get("id"));
                item.put("brandName", brandName);
            }
            paginateResult.setList(list);
        }
        paginateResult.setPage(pagination);
        return paginateResult;
    }
    
    /*
     * 保存浏览次数，如果存在就覆盖否则新增
     */
    @Override
    public void insert(String brandId, String dealerId) throws BusinessException
    {
        Integer isExits = brandViewContactMapper.isExist(brandId, dealerId, null);
        if (isExits <= 0)
        {
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
            DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
            if (null != dealerInfo)
            {
                BrandVisited brandVisited = new BrandVisited();
                brandVisited.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                brandVisited.setBrandId(brandId);
                brandVisited.setBrandName(brandInfo.getComName());
                brandVisited.setDealerId(dealerId);
                brandVisited.setDealerName(dealerInfo.getDealerName());
                brandVisited.setAreaNo(dealerInfo.getAreaNo());
                brandVisited.setProvinceName(dealerInfo.getProvinceName());
                brandVisited.setCityName(dealerInfo.getCityName());
                brandVisited.setAreaName(dealerInfo.getAreaName());
                brandVisited.setViewNum(0);
                brandVisited.setViewTime(CalendarUtils.getCurrentLong());
                brandVisitedMapper.insert(brandVisited);
            }
        }
        else
        {
            brandVisitedMapper.updateBrandView(brandId, dealerId);
        }
    }


    /**
     * 查询申请列表
     *
     * @param dealerId
     * @param brandId
     * @return
     */
    @Override
    public List<Map<String, Object>> getBrandApplyList(String dealerId, String brandId)
    {
        List<Map<String, Object>> list = brandVisitedMapper.getBrandApplyList(dealerId, brandId);
        for (Map<String, Object> item : list)
        {
            String brandesId = item.get("refrenceId").toString();
            String domain = GlobalStaticTextUtils.getBrandsIdSubDomain(brandesId);
            item.put("domain", domain);
            BrandRecruit _brandRecruit = brandRecruitMapper.findByBrandIdAndBrandesid(brandId, brandesId);
            if (_brandRecruit == null || _brandRecruit.getRecruitState() == null || _brandRecruit.getRecruitState().intValue() == 0)
            {
                item.put("brandRecruit", false);
            }
            else
            {
                item.put("brandRecruit", true);
            }
        }
        return list;
    }

}
