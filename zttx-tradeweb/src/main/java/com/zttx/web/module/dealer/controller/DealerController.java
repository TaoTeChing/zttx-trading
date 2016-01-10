package com.zttx.web.module.dealer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.module.brand.model.BrandVisitedModel;
import com.zttx.web.module.brand.service.BrandVisitedService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerCount;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerService;
import com.zttx.web.module.dealer.model.DealerVisitedModel;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.NetworkUtils;

/**
 * <p>File：DealerController.java </p>
 * <p>Title: DealerController </p>
 * <p>Description: DealerController </p>
 * <p>Copyright: Copyright (c) 2014 08/11/2015 11:16</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer")
public class DealerController extends GenericController
{
    @Autowired
    private UserInfoService      userInfoService;
    
    @Autowired
    private DealerInfoService    dealerInfoService;
    
    @Autowired
    private DealerCountService   dealerCountService;
    
    @Autowired
    private DealerServiceService dealerServiceService;
    
    @Autowired
    private DealerVisitedService dealerVisitedService;
    
    @Autowired
    private BrandVisitedService  brandVisitedService;
    
    @Autowired
    private DealerMessageService dealerMessageService;
    
    /* ========================================= 终端商用户中心登录 [@author易永耀] begin================================================ */
    /**
     * 终端管理中心首页
     * @param request
     * @param model
     * @param pagination
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/center")
    @RequiresPermissions(value = "dealer:center")
    public String index(HttpServletRequest request, Model model, Pagination pagination) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
        if (null != userPrincipal)
        {
            String dealerId = userPrincipal.getRefrenceId();
            UserInfo userInfo = userInfoService.selectByPrimaryKey(dealerId);
            DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
            DealerCount dealerCount = dealerCountService.selectByDealerId(dealerId);
            DealerService dealerService = dealerServiceService.getByDealerId(dealerId);
            // 我浏览过的品牌
            DealerVisitedModel dealerVisitedModel = new DealerVisitedModel();
            dealerVisitedModel.setDealerId(dealerId);
            PaginateResult<Map<String, Object>> pageDealerVisited = dealerVisitedService.getDealerVisitedsBy(dealerVisitedModel, pagination);
            // 浏览过我的品牌
            BrandVisitedModel brandVisitedModel = new BrandVisitedModel();
            brandVisitedModel.setDealerId(dealerId);
            PaginateResult<Map<String, Object>> pageBrandVisited = brandVisitedService.getBrandVisitedPage(pagination, brandVisitedModel);
            model.addAttribute("dealerUserm", userInfo);
            model.addAttribute("dealerInfo", dealerInfo);
            model.addAttribute("dealerCount", dealerCount);
            model.addAttribute("pageDealerVisited", pageDealerVisited);
            model.addAttribute("pageBrandVisited", pageBrandVisited);
            model.addAttribute("dealerService", dealerService);
            model.addAttribute("headDealerCount", dealerMessageService.getDealerMessageCount(dealerId));
        }
        return "dealer/agency_index";
    }
    
    /* ========================================= 终端商用户中心登录 end ================================================ */
    /**
     * 更新头像
     * @param userPhoto 临时路径
     * @return JsonMessage 头像路径
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/updatePhoto")
    public JsonMessage updatePhoto(String userPhoto)
    {
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS);
        try
        {
            if (StringUtils.isNotBlank(userPhoto))
            {
                String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
                DealerInfo dealerInfo = this.dealerInfoService.selectByPrimaryKey(dealerId);
                if (dealerInfo == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
                String headImage = FileClientUtil.moveAndDeleteFile(ImageConst.DEALER_IMG_PATH, userPhoto, UploadAttCateConst.USER_PHOTO);
                dealerInfo.setHeadImage(headImage);
                dealerInfo.setDomainName(NetworkUtils.getDoMainName());
                dealerInfo.setUpdateTime(CalendarUtils.getCurrentLong());
                dealerInfoService.updateByPrimaryKeySelective(dealerInfo);
                jsonMessage.setObject(dealerInfo.getHeadImage());
            }
        }
        catch (BusinessException e)
        {
            jsonMessage.setCode(e.getErrorCode().getCode());
            jsonMessage.setMessage(e.getErrorCode().getMessage());
        }
        return jsonMessage;
    }
}
