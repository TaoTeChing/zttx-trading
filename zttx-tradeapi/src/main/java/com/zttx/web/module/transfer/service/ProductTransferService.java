package com.zttx.web.module.transfer.service;

import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>File：ProductTransferService.java</p>
 * <p>Title: 产品信息迁移服务</p>
 * <p>
 *     Description:数据迁移服务中提供的所有接口和实现方法其它模块不可引用，
 *   因为这些接口只提供一次性服务。
 * </p>
 * <p>Copyright: Copyright (c)  15/10/8</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface ProductTransferService
{
    /**
     * 取出拿样产品的总记录数
     * @return
     * @throws BusinessException
     */
    Long findSampleProductInfoCount() throws BusinessException;
    
    /**
     * 取出所有拿样产品
     * <p>
     *     数据迁移用，其它地址不得引用
     * </p>
     * @param page
     * @return List
     */
    List<Map<String, Object>> findSampleProductInfo(Pagination page) throws BusinessException;
    
    /**
     * 找出所有参加过工厂店活动的产品总数
     * @return {@link Long}
     * @throws BusinessException
     */
    Long findFactoryActivityProductCount() throws BusinessException;
    
    /**
     * 找出所有参加过工厂店活动的产品信息
     * @param page
     * @return {@link List}
     * @throws BusinessException
     */
    List<Map<String, Object>> findFactoryActivityProduct(Pagination page) throws BusinessException;
    
    /**
     * 根据产品编号返回SKU直供价中最高的一条
     * @param productId
     * @return {@link BigDecimal}
     * @throws BusinessException
     */
    BigDecimal getMaxSkuDriectPrice(String productId) throws BusinessException;
    
    /**
     * 根据产品编号修改SKU拿样价格
     * @param productId
     * @param price
     * @throws BusinessException
     */
    void modifySkuSamplePrice(String productId, BigDecimal price) throws BusinessException;
    
    /**
     * 修改指定编号的产品授信状态
     * @param products
     * @throws BusinessException
     */
    void modifyProductCreditState(List<Map<String, Object>> products) throws BusinessException;
    
    /**
     * 根据产品编号批量将工厂店活动价设置到授信价中去
     * @param productId
     * @throws BusinessException
     */
    void modifySkuCreditPrice(String productId) throws BusinessException;
}
