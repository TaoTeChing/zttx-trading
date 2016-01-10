/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandAddress;

/**
 * 品牌商发货地址信息 持久层接口
 * <p>File：BrandAddressDao.java </p>
 * <p>Title: BrandAddressDao </p>
 * <p>Description:BrandAddressDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandAddressMapper extends GenericMapper<BrandAddress>
{
    /**
     * 关联查询，直接获取发货地址、仓库信息、品牌信息
     * @param filter
     * @return
     */
    List<Map<String, Object>> listAddressMap(BrandAddress filter);

    /**
     * 取消品牌默认发货地址
     * @param brandsId
     */
    int cancelSenderDefault(Boolean newSendDefalt, String brandsId, Boolean oldSendDefalt);

    /**
     * 取消品牌默认退货地址
     * @param newReturnDefalt
     * @param brandsId
     * @param oldReturnDefalt
     * @return
     */
    int cancelReturnDefault(@Param("newReturnDefalt")Boolean newReturnDefalt, @Param("brandsId")String brandsId, @Param("oldReturnDefalt")Boolean oldReturnDefalt);
    
    /**
     * 品牌商品牌默认地址
     * @param brandsId
     * @return
     */
    BrandAddress getDefaultAddress(@Param("brandsId")String brandsId);

    /**
     * 获取品牌的一个地址
     * @param brandsId
     * @return
     */
    BrandAddress getFirstAddress(@Param("brandsId")String brandsId);

    /**
     * 根据编号和品牌商Id删除
     * @param refrenceId
     * @param brandId
     */
    int deleteByIdAndBrandId(@Param("refrenceId")String refrenceId,@Param("brandId")String brandId);
    
    /**
     * 获取品牌地址
     * @param pagination
     * @param brandsId
     * @return
     */
    List<BrandAddress> listAddress(@Param("page") Pagination pagination, @Param("brandsId") String brandsId);
    
    /**
     * 获取品牌商默认地址
     * @param brandId
     * @return
     */
    BrandAddress getDefaultBrandAddressByBrandId(@Param("brandId") String brandId);
    
    /**
     * 获取品牌商第一个地址
     * @param brandId
     * @return
     */
    BrandAddress getFirstBrandAddressByBrandId(@Param("brandId") String brandId);
}
