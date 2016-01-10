package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.entity.DealerClass;
import com.zttx.web.module.dealer.mapper.DealerClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DealerClassDubboServiceImpl
 *
 * @author 江枫林
 * @date 2015/9/9
 */
@Service
public class DealerClassDubboServiceImpl implements DealerClassDubboService {

    @Autowired
    DealerClassMapper dealerClassMapper;

    /**
     * 查询终端商分类列表
     *
     * @param dealerClass
     * @param page
     * @return
     */
    @Override
    public PaginateResult<DealerClass> queryDealerClassList(DealerClass dealerClass, Pagination page) {
        PaginateResult<DealerClass> result = new PaginateResult<DealerClass>();
        List<DealerClass> dealerClassList = dealerClassMapper.getDealerClassList(dealerClass,page);
        result.setList(dealerClassList);
        return result;
    }
}
