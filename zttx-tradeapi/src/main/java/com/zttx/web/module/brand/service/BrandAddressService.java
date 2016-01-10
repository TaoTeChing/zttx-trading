/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandAddress;
import com.zttx.web.module.brand.model.BrandAddressModel;

/**
 * 品牌商发货地址信息 服务接口
 * <p>File：BrandAddressService.java </p>
 * <p>Title: BrandAddressService </p>
 * <p>Description:BrandAddressService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandAddressService extends GenericServiceApi<BrandAddress>{

    /**
     * 根据收货地址的编号查找到结果,并检查是否是该品牌商的收货地址
     * @param refrenceId
     * @param brandId
     * @return
     * @throws BusinessException
     */
    BrandAddress findByIdWithCheck(String refrenceId, String brandId) throws BusinessException;
    
    /**
     * 关联查询，直接获取发货地址、仓库信息、品牌信息
     * @param pagination
     * @param brandId
     * @return
     * @throws BusinessException
     */
    PaginateResult<Map<String, Object>> listAddressMap(Pagination pagination, String brandId) throws BusinessException;
    
    /**
     * 根据区域码 层级获取全名
     * @param areaNo
     * @param level
     * @param split_code
     * @return
     */
    String getFullNameByAreaNoAndLevel(Integer areaNo, Integer level, String split_code);
    
    /**
     * 保存地址
     * @param addressModel
     * @return
     * @throws BusinessException
     */
    BrandAddress insertAddress(BrandAddressModel addressModel) throws BusinessException;
    
    /**
     * 更新地址
     * @param addressModel
     * @return
     * @throws BusinessException
     */
    BrandAddress updateAddress(BrandAddressModel addressModel) throws BusinessException;
    
    /**
     * 根据编号和品牌商Id删除
     * @param addressId
     * @param branderId
     */
    void deleteByIdAndBranderId(String addressId, String branderId);
    
    /**
     * 设置默认地址
     * @param addressId
     * @param branderId
     * @throws BusinessException
     */
    void updateDefault(String addressId, String branderId) throws BusinessException;

    /**
     * 获取品牌默认地址
     * @param brandsId
     * @return
     * @throws BusinessException
     */
    BrandAddress getDefaultBrandAddress(String brandsId) throws BusinessException;
    
    /**
     * 获取品牌地址
     * @param pagination
     * @param brandsId
     * @return
     */
    PaginateResult<BrandAddress> listAddress(Pagination pagination, String brandsId);

    /**
     * 默认品牌商收货地址
     * @param brandId
     * @return
     */
    BrandAddress getDefaultBrandAddressByBrandId(String brandId);
}
