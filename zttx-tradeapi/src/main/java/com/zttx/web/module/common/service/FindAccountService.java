/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.FindAccount;

/**
 * 忘记登录账户 服务接口
 * <p>File：FindAccountService.java </p>
 * <p>Title: FindAccountService </p>
 * <p>Description:FindAccountService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface FindAccountService extends GenericServiceApi<FindAccount>
{
    /**
     * 保存
     * @param findAccount
     */
    void save(FindAccount findAccount) throws BusinessException;
}
