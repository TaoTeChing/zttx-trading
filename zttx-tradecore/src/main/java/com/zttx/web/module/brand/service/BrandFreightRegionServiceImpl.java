/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.TagCommonConst;
import com.zttx.web.module.brand.entity.BrandFreightDetail;
import com.zttx.web.module.brand.entity.BrandFreightRegion;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.mapper.BrandFreightRegionMapper;
import com.zttx.web.utils.CalendarUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 运费区域表 服务实现类
 * <p>File：BrandFreightRegion.java </p>
 * <p>Title: BrandFreightRegion </p>
 * <p>Description:BrandFreightRegion </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandFreightRegionServiceImpl extends GenericServiceApiImpl<BrandFreightRegion> implements BrandFreightRegionService {
    @Autowired(required = true)
    private BrandFreightRegionMapper brandFreightRegionMapper;

    @Autowired(required = true)
    public BrandFreightRegionServiceImpl(BrandFreightRegionMapper brandFreightRegionMapper) {
        super(brandFreightRegionMapper);
        this.brandFreightRegionMapper = brandFreightRegionMapper;
    }

    /**
     * 根据模板id删除所有区域数据
     *
     * @param templateId
     */
    @Override
    public void removeByTemplateId(String templateId) throws BusinessException {
        brandFreightRegionMapper.removeByTemplateId(templateId);
    }

    @Override
    public void saveRegionForTempleatDefault(BrandFreightTemplate template, BrandFreightDetail brandFreightDetail)  throws BusinessException  {

        //不是推荐模板，批量插入，是推荐模板单独保存
        BrandFreightRegion region = new BrandFreightRegion();

        region.setAreaName(null);
        region.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        region.setTemplateId(template.getRefrenceId());
        region.setDetailId(brandFreightDetail.getRefrenceId());
        region.setAreaNo(null);
        region.setCreateTime(CalendarUtils.getCurrentLong());

        brandFreightRegionMapper.insert(region);
    }

    @Override
    public void saveRegionForTempleatNotDefault(BrandFreightTemplate template,List<BrandFreightDetail> detailList)  throws BusinessException {
        if(detailList!=null){
            for (BrandFreightDetail brandFreightDetail:detailList) {
                String[] areaNoArr = brandFreightDetail.getAreaNos().split(",");
                List<BrandFreightRegion> regionArrayList = new ArrayList<BrandFreightRegion>();
                for (String areaNo : areaNoArr) {
                    String areaName = null;
                    if (areaNo != null) {
                        areaName = TagCommonConst.getAreaNameByNo(Integer.parseInt(areaNo));
                        if (StringUtils.isBlank(areaName)) {
                            throw new BusinessException(CommonConst.FREIGHT_REGION_AREANAME_EXIT_NO);
                        }
                    }
                    BrandFreightRegion region = new BrandFreightRegion();
                    region.setAreaName(areaName);
                    region.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    region.setTemplateId(template.getRefrenceId());
                    region.setDetailId(brandFreightDetail.getRefrenceId());
                    region.setAreaNo(Integer.parseInt(areaNo));
                    region.setCreateTime(CalendarUtils.getCurrentLong());

                    regionArrayList.add(region);
                }

                brandFreightRegionMapper.insertList(regionArrayList);
            }
        }

    }
}
