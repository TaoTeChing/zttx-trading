package com.zttx.web.module.brand.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BrandNewsModel implements java.io.Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 32133077712761126L;
    
    // 是否定时发布
    private Boolean           isWaitSend;
    
    // 日期
    private String            cronDate;
    
    private String            refrenceId;
    
    private String            brandId;
    
    private String            brandsId;
    
    private String            cateId;
    
    private String            newsTitle;
    
    private String            newsSummary;
    
    private String            newsContent;
    
    private Long              cronTime;
    
    private String            imageUrl;
    
    public String getRefrenceId()
    {
        return refrenceId;
    }
    
    public void setRefrenceId(String refrenceId)
    {
        this.refrenceId = refrenceId;
    }
    
    public String getBrandId()
    {
        return brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    @NotBlank(message = "必须选择品牌")
    public String getBrandsId()
    {
        return brandsId;
    }
    
    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    @NotBlank(message = "必须选择新闻分类")
    public String getCateId()
    {
        return cateId;
    }
    
    public void setCateId(String cateId)
    {
        this.cateId = cateId;
    }
    
    @NotBlank(message = "新闻标题不能为空")
    @Length(max = 128, message = "新闻标题的长度必须小于等于{max}个字")
    public String getNewsTitle()
    {
        return newsTitle;
    }
    
    public void setNewsTitle(String newsTitle)
    {
        this.newsTitle = newsTitle;
    }
    
    @Length(max = 256, message = "摘要的长度必须小于等于{max}个字")
    public String getNewsSummary()
    {
        return newsSummary;
    }
    
    public void setNewsSummary(String newsSummary)
    {
        this.newsSummary = newsSummary;
    }
    
    public String getNewsContent()
    {
        return newsContent;
    }
    
    public void setNewsContent(String newsContent)
    {
        this.newsContent = newsContent;
    }
    
    public Long getCronTime()
    {
        return cronTime;
    }
    
    public void setCronTime(Long cronTime)
    {
        this.cronTime = cronTime;
    }
    
    public String getImageUrl()
    {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }
    
    public Boolean getIsWaitSend()
    {
        return isWaitSend;
    }
    
    public void setIsWaitSend(Boolean isWaitSend)
    {
        this.isWaitSend = isWaitSend;
    }
    
    public String getCronDate()
    {
        return cronDate;
    }
    
    public void setCronDate(String cronDate)
    {
        this.cronDate = cronDate;
    }
}
