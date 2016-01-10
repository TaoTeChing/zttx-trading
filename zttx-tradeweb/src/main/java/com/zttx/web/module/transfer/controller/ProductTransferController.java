package com.zttx.web.module.transfer.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.module.transfer.service.ProductTransferService;

/**
 * <p>File：ProductTransferController.java</p>
 * <p>Title: 产品数据迁移控制器</p>
 * <p>Description:ProductTransferController </p>
 * <p>Copyright: Copyright (c)  15/10/8</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/transfer/product")
public class ProductTransferController extends GenericController
{
    public static final Logger     logger   = LoggerFactory.getLogger(ProductTransferController.class);
    
    public static final Integer    pageSize = 15;
    
    // 产品信息迁移服务
    @Autowired
    private ProductTransferService productTransferService;
    
    /**
     * 处理拿样产品数据
     *
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/sample")
    @RequiresPermissions(value = "brand:center")
    public JsonMessage sample() throws BusinessException
    {
        logger.info("开始同步拿样产品数据");
        Long rowNums = productTransferService.findSampleProductInfoCount();
        logger.info("拿样产品总记录数：" + rowNums);
        Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
        logger.info("处理批次：" + pageCounts);
        Pagination page = new Pagination(1, pageSize);
        for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
        {
            logger.info("第 " + currentPage + " 批");
            List<Map<String, Object>> mapList = productTransferService.findSampleProductInfo(page);
            for (Map<String, Object> map : mapList)
            {
                String productId = MapUtils.getString(map, "refrenceId");
                BigDecimal directPrice = productTransferService.getMaxSkuDriectPrice(productId);
                logger.info("拿样产品编号：" + productId + " 最高直供价格:" + directPrice.toString());
                productTransferService.modifySkuSamplePrice(productId, directPrice);
            }
            page = new Pagination(currentPage.intValue() + 1, pageSize);
        }
        logger.info("结束同步拿样产品数据");
        return super.getJsonMessage(ClientConst.SUCCESS);
    }
    
    /**
     * 处理授信产品数据迁移功能
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/credit")
    @RequiresPermissions(value = "brand:center")
    public JsonMessage credit() throws BusinessException
    {
        logger.info("开始同步授信产品数据");
        Long rowNums = productTransferService.findFactoryActivityProductCount();
        logger.info("授信产品总记录数：" + rowNums);
        Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
        logger.info("处理批次：" + pageCounts);
        Pagination page = new Pagination(1, pageSize);
        for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
        {
            logger.info("第 " + currentPage + " 批");
            // 找出所有参加过工厂店活动的产品信息
            List<Map<String, Object>> mapList = productTransferService.findFactoryActivityProduct(page);
            // 指修改产品的授信状态
            productTransferService.modifyProductCreditState(mapList);
            for (Map<String, Object> map : mapList)
            {// 根据产品编号批量将工厂店活动价设置到授信价中去
                String productId = MapUtils.getString(map, "refrenceId");
                logger.info("授信产品编号：" + productId + "");
                productTransferService.modifySkuCreditPrice(productId);
            }
            page = new Pagination(currentPage.intValue() + 1, pageSize);
        }
        logger.info("结束同步授信产品数据");
        return super.getJsonMessage(ClientConst.SUCCESS);
    }
}
