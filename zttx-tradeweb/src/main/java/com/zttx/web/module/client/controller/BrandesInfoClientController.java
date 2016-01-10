package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandDealService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.ProductInfo;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.search.solrj.BrandeSolrHandler;
import com.zttx.web.search.solrj.ProductSolrHandler;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.JsonMessageUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * 品牌商品牌信息管理接口
 * <p>File：BrandesInfoClientController.java</p>
 * <p>Title: BrandesInfoClientController</p>
 * <p>Description:BrandesInfoClientController</p>
 * <p>Copyright: Copyright (c) Sep 8, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/brandesInfo")
public class BrandesInfoClientController extends GenericController
{
    @Autowired
    private BrandesInfoService brandesInfoService;
    
    @Autowired
    private BrandDealService   brandDealService;
    
    @Autowired
    private BrandeSolrHandler  brandeSolrHandler;
    
    @Autowired
    private ProductInfoService productInfoService;
    
    @Autowired
    private ProductSolrHandler productSolrHandler;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    /**
     * 列表
     * @param request
     * @param param
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @ResponseBody
    @RequestMapping(value = "/search")
    public JsonMessage search(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        BrandesInfo brandesInfo = new BrandesInfo();
        BeanUtils.populate(brandesInfo, map);
        brandesInfo.setBrandState(MapUtils.getShort(map, "brandState"));
        brandesInfo.setPage(page);
        PaginateResult<BrandesInfo> result = new PaginateResult<BrandesInfo>(brandesInfo.getPage(), brandesInfoService.findList(brandesInfo));
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查看
     * @param request
     * @param param
     * @return
     */
    // @ResponseBody
    // @RequestMapping(value="/view")
    // public JsonMessage view(HttpServletRequest request, ClientParameter param)
    // {
    // Map<String, String> map = ParameterUtils.getMapFromParameter(param);
    // BrandesInfo brandesInfo = brandesInfoService.loadById(MapUtils.getString(map, "refrenceId"));
    // if (null==brandesInfo)
    // {
    // return super.getJsonMessage(ClientConst.DBERROR);
    // }
    // return super.getJsonMessage(CommonConst.SUCCESS, brandesInfo);
    // }
    /**
     * 处理申请(审核通过/审核不通过/取消/合作/终止合作)
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/handApply")
    public JsonMessage handApply(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        Short action = MapUtils.getShort(map, "action");
        Long beginTime = MapUtils.getLong(map, "beginTime");
        Long endTime = MapUtils.getLong(map, "endTime");
        String dealNos = MapUtils.getString(map, "dealNos");
        Short showed = MapUtils.getShort(map, "showed");
        Integer createIp = IPUtil.formatStrIpToInt(MapUtils.getString(map, "createIp"));
        String checkMark = MapUtils.getString(map, "checkMark");
        String userId = MapUtils.getString(map, "userId");
        
        if (StringUtils.isBlank(refrenceId) || null == action) { 
        	throw new BusinessException(ClientConst.NULERROR); 
        }
        
        brandesInfoService.updateState(refrenceId, action, beginTime, endTime, dealNos, createIp, showed, checkMark, userId);
        
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(refrenceId);
        if(null != brandesInfo){
        	if (BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED == action || BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED == action){//审核通过
        		brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
        		//合作的时候，开启旗下产品正常运行
                productInfoDubboConsumer.updateStopStateByBrandsId(brandesInfo.getRefrenceId(), BrandConstant.ProductInfoConst.STATE_NORMAL);
        		ProductInfo productInfo = new ProductInfo();
                productInfo.setBrandsId(brandesInfo.getRefrenceId());
                List<ProductInfo> list = productInfoService.findProductToSolr(productInfo, null);
                //List<ProductBaseInfo> list = productInfoDubboConsumer.findByBrandsIdAll(brandesInfo.getRefrenceId());
                productSolrHandler.addProductInfoList(list);
        	}else{
        		brandeSolrHandler.removeBrandesInfo(brandesInfo);
                ProductInfo productInfo = new ProductInfo();
                productInfo.setBrandsId(brandesInfo.getRefrenceId());
                List<ProductBaseInfo> list = productInfoDubboConsumer.findByBrandsIdAll(brandesInfo.getRefrenceId());
                for(ProductBaseInfo temp : list){
                	productSolrHandler.removeProductInfo(temp.getRefrenceId());
                }
                //不合作的时候，停止该品牌下的所有产品
                productInfoDubboConsumer.updateStopStateByBrandsId(brandesInfo.getRefrenceId(), BrandConstant.ProductInfoConst.STATE_STOPED);
        	}
        }
        
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 更改品牌主营经营类目
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateDealNos")
    public JsonMessage updateDealNos(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String brandesId = MapUtils.getString(map, "refrenceId");
        if (StringUtils.isBlank(brandesId)) { return JsonMessageUtil.getJsonMessage(CommonConst.PARAM_NULL); }
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        if (null == brandesInfo) { return super.getJsonMessage(CommonConst.BRANDES_INFO_NULL); }
        String dealNos = MapUtils.getString(map, "dealNos");
        Integer createIp = IPUtil.formatStrIpToInt(MapUtils.getString(map, "createIp"));
        brandDealService.updateBrandDeal(brandesInfo.getBrandId(), brandesId, dealNos, createIp);
        // brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
        // List<ProductBaseInfo> productBaseInfoList = dubboProductService.findByBrandsIdAll(brandesId);
        // if (!CollectionUtils.isEmpty(productBaseInfoList))
        // {
        // for (ProductBaseInfo info : productBaseInfoList)
        // {
        // ProductInfo filter = new ProductInfo();
        // filter.setRefrenceId(info.getRefrenceId());
        // productSolrHandler.addProductInfoList(productInfoService.findProductToSolr(filter, null));
        // }
        // }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 使品牌过期
     * @param request
     * @param param
     * @return
     * @throws BusinessException 
     */
    // 方法重复了
    /*
     * @ResponseBody
     * @RequestMapping(value="/expired")
     * public JsonMessage expired(HttpServletRequest request, ClientParameter param) throws BusinessException
     * {
     * Map<String, String> map = ParameterUtils.getMapFromParameter(param);
     * brandesInfoService.updateBrandesInfo(MapUtils.getString(map, "refrenceId"));
     * return super.getJsonMessage(CommonConst.SUCCESS);
     * }
     */
    /**
     * 品牌商下的所有品牌信息
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    /*
     * @ResponseBody
     * @RequestMapping(value = "/getBrandesInfos")
     * public JsonMessage getBrandesInfos(HttpServletRequest request, ClientParameter param) throws BusinessException
     * {
     * Map<String, String> map = ParameterUtils.getMapFromParameter(param);
     * Integer pageSize = MapUtils.getInteger(map, "pageSize");
     * Integer currentPage = MapUtils.getInteger(map, "currentPage");
     * String brandId = MapUtils.getString(map, "brandId");
     * Pagination pagaination = new Pagination();
     * pagaination.setPageSize(pageSize == null ? 10 : pageSize);
     * pagaination.setCurrentPage(currentPage == null ? 1 : currentPage);
     * BrandesInfo brandesInfo = new BrandesInfo();
     * brandesInfo.setBrandId(brandId);
     * // brandesInfo.setCreateTime(MapUtils.getLong(map, "createTime"));
     * PaginateResult<BrandesInfo> result = brandesInfoService.getBrandesInfos(brandesInfo, pagaination);
     * for (BrandesInfo b : result.getList())
     * {
     * b.setSubList(null);
     * }
     * return super.getJsonMessage(CommonConst.SUCCESS, result);
     * }
     */
    /**
     * 通过品牌Id获取品牌的名称以及品牌商Id
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/partBrandesInfos", method = RequestMethod.POST)
    public JsonMessage partBrandesInfos(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String brandsId = MapUtils.getString(map, "brandsId");
        if (StringUtils.isBlank(brandsId)) { return JsonMessageUtil.getJsonMessage(CommonConst.PARAM_NULL); }
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandsId);
        Map<String, Object> dataMap = Maps.newConcurrentMap();
        if (null != brandesInfo)
        {
            dataMap.put("brandsId", brandesInfo.getRefrenceId());
            dataMap.put("brandsName", brandesInfo.getBrandsName());
            dataMap.put("supplierId", brandesInfo.getBrandId());
        }
        return super.getJsonMessage(CommonConst.SUCCESS, dataMap);
    }
    
    /**
     * 控制品牌首页是否显示
     * @author 陈建平
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/updateShow", method = RequestMethod.POST)
    public JsonMessage updateShow(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        short showed = MapUtils.getShort(map, "showed");
        brandesInfoService.updateShowed(refrenceId, showed);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 更新品牌的"工厂店品牌"
     * @author 陈建平
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/updateFactoryStore")
    @ResponseBody
    public JsonMessage updateFactoryStore(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        boolean isFactoryStore = MapUtils.getBoolean(map, "isFactoryStore");
        String brandsId = MapUtils.getString(map, "brandsId");
        String deposit = MapUtils.getString(map, "deposit");
        if (StringUtils.isBlank(brandsId) || (isFactoryStore && StringUtils.isBlank(deposit)))// 工厂店为true时,押金不为空
        { return JsonMessageUtil.getJsonMessage(CommonConst.PARAM_NULL); }
        if (!isFactoryStore) // 非工厂店品牌,默认押金为0;
        { return JsonMessageUtil.getJsonMessage(CommonConst.FAIL); // 不支持将"工厂店品牌"修改为"普通品牌"
        }
        BigDecimal bd = new BigDecimal(deposit);
        brandesInfoService.updateFactoryStore(brandsId, isFactoryStore, bd);
        return JsonMessageUtil.getJsonMessage(CommonConst.SUCCESS);
    }
}
