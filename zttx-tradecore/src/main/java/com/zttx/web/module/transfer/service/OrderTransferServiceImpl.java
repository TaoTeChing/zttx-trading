package com.zttx.web.module.transfer.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.Pagination;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrders;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.transfer.mapper.OrderTransferMapper;

/**
 * <p>File：ProductTransferServiceImpl.java</p>
 * <p>Title: 订单信息迁移服务</p>
 * <p>Description:ProductTransferServiceImpl </p>
 * <p>Copyright: Copyright (c)  15/10/8</p>
 * <p>Company: 8637.com</p>
 *
 * @author 李星
 * @version 1.0
 */
@Service
public class OrderTransferServiceImpl implements OrderTransferService
{
    @Autowired(required = true)
    private OrderTransferMapper orderTransferMapper;
    
    @Autowired
    private DealerJoinMapper    dealerJoinMapper;
    
    @Override
    public Long findFavourableOrderInfoCount()
    {
        return orderTransferMapper.findFavourableOrderInfoCount();
    }
    
    @Override
    public List<DealerOrder> findFavourableOrderInfo(Pagination page)
    {
        return orderTransferMapper.findFavourableOrderInfo(page);
    }
    
    /**
     1、订单若有优惠或加价，需将优惠/加价金额优惠到各个SKU（仅具体到SKU，不具体到SKU产品单价）；
     2、优惠或加价计算方式：
     订单内共有3个SKU，下单合计价格分别为：100、200、300；当前订单优惠200元，则3个SKU优惠价格所占比例分别为：
     SKU A=1/6； SKU B=1/3；SKU C=1/2；
     那么优惠价格分别为：
     SKU A=100-200*1/6=66；
     SKU B=200-200*1/3=134；
     SKU C=200-34-66=100；
     需注意：最后一个SKU的优惠价格，需用总优惠价格-已优惠价格，不能通过百分比进行换算，否则会有误差；所占比例均保留至小数点后两位；
     * @param dealerOrder
     * @author 李星
     */
    @Override
    public void apportionDiscountToDealerOrder(DealerOrder dealerOrder)
    {
        List<DealerOrders> dealerOrdersList = orderTransferMapper.findDealerOrdersByOrderIdAndBrandId(dealerOrder.getRefrenceId(), dealerOrder.getBrandId());
        // 当订单有优惠(加价)
        if (null != dealerOrder.getAdjustPrice() && dealerOrder.getAdjustPrice().abs().compareTo(BigDecimal.ZERO) != 0)
        {
            BigDecimal totalSkuMoney = getTotalSkuMoney(dealerOrdersList);
            int c = 0;
            BigDecimal adjustMoney = dealerOrder.getAdjustPrice().abs(); // 订单优惠(加价)的数值
            BigDecimal tempAdjustMoney = adjustMoney;
            for (DealerOrders dealerOrders : dealerOrdersList)
            {
                BigDecimal skuMoney = getSkuMoney(dealerOrders);
                BigDecimal adjustPrice = skuMoney.subtract(tempAdjustMoney);
                if (c < dealerOrdersList.size() - 1)
                {
                    // 分摊,保留2位小数,四舍五入
                    BigDecimal willDiscount = adjustMoney.multiply(skuMoney).divide(totalSkuMoney, 2, BigDecimal.ROUND_HALF_EVEN);
                    // 优惠
                    if (dealerOrder.getAdjustPrice().compareTo(BigDecimal.ZERO) < 0) adjustPrice = skuMoney.subtract(willDiscount.abs());
                    // 加价
                    else adjustPrice = skuMoney.add(willDiscount.abs());
                    // 逐步减去已经分摊的;最后一个SKU的优惠价格不采用比例换算,采用总优惠价格-前面已优惠价格计算
                    tempAdjustMoney = tempAdjustMoney.subtract(willDiscount.abs());
                }
                // 保存优惠/加价后的SKU价格
                orderTransferMapper.updateDealerOrdersAdjustPrice(dealerOrders.getRefrenceId(), adjustPrice);
                c++;
            }
        }
    }
    
