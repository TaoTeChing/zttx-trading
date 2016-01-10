/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.ArticleInfo;
import com.zttx.web.module.fronts.model.ArticleFilter;

/**
 * 资讯文章信息 服务接口
 * <p>File：ArticleInfoService.java </p>
 * <p>Title: ArticleInfoService </p>
 * <p>Description:ArticleInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ArticleInfoService extends GenericServiceApi<ArticleInfo>
{
    /**
     * @param page 分页条件
     * @param articleInfoFilter 条件过滤对象
     * @param orderCriteria 分页条件
     * @return 分页数据
     * @author 章旭楠
     */
    PaginateResult<ArticleInfo> search(Pagination page, ArticleInfo articleInfoFilter, ArticleInfo.OrderCriteria orderCriteria);
    
    /**
     *  查询资讯索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param articleInfo
     * @return  {@link Long}
     * @throws BusinessException
     */
    Long findArticleToSolrCount(ArticleInfo articleInfo) throws BusinessException;
    
    /**
     * 查询资讯索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param articleInfo
     * @param pagination
     * @return {@link List}
     * @throws BusinessException
     */
    List<ArticleInfo> findArticleToSolr(ArticleInfo articleInfo, Pagination pagination) throws BusinessException;

    /**
     * 该方法作用是 分页查询出一些简单的信息
     * @param cateId 类型
     * @param pagination 分页
     * @param hasImg 是否需要图片
     * @return
     */
    PaginateResult<Map<String, Object>> findSimplyInfo(String cateId, Pagination pagination, Boolean hasImg);
    
    /**
     * 搜索文章信息
     * @param articleFilter
     * @param pagination
     * @return
     */
    PaginateResult<Map<String, Object>> searchArticleInfoList(ArticleFilter articleFilter, Pagination pagination);
    
    /**
     * 该方法作用是查询所有的文章信息通过文章分类来查询
     *     在以前版本该方法只是二级分类,现在的实现是先查出该分类下面的所有的分类信息,然后再查询所有分类信息的文章
     * @param cateId
     * @param pagination
     * @return
     */
    PaginateResult<ArticleInfo> findBy(String cateId, Pagination pagination);
    
    /**
     * 通过类别id来查询所有文章信息分页显示
     * @param type
     * @param pagination
     * @return
     */
    PaginateResult<ArticleInfo> findBy(Integer type, Pagination pagination);
    
    /**
     * 该方法作用是 查询前六变文章信息
     * @param type
     * @param pagination
     * @return
     */
    PaginateResult<ArticleInfo> findPrvSix(Integer type, Pagination pagination);
    
    /**
     * 加载最新的信息
     * @param pagination
     * @return
     */
    PaginateResult<Map<String, Object>> loadLatestNews(Pagination pagination);
    
    /**
     * 按照文章分类查找文章，返回的字段如下
     *   必须带图
     * @param cateId
     * @param pagination
     * @return
     */
    PaginateResult findSimpleByCate(String cateId, Pagination pagination);
    
    /**
     *  查询最热门的资讯,type为资讯类型，分页显示
     * @param type
     * @param pagination
     * @return
     */
    PaginateResult findHotBy(Integer type, Pagination pagination);
    
    /**
     * 通过类别id分页查询所有的文章信息
     * @param cateId
     * @param pagination
     * @return
     */
    PaginateResult<ArticleInfo> findAllBy(String cateId, Pagination pagination);
    
    /**
     * 保存(支撑接口调用)
     * @param articleInfo 对象
     * @throws BusinessException
     */
    ArticleInfo saveByClient(ArticleInfo articleInfo) throws BusinessException;
}
