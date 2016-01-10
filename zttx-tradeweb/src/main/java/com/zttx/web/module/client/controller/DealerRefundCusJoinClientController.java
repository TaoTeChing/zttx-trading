package com.zttx.web.module.client.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.model.DealerRefundModel;
import com.zttx.web.module.dealer.service.DealerRefReplyService;
import com.zttx.web.module.dealer.service.DealerRefundService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerRefundCusJoinClientController.java</p>
 * <p>Title: 经销商退款操作，客户介入相关</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014 5 30 13:23:37</p>
 * <p>Company: 8637.com</p>
 * @author 李星
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/refundCusJoin")
public class DealerRefundCusJoinClientController extends GenericController
{
    @Autowired
    private DealerRefundService   dealerRefundService;
    
    @Autowired
    private DealerRefReplyService dealerRefReplyService;
    
    /**
     * 客服介入的退款表单列表
     * @param request
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/search")
    public JsonMessage search(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DealerRefundModel dealerRefund = new DealerRefundModel();
        BeanUtils.populate(dealerRefund, map);
        PaginateResult<DealerRefundModel> result = dealerRefundService.selectAllCusJoin(dealerRefund, page);
        if (null == result) { return super.getJsonMessage(ClientConst.DBERROR); }
        for (DealerRefundModel newDealerRefund : result.getList())
        {
            if (newDealerRefund.getSerProStatus().equals("1"))
            {
                newDealerRefund.setCusJoinState("客服介入处理中");
            }
            else if (newDealerRefund.getSerProStatus().equals("2"))
            {
                newDealerRefund.setCusJoinState("纠纷处理中");
            }
            else if (newDealerRefund.getSerProStatus().equals("3"))
            {
                newDealerRefund.setCusJoinState("处理完毕");
            }
            else if (newDealerRefund.getSerProStatus().equals("4"))
            {
                newDealerRefund.setCusJoinState("纠纷关闭");
            }
            else if (newDealerRefund.getSerProStatus().equals("5"))
            {
                newDealerRefund.setCusJoinState(":等待客服介入中");
            }
            if (newDealerRefund.getRefundState().equals("1"))
            {
                newDealerRefund.setMixRefundState("申请退款等待处理");
            }
            else if (newDealerRefund.getRefundState().equals("2"))
            {
                newDealerRefund.setMixRefundState("同意退货等待发货");
            }
            else if (newDealerRefund.getRefundState().equals("3"))
            {
                newDealerRefund.setMixRefundState("退货已发货");
            }
            else if (newDealerRefund.getRefundState().equals("4"))
            {
                newDealerRefund.setMixRefundState("拒绝退款");
            }
            else if (newDealerRefund.getRefundState().equals("5"))
            {
                newDealerRefund.setMixRefundState("拒绝退货");
            }
            else if (newDealerRefund.getRefundState().equals("6"))
            {
                newDealerRefund.setMixRefundState(" 退款关闭");
            }
            else if (newDealerRefund.getRefundState().equals("7"))
            {
                newDealerRefund.setMixRefundState(" 取消退款");
            }
            else if (newDealerRefund.getRefundState().equals("8"))
            {
                newDealerRefund.setMixRefundState("申请退款等待处理");
            }
            else if (newDealerRefund.getRefundState().equals("9"))
            {
                newDealerRefund.setMixRefundState("同意退款");
            }
            else if (newDealerRefund.getRefundState().equals("10"))
            {
                newDealerRefund.setMixRefundState("同意退货");
            }
        }
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查看
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view")
    public JsonMessage view(HttpServletRequest request, ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerRefundModel dealerRefund = dealerRefundService.getEntityById(MapUtils.getString(map, "refrenceId"));
        if (null == dealerRefund) { return super.getJsonMessage(ClientConst.DBERROR); }
        if (dealerRefund.getSerProStatus().equals("1"))
        {
            dealerRefund.setCusJoinState("客服介入处理中");
        }
        else if (dealerRefund.getSerProStatus().equals("2"))
        {
            dealerRefund.setCusJoinState("纠纷处理中");
        }
        else if (dealerRefund.getSerProStatus().equals("3"))
        {
            dealerRefund.setCusJoinState("处理完毕");
        }
        else if (dealerRefund.getSerProStatus().equals("4"))
        {
            dealerRefund.setCusJoinState("纠纷关闭");
        }
        else if (dealerRefund.getSerProStatus().equals("5"))
        {
            dealerRefund.setCusJoinState(":等待客服介入中");
        }
        if (dealerRefund.getRefundState().equals("1"))
        {
            dealerRefund.setMixRefundState("申请退款等待处理");
        }
        else if (dealerRefund.getRefundState().equals("2"))
        {
            dealerRefund.setMixRefundState("同意退货等待发货");
        }
        else if (dealerRefund.getRefundState().equals("3"))
        {
            dealerRefund.setMixRefundState("退货已发货");
        }
        else if (dealerRefund.getRefundState().equals("4"))
        {
            dealerRefund.setMixRefundState("拒绝退款");
        }
        else if (dealerRefund.getRefundState().equals("5"))
        {
            dealerRefund.setMixRefundState("拒绝退货");
        }
        else if (dealerRefund.getRefundState().equals("6"))
        {
            dealerRefund.setMixRefundState(" 退款关闭");
        }
        else if (dealerRefund.getRefundState().equals("7"))
        {
            dealerRefund.setMixRefundState(" 取消退款");
        }
        else if (dealerRefund.getRefundState().equals("8"))
        {
            dealerRefund.setMixRefundState("申请退款等待处理");
        }
        else if (dealerRefund.getRefundState().equals("9"))
        {
            dealerRefund.setMixRefundState("同意退款");
        }
        else if (dealerRefund.getRefundState().equals("10"))
        {
            dealerRefund.setMixRefundState("同意退货");
        }
        dealerRefund.setDealerRefReplies(dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId()));
        return super.getJsonMessage(CommonConst.SUCCESS, dealerRefund);
    }
    
    @ResponseBody
    @RequestMapping(value = "/handApply")
    public JsonMessage handApply(HttpServletRequest request, ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        Short action = MapUtils.getShort(map, "action");
        String serProResult = MapUtils.getString(map, "serProResult");
        DealerRefund dealerRefund = dealerRefundService.getEntityById(refrenceId);
        if (dealerRefund == null) { throw new IllegalArgumentException(); }
        dealerRefund.setRefrenceId(refrenceId);
        dealerRefund.setSerProStatus(action);
        dealerRefund.setSerProResult(serProResult);
        dealerRefundService.updateSerProStatusResult(dealerRefund);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 客服介入处理中
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cusjoining")
    public JsonMessage cusjoining(HttpServletRequest request, ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        DealerRefund dealerRefund = dealerRefundService.getEntityById(refrenceId);
        if (dealerRefund == null) { throw new IllegalArgumentException(); }
        return super.getJsonMessage(dealerRefundService.updateCusjoining(dealerRefund));
    }
    
    /**
     * 客服取消处理
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/refuseJoin")
    public JsonMessage refuseJoin(HttpServletRequest request, ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        String serProResult = MapUtils.getString(map, "serProResult");
        DealerRefund dealerRefund = dealerRefundService.getEntityById(refrenceId);
        dealerRefund.setSerProResult(serProResult);
        return super.getJsonMessage(dealerRefundService.updateRefuseJoin(dealerRefund));
    }
    
    /**
     * 客服修改处理
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateModify")
    public JsonMessage updateModify(HttpServletRequest request, ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        String mount = MapUtils.getString(map, "realityRefundAmount");
        String date = MapUtils.getString(map, "nextActTime");
        Long nextActTime = CalendarUtils.getLongFromTime(date, "yyyy-MM-dd");
        BigDecimal refundAmount = new BigDecimal(mount);
        DealerRefundModel dealerRefund = dealerRefundService.getEntityById(refrenceId);
        // 如果当前状态为：申请退款等待处理
        if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_HANDLE)
        {
            // 如果是需要退货，则是等待确认收货
            if (dealerRefund.getNeedRefund())
            {
                dealerRefund.setRefundState(DealerConstant.DealerRefund.SHIPED);
            }
            else
            {
                // 修改状态为，等待品牌商确认退款；
                dealerRefund.setRefundState(DealerConstant.DealerRefund.WAIT_HANDLE);
            }
        }
        // 如果当前状态为：拒绝退款等待处理，修改状态为申请退款，等待品牌商确认退款；
        if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_REFUND)
        {
            dealerRefund.setRefundState(DealerConstant.DealerRefund.WAIT_HANDLE);
        }
        // 如果当前状态为：拒绝退货等待处理，修改状态为已发货，等待品牌商确认收货
        if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_RETURN)
        {
            dealerRefund.setRefundState(DealerConstant.DealerRefund.SHIPED);
        }
        dealerRefund.setNextActTime(nextActTime);
        dealerRefund.setRefundAmount(refundAmount);
        dealerRefund.setComPayment(dealerRefund.getTotalAmount().subtract(dealerRefund.getRefundAmount()));
        return super.getJsonMessage(dealerRefundService.updateModify(dealerRefund));
    }
}
