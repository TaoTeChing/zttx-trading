/*
 * @(#)DealerShopersDubboService.java 2015-9-2 上午10:10:26
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.util.List;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.web.module.dealer.entity.DealerShopers;

/**
 * <p>File：DealerShopersDubboService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-2 上午10:10:26</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public interface DealerShoperDubboService
{
    /**
     * 添加产品到购物车
     * @param dealerId
     * @param shopers
     * @return
     */
    JsonMessage addProductIntoShopper(String dealerId,List<DealerShopers> shopers);
}
