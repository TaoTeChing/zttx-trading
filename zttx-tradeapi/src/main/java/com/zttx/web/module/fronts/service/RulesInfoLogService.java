/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.fronts.entity.RulesInfoLog;
/**
 * 规则内容历史记录 服务接口
 * <p>File：RulesInfoLogService.java </p>
 * <p>Title: RulesInfoLogService </p>
 * <p>Description:RulesInfoLogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface RulesInfoLogService extends GenericServiceApi<RulesInfoLog>{

	void addRulesInfoLog(RulesInfoLog rulesInfoLog);

}
