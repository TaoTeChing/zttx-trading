/*
 * @(#)BrandsCountDubboServiceImpl.java 2015-8-22 下午6:13:25
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandsCount;
import com.zttx.web.module.brand.mapper.BrandsCountMapper;
import com.zttx.web.module.brand.service.BrandsCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>File：BrandsCountDubboServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-22 下午6:13:25</p>
 * <p>Company: 8637.com</p>
 *
 * @author 黄小平
 * @version 1.0
 */
@Service
public class BrandsCountDubboServiceImpl implements BrandsCountDubboService {
    @Autowired
    private BrandsCountService brandsCountService;
    @Autowired
    private BrandsCountMapper brandsCountMapper;

    @Override
    public void modifyBrandsCount(String brandsId, String[] countType) throws BusinessException {
        brandsCountService.modifyBrandsCount(brandsId, countType);
    }

    /**
     * 查询品牌商计数器数据
     *
     * @param refrenceId
     * @return
     */
    @Override
    public BrandsCount queryBrandCountList(String refrenceId) {

        BrandsCount brandCountObj = brandsCountMapper.selectByPrimaryKey(refrenceId);

        return brandCountObj;
    }
}
