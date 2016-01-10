package com.zttx.web.module.common.controller;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.entity.DealerShoper;
import com.zttx.web.module.dealer.service.DealerShoperService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>File：DealerShopperCartController.java </p>
 * <p>Title: DealerShopperCartController </p>
 * <p>Description: DealerShopperCartController </p>
 * <p>Copyright: Copyright (c) 15/9/23</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common")
public class DealerShopperCartController extends GenericController
{
    @Autowired
    private DealerShoperService dealerShoperService;
    
    /**
     * 提供首页取购物车信息
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/dealerShoper/homeCart", method = RequestMethod.GET)
    public JsonMessage homeCartIndex() throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        if (null == userPrincipal || !userPrincipal.getUserType().equals(DealerConstant.userType.DEALER_TYPE)) { return this.getJsonMessage(CommonConst.FAIL); }
        //只取3条数据
        List<DealerShoper> dealerShoperList = dealerShoperService.selectDealerShoperBy(userPrincipal.getRefrenceId(), Boolean.TRUE);
        int dealerShoperCount = dealerShoperService.getShoperCountByUserId(userPrincipal.getRefrenceId()).intValue();
        if (dealerShoperCount > 3)
        {
            dealerShoperCount = dealerShoperCount - 3;
        }else{
            dealerShoperCount = 0;
        }
        JsonMessage jsonMessage = new JsonMessage(CommonConst.SUCCESS);
        jsonMessage.setRows(dealerShoperList);
        jsonMessage.setObject(dealerShoperCount);
        return jsonMessage;
    }
    
    /**
     * 提供首页删除购物车产品
     * @param shoperId
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/dealerShoper/deleteHomeCart", method = RequestMethod.GET)
    public JsonMessage deleteHomeCartAction(@RequestParam(value = "shoperId", required = true) String shoperId) throws BusinessException
    {
        if (null != OnLineUserUtils.getCurrentDealer())
        {
            dealerShoperService.delete(shoperId);
            return this.getJsonMessage(CommonConst.SUCCESS);
        }
        return this.getJsonMessage(CommonConst.FAIL);
    }
}
