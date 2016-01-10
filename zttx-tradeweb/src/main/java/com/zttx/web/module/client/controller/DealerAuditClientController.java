package com.zttx.web.module.client.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.dealer.entity.DealerAudit;
import com.zttx.web.module.dealer.service.DealerAuditService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerAuditClientController.java </p>
 * <p>Title: DealerAuditClientController </p>
 * <p>Description: 经销商审核日志接口 </p>
 * <p>Copyright: Copyright (c) 15/9/12</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/DealerAudit")
public class DealerAuditClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(DealerAuditClientController.class);
    
    @Autowired
    private DealerAuditService  dealerAuditService;
    
    /**
     * 分页查询经销商审核日志
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DealerAudit searchBean = new DealerAudit();
        BeanUtils.populate(searchBean, map);
        searchBean.setPage(page);
        List<DealerAudit> result = dealerAuditService.findList(searchBean);
        return super.getJsonMessage(CommonConst.SUCCESS, new PaginateResult<>(searchBean.getPage(), result));
    }
    
    /**
     * 查看经销商审核日志
     * @param param refrenceId
     * @return JsonMessage
     * @throws Exception
     */
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerAudit dealerAudit = dealerAuditService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == dealerAudit) { return super.getJsonMessage(CommonConst.DATA_NOT_EXISTS); }
        return super.getJsonMessage(CommonConst.SUCCESS, dealerAudit);
    }
    
    /**
     * 删除
     * @param param refrenceId
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        dealerAuditService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 新增/修改
     * @param param
     *  refrenceId=null：新增
     *  refrenceId!=null：修改
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage execute(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerAudit dealerAudit = new DealerAudit();
        BeanUtils.populate(dealerAudit, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, dealerAudit))
        {
            dealerAuditService.save(dealerAudit);
        }
        return jsonMessage;
    }
}
