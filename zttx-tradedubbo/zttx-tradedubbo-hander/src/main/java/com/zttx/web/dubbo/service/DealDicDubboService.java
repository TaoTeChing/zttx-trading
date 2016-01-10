/*
 * @(#)BrandContractDubboService.java 2015-9-2 上午10:32:20
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.web.module.common.entity.DealDic;

import java.util.List;


/**
 * 
 * <p>File：DealDicDubboService.java</p>
 * <p>Title: DealDicDubboService</p>
 * <p>Description:DealDicDubboService</p>
 * <p>Copyright: Copyright (c) 2015年9月6日</p>
 * <p>Company: 8637.com</p>
 * @author 江枫林
 * @version 1.0
 */
public interface DealDicDubboService
{
    /**
     * 查询所有字典数据
     *
     * @return
     */
    List<DealDic> queryDealDicList();
}
