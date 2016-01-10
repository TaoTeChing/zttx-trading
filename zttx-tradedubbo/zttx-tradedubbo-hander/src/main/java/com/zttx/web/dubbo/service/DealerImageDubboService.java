/*
 * @(#)BrandContractDubboService.java 2015-9-2 上午10:32:20
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.entity.DealerImage;

import java.util.List;

/**
 * <p>File：DealerImageDubboService.java</p>
 * <p>Title: DealerImageDubboService</p>
 * <p>Description:DealerImageDubboService</p>
 * <p>Copyright: Copyright (c) 2015年9月6日</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
public interface DealerImageDubboService {

    /**
     * 查询终端商图片列表
     *
     * @param dealerIdList
     * @param page
     * @return
     */
    public PaginateResult<DealerImage> queryDealerImagesList(List<String> dealerIdList,Long updateTime, Pagination page) ;

}
