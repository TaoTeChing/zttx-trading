/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商退款信息 实体对象
 * <p>File：DealerRefund.java</p>
 * <p>Title: DealerRefund</p>
 * <p>Description:DealerRefund</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class DealerRefund extends GenericEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号=>订单表中的refrenceId
     */
    private java.lang.String orderId;
    /**
     * 退款友好编号
     */
    private java.lang.Long refundId;
    /**
     * 好友订单编号=订单表中的orderId
     */
    private java.lang.Long orderNumber;
    /**
     * 经销商主帐号编号
     */
    private java.lang.String dealerId;
    private java.lang.String dealerName;//经销商名称 用于显示
    /**
     * 品牌商主帐号编号
     */
    private java.lang.String brandId;
    /**
     * 是否收到货
     */
    @NotNull(message = "到货状态不能为空")
    private java.lang.Boolean received;
    /**
     * 是否需要退货
     */
    @NotNull(message = "是否需要退货不能为空")
    private java.lang.Boolean needRefund;
    /**
     * 总金额
     */
    private java.math.BigDecimal totalAmount;
    /**
     * 退款金额
     */
    @NotNull(message = "退款金额不能为空")
    private java.math.BigDecimal refundAmount;
    /**
     * 支付给品牌商的金额
     */
    private java.math.BigDecimal comPayment;
    /**
     * 退款的原因
     */
    @NotBlank(message = "退款的原因不能为空")
    private java.lang.String refundReason;
    /**
     * 退款描述
     */
    @NotBlank(message = "退款说明不能为空")
    private java.lang.String refundMark;
    /**
     * 品牌商收货地址
     */
    private java.lang.String brandRecAddr;
    /**
     * 商品退款时间
     */
    private java.lang.Long returnTime;
    /**
     * 物流公司名称
     */
    private java.lang.String logisticsName;
    /**
     * 运送单号
     */
    private java.lang.String transNum;
    /**
     * 退款状态  1：申请退款等待处理2：同意退货等待发货 3：退货已发货  4：拒绝退款5：拒绝退货   6：退款关闭 7: 取消退款9：同意退款 10：同意退货退款
     */
    private java.lang.Short refundState;
    /**
     * 备注
     */
    private java.lang.String remark;
    /**
     * 品牌商处理结果
     */
    private java.lang.String brandMark;
    /**
     * 操作时间
     */
    private java.lang.Long createTime;
    /**
     * 修改时间
     */
    private java.lang.Long updateTime;
    /**
     * nextActTime
     */
    private java.lang.Long nextActTime;
    /**
     * 协议修改次数
     */
    private java.lang.Integer updateNum;
    /**
     * 是否申请客服介入
     */
    private java.lang.Boolean cusJoin;
    /**
     * 申请客服介入的时间
     */
    private java.lang.Long joinTime;
    /**
     * 协议提出时间
     */
    private java.lang.Long applyTime;
    /**
     * 客服介入处理状态1：客服介入处理中，2：纠纷处理中，3：处理完毕,4:纠纷关闭5:等待客服介入中
     */
    private java.lang.Short serProStatus;
    /**
     * 客服处理结果
     */
    private java.lang.String serProResult;
    /**
     * 是否为工厂店品牌
     */
    private java.lang.Boolean factoryStore;
    /**
     * 抵用金额(欠收抵应付金额)
     */
    private java.math.BigDecimal reachAmount;
    /**
     * 退款支付方式(0:直接支付 1:欠收抵应付 2:0和1同步存在)
     */
    private java.lang.Short reachStatus;

    /*========================================= 临时字段 [@author易永耀] begin================================================*/
    private String needRefundSel;

    public String getNeedRefundSel() {
        return needRefundSel;
    }

    public void setNeedRefundSel(String needRefundSel) {
        this.needRefundSel = needRefundSel;
    }
    /*========================================= 临时字段 end ================================================*/


    public java.lang.String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }

    public java.lang.Long getRefundId() {
        return this.refundId;
    }

    public void setRefundId(java.lang.Long refundId) {
        this.refundId = refundId;
    }

    public java.lang.Long getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(java.lang.Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public java.lang.String getDealerId() {
        return this.dealerId;
    }

    public void setDealerId(java.lang.String dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public java.lang.String getBrandId() {
        return this.brandId;
    }

    public void setBrandId(java.lang.String brandId) {
        this.brandId = brandId;
    }

    public java.lang.Boolean getReceived() {
        return this.received;
    }

    public void setReceived(java.lang.Boolean received) {
        this.received = received;
    }

    public java.lang.Boolean getNeedRefund() {
        return this.needRefund;
    }

    public void setNeedRefund(java.lang.Boolean needRefund) {
        this.needRefund = needRefund;
    }

    public java.math.BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(java.math.BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public java.math.BigDecimal getRefundAmount() {
        return this.refundAmount;
    }

    public void setRefundAmount(java.math.BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public java.math.BigDecimal getComPayment() {
        return this.comPayment;
    }

    public void setComPayment(java.math.BigDecimal comPayment) {
        this.comPayment = comPayment;
    }

    public java.lang.String getRefundReason() {
        return this.refundReason;
    }

    public void setRefundReason(java.lang.String refundReason) {
        this.refundReason = refundReason;
    }

    public java.lang.String getRefundMark() {
        return this.refundMark;
    }

    public void setRefundMark(java.lang.String refundMark) {
        this.refundMark = refundMark;
    }

    public java.lang.String getBrandRecAddr() {
        return this.brandRecAddr;
    }

    public void setBrandRecAddr(java.lang.String brandRecAddr) {
        this.brandRecAddr = brandRecAddr;
    }

    public java.lang.Long getReturnTime() {
        return this.returnTime;
    }

    public void setReturnTime(java.lang.Long returnTime) {
        this.returnTime = returnTime;
    }

    public java.lang.String getLogisticsName() {
        return this.logisticsName;
    }

    public void setLogisticsName(java.lang.String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public java.lang.String getTransNum() {
        return this.transNum;
    }

    public void setTransNum(java.lang.String transNum) {
        this.transNum = transNum;
    }

    public java.lang.Short getRefundState() {
        return this.refundState;
    }

    public void setRefundState(java.lang.Short refundState) {
        this.refundState = refundState;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public java.lang.String getBrandMark() {
        return this.brandMark;
    }

    public void setBrandMark(java.lang.String brandMark) {
        this.brandMark = brandMark;
    }

    public java.lang.Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.lang.Long createTime) {
        this.createTime = createTime;
    }

    public java.lang.Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.lang.Long updateTime) {
        this.updateTime = updateTime;
    }

    public java.lang.Long getNextActTime() {
        return this.nextActTime;
    }

    public void setNextActTime(java.lang.Long nextActTime) {
        this.nextActTime = nextActTime;
    }

    public java.lang.Integer getUpdateNum() {
        return this.updateNum;
    }

    public void setUpdateNum(java.lang.Integer updateNum) {
        this.updateNum = updateNum;
    }

    public java.lang.Boolean getCusJoin() {
        return this.cusJoin;
    }

    public void setCusJoin(java.lang.Boolean cusJoin) {
        this.cusJoin = cusJoin;
    }

    public java.lang.Long getJoinTime() {
        return this.joinTime;
    }

    public void setJoinTime(java.lang.Long joinTime) {
        this.joinTime = joinTime;
    }

    public java.lang.Long getApplyTime() {
        return this.applyTime;
    }

    public void setApplyTime(java.lang.Long applyTime) {
        this.applyTime = applyTime;
    }

    public java.lang.Short getSerProStatus() {
        return this.serProStatus;
    }

    public void setSerProStatus(java.lang.Short serProStatus) {
        this.serProStatus = serProStatus;
    }

    public java.lang.String getSerProResult() {
        return this.serProResult;
    }

    public void setSerProResult(java.lang.String serProResult) {
        this.serProResult = serProResult;
    }

    public java.lang.Boolean getFactoryStore() {
        return this.factoryStore;
    }

    public void setFactoryStore(java.lang.Boolean factoryStore) {
        this.factoryStore = factoryStore;
    }

    public java.math.BigDecimal getReachAmount() {
        return this.reachAmount;
    }

    public void setReachAmount(java.math.BigDecimal reachAmount) {
        this.reachAmount = reachAmount;
    }

    public java.lang.Short getReachStatus() {
        return this.reachStatus;
    }

    public void setReachStatus(java.lang.Short reachStatus) {
        this.reachStatus = reachStatus;
    }

}

