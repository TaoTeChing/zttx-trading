/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.ValidateUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.module.brand.entity.BrandService;
import com.zttx.web.module.brand.mapper.BrandServiceMapper;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.model.Customer;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerService;
import com.zttx.web.module.dealer.service.DealerServiceService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.ClientValidator;

/**
 * 品牌商客服信息 服务实现类
 * <p>File：BrandService.java </p>
 * <p>Title: BrandService </p>
 * <p>Description:BrandService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandServiceServiceImpl extends GenericServiceApiImpl<BrandService> implements BrandServiceService
{
    @Autowired
    private UserInfoService      userInfoService;
    
    @Autowired
    private DealerServiceService dealerServiceService;
    
    private BrandServiceMapper   brandServiceMapper;

    @Autowired(required = true)
    public BrandServiceServiceImpl(BrandServiceMapper brandServiceMapper)
    {
        super(brandServiceMapper);
        this.brandServiceMapper = brandServiceMapper;
    }
    
    /**
	 * 根据品牌商编号获取品牌客服信息
	 * @author 陈建平
	 * @param brandId
	 * @return
	 */
    @Override
    public BrandService findBrandService(String brandId)
    {
        BrandService filter = new BrandService();
        filter.setBrandId(brandId);
        List<BrandService> list = brandServiceMapper.findList(filter);
        if(null!=list && list.size()>0){
        	return list.get(0);
        }
        return null;
    }
    
    /**
     * 保存客户信息
     * @author 陈建平
     * @param customer
     * @throws BusinessException
     */
    @Override
    public BrandService saveBrandService(BrandService brandService) throws BusinessException
    {
        if (StringUtils.isBlank(brandService.getRefrenceId()))
        {
            brandService.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            brandService.setCreateTime(CalendarUtils.getCurrentLong());
            brandService.setUpdateTime(CalendarUtils.getCurrentLong());
            brandService.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
            brandServiceMapper.insert(brandService);
            return brandService;
        }
        else
        {
            BrandService old = brandServiceMapper.selectByPrimaryKey(brandService.getRefrenceId());
            if (null == old) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            old.setBrandId(brandService.getBrandId());
            old.setServiceName(brandService.getServiceName());
            old.setUserId(brandService.getUserId());
            old.setServiceImage(brandService.getServiceImage());
            old.setDomainName(brandService.getDomainName());
            old.setUserGender(brandService.getUserGender());
            old.setJobNum(brandService.getJobNum());
            old.setServiceTel(brandService.getServiceTel());
            old.setServiceMobile(brandService.getServiceMobile());
            old.setServiceCate(brandService.getServiceCate());
            old.setUpdateTime(CalendarUtils.getCurrentLong());
            brandServiceMapper.updateByPrimaryKey(old);
            return old;
        }
    }
    
    /**
     * 保存客户信息
     * @author 陈建平
     * @param customer
     * @throws BusinessException
     */
    @Override
    public void save(Customer customer) throws BusinessException
    {
        ClientValidator.validateByClient(customer);
        if (org.apache.commons.lang3.StringUtils.isBlank(customer.getMobile()) && org.apache.commons.lang3.StringUtils.isBlank(customer.getTelPhoneSystemNumber()))
        {
            throw new BusinessException(ClientConst.FAILURE.getCode(), "手机号码或者电话号码必须填写一个");
        }
        else if (org.apache.commons.lang3.StringUtils.isNotBlank(customer.getMobile()))
        {
            if (!ValidateUtils.isMobileFormat(customer.getMobile(), true, 11)) { throw new BusinessException(ClientConst.FAILURE.getCode(), "手机号码格式不正确"); }
        }
        else
        // 电话分机号，(003) 不校验
        {
        }
        if (new Short((short) UserAccountConst.BRAND).equals(customer.getType()))
        {
            this.saveBrandService(customer);
        }
        else if (new Short((short) UserAccountConst.DEALER).equals(customer.getType()))
        {
            this.saveDealerService(customer);
        }
        else
        {
            throw new BusinessException(ClientConst.FAILURE.getCode(), "未知用户类型");
        }
    }
    
    // 品牌商客服信息保存
    private void saveBrandService(Customer customer) throws BusinessException
    {
        UserInfo brandUserm = userInfoService.selectByPrimaryKey(customer.getRefrenceId());
        if (null == brandUserm) { throw new BusinessException(ClientConst.FAILURE.getCode(), "该品牌商不存在"); }
        BrandService brandService = this.findBrandService(brandUserm.getRefrenceId());
        if (brandService == null) 
        {
        	// 新增
            brandService = new BrandService();
            brandService.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            brandService.setBrandId(brandUserm.getRefrenceId());
            brandService.setCreateTime(CalendarUtils.getCurrentLong());
            brandService.setUpdateTime(CalendarUtils.getCurrentLong());
            brandService.setDelFlag(false);
            brandService.setJobNum(customer.getJobNum());
            brandService.setServiceCate(customer.getServiceCate());
            brandService.setServiceImage(customer.getMainPhotoPath());
            brandService.setServiceMobile(customer.getMobile());
            brandService.setServiceName(customer.getName());
            brandService.setServiceTel(customer.getTelPhoneSystemNumber());
            brandService.setUserGender(customer.getSex());
            brandService.setUserId(customer.getEmployeId());
            brandServiceMapper.insert(brandService);
        }
        else
        { 
        	// 修改
            brandService.setJobNum(customer.getJobNum());
            brandService.setServiceCate(customer.getServiceCate());
            brandService.setServiceImage(customer.getMainPhotoPath());
            brandService.setServiceMobile(customer.getMobile());
            brandService.setServiceName(customer.getName());
            brandService.setServiceTel(customer.getTelPhoneSystemNumber());
            brandService.setUserGender(customer.getSex());
            brandService.setUserId(customer.getEmployeId());
            brandService.setUpdateTime(CalendarUtils.getCurrentLong());
            brandServiceMapper.updateByPrimaryKey(brandService);
        }
        // 批量更新客服信息
        brandServiceMapper.updateBrandServiceByUserId(brandService);
    }
    
    // 经销商客服信息保存
    private void saveDealerService(Customer customer) throws BusinessException
    {
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(customer.getRefrenceId());
        if (dealerUserm == null) { throw new BusinessException(ClientConst.FAILURE.getCode(), "该经销商商不存在"); }
        DealerService dealerService = dealerServiceService.getByDealerId(dealerUserm.getRefrenceId());
        if (null == dealerService) 
        {
        	// 新增
            dealerService = new DealerService();
            dealerService.setCreateTime(CalendarUtils.getCurrentLong());
            dealerService.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerService.setDealerId(dealerUserm.getRefrenceId());
            dealerService.setDelFlag(false);
            dealerService.setJobNum(customer.getJobNum());
            dealerService.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerService.setServiceCate(customer.getServiceCate());
            dealerService.setServiceImage(customer.getMainPhotoPath());
            dealerService.setServiceMobile(customer.getMobile());
            dealerService.setServiceName(customer.getName());
            dealerService.setServiceTel(customer.getTelPhoneSystemNumber());
            dealerService.setUserGender(customer.getSex());
            dealerService.setUserId(customer.getEmployeId());
            dealerServiceService.insert(dealerService);
        }
        else
        {
        	// 修改
            dealerService.setJobNum(customer.getJobNum());
            dealerService.setServiceCate(customer.getServiceCate());
            dealerService.setServiceImage(customer.getMainPhotoPath());
            dealerService.setServiceMobile(customer.getMobile());
            dealerService.setServiceName(customer.getName());
            dealerService.setServiceTel(customer.getTelPhoneSystemNumber());
            dealerService.setUserGender(customer.getSex());
            dealerService.setUserId(customer.getEmployeId());
            dealerService.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerServiceService.updateByPrimaryKey(dealerService);
        }
        dealerServiceService.updateDealerServiceByUserId(dealerService);
    }
    
    /**
     * 根据客服ID修改所有该客服信息
     * @author 陈建平
     * @param brandService
     */
    @Override
    public void updateBrandServiceByUserId(BrandService brandService){
    	brandServiceMapper.updateBrandServiceByUserId(brandService);
    }
}
