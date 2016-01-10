/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.dealer.entity.DealerAudit;

/**
 * 经销商审核日志 持久层接口
 * <p>File：DealerAuditDao.java </p>
 * <p>Title: DealerAuditDao </p>
 * <p>Description:DealerAuditDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerAuditMapper extends GenericMapper<DealerAudit>
{

}
