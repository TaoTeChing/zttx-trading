/*
 * @(#)DemoMapper.java 2015-8-10 上午9:42:12
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.Demo;

/**
 * <p>File：DemoMapper.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-10 上午9:42:12</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@MyBatisDao
public interface DemoMapper extends GenericMapper<Demo>
{
}
