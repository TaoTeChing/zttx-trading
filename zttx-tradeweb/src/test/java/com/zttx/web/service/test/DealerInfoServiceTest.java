package com.zttx.web.service.test;

import com.google.common.collect.Maps;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerOrdercService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

/**
 * Created by Administrator on 2015/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class DealerInfoServiceTest {
    @Autowired
    private DealerInfoService dealerInfoService;
    @Autowired
    private DealerOrdercService dealerOrdercService;
    @Autowired
    private DealerInfoMapper   dealerInfoMapper;

    @Test
    public void getDealerInfo()
    {
        String dealerId = "00001FD11C8311E4BF7E2EFE7342EFEC";
        DealerInfo dealerInfo =  dealerInfoService.getDealerInfo(dealerId);
        System.out.println(dealerInfo.getAreaNo());
    }
     @Test  //测试分页
     public void paginationTest()
     {
         Pagination pagination = new Pagination();
         pagination.setPageSize(1);
         /*PaginateResult paginateResult = dealerOrdercService.findOrdercProductsByDealerId("", pagination, null);*/
        /* List<DealerOrderc> dealerOrdercList =  paginateResult.getList();*/

     }


     @Test
     public void  mapTest()
     {
         Map<String,Object> maps = Maps.newHashMap();
         maps.put("dealerId", "E30EA1437E0343D6907138D67641ED39");
         maps.put("brandId","D2F01CD58A014A8FAB9F562CD7BD877E");
         Pagination pagination = new Pagination();
         maps.put("pagination",pagination);
        // List<Map<String,Object>> listMap = dealerOrdercService.listMap(maps);
         //System.out.println(listMap.get(0).get("dealNo"));
     }
}
