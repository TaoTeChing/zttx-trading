package com.zttx.web.dubbo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandesInfoMapper;

/**
 * <p>File：BrandsDubboServiceImpl.java </p>
 * <p>Title: 品牌远程服务接口实现类 </p>
 * <p>Description: BrandsDubboServiceImpl </p>
 * <p>Copyright: Copyright (c) 2014 08/19/2015 11:23</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
@Service
public class BrandsDubboServiceImpl implements BrandsDubboService {

    @Autowired
    BrandesInfoMapper brandesInfoMapper;

    /**
     * 根据品牌id查询品牌信息
     *
     * @param brandsId
     * @return
     */
    @Override
    public Map<String, Object> getBrandsInfoByBrandsId(String brandsId) {
        return brandesInfoMapper.getBrandsInfoByBrandsId(brandsId);
    }

    /**
     * 查询品牌商下品牌信息列表
     *
     * @param pagination
     * @param brandesInfo
     * @return
     * @throws BusinessException
     */
    @Override
    public PaginateResult<BrandesInfo> queryBrandesInfosList(Pagination pagination, BrandesInfo brandesInfo) throws BusinessException {
        PaginateResult<BrandesInfo> paginateResult = new PaginateResult<BrandesInfo>();
        List<BrandesInfo> brandesInfoList = brandesInfoMapper.queryBrandesInfosList(pagination, brandesInfo);
        paginateResult.setList(brandesInfoList);
        paginateResult.setPage(pagination);
        return paginateResult;
    }


}
