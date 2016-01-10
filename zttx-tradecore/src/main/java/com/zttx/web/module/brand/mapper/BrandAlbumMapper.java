/*
 * @(#)BrandAlbumMapper.java 2015-8-26 下午1:47:29
 * Copyright 2015 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandAlbum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>File：BrandAlbumMapper.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-26 下午1:47:29</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
@MyBatisDao
public interface BrandAlbumMapper extends GenericMapper<BrandAlbum>
{
    void delBatch(@Param("brandAlbumIdList")List brandAlbumIdList);
}
