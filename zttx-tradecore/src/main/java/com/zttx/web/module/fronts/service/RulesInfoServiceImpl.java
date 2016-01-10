/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.NetworkUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.fronts.entity.RulesCate;
import com.zttx.web.module.fronts.entity.RulesInfo;
import com.zttx.web.module.fronts.entity.RulesInfoLog;
import com.zttx.web.module.fronts.mapper.RulesInfoMapper;

/**
 * 规则内容信息 服务实现类
 * <p>File：RulesInfo.java </p>
 * <p>Title: RulesInfo </p>
 * <p>Description:RulesInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class RulesInfoServiceImpl extends GenericServiceApiImpl<RulesInfo> implements RulesInfoService
{
    private RulesInfoMapper     rulesInfoMapper;
    
    @Autowired
    private RulesCateService    rulesCateService;
    
    @Autowired
    private RulesInfoLogService rulesInfoLogService;
    
    @Autowired(required = true)
    public RulesInfoServiceImpl(RulesInfoMapper rulesInfoMapper)
    {
        super(rulesInfoMapper);
        this.rulesInfoMapper = rulesInfoMapper;
    }
    
    public Long findRulesToSolrCount(RulesInfo rulesInfo) throws BusinessException
    {
        return rulesInfoMapper.findRulesToSolrCount(rulesInfo);
    }
    
    @Override
    public List<RulesInfo> findRulesToSolr(RulesInfo rulesInfo, Pagination pagination) throws BusinessException
    {
        List<RulesInfo> rulesInfos = rulesInfoMapper.findRulesToSolr(rulesInfo, pagination);
        for (RulesInfo info : rulesInfos)
        {
            if (StringUtils.isNoneBlank(info.getCateId()))
            {
                List<RulesCate> rulesCates = rulesCateService.getParentRulesCates(info.getCateId());
                info.setRulesCates(rulesCates);
            }
        }
        return rulesInfos;
    }
    
    @Override
    public List<RulesInfo> getRulesInfoByCateKey(String cateId, Pagination pagination)
    {
        return rulesInfoMapper.getRulesInfoByCateKey(cateId, pagination);
    }
    
    @Override
    public List<RulesInfo> getNewRulesInfo(Pagination pagination)
    {
        return rulesInfoMapper.getNewRulesInfo(pagination);
    }
    
    /**
     * 保存（提供给接口）
     *
     * @param rulesInfo 规则信息
     */
    @Override
    public RulesInfo saveByClient(RulesInfo rulesInfo) throws BusinessException
    {
        if (StringUtils.isBlank(rulesInfo.getRefrenceId()))
        {
            rulesInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            rulesInfo.setCreateTime(CalendarUtils.getCurrentLong());
            rulesInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            rulesInfo.setDomainName(NetworkUtils.getDoMainName());
            rulesInfo.setViewNum(0);
            // rulesInfo.setRulesParentId(findParentIds(rulesInfo.getCateId()));
            this.insertSelective(rulesInfo);
        }
        else
        {
            RulesInfo old = this.selectByPrimaryKey(rulesInfo.getRefrenceId());
            if (old == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            this.addRulesInfoLog(old);
            rulesInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(rulesInfo);
        }
        rulesCateService.updateArticleNum(rulesInfo.getCateId(), 0);
        return rulesInfo;
    }
    
    private void addRulesInfoLog(RulesInfo rulesInfo)
    {
        RulesInfoLog rulesInfoLog = new RulesInfoLog();
        try
        {
            BeanUtils.copyProperties(rulesInfoLog, rulesInfo);
            rulesInfoLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            rulesInfoLog.setCreateTime(CalendarUtils.getCurrentLong());
            rulesInfoLog.setRulesId(rulesInfo.getRefrenceId());
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException();
        }
        rulesInfoLogService.insertSelective(rulesInfoLog);
    }
    
    /**
     * 分页查询（提供给接口）
     *
     * @param page       分页对象
     * @param searchBean 查询条件
     * @return List
     */
    @Override
    public PaginateResult<RulesInfo> selectByClient(Pagination page, RulesInfo searchBean)
    {
        return new PaginateResult(page, this.rulesInfoMapper.selectByClient(page, searchBean));
    }
    
    /**
     * 该类型下面是否存在文章
     *
     * @param cateId
     * @return
     */
    @Override
    public boolean hasArticle(String cateId)
    {
        return rulesInfoMapper.countArticleByCateId(cateId) > 0;
    }
}
