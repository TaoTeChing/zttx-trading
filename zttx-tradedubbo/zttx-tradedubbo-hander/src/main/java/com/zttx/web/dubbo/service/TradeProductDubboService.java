/*
 * @(#)TradeProductDubboService.java 2015-9-2 上午10:21:57
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.util.List;

/**
 * <p>File：TradeProductDubboService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-2 上午10:21:57</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public interface TradeProductDubboService
{
    /**
     * 查询所有有加盟关系的授权产品信息
     * @param dealerId
     * @param brandsId
     * @return
     */
    List<String> getAuthorizedProducts2(String dealerId,String brandsId);
}
