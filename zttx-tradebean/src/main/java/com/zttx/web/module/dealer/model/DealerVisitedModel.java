package com.zttx.web.module.dealer.model;

import com.zttx.web.module.brand.entity.BrandDeal;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerVisited;

import java.beans.Transient;
import java.util.List;

/**
 * <p>File:DealerVisitedModel</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/8/12 17:18</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class DealerVisitedModel extends DealerVisited {

    private BrandInfo brandInfo;

    private BrandesInfo brandesInfo;

    private DealerInfo dealerInfo;

    private String     brandDealStr;

    private List<BrandDeal> brandDeals;

    // 开始时间
    private String            startTimeStr;

    // 结束时间
    private String            endTimeStr;

    // 企业规模
    private String            emploeeNum;

    // 年营业额
    private String            moneyNum;
    @Transient
    public BrandInfo getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(BrandInfo brandInfo) {
        this.brandInfo = brandInfo;
    }
    @Transient
    public BrandesInfo getBrandesInfo() {
        return brandesInfo;
    }

    public void setBrandesInfo(BrandesInfo brandesInfo) {
        this.brandesInfo = brandesInfo;
    }
    @Transient
    public DealerInfo getDealerInfo() {
        return dealerInfo;
    }

    public void setDealerInfo(DealerInfo dealerInfo) {
        this.dealerInfo = dealerInfo;
    }
    @Transient
    public String getBrandDealStr() {
        return brandDealStr;
    }

    public void setBrandDealStr(String brandDealStr) {
        this.brandDealStr = brandDealStr;
    }
    @Transient
    public List<BrandDeal> getBrandDeals() {
        return brandDeals;
    }

    public void setBrandDeals(List<BrandDeal> brandDeals) {
        this.brandDeals = brandDeals;
    }
    @Transient
    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }
    @Transient
    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
    @Transient
    public String getEmploeeNum() {
        return emploeeNum;
    }

    public void setEmploeeNum(String emploeeNum) {
        this.emploeeNum = emploeeNum;
    }
    @Transient
    public String getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(String moneyNum) {
        this.moneyNum = moneyNum;
    }
}
