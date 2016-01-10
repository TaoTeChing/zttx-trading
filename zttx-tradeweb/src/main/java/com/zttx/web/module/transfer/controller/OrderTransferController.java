package com.zttx.web.module.transfer.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.transfer.service.OrderTransferService;

/**
 * <p>File：OrderTransferController.java</p>
 * <p>Title: 订单数据迁移控制器</p>
 * <p>Description:OrderTransferController </p>
 * <p>Copyright: Copyright (c)  15/10/8</p>
 * <p>Company: 8637.com</p>
 *
 * @author 李星
 * @version 1.0
 */
@Controller
@RequestMapping("/transfer/order")
public class OrderTransferController extends GenericController
{
    public static final Logger   logger   = LoggerFactory.getLogger(OrderTransferController.class);

    public static final Integer  pageSize = 15;
    
    // 订单信息迁移服务
    @Autowired
    private OrderTransferService orderTransferService;
    
    /**
     * 处理有优惠的订单
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/favourable")
    @RequiresPermissions(value = "brand:center")
    public JsonMessage favourable() throws BusinessException
    {
        logger.info("开始同步有优惠订单数据");
        Long rowNums = orderTransferService.findFavourableOrderInfoCount();
        logger.info("有优惠订单总记录数：" + rowNums);
        Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
        logger.info("处理批次：" + pageCounts);
        Pagination page = new Pagination(1, pageSize);
        for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
        {
            logger.info("第 " + currentPage + " 批");
            List<DealerOrder> list = orderTransferService.findFavourableOrderInfo(page);
            for (DealerOrder dealerOrder : list)
            {
                orderTransferService.apportionDiscountToDealerOrder(dealerOrder);
            }
            page = new Pagination(currentPage.intValue() + 1, pageSize);
        }
        logger.info("结束同步有优惠订单数据");
        return super.getJsonMessage(ClientConst.SUCCESS);
    }
    
    /**
     * 处理未完成的工厂店订单
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/factoryUnComplete")
    @RequiresPermissions(value = "brand:center")
    public JsonMessage factoryUnComplete() throws BusinessException
    {
        logger.info("开始同步未完成的工厂店订单数据");
        Long rowNums = orderTransferService.findUnCompleteFactoryOrderInfoCount();
        logger.info("未完成工厂店订单总记录数：" + rowNums);
        Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
        logger.info("处理批次：" + pageCounts);
        Pagination page = new Pagination(1, pageSize);
        for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
        {
            logger.info("第 " + currentPage + " 批");
            List<DealerOrder> list = orderTransferService.findUnCompleteFactoryOrderInfo(page);
            for (DealerOrder dealerOrder : list)
            {
                orderTransferService.processUnCompleteFactoryOrder(dealerOrder);
            }
            page = new Pagination(currentPage.intValue() + 1, pageSize);
        }
        logger.info("结束同步未完成的工厂店订单数据");
        return super.getJsonMessage(ClientConst.SUCCESS);
    }
    
    /**
     * 处理等待确认收货的工厂店订单
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/factoryWaitReceive")
    @RequiresPermissions(value = "brand:center")
    public JsonMessage factoryWaitReceive() throws BusinessException
    {
        logger.info("开始同步等待确认收货的工厂店订单数据");
        Long rowNums = orderTransferService.findWaitReceiveFactoryOrderInfoCount();
        logger.info("等待确认收货的工厂店订单总记录数：" + rowNums);
        Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
        logger.info("处理批次：" + pageCounts);
        Pagination page = new Pagination(1, pageSize);
        for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
        {
            logger.info("第 " + currentPage + " 批");
            List<DealerOrder> list = orderTransferService.findWaitReceiveFactoryOrderInfo(page);
            for (DealerOrder dealerOrder : list)
            {
                orderTransferService.processWaitReceiveFactoryOrder(dealerOrder, false);
            }
            List<DealerOrder> listHasComfirm = getOrderHasComfirm();
            for (DealerOrder dealerOrder : listHasComfirm)
            {
                orderTransferService.processWaitReceiveFactoryOrder(dealerOrder, true);
            }
            page = new Pagination(currentPage.intValue() + 1, pageSize);
        }
        logger.info("结束同步等待确认收货的工厂店订单数据");
        return super.getJsonMessage(ClientConst.SUCCESS);
    }
    
    // 产品列出的需单独处理的订单
    private List<DealerOrder> getOrderHasComfirm()
    {
        List<DealerOrder> result = Lists.newArrayList();
        Long[] waitComfirmedOrderIds = {3798025321L, 3764899999L}; // 需独立处理的订单,等待产品确认
        Long[] hasComfirmedOrderIds = {3728623624L, 3794066343L, 3764998276L, 3750048411L, 3746197776L, 3773683953L}; // 产品已确认需独立处理的订单
        for (Long orderId : hasComfirmedOrderIds)
        {
            result.add(orderTransferService.findByOrderId(orderId));
        }
        return result;
    }
}
