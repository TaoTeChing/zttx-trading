/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerShoper;
import com.zttx.web.module.dealer.entity.DealerShopers;

/**
 * 经销商购物车 服务接口
 * <p>File：DealerShoperService.java </p>
 * <p>Title: DealerShoperService </p>
 * <p>Description:DealerShoperService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguyjava.util.List<com.zttx.web.module.dealer.entity.DealerShoper> selectDealerShoperBy(String );
 * @version 1.0
 */
public interface DealerShoperService extends GenericServiceApi<DealerShoper>
{
    /**
     * 统计购物车 产品类型总数量
     * @param userId
     * @return
     */
    Long getShoperCountByUserId(String userId);
    
    /**
     * 经销商 加车 （批量和单一）
     * @param productsId
     * @param productsType 产品类型：0普通加车（直供价） 1 授信加成（授信价） 2拿样加成（拿样价）
     * @param dealerId
     * @author 易永耀
     */
    void batchSaveShopers(List<String> productsId, List<Integer> productsType, String dealerId) throws BusinessException;
    
    /**
     * 获取 该经销商 购物车有效车单 isHome 是来判断是否是首页的购物车显示
     * @authro 易永耀
     */
    List<DealerShoper> selectDealerShoperBy(String dealerId, Boolean isHome) throws BusinessException;
    
    /**
     * 经销商 购物车 批量/单一移除
     * @param dealerShopersId
     * @param dealerId
     * @return
     * @author 易永耀
     */
    int batchRemoveShopers(List<String> dealerShopersId, String dealerId);
    
    /**
     * 购物车中 修改交易模式：0现款 1授信 2拿样
     * @param shoperId
     * @param productType
     * @return
     * @author 易永耀
     */
    DealerShoper updateProductTradeModel(String dealerId, String shoperId, Integer productType) throws BusinessException;
    
    /**
     * 根据 shopId 获取已经加车并将结算车单
     * @param dealerId
     * @param shopIds
     * @return
     * @author 易永耀
     */
    List<DealerShoper> getPurchaseDealerShoperList(String dealerId, String ... shopIds) throws BusinessException;
    
    /**
     * 产品详情直接加车
     *
     * @param productId 产品id
     * @param productType 类型
     * @param dealerId 经销商id
     * @param skuIds not null
     * @param amounts not null
     * @throws BusinessException
     */
    String saveShopers(String productId, Short productType, String dealerId, List<String> skuIds, List<Integer> amounts) throws BusinessException;
    
    /**
     * 复制订单到进货单
     * @param dealerId
     * @param dealerShoperMap
     * @throws BusinessException
     */
    void saveCountShopersToShoper(String dealerId, Map<String, DealerShoper> dealerShoperMap) throws BusinessException;
    
    /**
     * 更新购物车产品和价格
     * @param dealerShopers
     * @param dealerId
     */
    void update(DealerShopers dealerShopers, String dealerId);
    
    /**
     * 判断购物车是否存在
     * @param productId
     * @param dealerId
     * @return
     */
    Boolean isExist(String productId, String dealerId);
    
    /**
     * 根据经销商id和产品id获取购物车
     * @param dealerId
     * @param productId
     * @return
     */
    DealerShoper getDealerShoperBy(String dealerId, String productId);
    
    /**
     * 改版 加车
     * @author 易永耀
     * @param productId
     * @param productType
     * @param skuIds
     * @param purchaseNum
     * @param dealerId
     */
    String saveDealerShoper(String productId, Short productType, List<String> skuIds, List<Integer> purchaseNum, String dealerId) throws BusinessException;
    
    /**
     * 改版 加车
     * @param productId
     * @param productType
     * @param dealerShopersList
     * @param dealerId
     * @return
     * @throws BusinessException
     */
    String saveDealerShoper(String productId, Short productType, List<DealerShopers> dealerShopersList, String dealerId) throws BusinessException;
    
    /**
     * 查询更多 购物车 sku 信息
     * @author 易永耀
     * @param productId
     * @param productType
     * @param dealerId
     * @param shopId
     * @return
     */
    List<DealerShopers> getMoreDealerShopers(String productId, Short productType, String dealerId, String shopId) throws BusinessException;
    
    /**
     * 同步购物车
     * @param dataList
     * @param dealerId
     * @return
     * @throws BusinessException
     */
    Map<String, Map<String, Object>> synShopers(List<JSONObject> dataList, String dealerId) throws BusinessException;
    
    /**
     * 校验购物车中的产品有效性
     * @param dealerId
     * @param productId
     * @return true 有效
     */
    Boolean validateDealerShopProduct(String dealerId, String productId);
    
    /**
     * 获取有效数据 带价格
     * @param dealerId
     * @param dealerShoper
     * @throws BusinessException
     */
    void getValidMapWithTypeAndPrice(String dealerId, DealerShoper dealerShoper) throws BusinessException;
    
    /**
     * 产品详情页直接结算(抽象生成购物车)
     * @param productId 产品id
     * @param productType 类型（现款，授信，返点）
     * @param skuIds skuId 集合
     * @param purchaseNums 购买数量集合
     * @param dealerId 经销商id
     * @return 虚拟购物车数据
     * @throws BusinessException
     */
    DealerShoper productToBalance(String productId, Short productType, List<String> skuIds, List<Integer> purchaseNums, String dealerId) throws BusinessException;
}
