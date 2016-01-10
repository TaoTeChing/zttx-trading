/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.EmailValidate;

/**
 * 邮件验证 持久层接口
 * <p>File：EmailValidateDao.java </p>
 * <p>Title: EmailValidateDao </p>
 * <p>Description:EmailValidateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface EmailValidateMapper extends GenericMapper<EmailValidate>
{

    /**
     * 根据邮箱地址、更新使用状态返回对应记录
     * @param emailAddr 邮箱
     * @param useState  使用状态
     * @return
     * @author 李星
     */
    EmailValidate search(@Param("emailAddr") String emailAddr, @Param("useState") Boolean useState);
    
    /**
     * 更新邮箱验证状态
     * @param refrenceId 实体主键
     * @param useState   使用状态  0：未使用 1：已使用
     * @param useTime    验证使用时间
     */
    Integer update(@Param("refrenceId") String refrenceId, @Param("useState") Boolean useState, @Param("useTime") Long useTime);
}
