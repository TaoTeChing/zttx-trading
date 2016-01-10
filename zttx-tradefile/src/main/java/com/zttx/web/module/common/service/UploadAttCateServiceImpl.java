/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.UploadAttCate;
import com.zttx.web.module.common.mapper.UploadAttCateMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 上传图片附件类型 服务实现类
 * <p>File：UploadAttCate.java </p>
 * <p>Title: UploadAttCate </p>
 * <p>Description:UploadAttCate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UploadAttCateServiceImpl extends GenericServiceApiImpl<UploadAttCate> implements UploadAttCateService
{

    private UploadAttCateMapper uploadAttCateMapper;

    @Autowired(required = true)
    public UploadAttCateServiceImpl(UploadAttCateMapper uploadAttCateMapper)
    {
        super(uploadAttCateMapper);
        this.uploadAttCateMapper = uploadAttCateMapper;
    }
}
