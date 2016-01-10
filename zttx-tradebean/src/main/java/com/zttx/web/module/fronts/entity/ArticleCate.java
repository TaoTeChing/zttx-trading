/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Sets;
import com.zttx.sdk.core.GenericEntity;

/**
 * 网站资讯类别 实体对象
 * <p>File：ArticleCate.java</p>
 * <p>Title: ArticleCate</p>
 * <p>Description:ArticleCate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ArticleCate extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**类别名称*/
    @NotBlank(message = "类别名称不能为空")
    private java.lang.String     cateName;
    
    /**图标域名*/
    private java.lang.String     domainName;
    
    /**类别图标*/
    private java.lang.String     cateIcon;
    
    /**类别描述*/
    private java.lang.String     cateMark;
    
    /**上层编号*/
    private java.lang.String     parentId;
    
    /**排序编号*/
    @NotNull(message = "排序编号不能为空")
    private java.lang.Integer    cateOrder;
    
    /**类别层级*/
    private java.lang.Short      cateLevel;
    
    /**文章数量*/
    private java.lang.Integer    articleNum;
    
    /**模板文件*/
    private java.lang.String     moduleFile;
    
    /**静态文件*/
    private java.lang.String     htmlFile;
    
    /**建档时间*/
    private java.lang.Long       createTime;
    
    /**修改时间*/
    private java.lang.Long       updateTime;
    
    /**
     * 取子类别
     */
    private List<ArticleCate>    subList;
    
    private TreeSet<ArticleCate> children;
    
    public ArticleCate()
    {
    }
    
    /**
     * @param infoHot
     * @param string
     */
    public ArticleCate(String infoHot, String string)
    {
    }
    
    public java.lang.String getCateName()
    {
        return this.cateName;
    }
    
    public void setCateName(java.lang.String cateName)
    {
        this.cateName = cateName;
    }
    
    public java.lang.String getDomainName()
    {
        return this.domainName;
    }
    
    public void setDomainName(java.lang.String domainName)
    {
        this.domainName = domainName;
    }
    
    public java.lang.String getCateIcon()
    {
        return this.cateIcon;
    }
    
    public void setCateIcon(java.lang.String cateIcon)
    {
        this.cateIcon = cateIcon;
    }
    
    public java.lang.String getCateMark()
    {
        return this.cateMark;
    }
    
    public void setCateMark(java.lang.String cateMark)
    {
        this.cateMark = cateMark;
    }
    
    public java.lang.String getParentId()
    {
        return this.parentId;
    }
    
    public void setParentId(java.lang.String parentId)
    {
        this.parentId = parentId;
    }
    
    public java.lang.Integer getCateOrder()
    {
        return this.cateOrder;
    }
    
    public void setCateOrder(java.lang.Integer cateOrder)
    {
        this.cateOrder = cateOrder;
    }
    
    public java.lang.Short getCateLevel()
    {
        return this.cateLevel;
    }
    
    public void setCateLevel(java.lang.Short cateLevel)
    {
        this.cateLevel = cateLevel;
    }
    
    public java.lang.Integer getArticleNum()
    {
        return this.articleNum;
    }
    
    public void setArticleNum(java.lang.Integer articleNum)
    {
        this.articleNum = articleNum;
    }
    
    public java.lang.String getModuleFile()
    {
        return this.moduleFile;
    }
    
    public void setModuleFile(java.lang.String moduleFile)
    {
        this.moduleFile = moduleFile;
    }
    
    public java.lang.String getHtmlFile()
    {
        return this.htmlFile;
    }
    
    public void setHtmlFile(java.lang.String htmlFile)
    {
        this.htmlFile = htmlFile;
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
    
    public List<ArticleCate> getSubList()
    {
        return subList;
    }
    
    public void setSubList(List<ArticleCate> subList)
    {
        this.subList = subList;
    }
    
    public TreeSet<ArticleCate> getChildren()
    {
        if (null == this.children) this.children = Sets.newTreeSet(new ArticleCate.ArticleCateComparator());
        return children;
    }
    
    public ArticleCate setChildren(TreeSet<ArticleCate> children)
    {
        this.children = children;
        return this;
    }
    
    public static class ArticleCateComparator implements Comparator<ArticleCate>, Serializable
    {
        private static final long serialVersionUID = -8375103768210122501L;
        
        @Override
        public int compare(ArticleCate o1, ArticleCate o2)
        {
            int result = 0;
            result = o1.getCateLevel().compareTo(o2.getCateLevel());
            if (result == 0) result = o1.getCateOrder().compareTo(o2.getCateOrder());
            if (result == 0) result = o1.getCreateTime().compareTo(o2.getCreateTime());
            if (result == 0) result = o1.getRefrenceId().compareTo(o2.getRefrenceId());
            return result;
        }
    }
}
