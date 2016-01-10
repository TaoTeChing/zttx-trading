package com.zttx.web.module.common.model;

/**
 * <p>File:JoinModel</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/9/25 14:34</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class JoinModel {

    // 经销商ID
    private String dealerId;

    // 品牌商ID
    private String brandId;

    // 申请人名称
    private String senderName;

    // 手机号
    private String phone;

    // 内容
    private String content;

    // 站内消息类型
    private String netMsgCate;

    public JoinModel()
    {
        super();
    }

    /**
     * @param dealerId
     * @param brandId
     * @param applicantName
     * @param phone
     * @param content
     * @param msgTitle
     * @author 周光暖
     */
    public JoinModel(String dealerId, String brandId, String senderName, String phone, String content, String netMsgCate)
    {
        super();
        this.dealerId = dealerId;
        this.brandId = brandId;
        this.senderName = senderName;
        this.phone = phone;
        this.content = content;
        this.netMsgCate = netMsgCate;
    }

    public String getFormatContent()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(netMsgCate + "：").append(senderName).append("<br/>");
        sb.append("手机号：").append(phone).append("<br/>");
        sb.append("内  容：").append(content).append("<br/>");
        return sb.toString();
    }

    public String getFormatTitle()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(netMsgCate + "-").append(senderName);
        return sb.toString();
    }

    public String getDealerId()
    {
        return dealerId;
    }

    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }

    public String getSenderName()
    {
        return senderName;
    }

    public void setSenderName(String senderName)
    {
        this.senderName = senderName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getBrandId()
    {
        return brandId;
    }

    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }

    public String getNetMsgCate()
    {
        return netMsgCate;
    }

    public void setNetMsgCate(String netMsgCate)
    {
        this.netMsgCate = netMsgCate;
    }
}
