/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.dealer.entity.DealerShopEnvTemp;
import com.zttx.web.module.dealer.mapper.DealerShopEnvTempMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 经销店铺 临时信息 服务实现类
 * <p>File：DealerShopEnvTemp.java </p>
 * <p>Title: DealerShopEnvTemp </p>
 * <p>Description:DealerShopEnvTemp </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerShopEnvTempServiceImpl extends GenericServiceApiImpl<DealerShopEnvTemp> implements DealerShopEnvTempService
{
    private DealerShopEnvTempMapper dealerShopEnvTempMapper;
    
    @Autowired(required = true)
    public DealerShopEnvTempServiceImpl(DealerShopEnvTempMapper dealerShopEnvTempMapper)
    {
        super(dealerShopEnvTempMapper);
        this.dealerShopEnvTempMapper = dealerShopEnvTempMapper;
    }
    
    @Override
    public PaginateResult<DealerShopEnvTemp> searchByClient(DealerShopEnvTemp dealerShopEnvTemp)
    {
        List<DealerShopEnvTemp> dealerShopEnvTempList = findList(dealerShopEnvTemp);
        for (DealerShopEnvTemp _dealerShopEnvTemp : dealerShopEnvTempList)
        {
            _dealerShopEnvTemp.setDetailObj(JSONObject.parse(_dealerShopEnvTemp.getDetail()));
            _dealerShopEnvTemp.setDetail(null);
        }
        return new PaginateResult<>(dealerShopEnvTemp.getPage(), dealerShopEnvTempList);
    }
    
    @Override
    public void updateDealerShopEnvTempStatus(String refrenceId, int status, String auditUser, String auditIp) throws BusinessException
    {
        DealerShopEnvTemp dealerShopEnvTemp = dealerShopEnvTempMapper.selectByPrimaryKey(refrenceId);
        if (dealerShopEnvTemp == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        dealerShopEnvTemp.setStatus(status);
        dealerShopEnvTemp.setAuditTime(CalendarUtils.getCurrentLong());
        dealerShopEnvTemp.setAuditUser(auditUser);
        dealerShopEnvTemp.setAuditIp(auditIp);
        dealerShopEnvTempMapper.updateByPrimaryKey(dealerShopEnvTemp);
    }
}
