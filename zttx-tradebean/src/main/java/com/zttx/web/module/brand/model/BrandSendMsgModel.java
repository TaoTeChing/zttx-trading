/*
 *
 *  * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 *  * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.zttx.web.module.brand.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.zttx.web.consts.CommonConstant;

public class BrandSendMsgModel
{
    // 消息标题
    @NotBlank(message = "消息标题不能为空")
    private String   title;
    
    // 消息内容
    @NotBlank(message = "消息内容不能为空")
    private String   content;
    
    // 短信内容
    private String   mobContent;
    
    // 发送对象(单个)
    private String   dealerId;
    
    // 发送对象(多个)
    @NotEmpty(message = "发送对象不能为空")
    private String[] dealerIds;
    
    // 手机短信 false:不发送 true:发送
    private Boolean  sendMobNote = false;
    
    // 站内短信 false:不发送 true:发送
    private Boolean  sendNetNote = true;
    
    // 品牌商ID
    private String   brandId;
    
    private Boolean  docOpen;            // true:所有 false:指定
    
    @Length(max = CommonConstant.Message.MAX_NET_TITLE, message = "消息标题的长度必须小于{max}个字")
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    @Length(max = CommonConstant.Message.MAX_NET_CONTENT, message = "消息内容的长度必须小于{max}个字")
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    @Length(max = CommonConstant.Message.MAX_MOB_CONTENT, message = "短信内容的长度必须小于{max}个字")
    public String getMobContent()
    {
        return mobContent;
    }
    
    public void setMobContent(String mobContent)
    {
        this.mobContent = mobContent;
    }
    
    public String[] getDealerIds()
    {
        return dealerIds;
    }
    
    public void setDealerIds(String[] dealerIds)
    {
        this.dealerIds = dealerIds;
    }
    
    public String getBrandId()
    {
        return brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public String getDealerId()
    {
        return dealerId;
    }
    
    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public Boolean getSendMobNote()
    {
        return sendMobNote;
    }
    
    public void setSendMobNote(Boolean sendMobNote)
    {
        this.sendMobNote = sendMobNote;
    }
    
    public Boolean getSendNetNote()
    {
        return sendNetNote;
    }
    
    public void setSendNetNote(Boolean sendNetNote)
    {
        this.sendNetNote = sendNetNote;
    }
    
    public Boolean getDocOpen()
    {
        return docOpen;
    }
    
    public BrandSendMsgModel setDocOpen(Boolean docOpen)
    {
        this.docOpen = docOpen;
        return this;
    }
}
