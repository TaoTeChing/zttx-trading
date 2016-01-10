/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.dealer.entity.WebServiceCom;
import com.zttx.web.module.dealer.mapper.WebServiceComMapper;

/**
 * 网站服务商 服务实现类
 * <p>File：WebServiceCom.java </p>
 * <p>Title: WebServiceCom </p>
 * <p>Description:WebServiceCom </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class WebServiceComServiceImpl extends GenericServiceApiImpl<WebServiceCom> implements WebServiceComService
{
    private WebServiceComMapper webServiceComMapper;
    
    @Autowired(required = true)
    public WebServiceComServiceImpl(WebServiceComMapper webServiceComMapper)
    {
        super(webServiceComMapper);
        this.webServiceComMapper = webServiceComMapper;
    }
    
    @Override
    public PaginateResult<WebServiceCom> searchByClient(Pagination page, WebServiceCom searchBean)
    {
        searchBean.setPage(page);
        return new PaginateResult(searchBean.getPage(), webServiceComMapper.findList(searchBean));
    }
    
    /**
     * 保存 （支撑调用）
     *
     * @param webServiceCom 保存对象
     * @return 主键
     */
    @Override
    public String saveByClient(WebServiceCom webServiceCom)
    {
        if (StringUtils.isBlank(webServiceCom.getRefrenceId()))
        {
            webServiceCom.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            webServiceCom.setCreateTime(CalendarUtils.getCurrentLong());
            this.webServiceComMapper.insertSelective(webServiceCom);
        }
        else
        {
            webServiceCom.setUpdateTime(CalendarUtils.getCurrentLong());
            webServiceComMapper.updateByPrimaryKeySelective(webServiceCom);
        }
        return webServiceCom.getRefrenceId();
    }
}
