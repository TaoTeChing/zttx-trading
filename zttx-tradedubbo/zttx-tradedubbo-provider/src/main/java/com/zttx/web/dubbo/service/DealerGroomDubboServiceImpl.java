package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerGroom;
import com.zttx.web.module.dealer.mapper.DealerGroomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DealerGroomDubboServiceImpl
 *
 * @author 江枫林
 * @date 2015/9/7
 */
@Service
public class DealerGroomDubboServiceImpl implements DealerGroomDubboService {

    @Autowired
    DealerGroomMapper dealerGroomMapper;

    /**
     * 查询推荐的品牌商列表
     *
     * @param dealerGroom
     * @param pagination
     * @return
     * @throws BusinessException
     */
    @Override
    public PaginateResult<DealerGroom> queryDealerGrooms(DealerGroom dealerGroom, Pagination pagination) throws BusinessException {
        PaginateResult<DealerGroom> paginateResult = new PaginateResult<DealerGroom>();
        List<DealerGroom> dealerGroomList = dealerGroomMapper.queryDealerGrooms(dealerGroom,pagination);
        paginateResult.setList(dealerGroomList);
        paginateResult.setPage(pagination);
        return paginateResult;
    }
}
