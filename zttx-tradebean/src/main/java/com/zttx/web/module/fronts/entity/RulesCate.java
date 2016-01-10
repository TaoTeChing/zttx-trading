/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Sets;
import com.zttx.sdk.core.GenericEntity;

/**
 * 网站规则类别 实体对象
 * <p>File：RulesCate.java</p>
 * <p>Title: RulesCate</p>
 * <p>Description:RulesCate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class RulesCate extends GenericEntity
{
    private static final long  serialVersionUID = -5940205272269706011L;
    
    /**类别名称*/
    @Length(max = 16, message = "类别名称长度不能超过16位！")
    @NotBlank(message = "类别名称不能为空！")
    private java.lang.String   cateName;
    
    /**类别描述*/
    @Length(max = 128, message = "类别描述长度不能超过128！")
    private java.lang.String   cateMark;
    
    /**规则分类类型1：文章，2：列表*/
    @Max(value = 2, message = "规则分类类型不存在！")
    @Min(value = 1, message = "规则分类类型不存在！")
    private java.lang.Short    cateType;
    
    /**上层编号*/
    @NotNull(message = "父类别不能为空！")
    private java.lang.String   parentId;
    
    /**规则内容*/
    private java.lang.String   cateText;
    
    /**排序编号*/
    @NotNull(message = "排序编号不能为空！")
    private java.lang.Integer  cateOrder;
    
    /**类别层级*/
    private java.lang.Short    cateLevel;
    
    /**文章数量*/
    private java.lang.Integer  articleNum;
    
    /**建档时间*/
    private java.lang.Long     createTime;
    
    private List<RulesCate>    subList;
    
    private TreeSet<RulesCate> children;
    
    public java.lang.String getCateName()
    {
        return this.cateName;
    }
    
    public void setCateName(java.lang.String cateName)
    {
        this.cateName = cateName;
    }
    
    public java.lang.String getCateMark()
    {
        return this.cateMark;
    }
    
    public void setCateMark(java.lang.String cateMark)
    {
        this.cateMark = cateMark;
    }
    
    public java.lang.Short getCateType()
    {
        return this.cateType;
    }
    
    public void setCateType(java.lang.Short cateType)
    {
        this.cateType = cateType;
    }
    
    public java.lang.String getParentId()
    {
        return this.parentId;
    }
    
    public void setParentId(java.lang.String parentId)
    {
        this.parentId = parentId;
    }
    
    public java.lang.String getCateText()
    {
        return this.cateText;
    }
    
    public void setCateText(java.lang.String cateText)
    {
        this.cateText = cateText;
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
    
    public java.lang.Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(java.lang.Long createTime)
    {
        this.createTime = createTime;
    }
    
    public List<RulesCate> getSubList()
    {
        return subList;
    }
    
    public void setSubList(List<RulesCate> subList)
    {
        this.subList = subList;
    }
    
    public TreeSet<RulesCate> getChildren()
    {
        if (null == this.children) this.children = Sets.newTreeSet(new RulesCate.RulesCateComparator());
        return children;
    }
    
    public void setChildren(TreeSet<RulesCate> children)
    {
        this.children = children;
    }
    
    public static class RulesCateComparator implements Comparator<RulesCate>, Serializable
    {
        private static final long serialVersionUID = -643073605101232538L;
        
        @Override
        public int compare(RulesCate o1, RulesCate o2)
        {
            int result = 0;
            result = o1.getCateLevel().compareTo(o2.getCateLevel());
            if (result == 0) result = o1.getCateOrder().compareTo(o2.getCateOrder());
            if (result == 0) result = o1.getCreateTime().compareTo(o2.getCreateTime());
            if (result == 0) result = o1.getArticleNum().compareTo(o2.getArticleNum());
            if (result == 0) result = o1.getRefrenceId().compareTo(o2.getRefrenceId());
            return result;
        }
    }
}
