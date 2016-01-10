/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandFreightDetail;
import com.zttx.web.module.brand.entity.BrandFreightSettings;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * 运费明细表 服务接口
 * <p>File：BrandFreightDetailService.java </p>
 * <p>Title: BrandFreightDetailService </p>
 * <p>Description:BrandFreightDetailService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 江枫林
 * @version 1.0
 */
public interface BrandFreightDetailService extends GenericServiceApi<BrandFreightDetail>{

    public void removeByTemplateId(String templateId);

    public BrandFreightDetail insertDefault(BrandFreightTemplate template, BrandFreightSettings settings, BrandFreightDetail param) throws BusinessException;

    public List<BrandFreightDetail> insertNotDefault(BrandFreightTemplate template, BrandFreightSettings settings, List<BrandFreightDetail> paramList) throws BusinessException;
    /**
     * 查询运费明细
     * @param templateId
     * @param carryType
     * @param areaNo
     * @return
     * @author 张昌苗
     */
    BrandFreightDetail getDetail(String templateId, Integer carryType, Integer areaNo);
    /**
     * 计算运费
     * @param detail 运费明细
     * @param carryType 运送方式
     * @param sumWeight 产品总重
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    BigDecimal calculateFreightAmount(BrandFreightDetail detail, Integer carryType, BigDecimal sumWeight) throws BusinessException;
    /**
     * 计算最便宜的运费
     * @param brandId 品牌商ID
     * @param templateIdList 模板ID列表
     * @param areaNo 送货区域
     * @param carryType 运送方式
     * @param sumWeight 产品总重
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    BigDecimal calculateCheapFreightAmount(String brandId, List<String> templateIdList, Integer areaNo, Integer carryType, BigDecimal sumWeight) throws BusinessException;
}
