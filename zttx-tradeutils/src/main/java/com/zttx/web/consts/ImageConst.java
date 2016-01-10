package com.zttx.web.consts;

import java.io.File;

/**
 * 
 * <p>File：ImageConst.java</p>
 * <p>Title: ImageConst</p>
 * <p>Description:ImageConst</p>
 * <p>Copyright: Copyright (c) Aug 13, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public class ImageConst
{
	// 图片类型
    public static final int     IMAGE             = 1;
    
    // 普通文本验证
    public static final int     TEXT              = 2;
    
    // 视频验证
    public static final int     VIDEO             = 3;
    
    // Flash验证
    public static final int     FLASH             = 4;
    
    // 通用类型
    public static final int     COMMON            = 5;
    
    // FLASH组件图片上传
    public static final int     FLASH_IMAGE       = 6;
    
    // 压缩文件验证
    public static final int     ZIP               = 7;
    
    // flv
    public static final int     FLV               = 8;
    
    // 静态页面类型
    protected static final int  STATIC            = 1;
    
    // 文章页面类型
    protected static final int  ARTICLE           = 2;
    
    // 是否删除没有转码之前的视频文件
    protected static boolean    ISDELETE          = false;
    
    // 文件分割符 适用于文件夹上的反斜杠
    public final static String  File_SEPARATOR    = "/";//文件服务器统一转换 分隔符，避免跨系统传地址出错
    
    // 经销商图片上传路径
    public final static String  DEALER_IMG_PATH   = "upload" + File_SEPARATOR + "dealer" + File_SEPARATOR + "img";
    
    // 经销商文档上传路径
    public final static String  DEALER_DOC_PATH   = "upload" + File_SEPARATOR + "dealer" + File_SEPARATOR + "doc";
    
    // 品牌商图片上传路径
    public final static String  BRAND_IMG_PATH    = "upload" + "/" + "brand" + "/" + "img";
    
    // 品牌商文档上传路径
    public final static String  BRAND_DOC_PATH    = "upload" + "/" + "brand" + "/" + "doc";
    
    // 商学院图片上传路径
    public final static String  SCHOOL_IMG_PATH   = "upload" + File_SEPARATOR + "school" + File_SEPARATOR + "img";
    
    // 商学院文件上传路径
    public final static String  SCHOOL_DOC_PATH   = "upload" + File_SEPARATOR + "school" + File_SEPARATOR + "doc";
    
    // 品牌商图库上传路径
    public final static String  BRAND_LIB         = "upload" + File_SEPARATOR + "brand" + File_SEPARATOR + "lib";
    
    public final static String  COMMON_IMG_PATH   = "upload" + File_SEPARATOR + "common" + File_SEPARATOR + "img";
    
    // 存放客服图片的路径
    public final static String  CUSTOMER_IMG_PATH = "upload" + File_SEPARATOR + "customer" + File_SEPARATOR + "img";
    
    // 编辑器图片存储方式
    public final static String  EDITOR_IMG_PATH   = "upload" + File_SEPARATOR + "editor";
    
    // 静态的FreeMarket页面
    public static final String  STATIC_HTML       = "html";
    
    // 文章的FreeMarket页面
    public static final String  ARTICLE_HTML      = "WEB-INF" + File_SEPARATOR + "view" + File_SEPARATOR + "html";
    
    // 适用于页面上的反斜杠
    public final static String  HTML_TAG          = "/";
    
    // 存放临时文件的目录
    public final static String  TEMP              = "temp";
    
    // 视频目录
    public final static String  VIDEO_PATH        = "video";
}
