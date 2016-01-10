/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerPayword;

/**
 * 经销商支付密码 服务接口
 * <p>File：DealerPaywordService.java </p>
 * <p>Title: DealerPaywordService </p>
 * <p>Description:DealerPaywordService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerPaywordService extends GenericServiceApi<DealerPayword>
{
    /**
     * 修改支付密码
     * @param newPwd 新的支付密码
     * @author 夏铭
     */
    void updatePayPassword(DealerPayword newPwd);
}
