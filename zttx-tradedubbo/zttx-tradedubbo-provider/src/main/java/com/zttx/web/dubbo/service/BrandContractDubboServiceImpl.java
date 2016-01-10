/*
 * @(#)BrandContractDubboServiceImpl.java 2015-9-2 上午10:34:32
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandContactService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;

/**
 * <p>File：BrandContractDubboServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-2 上午10:34:32</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Service
public class BrandContractDubboServiceImpl implements BrandContractDubboService
{
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private BrandInfoService brandInfoService;
    @Autowired
    private BrandContactService brandContactService;
    
    @Override
    public Map<String,Object> getBrandContract(String brandId)
    {
        UserInfo brandUserm = userInfoService.selectByPrimaryKey(brandId);
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        Map<String, Object> result = Maps.newHashMap();
        if (brandInfo != null)
        {
            if (brandUserm != null)
            {
                result.put("userMobile", StringUtils.trimToEmpty(brandUserm.getUserMobile()));
            }
            result.put("comName", StringUtils.trimToEmpty(brandInfo.getComName()));
            result.put("areaNo", brandInfo.getAreaNo());
            result.put("comAddress", StringUtils.trimToEmpty(brandInfo.getComAddress()));
            BrandContact brandContact = brandContactService.findByBrandId(brandId);
            if (brandContact != null)
            {
                if (StringUtils.isNotBlank(brandContact.getUserMobile()))
                {
                    result.put("userMobile", StringUtils.trimToEmpty(brandContact.getUserMobile()));
                }
                result.put("contact", StringUtils.trimToEmpty(brandContact.getUserName()));
                result.put("userTelphone", StringUtils.trimToEmpty(brandContact.getUserTelphone()));
                result.put("userFax", StringUtils.trimToEmpty(brandContact.getUserFax()));
            }
            return result;
        }
        else
        {
            return null;
        }
    }
}
