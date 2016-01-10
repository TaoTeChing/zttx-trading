/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.SecurityCert;

/**
 * 经销商/品牌商申请更改手机认证 持久层接口
 * <p>File：SecurityCertDao.java </p>
 * <p>Title: SecurityCertDao </p>
 * <p>Description:SecurityCertDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface SecurityCertMapper extends GenericMapper<SecurityCert>
{
    /**
     * 根据用户ID和审核状态查询
     * @param userId
     * @param actState
     * @return
     */
    SecurityCert findByUserIdAndActState(@Param("userId") String userId, @Param("actState") Short actState);
}
