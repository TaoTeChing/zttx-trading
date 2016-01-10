/**
 * 
 */
package com.zttx.web.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import net.coobird.thumbnailator.Thumbnailator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zttx.sdk.exception.FileOperateException;

/**
 * <p>File：TargetImgUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014-12-15 下午1:37:40</p>
 * <p>Company: 8637.com</p>
 * @author 吕岳斌
 * @version 1.0
 */
public class TargetImgUtils
{
    private static Log logger        = LogFactory.getLog(TargetImgUtils.class);
    
    private static int MAXTARGETSIZE = 900;                                    // 压缩判断临界点，可调整
    
    // 压缩规则
    // w=上传图片宽度 h=上传图片高度
    //
    // 当w h 都小于MAXTARGETSIZE 不压缩，做copy
    //
    //
    // 当w h 有一个大于MAXTARGETSIZE 做压缩 ：
    // 获取hw比例 r=H/W，
    // r>=1 按 w=220 h=w*r;
    // w=440 h=w*r;
    // w=750 h=w*r 进行等比压缩。
    //
    // 否则 h=220 w=h/r;
    // h=440 w=h/r;
    // h=750 w=h/r 进行等比压缩。
    //
    /**
     * 压缩成多尺寸图
     *Boolean
     *  @version         1.0, 2014-12-15 下午3:28:33    
     * @param fullPath 图片原路径：/upload/image/2014/20140901/5FE16F6DB2F44520A6698BC67D1A3AFF.jpg
     * @param targetSizes 压缩大小集合
     * @return
     * @throws FileOperateException  
     * @author 吕岳斌
     */
    public static Boolean targetImageBySizes(String fullPath, List<Integer> targetSizes) throws FileOperateException
    {
        // fullPath = WebRootListener.getWebRoot()+fullPath;
        String extension = FilenameUtils.getExtension(fullPath);
        File f = new File(fullPath);
        // 获取图片尺寸
        int width = -1;
        int hight = -1;
        ImageInputStream imageStream = null;
        try
        {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(extension);
            ImageReader reader = (ImageReader) readers.next();
            imageStream = ImageIO.createImageInputStream(f);
            reader.setInput(imageStream, true);
            width = reader.getWidth(0);
            hight = reader.getHeight(0);
        }
        catch (IOException e1)
        {
            return false;
        }
        finally
        {
            try
            {
                imageStream.close();
            }
            catch (IOException e)
            {
                logger.error(e);
                return false;
            }
        }
        for (Integer targetSize : targetSizes)
        {
            String targetName = getTargetPath(fullPath, targetSize, extension);
            File targetFile = new File(targetName);
            // 压缩图片
            try
            {
                if (width >= MAXTARGETSIZE || hight >= MAXTARGETSIZE)
                {
                    double scale = (double) hight / (double) width;
                    if (scale >= 1)
                    {// 按宽度进行压缩，修改H大小
                        targetSize = (int) (targetSize * scale);
                        if (!targetFile.exists())
                        {
                            Thumbnailator.createThumbnail(f, targetFile, targetSize, targetSize);
                        }
                    }
                    else
                    {// 按高度进行压缩，直接等比压缩
                        targetSize = (int) (targetSize / scale);
                        if (!targetFile.exists())
                        {
                            Thumbnailator.createThumbnail(f, targetFile, targetSize, targetSize);
                        }
                    }
                }
                else
                {
                    File moveDest = new File(targetName);
                    FileUtils.copyFile(f, moveDest);
                }
            }
            catch (Exception e)
            {
                logger.error(e);
                return false;
            }
        }
        return true;
    }
    
    /**
     *图片压缩
     *Boolean
     *  @version         1.0, 2014-12-15 下午2:25:29    
     * @param fullPath 图片原路径：/upload/image/2014/20140901/5FE16F6DB2F44520A6698BC67D1A3AFF.jpg
     * @param targetSize 压缩大小：440
     * @return
     * @throws FileOperateException  
     * @author 吕岳斌
     */
    public static Boolean targetImage(String fullPath, int targetSize) throws FileOperateException
    {
        // fullPath = WebRootListener.getWebRoot()+fullPath;
        String extension = FilenameUtils.getExtension(fullPath);
        File f = new File(fullPath);
        String targetName = getTargetPath(fullPath, targetSize, extension);
        File targetFile = new File(targetName);
        // 获取图片尺寸
        int width = -1;
        int hight = -1;
        ImageInputStream imageStream = null;
        try
        {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(extension);
            ImageReader reader = (ImageReader) readers.next();
            imageStream = ImageIO.createImageInputStream(f);
            reader.setInput(imageStream, true);
            width = reader.getWidth(0);
            hight = reader.getHeight(0);
        }
        catch (IOException e1)
        {
            return false;
        }
        finally
        {
            try
            {
                imageStream.close();
            }
            catch (IOException e)
            {
                logger.error(e);
                return false;
            }
        }
        // 压缩图片
        try
        {
            if (width >= MAXTARGETSIZE || hight >= MAXTARGETSIZE)
            {
                double scale = (double) hight / (double) width;
                if (scale >= 1)
                {// 按宽度进行压缩，修改H大小
                    targetSize = (int) (targetSize * scale);
                    if (!targetFile.exists())
                    {
                        Thumbnailator.createThumbnail(f, targetFile, targetSize, targetSize);
                    }
                }
                else
                {// 按高度进行压缩，直接等比压缩
                    targetSize = (int) (targetSize / scale);
                    if (!targetFile.exists())
                    {
                        Thumbnailator.createThumbnail(f, targetFile, targetSize, targetSize);
                    }
                }
            }
            else
            {
                File moveDest = new File(targetName);
                FileUtils.copyFile(f, moveDest);
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            return false;
        }
        return true;
    }
    
    /**
     *获取压缩图片全路径
     *String
     *  @version         1.0, 2014-12-15 下午1:57:18    
     * @param srcPath	
     * @param targetSize
     * @param extension
     * @return  
     * @author 吕岳斌
     */
    private static String getTargetPath(String srcPath, int targetSize, String extension)
    {
        String result = srcPath + "_" + targetSize + "x" + targetSize + "." + extension;
        return result;
    }
    
    public static void main(String[] args) throws FileOperateException
    {
        File directory = new File("E:/work/editor");
        String ext = "png,jpg";
        Collection<File> files = FileUtils.listFiles(directory, ext.split(","), true);
        for (File f : files)
        {
            List<Integer> targetSizes = new ArrayList<Integer>();
            targetSizes.add(660);
            targetSizes.add(550);
            targetSizes.add(440);
            targetSizes.add(220);
            System.out.println(targetImageBySizes(f.getPath(), targetSizes));
            // System.out.println(targetImage(f.getPath(), 440));
        }
    }
}
