/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.RoleInfo;

/**
 * 角色信息 服务接口
 * <p>File：RoleInfoService.java </p>
 * <p>Title: RoleInfoService </p>
 * <p>Description:RoleInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface RoleInfoService extends GenericServiceApi<RoleInfo>
{
    List<RoleInfo> findByRoleInfo(RoleInfo roleInfo);
    
    void enable(String refrenceId);
    
    /**
     * 判断是否存在角色编号
     * @param roleCode 必填
     * @param refrenceId 排除自身 选填
     * @return
     */
    boolean isExistRoleCode(String roleCode, String refrenceId);
    
    /**
     * 删除角色
     * 同时删除对应权限
     * @param refrenceId 菜单id
     */
    void seleteCascade(String refrenceId);
}
