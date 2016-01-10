package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.model.ProductFilter;

import java.util.List;
import java.util.Map;

/**
 * <p>File:DealerGrantService</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/8/17 20:02</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */

public interface DealerGrantService{
    /**
     * 分页查询 经销商授权产品
     * @param pagination
     * @param filter
     * @return
     * @author 易永耀
     */
    PaginateResult<Map<String,Object>> selectGrantProductPage(Pagination pagination, ProductFilter filter) throws BusinessException;

    /**
     * 经销商 授权产品库  品牌类目查询
     * @param dealerId
     * @return
     */
    List<Map<String,Object>> selectGrantCata(String dealerId);


}
