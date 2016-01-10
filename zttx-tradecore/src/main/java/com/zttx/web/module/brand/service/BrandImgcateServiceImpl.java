/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.BrandConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandImgcate;
import com.zttx.web.module.brand.mapper.BrandImgcateMapper;
import com.zttx.web.module.common.model.MenuTree;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商图片分类信息 服务实现类
 * <p>File：BrandImgcate.java </p>
 * <p>Title: BrandImgcate </p>
 * <p>Description:BrandImgcate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandImgcateServiceImpl extends GenericServiceApiImpl<BrandImgcate> implements BrandImgcateService
{
    private BrandImgcateMapper brandImgcateMapper;
    
    @Autowired
    private BrandImageService  brandImageService;
    
    @Autowired(required = true)
    public BrandImgcateServiceImpl(BrandImgcateMapper brandImgcateMapper)
    {
        super(brandImgcateMapper);
        this.brandImgcateMapper = brandImgcateMapper;
    }
    
    /**
     * 是否存在同名分类
     *
     * @param brandId  品牌商ID
     * @param cateName 分类名称
     * @param cateId   分类ID(null：全部，不空：除此之外)
     * @return Boolean false：不存在，true：存在
     * @author 章旭楠
     */
    @Override
    public Boolean isExistName(String brandId, String cateName, String cateId)
    {
        List list = brandImgcateMapper.isExistName(brandId, cateName, cateId);
        return list.size() > 0;
    }
    
    /**
     * 批量删除图库分类
     *
     * @param ids     要删除的ID 数组
     * @param brandId 品牌商ID
     * @author 章旭楠
     */
    @Override
    public void deleteBatch(String[] ids, String brandId) throws BusinessException
    {
        if (null == ids || ids.length == 0) { return; }
        BrandImgcate defaultCate = this.brandImgcateMapper.findDefaultImgCate(brandId);
        if (null == defaultCate)
        {
            defaultCate = this.createDefaultImgCate(brandId);
        }
        for (String id : ids)
        {
            BrandImgcate cate = brandImgcateMapper.selectByPrimaryKey(id);
            if (cate == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            brandImageService.updateImageCate(cate.getRefrenceId(), defaultCate.getRefrenceId(), defaultCate.getBrandId());
            this.deleteCascade(id, brandId);
        }
    }
    
    @Override
    public List<MenuTree> getCateMenuTree(String brandId)
    {
        List<BrandImgcate> list = this.brandImgcateMapper.findByBrandId(brandId);
        if (list == null || list.isEmpty()) { return null; }
        return MenuTree.getBrandImgcateTree(list);
    }
    
    private void add(List<BrandImgcate> cateList) throws BusinessException
    {
        if (null == cateList || cateList.isEmpty()) { return; }
        for (BrandImgcate cate : cateList)
        {
            if (this.isExistName(cate.getBrandId(), cate.getCateName(), null) || ApplicationConst.PICCATE_DEFAULT_NAME.equals(cate.getCateName())) { throw new BusinessException(
                    BrandConst.IMG_CATE_NAME_REPEAT); }
        }
        this.brandImgcateMapper.insertBatch(cateList);
    }
    
    private void update(List<BrandImgcate> cateList) throws BusinessException
    {
        if (null == cateList || cateList.isEmpty()) { return; }
        for (BrandImgcate cate : cateList)
        {

        }
        this.updateBatch(cateList);
    }

    /**
     * 批量添加或者更新图库分类
     *
     * @param cateNames
     * @param uuids
     * @param brandId
     * @param levels
     * @throws BusinessException
     */
    @Override
    public void saveOrUpdate(String[] cateNames, String[] uuids, String brandId, String[] levels) throws BusinessException
    {
        if (null != cateNames && cateNames.length > 0)
        {
            if (cateNames.length != uuids.length || cateNames.length != levels.length) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
            List<BrandImgcate> insertList = Lists.newArrayList();
            int orderIndexLevelOne = 0;
            int orderIndexLevelTwo = 0;
            String pid = null;
            for (int i = 0; i < cateNames.length; i++)
            {
                String uuid = uuids[i];
                String level = levels[i];
                boolean isLevelOne = "1".equals(level);
                if (StringUtils.isBlank(uuid))
                {// uuid为空 新增
                    uuid = SerialnoUtils.buildPrimaryKey();
                    insertList.add(createBrandImgcate(brandId, uuid, cateNames[i], isLevelOne ? null : pid, isLevelOne ? orderIndexLevelOne++ : orderIndexLevelTwo++));
                }
                else
                {
                    BrandImgcate cate = brandImgcateMapper.selectByPrimaryKey(uuid);
                    if (null == cate) continue;
                    cate.setCateName(cateNames[i]);
                    cate.setCateOrder(isLevelOne ? orderIndexLevelOne++ : orderIndexLevelTwo++);
                    cate.setUpdateTime(CalendarUtils.getCurrentLong());
                    if (this.isExistName(cate.getBrandId(), cate.getCateName(), cate.getRefrenceId())) { throw new BusinessException(BrandConst.IMG_CATE_NAME_REPEAT); }
                    brandImgcateMapper.updateByPrimaryKeySelective(cate);
                }
                pid = isLevelOne ? uuid : pid;
                orderIndexLevelTwo = isLevelOne ? 0 : orderIndexLevelTwo;//遇到等级1 的 等级2序号重置
            }
            this.add(insertList);
        }
    }
    
    private BrandImgcate createBrandImgcate(String brandId, String uuid, String cateName, String pid, int orderIndex)
    {
        BrandImgcate cate = new BrandImgcate();
        cate.setRefrenceId(uuid);
        cate.setBrandId(brandId);
        cate.setCateName(cateName);
        cate.setParentId(pid);
        cate.setCateOrder(orderIndex);
        cate.setCateDefault(BrandConstant.BrandImgcate.CATE_NONE_DEFAULT);
        cate.setCreateTime(CalendarUtils.getCurrentLong());
        cate.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
        return cate;
    }

    /**
     * 查找默认分类
     *
     * @param brandId
     * @return BrandImgcate
     */
    @Override
    public BrandImgcate findDefaultImgCate(String brandId) {
        return this.brandImgcateMapper.findDefaultImgCate(brandId);
    }

    /**
     * 创建默认分类
     *
     * @param brandId
     * @return
     */
    @Override
    public BrandImgcate createDefaultImgCate(String brandId)
    {
        BrandImgcate cate = new BrandImgcate();
        cate.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        cate.setBrandId(brandId);
        cate.setCateName(ApplicationConst.PICCATE_DEFAULT_NAME);
        cate.setParentId(null);
        cate.setCateOrder(0);
        cate.setCateDefault(BrandConstant.BrandImgcate.CATE_DEFAULT);
        cate.setCreateTime(CalendarUtils.getCurrentLong());
        cate.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
        this.brandImgcateMapper.insertSelective(cate);
        return cate;
    }
    
    /**
     * 删除分类的同时删除其子集(逻辑删除)
     *
     * @param cateId
     * @param brandId
     */
    @Override
    public void deleteCascade(String cateId, String brandId)
    {
        brandImgcateMapper.deleteCascade(cateId, brandId);
    }
}
