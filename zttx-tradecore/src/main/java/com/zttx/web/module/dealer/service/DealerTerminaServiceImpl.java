/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.entity.DealerTermina;
import com.zttx.web.module.dealer.mapper.DealerTerminaMapper;
import com.zttx.web.utils.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 经销商中止合作日志 服务实现类
 * <p>File：DealerTermina.java </p>
 * <p>Title: DealerTermina </p>
 * <p>Description:DealerTermina </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerTerminaServiceImpl extends GenericServiceApiImpl<DealerTermina> implements DealerTerminaService
{

    private DealerTerminaMapper dealerTerminaMapper;

    @Autowired(required = true)
    public DealerTerminaServiceImpl(DealerTerminaMapper dealerTerminaMapper)
    {
        super(dealerTerminaMapper);
        this.dealerTerminaMapper = dealerTerminaMapper;
    }

    /**
     * 保存终端商终止合作日志
     * @param dealerJoin
     * @param stopUserId
     * @return
     */
    @Override
    public DealerTermina insertDealerTermina(DealerJoin dealerJoin,String stopUserId)
    {
        DealerTermina dealerTermina = new DealerTermina();
        dealerTermina.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        dealerTermina.setTerminaTime(CalendarUtils.getCurrentLong());
        dealerTermina.setUserId(stopUserId);
        dealerTermina.setDealerId(dealerJoin.getDealerId());
        dealerTermina.setBrandId(dealerJoin.getBrandId());
        dealerTermina.setBrandsId(dealerJoin.getBrandsId());
        dealerTermina.setLogoName(dealerJoin.getLogoName());
        dealerTermina.setDomainName(dealerJoin.getDomainName());
        dealerTerminaMapper.insert(dealerTermina);
        return dealerTermina;
    }
}
