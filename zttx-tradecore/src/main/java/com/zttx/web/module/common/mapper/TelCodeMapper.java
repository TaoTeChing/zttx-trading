/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.TelCode;

/**
 * 手机验证码 持久层接口
 * <p>File：TelCodeDao.java </p>
 * <p>Title: TelCodeDao </p>
 * <p>Description:TelCodeDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface TelCodeMapper extends GenericMapper<TelCode>
{

    /**
     * 根据手机号,验证类型,使用状态查找TelCode
     * @param userMobile
     * @param verifyType
     * @param useState
     * @return
     */
    TelCode search(@Param("userMobile") String userMobile, @Param("verifyType") String verifyType, @Param("useState") Boolean useState);
    
    /**
     * 更新手机验证记录状态
     * @param refrenceId
     * @param useState
     * @param useTime
     */
    int updateUseState(@Param("refrenceId") String refrenceId, @Param("useState") Boolean useState, @Param("useTime") Long useTime);
}
