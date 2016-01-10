/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerAddr;

/**
 * 经销商地址信息 服务接口
 * <p>File：DealerAddrService.java </p>
 * <p>Title: DealerAddrService </p>
 * <p>Description:DealerAddrService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerAddrService extends GenericServiceApi<DealerAddr>
{
    /**
     * 查询经销商下所有的收货地址
     * @param dealerId
     * @return
     */
    List<DealerAddr> getAllDealerAddrsList(String dealerId);
    
    /**
     * 获取默认收货地址
     * @param dealerId
     * @return
     * @throws BusinessException
     */
    DealerAddr getDefaultDealerAddrBy(String dealerId) throws BusinessException;
    
    /**
     * 保存收货地址通用方法
     * @param dealerAddr
     *          refrenceId null ：新增
     *          refrenceId not null ：修改
     * @throws BusinessException
     * @author 章旭楠
     */
    void save(DealerAddr dealerAddr) throws BusinessException;
    
    /**
     * 获取该经销商收货地址总数
     * @param dealerId
     * @return
     */
    int getTotalDealerAddrCount(String dealerId);
    
    /**
     * 修改经销商默认地址状态
     * @param dealerId 经销商id， 不为空
     * @param refrenceId 地址id ，允许为空
     * @param isDefault 是否默认
     */
    void updateDealerDefaultAddrs(String dealerId, String refrenceId, boolean isDefault);
    
    /**
     * 获取区域代码
     * @param province
     * @param city
     * @param county
     * @return
     */
    String getAreaNo(String province, String city, String county);
    
    /**
     * 设置默认收货地址
     * @param refrenceId 地址id
     * @param dealerId 经销商id
     * @throws BusinessException
     */
    void setDefaultAddress(String refrenceId, String dealerId) throws BusinessException;
    
    /**
     * 分页获取经销商地址
     * @param page
     * @param dealerId
     * @return
     */
    PaginateResult<DealerAddr> list(Pagination page, String dealerId);
    
    /**
     * 删除经销商地址(逻辑删除)
     * @param uuid 验证码类型对象的UUID
     * @return Integer 代号
     */
    Integer delete(String uuid, String dealerId);
}
