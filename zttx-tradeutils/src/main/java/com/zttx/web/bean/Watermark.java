/*
 * @(#)Watermark 2014/5/12 10:35
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.bean;

/**
 * <p>File：Watermark</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/5/12 10:35</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
import java.awt.image.BufferedImage;

import com.zttx.web.consts.ApplicationConst;

public class Watermark
{
    private String        fontType = "黑体";                            // 字体类型
    
    private String        text     = ApplicationConst.WATER_MARK_WORD; // 文字内容
    
    private BufferedImage watermarkImg;                               // 水印图片
    
    /**
     * 
     */
    public Watermark()
    {
        super();
    }
    
    /**
     * @param text
     */
    public Watermark(String text)
    {
        super();
        this.text = text;
    }
    
    // private int position; // 水印位置
    public String getFontType()
    {
        return fontType;
    }
    
    public void setFontType(String fontType)
    {
        this.fontType = fontType;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public BufferedImage getWatermarkImg()
    {
        return watermarkImg;
    }
    
    public void setWatermarkImg(BufferedImage watermarkImg)
    {
        this.watermarkImg = watermarkImg;
    }
}
