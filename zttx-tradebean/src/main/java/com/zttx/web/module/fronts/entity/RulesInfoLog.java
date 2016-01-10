/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 规则内容历史记录 实体对象
 * <p>File：RulesInfoLog.java</p>
 * <p>Title: RulesInfoLog</p>
 * <p>Description:RulesInfoLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class RulesInfoLog extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**规则编号*/
    private java.lang.String  rulesId;
    
    /**文章主题*/
    private java.lang.String  articleTitle;
    
    /**文章内容*/
    private java.lang.String  articleText;
    
    /**文章图片域名*/
    private java.lang.String  domainName;
    
    /**文章图片*/
    private java.lang.String  articleImage;
    
    /**发布时间*/
    private java.lang.Long    createTime;
    
    /**查看次数*/
    private java.lang.Integer viewNum;
    
    public java.lang.String getRulesId()
    {
        return this.rulesId;
    }
    
    public void setRulesId(java.lang.String rulesId)
    {
        this.rulesId = rulesId;
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
    
    public java.lang.Integer getViewNum()
    {
        return this.viewNum;
    }
    
    public void setViewNum(java.lang.Integer viewNum)
    {
        this.viewNum = viewNum;
    }
}
