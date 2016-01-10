package com.zttx.web.module.fronts.controller;

import com.google.code.kaptcha.Producer;
import com.zttx.web.security.RedisSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * <p>File：KaptchaController.java </p>
 * <p>Title: KaptchaController </p>
 * <p>Description: KaptchaController </p>
 * <p>Copyright: Copyright (c) 15/9/2</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
public class KaptchaController
{
    private static final Integer timeOut = 300;      // 五分钟
    
    @Autowired
    private Producer             producer;
    
    @Autowired
    private RedisSessionManager redisSessionManager;
    
    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Prama", "no-cache");
        response.setContentType("image/jpeg");
        String captext = producer.createText();
        redisSessionManager.remove(request, RedisSessionManager.SessionKey.CAPTCHA);
        redisSessionManager.put(request, RedisSessionManager.SessionKey.CAPTCHA, captext, timeOut);
        BufferedImage bi = producer.createImage(captext);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try
        {
            out.flush();
        }
        finally
        {
            out.close();
        }
        return null;
    }
}
