package com.zttx.web.module.dealer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.service.ProductPriceService;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.mapper.DealerShoperMapper;
import com.zttx.web.module.dealer.model.ProductFilter;

/**
 * <p>File:DealerGrantServiceImpl</p>
 * <p>Title: 授权产品逻辑实现层</p>
 * <p>Description:  虚拟实现类，该类的实体对象不存在，只是用来处理授权产品的逻辑</p>
 * <p>Copyright: Copyright (c)2015/8/17 20:03</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
@Service
public class DealerGrantServiceImpl implements DealerGrantService
{
    @Autowired
    private DealerJoinMapper    dealerJoinMapper;
    
    @Autowired
    private DealerShoperMapper  dealerShoperMapper;
    
    @Autowired
    private DealerJoinService   dealerJoinService;
    
    @Autowired
    private ProductPriceService productPriceService;
    
    @Override
    public PaginateResult<Map<String, Object>> selectGrantProductPage(Pagination pagination, ProductFilter filter) throws BusinessException
    {
        if (null != filter && null != filter.getDealerId())
        {
            filter.setPage(pagination);
            List<Map<String, Object>> mapList = dealerJoinMapper.selectGrantProductList(filter);
            for (Map<String, Object> map : mapList)
            {
                map.put("isPoint", dealerJoinService.isSupportPoint(filter.getDealerId(), (String) map.get("productId")));// 是否支持返点：查询当前数据库 产品支持返点+返点加盟
                Map<String, Object> validMap = productPriceService.toConfirmProTypeAndPrice(map);
                if (null != validMap)
                {
                    map.putAll(validMap); // 将产品的有效性与价格装入map
                }
            }
            if (null != filter.getKeyWord()) // 快速下单 产品是否已经加入购物车
            {
                for (Map<String, Object> map : mapList)
                {
                    map.put("isAdd", null != dealerShoperMapper.selectDealerShoperBy(filter.getDealerId(), (String) map.get("productId")));
                }
            }
            return new PaginateResult<>(pagination, mapList);
        }
        return null;
    }
    
    @Override
    public List<Map<String, Object>> selectGrantCata(String dealerId)
    {
        if (null != dealerId) { return dealerJoinMapper.selectGrantCataList(dealerId); }
        return null;
    }
}
