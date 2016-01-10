/*
 * @(#)TransferNewAddSize.java 2015-9-25 下午12:32:25
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.transfer.main;

import java.util.List;

import com.zttx.transfer.biz.ProductService;
import com.zttx.transfer.biz.SkuAttrValueService;
import com.zttx.transfer.biz.SkuService;
import com.zttx.transfer.utils.DbModelHelper;

/**
 * <p>File：TransferNewAddSize.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-25 下午12:32:25</p>
 * <p>Company: 8637.com</p>
 *
 * @author 黄小平
 * @version 1.0
 */
public class TransferNewAddSize {
    private static ProductService productService = new ProductService();

    private static SkuService skuService = new SkuService();

    private static SkuAttrValueService skuAttrValueService = new SkuAttrValueService();

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("参数错误");
            return;
        }
        String dealNo = args[0];
        List<String> attrItemIdAndAttrId = skuAttrValueService.findDefaultItemId(dealNo);
        List<String> productList = productService.findProductByDealDicNo(dealNo);
        for (int i = 0; i < productList.size(); i++) {
            List<String> skuId = skuService.updateSku(productList.get(i), attrItemIdAndAttrId.get(0));
            for (int j = 0; j < skuId.size(); j++) {
                skuAttrValueService.updateBySkuId(skuId.get(j), productList.get(i), attrItemIdAndAttrId.get(0), attrItemIdAndAttrId.get(1));
            }
        }
        DbModelHelper.getInstance().release(null, null, DbModelHelper.getInstance().getConnection());
    }
}
