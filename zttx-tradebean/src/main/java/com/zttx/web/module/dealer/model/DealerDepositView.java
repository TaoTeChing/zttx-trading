package com.zttx.web.module.dealer.model;

import java.math.BigDecimal;

/**
 * 设置押金视图类
 *
 * @author 江枫林
 * @date 2015/9/24
 */
public class DealerDepositView {

    Integer joinForm;//交易模式
    Boolean point;//是否支持返点
    BigDecimal creditAmount;//信誉额度
    BigDecimal creditPaidPercent;//押金比例
    BigDecimal discount;//折扣
    BigDecimal depositTotalAmount;//押金金额

    
    public Boolean getPoint()
    {
        return point;
    }

    public void setPoint(Boolean point)
    {
        this.point = point;
    }

    public Integer getJoinForm() {
        return joinForm;
    }

    public void setJoinForm(Integer joinForm) {
        this.joinForm = joinForm;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getCreditPaidPercent() {
        return creditPaidPercent;
    }

    public void setCreditPaidPercent(BigDecimal creditPaidPercent) {
        this.creditPaidPercent = creditPaidPercent;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDepositTotalAmount() {
        return depositTotalAmount;
    }

    public void setDepositTotalAmount(BigDecimal depositTotalAmount) {
        this.depositTotalAmount = depositTotalAmount;
    }
}
