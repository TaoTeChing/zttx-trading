/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandAddress;
import com.zttx.web.module.brand.entity.BrandStore;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandAddressMapper;
import com.zttx.web.module.brand.mapper.BrandStoreMapper;
import com.zttx.web.module.brand.mapper.BrandesInfoMapper;
import com.zttx.web.module.brand.model.BrandAddressModel;
import com.zttx.web.module.common.entity.Regional;
import com.zttx.web.module.common.service.RegionalService;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商发货地址信息 服务实现类
 * <p>File：BrandAddress.java </p>
 * <p>Title: BrandAddress </p>
 * <p>Description:BrandAddress </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandAddressServiceImpl extends GenericServiceApiImpl<BrandAddress> implements BrandAddressService
{

    @Autowired
    private RegionalService    regionalService;
    
    @Autowired
    private BrandesInfoMapper  brandesInfoMapper;
    
    @Autowired
    private BrandStoreMapper   brandStoreMapper;

    private BrandAddressMapper brandAddressMapper;

    @Autowired(required = true)
    public BrandAddressServiceImpl(BrandAddressMapper brandAddressMapper)
    {
        super(brandAddressMapper);
        this.brandAddressMapper = brandAddressMapper;
    }
    
    @Override
    public BrandAddress findByIdWithCheck(String refrenceId, String brandId) throws BusinessException
    {
        BrandAddress address = brandAddressMapper.selectByPrimaryKey(refrenceId);
        if (!address.getBrandId().equals(brandId)) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        return address;
    }
    
    /**
     * 关联查询，直接获取发货地址、仓库信息、品牌信息
     * @param pagination
     * @param brandId
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> listAddressMap(Pagination pagination, String brandId) throws BusinessException
    {
        BrandAddress filter = new BrandAddress();
        filter.setBrandId(brandId);
        filter.setPage(pagination);
        filter.setDelFlag(Boolean.FALSE);
        List<Map<String, Object>> list = brandAddressMapper.listAddressMap(filter);
        PaginateResult<Map<String, Object>> result = new PaginateResult(pagination, list);
        for (Map<String, Object> map : result.getList())
        {
            Integer areaNo = (Integer) map.get("areaNo");
            map.put("areaName", getFullNameByAreaNoAndLevel(areaNo, 3, " "));
        }
        return result;
    }
    
    @Override
    public String getFullNameByAreaNoAndLevel(Integer areaNo, Integer level, String split_code)
    {
        String splitCode = "/";
        List<Regional> cacheList = regionalService.selectAll();
        Regional currentRegion = regionalService.getAreaCode(areaNo, cacheList);
        StringBuilder fullName = new StringBuilder();
        if (null != currentRegion && null != level)
        {
            if (split_code != null)
            {
                splitCode = split_code;
            }
            if (currentRegion.getAreaLevel().intValue() == 1)
            {
                fullName.append(currentRegion.getAreaName());
            }
            if (currentRegion.getAreaLevel() == 2)
            {
                Integer code = regionalService.getFatherCode(currentRegion.getAreaNo(), 2);
                String name1 = regionalService.getAreaCode(code, cacheList).getAreaName();
                fullName.append(name1).append(splitCode).append(currentRegion.getAreaName());
                if (level == 1)
                {
                    String tempStr = fullName.toString();
                    String[] arr = tempStr.split(splitCode);
                    fullName = new StringBuilder();
                    fullName.append(arr[0]);
                }
            }
            if (currentRegion.getAreaLevel().intValue() == 3)
            {
                Integer code = regionalService.getFatherCode(currentRegion.getAreaNo(), 3);
                Regional obj = regionalService.getAreaCode(code, cacheList);// 市级
                String name2 = obj.getAreaName();
                Integer code1 = regionalService.getFatherCode(obj.getAreaNo(), 2);
                Regional obj1 = regionalService.getAreaCode(code1, cacheList);// 省级
                String name1 = obj1.getAreaName();
                fullName.append(name1).append(splitCode).append(name2).append(splitCode).append(currentRegion.getAreaName());
                if (level == 2)
                {
                    String tempStr = fullName.toString();
                    int pos = tempStr.lastIndexOf(splitCode);
                    fullName = new StringBuilder();
                    fullName.append(tempStr.substring(0, pos));
                }
                if (level == 1)
                {
                    String tempStr = fullName.toString();
                    String[] arr = tempStr.split(splitCode);
                    fullName = new StringBuilder();
                    fullName.append(arr[0]);
                }
            }
        }
        return fullName.toString();
    }
    
    @Override
    public BrandAddress insertAddress(BrandAddressModel addressModel) throws BusinessException
    {
        BrandesInfo brandesInfo = brandesInfoMapper.findBrandesInfo(addressModel.getBrandId(), addressModel.getBrandsId());
        if (null == brandesInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        BrandStore brandStore = processBrandStore(addressModel);
        BrandAddress address = new BrandAddress();
        address.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        address.setBrandId(addressModel.getBrandId());
        address.setBrandsId(brandesInfo.getRefrenceId());
        address.setStoreId(brandStore.getRefrenceId());
        address.setUserName(addressModel.getUserName());
        address.setAreaNo(addressModel.getAreaNo());
        address.setStreet(addressModel.getStreet());
        address.setUserTel(addressModel.getUserTel());
        address.setUserMobile(addressModel.getUserMobile());
        address.setAddressMark(addressModel.getAddressMark());
        address.setZipCode(addressModel.getZipCode());
        if (addressModel.getSendDefault() != null)
        {
            address.setSendDefault(addressModel.getSendDefault());
            if (addressModel.getSendDefault())
            {
                brandAddressMapper.cancelSenderDefault(false, address.getBrandsId(), true);
            }
        }
        else
        {
            address.setSendDefault(Boolean.FALSE);
        }
        if (addressModel.getReturnDefault() != null && addressModel.getReturnDefault())
        {
            brandAddressMapper.cancelReturnDefault(false, address.getBrandsId(), true);
            address.setReturnDefault(true);
        }
        else
        {
            BrandAddress defaultAddress = brandAddressMapper.getDefaultAddress(address.getBrandsId());
            if (null == defaultAddress)
            {
                address.setReturnDefault(true);
            }
            else
            {
                address.setReturnDefault(false);
            }
        }
        address.setCreateTime(CalendarUtils.getCurrentLong());
        address.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
        brandAddressMapper.insert(address);
        return address;
    }
    
    @Override
    public BrandAddress updateAddress(BrandAddressModel addressModel) throws BusinessException
    {
        BrandAddress address = brandAddressMapper.selectByPrimaryKey(addressModel.getRefrenceId());
        if (null == address) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        if (!address.getBrandId().equals(addressModel.getBrandId())) { throw new BusinessException((CommonConst.DATA_NOT_EXISTS)); }
        BrandStore brandStore = processBrandStore(addressModel);
        address.setStoreId(brandStore.getRefrenceId());
        address.setUserName(addressModel.getUserName());
        address.setAreaNo(addressModel.getAreaNo());
        address.setStreet(addressModel.getStreet());
        address.setUserTel(addressModel.getUserTel());
        address.setUserMobile(addressModel.getUserMobile());
        address.setAddressMark(addressModel.getAddressMark());
        address.setZipCode(addressModel.getZipCode());
        if (addressModel.getSendDefault() != null)
        {
            address.setSendDefault(addressModel.getSendDefault());
            if (addressModel.getSendDefault())
            {
                brandAddressMapper.cancelSenderDefault(false, address.getBrandsId(), true);
            }
        }
        else
        {
            address.setSendDefault(Boolean.FALSE);
        }
        if (addressModel.getReturnDefault() != null) address.setReturnDefault(addressModel.getReturnDefault());
        else address.setReturnDefault(Boolean.FALSE);
        this.brandAddressMapper.updateByPrimaryKey(address);
        return address;
    }
    
    /**
     * 仓库处理逻辑
     *
     * @param addressModel
     * @return
     * @throws BusinessException
     */
    private BrandStore processBrandStore(BrandAddressModel addressModel) throws BusinessException
    {
        BrandStore brandStore;
        if (addressModel.getNewStore())
        {
            // 检测该品牌下是否有相同名字的仓库
            BrandStore filter = new BrandStore();
            filter.setStoreName(addressModel.getStoreValue()); // StoreValue ?
            filter.setBrandId(addressModel.getBrandId());
            filter.setBrandsId(addressModel.getBrandsId());
            brandStore = brandStoreMapper.findBrandStore(filter);
            if (null == brandStore)
            {
                brandStore = new BrandStore();
                brandStore.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandStore.setBrandId(addressModel.getBrandId());
                brandStore.setBrandsId(addressModel.getBrandsId());
                brandStore.setStoreName(addressModel.getStoreValue());
                brandStore.setCreateTime(CalendarUtils.getCurrentLong());
                brandStore.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                this.brandStoreMapper.insert(brandStore);
            }
        }
        else
        {
            BrandStore filter = new BrandStore();
            filter.setRefrenceId(addressModel.getStoreValue()); // StoreValue ?
            filter.setBrandId(addressModel.getBrandId());
            filter.setBrandsId(addressModel.getBrandsId());
            brandStore = brandStoreMapper.findBrandStore(filter);
            if (null == brandStore) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        }
        return brandStore;
    }
    
    @Override
    public void deleteByIdAndBranderId(String addressId, String branderId)
    {
        brandAddressMapper.deleteByIdAndBrandId(addressId, branderId);
    }
    
    @Override
    public void updateDefault(String addressId, String branderId) throws BusinessException
    {
        BrandAddress address = brandAddressMapper.selectByPrimaryKey(addressId);
        if (null == address) { return; }
        if (!address.getBrandId().equals(branderId)) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        brandAddressMapper.cancelReturnDefault(false, address.getBrandsId(), true);
        address.setReturnDefault(true);
        brandAddressMapper.updateByPrimaryKey(address);
    }
    
    @Override
    public BrandAddress getDefaultBrandAddress(String brandsId) throws BusinessException
    {
        BrandAddress address = brandAddressMapper.getDefaultAddress(brandsId);
        if (address == null)
        {
            address = brandAddressMapper.getFirstAddress(brandsId);
        }
        return address;
    }
    
    @Override
    public PaginateResult<BrandAddress> listAddress(Pagination pagination, String brandsId)
    {
        List<BrandAddress> list = brandAddressMapper.listAddress(pagination, brandsId);
        return new PaginateResult<>(pagination, list);
    }
    
    @Override
    public BrandAddress getDefaultBrandAddressByBrandId(String brandId)
    {
        return brandAddressMapper.getDefaultBrandAddressByBrandId(brandId);
    }
}
