package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.mapper.DealerImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DealerImageDubboServiceImpl
 *
 * @author 江枫林
 * @date 2015/9/9
 */
@Service
public class DealerImageDubboServiceImpl implements DealerImageDubboService {

    @Autowired
    DealerImageMapper dealerImageMapper;
    /**
     * 查询终端商图片列表
     *
     * @param dealerIdList
     * @param page
     * @return
     */
    @Override
    public PaginateResult<DealerImage> queryDealerImagesList(List<String>dealerIdList,Long updateTime, Pagination page) {
        PaginateResult<DealerImage> imagePaginateResult = new  PaginateResult<DealerImage>();
        Map<String ,List<String>> idMap = new HashMap<String,List<String>>();
        idMap.put("idList",dealerIdList);
        List<DealerImage> dealerImageList = dealerImageMapper.selectDealerImages(idMap,updateTime,page);
        imagePaginateResult.setList(dealerImageList);
        imagePaginateResult.setPage(page);
        return null;
    }

}
