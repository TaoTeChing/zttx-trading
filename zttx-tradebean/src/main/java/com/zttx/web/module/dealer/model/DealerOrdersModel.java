package com.zttx.web.module.dealer.model;

import com.alibaba.fastjson.JSONArray;
import com.zttx.web.module.dealer.entity.DealerOrders;

import java.math.BigDecimal;

/**
 * DealerOrdersModel
 *
 * @author 江枫林
 * @date 2015/9/10
 */
public class DealerOrdersModel extends DealerOrders {
    private JSONArray attrs;

    private String     sourceId;

    private JSONArray  jsonArray;     // 配货的具体数据

    private boolean    order_blank;   // 配货是否成功

    private BigDecimal skuDirectPrice;

    public boolean isOrder_blank()
    {
        return order_blank;
    }

    public void setOrder_blank(boolean order_blank)
    {
        this.order_blank = order_blank;
    }

    public JSONArray getJsonArray()
    {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray)
    {
        this.jsonArray = jsonArray;
    }

    public JSONArray getAttrs()
    {
        return attrs;
    }

    public void setAttrs(JSONArray attrs)
    {
        this.attrs = attrs;
    }

    public String getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(String sourceId)
    {
        this.sourceId = sourceId;
    }

    public BigDecimal getSkuDirectPrice()
    {
        return skuDirectPrice;
    }

    public void setSkuDirectPrice(BigDecimal skuDirectPrice)
    {
        this.skuDirectPrice = skuDirectPrice;
    }
}
