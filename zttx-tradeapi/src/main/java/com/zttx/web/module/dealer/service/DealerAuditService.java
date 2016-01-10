/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerAudit;

/**
 * 经销商审核日志 服务接口
 * <p>File：DealerAuditService.java </p>
 * <p>Title: DealerAuditService </p>
 * <p>Description:DealerAuditService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerAuditService extends GenericServiceApi<DealerAudit>
{
    /**
     * 保存审核日志
     * @param dealerAudit
     */
    void save(DealerAudit dealerAudit);
}
