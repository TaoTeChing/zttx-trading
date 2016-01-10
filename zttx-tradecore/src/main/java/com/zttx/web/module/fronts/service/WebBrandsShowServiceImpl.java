/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.mapper.BrandRecruitMapper;
import com.zttx.web.module.brand.mapper.BrandesInfoMapper;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.fronts.entity.WebBrandsShow;
import com.zttx.web.module.fronts.mapper.WebBrandsShowMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 首页感兴趣品牌展示 服务实现类
 * <p>File：WebBrandsShow.java </p>
 * <p>Title: WebBrandsShow </p>
 * <p>Description:WebBrandsShow </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class WebBrandsShowServiceImpl extends GenericServiceApiImpl<WebBrandsShow> implements WebBrandsShowService
{
    private WebBrandsShowMapper  webBrandsShowMapper;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    @Autowired
    private BrandesInfoMapper    brandesInfoMapper;
    
    @Autowired
    private BrandRecruitMapper   brandRecruitMapper;
    
    @Autowired(required = true)
    public WebBrandsShowServiceImpl(WebBrandsShowMapper webBrandsShowMapper)
    {
        super(webBrandsShowMapper);
        this.webBrandsShowMapper = webBrandsShowMapper;
    }
    
    @Override
    public void saveByClient(WebBrandsShow webBrandsShow) throws BusinessException
    {
        Short showType = webBrandsShow.getShowType();
        List<DataDictValue> dataDictValues = dataDictValueService.findByDictCode("webBrandesShowType");
        if (!showTypeContains(dataDictValues, showType)) { throw new BusinessException(ClientConst.PARAMERROR.code, "显示类型参数不合法"); }
        if (this.isBrandsIdExist(webBrandsShow.getRefrenceId(), webBrandsShow.getBrandsId(), webBrandsShow.getShowType())) { throw new BusinessException(
                ClientConst.ERROR_HANDLE.code, "该品牌已存在，不能重复添加"); }
        if (StringUtils.isBlank(webBrandsShow.getRefrenceId()))
        {
            webBrandsShow.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            webBrandsShow.setCreateTime(CalendarUtils.getCurrentLong());
            webBrandsShow.setUpdateTime(CalendarUtils.getCurrentLong());
            this.webBrandsShowMapper.insertSelective(webBrandsShow);
        }
        else
        {
            WebBrandsShow old = webBrandsShowMapper.selectByPrimaryKey(webBrandsShow.getRefrenceId());
            if (old == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            webBrandsShow.setUpdateTime(CalendarUtils.getCurrentLong());
            webBrandsShowMapper.updateByPrimaryKeySelective(webBrandsShow);
        }
    }
    
    /**
     * 是否包含
     * @param dataDictValues
     * @param showType
     * @return
     */
    private boolean showTypeContains(List<DataDictValue> dataDictValues, Short showType)
    {
        for (DataDictValue dataDictValue : dataDictValues)
        {
            Short dictValue = Short.parseShort(dataDictValue.getDictValue());
            if (showType.equals(dictValue))
            {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public PaginateResult<WebBrandsShow> searchByClient(WebBrandsShow searchBean, Pagination pagination)
    {
        searchBean.setPage(pagination);
        return new PaginateResult<>(searchBean.getPage(), webBrandsShowMapper.searchByClient(searchBean));
    }
    
    @Override
    public Boolean isBrandsIdExist(String refrenceId, String brandsId, Short showType)
    {
        return webBrandsShowMapper.countBy(refrenceId, brandsId, showType) > 0;
    }
    
    @Override
    public List<BrandsInfoModel> indexList(Pagination pagination, Short showType)
    {
        return brandesInfoMapper.findIndexList(pagination, BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED, showType);
    }
}
