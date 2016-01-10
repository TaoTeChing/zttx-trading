/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandFreightDetail;
import com.zttx.web.module.brand.entity.BrandFreightSettings;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.mapper.BrandFreightDetailMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.RegularUtils;
import com.zttx.web.utils.ValidateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 运费明细表 服务实现类
 * <p>File：BrandFreightDetail.java </p>
 * <p>Title: BrandFreightDetail </p>
 * <p>Description:BrandFreightDetail </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
@Service
public class BrandFreightDetailServiceImpl extends GenericServiceApiImpl<BrandFreightDetail> implements BrandFreightDetailService {

    @Autowired(required = true)
    private BrandFreightDetailMapper brandFreightDetailMapper;

    @Autowired(required = true)
    public BrandFreightDetailServiceImpl(BrandFreightDetailMapper brandFreightDetailMapper) {
        super(brandFreightDetailMapper);
        this.brandFreightDetailMapper = brandFreightDetailMapper;
    }


    @Override
    public void removeByTemplateId(String templateId) {
        brandFreightDetailMapper.removeByTemplateId(templateId);
    }

    /**
     * 保存默认模板
     *
     * @param template
     * @param settings
     * @param brandFreightDetail
     * @throws BusinessException
     */
    @Override
    public BrandFreightDetail insertDefault(BrandFreightTemplate template, BrandFreightSettings settings, BrandFreightDetail brandFreightDetail) throws BusinessException {
        if (null == brandFreightDetail) {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }

        return insertBrandFreightDetail(template, settings, brandFreightDetail, BrandConstant.BrandFreightDetail.IS_DEFAULT_YES);
    }

    /**
     * 批量保存非默认的模板
     *
     * @param template
     * @param settings
     * @param brandFreightDetailList
     * @throws BusinessException
     */
    @Override
    public List<BrandFreightDetail> insertNotDefault(BrandFreightTemplate template, BrandFreightSettings settings, List<BrandFreightDetail> brandFreightDetailList) throws BusinessException {
        if (CollectionUtils.isEmpty(brandFreightDetailList)) {
            //            return;  //老代码这样写！！
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }

        List<BrandFreightDetail> detail = new ArrayList<BrandFreightDetail>();

        for (BrandFreightDetail freightDetail : brandFreightDetailList) {
            BrandFreightDetail detailObj = insertBrandFreightDetail(template, settings, freightDetail, BrandConstant.BrandFreightDetail.IS_DEFAULT_NO);
            detail.add(detailObj);
        }

        return detail;
    }

    @Override
    public BrandFreightDetail getDetail(String templateId, Integer carryType, Integer areaNo) {

        return brandFreightDetailMapper.getDetail(templateId, carryType, areaNo, RegularUtils.getPreNo(areaNo, 2), RegularUtils.getPreNo(areaNo, 4));
    }

    @Override
    public BigDecimal calculateFreightAmount(BrandFreightDetail detail, Integer carryType, BigDecimal sumWeight) throws BusinessException
    {
        if (BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT == carryType.intValue()
                || BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT == carryType.intValue()) { return BigDecimal.ZERO; }
        BigDecimal freightAmount;
        if (sumWeight.compareTo(detail.getFirstWeight()) < 1)
        {
            freightAmount = detail.getFirstPrice();
        }
        else
        {
            freightAmount = detail.getFirstPrice().add(
                    sumWeight.subtract(detail.getFirstWeight()).divide(detail.getExtendWeight(), 0, BigDecimal.ROUND_UP).multiply(detail.getExtendPrice()));
        }
        return freightAmount;
    }


    public BigDecimal calculateCheapFreightAmount(String brandId, List<String> templateIdList, Integer areaNo, Integer carryType, BigDecimal sumWeight)
            throws BusinessException
    {
        List<BrandFreightDetail> detailList = listDetail(brandId, templateIdList, carryType, areaNo);
        if (CollectionUtils.isEmpty(detailList)) { return null; }
        BigDecimal cheapFreightAmount = null;
        for (int i = 0; i < detailList.size(); i++)
        {
            BrandFreightDetail detail = detailList.get(i);
            BigDecimal tempFreightAmount = calculateFreightAmount(detail, carryType, sumWeight);
            if (i == 0)
            {
                cheapFreightAmount = tempFreightAmount;
            }
            else if (tempFreightAmount.compareTo(cheapFreightAmount) < 0)
            {
                cheapFreightAmount = tempFreightAmount;
            }
        }
        return cheapFreightAmount;
    }



    public List<BrandFreightDetail> listDetail(String brandId, List<String> templateIdList, Integer carryType, Integer areaNo)
    {
        return brandFreightDetailMapper.listDetail(brandId, templateIdList, carryType, areaNo);
    }
    /**
     * 保存模板详情
     *
     * @param template
     * @param settings
     * @param brandFreightDetail
     * @param isDefault
     * @throws BusinessException
     */
    private BrandFreightDetail insertBrandFreightDetail(BrandFreightTemplate template, BrandFreightSettings settings, BrandFreightDetail brandFreightDetail, Short isDefault) throws BusinessException {
        if (null == brandFreightDetail.getFirstWeight() || brandFreightDetail.getFirstWeight().compareTo(BigDecimal.ZERO) < 0 || null == brandFreightDetail.getFirstPrice()
                || brandFreightDetail.getFirstPrice().compareTo(BigDecimal.ZERO) < 0 || null == brandFreightDetail.getExtendWeight() || brandFreightDetail.getExtendWeight().compareTo(BigDecimal.ZERO) < 0
                || null == brandFreightDetail.getExtendPrice() || brandFreightDetail.getExtendPrice().compareTo(BigDecimal.ZERO) < 0) {
//            return;  //老代码这样写！！
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
        // 运费价格重量正则校验 修改重量或价格最大值时,要注意数据变成科学计数法,而无法匹配正则
        if (!ValidateUtils.isWight(brandFreightDetail.getFirstWeight().doubleValue(), 0.00, 10000000.00)
                || !ValidateUtils.isWight(brandFreightDetail.getExtendWeight().doubleValue(), 0.00, 10000000.00)) {
            throw new BusinessException(CommonConst.FREIGHT_WEIGHT_ERROR);
        }
        if (!ValidateUtils.isMoney(brandFreightDetail.getFirstPrice(), BigDecimal.valueOf(0.00), BigDecimal.valueOf(10000000.00))
                || !ValidateUtils.isMoney(brandFreightDetail.getExtendPrice(), BigDecimal.valueOf(0.00), BigDecimal.valueOf(10000000.00))) {
            throw new BusinessException(
                    CommonConst.FREIGHT_MONEY_ERROR);
        }

        BrandFreightDetail detail = new BrandFreightDetail();

        detail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        detail.setTemplateId(template.getRefrenceId());
        detail.setSettingsId(settings.getRefrenceId());
        detail.setCarryType(settings.getCarryType());
        detail.setFirstWeight(brandFreightDetail.getFirstWeight());
        detail.setFirstPrice(brandFreightDetail.getFirstPrice());
        detail.setExtendWeight(brandFreightDetail.getExtendWeight());
        detail.setExtendPrice(brandFreightDetail.getExtendPrice());
        detail.setIsDefault(isDefault);
        detail.setCreateTime(CalendarUtils.getCurrentLong());
        detail.setAreaNos(brandFreightDetail.getAreaNos());
        //保存模板详情数据
        brandFreightDetailMapper.insert(detail);

        //返回保存的详情对象，用于后面保存模板区域使用
        return detail;
    }

}
