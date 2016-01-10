package com.zttx.web.dubbo.service;

import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.mapper.DealDicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DealDicDubboServiceImpl
 *
 * @author 江枫林
 * @date 2015/9/9
 */
@Service
public class DealDicDubboServiceImpl implements DealDicDubboService {

    @Autowired
    DealDicMapper dealDicMapper;

    /**
     * 查询所有字典数据
     *
     * @return
     */
    @Override
    public List<DealDic> queryDealDicList() {
        return dealDicMapper.selectAll();
    }

}
