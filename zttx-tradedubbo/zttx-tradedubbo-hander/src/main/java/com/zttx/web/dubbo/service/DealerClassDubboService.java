package com.zttx.web.dubbo.service;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.entity.DealerClass;
/**
 * 
 * <p>File：DealerClassDubboService.java</p>
 * <p>Title: DealerClassDubboService</p>
 * <p>Description:DealerClassDubboService</p>
 * <p>Copyright: Copyright (c) 2015年9月6日</p>
 * <p>Company: 8637.com</p>
 * @author txsb
 * @version 1.0
 */
public interface DealerClassDubboService {
       
    /**
     * 查询经营商类别信息
     * @param dealerClass
     * @param page
     * @return
     */
    PaginateResult<DealerClass>  queryDealerClassList(DealerClass dealerClass, Pagination page);
    
}
