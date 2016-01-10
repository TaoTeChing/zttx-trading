/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.exhibition.entity.DecorateSysMenu;
import com.zttx.web.module.exhibition.mapper.DecorateSysMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 展厅装修系统菜单 服务实现类
 * <p>File：DecorateSysMenu.java </p>
 * <p>Title: DecorateSysMenu </p>
 * <p>Description:DecorateSysMenu </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateSysMenuServiceImpl extends GenericServiceApiImpl<DecorateSysMenu> implements DecorateSysMenuService
{

    private DecorateSysMenuMapper decorateSysMenuMapper;

    @Autowired(required = true)
    public DecorateSysMenuServiceImpl(DecorateSysMenuMapper decorateSysMenuMapper)
    {
        super(decorateSysMenuMapper);
        this.decorateSysMenuMapper = decorateSysMenuMapper;
    }
}
