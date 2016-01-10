/*
 * @(#)BrandRecruitModel 2014/4/9 14:13
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


/**
 * <p>File：BrandRecruitModel</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/9 14:13</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class BrandRecruitModel
{
    private String brandsId;
    
    @NotBlank(message = "招募书标题不能为空")
    @Length(max = 128, message = "招募书标题长度不能超过128个字符")
    private String recruitTitle;
    
    @NotNull(message = "经营品牌条件必须指定")
    @Range(min = 0, max = 2, message = "经营品牌参数不正确")
    private Short  dealBrand;
    
    @NotNull(message = "开店经验条件必须指定")
    @Range(min = 0, max = 1, message = "开店经验参数不正确")
    private Short  dealExper;
    
    @NotNull(message = "实体店条件必须指定")
    @Range(min = 0, max = 1, message = "经营品牌参数不正确")
    private Short  dealShop;
    
    @NotNull(message = "经营年限条件必须指定")
    @Range(min = 0, max = 4, message = "经营年限参数不正确")
    private Short  dealYear;
    
    @NotNull(message = "招募书状态必须指定")
    @Range(min = 0, max = 1, message = "招募书状态参数不正确")
    private Short  recruitState = 1;
    
    @NotBlank(message = "招募书内容不能为空")
    private String recruitText;
    
    public String getBrandsId()
    {
        return brandsId;
    }
    
    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public String getRecruitTitle()
    {
        return recruitTitle;
    }
    
    public void setRecruitTitle(String recruitTitle)
    {
        this.recruitTitle = recruitTitle;
    }
    
    public Short getDealBrand()
    {
        return dealBrand;
    }
    
    public void setDealBrand(Short dealBrand)
    {
        this.dealBrand = dealBrand;
    }
    
    public Short getDealExper()
    {
        return dealExper;
    }
    
    public void setDealExper(Short dealExper)
    {
        this.dealExper = dealExper;
    }
    
    public Short getDealShop()
    {
        return dealShop;
    }
    
    public void setDealShop(Short dealShop)
    {
        this.dealShop = dealShop;
    }
    
    public Short getDealYear()
    {
        return dealYear;
    }
    
    public void setDealYear(Short dealYear)
    {
        this.dealYear = dealYear;
    }
    
    public Short getRecruitState()
    {
        return recruitState;
    }
    
    public void setRecruitState(Short recruitState)
    {
        this.recruitState = recruitState;
    }
    
    public String getRecruitText()
    {
        return recruitText;
    }
    
    public void setRecruitText(String recruitText)
    {
        this.recruitText = recruitText;
    }
}
