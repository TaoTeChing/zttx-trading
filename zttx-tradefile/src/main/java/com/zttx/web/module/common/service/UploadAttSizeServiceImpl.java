/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.UploadAttSize;
import com.zttx.web.module.common.mapper.UploadAttSizeMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 上传图片附件类型大小 服务实现类
 * <p>File：UploadAttSize.java </p>
 * <p>Title: UploadAttSize </p>
 * <p>Description:UploadAttSize </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UploadAttSizeServiceImpl extends GenericServiceApiImpl<UploadAttSize> implements UploadAttSizeService
{
    private UploadAttSizeMapper uploadAttSizeMapper;
    
    @Autowired(required = true)
    public UploadAttSizeServiceImpl(UploadAttSizeMapper uploadAttSizeMapper)
    {
        super(uploadAttSizeMapper);
        this.uploadAttSizeMapper = uploadAttSizeMapper;
    }
    
    @Override
    public List<UploadAttSize> findByCateKey(String cateKey)
    {
        return uploadAttSizeMapper.findByCateKey(cateKey);
    }
}
