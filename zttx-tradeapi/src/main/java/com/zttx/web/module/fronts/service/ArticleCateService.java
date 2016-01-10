/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;
import java.util.TreeSet;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.ArticleCate;

/**
 * 网站资讯类别 服务接口
 * <p>File：ArticleCateService.java </p>
 * <p>Title: ArticleCateService </p>
 * <p>Description:ArticleCateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ArticleCateService extends GenericServiceApi<ArticleCate>
{
    /**
     * 根据分类ID取上级分类
     * <p>
     *     采用递归方式取上级分类
     * </p>
     * @param cateId
     * @return
     */
    List<ArticleCate> getParentArticleCates(String cateId);
    
    /**
     * 返回带层级的资讯分类
     * @return
     */
    List<ArticleCate> getAllCates();
    
    /**
     *  查询文章分类信息通过父亲文章id下面的所有的子类信息
     * @param parendId
     * @return
     */
    List<ArticleCate> findBy(String parendId);
    
    /**
     * 保存咨询类目 （支撑接口调用）
     * @param articleCate
     * @throws BusinessException
     */
    void saveByClient(ArticleCate articleCate) throws BusinessException;
    
    /**
     * 更新文章数量
     * @param refrenceId 主键
     * @param roll
     *      数量 2：加2  /  -1:减1
     */
    void updateArticleNum(String refrenceId, int roll);
    
    /**
     * 树形查询
     * @return
     */
    TreeSet<ArticleCate> listTop();
    
    /**
     * 统计文章数量
     * @param refrenceId 主键
     * @return 数量
     */
    int countArticleNum(String refrenceId);
}
