/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.RoleInfo;

/**
 * 角色信息 持久层接口
 * <p>File：RoleInfoDao.java </p>
 * <p>Title: RoleInfoDao </p>
 * <p>Description:RoleInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface RoleInfoMapper extends GenericMapper<RoleInfo>
{
    void enable(String refrenceId);
    
    /**
     * 统计
     * @param roleCode 角色编号 必填
     * @param refrenceId 排除自身 选填
     * @return 数量
     */
    int countBy(@Param("roleCode") String roleCode, @Param("refrenceId") String refrenceId);
    
    String findRefrenceIdByCode(String roleCode);
}
