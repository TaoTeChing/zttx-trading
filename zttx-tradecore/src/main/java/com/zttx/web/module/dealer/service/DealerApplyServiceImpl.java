/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandCheck;
import com.zttx.web.module.brand.mapper.BrandCheckMapper;
import com.zttx.web.module.dealer.entity.DealerApply;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.mapper.DealerApplyMapper;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.utils.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 经销商加盟申请 服务实现类
 * <p>File：DealerApply.java </p>
 * <p>Title: DealerApply </p>
 * <p>Description:DealerApply </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerApplyServiceImpl extends GenericServiceApiImpl<DealerApply> implements DealerApplyService {

    @Autowired
    private DealerJoinMapper dealerJoinMapper;
    @Autowired
    private BrandCheckMapper brandCheckMapper;

    private DealerApplyMapper dealerApplyMapper;

    @Autowired(required = true)
    public DealerApplyServiceImpl(DealerApplyMapper dealerApplyMapper) {
        super(dealerApplyMapper);
        this.dealerApplyMapper = dealerApplyMapper;
    }

    /*
    * 终止合作
    *
    */
    @Override
    public void insertStopApply(String uuid, String brandId) throws BusinessException {
        DealerApply dealerApply = dealerApplyMapper.findByBrandIdAndId(uuid, brandId);
        dealerApply.setAuditState(DealerConstant.DealerApply.AUDIT_STATE_FAILURE);
        dealerApplyMapper.updateByPrimaryKey(dealerApply);
        DealerJoin dealerJoin = dealerJoinMapper.findByDealerIdBrandIdBrandsId(dealerApply.getDealerId(), dealerApply.getBrandId(), dealerApply.getBrandsId());
        dealerJoin.setEndTime(CalendarUtils.getCurrentLong());
        dealerJoin.setJoinState(DealerConstant.DealerJoin.STOP_COOPERATION);
        dealerJoin.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerJoinMapper.updateByPrimaryKey(dealerJoin);
        BrandCheck brandCheck = brandCheckMapper.findByDealerIdBrandIdBrandsId(dealerApply.getDealerId(), dealerApply.getBrandId(), dealerApply.getBrandsId());
        brandCheck.setCheckState(BrandConstant.BrandCheckConst.STOP_COOPERATION);
        brandCheckMapper.updateByPrimaryKey(brandCheck);
    }

    @Override
    public Integer addDealerApply(DealerApply dealerApply) {

            DealerApply _dealerApply = dealerApplyMapper.selectDealerApplyBy(dealerApply.getDealerId(), dealerApply.getBrandsId());
            // 记录不存 or 记录审核不通
            if (_dealerApply == null)
            {
                dealerApply.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                dealerApply.setApplyTime(CalendarUtils.getCurrentLong());
                dealerApply.setAuditState(DealerConstant.DealerApply.AUDIT_STATE_UNVERFIY);
                dealerApplyMapper.insert(dealerApply);
                return DealerConstant.DealerApply.APPLY_RESULT_SUCCESS;
            }
            else
            {
                return DealerConstant.DealerApply.APPLY_RESULT_PROCESS;
            }
    }


    /**
     * 撤消申请
     *
     * @param refrenceId
     */
    @Override
    public void delApply(String refrenceId)
    {
        DealerApply dealerApply = dealerApplyMapper.selectByPrimaryKey(refrenceId);
        if (null != dealerApply)
        {
            dealerApply.setAuditState(DealerConstant.DealerJoin.DIS_APPLY);
            dealerApply.setUndoTime(CalendarUtils.getCurrentLong());
            dealerApplyMapper.updateByPrimaryKey(dealerApply);
        }
    }
}
