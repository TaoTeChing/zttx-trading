package com.zttx.web.module.brand.service;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.model.BrandesAuthUserModel;
import com.zttx.web.security.shiro.model.UserPrincipal;

import java.util.Map;

/**
 * <p>File:BrandesAuthUserService</p>
 * <p>Title: </p>
 * <p>Description: 品牌授权用户价格控制</p>
 * <p>Copyright: Copyright (c)2015/9/6 9:22</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public interface BrandesAuthUserService
{
    /**
     * 根据 用户id,产品id 获取产品价格
     * @param userId
     * @param productId
     * @param state
     * @return {@link BrandesAuthUserModel}
     * @throws BusinessException
     * @author 易永耀
     */
    BrandesAuthUserModel getAuthPrice(String userId, String productId, String state) throws BusinessException;
    
    /**
     * 根据 用户id,产品id 获取产品价格
     * @param principal 用户编号
     * @param productMap 搜索引擎中的产品对象
     * @param state 状态
     * @return {@link BrandesAuthUserModel}
     * @throws BusinessException
     * @author 易永耀
     */
    BrandesAuthUserModel getAuthPrice(UserPrincipal principal, Map<String, Object> productMap, String state) throws BusinessException;
}
