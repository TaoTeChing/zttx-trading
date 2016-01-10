package com.zttx.web.module.client.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.IPUtil;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.RoleInfo;
import com.zttx.web.module.common.service.RoleInfoService;
import com.zttx.web.module.common.service.RoleMenuService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：MenuInfoClientController.java </p>
 * <p>Title: 角色管理远程接口 </p>
 * <p>Description: MenuInfoClientController </p>
 * <p>Copyright: Copyright (c) 15/9/9</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/client/role")
public class RoleInfoClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(RoleInfoClientController.class);
    
    @Autowired(required = true)
    private RoleInfoService     roleInfoService;
    
    @Autowired
    private RoleMenuService     roleMenuService;
    
    /**
     * 列表
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        RoleInfo searchBean = new RoleInfo();
        BeanUtils.populate(searchBean, map);
        searchBean.setPage(page);
        List<RoleInfo> roleInfoList = roleInfoService.findByRoleInfo(searchBean);
        return super.getJsonMessage(CommonConst.SUCCESS, new PaginateResult<>(searchBean.getPage(), roleInfoList));
    }
    
    /**
     * 查询
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        RoleInfo roleInfo = roleInfoService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == roleInfo) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, roleInfo);
    }
    
    /**
     * 逻辑删除
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        RoleInfo roleInfo = roleInfoService.selectByPrimaryKey(refrenceId);
        if (!roleInfo.getCanDel()) { return this.getJsonMessage(CommonConst.FAIL.getCode(), "不可删除"); }
        roleInfoService.seleteCascade(refrenceId);// 关联删除，删除对应权限
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 保存角色信息
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(ClientParameter param, HttpServletRequest request) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        RoleInfo roleInfo = new RoleInfo();
        BeanUtils.populate(roleInfo, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, roleInfo))
        {
            if (roleInfoService.isExistRoleCode(roleInfo.getRoleCode(), roleInfo.getRefrenceId())) { throw new BusinessException(ClientConst.FAILURE.code, "角色编码已经存在！"); }
            if (StringUtils.isBlank(roleInfo.getRefrenceId()))
            {
                roleInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                roleInfo.setCreateTime(CalendarUtils.getCurrentLong());
                roleInfo.setUpdateTime(CalendarUtils.getCurrentLong());
                roleInfo.setCreateIp(IPUtil.getIpAddr(request));
                roleInfoService.insertSelective(roleInfo);
            }
            else
            {
                roleInfo.setUpdateTime(CalendarUtils.getCurrentLong());
                roleInfoService.updateByPrimaryKeySelective(roleInfo);
            }
        }
        return jsonMessage;
    }
    
    /**
     * 设置角色菜单
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @RequestMapping("/setMenu")
    @ResponseBody
    public JsonMessage setMenu(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String roleId = MapUtils.getString(map, "refrenceId");
        String[] menuIds = StringUtils.split(MapUtils.getString(map, "menuIds", ""), ",");// 菜单id 逗号 切割
        roleMenuService.saveRoleMenu(menuIds, roleId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
