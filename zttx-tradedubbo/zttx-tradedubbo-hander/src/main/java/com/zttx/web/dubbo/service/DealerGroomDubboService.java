/*
 * Copyright 2015 Playguy, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerGroom;
/**
 * <p>File：DealerGroomDubboService.java</p>
 * <p>Title: DealerGroomDubboService</p>
 * <p>Description:DealerGroomDubboService</p>
 * <p>Copyright: Copyright (c) 2015年9月6日</p>
 * <p>Company: 8637.com</p>
 * @author txsb
 * @version 1.0
 */
public interface DealerGroomDubboService {
    
    
    public PaginateResult<DealerGroom>  queryDealerGrooms( DealerGroom  dealerGroom , Pagination pagination) 
            throws BusinessException;
}
