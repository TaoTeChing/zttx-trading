package com.zttx.web.module.client.controller;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
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
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.FreightCompany;
import com.zttx.web.module.common.service.FreightCompanyService;
import com.zttx.web.utils.ParameterUtils;

/**
 * 物流公司信息远程调用接口
 * <p>File：FreightCompanyClientController.java</p>
 * <p>Title: FreightCompanyClientController</p>
 * <p>Description:FreightCompanyClientController</p>
 * <p>Copyright: Copyright (c) Sep 17, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/freightCompany")
public class FreightCompanyClientController extends GenericController
{
    @Autowired
    private FreightCompanyService freightCompanyService;
    
    /**
     * 物流公司信息查询
     *
     * @param request
     * @param param
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> params = ParameterUtils.getMapFromParameter(param);
        int currentPage = MapUtils.getIntValue(params, "currentPage");
        int pageSize = MapUtils.getIntValue(params, "pageSize");
        Pagination pagination = new Pagination(currentPage, pageSize);
        FreightCompany freightCompany = new FreightCompany();
        BeanUtils.populate(freightCompany, params);
        freightCompany.setPage(pagination);
        PaginateResult<FreightCompany> result = new PaginateResult<FreightCompany>(pagination,freightCompanyService.findList(freightCompany));
        JsonMessage json = super.getJsonMessage(CommonConst.SUCCESS, result);
        return json;
    }
    
    /**
     * 新增/修改物流公司信息
     *
     * @param request
     * @param param
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> params = ParameterUtils.getMapFromParameter(param);
        FreightCompany freightCompany = new FreightCompany();
        freightCompany.setDelFlag(false);
        freightCompany.setCreateTime(new Date().getTime());
        BeanUtils.populate(freightCompany, params);
        freightCompanyService.save(freightCompany);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 查看物流公司信息
     *
     * @param request
     * @param param
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public JsonMessage detail(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> params = ParameterUtils.getMapFromParameter(param);
        FreightCompany freightCompany = freightCompanyService.selectByPrimaryKey(MapUtils.getString(params, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS, freightCompany);
    }
    
    /**
     * 删除物流公司信息
     *
     * @param request
     * @param param
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public JsonMessage remove(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> params = ParameterUtils.getMapFromParameter(param);
        freightCompanyService.delete(MapUtils.getString(params, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
