package com.zttx.web.module.dealer.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.model.BrandInviteModel;
import com.zttx.web.module.brand.service.BrandInviteService;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * <p>File：DealerInvitedControl.java</p>
 * <p>Title: 经销商被邀请的被管理界面</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-28 下午1:42:10</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吕海斌
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.DEALER)
public class DealerInvitedController extends GenericController
{
    @Autowired
    private BrandInviteService   brandInviteService;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    @Autowired
    private DealerJoinService    dealerJoinService;
    
    /**
     * 邀请中的 页面
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "copartner/invited")
    public String invitedIndex(Model model)
    {
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "/dealer/copartner_invited";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "copartner/invited/list")
    public JsonMessage list(Pagination pagination, BrandInviteModel brandInvite) throws BusinessException
    {
        pagination.setPageSize(10);
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        brandInvite.setDealerId(dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS, brandInviteService.getInviteApplyStateList(pagination, brandInvite));
    }
    
    /**
     * 接受邀请
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "invited/accept")
    public Object acceptInvited(BrandInvite brandInvite) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        brandInvite.setDealerId(dealerId);
        if (StringUtils.isBlank(brandInvite.getBrandsId())) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        DealerJoin filter = new DealerJoin();
        filter.setBrandsId(brandInvite.getBrandsId());
        filter.setDealerId(dealerId);
        List<DealerJoin> dealerJoinList = dealerJoinService.findDealerJoin(filter);
        if (dealerJoinList != null && dealerJoinList.size() > 0)
        {
            DealerJoin dealerJoin = dealerJoinList.get(0);
            if (null != dealerJoin
                    && (dealerJoin.getJoinState() == DealerConstant.DealerJoin.TERMINATED || dealerJoin.getJoinState() == DealerConstant.DealerJoin.STOP_COOPERATION || dealerJoin
                            .getJoinState() == DealerConstant.DealerJoin.STOP_COOPERATION_DEALER)) { return this.getJsonMessage(CommonConst.FAIL_DEALER_INVITED); }
        }
        try
        {
            brandInviteService.saveInviteJoin(brandInvite);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
