/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.ClientConstant;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.entity.BrandCrm;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandCrmMapper;
import com.zttx.web.module.brand.model.BrandCrmModel;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商更新信息表CRM 服务实现类
 * <p>File：BrandCrm.java </p>
 * <p>Title: BrandCrm </p>
 * <p>Description:BrandCrm </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandCrmServiceImpl extends GenericServiceApiImpl<BrandCrm> implements BrandCrmService
{
	@Autowired
	private UserInfoService      userInfoService;
	
	@Autowired
	private BrandInfoService     brandInfoService;
	
	@Autowired
	private BrandContactService  brandContactService;
	
	@Autowired
	private DealerInfoService    dealerInfoService;
	
	@Autowired
	private BrandesInfoService   brandesInfoService;

    private BrandCrmMapper       brandCrmMapper;

    @Autowired(required = true)
    public BrandCrmServiceImpl(BrandCrmMapper brandCrmMapper)
    {
        super(brandCrmMapper);
        this.brandCrmMapper = brandCrmMapper;
    }


    @Override
    public BrandCrm save(String jsonObject, short jsonType) {
        BrandCrm brandCrm = new BrandCrm();
        brandCrm.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandCrm.setCreateTime(CalendarUtils.getCurrentLong());
        brandCrm.setBrandState(ClientConstant.BRANDSTATE_NOT_GET);
        brandCrm.setJson(jsonObject);
        brandCrm.setJsonType(jsonType);
        brandCrmMapper.insert(brandCrm);
        return brandCrm;
    }
    
    /**
     * 通过接口保存品牌商更新信息
     * @author 陈建平
     * @param brandCrmModel
     * @throws BusinessException
     */
    @Override
    public void insertByClient(BrandCrmModel brandCrmModel) throws BusinessException
    {
        if (brandCrmModel == null)
        {
            throw new BusinessException(ClientConst.NULERROR);
        }
        else if (StringUtils.isBlank(brandCrmModel.getRefrenceId())) { 
        	throw new BusinessException(ClientConst.NULERROR); 
        }
        insert(brandCrmModel);
    }
    
    
    private void insert(BrandCrmModel brandCrmModel) throws BusinessException
    {
        BrandCrm brandCrm = new BrandCrm();
        switch (brandCrmModel.getJsonType())
        {
            case ClientConstant.BRAND_USERM:
                UserInfo brandUserInfo = userInfoService.selectByPrimaryKey(brandCrmModel.getRefrenceId());
                brandCrm.setJson(JSON.toJSONString(brandUserInfo));
                brandCrm.setJsonType(ClientConstant.BRAND_USERM);
                break;
            case ClientConstant.BRAND_INFO:
                BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandCrmModel.getRefrenceId());
                brandCrm.setJson(JSON.toJSONString(brandInfo));
                brandCrm.setJsonType(ClientConstant.BRAND_INFO);
                break;
            case ClientConstant.BRAND_CONTACT:
                BrandContact brandContact = brandContactService.selectByPrimaryKey(brandCrmModel.getRefrenceId());
                brandCrm.setJson(JSON.toJSONString(brandContact));
                brandCrm.setJsonType(ClientConstant.BRAND_CONTACT);
                break;
            case ClientConstant.DEALER_USERM:
            	UserInfo userInfo = userInfoService.selectByPrimaryKey(brandCrmModel.getRefrenceId());
                brandCrm.setJson(JSON.toJSONString(userInfo));
                brandCrm.setJsonType(ClientConstant.DEALER_USERM);
                break;
            case ClientConstant.DEALER_INFO:
                DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(brandCrmModel.getRefrenceId());
                brandCrm.setJson(JSON.toJSONString(dealerInfo));
                brandCrm.setJsonType(ClientConstant.DEALER_INFO);
                break;
            case ClientConstant.BRANDES_INFO:
                BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandCrmModel.getRefrenceId());
                brandCrm.setJson(JSON.toJSONString(brandesInfo));
                brandCrm.setJsonType(ClientConstant.BRANDES_INFO);
                break;
            default:
                throw new BusinessException(ClientConst.CLIENT_TYPE_OVERTOP);
        }
        brandCrm.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandCrm.setCreateTime(CalendarUtils.getCurrentLong());
        brandCrm.setBrandState(ClientConstant.BRANDSTATE_NOT_GET);
        insert(brandCrm);
    }
    
    /**
     * 通过接口获取品牌商更新信息
     * @author 陈建平
     * @param filter
     * @return
     */
    @Override
    public List<BrandCrm> selectBrandCrmByClient(BrandCrm filter){
    	return brandCrmMapper.selectBrandCrmByClient(filter);
    }
}
