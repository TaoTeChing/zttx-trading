/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.consts.TagCommonConst;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.entity.BrandRecruit;
import com.zttx.web.module.brand.entity.BrandsDomain;
import com.zttx.web.module.brand.mapper.BrandInviteMapper;
import com.zttx.web.module.brand.mapper.BrandRecruitMapper;
import com.zttx.web.module.brand.mapper.BrandViewContactMapper;
import com.zttx.web.module.brand.service.BrandsDomainService;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.entity.DealerVisited;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import com.zttx.web.module.dealer.mapper.DealerVisitedMapper;
import com.zttx.web.module.dealer.model.DealerVisitedModel;
import com.zttx.web.module.dealer.model.DealerVisitedView;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 经销商浏览记录 服务实现类
 * <p>File：DealerVisited.java </p>
 * <p>Title: DealerVisited </p>
 * <p>Description:DealerVisited </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerVisitedServiceImpl extends GenericServiceApiImpl<DealerVisited> implements DealerVisitedService {

    private DealerVisitedMapper dealerVisitedMapper;

    @Autowired
    private BrandViewContactMapper brandViewContactMapper;
    @Autowired
    private BrandInviteMapper brandInviteMapper;
    @Autowired
    private BrandRecruitMapper brandRecruitMapper;

    @Autowired
    private DealerInfoMapper dealerInfoMapper;

    @Autowired
    private DataDictValueService dataDictValueService;
    @Autowired
    private BrandsDomainService brandsDomainService;

    @Autowired(required = true)
    public DealerVisitedServiceImpl(DealerVisitedMapper dealerVisitedMapper) {
        super(dealerVisitedMapper);
        this.dealerVisitedMapper = dealerVisitedMapper;
    }

    @Override
    public PaginateResult<Map<String, Object>> getDealerVisitedsBy(DealerVisitedModel dealerVisitedModel, Pagination pagination) {
        dealerVisitedModel.setPage(pagination);
        List<Map<String, Object>> mapList = dealerVisitedMapper.getDealerVisitedsPage(dealerVisitedModel);
        if (null != mapList && !mapList.isEmpty()) {
            List<BrandInvite> inviteList = null;
            for (Map<String, Object> item : mapList) {
                item.put("emploeeNumName", dataDictValueService.findDictValueName(DataDictConstant.BRAND_COMPANY_SCOPE, "" + item.get("emploeeNum")));
                item.put("moneyNumName", dataDictValueService.findDictValueName(DataDictConstant.BRAND_TURNOVER, "" + item.get("moneyNum")));
                BrandsDomain brandsDomain = brandsDomainService.selectByBrandesId(MapUtils.getString(item, "brandsId"));
                item.put("domain", brandsDomain.getDomain());
                //查询加盟关系状态
                inviteList = brandInviteMapper.getBrandInviteList(dealerVisitedModel.getDealerId(), MapUtils.getString(item, "brandsId"), null);
                if (null != inviteList && !inviteList.isEmpty()) {
                    item.put("applyState", inviteList.get(0).getApplyState());
                }
                String brandesId = item.get("brandsId").toString();
                String brandId = item.get("brandId").toString();

                //查询是否有招募书
                BrandRecruit _brandRecruit = brandRecruitMapper.findByBrandIdAndBrandesid(brandId, brandesId);
                if (_brandRecruit == null || _brandRecruit.getRecruitState() == null || _brandRecruit.getRecruitState().intValue() == 0) {
                    item.put("brandRecruit", false);
                } else {
                    item.put("brandRecruit", true);
                }
            }
        }
        PaginateResult<Map<String, Object>> mapPaginateResult = new PaginateResult<>(pagination, mapList);
        return mapPaginateResult;
    }

    /**
     * 查询浏览过我的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> search(Pagination pagination, DealerVisitedView info) {
        PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<Map<String, Object>>();
        List<Map<String, Object>> list = dealerVisitedMapper.search(pagination, info);
        if (null != list && !list.isEmpty()) {
            for (Map<String, Object> item : list) {
                item.put("areaName", TagCommonConst.getFullArea(item.get("province"), item.get("city"), item.get("area"), "/"));
                int isExist = brandViewContactMapper.isExist(info.getBrandId(), item.get("id").toString(), null);
                if (isExist > 0) {
                    item.put("isExist", true);
                } else {
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
}
