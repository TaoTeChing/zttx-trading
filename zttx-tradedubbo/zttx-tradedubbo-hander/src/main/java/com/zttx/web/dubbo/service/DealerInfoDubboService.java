package com.zttx.web.dubbo.service;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.dealer.entity.DealerInfo;

/**
 * <p>DealerInfoDubboService.java </p>
 * <p>Title: 品牌远程服务接口 </p>
 * <p>Description: BrandsDubboService </p>
 * <p>Copyright: Copyright (c) 2014 08/19/2015 11:09</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public interface DealerInfoDubboService {

    /**
     * 查询终端商信息
     *
     * @param dealerId
     * @return
     */
    public DealerInfo queryDealerInfoById(String dealerId);

    /**
     * 查询品牌商加盟的所有终端商信息
     *
     * @param pagaination
     * @param brandInvite
     * @return
     */
    PaginateResult<DealerInfo>   queryDealerInfosList(Pagination pagaination,BrandInvite brandInvite);
    /**
     * 查询没有和品牌商有加盟关系的
     * @param brandId
     * @param brandId
     * @return
     */
    PaginateResult<DealerInfo>   queryDealerInfosNoRelationList(String brandId,Pagination pagaination);
    
}
