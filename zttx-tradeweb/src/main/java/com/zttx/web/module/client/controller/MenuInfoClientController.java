package com.zttx.web.module.client.controller;

import java.util.Map;
import java.util.TreeSet;

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
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.MenuInfo;
import com.zttx.web.module.common.model.ZtreeTreeNode;
import com.zttx.web.module.common.service.MenuInfoService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：MenuInfoClientController.java </p>
 * <p>Title: 菜单管理远程接口 </p>
 * <p>Description: MenuInfoClientController </p>
 * <p>Copyright: Copyright (c) 15/9/9</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/client/menu")
public class MenuInfoClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(MenuInfoClientController.class);
    
    @Autowired(required = true)
    private MenuInfoService     menuInfoService;
    
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
        // Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        MenuInfo searchBean = new MenuInfo();
        BeanUtils.populate(searchBean, map);
        // searchBean.setPage(page);
        TreeSet<ZtreeTreeNode> menuInfoTree = menuInfoService.getTree(searchBean);
        return super.getJsonMessage(CommonConst.SUCCESS, menuInfoTree);
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
        MenuInfo menuInfo = menuInfoService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == menuInfo) { return super.getJsonMessage(ClientConst.DBERROR); }
        menuInfo.setParent(menuInfoService.selectByPrimaryKey(menuInfo.getUpMenuId()));
        return super.getJsonMessage(CommonConst.SUCCESS, menuInfo);
    }
    
    /**
     * 保存菜单信息
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        MenuInfo menuInfo = new MenuInfo();
        BeanUtils.populate(menuInfo, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, menuInfo))
        {
            this.menuInfoService.saveByClient(menuInfo);
        }
        return jsonMessage;
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
        if (menuInfoService.hasChildMenu(refrenceId)) { throw new BusinessException(ClientConst.FAILURE.code, "该菜单存在子级，不允许删除"); }
        menuInfoService.deleteCascade(refrenceId);// 关联删除 角色菜单关系
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 树形菜单数据 
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping("/ztree")
    public JsonMessage getMenuTree(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String roleId = MapUtils.getString(map, "refrenceId");
        MenuInfo menuFilter = new MenuInfo();
        TreeSet<ZtreeTreeNode> menuTreeSet = menuInfoService.getTree(roleId, menuFilter);
        return getJsonMessage(CommonConst.SUCCESS, menuTreeSet);
    }
}
