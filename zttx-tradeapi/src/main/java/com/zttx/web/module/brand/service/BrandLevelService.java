/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandLevel;
/**
 * 经销商等级 服务接口
 * <p>File：BrandLevelService.java </p>
 * <p>Title: BrandLevelService </p>
 * <p>Description:BrandLevelService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandLevelService extends GenericServiceApi<BrandLevel>{

    /**
     * 根据品牌商id和品牌id查询 终端商登记
     * @param brandId
     * @param id
     * @return
     */
    List<BrandLevel> getBrandLevelsBy(String brandId, String brandesId);

    /**
     * 获取当前品牌商用户的父类UUID
     * @param request
     * @return
     * @author 
     */
    String getBrandParentRefrenceId(String  branderParentId);

    /**
     * 增家品牌级别信息，确保添加的名称不重复，
     * 判断当前状态是不是通进行操作
     * @param brandId
     * @param id
     * @return
     * @throws com.zttx.sdk.exception.BusinessException 
     */
    void addBrandLevel(BrandLevel brandLevel) throws BusinessException;
    
    
   
    /**
     * 判断级别的名称是否存在
     * @param brandId
     * @param brandsId
     * @param levelName
     * @return
     */
     boolean isExistName(String brandId, String brandsId, String levelName);

    /**
     * 分页查询,通过品牌商id，和品牌id
     * @param brandId
     * @param brandsId
     * @param page
     * @return
     */
    PaginateResult<BrandLevel> getBrandLevelsBy(String brandId, String brandsId, Pagination page);

    /**
     * 删除经销商信息。 去除经销商等级和他关联的
     * @param brandId
     * @param refrenceId
     */
    void delBrandLevelById(String brandId, String refrenceId) throws BusinessException;

    /**
     * 修改品牌级别信息，中间去掉重名的品牌级别信息
     * @param brandLevel
     */
    void modifyBrandLevel(BrandLevel brandLevel)  throws BusinessException;
    
    /**
     * 更新的时候判断，通过品牌商id。品牌id，级别名称。和refeenceId来判断是不是存在
     * @param brandId
     * @param brandsId
     * @param levelName
     * @param refrenceId
     * @return
     */
    boolean isExistName(String brandId, String brandsId, String levelName, String refrenceId);


    /**
     * 查询品牌商所有等级信息
     *
     * @param list
     * @throws BusinessException
     */
    public void fillBrands(List<BrandLevel> list) throws BusinessException;

    /**
     * 查询品牌商所有等级信息
     *
     * @param brandLevel
     * @throws BusinessException
     */
    public void fillBrands(BrandLevel brandLevel) throws BusinessException;
}
