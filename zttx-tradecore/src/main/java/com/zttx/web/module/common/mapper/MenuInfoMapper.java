/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.MenuInfo;

/**
 * 经销商、品牌商菜单信息 持久层接口
 * <p>File：MenuInfoDao.java </p>
 * <p>Title: MenuInfoDao </p>
 * <p>Description:MenuInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface MenuInfoMapper extends GenericMapper<MenuInfo>
{
    List<MenuInfo> findMenuByRoleId(String roleId);
    
    List<MenuInfo> getMainMenuList(String userId);
    
    List<MenuInfo> getSideMenuList(@Param("userId") String userId, @Param("parentMenuId") String parentMenuId);
    
    List<MenuInfo> findChildren(@Param("userId") String userId, @Param("parentMenuId") String parentMenuId);
    
    List<MenuInfo> findByMenuInfo(MenuInfo menuInfo);
    
    MenuInfo selectByPrimaryKeyWithParent(String refrenceId);
    
    void enable(String refrenceId);
    
    /**
     * 根据父id 查找子菜单数量
     * @param upMenuId 父id
     * @return
     */
    int countByUpMuneId(String upMenuId);
    
    /**
     * 根据id集合 查询相关记录
     * @param array
     * @return
     */
    List<MenuInfo> findByIds(String[] array);
}
