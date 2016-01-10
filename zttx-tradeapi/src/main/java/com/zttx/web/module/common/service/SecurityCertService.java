/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.SecurityCert;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * 经销商/品牌商申请更改手机认证 服务接口
 * <p>File：SecurityCertService.java </p>
 * <p>Title: SecurityCertService </p>
 * <p>Description:SecurityCertService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface SecurityCertService extends GenericServiceApi<SecurityCert>{

    /**
     * 根据用户ID和审核状态查询
     * @param userIdId
     * @param actState
     * @return
     */
    SecurityCert findSecurityCert(String userIdId, Short actState);

    SecurityCert save(SecurityCert securityCert, UserPrincipal brandUserm) throws BusinessException;

    /**
     * 分页查询 （支撑接口调用）
     * @param searchBean
     * @param page
     * @return
     */
    PaginateResult<SecurityCert> searchByClient(SecurityCert searchBean, Pagination page);

    /**
     * 更改处理结果，便根据申请类型做相应处理
     *（暂时只实现更换手机号码的申请处理）
     * applyType 申请类型：（1:更换手机号码）
     * actState	 操作结果：（0：未处理，1：处理）
     * @param securityCert 对象
     * @throws BusinessException
     */
    void updateActState(SecurityCert securityCert) throws BusinessException;
}
