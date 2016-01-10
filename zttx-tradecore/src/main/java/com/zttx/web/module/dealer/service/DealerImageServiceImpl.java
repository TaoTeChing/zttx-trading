/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.mapper.DealerImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 经销商店铺招牌 服务实现类
 * <p>File：DealerImage.java </p>
 * <p>Title: DealerImage </p>
 * <p>Description:DealerImage </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerImageServiceImpl extends GenericServiceApiImpl<DealerImage> implements DealerImageService {
    private DealerImageMapper dealerImageMapper;

    @Autowired(required = true)
    public DealerImageServiceImpl(DealerImageMapper dealerImageMapper) {
        super(dealerImageMapper);
        this.dealerImageMapper = dealerImageMapper;
    }

    @Override
    public PaginateResult<DealerImage> selectDealerImages(DealerImage dealerImage, Pagination page) throws BusinessException {
        Map<String, List<String>> param = Maps.newHashMap();
        String dealerId = dealerImage.getDealerId();
        param.put("idList", Lists.newArrayList(dealerId.split("~")));
        return new PaginateResult<>(dealerImage.getPage(), dealerImageMapper.selectDealerImages(param, dealerImage.getCreateTime(), page));
    }

    /**
     * 查询终端商图片列表
     *
     * @param dealerId
     * @return
     */
    @Override
    public List<DealerImage> selectDealerImageByDealerId(String dealerId) {
        return dealerImageMapper.selectDealerImagesByDealerId(dealerId);
    }
}
