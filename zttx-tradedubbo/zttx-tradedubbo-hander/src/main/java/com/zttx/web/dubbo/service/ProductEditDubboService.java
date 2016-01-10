/*
 * @(#)ProductEditDubboService.java 2015-9-23 下午3:17:54
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductEditAuditLog;
import com.zttx.web.module.common.entity.ProductEditDetail;

/**
 * <p>File：ProductEditDubboService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-23 下午3:17:54</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public interface ProductEditDubboService
{
    /**
     * 获取产品产品审核信息
     * 参数
     * state:0未审核，1已审核
     * info.brandsName:品牌名称
     * info.productNo:产品货号
     * info.pagination：分页
     * @param state
     * @param info
     * @return
     * @throws BusinessException
     */
    JsonMessage proList(Integer state, ProductBaseInfo  info) throws BusinessException;
    /**
     * 根据审核状态和产品id查找审核详情
     * @param editDetail
     * @return
     * @throws BusinessException
     */
    JsonMessage editList(ProductEditDetail editDetail) throws BusinessException;
    /**
     * 根据产品id查找审核日志
     * @param log
     * @return
     * @throws BusinessException
     */
    JsonMessage logList(ProductEditAuditLog log) throws BusinessException;
    /**
     * 审核通过或不通过
     * @param editId
     * @param isPass
     * @param result
     * @param operateId
     * @param operateName
     * @return
     * @throws BusinessException
     */
    JsonMessage audit(String editId,Integer isPass,String result,String operateId,String operateName) throws BusinessException;
}
