/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.ArticleInfo;

/**
 * 资讯文章信息 持久层接口
 * <p>File：ArticleInfoDao.java </p>
 * <p>Title: ArticleInfoDao </p>
 * <p>Description:ArticleInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ArticleInfoMapper extends GenericMapper<ArticleInfo>
{
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
    List<ArticleInfo> findArticleToSolr(@Param("articleInfo") ArticleInfo articleInfo, @Param("page") Pagination pagination) throws BusinessException;
    
    /**
     * 查询的sql语句
     * ========================================================================
     * select  count(DISTINCT a.refrenceId) from  ArticleInfo a, 
     * ArticleCate o where a.delState = 0 and (o.refrenceId = ? or o.parentId = ? )
     *  and (o.refrenceId=a.cateId or o.parentId = a.cateId) order by a.isComment  desc , a.createTime  desc;
     *  =========================================================================
     *  查询文章的数量，条件是文章没有被删除，文章和文章类别有关联关系,以评论和时间降序排列
     * @param cateId
     * @return
     */
    Long count(String cateId);

    /**
     * 查询所有类别和文章类容管理的类别和类容信息按照时间降序排列
     * @param cateId
     * @param pagination
     * @param hasImg 是否带图片
     * @return
     */
    List<ArticleInfo> list(@Param("cateId") String cateId, @Param("page") Pagination pagination, @Param("hasImg") Boolean hasImg);
    
    /**
     * 查询最新的新闻信息,分页显示
     * @param articleInfo
     * @param pagination
     * @return {@link List}
     * @throws BusinessException
     */
    List<Map<String, Object>> loadLatestNews(@Param("page") Pagination pagination);
    
    /**
     * 该方法作用是 查询文章的热点信息
     * @author txsb
     * @param map
     * @param pagination
     * @return
     */
    List<Map<String, Object>> findHotBy(@Param("type") int type, @Param("page") Pagination pagination);
    
    /**
     * 查询图片为非空的文章信息,通过类型来查询，类型分别为1，2，3，4，热门，头条信
     * @param type
     * @param pagination
     * @return
     */
    List<ArticleInfo> findBy(@Param("type") int type, @Param("page") Pagination pagination);
    
    /**
     * 通过浏览的次数从高到低来排序
     * 通过有图的文章信息放在最前面
     * @param pagination
     * @return
     */
    List<ArticleInfo> findOrder(@Param("page") Pagination pagination);
    
    /**
     * 通过类别id来查询文章信息，
     * 1.如果传入的类别是cateId 是 All这个字符串
     * 表示查询所有的文章信息且以文章图片非空的往前排序，将创建时间前的放在前面
     * 2.如果传入的类别是cateId 是 hot这个字符串
     * 表示查询所有的文章信息且以文章图片非空的往前排序，将创建时间前的放在前面，查询热点信息
     * 3.其他的话表示查询类别id来查询
     * @param cateId
     * @param pagination
     * @return
     */
    List<ArticleInfo> findByCateId(@Param("cateId") String cateId, @Param("page") Pagination pagination);
    
    /**
     * 分页查询
     * @param articleInfoFilter 过滤
     * @param pagination 分页
     * @param orderCriteria 排序
     * @return  分页结果集
     */
    List<ArticleInfo> search(@Param("articleInfoFilter") ArticleInfo articleInfoFilter, @Param("page") Pagination pagination,
            @Param("orderCriteria") ArticleInfo.OrderCriteria orderCriteria);
    
    /**
     * 统计该类型文章数量
     * @param cateId 类型id
     * @return 数量
     * @author 章旭楠
     */
    int countArticleNum(String cateId);
}