    /**
     * 获得订单所有SKU金额汇总值
     * @param dealerOrdersList
     * @return
     * @author 李星
     */
    private BigDecimal getTotalSkuMoney(List<DealerOrders> dealerOrdersList)
    {
        BigDecimal totalSkuMoney = new BigDecimal(0);
        for (DealerOrders dealerOrders : dealerOrdersList)
        {
            totalSkuMoney = totalSkuMoney.add(getSkuMoney(dealerOrders));
        }
        return totalSkuMoney;
    }
    
    /**
     * 获取SKU金额
     * @param dealerOrders
     * @return
     * @author 李星
     */
    private BigDecimal getSkuMoney(DealerOrders dealerOrders)
    {
        BigDecimal quantity = new BigDecimal(dealerOrders.getQuantity());
        BigDecimal skuMoney = dealerOrders.getPrice().multiply(quantity);
        // 有折扣
        if (dealerOrders.getDiscount() != null && dealerOrders.getDiscount().compareTo(BigDecimal.ZERO) != 0)
        {
            skuMoney = dealerOrders.getPrice().multiply(quantity).multiply(dealerOrders.getDiscount());
        }
        return skuMoney;
    }
    
    @Override
    public Long findUnCompleteFactoryOrderInfoCount()
    {
        return orderTransferMapper.findUnCompleteFactoryOrderInfoCount();
    }
    
    @Override
    public List<DealerOrder> findUnCompleteFactoryOrderInfo(Pagination page)
    {
        return orderTransferMapper.findUnCompleteFactoryOrderInfo(page);
    }
    
    @Override
    public void processUnCompleteFactoryOrder(DealerOrder dealerOrder)
    {
        // 订单有运费时
        if (null != dealerOrder.getAdjustFreight() && dealerOrder.getAdjustFreight().compareTo(BigDecimal.ZERO) > 0)
        {
            // 订单未发货,将订单置于付款状态,终端商在线支付运费;付款成功回调时会自动将货款添加到已用授信额度
            if (dealerOrder.getOrderStatus() == DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER)
            {
                dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY);
            }
        }
        else // 没有运费
        {
            // 更新已用授信额度
            DealerJoin dealerJoin = dealerJoinMapper.selectDealerJoinByDealerIdAndBrandsId(dealerOrder.getDealerId(), dealerOrder.getBrandsId());
            if (null != dealerJoin)
            {
                BigDecimal creditCurrent = dealerJoin.getCreditCurrent();
                dealerJoin.setCreditCurrent(creditCurrent.add(dealerOrder.getProductPrice()));
                dealerJoinMapper.updateByPrimaryKey(dealerJoin);
            }
        }
    }
    
    @Override
    public Long findWaitReceiveFactoryOrderInfoCount()
    {
        return orderTransferMapper.findWaitReceiveFactoryOrderInfoCount();
    }
    
    @Override
    public List<DealerOrder> findWaitReceiveFactoryOrderInfo(Pagination page)
    {
        return orderTransferMapper.findWaitReceiveFactoryOrderInfo(page);
    }
    
    @Override
    public DealerOrder findByOrderId(Long orderId)
    {
        return orderTransferMapper.findByOrderId(orderId);
    }
    
    @Override
    public void processWaitReceiveFactoryOrder(DealerOrder dealerOrder, boolean hasComfirm)
    {
        if (hasComfirm)
        {
            orderTransferMapper.updateorderStatus(dealerOrder.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
        }
        else
        {
            if (null == dealerOrder.getPayment())
            {
                dealerOrder.setPayment(BigDecimal.ZERO);
            }
            if (null == dealerOrder.getClearingAmount())
            {
                dealerOrder.setClearingAmount(BigDecimal.ZERO);
            }
            if (dealerOrder.getPayment().compareTo(dealerOrder.getClearingAmount()) <= 0)
            {
                orderTransferMapper.updateorderStatus(dealerOrder.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
            }
        }
    }
}
