/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 规则内容信息 实体对象
 * <p>File：RulesInfo.java</p>
 * <p>Title: RulesInfo</p>
 * <p>Description:RulesInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class RulesInfo extends GenericEntity
{
    private static final long serialVersionUID = -4061180463048128439L;
    
    /**类别编号*/
    @NotBlank(message = "类别编号不可为空！")
    private java.lang.String  cateId;
    
    /**规则主题*/
    @NotBlank(message = "规则主题不允许为空！")
    private java.lang.String  articleTitle;
    
    /**规则内容*/
    @NotBlank(message = "规则内容不允许为空！")
    private java.lang.String  articleText;
    
    /**规则图片域名*/
    private java.lang.String  domainName;
    
    /**规则图片*/
    private java.lang.String  articleImage;
    
    /**发布时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**查看次数*/
    private java.lang.Integer viewNum;
    
    private String            cateName;
    
    private List<RulesCate>   rulesCates;
    
    private String            orderByViewNum;
    
    private String            orderByCreateTime;
    
    public java.lang.String getCateId()
    {
        return this.cateId;
    }
    
    public void setCateId(java.lang.String cateId)
    {
        this.cateId = cateId;
    }
    
    public java.lang.String getArticleTitle()
    {
        return this.articleTitle;
    }
    
    public void setArticleTitle(java.lang.String articleTitle)
    {
        this.articleTitle = articleTitle;
    }
    
    public java.lang.String getArticleText()
    {
        return this.articleText;
    }
    
    public void setArticleText(java.lang.String articleText)
    {
        this.articleText = articleText;
    }
    
    public java.lang.String getDomainName()
    {
        return this.domainName;
    }
    
    public void setDomainName(java.lang.String domainName)
    {
        this.domainName = domainName;
    }
    
    public java.lang.String getArticleImage()
    {
        return this.articleImage;
    }
    
    public void setArticleImage(java.lang.String articleImage)
    {
        this.articleImage = articleImage;
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
    
    public java.lang.Integer getViewNum()
    {
        return this.viewNum;
    }
    
    public void setViewNum(java.lang.Integer viewNum)
    {
        this.viewNum = viewNum;
    }
    
    public String getCateName()
    {
        return cateName;
    }
    
    public void setCateName(String cateName)
    {
        this.cateName = cateName;
    }
    
    public List<RulesCate> getRulesCates()
    {
        return rulesCates;
    }
    
    public void setRulesCates(List<RulesCate> rulesCates)
    {
        this.rulesCates = rulesCates;
    }
    
    public String getOrderByViewNum()
    {
        return orderByViewNum;
    }
    
    public RulesInfo setOrderByViewNum(String orderByViewNum)
    {
        this.orderByViewNum = orderByViewNum;
        return this;
    }
    
    public String getOrderByCreateTime()
    {
        return orderByCreateTime;
    }
    
    public RulesInfo setOrderByCreateTime(String orderByCreateTime)
    {
        this.orderByCreateTime = orderByCreateTime;
        return this;
    }
}
