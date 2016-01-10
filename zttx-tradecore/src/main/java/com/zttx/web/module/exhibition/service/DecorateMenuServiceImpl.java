/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.module.exhibition.entity.*;
import com.zttx.web.module.exhibition.mapper.*;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.consts.DecorateMenuConst;
import com.zttx.web.module.exhibition.model.MenuJsonModel;

/**
 * 展厅装修菜单 服务实现类
 * <p>File：DecorateMenu.java </p>
 * <p>Title: DecorateMenu </p>
 * <p>Description:DecorateMenu </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateMenuServiceImpl extends GenericServiceApiImpl<DecorateMenu> implements DecorateMenuService
{
    @Autowired
    private DecorateMenuMapper decorateMenuMapper;
    
    @Autowired
    private DecorateSysMenuMapper decorateSysMenuMapper;

    @Autowired
    private DecorateHeaderLogMapper decorateHeaderLogMapper;

    @Autowired
    private DecorateHeaderMapper decorateHeaderMapper;

    @Autowired
    private DecorateMenuLogMapper decorateMenuLogMapper;

    @Autowired(required = true)
    public DecorateMenuServiceImpl(DecorateMenuMapper decorateMenuMapper)
    {
        super(decorateMenuMapper);
        this.decorateMenuMapper = decorateMenuMapper;
    }

    @Override
    public DecorateMenu findByBrandIdAndBrandsId(String brandId, String brandsId)
    {
        List<DecorateMenu> list=decorateMenuMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public DecorateMenu findLatestBrandMenus(String brandId, String brandsId)
    {
        DecorateMenu menu  = null;
        List<DecorateMenu> menuList = decorateMenuMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        if (CollectionUtils.isNotEmpty(menuList)) {
            menu = menuList.get(0);
        }
        // meger 系统默认导航菜单
        List<MenuJsonModel> allMenus = mergeSysMenu(menu, brandId, brandsId);
        if (null == menu)
        {
            menu = new DecorateMenu(brandId, brandsId, false, null);
        }
        menu.setMenus(allMenus);

        return menu;
    }

    private List<MenuJsonModel> mergeSysMenu(DecorateMenu menu, String brandId, String brandsId)
    {
        boolean isNew = false;
        List<MenuJsonModel> allMenus = null;
        if (menu != null)
        {
            String json = menu.getMenuValue();
            if (StringUtils.isNotBlank(json))
            {
                allMenus = JSON.parseArray(json, MenuJsonModel.class);
            }
        }
        else
        {
            isNew = true;
        }
        List<DecorateSysMenu> sysMenus = decorateSysMenuMapper.findList(new DecorateSysMenu());
        if (sysMenus != null && !sysMenus.isEmpty())
        {
            Map<String, Object> map = new HashMap<String, Object>();
            if (null == allMenus)
            {
                allMenus = new ArrayList<MenuJsonModel>();
            }
            for (MenuJsonModel model : allMenus)
            {
                String id = model.getId();
                if (StringUtils.isNotBlank(id))
                {
                    map.put(id, model);
                }
            }
            for (DecorateSysMenu sysMenu : sysMenus)
            {
                if (!map.containsKey(sysMenu.getRefrenceId()))
                {
                    MenuJsonModel model = new MenuJsonModel();
                    model.setId(sysMenu.getRefrenceId());
                    model.setN(sysMenu.getMenuName());
                    if (isNew)
                    {
                        model.setS(String.valueOf(DecorateMenuConst.MENU_SHOW));
                    }
                    else
                    {
                        model.setS(String.valueOf(DecorateMenuConst.MENU_HIDE));
                    }
                    if (StringUtils.isNotBlank(sysMenu.getMenuUrl()) && StringUtils.isNotBlank(brandsId) && StringUtils.isNotBlank(brandId))
                    {
                        String url = sysMenu.getMenuUrl().replace("{brandesId}", brandsId).replace("{brandId}", brandId);
                        model.setUrl(url);
                    }
                    allMenus.add(model);
                }
            }
        }
        return allMenus;
    }

    @Override
    public void saveNavlog(String brandId, String brandsId,
            DecorateHeaderLog header, List<MenuJsonModel> menus,
            HttpServletRequest request)  throws BusinessException
    {

        try {
            saveOrUpdateHeadNavLog(brandId, brandsId, header, request);
            saveMenuLog(brandId, brandsId, menus);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }

    }

    /**
     * 店铺导航设置
     * @param brandId
     * @param header
     * @param request
     * @throws BusinessException
     */
    private void saveOrUpdateHeadNavLog(String brandId, String brandsId, DecorateHeaderLog header, HttpServletRequest request) throws BusinessException,
            InvocationTargetException, IllegalAccessException
    {
        DecorateHeaderLog dbHeaderLog = null;
        List<DecorateHeaderLog> dbHeaderList = decorateHeaderLogMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        if (CollectionUtils.isNotEmpty(dbHeaderList)){
            dbHeaderLog = dbHeaderList.get(0);
        }

        String[] newUrls = new String[]{header.getNavDefaultUrl(), header.getNavChangeUrl()};
        if (dbHeaderLog == null)
        {
            dbHeaderLog = new DecorateHeaderLog();
            DecorateHeader temp = null;
            List<DecorateHeader> tempList = decorateHeaderMapper.findByBrandIdAndBrandsId(brandId, brandsId);
            if (CollectionUtils.isNotEmpty(tempList)){
                temp = tempList.get(0);
            }

            if (temp == null)
            {
                dbHeaderLog.setBrandId(brandId);
                dbHeaderLog.setBrandsId(brandsId);
                dbHeaderLog.setShowLogo(false);
                dbHeaderLog.setShowName(false);
                dbHeaderLog.setComName("");
                dbHeaderLog.setShowCate((short) 1);
            }
            else
            {
                BeanUtils.copyProperties(dbHeaderLog, temp);
                dbHeaderLog.setRefrenceId(null);
            }
        }
        for (int i = 0, len = newUrls.length; i < len; i++)
        {
            if (StringUtils.isNotBlank(newUrls[i]))
            {
                //String tempUrl = MultipartUtils.moveAndDeleteFile(request, MultipartUtils.BRAND_IMG_PATH, newUrls[i], null);
                String tempUrl = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, newUrls[i], UploadAttCateConst.BRANDS_LOGO);
                newUrls[i] = tempUrl;
            }
        }

        dbHeaderLog.setNavDefaultUrl(newUrls[0]);
        dbHeaderLog.setNavChangeUrl(newUrls[1]);
        dbHeaderLog.setNavDefaultColor(header.getNavDefaultColor());
        dbHeaderLog.setNavChangeColor(header.getNavChangeColor());
        dbHeaderLog.setNavSelectColor(header.getNavSelectColor());
        dbHeaderLog.setNavDefaultFont(header.getNavDefaultFont());
        dbHeaderLog.setNavChangeFont(header.getNavChangeFont());


        if (StringUtils.isNotBlank(header.getRefrenceId()))
        {
            header.setUpdateTime(CalendarUtils.getCurrentLong());
            decorateHeaderLogMapper.updateByPrimaryKey(header);
        }
        else
        {
            Long currentTime = CalendarUtils.getCurrentLong();
            dbHeaderLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dbHeaderLog.setUpdateTime(currentTime);
            dbHeaderLog.setCreateTime(currentTime);
            dbHeaderLog.setDelFlag(false);
            decorateHeaderLogMapper.insert(dbHeaderLog);
        }

    }


    /**
     * 保存菜单修改
     * @param brandId
     * @param menus
     */
    private void saveMenuLog(String brandId, String brandsId, List<MenuJsonModel> menus) throws InvocationTargetException, IllegalAccessException
    {
        String json = null;

        List<DecorateMenuLog> logList = decorateMenuLogMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        DecorateMenuLog log = null;
        if (CollectionUtils.isNotEmpty(logList)){
            log = logList.get(0);
        }
        if (log == null)
        {
            log = new DecorateMenuLog();

            List<DecorateMenu> tempList = decorateMenuMapper.findByBrandIdAndBrandsId(brandId, brandsId);
            DecorateMenu temp = null;
            if (CollectionUtils.isNotEmpty(tempList)){
                temp = tempList.get(0);
            }
            if (temp != null)
            {
                BeanUtils.copyProperties(log, temp);
                log.setRefrenceId(null);
            }
            else
            {
                log.setBrandId(brandId);
                log.setBrandsId(brandsId);
            }
        }
        if (menus != null && !menus.isEmpty())
        {
            json = JSON.toJSONString(menus);
        }
        log.setMenuValue(json);
        if (StringUtils.isBlank(log.getRefrenceId()))
        {
            Long currentTime = CalendarUtils.getCurrentLong();
            log.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            log.setUpdateTime(currentTime);
            log.setCreateTime(currentTime);
            log.setDelFlag(false);
            decorateMenuLogMapper.insert(log);
        }
        else
        {
            decorateMenuLogMapper.updateByPrimaryKey(log);
        }
    }
    
}
