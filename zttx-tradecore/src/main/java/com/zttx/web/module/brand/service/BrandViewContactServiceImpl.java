/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandCount;
import com.zttx.web.module.brand.entity.BrandViewContact;
import com.zttx.web.module.brand.mapper.BrandCountMapper;
import com.zttx.web.module.brand.mapper.BrandInviteMapper;
import com.zttx.web.module.brand.mapper.BrandViewContactMapper;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.mapper.DealerGroomMapper;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商查看经销商联系信息 服务实现类
 * <p>File：BrandViewContact.java </p>
 * <p>Title: BrandViewContact </p>
 * <p>Description:BrandViewContact </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandViewContactServiceImpl extends GenericServiceApiImpl<BrandViewContact> implements BrandViewContactService {

    @Autowired
    private DealerInfoService dealerInfoService;

    @Autowired
    private BrandCountMapper       brandCountMapper;
    
    @Autowired
    private BrandCountService brandCountService;
    @Autowired
    private DealerGroomMapper dealerGroomMapper;
    @Autowired
    private BrandInviteMapper brandInviteMapper;
    @Autowired
    private UserInfoService userInfoService;

    private BrandViewContactMapper brandViewContactMapper;


    @Autowired(required = true)
    public BrandViewContactServiceImpl(BrandViewContactMapper brandViewContactMapper) {
        super(brandViewContactMapper);
        this.brandViewContactMapper = brandViewContactMapper;
    }

    /*
     * 校验是否存在
     * */
    @Override
    public Integer isExist(String brandId, String dealerId) {
        Short viewType = BrandConstant.BrandViewContact.VIEW_TYPE_INITIATIVE;
        Integer isExist = brandViewContactMapper.isExist(brandId, dealerId, null);
        return isExist;
    }

    /*
     * 修改浏览次数
     */
    @Override
    public DealerInfo modifyViewContact(String brandId, String dealerId) throws BusinessException {
        DealerInfoModel dealerInfo = dealerInfoService.findById(dealerId);
        if (null == dealerInfo) {
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        }

        UserInfo userInfo = userInfoService.selectByPrimaryKey(dealerId);
        Integer contactIsExistInt = brandViewContactMapper.isExist(brandId, dealerId, null);
        Short viewType = BrandConstant.BrandViewContact.VIEW_TYPE_INITIATIVE;
        if (contactIsExistInt > 0) {
            Integer groomIsExistInt = dealerGroomMapper.isExist(brandId, dealerId);
            if (groomIsExistInt > 0) {
                viewType = BrandConstant.BrandViewContact.VIEW_TYPE__RECOMMEND;
            } else {
                Integer inviteIsExistInt = brandInviteMapper.isExist(brandId, dealerId);
                if (inviteIsExistInt > 0) {
                    viewType = BrandConstant.BrandViewContact.VIEW_TYPE_APPLICATION;
                }

            }
        }
        BrandViewContact brandViewContact = new BrandViewContact();
        brandViewContact.setBrandId(brandId);
        brandViewContact.setDealerId(dealerId);
        brandViewContact.setViewType(viewType);
        if (contactIsExistInt == 0) {
            brandViewContact.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
            brandViewContact.setViewTime(CalendarUtils.getCurrentLong());
            brandViewContactMapper.insert(brandViewContact);
        }

        BrandCount brandCount = brandCountService.selectByPrimaryKey(brandId);
        Integer viewCount = brandCountService.getViewDealerTotal(brandId);
        boolean brandCountisExist = true;
        if (null != brandCount ) {
            if(viewCount.intValue() <= brandCount.getViewDealerCount().intValue()){
                throw new BusinessException(BrandConst.VIEW_CONTACT_LACK_POINTS);
            }
        } else {
            brandCountisExist = false;
            brandCount = new BrandCount();
            brandCount.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        }
        // 查看经销商联系信息数量
        Long count = brandViewContactMapper.getBrandViewContactCount(brandViewContact);
        brandCount.setViewDealerCount(count.intValue());
        if (brandCountisExist) {
            brandCountService.updateByPrimaryKey(brandCount);
        } else {
            brandCountService.insert(brandCount);
        }
        Boolean isExits = false;
        if (contactIsExistInt > 0) {
            isExits = true;
        }
        dealerInfo.setIsViewAdd(isExits);
        dealerInfo.setUserMobile(userInfo.getUserMobile());

        return dealerInfo;
    }

    @Override
    public Boolean insertWithCheck(String brandId, String dealerId, Boolean isAdd) throws BusinessException
    {
        return this.insertWithCheck(brandId, dealerId, isAdd, null);
    }
    
    public Boolean insertWithCheck(String brandId, String dealerId, Boolean isAdd, BrandViewContact brandViewContact) throws BusinessException
    {
        Short viewType = BrandConstant.BrandViewContact.VIEW_TYPE_INITIATIVE;
        Boolean isExist = this.isExist(brandId, dealerId, null);
        if (brandViewContact == null)
        {
            brandViewContact = new BrandViewContact();
        }
        if (!isExist)
        {
            isExist = dealerGroomMapper.isExist(brandId, dealerId) > 0;
            if (!isExist)
            {
                isExist = brandInviteMapper.isExist(brandId, dealerId) > 0;
                if (isExist)
                {
                    viewType = BrandConstant.BrandViewContact.VIEW_TYPE_APPLICATION;
                }
            }
            else
            {
                viewType = BrandConstant.BrandViewContact.VIEW_TYPE__RECOMMEND;
            }
            if (isExist || isAdd)
            {
                brandViewContact.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                brandViewContact.setBrandId(brandId);
                brandViewContact.setDealerId(dealerId);
                brandViewContact.setViewTime(CalendarUtils.getCurrentLong());
                brandViewContact.setViewType(viewType);
                brandViewContactMapper.insert(brandViewContact);
                if (!isExist)
                {
                    BrandCount brandCount = brandCountMapper.selectByPrimaryKey(brandId);
                    Integer viewCount = getViewDealerTotal(brandCount);
                    if (null != brandCount && viewCount.intValue() > brandCount.getViewDealerCount().intValue())
                    {
                        List<Integer> countTypeList = Lists.newArrayList();
                        // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
                        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_VIEWDEALERCOUNT);
                        brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
                    }
                }
            }
        }
        return isExist;
    }
    
    private Integer getViewDealerTotal(BrandCount brandCount)
    {
        Integer viewCount = BrandConstant.BrandCountConst.BRAND_VIEW_COUNT;
        if (null != brandCount.getViewDealerTotal() && 0 < brandCount.getViewDealerTotal())
        {
            viewCount = brandCount.getViewDealerTotal();
        }
        return viewCount;
    }
    
    @Override
    public Boolean isExist(String brandId, String dealerId, Short viewType)
    {
        return brandViewContactMapper.isExist(brandId, dealerId, viewType) > 0;
    }
}
