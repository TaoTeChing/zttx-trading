/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.brand.entity.BrandesInfoRegional;
import com.zttx.web.module.brand.mapper.BrandesInfoRegionalMapper;
import com.zttx.web.module.brand.model.BrandesInfoRegionalModel;

/**
 * 品牌商品牌地区列表 服务实现类
 * <p>File：BrandesInfoRegional.java </p>
 * <p>Title: BrandesInfoRegional </p>
 * <p>Description:BrandesInfoRegional </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandesInfoRegionalServiceImpl extends GenericServiceApiImpl<BrandesInfoRegional> implements BrandesInfoRegionalService
{

    private BrandesInfoRegionalMapper brandesInfoRegionalMapper;

    @Autowired(required = true)
    public BrandesInfoRegionalServiceImpl(BrandesInfoRegionalMapper brandesInfoRegionalMapper)
    {
        super(brandesInfoRegionalMapper);
        this.brandesInfoRegionalMapper = brandesInfoRegionalMapper;
    }
    
    /**
     * 根据区域编码 获取 该区域所有授权的产品ID
     * @author chenjp
     * @param searchBean
     * @return
     */
    @Override
    public List<String> listProductIdByAreaNo(BrandesInfoRegionalModel searchBean){
        return brandesInfoRegionalMapper.listProductIdByAreaNo(searchBean);
    }
    
    /**
     * 删除品牌下的所有授权区域
     * @author chenjp
     * @param brandesId 品牌编号
     * @return
     */
    @Override
    public Integer deleteBrandesInfoRegional(String brandesId){
        return brandesInfoRegionalMapper.deleteBrandesInfoRegional(brandesId);
    }
    
    /**
     * 批量添加品牌授权区域
     * @author chenjp
     * @param brandesInfoRegionalModel
     */
    @Override
    public void insertBrandesInfoRegional(BrandesInfoRegionalModel brandesInfoRegionalModel){
    	List<BrandesInfoRegional> list = new ArrayList<BrandesInfoRegional>();
        if (brandesInfoRegionalModel.getAreaAry()!=null) {
            for (Integer areaNo : brandesInfoRegionalModel.getAreaAry()) {
                BrandesInfoRegional brandesInfoRegional = new BrandesInfoRegional();
                brandesInfoRegional.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandesInfoRegional.setBrandId(brandesInfoRegionalModel.getBrandId());
                brandesInfoRegional.setBrandesId(brandesInfoRegionalModel.getBrandesId());
                brandesInfoRegional.setAreaNo(areaNo);
                brandesInfoRegional.setCreateTime(CalendarUtils.getCurrentLong());
                list.add(brandesInfoRegional);
            }
            brandesInfoRegionalMapper.insertBatch(list);
        }
    }
    
    /**
     * 获取品牌授权区域
     * @author chenjp
     * @param searchBean
     * @return
     */
    @Override
    public List<BrandesInfoRegional> getBrandesInfoRegionalList(BrandesInfoRegionalModel searchBean){
        return brandesInfoRegionalMapper.getBrandesInfoRegionalList(searchBean);
    }

    @Override
    public BrandesInfoRegional findByBrandsIdAndAreaNo(String brandsId, Integer areaNo)
    {
        areaNo = areaNo/100 * 100;
        return brandesInfoRegionalMapper.findByBrandsIdAndAreaNo(brandsId,areaNo);
    }
}
