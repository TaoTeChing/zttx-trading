package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.module.brand.entity.BrandViewContact;
import com.zttx.web.module.brand.mapper.BrandViewContactMapper;
import com.zttx.web.utils.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BrandViewContactDubboServiceImpl
 *
 * @author 江枫林
 * @date 2015/9/9
 */
@Service
public class BrandViewContactDubboServiceImpl implements BrandViewContactDubboService{

    @Autowired
    BrandViewContactMapper brandViewContactMapper;

    /**
     * 查询品牌商查看经销商查看记录列表
     *
     * @param page
     * @param brandViewContact
     * @return
     */
    @Override
    public PaginateResult<BrandViewContact> queryBrandViewContactsList(Pagination page, BrandViewContact brandViewContact) {
        PaginateResult<BrandViewContact> brandViewContactPaginateResult = new  PaginateResult<BrandViewContact>();
        List<BrandViewContact> brandViewContactList = brandViewContactMapper.queryBrandViewContactsList(brandViewContact,page);
        brandViewContactPaginateResult.setPage(page);
        brandViewContactPaginateResult.setList(brandViewContactList);
        return brandViewContactPaginateResult;
    }

    /**
     * 保存品牌商查看终端商记录
     *
     * @param brandViewContact
     */
    @Override
    public BrandViewContact addBrandViewContact(BrandViewContact brandViewContact) {
        BrandViewContact isExist = brandViewContactMapper.isExistForBrandViewContact(brandViewContact);

        if(isExist==null){
            isExist = new BrandViewContact();
            isExist.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            isExist.setBrandId(brandViewContact.getBrandId());
            isExist.setDealerId(brandViewContact.getDealerId());
            isExist.setViewType( BrandConstant.BrandViewContact.VIEW_TYPE_INITIATIVE);
            isExist.setViewTime(CalendarUtils.getCurrentLong());
            isExist.setDelFlag(false);
            brandViewContactMapper.insert(isExist);
        }

        return isExist;
    }
}
