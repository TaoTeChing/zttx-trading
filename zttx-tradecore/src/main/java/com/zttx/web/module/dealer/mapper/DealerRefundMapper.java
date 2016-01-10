/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.model.DealerRefundModel;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 经销商退款信息 持久层接口
 * <p>File：DealerRefundDao.java </p>
 * <p>Title: DealerRefundDao </p>
 * <p>Description:DealerRefundDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerRefundMapper extends GenericMapper<DealerRefund>
{
    /**
     * 根据订单号查询退款记录
     * @param orderNumber 订单表中的orderId
     * @return
     */
    DealerRefund getEntityByOrderNumber(@Param("orderNumber") Long orderNumber);
    /**
     * 获取退款数量
     * @param refund
     * @return
     */
    Long getDealerRefundCount(DealerRefund refund);
    
    /**
     * 修改退款记录的 nextActTime 属性,nextActTime为NULL表示没有下一个操作
     * @param refrenceId
     * @param nextActTime
     */
    Integer updateNextActTime(@Param("refrenceId") String refrenceId, @Param("nextActTime") Long nextActTime);
    
    /**
     * 修改退款状态
     * @param refrenceId
     * @param refundState
     * @return
     */
    Integer updateRefundState(@Param("refrenceId") String refrenceId, @Param("refundState") Short refundState);
    
    /**
     * 授信订单修改退款状态
     * @param refrenceId
     * @param refundState
     * @param reachAmount
     */
    Integer updateRefundState4Factory(@Param("refrenceId") String refrenceId, @Param("refundState") Short refundState, @Param("reachAmount") BigDecimal reachAmount,
            @Param("reachStatus") Short reachStatus);
    
    /**
     * 根据订单主键查找
     * @param orderId 订单表中的refrenceId
     * @return
     */
    DealerRefund findByOrderId(@Param("orderId") String orderId);
    
    /**
     * 根据主键查询,返回对象包括brandName,dealerName信息
     * @param refrenceId
     * @return
     */
    DealerRefundModel getEntityById(@Param("refrenceId") String refrenceId);
    
    /**
     * 修改客服介入处理状态
     * @param refrenceId
     * @param serProStatus
     * @return
     */
    Integer updateSerProStatus(@Param("refrenceId") String refrenceId, @Param("serProStatus") Short serProStatus);
    
    /**
     * 取消客服介入
     * @param dealerRefund
     * @return
     */
    Integer updateRefuseJoin(DealerRefund dealerRefund);
    
    /**
     * 拒绝退款
     * @param refrenceId
     * @param refundState
     * @param nextActTime
     */
    void refuseRefund(@Param("refrenceId") String refrenceId, @Param("refundState") Short refundState, @Param("nextActTime") Long nextActTime);
    
    /**
     * 修改refundState,brandRecAddr,nextActTime,updateTime
     * @param refrenceId
     * @param refundState
     * @param brandRecAddr
     * @param nextActTime
     */
    Integer updateAgreeReturnBoth1(@Param("refrenceId") String refrenceId, @Param("refundState") Short refundState, @Param("brandRecAddr") String brandRecAddr,
            @Param("nextActTime") Long nextActTime,@Param("updateTime") Long updateTime);
    
    /**
     * 根据退款单编号和品牌商编号取退款信息
     * @param refundId
     * @param brandId
     * @return
     */
    DealerRefund getByRefundIdAndBrandId(@Param("refundId") Long refundId, @Param("brandId") String brandId);

    /**
     * 授信订单退款信息
     * @param dealerRefund
     * @return
     */
    List<DealerRefund> factoryStoreDealerRefund(DealerRefund dealerRefund);
    
    /**
     * 同步退款信息(boss)
     * @param dealerRefund
     * @return
     */
    List<DealerRefund> getDealerRefundList4Boss(DealerRefundModel dealerRefund);
    
    /**
     * 客服介入下，更改退款状态
     * @param refrenceId
     * @param refundState
     * @param serProStatus
     */
    void updateRefundStatutsCusJoin(@Param("refrenceId") String refrenceId, @Param("refundState") Short refundState, @Param("serProStatus") Short serProStatus);
    
    /**
     * 根据条件获取退款信息
     * 条件 refundState < 6 and d.nextActTime < ?
     * @param nextActTime
     * @param page
     * @return
     */
    List<DealerRefundModel> getDealerRefund(@Param("nextActTime") Long nextActTime, @Param("page") Pagination page);
    
    /**
     * 查询所有申请客服介入的记录
     * @param dealerRefund
     * @return
     * @author 李星
     */
    List<DealerRefundModel> selectAllCusJoin(DealerRefundModel dealerRefund);
    
    /**
     * 修改客服介入状态
     * @param dealerRefund
     */
    void updateModify(DealerRefund dealerRefund);

    /**
     * 获取已完成的所有退款记录(9：同意退款 10：同意退货退款)
     *
     * @param refrenceId
     * @return
     */
    List<Map<String, Object>> getCompleteDealerRefundList(String refrenceId);


    /**
     * 退货发货
     * @param dealerRefund
     * @param dealerId
     * @return
     */
    int returnDeliver(@Param("dealerRefund")DealerRefund dealerRefund, @Param("dealerId")String dealerId);
    
    /**
     * 客服介入
     * @param refrenceId
     * @param dealerId
     */
    void appserJoin(@Param("joinTime") Long joinTime, @Param("refrenceId") String refrenceId, @Param("dealerId") String dealerId);
    /**
     * 查询所有铺货订单的退款退货记录 --- 目录类型
     * @param dealerId
     * @param brandId
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectRefundTypeList(@Param("dealerId") String dealerId, @Param("brandId") String brandId);

    /*
     *查询所有铺货订单的退款退货记录 ---data
     * @param dealerRefund
     * @return
     * @author  易永耀
     */
    List<DealerRefund> selectDealerRefund(DealerRefund dealerRefund);
    /**查询未完成退款的金额信息
     * @param dealerId
     * @param brandId
     * @return
     * @author 易永耀
     */
    Map<String,Object> getDealerRefundInfoProcessing(String dealerId, String brandId);
}
