package com.zttx.web.module.brand.model;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/7/29.
 */
public class UserGrantModel {


    public String productId;
    public String productImage;               //产品主图
    public String typeNo;                     // "A00003"工厂店 "A00001"爆款  "A00002" 清仓  "default"普通

    public BigDecimal productPrice;           //产品的吊牌价

    public BigDecimal proLowestSkuPrice;     //产品中最低的sku价格（产品对该终端商授权才会有值）
    public Integer priceType;                // 0 吊牌价  1 直供价   2 活动价

    public Boolean  isValid = true ;         //是否失效   false 0 失效  true 1 有效
    public Boolean  isAuth  = false;         //产品是否对该用用户授权   true 授权 false 未授权
    public Boolean  isStateChange = false;   //是否产品状态改变，本应该查出活动价，但活动已经不存在查出了直供价，这样的状态改变  则为true
    public Integer  startNum  ;              //产品的起批量
    public short    userType ;               //用户类型 0游客  1经销商  2品牌商




    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }


    public BigDecimal getProLowestSkuPrice() {
        return proLowestSkuPrice;
    }

    public void setProLowestSkuPrice(BigDecimal proLowestSkuPrice) {
        this.proLowestSkuPrice = proLowestSkuPrice;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Boolean getIsStateChange() {
        return isStateChange;
    }

    public void setIsStateChange(Boolean isStateChange) {
        this.isStateChange = isStateChange;
    }

    public short getUserType() {
        return userType;
    }

    public void setUserType(short userType) {
        this.userType = userType;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isAuth) {
        this.isAuth = isAuth;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
