/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.entity.DealerMessage;
import com.zttx.web.module.dealer.service.DealerMessageService;
import com.zttx.web.module.dealer.service.DealerReadService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 经销商消息管理 控制器
 * <p>File：DealerMessageController.java </p>
 * <p>Title: DealerMessageController </p>
 * <p>Description:DealerMessageController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.DEALER)
public class DealerMessageController extends GenericController
{
    @Autowired(required = true)
    private DealerMessageService dealerMessageService;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    @Autowired
    private DealerReadService    dealerReadService;
    
    /**
     * 跳转消息页面
     *
     * @param page
     * @param dealerMessageModel
     * @param model
     * @return
     * @author 章旭楠
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/message")
    public String listMessage(Pagination page, DealerMessage dealerMessageModel, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getPrincipal().getRefrenceId();
        dealerMessageModel.setDealerId(dealerId);
        PaginateResult<DealerMessage> paginateResult = this.dealerMessageService.listDealerMessage(page, dealerMessageModel);
        model.addAttribute("dataList", paginateResult.getList());
        model.addAttribute("page", paginateResult.getPage());
        model.addAttribute("dealerMessageModel", dealerMessageModel);
        // 添加字典
        model.addAttribute("messageType", dataDictValueService.findByDictCode(DataDictConstant.DEALER_MESSAGE_TYPE));
        return "dealer/message_system";
    }
    
    /**
     * 消息管理页面（包含消息数据）
     *
     * @param page
     * @param dealerMessageModel
     * @param model
     * @return
     * @author 章旭楠
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/message/system")
    public String listSysMessage(Pagination page, DealerMessage dealerMessageModel, Model model) throws BusinessException
    {
        return listMessage(page, dealerMessageModel, model);
    }
    
    /**
     * 查看消息
     * @param msgId
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/message/readMsg/{msgId}")
    public String readMessage(@PathVariable("msgId") String msgId, Model model) throws BusinessException
    {
        // 获取当前经销商的父ID
        String dealerId = OnLineUserUtils.getPrincipal().getRefrenceId();
        // 获取消息对象
        DealerMessage dealerMessage = this.dealerMessageService.getMsgWithDealer(dealerId, msgId);
        // 传入参数，可在jsp中使用
        model.addAttribute("dealerMessage", dealerMessage);
        this.dealerReadService.saveRead(dealerId, msgId);
        return "dealer/message_details";
    }
    
    /**
     * 删除消息
     * @author 章旭楠
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/message/delete", method = RequestMethod.POST)
    public String deleteMessage(Pagination page, DealerMessage dealerMessageModel, RedirectAttributes params, String menuId)
    {
        List<String> msgIdList = dealerMessageModel.getMsgIdList();
        if (!msgIdList.isEmpty())
        {
            String dealerId = OnLineUserUtils.getPrincipal().getRefrenceId();
            this.dealerReadService.saveRead(dealerId, msgIdList);
            this.dealerMessageService.deleteDealerMessage(dealerId, msgIdList);
        }
        return this.redirectList(params, page, dealerMessageModel, menuId);
    }
    
    /**
     * 业务参数结束后，跳转到消息显示页面
     * @author 张昌苗
     */
    private String redirectList(RedirectAttributes params, Pagination page, DealerMessage dealerMessageModel, String menuId)
    {
        params.addAttribute("pageSize", page.getPageSize());
        params.addAttribute("currentPage", page.getCurrentPage());
        params.addAttribute("searchType", dealerMessageModel.getSearchType());
        params.addAttribute("searchWord", dealerMessageModel.getSearchWord());
        params.addAttribute("msgStatus", dealerMessageModel.getMsgStatus());
        params.addAttribute("menuId", menuId);// 锁定左边菜单
        return "redirect:/dealer/message/system";
    }
    
    /**
     * 标记为已读
     * @author 张昌苗
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/message/read", method = RequestMethod.POST)
    public String readMessage(Pagination page, DealerMessage dealerMessageModel, RedirectAttributes params, String menuId)
    {
        List<String> msgIdList = dealerMessageModel.getMsgIdList();
        if (!msgIdList.isEmpty())
        {
            String dealerId = OnLineUserUtils.getPrincipal().getRefrenceId();
            this.dealerReadService.saveRead(dealerId, msgIdList);
        }
        return this.redirectList(params, page, dealerMessageModel, menuId);
    }
    
    /**
     * 清空消息
     * @author 章旭楠
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/message/clear", method = RequestMethod.POST)
    public String clearMessage(Pagination page, DealerMessage dealerMessageModel, RedirectAttributes params, String menuId)
    {
        String dealerId = OnLineUserUtils.getPrincipal().getRefrenceId();
        this.dealerReadService.saveRead(dealerId);
        this.dealerMessageService.deleteDealerMessage(dealerId, null);
        return this.redirectList(params, page, dealerMessageModel, menuId);
    }
    
    /**
     * 获取未读的消息条数
     * @return
     * @author 章旭楠
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/message/count")
    @ResponseBody
    public JsonMessage countMessage()
    {
        String dealerId = OnLineUserUtils.getPrincipal().getRefrenceId();
        Long count = this.dealerMessageService.getDealerMessageCount(dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS, count);
    }
}
