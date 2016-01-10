/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.RegionalCode;

/**
 * 地区编码与常规描述的转换表 服务接口
 * <p>File：RegionalCodeService.java </p>
 * <p>Title: RegionalCodeService </p>
 * <p>Description:RegionalCodeService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface RegionalCodeService extends GenericServiceApi<RegionalCode>
{
    /**
     * 保存地区码
     * @param regionalCode
     * @throws BusinessException
     */
    void saveByClient(RegionalCode regionalCode) throws BusinessException;

    /**
     * 是否存在区域编码
     * @param refrenceId
     * @param areaNos
     * @return
     */
    boolean isExistsCode(String refrenceId, String areaNos);

    /**
     * 是否存在区域名称
     * @param refrenceId
     * @param areaName
     * @return
     */
    boolean isExistsName(String refrenceId, String areaName);
    
    /**
     * 根据主键或者区域编号或者区域名称返回区域对象
     *
     * @param refrenceId
     * @param areaNos
     * @param areaName
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    RegionalCode getRegionalCode(String refrenceId, String areaNos, String areaName) throws BusinessException;
}
