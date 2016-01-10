/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.module.brand.entity.BrandNews;
import com.zttx.web.module.brand.mapper.BrandNewsMapper;
import com.zttx.web.module.brand.model.BrandNewsModel;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商新闻资讯 服务实现类
 * <p>File：BrandNews.java </p>
 * <p>Title: BrandNews </p>
 * <p>Description:BrandNews </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandNewsServiceImpl extends GenericServiceApiImpl<BrandNews> implements BrandNewsService
{

    private BrandNewsMapper brandNewsMapper;

    @Autowired(required = true)
    public BrandNewsServiceImpl(BrandNewsMapper brandNewsMapper)
    {
        super(brandNewsMapper);
        this.brandNewsMapper = brandNewsMapper;
    }

    @Override
    public void addHitNum(String refrenceId)
    {
        brandNewsMapper.addHitNum(refrenceId);
    }
    
    /**
     * 保存品牌资讯
     * @author 陈建平
     * @param brandNewsModel
     * @return
     */
    public BrandNews insertBrandNews(BrandNewsModel brandNewsModel)
    {
        if (null != brandNewsModel.getIsWaitSend() && brandNewsModel.getIsWaitSend())
        {// 定时发布
            long cronTime = CalendarUtils.getLongFromTime(brandNewsModel.getCronDate(), ApplicationConst.DATE_FORMAT_YMDHMS);
            brandNewsModel.setCronTime(cronTime);
        }
        else
        // 即时发布
        {
            brandNewsModel.setCronTime(CalendarUtils.getCurrentLong());
        }
        BrandNews brandNews = new BrandNews();
    	try {
			BeanUtils.copyProperties(brandNews, brandNewsModel);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        if (StringUtils.isBlank(brandNewsModel.getRefrenceId()))
        {
        	brandNews.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        	brandNews.setCreateTime(CalendarUtils.getCurrentLong());
			brandNews.setUpdateTime(CalendarUtils.getCurrentLong());
			brandNews.setDelFlag(false);
			brandNews.setHitNum(0);
			brandNews.setInterestNum(0);
            brandNewsMapper.insert(brandNews);
        }
        else
        {
        	BrandNews temp = brandNewsMapper.selectByPrimaryKey(brandNewsModel.getRefrenceId());
        	brandNews.setUpdateTime(CalendarUtils.getCurrentLong());
        	brandNews.setCreateTime(temp.getCreateTime());
        	brandNews.setHitNum(temp.getHitNum());
        	brandNews.setInterestNum(temp.getInterestNum());
            brandNewsMapper.updateByPrimaryKey(brandNews);
        }
        return brandNews;
    }
    
    /**
     * 获取品牌商资讯
     * @author 陈建平
     * @param filter
     * @return
     */
    @Override
    public List<Map<String,Object>> listBrandNews(BrandNews filter){
    	return brandNewsMapper.listBrandNews(filter);
    }
}
