/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.brand.entity.BrandCard;
import com.zttx.web.module.brand.mapper.BrandCardMapper;
import com.zttx.web.module.brand.model.BrandCardModel;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.NetworkUtils;

/**
 * 品牌商证书信息 服务实现类
 * <p>File：BrandCard.java </p>
 * <p>Title: BrandCard </p>
 * <p>Description:BrandCard </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandCardServiceImpl extends GenericServiceApiImpl<BrandCard> implements BrandCardService
{
    private BrandCardMapper brandCardMapper;
    
    @Autowired(required = true)
    public BrandCardServiceImpl(BrandCardMapper brandCardMapper)
    {
        super(brandCardMapper);
        this.brandCardMapper = brandCardMapper;
    }
    
    @Override
    public PaginateResult<BrandCard> search(Pagination page, String brandId)
    {
        List<BrandCard> brandCardList = brandCardMapper.pageSearch(page, brandId);
        PaginateResult paginateResult = new PaginateResult(page, brandCardList);
        return paginateResult;
    }
    
    @Override
    public BrandCard getEntity(String refrenceId, String brandId)
    {
        return brandCardMapper.getEntity(refrenceId, brandId);
    }

    @Override
    public void saveImage(BrandCardModel newCard, BrandCard oldCard) throws BusinessException {
        if (StringUtils.isNotBlank(newCard.getCertImagePath())) {
            //String path = MultipartUtils.moveAndresizeFile(request, MultipartUtils.BRAND_IMG_PATH, newCard.getCertImagePath(), UploadAttCateConst.BRAND_CARD);
            newCard.setDomainName(NetworkUtils.getDoMainName());
            //newCard.setCertImage(path);
            newCard.setCertImage(newCard.getCertImagePath());
        } else {
            newCard.setDomainName(oldCard.getDomainName());
            newCard.setCertImage(oldCard.getCertImage());
            newCard.setCertPhoto(oldCard.getCertPhoto());
        }
    }

    @Override
    public void save(BrandCardModel newCard, BrandCard oldCard) {
        if (null != oldCard) {
            oldCard.setCertName(newCard.getCertName());
            oldCard.setDomainName(newCard.getDomainName());
            oldCard.setCertPhoto(newCard.getCertPhoto());
            oldCard.setCertImage(newCard.getCertImage());
            oldCard.setCertMark(newCard.getCertMark());
            this.brandCardMapper.updateByPrimaryKey(oldCard);
        } else {
            newCard.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            newCard.setCreateTime(CalendarUtils.getCurrentLong());
            this.brandCardMapper.insert(newCard);
        }
    }
}
