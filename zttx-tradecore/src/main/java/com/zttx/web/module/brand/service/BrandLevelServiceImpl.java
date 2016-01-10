/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.web.module.brand.entity.BrandesInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant.BrandUserm;
import com.zttx.web.consts.BrandConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.module.brand.entity.BrandLevel;
import com.zttx.web.module.brand.mapper.BrandLevelMapper;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.fronts.entity.HelpInfo;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.SerialnoUtils;

/**
 * 经销商等级 服务实现类
 * <p>File：BrandLevel.java </p>
 * <p>Title: BrandLevel </p>
 * <p>Description:BrandLevel </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandLevelServiceImpl extends GenericServiceApiImpl<BrandLevel> implements BrandLevelService
{
    private BrandLevelMapper   brandLevelMapper;
    
    @Autowired
    private UserInfoService    userInfoService;
    
    @Autowired
    private BrandesInfoService brandesInfoService;
    
    @Autowired
    private DealerJoinMapper   dealerJoinMapper;
    
    @Autowired(required = true)
    public BrandLevelServiceImpl(BrandLevelMapper brandLevelMapper)
    {
        super(brandLevelMapper);
        this.brandLevelMapper = brandLevelMapper;
    }
    
    @Override
    public List<BrandLevel> getBrandLevelsBy(String brandId, String brandesId)
    {
        BrandLevel params = new BrandLevel();
        params.setBrandId(brandId);
        params.setBrandsId(brandesId);
        return brandLevelMapper.findList(params);
    }
    
    @Override
    public String getBrandParentRefrenceId(String uuid)
    {
        String parentId = "";
        if (StringUtils.isNotBlank(uuid))
        {
            UserInfo userInfo = userInfoService.selectByPrimaryKey(uuid);
            if (null == userInfo) { return null; }
            parentId = userInfo.getRefrenceId();
            /**
             * 父亲账号不为空
             */
            if (userInfo.getParentId() != null)
            {
                parentId = userInfo.getParentId();
            }
        }
        return parentId;
    }
    
    @Override
    public void addBrandLevel(BrandLevel brandLevel) throws BusinessException
    {
        // 检查品牌
        Short[] brandStates = {BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED};
        // 判断当前状态是不是通进行操作
        brandesInfoService.validatorState(brandLevel.getBrandId(), brandLevel.getBrandsId(), brandStates);
        if (this.isExistName(brandLevel.getBrandId(), brandLevel.getBrandsId(), brandLevel.getLevelName())) { throw new BusinessException(BrandConst.LEVEL_NAME_REPEAT); }
        brandLevel.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        brandLevel.setCreateTime(CalendarUtils.getCurrentLong());
        brandLevel.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
        insert(brandLevel);
    }
    
    @Override
    public boolean isExistName(String brandId, String brandsId, String levelName)
    {
        BrandLevel param = new BrandLevel();
        param.setLevelName(levelName);
        param.setBrandId(brandId);
        param.setBrandsId(brandsId);
        List<BrandLevel> brandLevelList = findList(param);
        if (CollectionUtils.isEmpty(brandLevelList))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    @Override
    public boolean isExistName(String brandId, String brandsId, String levelName, String refrenceId)
    {
        BrandLevel param = new BrandLevel();
        param.setLevelName(levelName);
        param.setBrandId(brandId);
        param.setBrandsId(brandsId);
        param.setRefrenceId(refrenceId);
        List<BrandLevel> brandLevelList = findList(param);
        if (CollectionUtils.isEmpty(brandLevelList))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    @Override
    public PaginateResult<BrandLevel> getBrandLevelsBy(String brandId, String brandsId, Pagination page)
    {
        List<BrandLevel> brandLevelList = brandLevelMapper.getBrandLevelsBy(brandId, brandsId, page);
        return new PaginateResult<>(page, brandLevelList);
    }
    
    @Override
    public void delBrandLevelById(String brandId, String refrenceId) throws BusinessException
    {
        BrandLevel param = new BrandLevel();
        param.setRefrenceId(refrenceId);
        param.setBrandId(brandId);
        List<BrandLevel> _brandLevel = brandLevelMapper.findList(param);
        if (CollectionUtils.isEmpty(_brandLevel)) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        // 去除经销商等级
        DealerJoin filter = new DealerJoin();
        filter.setBrandId(brandId);
        filter.setLevelId(refrenceId);
        List<DealerJoin> dealerJoinList = dealerJoinMapper.findList(filter);
        for (DealerJoin dealerJoin : dealerJoinList)
        {
            dealerJoin.setLevelId(null);
            dealerJoinMapper.updateByPrimaryKeySelective(dealerJoin);
        }
        // 删除等级
        this.delete(refrenceId);
    }
    
    @Override
    public void modifyBrandLevel(BrandLevel brandLevel) throws BusinessException
    {
        // 检查品牌
        Short[] brandStates = {BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED};
        // 判断当前状态是不是通进行操作
        brandesInfoService.validatorState(brandLevel.getBrandId(), brandLevel.getBrandsId(), brandStates);
        // 检查重名
        if (this.isExistName(brandLevel.getBrandId(), brandLevel.getBrandsId(), brandLevel.getLevelName(), brandLevel.getRefrenceId())) { throw new BusinessException(
                BrandConst.LEVEL_NAME_REPEAT); }
        BrandLevel param = new BrandLevel();
        param.setBrandId(brandLevel.getBrandId());
        param.setRefrenceId(brandLevel.getRefrenceId());
        List<BrandLevel> _brandLevelList = this.findList(param);
        if (CollectionUtils.isEmpty(_brandLevelList)) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        BrandLevel _brandLevel = _brandLevelList.get(0);
        _brandLevel.setBrandsId(brandLevel.getBrandsId());
        _brandLevel.setLevelName(brandLevel.getLevelName());
        _brandLevel.setLevelMark(brandLevel.getLevelMark());
        this.updateByPrimaryKeySelective(_brandLevel);
    }


    /**
     * 查询品牌商所有等级信息
     *
     * @param list
     * @throws BusinessException
     */
    @Override
    public void fillBrands(List<BrandLevel> list) throws BusinessException
    {
        for (BrandLevel brandLevel : list)
        {
            this.fillBrands(brandLevel);
        }
    }


    /**
     * 查询品牌商所有等级信息
     *
     * @param brandLevel
     * @throws BusinessException
     */
    @Override
    public void fillBrands(BrandLevel brandLevel) throws BusinessException
    {
        BrandesInfo brandesInfo = this.brandesInfoService.selectByPrimaryKey(brandLevel.getBrandsId());
        if (null == brandesInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        brandLevel.setBrandesInfo(brandesInfo);
    }
}
