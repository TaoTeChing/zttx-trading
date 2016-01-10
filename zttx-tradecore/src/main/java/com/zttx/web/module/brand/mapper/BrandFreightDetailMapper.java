/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandFreightDetail;

import java.util.List;

/**
 * 运费明细表 持久层接口
 * <p>File：BrandFreightDetailDao.java </p>
 * <p>Title: BrandFreightDetailDao </p>
 * <p>Description:BrandFreightDetailDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandFreightDetailMapper extends GenericMapper<BrandFreightDetail>
{

    public void removeByTemplateId(String templateId);

    public List<BrandFreightDetail> listBySettingsId(String settingsId);

    BrandFreightDetail getDetail(String templateId, Integer carryType, Integer areaNo, Integer cityNo,Integer privceNo);

    List<BrandFreightDetail> listDetail(String brandId, List<String> templateIdList, Integer carryType, Integer areaNo);
}
