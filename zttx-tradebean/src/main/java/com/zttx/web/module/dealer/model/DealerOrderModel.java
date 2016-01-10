package com.zttx.web.module.dealer.model;

import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrders;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 李星 on 2015/8/14.
 */
public class DealerOrderModel extends DealerOrder
{
    // 订单统计类型
    private Short  orderCountType;
    
    // 订单状态
    private String orderStatusStr;
    
    // 开始时间
    private String startTimeStr;
    
    // 结束时间
    private String endTimeStr;
    
    // 是否显示关闭订单 值不为空就是不显示
    private String isShowClose;
    
    // 经销商名称
    private String dealerName;
    
    // 公司名称(comName)
    private String brandName;
    
    // 品牌名称
    private String brandsName;
    
    // 订单显示类型
    private String orderType;

    // 优惠或加价类型 0、优惠 1、加价
    private Short      privilege_select;
    
    private BigDecimal newBalance;
    
    private BigDecimal oldBalance;
    
    // 搜索
    private String     orderIdStr;

    // 原有的运费
    private BigDecimal tmpAdjust;
    
    // 是否拿样订单
    private Boolean    isSampleOrder;
    
    // 订单项
    private List<DealerOrders> dealerOrders;
    
    private String             userMobile;

    // 采购金额
    private BigDecimal         purcMoney;

    // 最终金额
    private BigDecimal         sumMoney;

    // 欠款
    private BigDecimal         debtMoney;
    
    /**
     * 获取 订单 当前已支付金额(货款和运费)
     */
    public BigDecimal getAlreadyPayAmount()
    {
        BigDecimal dc = BigDecimal.ZERO;
        if (getPayment() != null) dc = dc.add(getPayment());
        if (getAdjustFreight() != null && Short.valueOf(DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED).equals(getFrePayState())) dc = dc.add(getAdjustFreight());
        return dc;
    }

    public Short getOrderCountType()
    {
        return orderCountType;
    }
    
    public void setOrderCountType(Short orderCountType)
    {
        this.orderCountType = orderCountType;
    }
    
    public String getOrderStatusStr()
    {
        return orderStatusStr;
    }
    
    public void setOrderStatusStr(String orderStatusStr)
    {
        this.orderStatusStr = orderStatusStr;
    }
    
    public String getStartTimeStr()
    {
        return startTimeStr;
    }
    
    public void setStartTimeStr(String startTimeStr)
    {
        this.startTimeStr = startTimeStr;
    }
    
    public String getEndTimeStr()
    {
        return endTimeStr;
    }
    
    public void setEndTimeStr(String endTimeStr)
    {
        this.endTimeStr = endTimeStr;
    }
    
    public String getIsShowClose()
    {
        return isShowClose;
    }
    
    public void setIsShowClose(String isShowClose)
    {
        this.isShowClose = isShowClose;
    }
    
    public String getDealerName()
    {
        return dealerName;
    }
    
    public void setDealerName(String dealerName)
    {
        this.dealerName = dealerName;
    }
    
    public String getOrderType()
    {
        return orderType;
    }
    
    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }
    
    public String getBrandName()
    {
        return brandName;
    }
    
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }
    
    public String getBrandsName()
    {
        return brandsName;
    }
    
    public void setBrandsName(String brandsName)
    {
        this.brandsName = brandsName;
    }
    
    public Short getPrivilege_select()
    {
        return privilege_select;
    }
    
    public void setPrivilege_select(Short privilege_select)
    {
        this.privilege_select = privilege_select;
    }
    
    public BigDecimal getNewBalance()
    {
        return newBalance;
    }
    
    public void setNewBalance(BigDecimal newBalance)
    {
        this.newBalance = newBalance;
    }
    
    public BigDecimal getOldBalance()
    {
        return oldBalance;
    }
    
    public void setOldBalance(BigDecimal oldBalance)
    {
        this.oldBalance = oldBalance;
    }
    
    public String getOrderIdStr()
    {
        return orderIdStr;
    }
    
    public void setOrderIdStr(String orderIdStr)
    {
        this.orderIdStr = orderIdStr;
    }
    
    public BigDecimal getTmpAdjust()
    {
        return tmpAdjust;
    }
    
    public void setTmpAdjust(BigDecimal tmpAdjust)
    {
        this.tmpAdjust = tmpAdjust;
    }
    
    @Override
    public Boolean getIsSampleOrder()
    {
        return isSampleOrder;
    }
    
    @Override
    public void setIsSampleOrder(Boolean isSampleOrder)
    {
        this.isSampleOrder = isSampleOrder;
    }
    
    public List<DealerOrders> getDealerOrders()
    {
        return dealerOrders;
    }
    
    public void setDealerOrders(List<DealerOrders> dealerOrders)
    {
        this.dealerOrders = dealerOrders;
    }
    
    public String getUserMobile()
    {
        return userMobile;
    }
    
    public void setUserMobile(String userMobile)
    {
        this.userMobile = userMobile;
    }

    public BigDecimal getPurcMoney() {
        return purcMoney;
    }

    public void setPurcMoney(BigDecimal purcMoney) {
        this.purcMoney = purcMoney;
    }

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public BigDecimal getDebtMoney() {
        return debtMoney;
    }

    public void setDebtMoney(BigDecimal debtMoney) {
        this.debtMoney = debtMoney;
    }
}
