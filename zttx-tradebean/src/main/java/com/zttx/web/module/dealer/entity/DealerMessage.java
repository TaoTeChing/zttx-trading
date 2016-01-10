/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商消息管理 实体对象
 * <p>File：DealerMessage.java</p>
 * <p>Title: DealerMessage</p>
 * <p>Description:DealerMessage</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerMessage extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**经销商编号*/
    private java.lang.String  dealerId;
    
    /**发送人编号(站内消息)*/
    private java.lang.String  brandId;
    
    /**订单编号*/
    private java.lang.String  orderId;
    
    /**消息类型*/
    private java.lang.Short   msgCate;
    
    /**消息标题*/
    private java.lang.String  msgTitle;
    
    /**消息内容*/
    private java.lang.String  msgText;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**拒绝回复（表示该消息是否不能回复）0：可以回复，1：不能回复*/
    private java.lang.Boolean refuseReply;
    
    /**回复的消息编号*/
    private java.lang.String  replyId;
    
    /**回复时间*/
    private java.lang.Long    replyTime;
    
    /**回复内容*/
    private java.lang.String  replyText;
    
    /**回复人编号*/
    private java.lang.String  userId;
    
    // 消息类型
    private Short             searchType;
    
    // 关键字
    private String            searchWord;
    
    // 选择的消息ID
    private String            selectMsgIds;
    
    // 消息状态 0：全部，1：已读，2：未读
    private Short             msgStatus;
    
    // 品牌商名称
    private String            brandName;
    
    // 已读标志
    private Boolean           readed;// true :已读
    
    // private List<DealerRead> dealerReadList;
    public String getDealerId()
    {
        return dealerId;
    }
    
    public DealerMessage setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
        return this;
    }
    
    public String getBrandId()
    {
        return brandId;
    }
    
    public DealerMessage setBrandId(String brandId)
    {
        this.brandId = brandId;
        return this;
    }
    
    public String getOrderId()
    {
        return orderId;
    }
    
    public DealerMessage setOrderId(String orderId)
    {
        this.orderId = orderId;
        return this;
    }
    
    public Short getMsgCate()
    {
        return msgCate;
    }
    
    public DealerMessage setMsgCate(Short msgCate)
    {
        this.msgCate = msgCate;
        return this;
    }
    
    public String getMsgTitle()
    {
        return msgTitle;
    }
    
    public DealerMessage setMsgTitle(String msgTitle)
    {
        this.msgTitle = msgTitle;
        return this;
    }
    
    public String getMsgText()
    {
        return msgText;
    }
    
    public DealerMessage setMsgText(String msgText)
    {
        this.msgText = msgText;
        return this;
    }
    
    public Long getCreateTime()
    {
        return createTime;
    }
    
    public DealerMessage setCreateTime(Long createTime)
    {
        this.createTime = createTime;
        return this;
    }
    
    public Long getUpdateTime()
    {
        return updateTime;
    }
    
    public DealerMessage setUpdateTime(Long updateTime)
    {
        this.updateTime = updateTime;
        return this;
    }
    
    public Boolean isRefuseReply()
    {
        return refuseReply;
    }
    
    public DealerMessage setRefuseReply(Boolean refuseReply)
    {
        this.refuseReply = refuseReply;
        return this;
    }
    
    public String getReplyId()
    {
        return replyId;
    }
    
    public DealerMessage setReplyId(String replyId)
    {
        this.replyId = replyId;
        return this;
    }
    
    public Long getReplyTime()
    {
        return replyTime;
    }
    
    public DealerMessage setReplyTime(Long replyTime)
    {
        this.replyTime = replyTime;
        return this;
    }
    
    public String getReplyText()
    {
        return replyText;
    }
    
    public DealerMessage setReplyText(String replyText)
    {
        this.replyText = replyText;
        return this;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public DealerMessage setUserId(String userId)
    {
        this.userId = userId;
        return this;
    }
    
    public Short getSearchType()
    {
        return searchType;
    }
    
    public DealerMessage setSearchType(Short searchType)
    {
        this.searchType = searchType;
        return this;
    }
    
    public String getSearchWord()
    {
        return searchWord;
    }
    
    public DealerMessage setSearchWord(String searchWord)
    {
        this.searchWord = searchWord;
        return this;
    }
    
    public String getSelectMsgIds()
    {
        return selectMsgIds;
    }
    
    public DealerMessage setSelectMsgIds(String selectMsgIds)
    {
        this.selectMsgIds = selectMsgIds;
        return this;
    }
    
    public Short getMsgStatus()
    {
        return msgStatus;
    }
    
    public DealerMessage setMsgStatus(Short msgStatus)
    {
        this.msgStatus = msgStatus;
        return this;
    }
    
    public List<String> getMsgIdList()
    {
        List<String> list = Lists.newArrayList();
        if (StringUtils.isNotBlank(selectMsgIds))
        {
            String[] msgIdArray = selectMsgIds.split(",");
            for (String element : msgIdArray)
                list.add(element);
        }
        return list;
    }
    
    public String getBrandName()
    {
        return brandName;
    }
    
    public DealerMessage setBrandName(String brandName)
    {
        this.brandName = brandName;
        return this;
    }

    public Boolean getReaded() {
        return readed;
    }

    public DealerMessage setReaded(Boolean readed) {
        this.readed = readed;
        return this;
    }

    public String getMsgTextHtml()
    {
        return HtmlUtils.htmlUnescape(this.msgText);
    }
}
