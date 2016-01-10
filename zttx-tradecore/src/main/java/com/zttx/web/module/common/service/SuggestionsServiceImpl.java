/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.Suggestions;
import com.zttx.web.module.common.mapper.SuggestionsMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 意见和建议 服务实现类
 * <p>File：Suggestions.java </p>
 * <p>Title: Suggestions </p>
 * <p>Description:Suggestions </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SuggestionsServiceImpl extends GenericServiceApiImpl<Suggestions> implements SuggestionsService
{

    private SuggestionsMapper suggestionsMapper;

    @Autowired(required = true)
    public SuggestionsServiceImpl(SuggestionsMapper suggestionsMapper)
    {
        super(suggestionsMapper);
        this.suggestionsMapper = suggestionsMapper;
    }
}
