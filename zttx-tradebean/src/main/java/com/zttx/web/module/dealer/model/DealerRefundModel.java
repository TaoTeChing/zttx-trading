package com.zttx.web.module.dealer.model;

import java.util.List;

import com.zttx.web.module.dealer.entity.DealerRefund;

/**
 * Created by 李星 on 2015/8/27.
 */
public class DealerRefundModel extends DealerRefund
{
    private String dealerName;
    
    private String brandName;
    
    private Short  refundType;
    
    private Long   beginTime;
    
    private Long   endTime;
    
    private String                    applyTimeStart;
    
    private String                    applyTimeEnd;;
    
    // 客户继而状态
    private String                    cusJoinState;
    
    private String                    mixRefundState;
    
    private List<DealerRefReplyModel> dealerRefReplies;
    
    public String getDealerName()
    {
        return dealerName;
    }
    
    public void setDealerName(String dealerName)
    {
        this.dealerName = dealerName;
    }
    
    public String getBrandName()
    {
        return brandName;
    }
    
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }
    
    public Short getRefundType()
    {
        return refundType;
    }
    
    public void setRefundType(Short refundType)
    {
        this.refundType = refundType;
    }
    
    public Long getBeginTime()
    {
        return beginTime;
    }
    
    public void setBeginTime(Long beginTime)
    {
        this.beginTime = beginTime;
    }
    
    public Long getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(Long endTime)
    {
        this.endTime = endTime;
    }
    
    public String getCusJoinState()
    {
        return cusJoinState;
    }
    
    public void setCusJoinState(String cusJoinState)
    {
        this.cusJoinState = cusJoinState;
    }
    
    public String getMixRefundState()
    {
        return mixRefundState;
    }
    
    public void setMixRefundState(String mixRefundState)
    {
        this.mixRefundState = mixRefundState;
    }
    
    public List<DealerRefReplyModel> getDealerRefReplies()
    {
        return dealerRefReplies;
    }
    
    public void setDealerRefReplies(List<DealerRefReplyModel> dealerRefReplies)
    {
        this.dealerRefReplies = dealerRefReplies;
    }
    
    public String getApplyTimeStart()
    {
        return applyTimeStart;
    }
    
    public void setApplyTimeStart(String applyTimeStart)
    {
        this.applyTimeStart = applyTimeStart;
    }
    
    public String getApplyTimeEnd()
    {
        return applyTimeEnd;
    }
    
    public void setApplyTimeEnd(String applyTimeEnd)
    {
        this.applyTimeEnd = applyTimeEnd;
    }
}
