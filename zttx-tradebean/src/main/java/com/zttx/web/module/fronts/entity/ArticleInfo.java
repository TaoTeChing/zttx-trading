/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 资讯文章信息 实体对象
 * <p>File：ArticleInfo.java</p>
 * <p>Title: ArticleInfo</p>
 * <p>Description:ArticleInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ArticleInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**类别编号*/
    @NotBlank(message = "类别编号不允许为空！")
    private java.lang.String  cateId;
    
    private String            option;
    
    /**文章主题*/
    @NotBlank(message = "文章主题不允许为空！")
    private java.lang.String  articleTitle;
    
    private java.lang.String  cateName;
    
    /**文章内容*/
    private java.lang.String  articleText;
    
    /**图片域名*/
    private java.lang.String  domainName;
    
    /**图片地址*/
    private java.lang.String  articleImage;
    
    /**文章来源*/
    private java.lang.String  articleSource;
    
    /**文章作者*/
    private java.lang.String  articleAuthor;
    
    /**查看次数*/
    private java.lang.Integer viewNum;
    
    /**分享次数*/
    private java.lang.Integer shareNum;
    
    /**是否置顶*/
    private java.lang.Boolean isTop            = Boolean.FALSE;
    
    /**是否头条*/
    private java.lang.Boolean isHead           = Boolean.FALSE;
    
    /**是否推荐*/
    private java.lang.Boolean isComment        = Boolean.FALSE;
    
    /**是否热门*/
    private java.lang.Boolean isHot            = Boolean.FALSE;
    
    /**评论次数*/
    private java.lang.Integer commentNum;
    
    /**收藏次数*/
    private java.lang.Integer collectNum;
    
    /**发布时间*/
    private java.lang.Long    createTime;
    
    private String            createTimeStr;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    private String            keywords;                        // 关键字
    
    /**
     * 类别信息
     */
    private List<ArticleCate> articleCates;
    
    public String getOption()
    {
        return option;
    }
    
    public void setOption(String option)
    {
        this.option = option;
    }
    
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
    
    public java.lang.String getArticleSource()
    {
        return this.articleSource;
    }
    
    public void setArticleSource(java.lang.String articleSource)
    {
        this.articleSource = articleSource;
    }
    
    public java.lang.String getArticleAuthor()
    {
        return this.articleAuthor;
    }
    
    public void setArticleAuthor(java.lang.String articleAuthor)
    {
        this.articleAuthor = articleAuthor;
    }
    
    public java.lang.Integer getViewNum()
    {
        return this.viewNum;
    }
    
    public void setViewNum(java.lang.Integer viewNum)
    {
        this.viewNum = viewNum;
    }
    
    public java.lang.Integer getShareNum()
    {
        return this.shareNum;
    }
    
    public void setShareNum(java.lang.Integer shareNum)
    {
        this.shareNum = shareNum;
    }
    
    public java.lang.Boolean getIsTop()
    {
        return this.isTop;
    }
    
    public void setIsTop(java.lang.Boolean isTop)
    {
        this.isTop = isTop;
    }
    
    public java.lang.Boolean getIsHead()
    {
        return this.isHead;
    }
    
    public void setIsHead(java.lang.Boolean isHead)
    {
        this.isHead = isHead;
    }
    
    public java.lang.Boolean getIsComment()
    {
        return this.isComment;
    }
    
    public void setIsComment(java.lang.Boolean isComment)
    {
        this.isComment = isComment;
    }
    
    public java.lang.Boolean getIsHot()
    {
        return this.isHot;
    }
    
    public void setIsHot(java.lang.Boolean isHot)
    {
        this.isHot = isHot;
    }
    
    public java.lang.Integer getCommentNum()
    {
        return this.commentNum;
    }
    
    public void setCommentNum(java.lang.Integer commentNum)
    {
        this.commentNum = commentNum;
    }
    
    public java.lang.Integer getCollectNum()
    {
        return this.collectNum;
    }
    
    public void setCollectNum(java.lang.Integer collectNum)
    {
        this.collectNum = collectNum;
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
    
    public List<ArticleCate> getArticleCates()
    {
        return articleCates;
    }
    
    public void setArticleCates(List<ArticleCate> articleCates)
    {
        this.articleCates = articleCates;
    }
    
    public java.lang.String getCateName()
    {
        return cateName;
    }
    
    public void setCateName(java.lang.String cateName)
    {
        this.cateName = cateName;
    }
    
    public String getCreateTimeStr()
    {
        return createTimeStr;
    }
    
    public void setCreateTimeStr(String createTimeStr)
    {
        this.createTimeStr = createTimeStr;
    }
    
    public String getKeywords()
    {
        return keywords;
    }
    
    public ArticleInfo setKeywords(String keywords)
    {
        this.keywords = keywords;
        return this;
    }
    
    /**
     * 排序条件 "desc"：降序 "asc"：升序 null,""：不参与排序
     */
    public static class OrderCriteria
    {
        private String createTime; // 发布时间
        
        private String viewNum;   // 查看次数
        
        private String shareNum;  // 分享次数
        
        private String commentNum; // 评论次数
        
        private String collectNum; // 收藏次数
        
        public String getCreateTime()
        {
            return createTime;
        }
        
        public void setCreateTime(String createTime)
        {
            this.createTime = createTime;
        }
        
        public String getViewNum()
        {
            return viewNum;
        }
        
        public void setViewNum(String viewNum)
        {
            this.viewNum = viewNum;
        }
        
        public String getShareNum()
        {
            return shareNum;
        }
        
        public void setShareNum(String shareNum)
        {
            this.shareNum = shareNum;
        }
        
        public String getCommentNum()
        {
            return commentNum;
        }
        
        public void setCommentNum(String commentNum)
        {
            this.commentNum = commentNum;
        }
        
        public String getCollectNum()
        {
            return collectNum;
        }
        
        public void setCollectNum(String collectNum)
        {
            this.collectNum = collectNum;
        }
    }
}
