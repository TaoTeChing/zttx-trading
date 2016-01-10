package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandVisited;

import java.beans.Transient;

/**
 * <p>File:BrandVisitedModel</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/8/12 16:20</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class BrandVisitedModel extends BrandVisited {
    // 开始时间
    private String startTimeStr;

    // 结束时间
    private String endTimeStr;

    // 企业规模
    private String emploeeNum;

    // 年营业额
    private String moneyNum;
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
