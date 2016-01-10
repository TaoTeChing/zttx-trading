/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商新闻资讯 实体对象
 * <p>File：BrandNews.java</p>
 * <p>Title: BrandNews</p>
 * <p>Description:BrandNews</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandNews extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**新闻标题*/
	private java.lang.String newsTitle;
	/**资讯类别编号*/
	private java.lang.String cateId;
	/**图片域名*/
	private java.lang.String imageDomin;
	/**图片地址*/
	private java.lang.String imageUrl;
	/**新闻摘要*/
	private java.lang.String newsSummary;
	/**新闻内容*/
	private java.lang.String newsContent;
	/**定时发布*/
	private java.lang.Long cronTime;
	/**点击次数*/
	private java.lang.Integer hitNum;
	/**关注次数*/
	private java.lang.Integer interestNum;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	private String state; //""：全部，0：待发布，1：已发布
	
	private Long   nowtime;
	
	private String cronDate;
	
	public Long getNowtime() {
		return nowtime;
	}

	public void setNowtime(Long nowtime) {
		this.nowtime = nowtime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getBrandsId()
	{
		return this.brandsId;
	}
	
	public void setBrandsId(java.lang.String brandsId)
	{
		this.brandsId = brandsId;
	}
	
	public java.lang.String getNewsTitle()
	{
		return this.newsTitle;
	}
	
	public void setNewsTitle(java.lang.String newsTitle)
	{
		this.newsTitle = newsTitle;
	}
	
	public java.lang.String getCateId()
	{
		return this.cateId;
	}
	
	public void setCateId(java.lang.String cateId)
	{
		this.cateId = cateId;
	}
	
	public java.lang.String getImageDomin()
	{
		return this.imageDomin;
	}
	
	public void setImageDomin(java.lang.String imageDomin)
	{
		this.imageDomin = imageDomin;
	}
	
	public java.lang.String getImageUrl()
	{
		return this.imageUrl;
	}
	
	public void setImageUrl(java.lang.String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
	
	public java.lang.String getNewsSummary()
	{
		return this.newsSummary;
	}
	
	public void setNewsSummary(java.lang.String newsSummary)
	{
		this.newsSummary = newsSummary;
	}
	
	public java.lang.String getNewsContent()
	{
		return this.newsContent;
	}
	
	public void setNewsContent(java.lang.String newsContent)
	{
		this.newsContent = newsContent;
	}
	
	public java.lang.Long getCronTime()
	{
		return this.cronTime;
	}
	
	public void setCronTime(java.lang.Long cronTime)
	{
		this.cronTime = cronTime;
	}
	
	public java.lang.Integer getHitNum()
	{
		return this.hitNum;
	}
	
	public void setHitNum(java.lang.Integer hitNum)
	{
		this.hitNum = hitNum;
	}
	
	public java.lang.Integer getInterestNum()
	{
		return this.interestNum;
	}
	
	public void setInterestNum(java.lang.Integer interestNum)
	{
		this.interestNum = interestNum;
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
	
    public String getCronDate()
    {
        if (this.getCronTime() != null) cronDate = CalendarUtils.getTimeFromLong(this.getCronTime(), ApplicationConst.DATE_FORMAT_YMDHMS);
        return cronDate;
    }
    
    public void setCronDate(String cronDate)
    {
        this.cronDate = cronDate;
    }
	
    public Boolean getIsWaitSend()
    {
        return this.cronTime > CalendarUtils.getCurrentLong();
    }
    
    public void setIsWaitSend(Boolean isWaitSend)
    {
    }
	
}

