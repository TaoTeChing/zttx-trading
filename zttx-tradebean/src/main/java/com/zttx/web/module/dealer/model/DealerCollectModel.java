package com.zttx.web.module.dealer.model;

import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.module.brand.entity.BrandDeal;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.dealer.entity.DealerCollect;
import com.zttx.web.module.dealer.entity.DealerInfo;

import java.util.List;

/**
 * <p>File:DealerCollectModel</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/8/13 9:07</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class DealerCollectModel extends DealerCollect {


    private UserInfo      userInfo;
   
    private BrandInfo brandInfo;

   
    private DealerInfo dealerInfo;

   
    private BrandesInfo brandesInfo;

   
    private String    brandDealStr;

   
    private List<BrandDeal> brandDeals;

    // 开始时间
   
    private String            startTimeStr;

    // 结束时间
   
    private String            endTimeStr;

    // 开始时间

    private Long            startTimeLong;

    // 结束时间

    private Long            endTimeLong;

    // 企业规模
   
    private String            emploeeNum;

    // 年营业额
   
    private String            moneyNum;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public BrandInfo getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(BrandInfo brandInfo) {
        this.brandInfo = brandInfo;
    }

    public DealerInfo getDealerInfo() {
        return dealerInfo;
    }

    public void setDealerInfo(DealerInfo dealerInfo) {
        this.dealerInfo = dealerInfo;
    }

    public BrandesInfo getBrandesInfo() {
        return brandesInfo;
    }

    public void setBrandesInfo(BrandesInfo brandesInfo) {
        this.brandesInfo = brandesInfo;
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

    public Long getStartTimeLong() {
        if(this.startTimeStr!=null){
            return CalendarUtils.getDateTimeFromString(startTimeStr,"yyyy-MM-dd").toDate().getTime();
        }
        return startTimeLong;
    }

    public void setStartTimeLong(Long startTimeLong) {
        this.startTimeLong = startTimeLong;
    }

    public Long getEndTimeLong() {
        if(this.endTimeStr!=null){
            return CalendarUtils.addDayToDate(CalendarUtils.getDateTimeFromString(endTimeStr, "yyyy-MM-dd").toDate(),1).getTime();
        }
        return endTimeLong;
    }

    public void setEndTimeLong(Long endTimeLong) {
        this.endTimeLong = endTimeLong;
    }
}
