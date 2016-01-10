package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandDeal;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.dealer.entity.DealerInfo;

import java.util.List;

/**
 * <p>File:BrandInviteModel</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/8/12 17:59</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class BrandInviteModel  extends BrandInvite {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4145994533208850936L;

    private BrandInfo brandInfo;

    private BrandesInfo brandesInfo;

    private DealerInfo dealerInfo;

    private String brandDealStr;

    private List<BrandDeal> brandDeals;

    @Deprecated
    private Long startTime;

    @Deprecated
    private Long endTime;

    private String empNums;

    private String shopNums;

    private String telphone;

    private Boolean isExitsInquiry;                         // 是否存在询价单

    // 开始时间
    private String startTimeStr;

    // 结束时间
    private String endTimeStr;

    // 企业规模
    private String emploeeNum;

    // 年营业额
    private String moneyNum;

    // 加盟来源
    private Short joinSource;

    private String userMobile;

    private Short invokeType;                             // 1表示品牌商erp

    private boolean factoryAndZero;                         // 是工厂店品牌并且押金为0

    private String productId;
    private Boolean             isViewAdd;       // 是否是新增查看联系记录

    public BrandInfo getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(BrandInfo brandInfo) {
        this.brandInfo = brandInfo;
    }

    public BrandesInfo getBrandesInfo() {
        return brandesInfo;
    }

    public void setBrandesInfo(BrandesInfo brandesInfo) {
        this.brandesInfo = brandesInfo;
    }

    public DealerInfo getDealerInfo() {
        return dealerInfo;
    }

    public void setDealerInfo(DealerInfo dealerInfo) {
        this.dealerInfo = dealerInfo;
    }

    public String getBrandDealStr() {
        return brandDealStr;
    }

    public void setBrandDealStr(String brandDealStr) {
        this.brandDealStr = brandDealStr;
    }

    public List<BrandDeal> getBrandDeals() {
        return brandDeals;
    }

    public void setBrandDeals(List<BrandDeal> brandDeals) {
        this.brandDeals = brandDeals;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getEmpNums() {
        return empNums;
    }

    public void setEmpNums(String empNums) {
        this.empNums = empNums;
    }

    public String getShopNums() {
        return shopNums;
    }

    public void setShopNums(String shopNums) {
        this.shopNums = shopNums;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Boolean isExitsInquiry() {
        return isExitsInquiry;
    }

    public void setIsExitsInquiry(Boolean isExitsInquiry) {
        this.isExitsInquiry = isExitsInquiry;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getEmploeeNum() {
        return emploeeNum;
    }

    public void setEmploeeNum(String emploeeNum) {
        this.emploeeNum = emploeeNum;
    }

    public String getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(String moneyNum) {
        this.moneyNum = moneyNum;
    }

    public Short getJoinSource() {
        return joinSource;
    }

    public void setJoinSource(Short joinSource) {
        this.joinSource = joinSource;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }


    public Short getInvokeType() {
        return invokeType;
    }

    public void setInvokeType(Short invokeType) {
        this.invokeType = invokeType;
    }

    public boolean getFactoryAndZero() {
        return factoryAndZero;
    }

    public void setFactoryAndZero(boolean factoryAndZero) {
        this.factoryAndZero = factoryAndZero;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Boolean isViewAdd() {
        return isViewAdd;
    }

    public void setIsViewAdd(Boolean isViewAdd) {
        this.isViewAdd = isViewAdd;
    }
}
