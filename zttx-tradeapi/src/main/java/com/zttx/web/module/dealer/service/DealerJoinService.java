/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.model.BrandJoinFilter;
import com.zttx.web.module.common.model.TransferSearchModel;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.model.DealerDepositView;
import com.zttx.web.module.dealer.model.DealerJoinModel;

/**
 * 经销商加盟信息 服务接口
 * <p>File：DealerJoinService.java </p>
 * <p>Title: DealerJoinService </p>
 * <p>Description:DealerJoinService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerJoinService extends GenericServiceApi<DealerJoin>
{
    /**
     * 根据dealerId获取与该经销商加盟的 map
     * @param dealerId
     * @return
     * @author 易永耀
     */
    public List<Map<String, Object>> findByDealerId(String dealerId, Short joinState);
    
    /**
     * 根据dealerId获取与该经销商加盟关系列表，带分页
     * @param dealerId
     * @return
     * @author 江枫林
     */
    public PaginateResult<Map<String, Object>> findJoinByDealerId(String dealerId, String brandName, String brandsName, Pagination pagination);
    
    /**
     * 根据经销商id和品牌id查找关系
     * @param dealerId
     * @param brandsId
     * @return
     */
    DealerJoin findByDealerIdAndBrandsId(String dealerId, String brandsId);
    
    /**
     * 根据加盟主键和品牌商id 查询有合作的加盟关系
     *
     * @param refrenceId
     * @param brandId
     * @return
     */
    public DealerJoin findByRefrenceIdAndBrandId(String refrenceId, String brandId);
    
    /**
     * 终端商已加盟的品牌列表
     *
     * @param dealerId
     * @param brandName
     * @return
     */
    public List<Map<String, Object>> findJoinByDealerId(String dealerId, String brandName, String brandsName);
    
    /**
     * 获取所有合作中的经销商，用于发送消息
     * @param brandId
     * @return
     */
    String[] getDealerIdsWithJoin(String brandId);
    
    /**
     * 根据过滤条件获取经销商加盟信息
     * @author 陈建平
     * @param filter
     * @return
     */
    List<Map<String, Object>> getDealerJoinList(DealerJoin filter);
    
    /**
     * 查询加盟关系
     *
     * @param filter
     * @author 江枫林
     * @return
     */
    public List<DealerJoin> findDealerJoin(DealerJoin filter);
    
    /**
     * 查询品牌商已合作的终端商
     *
     * @param branderId
     * @param pagination
     * @param filter
     * @return
     */
    public PaginateResult<Map<String, Object>> findByBrandId(String branderId, Pagination pagination, BrandJoinFilter filter) throws BusinessException;
    
    /**
     * 搜索加盟brandId所有的经销商
     * @author 陈建平
     * @param searchBean
     * @return
     */
    PaginateResult<Map<String, Object>> findCooperatedDealer(TransferSearchModel searchBean);
    
    /**
     * 搜索和dealerId合作的所有的品牌商
     * @author 陈建平
     * @param searchBean
     * @return
     */
    PaginateResult<Map<String, Object>> findCooperatedBrand(TransferSearchModel searchBean);
    
    /**
     * 品牌商终止加盟合作
     * @author 陈建平
     * @param dealerJoin
     * @param endMark
     * @throws BusinessException
     */
    void updateStopBrandJoinState(DealerJoin dealerJoin, String endMark) throws BusinessException;
    
    /**
     * 根据经销商编号DealerId，品牌编号brandsId查询
     * @param dealerId
     * @param brandsId
     * @return
     * @author 李星  与 findByDealerIdAndBrandsId 重复
     */
    DealerJoin selectDealerJoinByDealerIdAndBrandsId(String dealerId, String brandsId);
    
    /**
     * 增加已支付押金的金额
     * @param dealerJoin
     * @param paidAmount
     * @return
     * @author 张昌苗
     */
    void savePaidDepositAmount(DealerJoin dealerJoin, BigDecimal paidAmount) throws BusinessException;
    
    /**
     * 查新终端商下所有加盟品牌
     *
     * @param page
     * @param dealerId
     * @return
     */
    PaginateResult<Map<String, Object>> findByDealerId(Pagination page, String dealerId);
    
    /**
     * 终端商终止合作
     * @param dealerJoin
     * @throws BusinessException
     */
    public void updateStopDealerJoinState(DealerJoinModel dealerJoin) throws BusinessException;
    
    /*
     * 终止合作
     */
    public void brandTerminal(String id, String endMark, String branderId) throws BusinessException;
    
    /**
     * 根据终端商id和品牌商id查询加盟关系
     * @param dealerId
     * @param brandId
     * @return
     */
    public DealerJoin findByIdAndBrandId(String dealerId, String brandId);
    
    /**
     * 设置押金
     * @param dealerJoin
     * @param depositAmount
     * @throws BusinessException
     */
    public void saveSetDepositAmount(HttpServletRequest request, DealerJoin dealerJoin, DealerDepositView depositView) throws BusinessException;
    
    /**
     * 获取未结算的押金记录
     * @author 陈建平
     * @param page
     * @param filter
     * @return
     */
    PaginateResult<Map<String, Object>> selectUnClearingDepositList(Pagination page, DealerJoin filter);
    
    /**
     * 更新押金结算金额
     * @author 陈建平
     * @param refrenceIdArr
     * @param payAmountArr
     * @throws BusinessException
     */
    void updateDepositClearingAmount(String[] refrenceIdArr, BigDecimal[] payAmountArr) throws BusinessException;
    
    /**
     * 获取未付的押金金额
     * @param dealerJoin
     * @return
     * @author 张昌苗
     */
    public BigDecimal getUnpayDepositAmount(DealerJoin dealerJoin) throws BusinessException;
    
    /**
     * 分页查询出DealerJoin信息，关联dealerClass.查询
     * @param pagination
     * @param dealerJoin
     * @return
     */
    public PaginateResult<Map<String, Object>> queryDealerJoinPage(@Param("pagination") Pagination pagination, @Param("dealerJoin") DealerJoin dealerJoin);
    
    /**
     * 更新经销商level
     * @param dealerJoin
     */
    public void updateDealerLevel(DealerJoin dealerJoin, String[] idAry);
    
    /**
     * 获取授信关系（终端商ID，品牌商ID）
     * @return
     * @author 张昌苗
     */
    List<Map<String, Object>> listCreditJoin();
    
    /**
     * 根据终端商id和主键id批量获取加盟关系
     *
     * @param dealerId
     * @param joinIdArr
     * @return
     * @throws BusinessException
     */
    public List<DealerJoin> listWithException(String dealerId, String[] joinIdArr) throws BusinessException;
    
    /**
     * 根据终端商id和主键id获取加盟关系
     *
     * @param dealerId
     * @param joinId
     * @return
     * @throws BusinessException
     */
    public DealerJoin findWithException(String dealerId, String joinId) throws BusinessException;
    
    /**
     * 财务帐查经销商与品牌之间的授信关系，不受加盟状态限制
     * @author 易永耀
     * @param dealerId
     * @param brandsId
     * @return
     */
    DealerJoin getByDealerIdAndBrandsId(String dealerId, String brandsId);
    
    /**
     * 根据 dealerId brandId获取两之间的合作品牌
     * @author 易永耀
     * @param dealerId
     * @param brandId
     * @return
     *  map{brandesName:品牌名称}
     */
    List<Map<String, Object>> findByDealerIdAndBrandId(@Param("dealerId") String dealerId, @Param("brandId") String brandId) throws BusinessException;
    
    /**
     * 产品是否支持返点：经销商返点加盟+产品支持返点
     * @param dealerId 经销商id
     * @param productId 产品id
     * @return true 支持返点  false 不支持返点
     */
    Boolean isSupportPoint(String dealerId, String productId) throws BusinessException;
    
    /**
     * 产品是否支持返点：经销商返点加盟+产品支持返点
     * @param dealerJoin 加盟关系
     * @param productBaseInfo 产品
     * @return true 支持返点  false 不支持返点
     */
    Boolean isSupportPoint(DealerJoin dealerJoin, ProductBaseInfo productBaseInfo) throws BusinessException;
}
