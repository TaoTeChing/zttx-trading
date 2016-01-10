/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.consts.TagCommonConst;
import com.zttx.web.module.brand.mapper.BrandViewContactMapper;
import com.zttx.web.module.dealer.entity.DealerGroom;
import com.zttx.web.module.dealer.mapper.DealerGroomMapper;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 推荐给品牌商的经销商 服务实现类
 * <p>File：DealerGroom.java </p>
 * <p>Title: DealerGroom </p>
 * <p>Description:DealerGroom </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerGroomServiceImpl extends GenericServiceApiImpl<DealerGroom> implements DealerGroomService {

    private DealerGroomMapper dealerGroomMapper;

    @Autowired
    private BrandViewContactMapper brandViewContactMapper;
    @Autowired
    private DealerInfoMapper dealerInfoMapper;

    @Autowired(required = true)
    public DealerGroomServiceImpl(DealerGroomMapper dealerGroomMapper) {
        super(dealerGroomMapper);
        this.dealerGroomMapper = dealerGroomMapper;
    }

    /**
     * 推荐的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> search(Pagination pagination, DealerGroom info) {
        PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<Map<String, Object>>();
        List<Map<String, Object>> list = dealerGroomMapper.search(pagination, info);
        if (null != list && !list.isEmpty()) {
            for (Map<String, Object> item : list) {
                item.put("areaName", TagCommonConst.getFullArea(item.get("province"), item.get("city"), item.get("area"), "/"));
                int isExist = brandViewContactMapper.isExist(info.getBrandId(), item.get("id").toString(), null);
                if (isExist > 0) {
                    item.put("isExist", true);
                } else {
                    item.put("isExist", false);
                }
                String brandName = dealerInfoMapper.searchBrandsNameList((String) item.get("id"));
                item.put("brandName", brandName);
            }
            paginateResult.setList(list);
        }

        paginateResult.setPage(pagination);
        return paginateResult;
    }
    
    
    /**
     * 查询指定品牌商所有推荐的终端商
     *
     * @param fitler
     * @return
     */
    @Override
    public List<DealerGroom> listDealerGrooms(DealerGroom fitler){
    	return dealerGroomMapper.listDealerGrooms(fitler);
    }
}
