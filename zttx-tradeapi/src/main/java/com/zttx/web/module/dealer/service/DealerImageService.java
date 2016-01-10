/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerImage;

import java.util.List;

/**
 * 经销商店铺招牌 服务接口
 * <p>File：DealerImageService.java </p>
 * <p>Title: DealerImageService </p>
 * <p>Description:DealerImageService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerImageService extends GenericServiceApi<DealerImage>
{
    /**
     * 根据经销商编号跟创建时间获取分页结果集
     * @param dealerImage 参数
     * @return PaginateResult
     * @throws BusinessException
     */
    PaginateResult<DealerImage> selectDealerImages(DealerImage dealerImage, Pagination page) throws BusinessException;

    /**
     * 查询终端商图片列表
     *
     * @param dealerId
     * @return
     */
    public List<DealerImage> selectDealerImageByDealerId(String dealerId);
}
