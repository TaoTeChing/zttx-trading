/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandAudit;

/**
 * 品牌商审核日志 持久层接口
 * <p>File：BrandAuditDao.java </p>
 * <p>Title: BrandAuditDao </p>
 * <p>Description:BrandAuditDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandAuditMapper extends GenericMapper<BrandAudit>
{

}
