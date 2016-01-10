package com.zttx.web.dubbo.service;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.model.BrandesAuthUserModel;
import com.zttx.web.module.brand.service.BrandesAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>File：BrandesAuthUserDubboServiceImpl.java </p>
 * <p>Title: BrandesAuthUserDubboServiceImpl </p>
 * <p>Description: BrandesAuthUserDubboServiceImpl </p>
 * <p>Copyright: Copyright (c) 15/9/16</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class BrandesAuthUserDubboServiceImpl implements BrandesAuthUserDubboService {

    @Autowired
    private BrandesAuthUserService brandesAuthUserService;

    @Override
    public BrandesAuthUserModel getAuthPrice(String userId, String productId, String state) throws BusinessException {
        return brandesAuthUserService.getAuthPrice(userId, productId, state);
    }
}
