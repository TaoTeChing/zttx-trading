/*
 * @(#)BrandContractDubboService.java 2015-9-2 上午10:32:20
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.brand.entity.BrandViewContact;
/**
 * 
 * <p>File：DealDicDubboService.java</p>
 * <p>Title: DealDicDubboService</p>
 * <p>Description:DealDicDubboService</p>
 * <p>Copyright: Copyright (c) 2015年9月6日</p>
 * <p>Company: 8637.com</p>
 * @author txsb
 * @version 1.0
 */
public interface BrandViewContactDubboService
{
    /**
     * 查询品牌商查看经销商查看记录列表
     *
     * @param page
     * @param brandViewContact
     * @return
     */
    PaginateResult<BrandViewContact>  queryBrandViewContactsList(Pagination page, BrandViewContact brandViewContact);
    
    /**
     * 新增(品牌商查看经销商联系信息记录)
     * @param brandViewContact
     * @return
     */
    BrandViewContact  addBrandViewContact(BrandViewContact brandViewContact);
}
