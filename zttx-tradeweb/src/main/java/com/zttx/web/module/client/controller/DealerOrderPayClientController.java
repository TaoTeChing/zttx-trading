package com.zttx.web.module.client.controller;

import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.dealer.entity.DealerOrderPay;
import com.zttx.web.module.dealer.service.DealerOrderPayService;
import com.zttx.web.utils.ParameterUtils;

/**
 * 支付记录
 * <p>File：DealerOrderPayClientController.java</p>
 * <p>Title: DealerOrderPayClientController</p>
 * <p>Description:DealerOrderPayClientController</p>
 * <p>Copyright: Copyright (c) Sep 22, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/dealerOrderPay")
public class DealerOrderPayClientController extends GenericController
{
    @Autowired
    private DealerOrderPayService dealerOrderPayService;
    
    /**
     * 同步全部支付记录
     * @author chenjp
     * @param param
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonMessage list(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Integer pageSize = MapUtils.getInteger(map, "pageSize");
        Integer currentPage = MapUtils.getInteger(map, "currentPage");
        Pagination page = new Pagination();
        page.setPageSize(pageSize == null ? 10 : pageSize);
        page.setCurrentPage(currentPage == null ? 1 : currentPage);
        DealerOrderPay filter = new DealerOrderPay();
        filter.setUpdateTime(MapUtils.getLong(map, "updateTime"));
        filter.setPage(page);
        PaginateResult<DealerOrderPay> result = new PaginateResult<DealerOrderPay>(filter.getPage(), dealerOrderPayService.findList(filter));
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
