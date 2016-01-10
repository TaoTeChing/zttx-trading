/*
 * @(#)CommonController.java 2015-8-27 下午4:08:20
 * Copyright 2015 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.module.brand.entity.BrandsCount;
import com.zttx.web.module.brand.service.BrandsCountService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * <p>File：CommonController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-27 下午4:08:20</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
public class GenericBaseController extends GenericController
{
    @Autowired
    protected UserInfoService userInfoService;
    
    @Autowired
    protected BrandsCountService brandsCountService;

    private static Logger   logger = LoggerFactory
                                           .getLogger(GenericBaseController.class);

    /**
     * 获取当前 登录的 终端商Id
     * @return null 表示没有终端商登录
     */
    public String getCurrentLoginDealerId()
    {
        UserPrincipal principal = null;
        try
        {
            principal = OnLineUserUtils.getCurrentDealer();
        }
        catch (BusinessException e)
        {
        }
        if (principal != null
                && StringUtils.isNotBlank(principal.getRefrenceId()))
        {
            // 获取当前登录用户的所属终端商Id
            UserInfo userInfo = userInfoService.selectByPrimaryKey(principal
                    .getRefrenceId());
            // userInfo parentId 为null,则表明当前用户即为主账号,否则取 parentId为主账号
            String dealerId = StringUtils.isNotBlank(userInfo.getParentId()) ? userInfo
                    .getParentId() : userInfo.getRefrenceId();
            return dealerId;
        }
        return null;
    }
    
    
    /**
     * 获取当前 登录的  品牌商Id
     * @return null 表示没有品牌商登录
     */
    public String getCurrentLoginBrandId()
    {
        UserPrincipal principal = null;
        try
        {
            principal = OnLineUserUtils.getCurrentBrand();
        }
        catch (BusinessException e)
        {
        }
        if (principal != null
                && StringUtils.isNotBlank(principal.getRefrenceId()))
        {
            // 获取当前登录用户的所属品牌商Id
            UserInfo userInfo = userInfoService.selectByPrimaryKey(principal
                    .getRefrenceId());
            // userInfo parentId 为null,则表明当前用户即为主账号,否则取 parentId为主账号
            String brandId = StringUtils.isNotBlank(userInfo.getParentId()) ? userInfo
                    .getParentId() : userInfo.getRefrenceId();
            return brandId;
        }
        return null;
    }

    
    /**
     * 获取当前 登录的  品牌商Id
     * @return null 表示没有品牌商登录
     */
    public String getCurrentLoginUserId()
    {
        UserPrincipal principal = null;
        try
        {
            principal = OnLineUserUtils.getCurrentBrand();
        }
        catch (BusinessException e)
        {
        }
        if (principal != null
                && StringUtils.isNotBlank(principal.getRefrenceId()))
        {   
            return principal.getRefrenceId();
        }
        return null;
    }

    
    /**
     * 增加浏览品牌纪录     如果用户已经登录并且是终端商，则 添加终端商访问品牌记录 
     */
    public void executeDealerViewBrands(String brandId, String brandesId)
    {
        /*UserPrincipal principal = null;
        try
        {
            principal = OnLineUserUtils.getCurrentDealer();
        }
        catch (BusinessException e)
        {
        }
        if (principal != null
                && StringUtils.isNotBlank(principal.getRefrenceId())
                && StringUtils.isNotBlank(brandId))
        {*/
            // 获取当前登录用户的所属终端商Id
            //UserInfo userInfo = userInfoService.selectByPrimaryKey(principal
            //        .getRefrenceId());
            // userInfo parentId 为null,则表明当前用户即为主账号,否则取 parentId为主账号
            //String dealerId = StringUtils.isNotBlank(userInfo.getParentId()) ? userInfo
            //        .getParentId() : userInfo.getRefrenceId();
            //if (StringUtils.isNotBlank(dealerId))
           // {
                // 添加品牌访问计数
            	BrandsCount brandsCount = brandsCountService.findByBrandIdAndBrandsId(brandId, brandesId);
            	if(null != brandsCount){
            		//int inviteCount = brandsCount.getInviteCount()==null?0:brandsCount.getInviteCount();
            		//brandsCount.setInviteCount(inviteCount+1);
            		brandsCount.setViewNum((brandsCount.getViewNum()==null?0:brandsCount.getViewNum())+1);
                    brandsCount.setUpdateTime(CalendarUtils.getCurrentLong());
            	    brandsCountService.updateByPrimaryKey(brandsCount);
            	}
           // }
        //}
    }

    /**
     * 增加收藏品牌纪录,如终端商已经登录，则生成记录
     */
    public void executeDealerCollectBrands(String brandId, String brandesId)
    {
        UserPrincipal principal = null;
        try
        {
            principal = OnLineUserUtils.getCurrentDealer();
        }
        catch (BusinessException e)
        {
        }
        if (principal != null
                && StringUtils.isNotBlank(principal.getRefrenceId())
                && StringUtils.isNotBlank(brandId))
        {
            // 获取当前登录用户的所属终端商Id
            UserInfo userInfo = userInfoService.selectByPrimaryKey(principal
                    .getRefrenceId());
            // userInfo parentId 为null,则表明当前用户即为主账号,否则取 parentId为主账号
            String dealerId = StringUtils.isNotBlank(userInfo.getParentId()) ? userInfo
                    .getParentId() : userInfo.getRefrenceId();
            // 添加品牌收藏
            if (StringUtils.isNotBlank(dealerId))
            {
            	BrandsCount brandsCount = brandsCountService.findByBrandIdAndBrandsId(brandId, brandesId);
            	if(null != brandsCount){
            		int favNum = brandsCount.getFavNum()==null?0:brandsCount.getFavNum();
            		brandsCount.setFavNum(favNum+1);
                    brandsCount.setUpdateTime(CalendarUtils.getCurrentLong());
            		brandsCountService.updateByPrimaryKey(brandsCount);
            	}
            }
        }
    }

    /**
     * 判断当前是否有用户已经登录
     */
    public boolean isAlreadyLogin()
    {
        boolean isLogin = false;
        try
        {
            UserPrincipal principal = OnLineUserUtils.getPrincipal();
            if (principal != null
                    && StringUtils.isNotBlank(principal.getRefrenceId()))
            {
                isLogin = true;
            }
        }
        catch (Exception e)
        {
        }
        return isLogin;
    }

    /*
     * 判断当前是否是 品牌商用户登录
     */
    public boolean isBrandLogin()
    {
        boolean isBrandLogin = false;
        try
        {
            UserPrincipal principal = OnLineUserUtils.getCurrentBrand();
            if (principal != null
                    && StringUtils.isNotBlank(principal.getRefrenceId()))
            {
                isBrandLogin = true;
            }
        }
        catch (BusinessException e)
        {
        }
        return isBrandLogin;
    }
    
    
    
    /**
     * 获取区域可能用节点
     * @param province     省
     * @param city         市
     * @param area         区
     * @return
     * @author 施建波
     */
    public String getAreaNo(String province, String city, String county)
    {
        String areaNo = province;
        if (StringUtils.isNotBlank(city)) areaNo = city;
        if (StringUtils.isNotBlank(county)) areaNo = county;
        return areaNo;
    }
    
    /**
     * 根据时间获取季节
     * @param time
     * @return
     */
    public short getSeason(Long time){
        return (short)(((CalendarUtils.getMonth(time)-1)/3)+1);
    }
}
