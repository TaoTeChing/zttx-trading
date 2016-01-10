/*
 * @(#)DecorateHeaderLogModel.java 14-4-23 下午2:23
 * Copyright 2014 吴万杰, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>File：DecorateHeaderLogModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 14-4-23 下午2:23</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吴万杰
 * @version 1.0
 */
public class DecorateHeaderLogModel implements Serializable
{
    private static final long serialVersionUID = -5765246311772673355L;
    
    List<MenuJsonModel>       menus;                                    // 菜单导航
    
    public List<MenuJsonModel> getMenus()
    {
        return menus;
    }
    
    public void setMenus(List<MenuJsonModel> menus)
    {
        this.menus = menus;
    }
}
