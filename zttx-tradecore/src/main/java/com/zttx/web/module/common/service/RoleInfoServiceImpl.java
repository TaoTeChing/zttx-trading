/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.RoleInfo;
import com.zttx.web.module.common.mapper.RoleInfoMapper;
import com.zttx.web.module.common.mapper.RoleMenuMapper;

/**
 * 角色信息 服务实现类
 * <p>File：RoleInfo.java </p>
 * <p>Title: RoleInfo </p>
 * <p>Description:RoleInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RoleInfoServiceImpl extends GenericServiceApiImpl<RoleInfo> implements RoleInfoService
{
    private RoleInfoMapper roleInfoMapper;
    
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    
    @Autowired(required = true)
    public RoleInfoServiceImpl(RoleInfoMapper roleInfoMapper)
    {
        super(roleInfoMapper);
        this.roleInfoMapper = roleInfoMapper;
    }
    
    @Override
    public List<RoleInfo> findByRoleInfo(RoleInfo roleInfo)
    {
        return roleInfoMapper.findList(roleInfo);
    }
    
    @Override
    public void enable(String refrenceId)
    {
        roleInfoMapper.enable(refrenceId);
    }
    
    /**
     * 判断是否存在角色编号
     *
     * @param roleCode
     * @param refrenceId 排除自身
     * @return
     */
    @Override
    public boolean isExistRoleCode(String roleCode, String refrenceId)
    {
        return roleInfoMapper.countBy(roleCode, refrenceId) > 0;
    }
    
    @Override
    public void seleteCascade(String refrenceId)
    {
        // 删除角色前，先把角色权限删除
        roleMenuMapper.deleteByRoleId(refrenceId);
        roleInfoMapper.delete(refrenceId);
    }
}
