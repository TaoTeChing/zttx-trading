package com.zttx.web.module.client.controller;

import java.util.Map;

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
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.SecurityCert;
import com.zttx.web.module.common.service.SecurityCertService;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：SecurityCertClientController.java </p>
 * <p>Title: SecurityCertClientController </p>
 * <p>Description:（经销商/品牌商）申请更改手机认证管理接口 </p>
 * <p>Copyright: Copyright (c) 九月 29，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/securityCert")
public class SecurityCertClientController extends GenericController
{
    @Autowired
    private SecurityCertService securityCertService;
    
    /**
     * 列表（分页）
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        SecurityCert searchBean = new SecurityCert();
        searchBean.setUserCate(MapUtils.getShort(map, "userCate"));
        searchBean.setApplyType(MapUtils.getShort(map, "applyType"));
        searchBean.setActState(MapUtils.getShort(map, "actState"));
        PaginateResult<SecurityCert> result = securityCertService.searchByClient(searchBean, page);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查看
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/view")
    public JsonMessage view(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        SecurityCert securityCert = securityCertService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == securityCert) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, securityCert);
    }
    
    /**
     * 处理申请
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/handApply")
    public JsonMessage handApply(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        SecurityCert securityCert = new SecurityCert();
        BeanUtils.populate(securityCert, map);
        securityCert.setUserCate(MapUtils.getShort(map, "userCate"));
        securityCert.setApplyType(MapUtils.getShort(map, "applyType"));
        securityCert.setActState(MapUtils.getShort(map, "actState"));
        String actionIp = MapUtils.getString(map, "actionIp");
        securityCert.setActionIp(IPUtil.formatStrIpToInt(actionIp));
        securityCertService.updateActState(securityCert);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
