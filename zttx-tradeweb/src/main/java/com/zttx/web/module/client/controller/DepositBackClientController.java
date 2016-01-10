package com.zttx.web.module.client.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.DepositBack;
import com.zttx.web.module.common.service.DepositBackService;
import com.zttx.web.module.common.service.PayApiService;
import com.zttx.web.utils.ParameterUtils;

/**
 * 转账记录接口
 * <p>File：DeposiBackClientController.java</p>
 * <p>Title: DeposiBackClientController</p>
 * <p>Description:DeposiBackClientController</p>
 * <p>Copyright: Copyright (c) Aug 19, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/depositBack")
public class DepositBackClientController extends GenericController
{
    @Autowired
    private DepositBackService depositBackService;
    
    @Autowired
    private PayApiService      payApiService;
    
    /**
     * 获取转账记录
     * @author chenjp
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage list(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DepositBack filter = new DepositBack();
        filter.setBrandName(MapUtils.getString(map, "brandName"));
        filter.setBrandsName(MapUtils.getString(map, "brandsName"));
        filter.setDealerName(MapUtils.getString(map, "dealerName"));
        filter.setType(MapUtils.getShort(map, "type"));
        filter.setStatus(MapUtils.getShort(map, "status"));
        filter.setPage(page);
        PaginateResult<DepositBack> paginateResult = new PaginateResult<DepositBack>(filter.getPage(), this.depositBackService.findList(filter));
        return super.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 解冻
     * @author chenjp
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/updateDepositBackStatus")
    @ResponseBody
    public JsonMessage updateDepositBackStatus(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        String operateUserId = MapUtils.getString(map, "operateUserId");
        String operateUserName = MapUtils.getString(map, "operateUserName");
        if(StringUtils.isBlank(refrenceId)){
            return super.getJsonMessage(CommonConst.FAIL.getCode(),"请选择要解冻的记录");
        }
        payApiService.updateDepositBackStatus(refrenceId, operateUserId, operateUserName);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
