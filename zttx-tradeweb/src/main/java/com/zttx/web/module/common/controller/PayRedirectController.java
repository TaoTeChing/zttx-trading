package com.zttx.web.module.common.controller;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>File：PayRedirectController.java </p>
 * <p>Title: 结算平台回调控制器 </p>
 * <p>Description:PayRedirectController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/pay")
public class PayRedirectController extends GenericController
{
    /**
     * 根据当前用户会话判断用户类型，然后跳转到具体用户类型所对应的后台
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/back")
    public String backup() throws BusinessException
    {
        String url = "redirect:/";
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null != principal)
        {
            if (principal.getUserType().equals(BrandConstant.userType.BRAND_TYPE))
            {
                url = "redirect:/brand/center";
            }
            else
            {
                url = "redirect:/dealer/center";
            }
        }
        return url;
    }
}
