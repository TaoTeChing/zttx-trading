/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.common.entity.ParityColumn;
import com.zttx.web.module.common.entity.ProductParity;
import com.zttx.web.module.common.mapper.ParityColumnMapper;
import com.zttx.web.module.common.mapper.ProductParityMapper;
import com.zttx.web.module.common.model.ParityModel;

/**
 * 产品比价表 服务实现类
 * <p>File：ProductParity.java </p>
 * <p>Title: ProductParity </p>
 * <p>Description:ProductParity </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductParityServiceImpl extends GenericServiceApiImpl<ProductParity> implements ProductParityService
{

    private ProductParityMapper productParityMapper;
    @Autowired
    private ParityColumnMapper parityColumnMapper;

    @Autowired(required = true)
    public ProductParityServiceImpl(ProductParityMapper productParityMapper)
    {
        super(productParityMapper);
        this.productParityMapper = productParityMapper;
    }

    @Override
    public void addOrUpdate(ParityModel model, String productId)
    {
            List<ProductParity> parityList=model.getParity();
            if(parityList==null){
                return;
            }
            for(int i=0;i<parityList.size();i++){
                ProductParity parity=productParityMapper.findByParityColumnIdAndProductId(parityList.get(i).getParityId(),productId);
                if(parity==null){
                    parity=new ProductParity();
                    parity.setCreateTime(CalendarUtils.getCurrentTime());
                    parity.setDelFlag(false);
                    parity.setIsShow(true);
                    parity.setParityId(parityList.get(i).getParityId());
                    parity.setPrice(parityList.get(i).getPrice());
                    parity.setProductId(productId);
                    parity.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    parity.setUpdateTime(CalendarUtils.getCurrentTime());
                    parity.setUrl(parityList.get(i).getUrl());
                    productParityMapper.insert(parity);
                    continue;
                }
                parity.setPrice(parityList.get(i).getPrice());
                parity.setUrl(parityList.get(i).getUrl());
                parity.setUpdateTime(CalendarUtils.getCurrentTime());
                productParityMapper.updateByPrimaryKey(parity);
            }
         
        
    }

    @Override
    public List<Map<String,Object>> findParityModel(String productId) 
    {
        ProductParity parity=new ProductParity();
        parity.setProductId(productId);
        List<Map<String,Object>> model=productParityMapper.findParityModel(parity);
        return model;
       /* ParityModel model=new ParityModel();
        model.setModify(1);
        List<ParityColumn> columnList=parityColumnMapper.selectAllByProductId(productId); 
        for(int i=0;i<columnList.size();i++){
            ParityColumn column=columnList.get(i);
            ProductParity productParity=productParityMapper.findByParityColumnId(column.getRefrenceId());
            if(column.getName().equals(ParityModel.ALBB)){
                model.setAlbbName(column.getName());
                model.setAlbbLink(productParity.getUrl());
                model.setAlbbPrice(productParity.getPrice()==null?null:productParity.getPrice().doubleValue());
            }else if(column.getName().equals(ParityModel.QT)){
                model.setQtName(column.getName());
                model.setQtLink(productParity.getUrl());
                model.setQtPrice(productParity.getPrice()==null?null:productParity.getPrice().doubleValue());
            }else if(column.getName().equals(ParityModel.REF)){
                model.setRefName(column.getName());
                model.setRefPrice(productParity.getPrice()==null?null:productParity.getPrice().doubleValue());
            }else if(column.getName().equals(ParityModel.TM)){
                model.setTmName(column.getName());
                model.setTmLink(productParity.getUrl());
                model.setTmPrice(productParity.getPrice()==null?null:productParity.getPrice().doubleValue());
            }else if(column.getName().equals(ParityModel.VPH)){
                model.setVphName(column.getName());
                model.setVphLink(productParity.getUrl());
                model.setVphPrice(productParity.getPrice()==null?null:productParity.getPrice().doubleValue());
            }
        }*/
        //return model;
    }
}
