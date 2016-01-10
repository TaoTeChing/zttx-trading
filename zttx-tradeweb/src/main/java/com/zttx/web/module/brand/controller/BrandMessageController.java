/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandMessage;
import com.zttx.web.module.brand.model.BrandSendMsgModel;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandMessageService;
import com.zttx.web.module.brand.service.BrandReadService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.entity.DealerMessage;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.module.dealer.service.DealerMessageService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 品牌商消息管理 控制器
 * <p>File：BrandMessageController.java </p>
 * <p>Title: BrandMessageController </p>
 * <p>Description:BrandMessageController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.BRAND)
public class BrandMessageController extends GenericController
{
    @Autowired(required = true)
    private BrandMessageService  brandMessageService;
    
    @Autowired
    private BrandReadService     brandReadService;
    
    @Autowired
    private DealerMessageService dealerMessageService;
    
    @Autowired
    private BrandesInfoService   brandesInfoService;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    @Autowired
    private DealerJoinService    dealerJoinService;
    
    /**
     * 跳转到/message/list
     * @author 章旭楠
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/message")
    public String listMessage()
    {
        return "redirect:/brand/message/list";
    }
    
    /**
     * 消息管理页面（包含消息数据）
     * @author 章旭楠
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/message/list")
    public String listMessage(Pagination page, BrandMessage brandMessageModel, Model model)
    {
        String brandId = OnLineUserUtils.getPrincipal().getRefrenceId();
        brandMessageModel.setBrandId(brandId);
        // 获取数据
        PaginateResult<Map<String, Object>> paginateResult;
        if (CommonConstant.Message.MSG_LIST_SEND.equals(brandMessageModel.getListType()))
        {
            paginateResult = this.brandMessageService.listBrandSendMessage(page, brandMessageModel);// 我已发送的消息
        }
        else
        {
            paginateResult = this.brandMessageService.listBrandMessage(page, brandMessageModel);// 全部消息、站内消息、系统消息
        }
        model.addAttribute("dataList", paginateResult.getList());
        model.addAttribute("page", paginateResult.getPage());
        model.addAttribute("brandMessageModel", brandMessageModel);
        model.addAttribute("messageStatus", dataDictValueService.findByDictCode(DataDictConstant.BRAND_MESSAGE_STATUS));
        return "brand/list_brandMessage";
    }
    
    /**
     * 查看消息
     * @author 章旭楠
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/message/readMsg/{msgId}")
    public String readMessage(BrandMessage brandMessageModel, @PathVariable("msgId") String msgId, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getPrincipal().getRefrenceId();
        if (CommonConstant.Message.MSG_LIST_SEND.equals(brandMessageModel.getListType()))
        {
            DealerMessage msg = this.dealerMessageService.getMsgWithBrand(brandId, msgId);
            model.addAttribute("msgTitle", msg.getMsgTitle());
            model.addAttribute("msgCate", msg.getMsgCate());
            model.addAttribute("msgCreateTime", msg.getCreateTime());
            model.addAttribute("msgSenderName", msg.getBrandName());
            model.addAttribute("msgContent", msg.getMsgText());
        }
        else
        {
            BrandMessage msg = this.brandMessageService.getBrandMessage(brandId, msgId);
            model.addAttribute("msgTitle", msg.getMsgTitle());
            model.addAttribute("msgCate", msg.getMsgCate());
            model.addAttribute("msgCreateTime", msg.getCreateTime());
            model.addAttribute("msgSenderName", msg.getSenderName());
            model.addAttribute("msgContent", msg.getMsgText());
            // 把消息标记为已读
            this.brandReadService.saveRead(brandId, msgId);
        }
        return "brand/list_brandMessageInfo";
    }
    
    /**
     * 删除消息
     * @author 章旭楠
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/message/delete", method = RequestMethod.POST)
    public String deleteMessage(Pagination page, BrandMessage brandMessageModel, RedirectAttributes params)
    {
        List<String> msgIdList = brandMessageModel.getMsgIdList();
        if (!msgIdList.isEmpty())
        {
            String brandId = OnLineUserUtils.getPrincipal().getRefrenceId();
            if (CommonConstant.Message.MSG_LIST_SEND.equals(brandMessageModel.getListType()))
            {
                // 删除发送的消息
                this.brandMessageService.deleteBrandSendMessage(brandId, msgIdList);
            }
            else
            {
                // 删除收到的消息
                this.brandReadService.saveRead(brandId, msgIdList);
                this.brandMessageService.deleteBrandMessage(brandId, msgIdList);
            }
        }
        return this.redirectList(params, page, brandMessageModel);
    }
    
    /**
     * 发布消息页面
     * @param model
     * @return
     * @throws BusinessException
     * @author 章旭楠
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/message/send")
    public String sendMessage(Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getPrincipal().getRefrenceId();
        List<BrandsInfoModel> list = this.brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandesList", list);
        return "brand/send_brandMessage";
    }
    
    /**
     * 筛选某个品牌下的经销商
     * @param dealerJoinModel 获取请求参数对象
     * @return 经销商列表
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/message/dealers")
    public JsonMessage queryJoinedDealers(DealerJoin dealerJoinModel)
    {
        String brandId = OnLineUserUtils.getPrincipal().getRefrenceId();
        dealerJoinModel.setBrandId(brandId);
        dealerJoinModel.setJoinState(DealerConstant.DealerJoin.COOPERATED);// 已合作状态
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", dealerJoinService.getDealerJoinList(dealerJoinModel));
        return this.getJsonMessage(CommonConst.SUCCESS, map);
    }
    
    /**
     * 给经销商发送消息（AJAX）
     * @author 章旭楠
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/message/sendDealer", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage sendDealer(BrandSendMsgModel brandSendMsgModel) throws BusinessException
    {
        String brandId = OnLineUserUtils.getPrincipal().getRefrenceId();
        JsonMessage result = new JsonMessage();
        Integer code = CommonConst.FAIL.code;
        if (null != brandSendMsgModel.getDocOpen() && brandSendMsgModel.getDocOpen())// 发送给所有
        {
            String[] allDealerIds = this.dealerJoinService.getDealerIdsWithJoin(brandId);
            brandSendMsgModel.setDealerIds(allDealerIds);
        }
        if (beanValidator(result, brandSendMsgModel))
        {
            this.brandMessageService.sendDealerMessage(brandId, brandSendMsgModel.getDealerIds(), brandSendMsgModel.getTitle(), brandSendMsgModel.getContent());
            code = CommonConst.SUCCESS.code;
        }
        result.setCode(code);
        return result;
    }
    
    /**
     * 业务参数结束后，跳转到消息显示页面
     * @author 章旭楠
     */
    private String redirectList(RedirectAttributes params, Pagination page, BrandMessage brandMessageModel)
    {
        params.addAttribute("pageSize", page.getPageSize());
        params.addAttribute("currentPage", page.getCurrentPage());
        params.addAttribute("listType", brandMessageModel.getListType());
        params.addAttribute("searchType", brandMessageModel.getSearchType());
        params.addAttribute("searchWord", brandMessageModel.getSearchWord());
        params.addAttribute("msgStatus", brandMessageModel.getMsgStatus());
        params.addAttribute("sendBeginTime", brandMessageModel.getSendBeginTimeString());
        params.addAttribute("sendEndTime", brandMessageModel.getSendEndTimeString());
        return "redirect:/brand/message/list";
    }
}
