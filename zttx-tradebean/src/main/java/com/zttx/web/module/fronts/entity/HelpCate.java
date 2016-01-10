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
 * 帮助分类 实体对象
 * <p>File：HelpCate.java</p>
 * <p>Title: HelpCate</p>
 * <p>Description:HelpCate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class HelpCate extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**分类名称*/
    @NotBlank(message = "分类名称不能为空")
    private java.lang.String  cateName;
    
    /**上级分类*/
    private java.lang.String  parentId;
    
    /**帮助分类*/
    private java.lang.Integer cateNo           = 0;
    
    /**层级*/
    private java.lang.Integer helpLevel;
    
    /**帮助分类描述*/
    private java.lang.String  description;
    
    /**显示类型2：文章显示1：列表显示*/
    @NotNull(message = "显示类型不能为空")
    private java.lang.Short   showType;
    
    /**
     * 是否显示
     * 1:显示
     * 2:不显示
     * */
    @NotNull(message = "是否显示不能为空")
    private java.lang.Short   showFlag;
    
    /**排序字段*/
    @NotNull(message = "排序不可为空")
    private java.lang.Integer orderId;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**最后修改时间*/
    private java.lang.Long    updateTime;
    
    private List<HelpCate>    subList;
    
    private TreeSet<HelpCate> children;             // 下级分类（内部支撑使用）
    
    private String            htmlText         = "";
    
    public String getCateName()
    {
        return cateName;
    }
    
    public void setCateName(String cateName)
    {
        this.cateName = cateName;
    }
    
    public String getParentId()
    {
        return parentId;
    }
    
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }
    
    public Integer getCateNo()
    {
        return cateNo;
    }
    
    public void setCateNo(Integer cateNo)
    {
        this.cateNo = cateNo;
    }
    
    public Integer getHelpLevel()
    {
        return helpLevel;
    }
    
    public void setHelpLevel(Integer helpLevel)
    {
        this.helpLevel = helpLevel;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public HelpCate setDescription(String description)
    {
        this.description = description;
        return this;
    }
    
    public Short getShowType()
    {
        return showType;
    }
    
    public void setShowType(Short showType)
    {
        this.showType = showType;
    }
    
    public Short getShowFlag()
    {
        return showFlag;
    }
    
    public void setShowFlag(Short showFlag)
    {
        this.showFlag = showFlag;
    }
    
    public Integer getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }
    
    public Long getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
    
    public Long getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public List<HelpCate> getSubList()
    {
        return subList;
    }
    
    public void setSubList(List<HelpCate> subList)
    {
        this.subList = subList;
    }
    
    public TreeSet<HelpCate> getChildren()
    {
        if (null == this.children)
        {
            this.children = Sets.newTreeSet(new HelpCate.HelpCateComparator());
        }
        return children;
    }
    
    public String getHtmlText()
    {
        return htmlText;
    }
    
    public HelpCate setHtmlText(String htmlText)
    {
        this.htmlText = htmlText;
        return this;
    }
    
    public static class HelpCateComparator implements Comparator<HelpCate>, Serializable
    {
        private static final long serialVersionUID = 7231777897433726480L;
        
        @Override
        public int compare(HelpCate o1, HelpCate o2)
        {
            int result;
            result = o1.getHelpLevel().compareTo(o2.getHelpLevel());
            if (result == 0) result = o1.getCateNo().compareTo(o2.getCateNo());
            if (result == 0) result = o1.getCreateTime().compareTo(o2.getCreateTime());
            if (result == 0) result = o1.getRefrenceId().compareTo(o2.getRefrenceId());
            return result;
        }
    }
}
