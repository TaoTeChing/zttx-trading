package com.zttx.web.module.dealer.controller;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.EncryptUtils;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.dubbo.service.DealerOrderDubboService;
import com.zttx.web.dubbo.service.DealerShoperDubboService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerShopers;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.UsernamePasswordToken;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.IPUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>File：DealerErpController.java </p>
 * <p>Title: 门店ERP免登陆后的操作控制器 </p>
 * <p>Description: DealerErpController </p>
 * <p>Copyright: Copyright (c) 15/9/16</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/erp")
public class DealerErpController extends GenericController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private DealerShoperDubboService dealerShoperDubboService;
    @Autowired
    private DealerOrderDubboService dealerOrderDubboService;

    @RequestMapping("/test")
    public String testErp(Model model) throws BusinessException
    {
        String date = CalendarUtils.getDate(new Date(), "yyyyMMdd");
        String dealerId = "4925122E80994AA083864248C64BEDAD";
        String userDes = "zttx";
        String encrypt = EncryptUtils.encrypt(dealerId + userDes + date, "MD5");
        model.addAttribute("dealerId", dealerId);
        model.addAttribute("userDes", userDes);
        model.addAttribute("encrypt", encrypt);
        return "common/test";
    }
    @RequestMapping("/test/addCart")
    public String testAddCart(String dealerId ,@RequestParam(value = "productId")List<String> productId,
                              @RequestParam(value = "productSkuId") List<String> productSkuId,@RequestParam(value = "purchaseNum") List<Integer> purchaseNum)
    {
        List<DealerShopers> dealerShopersList = Lists.newArrayList();

        for (int i = 0; i < productId.size(); i++) {
            DealerShopers dealerShopers = new DealerShopers();
            dealerShopers.setProductId(productId.get(i));
            dealerShopers.setProductSkuId(productSkuId.get(i));
            dealerShopers.setPurchaseNum(purchaseNum.get(i));
            dealerShopersList.add(dealerShopers);
        }
        dealerShoperDubboService.addProductIntoShopper(dealerId, dealerShopersList);
        return "redirect:/erp/test";
    }


    @RequestMapping("/test/search")
    public String testSearch(String dealerId, String orderId,Model model) throws BusinessException
    {
        DealerOrder dealerOrder = new DealerOrder();
        dealerOrder.setDealerId(dealerId);
        dealerOrder.setOrderId(Long.valueOf(orderId));
        Pagination page = new Pagination();
        List<Map<String,Object>>  result = dealerOrderDubboService.search(dealerOrder, page);
        model.addAttribute("result",result);
        return "common/test";
    }
    /**
     * 购物车地址
     *
     * @param request
     * @param dealerId
     * @param userDes
     * @param encrypt
     * @return
     */
    @RequestMapping("/shopper")
    public String shopper(HttpServletRequest request, String dealerId, String userDes, String encrypt) {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null != principal && dealerId.equals(principal.getRefrenceId())) {
            return "redirect:/dealer/dealerShoper/cart?isClient=true";
        }
        String date = CalendarUtils.getDate(new Date(), "yyyyMMdd");
        String encryptcheck = EncryptUtils.encrypt(dealerId + userDes + date, "MD5");
        if (encrypt == null || !encrypt.equals(encryptcheck)) return "dealer/erp_shopper_error";
        UserInfo userInfo = userInfoService.selectByPrimaryKey(dealerId);
        if (null != userInfo) {
            String ip = IPUtil.getOriginalIpAddr(request);
            AuthenticationToken token = new UsernamePasswordToken(userInfo.getUserMobile(), userInfo.getUserPwd().toCharArray(), ip, true);
            Subject currentUser = SecurityUtils.getSubject();
            try {
                currentUser.login(token);
                return "redirect:/dealer/dealerShoper/cart?isClient=true";
            } catch (Exception e) {
                return "dealer/erp_shopper_error";
            }
        }
        return "dealer/erp_shopper_error";
    }

    /**
     * 财务帐地址  吴万杰用
     *
     * @param request
     * @param userDes
     * @param encrypt
     * @return
     */
    @RequestMapping(value = "/financial",method = RequestMethod.GET)
    public String financial(HttpServletRequest request,String dealerId,String userDes, String encrypt) {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null != principal && dealerId.equals(principal.getRefrenceId())) {
            return "redirect:/dealer/dealerFinancial/financial";
        }
        String date = CalendarUtils.getDate(new Date(), "yyyyMMdd");
        String encryptcheck = EncryptUtils.encrypt(dealerId + userDes + date, "MD5");
        if (encrypt == null || !encrypt.equals(encryptcheck)) return "dealer/erp_shopper_error";
        UserInfo userInfo = userInfoService.selectByPrimaryKey(dealerId);
        if(userInfo == null || !userInfo.getUserType().equals(DealerConstant.userType.DEALER_TYPE)){ return "dealer/erp_shopper_error";}
        if (null != userInfo) {
            String ip = IPUtil.getOriginalIpAddr(request);
            AuthenticationToken token = new UsernamePasswordToken(userInfo.getUserMobile(), userInfo.getUserPwd().toCharArray(), ip, true);
            Subject currentUser = SecurityUtils.getSubject();
            try {
                currentUser.login(token);
                return "redirect:/dealer/dealerFinancial/financial";
            } catch (Exception e) {
                return "dealer/erp_shopper_error";
            }
        }
        return "dealer/erp_shopper_error";
    }
}
