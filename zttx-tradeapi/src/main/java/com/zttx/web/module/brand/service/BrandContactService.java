/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandContact;


/**
 * 品牌商联系方式 服务接口
 * <p>File：BrandContactService.java </p>
 * <p>Title: BrandContactService </p>
 * <p>Description:BrandContactService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandContactService extends GenericServiceApi<BrandContact>{

    /**
     * 根据品牌商编号获取品牌商联系方式
     * @param brandId
     * @return
     */
    BrandContact findByBrandId(String brandId);

    /**
     * 更新用户联系方式
     * @param brandContact
     * @param old
     * @return
     */
    BrandContact updateFile(BrandContact brandContact, BrandContact old) throws BusinessException;

    /**
     * 保存或更新客户联系方式
     * @param newContact
     * @param oldContact
     * @return
     * @throws BusinessException
     */
    BrandContact save(BrandContact newContact, BrandContact oldContact) throws BusinessException;

    /**
     * 更新头像
     * @param userPhoto
     * @param oldContact
     * @throws BusinessException
     */
    void updatePhote(String userPhoto, BrandContact oldContact) throws BusinessException;
}
