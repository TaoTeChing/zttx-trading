package com.zttx.web.dubbo.service;

import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.entity.DealerOrders;
import com.zttx.web.module.dealer.mapper.DealerOrdersMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DealerOrderItemsServiceImpl
 *
 * @author 江枫林
 * @date 2015/9/10
 */
@Service
public class DealerOrderItemsServiceImpl implements DealerOrderItemsService {


    @Autowired
    DealerOrdersMapper dealerOrdersMapper;
    @Autowired
    ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;

    /**
     * 获取品牌商所有订单项数据
     *
     * @param pagination
     * @param dealerOrders
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> getDealerOrders(Pagination pagination, DealerOrders dealerOrders) {
        PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<Map<String, Object>>();
        List<Map<String, Object>> result = dealerOrdersMapper.getDealerOrdersByUpdateTime(pagination, dealerOrders);
        try {
            for (Map<String, Object> map : result) {
                List<String> skuIdList = new ArrayList<String>();
                skuIdList.add(MapUtils.getString(map, "productSkuId"));
                
                List<ProductSku> skuList = productSkuInfoDubboConsumer.findByProductSkuIds(skuIdList);
                if(skuList!=null&&skuList.size()>0){
                    map.put("attrValues", skuList.get(0).getAttrValueList());
                }
            }
            paginateResult.setList(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        paginateResult.setPage(pagination);
        return paginateResult;
    }

}
