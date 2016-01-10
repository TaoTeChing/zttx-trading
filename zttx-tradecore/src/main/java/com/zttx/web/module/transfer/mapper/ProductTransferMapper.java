package com.zttx.web.module.transfer.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zttx.sdk.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：ProductTransferMapper.java</p>
 * <p>Title: 产品信息迁移服务</p>
 * <p>Description:ProductTransferMapper </p>
 * <p>Copyright: Copyright (c)  15/10/8</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductTransferMapper
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
    List<Map<String, Object>> findSampleProductInfo(@Param("page") Pagination page) throws BusinessException;
    
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
    List<Map<String, Object>> findFactoryActivityProduct(@Param("page") Pagination page) throws BusinessException;
    
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
    void modifySkuSamplePrice(@Param("productId") String productId, @Param("price") BigDecimal price) throws BusinessException;
    
    /**
     * 修改指定编号的产品授信状态
     * @param list
     * @throws BusinessException
     * @deprecated
     */
    void modifyBatchProductCreditState(List<String> list) throws BusinessException;

    /**
     * 修改指定编号的产品授信状态
     * @param productId
     * @throws BusinessException
     */
    void modifyProductCreditState(String productId) throws BusinessException;
    /**
     * 根据产品编号批量将工厂店活动价设置到授信价中去
     * @param productId
     * @throws BusinessException
     */
    void modifySkuCreditPrice(String productId) throws BusinessException;
}
