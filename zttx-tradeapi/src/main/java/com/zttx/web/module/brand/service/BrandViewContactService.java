/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandViewContact;
import com.zttx.web.module.dealer.entity.DealerInfo;

/**
 * 品牌商查看经销商联系信息 服务接口
 * <p>File：BrandViewContactService.java </p>
 * <p>Title: BrandViewContactService </p>
 * <p>Description:BrandViewContactService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandViewContactService extends GenericServiceApi<BrandViewContact>{

    /*
     * 校验是否存在
     * */
    public Integer isExist(String brandId, String dealerId);

    /*
     * 修改浏览次数
     */
    public DealerInfo modifyViewContact(String brandId, String dealerId) throws BusinessException;
    
    /**
     * 检测是否是推荐或申请的终端商，如果是就加入到联系表中
     *
     * @param brandId  品牌商编号
     * @param dealerId 终端商编号
     * @param isAdd    是否新增
     * @author 李星
     */
    Boolean insertWithCheck(String brandId, String dealerId, Boolean isAdd) throws BusinessException;
    
    /**
     * 检测是否存在查看过联系方式的终端商
     *
     * @param brandId  品牌商编号
     * @param dealerId 终端商编号
     * @param viewType 来源类型
     * @return
     * @author 李星
     */
    Boolean isExist(String brandId, String dealerId, Short viewType);
}
