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
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.fronts.entity.ArticleCate;
import com.zttx.web.module.fronts.mapper.ArticleCateMapper;
import com.zttx.web.module.fronts.mapper.ArticleInfoMapper;
import com.zttx.web.utils.NetworkUtils;

/**
 * 网站资讯类别 服务实现类
 * <p>File：ArticleCate.java </p>
 * <p>Title: ArticleCate </p>
 * <p>Description:ArticleCate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class ArticleCateServiceImpl extends GenericServiceApiImpl<ArticleCate> implements ArticleCateService
{
    private ArticleCateMapper    articleCateMapper;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    @Autowired
    private ArticleInfoMapper    articleInfoMapper;
    
    @Autowired(required = true)
    public ArticleCateServiceImpl(ArticleCateMapper articleCateMapper)
    {
        super(articleCateMapper);
        this.articleCateMapper = articleCateMapper;
    }
    
    @Override
    public TreeSet<ArticleCate> listTop()
    {
        Integer value = dataDictValueService.getSingleDictValue(DataDictConstant.ARTICLECATE_LEVEL);
        int levelNum = value == null ? 2 : value;
        List<ArticleCate> all = selectAll();
        Map<String, ArticleCate> resource = Maps.newHashMap();
        for (ArticleCate articleCate : all)
        {
            resource.put(articleCate.getRefrenceId(), articleCate);
        }
        for (int i = levelNum; i > 0; i--)
        {
            for (ArticleCate articleCate : resource.values())
            {
                if (articleCate.getCateLevel() == i)
                {
                    String parentId = articleCate.getParentId();
                    ArticleCate parent = resource.get(parentId);
                    if (null == parent)
                    {
                        continue;
                    }
                    parent.getChildren().add(articleCate);
                }
            }
        }
        TreeSet<ArticleCate> topList = Sets.newTreeSet(new ArticleCate.ArticleCateComparator());
        for (ArticleCate articleCate : resource.values())
        {
            if (articleCate.getCateLevel() == 1)
            {
                topList.add(resource.get(articleCate.getRefrenceId()));
            }
        }
        return topList;
    }
    
    /**
     * 保存咨询类目 （支撑接口调用）
     * @param articleCate 对象
     * @throws BusinessException 异常
     */
    @Override
    public void saveByClient(ArticleCate articleCate) throws BusinessException
    {
        String parentId = articleCate.getParentId();
        ArticleCate parent = this.selectByPrimaryKey(parentId);
        if (null != parent)
        {
            Short cateLevel = (short) (parent.getCateLevel() + 1);
            Integer value = dataDictValueService.getSingleDictValue(DataDictConstant.ARTICLECATE_LEVEL);
            Short LevelNum = value == null ? (short) 2 : value.shortValue();
            if (cateLevel > LevelNum) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "不能再创建子级！"); }
            articleCate.setCateLevel(cateLevel);
        }
        else
        {
            articleCate.setCateLevel((short) 1);
        }
        if (StringUtils.isNotBlank(articleCate.getRefrenceId()))
        {
            articleCate.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(articleCate);
        }
        else
        {
            articleCate.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            articleCate.setArticleNum(0);
            articleCate.setDomainName(NetworkUtils.getDoMainName());
            articleCate.setCreateTime(CalendarUtils.getCurrentLong());
            articleCate.setUpdateTime(CalendarUtils.getCurrentLong());
            this.insertSelective(articleCate);
        }
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
        ArticleCate articleCate = this.selectByPrimaryKey(refrenceId);
        if (null != articleCate)
        {
            Integer articleNum = this.countArticleNum(articleCate.getRefrenceId()) + roll;
            articleNum = articleNum >= 0 ? articleNum : 0;
            articleCate.setArticleNum(articleNum);
            this.updateByPrimaryKeySelective(articleCate);
        }
    }
    
    /**
     * 统计文章数量
     *
     * @param refrenceId 主键
     * @return 数量
     */
    @Override
    public int countArticleNum(String refrenceId)
    {
        return articleInfoMapper.countArticleNum(refrenceId);
    }
    
    @Override
    public List<ArticleCate> getParentArticleCates(String cateId)
    {
        return getParentHelpCates(cateId, new ArrayList<ArticleCate>());
    }
    
    @Override
    public List<ArticleCate> getAllCates()
    {
        List<ArticleCate> list = articleCateMapper.findByLevel(new Short("1"));
        for (ArticleCate cate : list)
        {
            cate.setSubList(getSubList(cate.getRefrenceId(), new ArrayList<ArticleCate>()));
        }
        return list;
    }
    
    /**
     * 递归查询
     *
     * @param parentId
     * @param cates
     * @return {@link List}
     */
    List<ArticleCate> getSubList(String parentId, List<ArticleCate> cates)
    {
        List<ArticleCate> cateList = articleCateMapper.findByParentId(parentId);
        if (null != cateList)
        {
            cates.addAll(cateList);
            for (ArticleCate cate : cateList)
            {
                return getSubList(cate.getRefrenceId(), cates);
            }
        }
        return cates;
    }
    
    /**
     * 递归查询
     *
     * @param cateId
     * @param cates
     * @return {@link List}
     */
    List<ArticleCate> getParentHelpCates(String cateId, List<ArticleCate> cates)
    {
        ArticleCate articleCate = selectByPrimaryKey(cateId);
        if (null != articleCate)
        {
            cates.add(articleCate);
            if (null != articleCate.getParentId() && articleCate.getParentId() != articleCate.getRefrenceId()) { return getParentHelpCates(articleCate.getParentId(), cates); }
        }
        return cates;
    }
    
    /**
     * 查询文章分类信息通过父亲文章id下面的所有的子类信息
     * @param parendId
     * @return
     */
    @Override
    public List<ArticleCate> findBy(String parendId)
    {
        return getSubList(parendId, new ArrayList<ArticleCate>());
    }
}
