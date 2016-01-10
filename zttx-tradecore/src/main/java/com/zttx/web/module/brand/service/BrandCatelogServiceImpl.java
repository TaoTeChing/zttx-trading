/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.brand.entity.BrandCatelog;
import com.zttx.web.module.brand.mapper.BrandCatelogMapper;
import com.zttx.web.module.brand.model.BrandCatelogModel;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.mapper.DealDicMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.EntityUtils;

/**
 * 品牌商主营品类 服务实现类
 * <p>File：BrandCatelog.java </p>
 * <p>Title: BrandCatelog </p>
 * <p>Description:BrandCatelog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandCatelogServiceImpl extends GenericServiceApiImpl<BrandCatelog> implements BrandCatelogService
{
    @Autowired
    private DealDicMapper      dealDicMapper;
    
    private BrandCatelogMapper brandCatelogMapper;
    
    @Autowired(required = true)
    public BrandCatelogServiceImpl(BrandCatelogMapper brandCatelogMapper)
    {
        super(brandCatelogMapper);
        this.brandCatelogMapper = brandCatelogMapper;
    }
    
    @Override
    public List<BrandCatelogModel> getBrandCatelogByBrandId(String brandId)
    {
        List<BrandCatelogModel> brandCatelogModels = Lists.newArrayList();
        List<BrandCatelog> brandCatelogs = brandCatelogMapper.selectBrandCatelogsByBrandId(brandId);
        for (BrandCatelog brandCatelog : brandCatelogs)
        {
            BrandCatelogModel model = new BrandCatelogModel();
            EntityUtils.copyProperties(model, brandCatelog);
            model.setDealDic(this.selectDealDicByDealNo(brandCatelog.getDealNo()));
            brandCatelogModels.add(model);
        }
        return brandCatelogModels;
    }
    
    private DealDic selectDealDicByDealNo(Integer dealNo)
    {
        List<DealDic> dealDics = dealDicMapper.selectDealDicByDealNo(dealNo);
        if (dealDics != null && dealDics.size() == 1) { return dealDics.get(0); }
        return null;
    }
    
    /**
     * 修改品牌商经营类目（会替换掉之前的所有类目）
     * @author 陈建平
     * @param brandId
     * @param dealDicIds 
     */
    @Override
    public void updateBrandCatelogByClient(String brandId, String dealDicIds)
    {
        if (null == brandId || StringUtils.isBlank(dealDicIds)) { throw new IllegalArgumentException("请求参数为空"); }
        brandCatelogMapper.deleteBrandCatelogsByBrandId(brandId);
        String[] dealDicIdsList = dealDicIds.split("\\|");
        for (String dealDicId : dealDicIdsList)
        {
        	if(StringUtils.isNotBlank(dealDicId)){
        		BrandCatelog entity = new BrandCatelog();
                try
                {
                    DealDic dealDic = dealDicMapper.selectByPrimaryKey(dealDicId);
                    entity.setDealNo(dealDic.getDealNo());
                }
                catch (NullPointerException e)
                {
                    throw new IllegalArgumentException("品牌商经营类目不存在");
                }
                entity.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                entity.setBrandId(brandId);
                entity.setCreateTime(CalendarUtils.getCurrentLong());
                entity.setUpdateTime(CalendarUtils.getCurrentLong());
                entity.setDelFlag(false);
                brandCatelogMapper.insert(entity);
        	}
        }
    }
}
