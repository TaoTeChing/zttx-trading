package com.zttx.web.dubbo.service;
import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandesInfo;

/**
 * <p>File：BrandsDubboService.java </p>
 * <p>Title: 品牌远程服务接口 </p>
 * <p>Description: BrandsDubboService </p>
 * <p>Copyright: Copyright (c) 2014 08/19/2015 11:09</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public interface BrandsDubboService {
    /**
     * 根据品牌id获取品牌信息
     * @param brandsId
     * @return
     */
    Map<String, Object> getBrandsInfoByBrandsId(String brandsId);

    /**
     * 查询品牌商下品牌信息列表
     *
     * @param pagination
     * @param brandesInfo
     * @return
     * @throws BusinessException
     */
    public PaginateResult<BrandesInfo> queryBrandesInfosList(Pagination pagination, BrandesInfo brandesInfo) throws BusinessException;
    
}
