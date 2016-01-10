package com.zttx.web.service.test;

import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.dubbo.service.DealerOrderDubboService;
import com.zttx.web.module.common.service.ProductViewLogService;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.model.ProductFilter;
import com.zttx.web.module.dealer.service.DealerOrdercService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>File:DealerShoperTest</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/8/11 18:52</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
//我的产品库测试
public class DealerShoperTest extends GenericTest{

    @Autowired
    private DealerOrdercService  dealerOrdercService;
    @Autowired
    private ProductViewLogService productViewLogService;
    @Autowired
    private DealerOrderDubboService dealerOrderDubboService;

    @Test
    public void dealerShoperOrderc_list()     //测试经销商常进款式
    {
        Pagination pagination = new Pagination();
        ProductFilter productFilter = new ProductFilter();
        String dealerId = "E30EA1437E0343D6907138D67641ED39";
     /*   PaginateResult paginateResult = dealerOrdercService.findOrdercProductsByDealerId(dealerId, pagination, productFilter);
        System.out.println("paginateResult = " + paginateResult);*/
    }

    @Test
    public void dealerShoperProduct_list()     //测试经销商浏览记录
    {
        Pagination pagination = new Pagination();
        ProductFilter productFilter = new ProductFilter();
        String dealerId = "2F509780FFC54E20A4C7E264A74BBA6E";
      /*  PaginateResult paginateResult = productViewLogService.getProductViewLogPaginateResult(dealerId, pagination, productFilter);
        System.out.println("paginateResult = " + paginateResult);*/
    }
    @Test
    public void dealerOrderErpSearch() throws BusinessException {
        DealerOrder dealerOrder = new DealerOrder();
        dealerOrder.setDealerId("77425393EC194E23BC3A7FB6693BBA9F");
        Pagination page = new Pagination();
        dealerOrderDubboService.search(dealerOrder,page);
    }



}
