package com.zttx.web.dubbo.service;

import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.mapper.BrandInviteMapper;
import com.zttx.web.module.dealer.entity.DealerGroom;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.mapper.DealerGroomMapper;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DealerInfoDubboServiceImpl
 *
 * @author 江枫林
 * @date 2015/9/7
 */
@Service
public class DealerInfoDubboServiceImpl implements DealerInfoDubboService {

    private static Logger         logger         = LoggerFactory.getLogger(DealerInfoDubboServiceImpl.class);
    @Autowired
    BrandInviteMapper brandInviteMapper;
    @Autowired
    DealerGroomMapper dealerGroomMapper;
    @Autowired
    DealerInfoMapper dealerInfoMapper;

    /**
     * 查询终端商信息
     *
     * @param dealerId
     * @return
     */
    @Override
    public DealerInfo queryDealerInfoById(String dealerId) {
        return dealerInfoMapper.selectByPrimaryKey(dealerId);
    }
    /**
     * 查询品牌商加盟的所有终端商信息
     *
     * @param pagaination
     * @param brandInvite
     * @return
     */
    @Override
    public PaginateResult<DealerInfo> queryDealerInfosList(Pagination pagaination, BrandInvite brandInvite) {
        PaginateResult<DealerInfo> dealerList = new PaginateResult<DealerInfo>();
//        LoggerUtils.logInfo(logger,"参数1：" + JSONObject.toJSONString(pagaination));
//        LoggerUtils.logInfo(logger, "参数2：" + JSONObject.toJSONString(brandInvite));
        List<BrandInvite> brandInvites = brandInviteMapper.getBrandInvites(brandInvite);
        DealerGroom dealerGroom = new DealerGroom();
        dealerGroom.setBrandId(brandInvite.getBrandId());
        dealerGroom.setCreateTime(brandInvite.getUpdateTime());
        List<DealerGroom> dealerGrooms = dealerGroomMapper.listDealerGrooms(dealerGroom);
        List<DealerInfo> result = dealerInfoMapper.getDealerInfoList(this.getDealerId(brandInvites, dealerGrooms), pagaination);
//        LoggerUtils.logInfo(logger, "结果1：" + JSONObject.toJSONString(result));
        dealerList.setList(result);
        dealerList.setPage(pagaination);

        return dealerList;
    }

    /**
     * 构造所有终端商id列表
     *
     * @param brandInvites
     * @param dealerGrooms
     * @return
     */
    private Map<String,List<String>> getDealerId(List<BrandInvite> brandInvites, List<DealerGroom> dealerGrooms) {
        Map<String,List<String>> result = new HashMap<String,List<String>>();
        List<String> param = new ArrayList<String>();
        if (CollectionUtils.isEmpty(dealerGrooms) && CollectionUtils.isEmpty(brandInvites)) {
            return null;
        }
        if (!CollectionUtils.isEmpty(brandInvites)) {
            for (BrandInvite b : brandInvites) {
                param.add(b.getDealerId());
            }
        }
        if (!CollectionUtils.isEmpty(dealerGrooms)) {
            for (DealerGroom d : dealerGrooms) {
                param.add(d.getDealerId());
            }
        }
        result.put("idList",param);
        return result;
    }

    /**
     * 查询品牌商没有加盟的所有终端商信息
     *
     * @param pagaination
     * @param brandId
     * @return
     */
    @Override
    public PaginateResult<DealerInfo> queryDealerInfosNoRelationList(String brandId, Pagination pagaination) {
        PaginateResult<DealerInfo> dealerList = new PaginateResult<DealerInfo>();
        List<BrandInvite> list = brandInviteMapper.getBrandInvite_erp(brandId);
        List<DealerInfo> result = dealerInfoMapper.getNotDealerInfoList(getDealer(list), pagaination);
        dealerList.setList(result);
        dealerList.setPage(pagaination);

        return dealerList;
    }

    /**
     * 构造终端商id列表
     *
     * @param list
     * @return
     */
    private Map<String, List<String>> getDealer(List<BrandInvite> list) {
        Map<String, List<String>> params = Maps.newHashMap();
        List<String> param = new ArrayList<String>();
        //占位符，防止sql出现空异常
        param.add("1");
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (!CollectionUtils.isEmpty(list)) {
            for (BrandInvite d : list) {
                param.add(d.getDealerId());
            }
        }
        params.put("idList", param);
        return params;
    }
}
