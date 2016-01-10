/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商消息管理 实体对象
 * <p>File：BrandMessage.java</p>
 * <p>Title: BrandMessage</p>
 * <p>Description:BrandMessage</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandMessage extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**品牌商编号*/
    private java.lang.String  brandId;
    
    /**经销商编号*/
    private java.lang.String  dealerId;
    
    /**订单编号*/
    private java.lang.String  orderId;
    
    /**消息类型*/
    private java.lang.Short   msgCate;
    
    /**消息主题*/
    private java.lang.String  msgTitle;
    
    /**消息内容*/
    private java.lang.String  msgText;
    
    /**留言-用户性别*/
    private java.lang.Integer areaNo;
    
    /**留言-用户姓名*/
    private java.lang.String  userName;
    
    /**留言-手机号*/
    private java.lang.String  userMobile;
    
    /**留言-联系方式 QQ/Email*/
    private java.lang.String  userContact;
    
    /**留言-区域*/
    private java.lang.Integer userGender;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**是否拒绝回复*/
    private java.lang.Boolean refuseReply;
    
    /**回复的消息编号*/
    private java.lang.String  replyId;
    
    /**回复的时间*/
    private java.lang.Long    replyTime;
    
    /**回复的内容*/
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
    
    // 开始发送时间
    @DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
    private Date              sendBeginTime;
    
    private Long              sendBeginLongTime;    // 数据库查询条件
    
    // 结束发送时间
    @DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
    private Date              sendEndTime;
    
    private Long              sendEndLongTime;      // 数据库查询条件
    
    // 显示类型 0:接收的消息 1:发送的消息
    private Short             listType;
    // 消息发送人
    private String senderName;
    
    public Long getSendBeginLongTime()
    {
        return sendBeginLongTime;
    }
    
    public void setSendBeginLongTime(Long sendBeginLongTime)
    {
        this.sendBeginLongTime = sendBeginLongTime;
    }
    
    public Long getSendEndLongTime()
    {
        return sendEndLongTime;
    }
    
    public void setSendEndLongTime(Long sendEndLongTime)
    {
        this.sendEndLongTime = sendEndLongTime;
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
    
    public String getSendBeginTimeString()
    {
        if (null == this.sendBeginTime) return "";
        return CalendarUtils.getDate(this.sendBeginTime, ApplicationConst.DATE_FORMAT_YMD);
    }
    
    public String getSendEndTimeString()
    {
        if (null == this.sendEndTime) return "";
        return CalendarUtils.getDate(this.sendEndTime, ApplicationConst.DATE_FORMAT_YMD);
    }
    
    public Short getSearchType()
    {
        if (searchType == null) searchType = (short) 0;
        return searchType;
    }
    
    public void setSearchType(Short searchType)
    {
        this.searchType = searchType;
    }
    
    public String getSearchWord()
    {
        return searchWord;
    }
    
    public void setSearchWord(String searchWord)
    {
        this.searchWord = searchWord;
    }
    
    public String getSelectMsgIds()
    {
        return selectMsgIds;
    }
    
    public void setSelectMsgIds(String selectMsgIds)
    {
        this.selectMsgIds = selectMsgIds;
    }
    
    public Short getMsgStatus()
    {
        if (msgStatus == null) msgStatus = (short) 0;
        return msgStatus;
    }
    
    public void setMsgStatus(Short msgStatus)
    {
        this.msgStatus = msgStatus;
    }
    
    public Date getSendBeginTime()
    {
        return sendBeginTime;
    }
    
    public void setSendBeginTime(Date sendBeginTime)
    {
        this.sendBeginTime = sendBeginTime;
    }
    
    public Date getSendEndTime()
    {
        return sendEndTime;
    }
    
    public void setSendEndTime(Date sendEndTime)
    {
        this.sendEndTime = sendEndTime;
    }
    
    public Short getListType()
    {
        if (listType == null) listType = (short) 0;
        return listType;
    }
    
    public void setListType(Short listType)
    {
        this.listType = listType;
    }
    
    public java.lang.String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }
    
    public java.lang.String getDealerId()
    {
        return this.dealerId;
    }
    
    public void setDealerId(java.lang.String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public java.lang.String getOrderId()
    {
        return this.orderId;
    }
    
    public void setOrderId(java.lang.String orderId)
    {
        this.orderId = orderId;
    }
    
    public java.lang.Short getMsgCate()
    {
        return this.msgCate;
    }
    
    public void setMsgCate(java.lang.Short msgCate)
    {
        this.msgCate = msgCate;
    }
    
    public java.lang.String getMsgTitle()
    {
        return this.msgTitle;
    }
    
    public void setMsgTitle(java.lang.String msgTitle)
    {
        this.msgTitle = msgTitle;
    }
    
    public java.lang.String getMsgText()
    {
        return this.msgText;
    }
    
    public void setMsgText(java.lang.String msgText)
    {
        this.msgText = msgText;
    }
    
    public java.lang.Integer getAreaNo()
    {
        return this.areaNo;
    }
    
    public void setAreaNo(java.lang.Integer areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public java.lang.String getUserName()
    {
        return this.userName;
    }
    
    public void setUserName(java.lang.String userName)
    {
        this.userName = userName;
    }
    
    public java.lang.String getUserMobile()
    {
        return this.userMobile;
    }
    
    public void setUserMobile(java.lang.String userMobile)
    {
        this.userMobile = userMobile;
    }
    
    public java.lang.String getUserContact()
    {
        return this.userContact;
    }
    
    public void setUserContact(java.lang.String userContact)
    {
        this.userContact = userContact;
    }
    
    public java.lang.Integer getUserGender()
    {
        return this.userGender;
    }
    
    public void setUserGender(java.lang.Integer userGender)
    {
        this.userGender = userGender;
    }
    
    public java.lang.Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(java.lang.Long createTime)
    {
        this.createTime = createTime;
    }
    
    public java.lang.Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(java.lang.Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public java.lang.Boolean getRefuseReply()
    {
        return this.refuseReply;
    }
    
    public void setRefuseReply(java.lang.Boolean refuseReply)
    {
        this.refuseReply = refuseReply;
    }
    
    public java.lang.String getReplyId()
    {
        return this.replyId;
    }
    
    public void setReplyId(java.lang.String replyId)
    {
        this.replyId = replyId;
    }
    
    public java.lang.Long getReplyTime()
    {
        return this.replyTime;
    }
    
    public void setReplyTime(java.lang.Long replyTime)
    {
        this.replyTime = replyTime;
    }
    
    public java.lang.String getReplyText()
    {
        return this.replyText;
    }
    
    public void setReplyText(java.lang.String replyText)
    {
        this.replyText = replyText;
    }
    
    public java.lang.String getUserId()
    {
        return this.userId;
    }
    
    public void setUserId(java.lang.String userId)
    {
        this.userId = userId;
    }
    
    public String getSenderName()
    {
        return senderName;
    }
    
    public void setSenderName(String senderName)
    {
        this.senderName = senderName;
    }
}
