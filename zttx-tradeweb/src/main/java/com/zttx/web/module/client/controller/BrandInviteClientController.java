package com.zttx.web.module.client.controller;

import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.model.BrandInviteModel;
import com.zttx.web.module.brand.service.BrandInviteService;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：BrandInviteClientController.java</p>
 * <p>Title: （品牌商邀请、经销商申请）加盟接口管理</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-7-17 上午9:14:34</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/brandInvite")
public class BrandInviteClientController extends GenericController
{
    @Autowired
    private BrandInviteService   brandInviteService;
    
    @Autowired
    private DealerJoinService    dealerJoinService;
    
    /**
     * 列表（分页）
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        BrandInvite searchBean = new BrandInvite();
        BeanUtils.populate(searchBean, map);
        searchBean.setOpratorCata(MapUtils.getShort(map, "opratorCata"));
        searchBean.setApplyState(MapUtils.getShort(map, "applyState"));
        searchBean.setInviteState(MapUtils.getInteger(map, "inviteState"));
        searchBean.setPage(page);
        PaginateResult<BrandInvite> result = new PaginateResult<BrandInvite>(page,brandInviteService.findList(searchBean));
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查看
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view")
    public JsonMessage view(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        BrandInvite brandInvite = brandInviteService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == brandInvite) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, brandInvite);
    }
    
    /**
     * 指定终端商加盟某品牌
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/joinApply")
    public JsonMessage joinApply(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String dealerId = MapUtils.getString(map, "dealerId");
        String brandsId = MapUtils.getString(map, "brandsId");
        BrandInvite brandInvite = new BrandInvite();
        brandInvite.setSourceType((short) 1);// 用于支撑系统，因此来源为1
        brandInvite.setBrandsId(brandsId);
        brandInvite.setDealerId(dealerId);
        BrandInvite result = brandInviteService.updateJoinApply(brandInvite, map);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 指定品牌商邀请经销商加盟(用于支撑系统)
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/inviteJoin")
    public JsonMessage inviteJoin(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String dealerId = MapUtils.getString(map, "dealerId");
        String brandsId = MapUtils.getString(map, "brandsId");
        BrandInviteModel brandInvite = new BrandInviteModel();
        brandInvite.setSourceType((short) 1);// 1表示来源于支撑系统
        BrandInvite result = brandInviteService.brand_invite_dealer(dealerId, brandsId, brandInvite);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 同意终端商对品牌的加盟申请或
     * 同意品牌商对经销商的邀请
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/agreeApply")
    public JsonMessage agreeApply(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        if (StringUtils.isBlank(refrenceId)) { throw new BusinessException("请求参数不能为空"); }
        BrandInvite brandInvite = brandInviteService.selectByPrimaryKey(refrenceId);
        if (null == brandInvite) { throw new BusinessException("不存在该经销商申请加盟该品牌信息"); }
        if (brandInvite.getApplyState() != (short) 0 && brandInvite.getApplyState() != (short) 3) { throw new BusinessException("该记录已做处理，操作失败"); }
        brandInviteService.updateBrandInvite(brandInvite.getRefrenceId());
        Map<String, Object> result = Maps.newHashMap();
        result.put("applyState", 1);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 撤销终端商对品牌的加盟申请
     * 或撤销品牌商对终端商的邀请
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/removeApply")
    public JsonMessage removeApply(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        if (StringUtils.isBlank(refrenceId)) { throw new BusinessException("请求参数不能为空"); }
        BrandInvite brandInvite = brandInviteService.selectByPrimaryKey(refrenceId);
        if (null == brandInvite) { throw new BusinessException("不存在该经销商申请加盟该品牌信息"); }
        if (brandInvite.getApplyState() != (short) 0 && brandInvite.getApplyState() != (short) 3) { 
        	throw new BusinessException("该记录已做处理，操作失败"); 
        }
        // 1表示来源是经销商申请,5表示撤销申请
        Map<String, Object> result = Maps.newHashMap();
        if (brandInvite.getOpratorCata() == 1)
        {
            brandInviteService.updateInviteRemove(brandInvite);
            result.put("applyState", 5);
        }
        // 2表示来源是品牌商邀请,7表示撤销邀请
        if (brandInvite.getOpratorCata() == 2)
        {
            brandInviteService.removeInvite(brandInvite);
            result.put("applyState", 7);
        }
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 返回经销商申请加盟的品牌列表或
     * 返回品牌商邀请加盟的经销商列表
     * @param request
     * @param param
     * @return
     * @throws Exception
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/applyOrInvite", method = RequestMethod.POST)
    public JsonMessage applyOrInvite(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        BrandInviteModel brandInvite = new BrandInviteModel();
        brandInvite.setDealerId(MapUtils.getString(map, "dealerId"));
        brandInvite.setRefrenceId(MapUtils.getString(map, "refrenceId"));
        brandInvite.setOpratorCata(MapUtils.getShort(map, "opratorCata"));
        brandInvite.setBrandId(MapUtils.getString(map, "brandId"));
        brandInvite.setUserMobile(MapUtils.getString(map, "userMobile"));
        brandInvite.setUpdateTime(MapUtils.getLong(map, "updateTime"));
        brandInvite.setDealerName(MapUtils.getString(map, "dealerName"));
        brandInvite.setInvokeType(MapUtils.getShort(map, "invokeType"));
        brandInvite.setApplyState(MapUtils.getShort(map, "applyState"));
        Integer pageSize = MapUtils.getInteger(map, "pageSize");
        Integer currentPage = MapUtils.getInteger(map, "currentPage");
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize == null ? 10 : pageSize);
        pagination.setCurrentPage(currentPage == null ? 1 : currentPage);
        PaginateResult<BrandInvite> result = brandInviteService.getBrandInvites(pagination,brandInvite);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 根据经销商编号，是否已读等条件返回加盟信息
     * @param request
     * @param param
     * @return
     * @throws Exception
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/brandInviteMessage_crm", method = RequestMethod.POST)
    public JsonMessage brandInviteMessage(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        BrandInvite brandInvite = new BrandInvite();
        brandInvite.setDealerId(MapUtils.getString(map, "dealerId"));
        brandInvite.setOpratorCata(MapUtils.getShort(map, "opratorCata"));
        brandInvite.setReadState(MapUtils.getShort(map, "readState"));
        List<BrandInvite> result = brandInviteService.findList(brandInvite);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 修改是否已读状态
     * @param request
     * @param param
     * @return
     * @throws Exception
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/updateReadState", method = RequestMethod.POST)
    public JsonMessage updateReadState(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        BrandInvite b = brandInviteService.selectByPrimaryKey(refrenceId);
        if (null == b) { throw new BusinessException("该记录不存在或已删除"); }
        Short readState = MapUtils.getShort(map, "readState");
        if (null == readState) { throw new BusinessException("请求参数不能为空"); }
        b.setReadState(readState);
        brandInviteService.updateByPrimaryKey(b);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 返回加盟信息(经销商主动申请)
     * @param request
     * @param param
     * @return
     * @throws Exception
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/brandInvite_crm_dealer", method = RequestMethod.POST)
    public JsonMessage brandInvite_crm_dealer(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        BrandInvite brandInvite = new BrandInvite();
        brandInvite.setDealerId(MapUtils.getString(map, "dealerId"));
        brandInvite.setOpratorCata((short) 1);// 经销商主动申请
        List<BrandInvite> result = brandInviteService.findList(brandInvite);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 返回加盟信息(品牌商邀请,用于支撑系统)
     * @param request
     * @param param
     * @return
     * @throws Exception
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/brandInvite_crm_brand", method = RequestMethod.POST)
    public JsonMessage brandInvite_crm_brand(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        BrandInvite brandInvite = new BrandInvite();
        brandInvite.setDealerId(MapUtils.getString(map, "dealerId"));
        brandInvite.setOpratorCata((short) 2);// 品牌商邀请
        List<BrandInvite> result = brandInviteService.findList(brandInvite);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 指定品牌商邀请经销商加盟(用于品牌商erp)
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/inviteJoin_brand_erp", method = RequestMethod.POST)
    public JsonMessage inviteJoin_brand_erp(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String dealerId = MapUtils.getString(map, "dealerId");
        String brandsId = MapUtils.getString(map, "brandsId");
        String inviteText = MapUtils.getString(map, "inviteText");
        BrandInviteModel brandInvite = new BrandInviteModel();
        brandInvite.setInviteText(inviteText);
        brandInvite.setSourceType((short) 2);// 2表示来源于品牌商erp
        BrandInvite result = brandInviteService.brand_invite_dealer(dealerId, brandsId, brandInvite);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 品牌商拒绝经销商
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/refuseDealer", method = RequestMethod.POST)
    public JsonMessage refuseDealer(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        String auditMark = MapUtils.getString(map, "auditMark");
        if (StringUtils.isBlank(refrenceId)) { throw new BusinessException("请求参数不能为空"); }
        BrandInvite brandInvite = brandInviteService.selectByPrimaryKey(refrenceId);
        if (null == brandInvite) { throw new BusinessException("不存在该经销商申请加盟该品牌信息"); }
        if (brandInvite.getApplyState() != (short) 0) { throw new BusinessException("该记录已做处理，操作失败"); }
        brandInvite.setAuditMark(auditMark);
        Map<String, Object> result = Maps.newHashMap();
        brandInviteService.insertRejectApply(brandInvite);
        result.put("applyState", 2);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 品牌商中止经销商合作
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/discontinueDealer", method = RequestMethod.POST)
    public JsonMessage discontinueDealer(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        if (StringUtils.isBlank(refrenceId)) { throw new BusinessException("请求参数不能为空"); }
        BrandInvite brandInvite = brandInviteService.selectByPrimaryKey(refrenceId);
        if (null == brandInvite || brandInvite.getApplyState() != (short) 1) { throw new BusinessException("经销商未与该品牌商合作"); }
        DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(brandInvite.getDealerId(), brandInvite.getBrandsId());
        dealerJoinService.updateStopBrandJoinState(dealerJoin, "");
        Map<String, Object> result = Maps.newHashMap();
        result.put("applyState", 6);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
