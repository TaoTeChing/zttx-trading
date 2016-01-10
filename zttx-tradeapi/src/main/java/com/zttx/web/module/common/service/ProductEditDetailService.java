/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductEditDetail;
/**
 * 产品修改详情 服务接口
 * <p>File：ProductEditDetailService.java </p>
 * <p>Title: ProductEditDetailService </p>
 * <p>Description:ProductEditDetailService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductEditDetailService extends GenericServiceApi<ProductEditDetail>{
    
    /**
     * 查询编缉记录
     * @param param
     * @return
     * @throws BusinessException
     */
    ProductEditDetail executeSelect(ProductEditDetail param) throws BusinessException;
    
    /**
     * 保存产品编辑记录
     * @param param
     * @author 张昌苗
     */
     ProductEditDetail executeSave(ProductEditDetail param) throws BusinessException;
     /**
      * 根据审核状态和产品id查找审核信息
      * @param editDetail
      * @return
      */
     PaginateResult<Map<String, Object>> searchEditList(ProductEditDetail editDetail);
     /**
      * 审核通过
      * @param editId
      * @param operateId
      * @param operateName
      * @throws BusinessException
      */
     void executePassAudit(String editId, String operateId, String operateName) throws BusinessException;
     /**
      * 审核不通过
      * @param editId
      * @param result
      * @param operateId
      * @param operateName
      * @throws BusinessException
      */
     void executeUnPassAudit(String editId, String result, String operateId, String operateName) throws BusinessException;
}
