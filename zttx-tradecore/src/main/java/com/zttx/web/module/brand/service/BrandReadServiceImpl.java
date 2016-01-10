/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.brand.entity.BrandRead;
import com.zttx.web.module.brand.mapper.BrandReadMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商消息阅读 服务实现类
 * <p>File：BrandRead.java </p>
 * <p>Title: BrandRead </p>
 * <p>Description:BrandRead </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandReadServiceImpl extends GenericServiceApiImpl<BrandRead> implements BrandReadService
{
    private BrandReadMapper brandReadMapper;
    
    @Autowired(required = true)
    public BrandReadServiceImpl(BrandReadMapper brandReadMapper)
    {
        super(brandReadMapper);
        this.brandReadMapper = brandReadMapper;
    }
    
    /**
     * 阅读多条消息
     * @param brandId 品牌商ID
     * @param msgIdList 消息ID列表
     * @author 章旭楠
     */
    @Override
    public void saveRead(String brandId, List<String> msgIdList)
    {
        msgIdList = this.getUnReadMsgIdList(brandId, msgIdList);
        if (msgIdList.isEmpty()) { return; }
        List<BrandRead> brandReads = this.createBrandReadList(brandId, msgIdList);
        this.brandReadMapper.insertBatch(brandReads);
    }
    
    /**
     * 阅读一条消息
     * @param brandId 品牌商ID
     * @param msgId 消息ID
     * @author 章旭楠
     */
    @Override
    public void saveRead(String brandId, String msgId)
    {
        msgId = getUnReadMsgIdList(brandId, msgId);
        if (StringUtils.isNotBlank(msgId))
        {
            BrandRead brandRead = createBrandRead(brandId, msgId);
            brandReadMapper.insertSelective(brandRead);
        }
    }
    
    private String getUnReadMsgIdList(String brandId, String msgId)
    {
        if (!this.isExistBrandRead(brandId, msgId)) { return msgId; }
        return "";
    }
    
    private List<String> getUnReadMsgIdList(String brandId, List<String> msgIdList)
    {
        List<String> newMsgIdList = Lists.newArrayList();
        for (int i = 0; i < msgIdList.size(); i++)
        {
            String msgId = msgIdList.get(i);
            if (!this.isExistBrandRead(brandId, msgId))
            {
                newMsgIdList.add(msgId);
            }
        }
        return newMsgIdList;
    }
    
    /**
     * 判断与品牌商相关的已读记录是否已存在
     *
     * @param brandId 品牌商ID
     * @param msgId   消息ID
     * @return Boolean true：存在， false：不存在
     * @author 章旭楠
     */
    @Override
    public Boolean isExistBrandRead(String brandId, String msgId)
    {
        List list = brandReadMapper.selectBrandRead(brandId, msgId);
        return list.size() > 0;
    }
    
    private List<BrandRead> createBrandReadList(String brandId, List<String> msgIdList)
    {
        List<BrandRead> list = Lists.newArrayList();
        for (int i = 0; i < msgIdList.size(); i++)
        {
            list.add(this.createBrandRead(brandId, msgIdList.get(i)));
        }
        return list;
    }
    
    private BrandRead createBrandRead(String brandId, String msgId)
    {
        BrandRead brandRead = new BrandRead();
        brandRead.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandRead.setBrandId(brandId);
        brandRead.setMsgId(msgId);
        brandRead.setReadTime(CalendarUtils.getCurrentLong());
        brandRead.setDelFlag(false);
        return brandRead;
    }
}
