/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.bean.EnumDescribable;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.dealer.entity.DealerRefAttacht;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.model.DealerRefundModel;

/**
 * 经销商退款信息 服务接口
 * <p>File：DealerRefundService.java </p>
 * <p>Title: DealerRefundService </p>
 * <p>Description:DealerRefundService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface DealerRefundService extends GenericServiceApi<DealerRefund>{

    /**
     * 根据订单号查询退款记录
     *
     * @param orderNumber
     * @return
     */
    DealerRefund getEntityByOrderNumber(Long orderNumber);
    
    /**
     * 根据主键查询,返回对象包括brandName,dealerName信息
     *
     * @param refrenceId
     * @return
     */
    DealerRefundModel getEntityById(@Param("refrenceId") String refrenceId);
    
    /**
     * 同意退款(品牌商操作)
     * @param refrenceId
     * @param brandId
     * @param pwd
     * @throws BusinessException
     */
    void updateAgreeReturn(String refrenceId, String brandId, String pwd) throws BusinessException;
    
    /**
     * 获取当期欠付款数据
     * @param brandId
     * @param dealerId
     * @return
     * @throws BusinessException
     * @author 李星
     */
    BigDecimal getAllDebtMoney(String brandId, String dealerId) throws BusinessException;
    
    /**
     * 拒绝退款
     *
     * @param refrenceId
     * @param userId
     * @throws BusinessException
     * @throws Exception
     * @author 张孟如
     */
    void refuseReturn(String refrenceId, String userId, String remark) throws BusinessException;
    
    /**
     * 退货退款--品牌商操作超时，同意退货（系统操作）
     *
     * @param dealerRefunderList
     * @param isSystemJob
     */
    void updateAutoAgreeReturnBoth(List<DealerRefundModel> dealerRefunderList, Boolean isSystemJob) throws BusinessException;
    
    /**
     * 拒绝退货
     *
     * @param refrenceId
     * @param userId
     * @throws BusinessException
     * @throws Exception
     * @author 张孟如
     */
    void refuseRefund(String refrenceId, String userId, String remark) throws BusinessException;
    
    /**
     * 授信订单同意退款(品牌商操作)
     *
     * @param refrenceId
     * @param brandId
     * @param pwd
     */
    void updateFactoryAgreeReturn(String refrenceId, String brandId, String pwd) throws BusinessException;

    /**
     * 同意退款(品牌商操作)
     * @param refrenceId
     * @param brandId
     * @param pwd
     * @throws BusinessException
     */
    void updateTotalAgreeReturn(String refrenceId, String brandId, String pwd) throws BusinessException;
    
    /**
     * 根据退款单编号和品牌商编号取退款信息
     *
     * @param refundId
     * @param brandId
     * @return
     */
    DealerRefund getByRefundIdAndBrandId(Long refundId, String brandId);
    
    /**
     * 查询工厂店的退款信息
     *
     * @param dealerRefund
     * @param page
     * @return {@link com.zttx.sdk.bean.PaginateResult<com.zttx.web.module.dealer.entity.DealerRefund>}
     * @throws BusinessException
     */
    public PaginateResult<DealerRefund> factoryStoreDealerRefund(DealerRefund dealerRefund, Pagination page) throws BusinessException;
    
    /**
     * 同步退款信息(boss)
     * @param dealerRefund
     * @param page
     * @return
     */
    PaginateResult<DealerRefund> getDealerRefundList4Boss(DealerRefundModel dealerRefund, Pagination page);
    
    /**
     * 品牌商操作超时,自动同意退款(系统操作)
     * @param dealerRefund
     */
    void updateAutoAgreeReturn(DealerRefundModel dealerRefund) throws BusinessException;
    
    /**
     * 根据条件获取退款信息
     * 条件 refundState < 6 and d.nextActTime < ?
     * @param nextActTime
     * @param page
     * @return
     */
    PaginateResult<DealerRefundModel> getDealerRefund(Long nextActTime, Pagination page);
    
    /**
     * 退货退款--品牌商操作超时，同意退货（系统操作）
     * @param dealerRefund
     * @param isSystemJob
     */
    void updateAutoAgreeReturnBoth(DealerRefundModel dealerRefund, Boolean isSystemJob) throws BusinessException;
    
    /**
     * 经销商填写物流信息超时，执行此调度(系统调度方法 )
     * <p>规避大事务所造成的数据锁的问题</p>
     */
    void updateLogisTimeoutTask(DealerRefundModel dealerRefund) throws BusinessException;
    
    /**
     * 查询所有申请客服介入的记录
     * @param dealerRefund
     * @param page
     * @return
     */
    PaginateResult<DealerRefundModel> selectAllCusJoin(DealerRefundModel dealerRefund, Pagination page);
    
    /**
     * 更新客服介入状态
     * @param dealerRefund
     */
    void updateSerProStatusResult(DealerRefund dealerRefund);
    
    /**
     * 更新客服介入状态
     * @param dealerRefund
     * @return
     */
    EnumDescribable updateCusjoining(DealerRefund dealerRefund);
    
    /**
     * 更新客服介入状态
     * @param dealerRefund
     * @return
     */
    EnumDescribable updateRefuseJoin(DealerRefund dealerRefund);
    
    /**
     * 客服修改状态
     * @param dealerRefund
     * @return
     */
    EnumDescribable updateModify(DealerRefundModel dealerRefund);
    
    /**
     * 取消退款
     * @param refrenceId
     * @param userId
     * @throws BusinessException
     */
    void updateRefundProcess(String refrenceId, String userId) throws BusinessException;
    
    /**
     * 验证申请退款金额是否有效
     * @param refundAmount 申请退款金额
     * @param totalAmount  订货金额
     * @param freight      运费
     */
    boolean validateRefundAmount(BigDecimal refundAmount, BigDecimal totalAmount, BigDecimal freight) throws BusinessException;
    
    /**
     * 申请退款
     * @param dealerRefund           退款申请信息
     * @param dealerRefAttachts      退款申请信息
     * @param dealerUserm            经销商用户信息
     */
    void addApply(DealerRefundModel dealerRefund, List<DealerRefAttacht> dealerRefAttachts, UserInfo dealerUserm) throws BusinessException;
    
    /**
     * 修改退款申请
     * @param dealerRefund           退款申请信息
     * @param dealerRefAttachts      退款申请信息
     * @param dealerUserm            经销商用户信息
     */
    void updateApply(DealerRefundModel dealerRefund, List<DealerRefAttacht> dealerRefAttachts, UserInfo dealerUserm) throws BusinessException;
    
    /**
     * 退货发货
     * @param dealerRefund
     * @param dealerId
     * @author 周光暖
     */
    void updateDeliver(DealerRefundModel dealerRefund, String dealerId);

    /**
     * 退货发货
     * @param dealerRefund
     * @param dealerId
     * @author 周光暖
     */
    void updateDeliverFactory(DealerRefundModel dealerRefund, String dealerId);
    
    /**
     * 客服介入
     * @param refrenceId
     * @param dealerId
     */
    void appserjoin(String refrenceId, String dealerId);

    /**
     * 客服介入总账
     * @param refrenceId
     * @param dealerId
     */
    void appserjoinFactory(String refrenceId, String dealerId);

    /***
     * 查询所有铺货订单的退款退货记录 --- 详细数据
     * @param dealerRefund
     * @param page
     * @return
     * @author 易永耀
     */
    PaginateResult<DealerRefund> selectDealerRefund(DealerRefund dealerRefund, Pagination page);

    /**
     * 查询所有铺货订单的退款退货记录 --- 目录类型
     * @param dealerId
     * @param brandId
     * @return
     * @author 易永耀
     */
    Map<String,Object> selectRefundTypeList(String dealerId, String brandId);
    /**查询未完成退款的金额信息
     * @param dealerId
     * @param brandId
     * @return
     * @author 易永耀
     */
    Map<String,Object> getDealerRefundInfoProcessing(String dealerId, String brandId);

    /**
     * 保存退款申请
     * @param dealerRefund
     * @param dealerRefAttachts
     * @param dealerId
     * @param brandId
     * @throws BusinessException
     */
    void saveFactoryDealerRefund(DealerRefund dealerRefund, List<DealerRefAttacht> dealerRefAttachts, String dealerId, String brandId) throws BusinessException;
}
