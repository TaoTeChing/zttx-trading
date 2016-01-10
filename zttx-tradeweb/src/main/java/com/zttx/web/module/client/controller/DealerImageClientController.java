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
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.service.DealerImageService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerImageClientController.java </p>
 * <p>Title: DealerImageClientController </p>
 * <p>Description: 经销商店铺招牌接口 </p>
 * <p>Copyright: Copyright (c) 15/9/14</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/dealerImage")
public class DealerImageClientController extends GenericController
{
    @Autowired
    private DealerImageService dealerImageService;
    
    /**
     * 经销商图片列表
     * @param param 参数
     * @return JsonMessage
     * @throws BusinessException
     * @author 章旭楠
     */
    @ResponseBody
    @RequestMapping(value = "/dealerImage_band_erp", method = RequestMethod.POST)
    public JsonMessage dealerImageBandErp(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Integer pageSize = MapUtils.getInteger(map, "pageSize");
        Integer currentPage = MapUtils.getInteger(map, "currentPage");
        String dealerId = MapUtils.getString(map, "dealerId");
        Long createTime = MapUtils.getLong(map, "createTime");
        Pagination page = new Pagination();
        page.setPageSize(pageSize == null ? 10 : pageSize);
        page.setCurrentPage(currentPage == null ? 1 : currentPage);
        DealerImage dealerImage = new DealerImage();
        dealerImage.setDealerId(dealerId);
        dealerImage.setCreateTime(createTime);
        PaginateResult<DealerImage> result = dealerImageService.selectDealerImages(dealerImage, page);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
