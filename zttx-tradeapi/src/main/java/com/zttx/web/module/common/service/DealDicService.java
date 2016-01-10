/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.DealDic;

/**
 * 品牌经营品类信息 服务接口
 * <p>File：DealDicService.java </p>
 * <p>Title: DealDicService </p>
 * <p>Description:DealDicService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealDicService extends GenericServiceApi<DealDic>
{
    /**
     * 取产品顶级类别
     *
     * @return {@link java.util.List}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    List<DealDic> getTopProductDealDics() throws BusinessException;
    
    /**
     * 根据类目编号取上级类目
     * <p>
     *     采用递归的方式获取所有上级类目
     * </p>
     * @param dealNo
     * @return
     */
    List<DealDic> getParentDealDics(Integer dealNo);
    
    /**
     * 根据字典编码取下级与产品有关的字典
     *
     * @param dealNo
     * @return {@link java.util.List}
     * @throws BusinessException
     */
    List<DealDic> getProductDealDics(Integer dealNo) throws BusinessException;
    
    /**
     * 查询指定 parentId下的 经营品牌
     *
     * @param parentId
     * @return {@link java.util.List}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    List<DealDic> getDealDicsBy(String parentId) throws BusinessException;
    
    /**
     * 优先从缓存中查询指定 parentId下的 经营品牌
     *
     * @param parentId
     * @return
     * @author 吴万杰
     */
    List<DealDic> listFormCache(String parentId) throws BusinessException;
    
    /**
     * 根据dealNo获取类目
     *
     * @param dealNo
     * @return
     */
    DealDic getDealDicByDealNo(Integer dealNo);

    /**
     * 根据一组类目编号获取类目
     *
     * @param dealNos
     * @return
     */
    List<DealDic> getDealDicByDealNos(List<Integer> dealNos);
    
    /**
     * 根据目录品类级别查询记录
     *
     * @param level
     * @return
     * @author 吴万杰
     */
    List<DealDic> getDealDicByLevel(Short level) throws BusinessException;
    
    /**
     * 首页导航分类 只取大类
     *
     * @param page    大分类取的分页
     * @param subpage 小分类取的分页
     * @return
     * @author 鲍建明
     */
    List<DealDic> getDealDicOrderByField(Pagination page, Pagination subpage);
    
    /**
     * 把所有类目转化为JSON
     *
     * @author 张昌苗
     */
    String getDealDicJson() throws BusinessException;
    
    /**
     * 设置用户选择类目的历史记录缓存集合
     * 暂不支持缓存
     * @param brandId 品牌商编号
     * @param dealNo  栏目编号
     * @author 施建波
     */
    void setBrandDicList(String brandId, Integer dealNo);
    
    /**
     * 获取用户选择类目的历史记录缓存集合
     * 暂不支持缓存
     * @param brandId 品牌商编号
     * @return
     * @author 施建波
     */
    List<DealDic> getBrandDicList(String brandId);
    
    /**
     * 第三方保存：refrenceId（null：新增，非null：修改）
     *
     * @param dealDic refrenceId（String）主键
     *                dealName（String）品类名称		（必填）
     *                dealIcon（String）品类图标		（必填）
     *                dealOrder（Integer）排序编号	（必填）
     *                parentId（String）父级类目主键（null：一级类目，非null：指定类目的子类目）
     * @return
     * @author 周光暖
     */
    void saveByClient(DealDic dealDic) throws BusinessException;
    
    /**
     * 第三方删除
     *
     * @param refrenceId
     * @author 周光暖
     */
    void deleteByClient(String refrenceId) throws BusinessException;
    
    /**
     * 列表
     *
     * @param page
     * @param searchBean 过滤条件（dealName 模糊查询、 dealLevel）
     * @return
     * @author 周光暖
     */
    PaginateResult<DealDic> searchByClient(Pagination page, DealDic searchBean);
    
    /**
     * 通过ID来查询parentId
     *
     * @param refrenceId
     * @return DealDic(String parentId, String dealName)
     * @author 周光暖
     */
    DealDic findParentIdByRefrenceId(String refrenceId);
    
    /**
     * 通过ID来查询
     *
     * @param refrenceId
     * @return 全的DealDic
     * @author 周光暖
     */
    DealDic findByRefrenceId(String refrenceId);
    
    /**
     * 获取类目分页结果集
     *
     * @param dealDic
     * @param page
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    PaginateResult<DealDic> listDealDics(DealDic dealDic, Pagination page) throws BusinessException;

}
