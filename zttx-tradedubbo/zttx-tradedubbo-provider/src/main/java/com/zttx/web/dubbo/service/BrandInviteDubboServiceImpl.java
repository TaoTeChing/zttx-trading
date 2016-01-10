package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.model.BrandInviteModel;
import com.zttx.web.module.brand.service.BrandInviteService;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.service.DealerJoinService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BrandInviteDubboServiceImpl
 *
 * @author 江枫林
 * @date 2015/9/7
 */
@Service
public class BrandInviteDubboServiceImpl implements BrandInviteDubboService {

    @Autowired
    BrandInviteService brandInviteService;
    @Autowired
    DealerJoinService dealerJoinService;

    /**
     * 获取加盟关系列表
     *
     * @param pagination
     * @param brandInvite
     * @return
     * @throws Exception
     */
    @Override
    public PaginateResult<BrandInvite> applyOrInvite(Pagination pagination, BrandInviteModel brandInvite) throws Exception {
        return brandInviteService.getBrandInvites(pagination, brandInvite);
    }

    /**
     * 同意加盟
     *
     * @param refrenceId
     * @return
     * @throws Exception
     */
    @Override
    public void agreeApply(String refrenceId) throws BusinessException {
        BrandInvite brandInvite = brandInviteService.selectByPrimaryKey(refrenceId);
        if (null == brandInvite) {
            throw new BusinessException("不存在该经销商申请加盟该品牌信息");
        }
        if (brandInvite.getApplyState() != (short) 0 && brandInvite.getApplyState() != (short) 3) {
            throw new BusinessException("该记录已做处理，操作失败");
        }
        brandInviteService.updateBrandInvite(brandInvite.getRefrenceId());
    }

    /**
     * 品牌商点拒绝加盟
     *
     * @param refrenceId
     * @param auditMark
     * @throws BusinessException
     */
    @Override
    public void refuseDealer(String refrenceId, String auditMark) throws BusinessException {
        if (StringUtils.isBlank(refrenceId)) {
            throw new BusinessException("请求参数不能为空");
        }
        BrandInvite brandInvite = brandInviteService.selectByPrimaryKey(refrenceId);
        if (null == brandInvite) {
            throw new BusinessException("不存在该经销商申请加盟该品牌信息");
        }
        if (brandInvite.getApplyState() != (short) 0) {
            throw new BusinessException("该记录已做处理，操作失败");
        }
        brandInvite.setAuditMark(auditMark);
        brandInviteService.insertRejectApply(brandInvite);
    }

    /**
     * 指定品牌商邀请经销商加盟(用于品牌商erp)
     *
     * @param dealerId
     * @param brandsId
     * @return
     * @throws BusinessException
     */
    @Override
    public BrandInvite inviteJoinBrandErp(String dealerId, String brandsId, String inviteText) throws BusinessException {
        BrandInviteModel brandInvite = new BrandInviteModel();
        brandInvite.setInviteText(inviteText);
        brandInvite.setSourceType((short) 2);// 2表示来源于品牌商erp
        return brandInviteService.brand_invite_dealer(dealerId, brandsId, brandInvite);
    }

    @Override
    public void removeApply(String refrenceId) throws BusinessException {
        if (StringUtils.isBlank(refrenceId)) {
            throw new BusinessException("请求参数不能为空");
        }
        BrandInvite brandInvite = brandInviteService.selectByPrimaryKey(refrenceId);
        if (null == brandInvite) {
            throw new BusinessException("不存在该经销商申请加盟该品牌信息");
        }
        if (brandInvite.getApplyState() != (short) 0 && brandInvite.getApplyState() != (short) 3) {
            throw new BusinessException("该记录已做处理，操作失败");
        }
        // 1表示来源是经销商申请,5表示撤销申请
        if (brandInvite.getOpratorCata() == 1) {
            brandInviteService.updateInviteRemove(brandInvite);
        }
        // 2表示来源是品牌商邀请,7表示撤销邀请
        if (brandInvite.getOpratorCata() == 2) {
            brandInviteService.removeInvite(brandInvite);
        }
    }

    /**
     * 终止合作
     *
     * @param refrenceId
     * @throws Exception
     */
    @Override
    public void discontinueDealer(String refrenceId) throws BusinessException {
        if (StringUtils.isBlank(refrenceId)) {
            throw new BusinessException("请求参数不能为空");
        }
        BrandInvite brandInvite = brandInviteService.selectByPrimaryKey(refrenceId);
        if (null == brandInvite || brandInvite.getApplyState() != (short) 1) {
            throw new BusinessException("经销商未与该品牌商合作");
        }
        DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(brandInvite.getDealerId(), brandInvite.getBrandsId());
        brandInviteService.discontinueDealer(dealerJoin,"");
    }

}
