/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.module.fronts.entity.HelpCate;
import com.zttx.web.module.fronts.entity.HelpInfo;
import com.zttx.web.module.fronts.mapper.HelpInfoMapper;

/**
 * 帮助内容 服务实现类
 * <p>File：HelpInfo.java </p>
 * <p>Title: HelpInfo </p>
 * <p>Description:HelpInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class HelpInfoServiceImpl extends GenericServiceApiImpl<HelpInfo> implements HelpInfoService
{
    private HelpInfoMapper  helpInfoMapper;
    
    @Autowired(required = true)
    private HelpCateService helpCateService;
    
    @Autowired(required = true)
    public HelpInfoServiceImpl(HelpInfoMapper helpInfoMapper)
    {
        super(helpInfoMapper);
        this.helpInfoMapper = helpInfoMapper;
    }
    
    public Long findHelpToSolrCount(HelpInfo helpInfo) throws BusinessException
    {
        return helpInfoMapper.findHelpToSolrCount(helpInfo);
    }
    
    @Override
    public List<HelpInfo> findHelpToSolr(HelpInfo helpInfo, Pagination pagination) throws BusinessException
    {
        List<HelpInfo> helpInfos = helpInfoMapper.findHelpToSolr(helpInfo, pagination);
        for (HelpInfo info : helpInfos)
        {
            info.setHelpCates(helpCateService.getParentHelpCates(info.getHelpCateId()));
        }
        return helpInfos;
    }
    
    @Override
    public List<HelpInfo> getInfosByHelpCateId(String cateId, Pagination pagination)
    {
        return helpInfoMapper.getInfosByHelpCateId(cateId, pagination);
    }
    
    /**
     * 分页查询 （支撑接口调用）
     *
     * @param page
     * @param searchBean
     * @return
     */
    @Override
    public PaginateResult<HelpInfo> selectByClient(Pagination page, HelpInfo searchBean)
    {
        searchBean.setPage(page);
        return new PaginateResult<>(page, helpInfoMapper.selectByClient(searchBean));
    }
    
    /**
     * 保存帮助信息
     *
     * @param helpInfo
     */
    @Override
    public HelpInfo saveByClient(HelpInfo helpInfo) throws BusinessException
    {
        HelpCate helpCate = helpCateService.selectByPrimaryKey(helpInfo.getHelpCateId());
        if (helpCate == null) { throw new BusinessException(ClientConst.PARAMERROR.getCode(), "帮助分类不存在或已删除"); }
        helpInfo.setHelpNo(helpCate.getCateNo());
        Short helpCateShowType = helpCate.getShowType();// 分类显示类型
        if (helpCateShowType.equals(CommonConstant.HelpCate.SHOW_TYPE_ARTICLE)) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "文章分类不能添加文章"); }
        if (StringUtils.isNotBlank(helpInfo.getRefrenceId()))
        {
            helpInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(helpInfo);
        }
        else
        {
            helpInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            helpInfo.setCreateTime(CalendarUtils.getCurrentLong());
            helpInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            helpInfo.setHelpNo(1);
            this.insertSelective(helpInfo);
        }
        return helpInfo;
    }
}
