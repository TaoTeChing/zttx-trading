package com.zttx.web.module.common.service;

import com.zttx.web.bean.Watermark;
import com.zttx.web.module.common.entity.UploadAttSize;
import com.zttx.web.utils.ImageUtils;
import com.zttx.web.utils.LoggerUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>File：ImageServiceImpl.java </p>
 * <p>Title: ImageServiceImpl </p>
 * <p>Description: ImageServiceImpl </p>
 * <p>Copyright: Copyright (c) 2014 08/10/2015 13:56</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Component
public class ImageService
{
    private static final Logger  logger = LoggerFactory.getLogger(ImageService.class);
    
    @Autowired
    private UploadAttSizeService uploadAttSizeService;
    
    /**
     * 压缩图片
     * @param file
     * @param cateKey
     * @throws IOException
     * @author 夏铭
     */
    public void resizeImage(File file, String cateKey) throws IOException
    {
        resizeImage(file, cateKey, null);
    }
    
    /**
     * 压缩图片, 可以加水印
     * @param file
     * @param cateKey
     * @param watermark
     * @throws IOException
     * @author 夏铭
     */
    public void resizeImage(File file, String cateKey, Watermark watermark) throws IOException
    {
        String fileName = file.getAbsolutePath();
        String extension = FilenameUtils.getExtension(fileName);
        FileUtils.copyFile(file, new File(fileName + "_orig." + extension));
        if (StringUtils.isNotBlank(cateKey))
        {
            List<UploadAttSize> uploadAttSizes = uploadAttSizeService.findByCateKey(cateKey);
            if (!CollectionUtils.isEmpty(uploadAttSizes)) for (UploadAttSize uploadAttSize : uploadAttSizes)
            {
                String pathname = fileName + "_" + uploadAttSize.getWidth() + "x" + uploadAttSize.getHeight() + "." + extension;
                File newFile = new File(pathname);
                File temp = ImageUtils.resizeImage(file, uploadAttSize.getWidth(), uploadAttSize.getHeight());
                try
                {
                    com.zttx.web.utils.FileUtils.moveFile(temp, newFile, true);
                }
                catch (Exception e)
                {
                    LoggerUtils.logError(logger, e.getLocalizedMessage());
                }
            }
        }
    }
}
