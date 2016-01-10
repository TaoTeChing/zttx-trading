/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.NetworkUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.module.brand.entity.BrandDocopen;
import com.zttx.web.module.brand.entity.BrandDocument;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandDocumentMapper;
import com.zttx.web.module.brand.model.BrandDocumentModel;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;

/**
 * 品牌商资料信息 服务实现类
 * <p>File：BrandDocument.java </p>
 * <p>Title: BrandDocument </p>
 * <p>Description:BrandDocument </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandDocumentServiceImpl extends GenericServiceApiImpl<BrandDocument> implements BrandDocumentService
{
	@Autowired
	private BrandDocopenService brandDocopenService;
	
	@Autowired
	private BrandesInfoService  brandesInfoService;
	
    private BrandDocumentMapper brandDocumentMapper;

    @Autowired(required = true)
    public BrandDocumentServiceImpl(BrandDocumentMapper brandDocumentMapper)
    {
        super(brandDocumentMapper);
        this.brandDocumentMapper = brandDocumentMapper;
    }
    
    /**
     * 保存品牌商资料
     * @author 陈建平
     * @param brandDocument
     * @throws BusinessException
     */
    @Override
    public void insertDocument(BrandDocumentModel brandDocument) throws BusinessException
    {
        brandDocument.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandDocument.setDomainName(NetworkUtils.getDoMainName());
        brandDocument.setCreateTime(CalendarUtils.getCurrentLong());
        brandDocument.setDownNum(0);
        if (!StringUtils.isBlank(brandDocument.getDocnFile()))
        {
            String filePath = FileClientUtil.moveAndDeleteFile(ImageConst.BRAND_DOC_PATH, brandDocument.getDocnFile(), "");
            brandDocument.setDocnFile(filePath);
        }
        brandDocumentMapper.insert(brandDocument);
        updateDocopen(brandDocument);
    }
    
    /**
     * 获取品牌商资料
     * @author 陈建平
     * @param brandId
     * @param refrenceId
     * @return
     */
    @Override
    public BrandDocument getBrandDocument(String brandId, String refrenceId)
    {
    	BrandDocument filter = new BrandDocument();
    	filter.setBrandId(brandId);
    	filter.setRefrenceId(refrenceId);
        List<BrandDocument> documentList = brandDocumentMapper.findList(filter);
        if (null != documentList && !documentList.isEmpty()) { return documentList.get(0); }
        return null;
    }
    
    /**
     * 删除品牌商资料（物理删除）
     * @author 陈建平
     * @param brandId
     * @param refrenceId
     * @throws BusinessException
     */
    @Override
    public void deleteDocument(String brandId, String refrenceId) throws BusinessException
    {
        BrandDocument brandDocument = getBrandDocument(brandId, refrenceId);
        if (null == brandDocument) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        brandDocopenService.deleteDocopen(brandId, refrenceId);
        brandDocumentMapper.delete(refrenceId);
    }
    
    /**
     * 分页获取品牌商资料
     * @author 陈建平
     * @param page
     * @param refrenceId
     * @return
     */
    @Override
    public PaginateResult<BrandDocumentModel> getBrandDocumentList(Pagination page, String brandId)
    {
    	BrandDocument filter = new BrandDocument();
    	filter.setBrandId(brandId);
    	filter.setPage(page);
        PaginateResult<BrandDocumentModel> paginateResult = new PaginateResult<>(page, brandDocumentMapper.findBrandDocumentModelList(filter));
        List<BrandDocumentModel> result = paginateResult.getList();
        for (BrandDocumentModel brandDocumentModel : result)
        {
            BrandesInfo brandInfo = brandesInfoService.selectByPrimaryKey(brandDocumentModel.getBrandsId());
            brandDocumentModel.setBrandsName(brandInfo.getBrandsName());
        }
        return paginateResult;
    }
    
    /**
     * 修改品牌资料
     * @author 陈建平
     * @param brandDocument
     * @throws BusinessException
     */
    @Override
    public void updateDocument(BrandDocumentModel brandDocument) throws BusinessException
    {
        BrandDocument oldBrandDocument = getBrandDocument(brandDocument.getBrandId(), brandDocument.getRefrenceId());
        if (null == oldBrandDocument) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        oldBrandDocument.setBrandsId(brandDocument.getBrandsId());
        oldBrandDocument.setCateId(brandDocument.getCateId());
        oldBrandDocument.setDocName(brandDocument.getDocName());
        oldBrandDocument.setDocoFile(brandDocument.getDocoFile());
        setDocFile(brandDocument, oldBrandDocument);
        oldBrandDocument.setWebAddress(brandDocument.getWebAddress());
        oldBrandDocument.setDocPass(brandDocument.getDocPass());
        oldBrandDocument.setDocMark(brandDocument.getDocMark());
        oldBrandDocument.setDocOpen(brandDocument.getDocOpen());
        brandDocumentMapper.updateByPrimaryKey(oldBrandDocument);
        updateDocopen(brandDocument);
    }
    
    /**
     * 获取经绡商能查看的品牌商资料列表
     * @author 陈建平
     * @param page
     * @param dealerId
     * @param brandDocument
     * @return
     */
    @Override
    public PaginateResult<BrandDocument> getBrandDocumentListByDealerId(Pagination page, String dealerId, BrandDocument brandDocument)
    {
    	BrandDocumentModel filter = new BrandDocumentModel();
    	filter.setBrandsId(brandDocument.getBrandsId());
    	filter.setDealerId(dealerId);
    	filter.setCateId(brandDocument.getCateId());
    	filter.setPage(page);
        PaginateResult<BrandDocument> paginateResult = new PaginateResult<>(page, brandDocumentMapper.getBrandDocumentListByDealerId(filter));
        return paginateResult;
    }
    
    /**
     * 修改下载次数
     * @author 陈建平
     * @param refrenceId
     */
    @Override
    public void updateBrandDocumentdownNum(String refrenceId)
    {
    	brandDocumentMapper.updateBrandDocumentdownNum(refrenceId);
    }
    
    private void setDocFile(BrandDocument brandDocument, BrandDocument oldBrandDocument) throws BusinessException
    {
        String docFile = brandDocument.getDocnFile();
        String tmpDocFile = oldBrandDocument.getDocnFile();
        try
        {
            if (StringUtils.isBlank(docFile))
            {
                if (!StringUtils.isBlank(tmpDocFile))
                {
                    oldBrandDocument.setDocnFile(null);
                }
            }
            else
            {
                if (!StringUtils.isBlank(tmpDocFile))
                {
                    if (docFile.equals(tmpDocFile))
                    {
                    	return;
                    }
                }
                // 移动图片
                String filePath = FileClientUtil.moveAndDeleteFile(ImageConst.BRAND_DOC_PATH, docFile, "");
                oldBrandDocument.setDocnFile(filePath);
            }
        }
        catch (Exception e)
        {
            throw new BusinessException(CommonConst.IMG_SAVE_FAULT);
        }
    }
    
    private void updateDocopen(BrandDocumentModel brandDocument)
    {
        if (brandDocument.getDocOpen())
        {
        	brandDocopenService.deleteDocopen(brandDocument.getBrandId(), brandDocument.getRefrenceId());
        }
        else
        {
        	BrandDocopen filter = new BrandDocopen();
        	filter.setDocId(brandDocument.getRefrenceId());
            List<BrandDocopen> docOpenList = brandDocopenService.findList(filter);
            List<String> delDocOpenList = Lists.newArrayList();
            if (null != docOpenList && !docOpenList.isEmpty())
            {
                for (BrandDocopen docOpen : docOpenList)
                {
                    if (ArrayUtils.isNotEmpty(brandDocument.getDealerIds()))
                    {
                        int size = com.zttx.web.utils.StringUtils.strArraySearch(brandDocument.getDealerIds(), docOpen.getDealerId());
                        if (size >= 0)
                        {
                            brandDocument.getDealerIds()[size] = "";
                        }
                        else
                        {
                            delDocOpenList.add(docOpen.getRefrenceId());
                        }
                    }
                    else
                    {
                        delDocOpenList.add(docOpen.getRefrenceId());
                    }
                }
            }
            brandDocopenService.deleteBatch(delDocOpenList);
            saveBrandDocopenList(brandDocument);
        }
    }
    
    private void saveBrandDocopenList(BrandDocumentModel brandDocument)
    {
        if (ArrayUtils.isNotEmpty(brandDocument.getDealerIds()))
        {
            for (int i = 0; i < brandDocument.getDealerIds().length; i++)
            {
                String docId = brandDocument.getDealerIds()[i];
                if (StringUtils.isNotBlank(docId))
                {
                    BrandDocopen brandDocopen = new BrandDocopen();
                    brandDocopen.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    brandDocopen.setBrandId(brandDocument.getBrandId());
                    brandDocopen.setBrandsId(brandDocument.getBrandsId());
                    brandDocopen.setCreateTime(CalendarUtils.getCurrentLong());
                    brandDocopen.setUpdateTime(CalendarUtils.getCurrentLong());
                    brandDocopen.setDealerId(docId);
                    brandDocopen.setDocId(brandDocument.getRefrenceId());
                    brandDocopenService.insert(brandDocopen);
                }
            }
        }
    }
}
