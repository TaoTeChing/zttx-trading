package com.zttx.web.dubbo.service;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.model.BrandesAuthUserModel;

/**
 * <p>File：BrandesAuthUserDubboService.java </p>
 * <p>Title: BrandesAuthUserDubboService </p>
 * <p>Description: BrandesAuthUserDubboService </p>
 * <p>Copyright: Copyright (c) 15/9/16</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface BrandesAuthUserDubboService {

    /**
     * 根据 用户id,产品id 获取产品价格
     * @param userId
     * @param productId
     * @param state
     * @return
     * @throws BusinessException
     * @author 易永耀
     */
     BrandesAuthUserModel getAuthPrice(String userId,String productId,String state) throws BusinessException;
}
