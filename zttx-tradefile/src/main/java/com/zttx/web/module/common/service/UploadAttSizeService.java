/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.UploadAttSize;

import java.util.List;

/**
 * 上传图片附件类型大小 服务接口
 * <p>File：UploadAttSizeService.java </p>
 * <p>Title: UploadAttSizeService </p>
 * <p>Description:UploadAttSizeService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface UploadAttSizeService extends GenericServiceApi<UploadAttSize>
{
    /**
     *根据图片类别获取需要生成的大小尺寸的列表
     * @param cateKey
     * @return
     */
    List<UploadAttSize> findByCateKey(String cateKey);

}
