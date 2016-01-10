/*
 * @(#)DealerJoinDubboService.java 2015-9-2 上午10:24:12
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.util.List;
import java.util.Map;

/**
 * <p>File：DealerJoinDubboService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-2 上午10:24:12</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public interface DealerJoinDubboService
{
    /**
     * 根据经销商id获取品牌信息
     * @param dealerId
     * @return
     */
    List<Map<String,Object>> getCoorperatedBrands(String dealerId);
}
