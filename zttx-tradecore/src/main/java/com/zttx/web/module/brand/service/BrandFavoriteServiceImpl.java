/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.TagCommonConst;
import com.zttx.web.module.brand.entity.BrandFavorite;
import com.zttx.web.module.brand.mapper.BrandFavoriteMapper;
import com.zttx.web.module.brand.mapper.BrandViewContactMapper;
import com.zttx.web.module.brand.model.BrandFavoriteView;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import com.zttx.web.utils.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 品牌商收藏的经销商 服务实现类
 * <p>File：BrandFavorite.java </p>
 * <p>Title: BrandFavorite </p>
 * <p>Description:BrandFavorite </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandFavoriteServiceImpl extends GenericServiceApiImpl<BrandFavorite> implements BrandFavoriteService {

    private BrandFavoriteMapper brandFavoriteMapper;

    @Autowired
    private BrandViewContactMapper brandViewContactMapper;
    @Autowired
    private DealerInfoMapper dealerInfoMapper;

    @Autowired(required = true)
    public BrandFavoriteServiceImpl(BrandFavoriteMapper brandFavoriteMapper) {
        super(brandFavoriteMapper);
        this.brandFavoriteMapper = brandFavoriteMapper;
    }

    /**
     * 收藏的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> search(Pagination pagination, BrandFavoriteView info) {
        PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<Map<String, Object>>();
        List<Map<String, Object>> list = this.brandFavoriteMapper.search(pagination, info);
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

    /**
     * 判断是否已收藏
     *
     * @param brandId
     * @param dealerId
     * @return
     */
    @Override
    public Boolean isCollected(String brandId, String dealerId) {
        List<BrandFavorite> brandFavoriteList = brandFavoriteMapper.selectBrandFavorite(brandId, dealerId);
        if(brandFavoriteList!=null&&brandFavoriteList.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 收藏终端商
     *
     * @param brandId
     * @param dealerId
     * @throws BusinessException
     */
    @Override
    public void executeCollect(String brandId, String dealerId) throws BusinessException
    {
        List<BrandFavorite> brandFavoriteList = brandFavoriteMapper.selectBrandFavorite(brandId, dealerId);
        if (null != brandFavoriteList&&brandFavoriteList.size()>0) { throw new BusinessException(CommonConst.DATA_EXISTS); }
        BrandFavorite brandFavorite = new BrandFavorite();
        brandFavorite.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        brandFavorite.setBrandId(brandId);
        brandFavorite.setDealerId(dealerId);
        brandFavorite.setCollectTime(CalendarUtils.getCurrentLong());
        this.brandFavoriteMapper.insert(brandFavorite);
    }

    /**
     * 取消收藏
     *
     * @param brandId
     * @param dealerId
     * @throws BusinessException
     */
    @Override
    public void executeUnCollect(String brandId, String dealerId) throws BusinessException
    {
        List<BrandFavorite> brandFavoriteList = brandFavoriteMapper.selectBrandFavorite(brandId, dealerId);
        if (null == brandFavoriteList||brandFavoriteList.size()==0) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        this.brandFavoriteMapper.deleteByPrimaryKey(brandFavoriteList.get(0).getRefrenceId());
    }
}
