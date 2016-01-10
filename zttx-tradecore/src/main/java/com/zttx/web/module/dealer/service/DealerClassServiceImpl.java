/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.mapper.DealDicMapper;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.module.dealer.entity.DealerClass;
import com.zttx.web.module.dealer.mapper.DealerClassMapper;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

/**
 * 经销商经营品类l 服务实现类
 * <p>File：DealerClass.java </p>
 * <p>Title: DealerClass </p>
 * <p>Description:DealerClass </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerClassServiceImpl extends GenericServiceApiImpl<DealerClass> implements DealerClassService
{

    private DealerClassMapper dealerClassMapper;
    
    @Autowired
    private DealDicMapper dealDicMapper;
    
    @Autowired
    private DealDicService  dealDicService;

    @Autowired(required = true)
    public DealerClassServiceImpl(DealerClassMapper dealerClassMapper)
    {
        super(dealerClassMapper);
        this.dealerClassMapper = dealerClassMapper;
    }

    @Override
    public List<Integer> findByDealerId(String dealerId) {
        return dealerClassMapper.findByDealerId(dealerId);
    }
    
    /**
	 * 通过经销商ID来查询所有对应的品类编号
	 * @author 陈建平
	 * @param dealerId
	 * @return
	 */
    @Override
    public List<Integer> findDealNoBy(String dealerId){
    	return dealerClassMapper.findDealNoBy(dealerId);
    }
    
    /**
     * 根据经销商ID删除所有对应的品类编号
     * @author 陈建平
     * @param dealerId
     */
    @Override
    public void deleteByDealerInfoId(String dealerId){
    	dealerClassMapper.deleteByDealerInfoId(dealerId);
    }
    
    @Override
    public List<DealDic> findbyId(String dealerId)
    {
        DealerClass param = new DealerClass();
        param.setDealerId(dealerId);
        List<DealerClass> dealerClassList = findList(param);
        
        if (CollectionUtils.isEmpty(dealerClassList)) { return Lists.newArrayList(); }
        
        List<Integer> list =new ArrayList<Integer>();
        
        for(DealerClass dealerClass :dealerClassList)
        {
            list.add(dealerClass.getDealNo());
        }
        return dealDicMapper.getDealDicByDealNos(list);
    }
}
