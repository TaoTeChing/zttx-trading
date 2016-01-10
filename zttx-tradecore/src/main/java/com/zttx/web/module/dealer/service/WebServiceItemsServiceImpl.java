/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.entity.WebServiceItems;
import com.zttx.web.module.dealer.mapper.WebServiceItemsMapper;

/**
 * 网站服务项目 服务实现类
 * <p>File：WebServiceItems.java </p>
 * <p>Title: WebServiceItems </p>
 * <p>Description:WebServiceItems </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class WebServiceItemsServiceImpl extends GenericServiceApiImpl<WebServiceItems> implements WebServiceItemsService
{
    private WebServiceItemsMapper webServiceItemsMapper;
    
    @Autowired
    private DataDictValueService  dataDictValueService;
    
    @Autowired(required = true)
    public WebServiceItemsServiceImpl(WebServiceItemsMapper webServiceItemsMapper)
    {
        super(webServiceItemsMapper);
        this.webServiceItemsMapper = webServiceItemsMapper;
    }
    
    /**
     * 查询 （支撑接口调用）
     *
     * @param searchBean
     * @return
     */
    @Override
    public PaginateResult<WebServiceItems> selectByClient(WebServiceItems searchBean)
    {
        List<WebServiceItems> webServiceItemses = this.webServiceItemsMapper.selectByClient(searchBean);
        for (WebServiceItems webServiceItems : webServiceItemses)
        {
            String servicerCateName = dataDictValueService.findDictValueName(DataDictConstant.SERVICER_CATE, webServiceItems.getServicerCate() + "");
            webServiceItems.setServicerCateName(servicerCateName);
        }
        return new PaginateResult<>(searchBean.getPage(), webServiceItemses);
    }
    
    /**
     * 服务查询
     * @param webServiceItemsFilter
     * @return
     */
    @Override
    public List<WebServiceItems> search(WebServiceItems webServiceItemsFilter)
    {
        List<WebServiceItems> webServiceItemses = this.findList(webServiceItemsFilter);
        for (WebServiceItems webServiceItems : webServiceItemses)
        {
            String servicerCateName = dataDictValueService.findDictValueName(DataDictConstant.SERVICER_CATE, webServiceItems.getServicerCate() + "");
            webServiceItems.setServicerCateName(servicerCateName);
        }
        return webServiceItemses;
    }
    
    /**
     * 保存服务
     *
     * @param webServiceItems save对象
     * @return 主键
     * @throws BusinessException
     */
    @Override
    public String save(WebServiceItems webServiceItems) throws BusinessException
    {
        Short servicerCate = webServiceItems.getServicerCate();
        if (null == servicerCate) { throw new BusinessException(ClientConst.PARAMERROR.code, "服务类别不能为空！"); }
        List<DataDictValue> list = dataDictValueService.findByDictCode(DataDictConstant.SERVICER_CATE);
        if (!hasServicerCate(list, servicerCate)) { throw new BusinessException(ClientConst.PARAMERROR.code, "服务类别不存在！"); }
        webServiceItems.setServicePrice(webServiceItems.getPrice().multiply(BigDecimal.valueOf(webServiceItems.getMinBuyNum())));
        if (StringUtils.isBlank(webServiceItems.getRefrenceId()))
        {
            webServiceItems.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            webServiceItems.setCreateTime(CalendarUtils.getCurrentLong());
            webServiceItems.setDelFlag(false);
            webServiceItems.setBuyNum(0);
            webServiceItems.setViewNum(0);
            this.insertSelective(webServiceItems);
        }
        else
        {
            webServiceItems.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(webServiceItems);
        }
        return webServiceItems.getRefrenceId();
    }
    
    /**
     * 判断是否存在服务类型
     * @param list
     * @param servicerCate
     * @return
     */
    private boolean hasServicerCate(List<DataDictValue> list, Short servicerCate)
    {
        Boolean hadServicerCate = false;
        for (DataDictValue dataDictValue : list)
        {
            if (servicerCate.equals(Short.parseShort(dataDictValue.getDictValue())))
            {
                hadServicerCate = true;
                break;
            }
        }
        return hadServicerCate;
    }
    
    /**
     * 判断服务是否存在
     *
     * @param serviceId
     * @param servicerCate
     * @param chargType
     * @return
     */
    @Override
    public boolean isExist(String serviceId, Short servicerCate, Short chargType)
    {
        return this.webServiceItemsMapper.countBy(serviceId, servicerCate, chargType) > 0;
    }
}
