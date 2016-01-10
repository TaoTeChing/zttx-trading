/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.fronts.entity.RulesCate;
import com.zttx.web.module.fronts.mapper.RulesCateMapper;
import com.zttx.web.module.fronts.mapper.RulesInfoMapper;
import com.zttx.web.utils.ValidateUtils;

/**
 * 网站规则类别 服务实现类
 * <p>File：RulesCate.java </p>
 * <p>Title: RulesCate </p>
 * <p>Description:RulesCate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RulesCateServiceImpl extends GenericServiceApiImpl<RulesCate> implements RulesCateService
{
    private RulesCateMapper      rulesCateMapper;
    
    @Autowired(required = false)
    private RulesInfoMapper      rulesInfoMapper;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    @Autowired
    private RulesInfoService     rulesInfoService;
    
    @Autowired(required = true)
    public RulesCateServiceImpl(RulesCateMapper rulesCateMapper)
    {
        super(rulesCateMapper);
        this.rulesCateMapper = rulesCateMapper;
    }
    
    @Override
    public List<RulesCate> getParentRulesCates(String cateId)
    {
        return getParentRulesCates(cateId, new ArrayList<RulesCate>());
    }
    
    /**
     * 递归查询
     * @param cateId
     * @param rulesCates
     * @return  {@link List}
     */
    List<RulesCate> getParentRulesCates(String cateId, List<RulesCate> rulesCates)
    {
        RulesCate rulesCate = selectByPrimaryKey(cateId);
        if (null != rulesCate)
        {
            rulesCates.add(rulesCate);
            if (StringUtils.isNotBlank(rulesCate.getParentId()) && rulesCate.getParentId() != rulesCate.getRefrenceId()) { return getParentRulesCates(
                    rulesCate.getParentId(), rulesCates); }
        }
        return rulesCates;
    }
    
    @Override
    public List<RulesCate> getAllRuleCates()
    {
        List<RulesCate> list = rulesCateMapper.findByLevel(new Short("1"));
        for (RulesCate cate : list)
        {
            cate.setSubList(getSubList(cate.getRefrenceId(), new ArrayList<RulesCate>()));
        }
        return list;
    }
    
    /**
     * 递归查询,父亲类别下面的所有的子类别
     * @param parentId
     * @param cates
     * @return {@link List}
     */
    List<RulesCate> getSubList(String parentId, List<RulesCate> cates)
    {
        List<RulesCate> cateList = rulesCateMapper.findByParentId(parentId);
        if (null != cateList)
        {
            cates.addAll(cateList);
            for (RulesCate cate : cateList)
            {
                return getSubList(cate.getRefrenceId(), cates);
            }
        }
        return cates;
    }
    
    /**
     * 保存规则
     *
     * @param rulesCate 规则
     * @throws BusinessException
     */
    @Override
    public void saveByClient(RulesCate rulesCate) throws BusinessException
    {
        RulesCate parent = this.selectByPrimaryKey(rulesCate.getParentId());// 父类
        if (parent == null)
        {
            rulesCate.setCateLevel((short) 1);
        }
        else
        {
            if (parent.getCateType().equals(CommonConstant.RulesCate.CATE_TYPE_ARTICLE)) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(),
                    "规则分类为文章的类型不能再创建子级！"); }
            Short cateLevel = (short) (parent.getCateLevel() + 1);
            Integer value = dataDictValueService.getSingleDictValue(DataDictConstant.ARTICLECATE_LEVEL);
            Short LevelNum = value == null ? (short) 2 : value.shortValue();
            if (cateLevel > LevelNum) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "不能再创建子级！"); }
            rulesCate.setCateLevel(cateLevel);
        }
        boolean isArticleType = rulesCate.getCateType().equals(CommonConstant.RulesCate.CATE_TYPE_ARTICLE);// 是否文章类型
        if (isArticleType)
        {
            if (StringUtils.isBlank(rulesCate.getCateMark())) { throw new BusinessException(ClientConst.NULERROR.getCode(), "类别描述不能为空"); }
            if (StringUtils.isBlank(rulesCate.getCateText())) { throw new BusinessException(ClientConst.NULERROR.getCode(), "文章内容不能为空"); }
            if (ValidateUtils.length(rulesCate.getCateText()) > 65535) { throw new BusinessException(ClientConst.PARAMERROR.getCode(), "文章内容长度不能超过65535"); }
        }
        if (StringUtils.isNotBlank(rulesCate.getRefrenceId()))
        {// 修改
            RulesCate oldRulesCate = this.selectByPrimaryKey(rulesCate.getRefrenceId());
            if (oldRulesCate == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            if (isArticleType && this.hasChildren(rulesCate.getRefrenceId())) { throw new BusinessException(ClientConst.PARAMERROR.getCode(), "该规则分类已经存在子级，不能修改为文章类型！"); }
            if (this.hasChildren(rulesCate.getRefrenceId()) && !rulesCate.getParentId().equals(oldRulesCate.getParentId())) { throw new BusinessException(
                    ClientConst.PARAMERROR.getCode(), "该规则分类已经存在子级，不能修改父级！"); }
            this.updateByPrimaryKeySelective(rulesCate);
            // updateArticleNum(parent, rulesCate.getCateType(), oldRulesCate.getCateType());
        }
        else
        {// 新增
            rulesCate.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            rulesCate.setCreateTime(CalendarUtils.getCurrentLong());
            rulesCate.setArticleNum(0);
            this.insertSelective(rulesCate);
            // updateArticleNum(parent, rulesCate.getCateType(), null);
        }
        updateArticleNum(rulesCate.getParentId(), 0);
    }
    
    /**
     * 获得最顶层的集合（封装了 （TreeSet<RulesCate> children属性） 下级类别）
     *
     * @return TreeSet
     */
    @Override
    public TreeSet<RulesCate> selectTop()
    {
        Integer value = dataDictValueService.getSingleDictValue(DataDictConstant.RULESCATE_LEVEL);
        int levelNum = value == null ? 2 : value;// 默认两级
        Map<String, RulesCate> resource = Maps.newHashMap();
        List<RulesCate> all = this.selectAll();
        for (RulesCate rulesCate : all)
        {
            rulesCate.setCateText("");
            resource.put(rulesCate.getRefrenceId(), rulesCate);
        }
        for (int i = levelNum; i > 0; i--)
        {
            for (RulesCate rulesCate : resource.values())
            {
                if (rulesCate.getCateLevel() == i)
                {
                    String parentId = rulesCate.getParentId();
                    RulesCate parent = resource.get(parentId);
                    if (null == parent)
                    {
                        continue;
                    }
                    parent.getChildren().add(rulesCate);
                }
            }
        }
        TreeSet<RulesCate> topList = Sets.newTreeSet(new RulesCate.RulesCateComparator());
        for (RulesCate rulesCate : resource.values())
        {
            if (rulesCate.getCateLevel() == 1)
            {
                topList.add(resource.get(rulesCate.getRefrenceId()));
            }
        }
        return topList;
    }
    
    /**
     * 更新文章数量
     *
     * @param refrenceId 主键
     * @param roll
     */
    @Override
    public void updateArticleNum(String refrenceId, int roll)
    {
        RulesCate rulesCate = this.selectByPrimaryKey(refrenceId);
        if (null != rulesCate)
        {
            Integer articleNum = this.countArticleNum(rulesCate.getRefrenceId()) + roll;
            articleNum = articleNum >= 0 ? articleNum : 0;
            rulesCate.setArticleNum(articleNum);
            this.updateByPrimaryKeySelective(rulesCate);
        }
    }
    
    /**
     * 统计包含文章数量
     *
     * @param refrenceId      主键
     * @return 数量
     */
    @Override
    public int countArticleNum(String refrenceId)
    {
        int articleNum = rulesInfoMapper.countArticleByCateId(refrenceId);
        return articleNum + rulesCateMapper.countArticleNum(refrenceId, CommonConstant.RulesCate.CATE_TYPE_ARTICLE);
    }
    
    /**
     * 逻辑删除
     * @param refrenceId 主键
     * @throws BusinessException
     */
    @Override
    public void deleteByClient(String refrenceId) throws BusinessException
    {
        if (this.hasChildren(refrenceId)) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "存在子类别！"); }
        if (rulesInfoService.hasArticle(refrenceId)) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "存在文章！"); }
        RulesCate dbRulesCate = this.selectByPrimaryKey(refrenceId);
        this.delete(refrenceId);
        this.updateArticleNum(dbRulesCate.getParentId(), 0);
    }
    
    @Override
    public boolean hasChildren(String refrenceId)
    {
        return rulesCateMapper.findByParentId(refrenceId).size() > 0;
    }
}
