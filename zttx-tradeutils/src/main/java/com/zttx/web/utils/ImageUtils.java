/*
 * @(#)ImageUtils.java 2014-1-8 下午1:13:12
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;
import java.io.*;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zttx.web.bean.Watermark;
import com.zttx.web.utils.gif.AnimatedGifEncoder;
import com.zttx.web.utils.gif.GifDecoder;

/**
 * <p>File：ImageUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:13:12</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
@SuppressWarnings("restriction")
public final class ImageUtils
{
    private static final Logger logger        = LoggerFactory.getLogger(ImageUtils.class);
    
    public static final int     IMAGE_JPEG    = 0;
    
    public static final int     IMAGE_PNG     = 1;
    
    private static final float  IMAGE_QUALITY = 0.95f;
    
    // 私有构造器，防止类的实例化
    private ImageUtils()
    {
        super();
    }
    
    /**
     * 图片缩放
     * @param image 原始图片
     * @param width 缩放后的长度
     * @param height 缩放后的高度
     * @return
     * @author 夏铭
     */
    public static BufferedImage resizeImage(BufferedImage image, int width, int height)
    {
        // 不压缩
        if (width <= 0 && height <= 0) { return deepCopy(image); }
        // 长宽与原来的相等
        if (image.getWidth() == width && image.getHeight() == height) { return deepCopy(image); }
        BufferedImage resize = Scalr.resize(image, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, width, height);
        Graphics graphics = resize.getGraphics();
        graphics.drawImage(resize, 0, 0, resize.getWidth(), resize.getHeight(), Color.WHITE, null);
        graphics.dispose();
        return resize;
    }
    
    private static BufferedImage deepCopy(BufferedImage bi)
    {
        ColorModel cm = bi.getColorModel();
        boolean alphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, alphaPremultiplied, null);
    }
    
    /**
     * 图片打水印
     * @param image
     * @param watermark
     * @return
     */
    public static BufferedImage drawWatermark(BufferedImage image, Watermark watermark)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (StringUtils.isNotBlank(watermark.getText()))
        { // 文字水印
            int fontSize = 20;
            if (height >= width)
            {
                fontSize = width / 20;
            }
            else
            {
                fontSize = height / 20;
            }
            Font font = new Font(watermark.getFontType(), Font.PLAIN, fontSize);
            graphics2D.setFont(font);
            graphics2D.setColor(Color.RED);
            // 消除java.awt.Font字体的锯齿
            int fontX = width - getWatermarkLength(watermark.getText(), graphics2D) - 5;
            int fontY = height - getWatermarkLength(watermark.getText().substring(0, 1), graphics2D) - 5;
            graphics2D.drawString(watermark.getText(), fontX, fontY);
        }
        else if (watermark.getWatermarkImg() != null)
        { // 图片水印
            BufferedImage watermarkImg = watermark.getWatermarkImg();
            int waterWidth = watermarkImg.getWidth();
            int waterHeight = watermarkImg.getHeight();
            // 如果水印图片高或者宽大于目标图片是做的处理,使其水印宽或高等于目标图片的宽高，并且等比例缩放
            if (waterWidth > width)
            {
                waterWidth = width;
                waterHeight = (int) ((double) waterWidth / width * height);
            }
            if (waterHeight > width)
            {
                waterHeight = height;
                waterWidth = (int) ((double) waterHeight / height * width);
            }
            int x = width - waterWidth;
            int y = height - waterHeight;
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.7f));
            graphics2D.drawImage(watermarkImg, x, y, waterWidth, waterHeight, null);
        }
        graphics2D.dispose();
        return image;
    }
    
    /**
     * 图片打水印
     * @param file
     * @param watermark
     * @throws IOException
     * @author 夏铭
     */
    public static void drawWatermark(File file, Watermark watermark) throws IOException
    {
        String extension = FilenameUtils.getExtension(file.getName());
        if ("gif".equalsIgnoreCase(extension))
        {
            drawAnimatedImageFile(file, watermark);
        }
        BufferedImage image = ImageIO.read(file);
        BufferedImage bufferedImage = drawWatermark(image, watermark);
        ImageIO.write(bufferedImage, extension, file);
    }
    
    private static void drawAnimatedImageFile(File file, Watermark watermark) throws IOException
    {
        GifDecoder gifDecoder = new GifDecoder();
        InputStream inputStream = null;
        try
        {
            inputStream = FileUtils.openInputStream(file);
            int status = gifDecoder.read(new FileInputStream(file));
            if (status != GifDecoder.STATUS_OK) { return; }
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
        }
        OutputStream outputStream = null;
        try
        {
            outputStream = FileUtils.openOutputStream(file);
            AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
            gifEncoder.start(outputStream);
            gifEncoder.setRepeat(0);
            for (int i = 0; i < gifDecoder.getFrameCount(); i++)
            {
                BufferedImage frame = gifDecoder.getFrame(i);
                BufferedImage rescaled = drawWatermark(frame, watermark);
                //
                int delay = gifDecoder.getDelay(i);
                //
                gifEncoder.setDelay(delay);
                gifEncoder.addFrame(rescaled);
            }
            gifEncoder.finish();
        }
        finally
        {
            IOUtils.closeQuietly(outputStream);
        }
    }
    
    /**
     * 图片打水印
     * @param path
     * @param watermark
     * @throws IOException
     * @return
     * @author 夏铭
     */
    public static void drawWatermark(String path, Watermark watermark) throws IOException
    {
        drawWatermark(new File(path), watermark);
    }
    
    /**
     * 缩放图片文件
     * @param filePath 文件路径
     * @param width 缩放后的长度
     * @param height 缩放后的高度
     * @return
     * @author 夏铭
     */
    public static File resizeImageFile(String filePath, int width, int height) throws IOException
    {
        File file = new File(filePath);
        return resizeImageFile(file, width, height);
    }
    
    /**
     * 缩放图片文件
     * @param file 要缩放的图片文件
     * @param width 缩放后的长度
     * @param height 缩放后的高度
     * @return
     * @author 夏铭
     */
    public static File resizeImageFile(File file, int width, int height) throws IOException
    {
        String extension = FilenameUtils.getExtension(file.getName());
        if ("gif".equalsIgnoreCase(extension)) { return resizeAnimatedImageFile(file, width, height); }
        return resizeStaticImageFile(file, width, height);
    }
    
    private static File resizeStaticImageFile(File file, int width, int height) throws IOException
    {
        String extension = FilenameUtils.getExtension(file.getName());
        String finalName = getFinalFileName(file, extension, width, height);
        OutputStream outputStream = null;
        try
        {
            BufferedImage read = ImageIO.read(file);
            BufferedImage resizeImage = resizeImage(read, width, height);
            File outputFile = new File(finalName);
            outputStream = new FileOutputStream(outputFile);
            ImageIO.write(resizeImage, extension, outputStream);
            return outputFile;
        }
        finally
        {
            IOUtils.closeQuietly(outputStream);
        }
    }
    
    /**
     * 图片等比压缩
     * @param filelist 图片路径
     * @param ext 扩展名
     * @param n 图片名
     * @param w 目标宽
     * @param h 目标高
     * return File 目标File
     */
    public static File resizeImage(File file, int width, int height)
    {
        File outputFile = null;
        FileOutputStream outputStream = null;
        try
        {
            String extension = FilenameUtils.getExtension(file.getName());
            Image src = ImageIO.read(file); // 构造Image对象
            int[] wh = resizeCalculate(src, width, height);
            BufferedImage tag = new BufferedImage(wh[0], wh[1], BufferedImage.TYPE_INT_RGB);
            Image dest = src.getScaledInstance(wh[0], wh[1], Image.SCALE_SMOOTH);
            tag.getGraphics().drawImage(dest, 0, 0, null);
            String finalName = getFinalFileName(file, extension, width, height);
            outputFile = new File(finalName);
            outputStream = new FileOutputStream(outputFile); // 输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            jep.setQuality(IMAGE_QUALITY, true);
            encoder.encode(tag, jep);
        }
        catch (IOException ex)
        {
            LoggerUtils.logError(logger, ex.getMessage());
        }
        finally
        {
            IOUtils.closeQuietly(outputStream);
        }
        return outputFile;
    }
    
    public static BufferedImage toBufferedImage(File file) throws IOException
    {
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
        if (image instanceof BufferedImage) { return (BufferedImage) image; }
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        bimage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bimage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }
    
    /**
     * 缩放gif格式图片
     * @param file
     * @param width
     * @param height
     * @return
     * @throws IOException
     * @author 夏铭
     */
    private static File resizeAnimatedImageFile(File file, int width, int height) throws IOException
    {
        GifDecoder gifDecoder = new GifDecoder();
        InputStream inputStream = null;
        try
        {
            inputStream = FileUtils.openInputStream(file);
            int status = gifDecoder.read(new FileInputStream(file));
            if (status != GifDecoder.STATUS_OK) { return null; }
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
        }
        String finalName = getFinalFileName(file, "gif", width, height);
        OutputStream outputStream = null;
        File outputFile = new File(finalName);
        try
        {
            outputStream = FileUtils.openOutputStream(outputFile);
            AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
            gifEncoder.start(outputStream);
            gifEncoder.setRepeat(0);
            for (int i = 0; i < gifDecoder.getFrameCount(); i++)
            {
                BufferedImage frame = gifDecoder.getFrame(i);
                BufferedImage rescaled = resizeImage(frame, width, height);
                //
                int delay = gifDecoder.getDelay(i);
                //
                gifEncoder.setDelay(delay);
                gifEncoder.addFrame(rescaled);
            }
            gifEncoder.finish();
        }
        finally
        {
            IOUtils.closeQuietly(outputStream);
        }
        return outputFile;
    }
    
    /**
     * 获取水印文字总长度
     * @param string
     * @param g
     * @return
     * @author 夏铭
     */
    private static int getWatermarkLength(String string, Graphics2D g)
    {
        return g.getFontMetrics(g.getFont()).charsWidth(string.toCharArray(), 0, string.length());
    }
    
    private static String getFinalFileName(File file, String extension, int width, int height)
    {
        if (width == 0 && height == 0) { return file.getName(); }
        return new StringBuilder(file.getAbsolutePath()).append("_").append(width).append("x").append(height).append(".").append(extension).toString();
    }
    
    /**
     * 
     * @param old_w 原图宽
     * @param old_h 原图高
     * @param w 新图宽
     * @param h 新图高
     * @return int[] 新图宽、新图高
     */
    private static int[] resizeCalculate(Image src, int w, int h)
    {
        int[] wh = new int[2];
        if (null != src)
        {
            int scale = 3;// 精确到小数点第几位
            int new_w = 0;// 新的宽度
            int new_h = 0;// 新的高度
            int old_w = src.getWidth(null); // 源图宽
            int old_h = src.getHeight(null);// 源图高
            BigDecimal oldWidth = new BigDecimal(old_w);
            BigDecimal oldHeight = new BigDecimal(old_h);
            BigDecimal width = new BigDecimal(w);
            BigDecimal height = new BigDecimal(h);
            if (oldWidth.doubleValue() / oldHeight.doubleValue() == width.doubleValue() / height.doubleValue())
            {
                // 如果原宽高比跟新宽高比相等，则直接用新的宽高比，避免少一个像素的问题
                wh[0] = w;
                wh[1] = h;
            }
            else
            {
                BigDecimal bigWidth = oldWidth.divide(width, scale, BigDecimal.ROUND_HALF_EVEN);
                BigDecimal bigHeight = oldHeight.divide(height, scale, BigDecimal.ROUND_HALF_EVEN);
                double dw = bigWidth.doubleValue();
                double dh = bigHeight.doubleValue();
                if (dw >= dh)
                {
                    new_w = (int) (old_w / dw);
                    new_h = (int) (old_h / dw);
                }
                else if (dw < dh)
                {
                    new_w = (int) (old_w / dh);
                    new_h = (int) (old_h / dh);
                }
                wh[0] = new_w;
                wh[1] = new_h;
            }
            LoggerUtils.logInfo(logger, "原图宽：{}，原图高：{}，目标宽：{}，目标高：{}，新图宽：{}，新图高：{}", old_w, old_h, w, h, new_w, new_h);
        }
        return wh;
    }
    
    /*
     * 上面的代码是直接在家里写的，不小心和原先的加水印方法重复了，为了节省时间注释原先的加水印代码，刘总先原谅我吧
     */
    // /**
    // * 把水印印刷到图片上
    // * @param pressImg 水印文件
    // * @param targetImg 目标文件
    // * @param x x坐标
    // * @param y y坐标
    // */
    // public static void pressImage(String pressImg, String targetImg, int x,
    // int y)
    // {
    // try
    // {
    // // 目标文件
    // File _file = new File(targetImg);
    // Image src = ImageIO.read(_file);
    // int wideth = src.getWidth(null);
    // int height = src.getHeight(null);
    // BufferedImage image = new BufferedImage(wideth, height,
    // BufferedImage.TYPE_INT_RGB);
    // Graphics g = image.createGraphics();
    // g.drawImage(src, 0, 0, wideth, height, null);
    // // 水印文件
    // File _filebiao = new File(pressImg);
    // Image src_biao = ImageIO.read(_filebiao);
    // int wideth_biao = src_biao.getWidth(null);
    // int height_biao = src_biao.getHeight(null);
    // g.drawImage(src_biao, (wideth - wideth_biao) / 2,
    // (height - height_biao) / 2, wideth_biao, height_biao, null);
    // // 水印文件结束
    // g.dispose();
    // FileOutputStream out = new FileOutputStream(targetImg);
    // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
    // encoder.encode(image);
    // out.close();
    // }
    // catch (Exception e)
    // {
    // logger.error(e);
    // }
    // }
    //
    // /**
    // * 添加水印文件
    // * @param pressText 要添加的文字
    // * @param targetImg 要添加水印文字的图片
    // * @param fontName 字体名
    // * @param fontStyle 字体样式
    // * @param color 颜色
    // * @param fontSize 文字大小
    // * @param x x坐标
    // * @param y y坐标
    // */
    // public static void pressText(String pressText, String targetImg,
    // String fontName, int fontStyle, int color, int fontSize, int x,
    // int y)
    // {
    // try
    // {
    // File _file = new File(targetImg);
    // Image src = ImageIO.read(_file);
    // int wideth = src.getWidth(null);
    // int height = src.getHeight(null);
    // BufferedImage image = new BufferedImage(wideth, height,
    // BufferedImage.TYPE_INT_RGB);
    // Graphics g = image.createGraphics();
    // g.drawImage(src, 0, 0, wideth, height, null);
    // // String s="www.qhd.com.cn";
    // g.setColor(Color.RED);
    // g.setFont(new Font(fontName, fontStyle, fontSize));
    // g.drawString(pressText, wideth - fontSize - x, height - fontSize
    // / 2 - y);
    // g.dispose();
    // FileOutputStream out = new FileOutputStream(targetImg);
    // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
    // encoder.encode(image);
    // out.close();
    // }
    // catch (Exception e)
    // {
    // logger.error(e);
    // }
    // }
    //
    // /**
    // * Resizes an image
    // *
    // * @param imgName The image name to resize. Must be the complet path to
    // the file
    // * @param maxWidth The image's max width
    // * @param maxHeight The image's max height
    // * @return A resized <code>BufferedImage
    // * @throws IOException If the file is not found
    // */
    // public static BufferedImage resizeImage(String imgName, int type,
    // int maxWidth, int maxHeight) throws IOException
    // {
    // return resizeImage(ImageIO.read(new File(imgName)), type, maxWidth,
    // maxHeight);
    // }
    //
    // /**
    // * Resizes an image.
    // *
    // * @param image The image to resize
    // * @param maxWidth The image's max width
    // * @param maxHeight The image's max height
    // * @return A resized <code>BufferedImage
    // */
    // public static BufferedImage resizeImage(Image image, int type,
    // int maxWidth, int maxHeight)
    // {
    // // float zoom = 1.0F;
    // Dimension largestDimension = new Dimension(maxWidth, maxHeight);
    // // Original size
    // int imageWidth = image.getWidth(null);
    // int imageHeight = image.getHeight(null);
    // float aspectRation = (float) imageWidth / imageHeight;
    // if (imageWidth > maxWidth || imageHeight > maxHeight)
    // {
    // // int scaledW = Math.round(imageWidth * zoom);
    // // int scaledH = Math.round(imageHeight * zoom);
    // // Dimension preferedSize = new Dimension(scaledW, scaledH);
    // if ((float) largestDimension.width / largestDimension.height >
    // aspectRation)
    // {
    // largestDimension.width = (int) Math
    // .ceil(largestDimension.height * aspectRation);
    // }
    // else
    // {
    // largestDimension.height = (int) Math
    // .ceil((float) largestDimension.width / aspectRation);
    // }
    // imageWidth = largestDimension.width;
    // imageHeight = largestDimension.height;
    // }
    // return createBufferedImage(image, type, imageWidth, imageHeight);
    // }
    //
    // /**
    // * Saves an image to the disk.
    // *
    // * @param image The image to save
    // * @param toFileName The filename to use
    // * @param type The image type. Use <code>ImageUtils.IMAGE_JPEG to save as
    // JPEG
    // * images, or <code>ImageUtils.IMAGE_PNG to save as PNG.
    // * @return <code>false if no appropriate writer is found
    // * @throws IOException
    // */
    // public static boolean saveImage(BufferedImage image, String toFileName,
    // int type) throws IOException
    // {
    // return ImageIO.write(image, type == IMAGE_JPEG ? "jpg" : "png",
    // new File(toFileName));
    // }
    //
    // /**
    // * Compress and save an image to the disk.
    // * Currently this method only supports JPEG images.
    // *
    // * @param image The image to save
    // * @param toFileName The filename to use
    // * @param type The image type. Use <code>ImageUtils.IMAGE_JPEG to save as
    // JPEG
    // * images, or <code>ImageUtils.IMAGE_PNG to save as PNG.
    // * @throws IOException
    // */
    // public static void saveCompressedImage(BufferedImage image,
    // String toFileName, int type) throws IOException
    // {
    // if (type == IMAGE_PNG)
    // {
    // throw new UnsupportedOperationException(
    // "PNG compression not implemented");
    // }
    // ImageWriter writer = null;
    // Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
    // writer = (ImageWriter) iter.next();
    // ImageOutputStream ios = ImageIO.createImageOutputStream(new File(
    // toFileName));
    // writer.setOutput(ios);
    // ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());
    // iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    // iwparam.setCompressionQuality(0.7F);
    // writer.write(null, new IIOImage(image, null, null), iwparam);
    // ios.flush();
    // writer.dispose();
    // ios.close();
    // }
    //
    // /**
    // * Creates a <code>BufferedImage from an Image.
    // *
    // * @param image The image to convert
    // * @param w The desired image width
    // * @param h The desired image height
    // * @return The converted image
    // */
    // public static BufferedImage createBufferedImage(Image image, int type,
    // int w, int h)
    // {
    // if (type == ImageUtils.IMAGE_PNG && hasAlpha(image))
    // {
    // type = BufferedImage.TYPE_INT_ARGB;
    // }
    // else
    // {
    // type = BufferedImage.TYPE_INT_RGB;
    // }
    // BufferedImage bi = new BufferedImage(w, h, type);
    // Graphics g = bi.createGraphics();
    // g.drawImage(image, 0, 0, w, h, null);
    // g.dispose();
    // return bi;
    // }
    //
    /**
    * Determines if the image has transparent pixels.
    *
    * @param image The image to check for transparent pixel.s
    * @return <code>true of false, according to the result
    * @throws InterruptedException
    */
    public static boolean hasAlpha(Image image)
    {
        try
        {
            PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
            pg.grabPixels();
            return pg.getColorModel().hasAlpha();
        }
        catch (InterruptedException e)
        {
            return false;
        }
    }
}
