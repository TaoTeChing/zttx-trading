package com.zttx.web.module.dealer.model;

import com.google.common.collect.Lists;
import com.zttx.web.module.dealer.entity.DealerShoper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>File:ShoperModel</p>
 * <p>Title: </p>
 * <p>Description: 购物车模型</p>
 * <p>Copyright: Copyright (c)2015/8/22 16:07</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class ShoperModel implements Serializable {


    private List<DealerShoper> dealerShoperList = Lists.newArrayList();

    private String             brandesId;

    private String             brandesName;

    private String             brandId;

    private String             brandName;

    private Integer            purchaseNumAllSum = 0 ;   //结算页面同一品牌下的所有的购买的sku的数量         shopper_balance_v2.jsp 进货总量

    private BigDecimal purchasePriceAllSum = BigDecimal.ZERO; //结算页面同一品牌下的所有购买的sku的数量的总价钱  shopper_balance_v2.jsp总货款(未加运费)

    private BigDecimal         purchaseWeightAllSum = BigDecimal.ZERO; //结算页面同一品牌下购买的所有的产品的总重量 (用于计算运费)

    public List<DealerShoper> getDealerShoperList() {
        return dealerShoperList;
    }

    public void setDealerShoperList(List<DealerShoper> dealerShoperList) {
        this.dealerShoperList = dealerShoperList;
    }

    public String getBrandesId() {
        return brandesId;
    }

    public void setBrandesId(String brandesId) {
        this.brandesId = brandesId;
    }

    public String getBrandesName() {
        return brandesName;
    }

    public void setBrandesName(String brandesName) {
        this.brandesName = brandesName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getPurchaseNumAllSum() {
        return purchaseNumAllSum;
    }

    public void setPurchaseNumAllSum(Integer purchaseNumAllSum) {
        this.purchaseNumAllSum = purchaseNumAllSum;
    }

    public BigDecimal getPurchasePriceAllSum() {
        return purchasePriceAllSum;
    }

    public void setPurchasePriceAllSum(BigDecimal purchasePriceAllSum) {
        this.purchasePriceAllSum = purchasePriceAllSum;
    }

    public BigDecimal getPurchaseWeightAllSum() {
        return purchaseWeightAllSum;
    }

    public void setPurchaseWeightAllSum(BigDecimal purchaseWeightAllSum) {
        this.purchaseWeightAllSum = purchaseWeightAllSum;
    }
}
