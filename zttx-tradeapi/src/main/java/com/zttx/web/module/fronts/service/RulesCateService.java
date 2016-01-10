/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;
import java.util.TreeSet;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.RulesCate;

/**
 * 网站规则类别 服务接口
 * <p>File：RulesCateService.java </p>
 * <p>Title: RulesCateService </p>
 * <p>Description:RulesCateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface RulesCateService extends GenericServiceApi<RulesCate>
{
    /**
     * 根据分类ID取上级分类
     * <p>
     *     采用递归方式取上级分类
     * </p>
     * @param cateId
     * @return
     */
    List<RulesCate> getParentRulesCates(String cateId);
    
    /**
     * 获得所有的规则的信息以树的形式展现出来
     * @return
     */
    List<RulesCate> getAllRuleCates();
    
    /**
     * 保存规则
     * @param rulesCate 规则
     * @throws BusinessException
     */
    void saveByClient(RulesCate rulesCate) throws BusinessException;
    
    /**
     * 获得最顶层的集合（封装了 （TreeSet<RulesCate> children属性） 下级类别）
     * @return TreeSet
     */
    TreeSet<RulesCate> selectTop();
    
    /**
     * 更新文章数量
     * @param refrenceId 主键
     * @param roll
     *      数量 2：加2  /  -1:减1
     */
    void updateArticleNum(String refrenceId, int roll);
    
    /**
     * 统计包含文章数量
     * @param refrenceId 主键
     * @return 数量
     */
    int countArticleNum(String refrenceId);
    
    /**
     * 逻辑删除
     * @param refrenceId 主键
     * @throws BusinessException
     */
    void deleteByClient(String refrenceId) throws BusinessException;
    
    boolean hasChildren(String refrenceId);
}
