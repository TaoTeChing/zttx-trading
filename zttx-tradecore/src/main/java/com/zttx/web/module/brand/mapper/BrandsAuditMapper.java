/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandsAudit;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌审核日志 持久层接口
 * <p>File：BrandsAuditDao.java </p>
 * <p>Title: BrandsAuditDao </p>
 * <p>Description:BrandsAuditDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandsAuditMapper extends GenericMapper<BrandsAudit>
{
    /**
     * 根据品牌商编号和品牌编号获取审核信息
     * @param brandsId
     * @param checkState
     * @return
     */
    List<Map<String, Object>> getBrandsAuditMarkList(@Param("brandsId")String brandsId, @Param("checkState")Short checkState);
}
