/*
 * @(#)BrandJoinFilter 2014/4/2 13:07
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.DealerConstant;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>File：BrandJoinFilter</p>
 * <p>Title: </p>
 * <p>Description: 经销商筛选过滤器</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/2 13:07</p>
 * <p>Company: 8637.com</p>
 * @version 1.0
 */
public class BrandJoinFilter
{
    private String     dealerName;                                  // 经销商名称
    
    private String     bid;                                         // 品牌ID
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date       startTime;                                   // 加盟起始时间
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date       endTime;                                     // 加盟结束时间

    private Long       startTimeLong;                                   // 加盟起始时间

    private Long       endTimeLong;                                     // 加盟结束时间

    private String     dealerLevel;                                 // 经销商等级
    
    private Integer    areaNo;                                      // 经销商区域
    
    private String     docId;                                       // 资料ID
    
    private Short      state = DealerConstant.DealerJoin.COOPERATED; // 合作状态 1. 合作中 2.终止
    
    private BigDecimal min;                                         // 进货总额下限
    
    private BigDecimal max;                                         // 进货总额上限
    
    private String     province;                                    // 省份
    
    // 1、经绡商申请表DealerJoin　2、品牌商资料公开BrandDocopen
    private String     addLevelType;                                // 添加经绡商的类型
    
    private Boolean    isExitsInquiry;                              // 是否存在询价单
    
    public String getProvince()
    {
        return province;
    }
    
    public void setProvince(String province)
    {
        this.province = province;
    }
    
    public String getAddLevelType()
    {
        return addLevelType;
    }
    
    public void setAddLevelType(String addLevelType)
    {
        this.addLevelType = addLevelType;
    }
    
    public String getDealerName()
    {
        return dealerName;
    }
    
    public void setDealerName(String dealerName)
    {
        this.dealerName = dealerName;
    }
    
    public String getBid()
    {
        return bid;
    }
    
    public void setBid(String bid)
    {
        this.bid = bid;
    }
    
    public Date getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }
    
    public Date getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
    
    public String getDealerLevel()
    {
        return dealerLevel;
    }
    
    public void setDealerLevel(String dealerLevel)
    {
        this.dealerLevel = dealerLevel;
    }
    
    public Integer getAreaNo()
    {
        return areaNo;
    }
    
    public void setAreaNo(Integer areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public String getDocId()
    {
        return docId;
    }
    
    public void setDocId(String docId)
    {
        this.docId = docId;
    }
    
    public Short getState()
    {
        return state;
    }
    
    public void setState(Short state)
    {
        this.state = state;
    }
    
    public BigDecimal getMin()
    {
        return min;
    }
    
    public void setMin(BigDecimal min)
    {
        this.min = min;
    }
    
    public BigDecimal getMax()
    {
        return max;
    }
    
    public void setMax(BigDecimal max)
    {
        this.max = max;
    }
    
    public String getStartTimeString()
    {
        return this.startTime == null ? "" : new DateTime(this.startTime).toString(ApplicationConst.DATE_FORMAT_YMD);
    }
    
    public String getEndTimeString()
    {
        return this.endTime == null ? "" : new DateTime(this.endTime).toString(ApplicationConst.DATE_FORMAT_YMD);
    }
    
    public Boolean getIsExitsInquiry()
    {
        return isExitsInquiry;
    }
    
    public void setIsExitsInquiry(Boolean isExitsInquiry)
    {
        this.isExitsInquiry = isExitsInquiry;
    }

    public Long getStartTimeLong() {
        return this.startTime == null ? 0 : startTime.getTime();
    }

    public void setStartTimeLong(Long startTimeLong) {
        this.startTimeLong = startTimeLong;
    }

    public Long getEndTimeLong() {
        return this.endTime == null ? 0 : endTime.getTime()+24*60*60*1000;
    }

    public void setEndTimeLong(Long endTimeLong) {
        this.endTimeLong = endTimeLong;
    }
}
