package com.zttx.web.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public class ImageProcess
{
    public static int cut(String srcImageFile, String descDir, int destWidth, int destHeight)
    {
        try
        {
            Image img;
            ImageFilter cropFilter;
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight();
            int srcHeight = bi.getWidth();
            if (srcWidth / 2 > destWidth && srcHeight / 2 > destHeight)
            {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                cropFilter = new CropImageFilter(srcWidth / 2 + destWidth / 2, srcHeight / 2 + destHeight / 2, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null);
                g.dispose();
                ImageIO.write(tag, "JPEG", new File(descDir + "_default" + ".jpg"));
            }
            else
            {
                System.out.println("默认剪切越界");
                return 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 1;
    }
    
    public static int cut(String srcImageFile, String descDir, int x, int y, int destWidth, int destHeight)
    {
        try
        {
            Image img;
            ImageFilter cropFilter;
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight();
            int srcHeight = bi.getWidth();
            if ((srcWidth - x) > destWidth && (srcHeight - y) > destHeight)
            {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null);
                g.dispose();
                ImageIO.write(tag, "JPEG", new File(descDir + "_self" + ".jpg"));
            }
            else
            {
                System.out.println(x + " " + y + "自定义剪切越界");
                return 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 1;
    }
    
    public static void scale(String srcImageFile, String descDir, int w, int h, boolean flag, Color col)
    {
        try
        {
            BufferedImage src = ImageIO.read(new File(srcImageFile));
            if (flag)
            {
                Image image = src.getScaledInstance(w, h, Image.SCALE_DEFAULT);
                BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(image, 0, 0, null);
                g.dispose();
                ImageIO.write(tag, "JPEG", new File(descDir));
            }
            else
            {
                if ((src.getWidth() / w == src.getHeight() / h) && (src.getHeight() % h == 0) && (src.getWidth() % w == 0))
                {
                    Image image = src.getScaledInstance(w, h, Image.SCALE_DEFAULT);
                    BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    Graphics g = tag.getGraphics();
                    g.drawImage(image, 0, 0, null);
                    g.dispose();
                    ImageIO.write(tag, "JPEG", new File(descDir));
                }
                else if ((src.getWidth() / w < src.getHeight() / h) && (src.getHeight() % h == 0))
                {
                    int m = src.getWidth() * h / src.getHeight();
                    int x = (w - m) / 2;
                    Image image = src.getScaledInstance(m, h, Image.SCALE_DEFAULT);
                    BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    Graphics g = tag.getGraphics();
                    g.setColor(col);
                    g.fillRect(0, 0, w, h);
                    g.drawImage(image, x, 0, null);
                    g.dispose();
                    ImageIO.write(tag, "JPEG", new File(descDir));
                    /*
                     * FileOutputStream out = new FileOutputStream(new File(result));
                     * JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                     * JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
                     * param.setQuality(0.5f, true);
                     * encoder.encode(tag,param);
                     * out.close();
                     */
                }
                else if ((src.getWidth() / w > src.getHeight() / h) && (src.getWidth() % w == 0))
                {
                    int n = src.getHeight() * w / src.getWidth();
                    int y = (h - n) / 2;
                    Image image = src.getScaledInstance(w, n, Image.SCALE_DEFAULT);
                    BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    Graphics g = tag.getGraphics();
                    g.setColor(col);
                    g.fillRect(0, 0, w, h);
                    g.drawImage(image, 0, y, null);
                    g.dispose();
                    ImageIO.write(tag, "JPEG", new File(descDir));
                }
                else
                {
                    Image image = src.getScaledInstance(w, h, Image.SCALE_DEFAULT);
                    BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    Graphics g = tag.getGraphics();
                    g.drawImage(image, 0, 0, null);
                    g.dispose();
                    ImageIO.write(tag, "JPEG", new File(descDir));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void waterMark(String imgPath, String markPath, int x, int y, int z, float alpha)
    {
        try
        {
            // 加载待处理图片文件
            Image img = ImageIO.read(new File(imgPath));
            BufferedImage image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(img, 0, 0, null);
            // 加载水印图片文件
            Image src_biao = ImageIO.read(new File(markPath));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g.drawImage(src_biao, x, y, src_biao.getWidth(null) / z, src_biao.getHeight(null) / z, null);
            g.dispose();
            // 保存处理后的文件
            FileOutputStream out = new FileOutputStream(imgPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 创建图片缩略图(等比缩放)
     * @param src 源图片文件完整路径
     * @param dist 目标图片文件完整路径
     * @param width 缩放的宽度
     * @param height 缩放的高度
     */
    public static void createThumbnail(String src, String dist, float width, float height)
    {
        try
        {
            File srcfile = new File(src);
            if (!srcfile.exists())
            {
                System.out.println("文件不存在");
                return;
            }
            BufferedImage image = ImageIO.read(srcfile);
            // 获得缩放的比例
            double ratio = 1.0;
            // 判断如果高、宽都不大于设定值，则不处理
            if (image.getHeight() > height || image.getWidth() > width)
            {
                if (image.getHeight() > image.getWidth())
                {
                    ratio = height / image.getHeight();
                }
                else
                {
                    ratio = width / image.getWidth();
                }
            }
            // 计算新的图面宽度和高度
            int newWidth = (int) (image.getWidth() * ratio);
            int newHeight = (int) (image.getHeight() * ratio);
            BufferedImage bfImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            FileOutputStream os = new FileOutputStream(dist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfImage);
            os.close();
            System.out.println("创建缩略图成功");
        }
        catch (Exception e)
        {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }
    }
    
    public static void main(String args[])
    {
        // cut("d:/11.jpg","d:/22.jpg",100,100);
        // cut("d:/11.jpg","d:/22.jpg",0,0,100,100);
        // scale("d:/11.jpg","d:/22.jpg",100,100,true,Color.white);
        // createThumbnail("d:/11.jpg","d:/22.jpg",100,100);
        // scale("e:/1.jpg", "e:/scale2.jpg",500,300,true,Color.white);
        // waterMark("e:/2.jpg", "e:/3.jpg", 50,60,4,0.6f);
    }
}
