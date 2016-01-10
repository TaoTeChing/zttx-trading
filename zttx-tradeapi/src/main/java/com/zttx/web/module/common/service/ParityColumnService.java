/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.ParityColumn;
/**
 * 比价栏目表 服务接口
 * <p>File：ParityColumnService.java </p>
 * <p>Title: ParityColumnService </p>
 * <p>Description:ParityColumnService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ParityColumnService extends GenericServiceApi<ParityColumn>{

    /**
     *  获取所有比价栏目
     * @return
     */
    List<ParityColumn> getParityColumnList();

}
