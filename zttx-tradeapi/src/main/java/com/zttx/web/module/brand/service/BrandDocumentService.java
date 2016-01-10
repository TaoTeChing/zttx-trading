/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandDocument;
import com.zttx.web.module.brand.model.BrandDocumentModel;
/**
 * 品牌商资料信息 服务接口
 * <p>File：BrandDocumentService.java </p>
 * <p>Title: BrandDocumentService </p>
 * <p>Description:BrandDocumentService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandDocumentService extends GenericServiceApi<BrandDocument>{

	/**
     * 保存品牌商资料
     * @author 陈建平
     * @param brandDocument
     * @throws BusinessException
     */
    void insertDocument(BrandDocumentModel brandDocument) throws BusinessException;
    
    /**
     * 获取品牌商资料
     * @author 陈建平
     * @param brandId
     * @param refrenceId
     * @return
     */
    BrandDocument getBrandDocument(String brandId, String refrenceId);
    
    /**
     * 删除品牌商资料（物理删除）
     * @author 陈建平
     * @param brandId
     * @param refrenceId
     * @throws BusinessException
     */
    void deleteDocument(String brandId, String refrenceId) throws BusinessException;
    
    /**
     * 分页获取品牌商资料
     * @author 陈建平
     * @param page
     * @param refrenceId
     * @return
     */
    PaginateResult<BrandDocumentModel> getBrandDocumentList(Pagination page, String brandId);



    
    /**
     * 修改品牌资料
     * @author 陈建平
     * @param brandDocument
     * @throws BusinessException
     */
    void updateDocument(BrandDocumentModel brandDocument) throws BusinessException;
    
    /**
     * 获取经绡商能查看的品牌商资料列表
     * @author 陈建平
     * @param page
     * @param dealerId
     * @param brandDocument
     * @return
     */
    PaginateResult<BrandDocument> getBrandDocumentListByDealerId(Pagination page, String dealerId, BrandDocument brandDocument);
    
    /**
     * 修改下载次数
     * @author 陈建平
     * @param refrenceId
     */
    void updateBrandDocumentdownNum(String refrenceId);
}
