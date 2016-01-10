/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 帮助内容 实体对象
 * <p>File：HelpInfo.java</p>
 * <p>Title: HelpInfo</p>
 * <p>Description:HelpInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class HelpInfo extends GenericEntity
{
    private static final long serialVersionUID = 3222494007171715869L;
    
    /**帮助分类*/
    private java.lang.Integer helpNo;
    
    private java.lang.String  cateName;                               // 类别名称 （显示）
    
    /**分类编号*/
    @NotBlank(message = "分类编号不可为空")
    private java.lang.String  helpCateId;
    
    /**标题*/
    @NotBlank(message = "标题不可为空")
    private java.lang.String  title;
    
    /**简单描述*/
    private java.lang.String  subMark;
    
    /**内容*/
    @NotBlank(message = "内容不可为空")
    private java.lang.String  htmlText;
    
    /**是否推荐*/
    private java.lang.Boolean isRecommand      = Boolean.FALSE;
    
    /**是否热门*/
    private java.lang.Boolean isHot            = Boolean.FALSE;
    
    /**是否常见问题*/
    private java.lang.Boolean isFaq            = Boolean.FALSE;
    
    /**创建IP*/
    private java.lang.Integer createIp;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**最后修改时间*/
    private java.lang.Long    updateTime;
    
    private List<HelpCate>    helpCates;
    
    public Integer getHelpNo()
    {
        return helpNo;
    }
    
    public void setHelpNo(Integer helpNo)
    {
        this.helpNo = helpNo;
    }
    
    public String getHelpCateId()
    {
        return helpCateId;
    }
    
    public void setHelpCateId(String helpCateId)
    {
        this.helpCateId = helpCateId;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getSubMark()
    {
        return subMark;
    }
    
    public void setSubMark(String subMark)
    {
        this.subMark = subMark;
    }
    
    public String getHtmlText()
    {
        return htmlText;
    }
    
    public void setHtmlText(String htmlText)
    {
        this.htmlText = htmlText;
    }
    
    public Boolean getIsRecommand()
    {
        return isRecommand;
    }
    
    public void setIsRecommand(Boolean isRecommand)
    {
        this.isRecommand = isRecommand;
    }
    
    public Boolean getIsHot()
    {
        return isHot;
    }
    
    public void setIsHot(Boolean isHot)
    {
        this.isHot = isHot;
    }
    
    public Boolean getIsFaq()
    {
        return isFaq;
    }
    
    public void setIsFaq(Boolean isFaq)
    {
        this.isFaq = isFaq;
    }
    
    public Integer getCreateIp()
    {
        return createIp;
    }
    
    public void setCreateIp(Integer createIp)
    {
        this.createIp = createIp;
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
    
    public List<HelpCate> getHelpCates()
    {
        return helpCates;
    }
    
    public void setHelpCates(List<HelpCate> helpCates)
    {
        this.helpCates = helpCates;
    }
    
    public String getCateName()
    {
        return cateName;
    }
    
    public HelpInfo setCateName(String cateName)
    {
        this.cateName = cateName;
        return this;
    }
}
