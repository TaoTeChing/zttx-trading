/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandDocument;
import com.zttx.web.module.brand.model.BrandDocumentModel;

/**
 * 品牌商资料信息 持久层接口
 * <p>File：BrandDocumentDao.java </p>
 * <p>Title: BrandDocumentDao </p>
 * <p>Description:BrandDocumentDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandDocumentMapper extends GenericMapper<BrandDocument>
{
	/**
	 * 分页获取品牌商资料
	 * @author 陈建平
	 * @param brandDocument
	 * @return
	 */
	List<BrandDocumentModel> findBrandDocumentModelList(BrandDocument brandDocument);
	
	/**
	 * 获取经绡商能查看的品牌商资料列表
	 * @author 陈建平
	 * @param brandDocument
	 * @return
	 */
	List<BrandDocument> getBrandDocumentListByDealerId(BrandDocumentModel brandDocument);
	
	/**
     * 修改下载次数
     * @author 陈建平
     * @param refrenceId
     */
    void updateBrandDocumentdownNum(String refrenceId);
}
