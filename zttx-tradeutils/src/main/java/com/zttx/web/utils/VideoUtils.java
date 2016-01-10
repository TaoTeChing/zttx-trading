/*
 * @(#)VideoUtils.java 2014-3-20 上午9:31:40
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.zttx.sdk.exception.FileOperateException;
import com.zttx.web.consts.CommonConst;

/**
 * <p>File：VideoUtils.java</p>
 * <p>Title: 转换视频和flash格式的工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-20 上午9:31:40</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public class VideoUtils
{
    // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
    // 暂不支持的格式
    private static String[]     NO_HOLD       = {"rm", "rmvb"};
    
    // linux系统
    private static int          LINUX_SYSTEM  = 2;
    
    private static String       for_LINUX     = File.separator + "WEB-INF" + File.separator + "ffmpeg-2.1.1" + File.separator + "ffmpeg";
    
    // window 操作系统
    private static int          WINDOW_SYSTEM = 1;
    
    private static String       for_WINDOWS   = for_LINUX + ".exe";
    
    private static final Logger logger        = Logger.getLogger(VideoUtils.class);
    
    // 项目路径
    private String              projectPath;
    
    public VideoUtils(String projectPath)
    {
        this.projectPath = projectPath;
    }
    
    /**
     * 视频转换。跨服务器平台的
     * @param inputFile
     * @param outputFile
     * @return boolean
     * @throws FileOperateException
     */
    public boolean convert(String inputFile, String outputFile) throws FileOperateException
    {
        if (!checkFile(inputFile)) { return false; }
        boolean flag = false;
        int status = checkContentType(inputFile);
        if (status == -1)
        {
            throw new FileOperateException(CommonConst.VIDEO_FORMAT_ERROR);
        }
        else if (status == 1)
        {
            flag = true;
        }
        else
        {
            switch (getSystem())
            {
                case 1: // window系统
                    flag = processWindows(inputFile, outputFile);
                    break;
                case 2: // Linux系统
                    flag = processLinux(inputFile, outputFile);
                    break;
                default:
                    logger.error("视频上传功能暂不支持该服务器操作系统！");
                    throw new RuntimeException("操作系统未知");
            }
        }
        return flag;
    }
    
    /**
     * 检查视频类型
     * 
     * @param inputFile
     * @return ffmpeg 能解析返回0，不能解析返回1
     */
    private int checkContentType(String inputFile)
    {
        String type = inputFile.substring(inputFile.lastIndexOf(".") + 1, inputFile.length()).toLowerCase();
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        if (type.equals("avi"))
        {
            return 0;
        }
        else if (type.equals("mpg"))
        {
            return 0;
        }
        else if (type.equals("wmv"))
        {
            return 0;
        }
        else if (type.equals("3gp"))
        {
            return 0;
        }
        else if (type.equals("mov"))
        {
            return 0;
        }
        else if (type.equals("mp4"))
        {
            return 0;
        }
        else if (type.equals("asf"))
        {
            return 0;
        }
        else if (type.equals("asx"))
        {
            return 0;
        }
        else if (type.equals("flv"))
        {
            return 1;
        }
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
        else if (type.equals("wmv9"))
        {
            return -1;
        }
        else if (type.equals("rm"))
        {
            return -1;
        }
        else if (type.equals("rmvb")) { return -1; }
        return -1;
    }
    
    /**
     * 获取当前系统
     * @return
     */
    private int getSystem()
    {
        String systemName = System.getProperties().getProperty("os.name");
        if (systemName.toLowerCase().startsWith("win"))
        {
            return WINDOW_SYSTEM; // 1
        }
        else if (systemName.equals("Linux")) { return LINUX_SYSTEM; // 2
        }
        return -1;
    }
    
    // 判断文件
    private boolean checkFile(String inputFile) throws FileOperateException
    {
        File file = new File(inputFile);
        String type = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (Arrays.binarySearch(NO_HOLD, type) >= 0) { throw new FileOperateException(CommonConst.VIDEO_FORMAT_ERROR); }
        return file.isFile();
    }
    
    /**
     * 不支持rm, rmvb等格式视频
     * @param inputFile
     * @param outputFile
     * @return
     * @throws FileOperateException 
     */
    private boolean processLinux(String inputFile, String outputFile) throws FileOperateException
    {
        // 这里我不做其他类型的转吗
        File file = new File(outputFile);
        if (file.exists())
        {
            return true;
        }
        else
        {
            // 低精度转换
            /*
             * avi --> flv String avitoflv = "ffmpeg -i " + infile + " -ar 22050 -ab 56 -f flv -y -s 320x240 " + outfile; flv --> 3gp String flvto3gp = ff+"-i "+ infile +
             * " -ar 8000 -ac 1 -acodec amr_nb -vcodec h263 -s 176x144 -r 12 -b 30 -ab 12 " + outfile; avi --> 3gp String avito3gp = ff+"-i " + infile +
             * " -ar 8000 -ac 1 -acodec amr_nb -vcodec h263 -s 176x144 -r 12 -b 30 -ab 12 " + outfile; // avi -> jpg String avitojpg = ff+"-i " + infile+
             * " -y -f image2 -ss 00:00:10 -t 00:00:01 -s 350x240 " + outfile;
             */
            List<String> commend = new ArrayList<String>();
            commend.add(projectPath + for_LINUX);
            commend.add("-i");
            commend.add(inputFile);
            commend.add("-ar");
            commend.add("22050");
            commend.add("-ab");
            commend.add("56");
            commend.add("-f");
            commend.add("flv");
            commend.add("-y");
            commend.add("-s");
            commend.add("320*240");
            commend.add(outputFile);
            runCmd(commend);
            return true;
        }
    }
    
    // 运行命令
    @SuppressWarnings("unused")
    public void runCmd(List<String> command) throws FileOperateException
    {
        BufferedReader br = null;
        try
        {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(command.toArray(new String[]{}));
            br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            while ((line = br.readLine()) != null)
            {
            }
        }
        catch (Exception e)
        {
            logger.error("Linux for ffmpeg 视频转换程序执行失败！");
            throw new FileOperateException(CommonConst.VIDEO_PROCEDU_ERROR);
        }
        finally
        {
            closeBuffer(br);
        }
    }
    
    /**
     * ffmepg: 能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
     * 
     * @param inputFile
     * @param outputFile
     * @return
     * @throws FileOperateException 
     */
    private boolean processWindows(String inputFile, String outputFile) throws FileOperateException
    {
        File file = new File(outputFile);
        if (file.exists())
        {
            return true;
        }
        else
        {
            List<String> commend = new java.util.ArrayList<String>();
            // 低精度
            commend.add(projectPath + for_WINDOWS);
            commend.add("-i");
            commend.add(inputFile);
            commend.add("-ab");
            commend.add("128");
            commend.add("-acodec");
            commend.add("libmp3lame");
            commend.add("-ac");
            commend.add("1");
            commend.add("-ar");
            commend.add("22050");
            commend.add("-r");
            commend.add("29.97");
            // 清晰度 -qscale 4 为最好但文件大, -qscale 6就可以了
            commend.add("-qscale");
            commend.add("6");
            commend.add("-y");
            commend.add(outputFile);
            execute(commend);
            return true;
        }
    }
    
    /**
     * 执行转换程序
     * @param commend
     * @throws FileOperateException 
     */
    private void execute(List<String> commend) throws FileOperateException
    {
        BufferedReader br = null;
        try
        {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.redirectErrorStream(true);
            Process pro = builder.start();
            br = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
            pro.waitFor();
        }
        catch (Exception e)
        {
            logger.error("windows for ffmpeg 视频转换程序执行失败！");
            throw new FileOperateException(CommonConst.VIDEO_PROCEDU_ERROR);
        }
        finally
        {
            closeBuffer(br);
        }
    }
    
    /**
     * 关闭刘操作
     * @param br
     */
    private void closeBuffer(BufferedReader br)
    {
        try
        {
            if (br != null)
            {
                br.close();
            }
        }
        catch (IOException e)
        {
            logger.error("BufferedReader close is error!");
        }
    }
    /***********************************************************下面的代码是比较全*********************************************************************/
    /*
     * // mencoder.exe所放的路径 private String mencoder_home ; private static String mencoder = "videoUtils" + File.separator + "MPlayer" + File.separator + "mencoder.exe";
     * private static String ffmpeg = "videoUtils" + File.separator + "ffmpeg" + File.separator + "bin" + File.separator + "ffmpeg.exe"; // ffmpeg.exe所放的路径 private String
     * ffmpeg_home ; private static final Logger logger = Logger.getLogger(VideoUtils.class); //// 存放rm,rmvb等无法使用ffmpeg直接转换为flv文件先转成的avi文件 private String tempFile_home;
     * public VideoUtils(String tempFilePath, String ProjectPath) { this.tempFile_home = tempFilePath; this.mencoder_home = ProjectPath + File.separator + mencoder;
     * this.ffmpeg_home = ProjectPath + File.separator + ffmpeg; }
     *//**
       * 功能函数
       * 
       * @param inputFile
       *            待处理视频，需带路径
       * @param outputFile
       *            处理后视频，需带路径
       * @return
       * @throws FileOperateException 
       */
    /*
     * public boolean convert(String inputFile, String outputFile) throws FileOperateException { if (!checkfile(inputFile)) { return false; } if (process(inputFile,
     * outputFile)) { return true; } return false; } // 检查文件是否存在 private boolean checkfile(String path) { File file = new File(path); if (!file.isFile()) { return false; }
     * return true; }
     *//**
       * 转换过程 ：先检查文件类型，在决定调用 processFlv还是processAVI
       * 
       * @param inputFile
       * @param outputFile
       * @return
       * @throws FileOperateException 
       */
    /*
     * private boolean process(String inputFile, String outputFile) throws FileOperateException { int type = checkContentType(inputFile); boolean status = false; if (type
     * == 0) { status = processFLV(inputFile, outputFile);// 直接将文件转为flv文件 } else if (type == 1) { String avifilepath = processAVI(type, inputFile); if (avifilepath == null)
     * return false;// avi文件没有得到 status = processFLV(avifilepath, outputFile);// 将avi转为flv } return status; }
     *//**
       * 检查视频类型
       * 
       * @param inputFile
       * @return ffmpeg 能解析返回0，不能解析返回1
       */
    /*
     * private int checkContentType(String inputFile) { String type = inputFile.substring(inputFile.lastIndexOf(".") + 1, inputFile.length()).toLowerCase(); //
     * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等） if (type.equals("avi")) { return 0; } else if (type.equals("mpg")) { return 0; } else if (type.equals("wmv")) {
     * return 0; } else if (type.equals("3gp")) { return 0; } else if (type.equals("mov")) { return 0; } else if (type.equals("mp4")) { return 0; } else if
     * (type.equals("asf")) { return 0; } else if (type.equals("asx")) { return 0; } else if (type.equals("flv")) { return 0; } // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), //
     * 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式. else if (type.equals("wmv9")) { return 0; } else if (type.equals("rm")) { return 0; } else if (type.equals("rmvb")) { return
     * 0; } return 9; }
     *//**
       * ffmepg: 能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
       * 
       * @param inputFile
       * @param outputFile
       * @return
       * @throws FileOperateException 
       */
    /*
     * private boolean processFLV(String inputFile, String outputFile) throws FileOperateException { if (!checkfile(inputFile)) { return false; } File file = new
     * File(outputFile); if (file.exists()) { return true; } else { List<String> commend = new java.util.ArrayList<String>(); // 低精度 commend.add(ffmpeg_home);
     * commend.add("-i"); commend.add(inputFile); commend.add("-ab"); commend.add("128"); commend.add("-acodec"); commend.add("libmp3lame"); commend.add("-ac");
     * commend.add("1"); commend.add("-ar"); commend.add("22050"); commend.add("-r"); commend.add("29.97"); // 清晰度 -qscale 4 为最好但文件大, -qscale 6就可以了 commend.add("-qscale");
     * commend.add("4"); commend.add("-y"); commend.add(outputFile); execute(commend); return true; } }
     *//**
       * 生成视频截图
       * @param imageSavePath  图片保存路径
       * @param screenSize  截图大小 如：640*480
       * @param inputFile 源文件
       * @throws FileOperateException 
       */
    /*
     * public void makeScreeCut(String imageSavePath, String screenSize, String inputFile) throws FileOperateException{ List<String> commend = new
     * java.util.ArrayList<String>(); commend.clear(); commend.add(ffmpeg_home); commend.add("-i"); commend.add(inputFile); //视频源地址 commend.add("-y"); commend.add("-f");
     * commend.add("image2"); commend.add("-ss"); commend.add("8"); commend.add("-t"); commend.add("0.001"); commend.add("-s"); commend.add(screenSize);
     * commend.add(imageSavePath); execute(commend); }
     *//**
       * 执行转换程序
       * @param commend
       * @throws FileOperateException 
       */
    /*
     * public void execute(List<String> commend ) throws FileOperateException{ BufferedReader br = null ; try { ProcessBuilder builder = new ProcessBuilder();
     * builder.command(commend); builder.redirectErrorStream(true); Process pro = builder.start(); br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
     * String line ; while ( (line = br.readLine()) != null) { } pro.waitFor(); } catch (Exception e) { throw new FileOperateException(CommonConst.MAKE_ERROR); } finally{
     * try { if (br != null) { br.close(); br = null; } } catch (IOException e) { logger.error("BufferedReader close is error!"); } } }
     *//**
       * Mencoder:
       * 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
       * 
       * @param type
       * @param inputFile
       * @return
       */
    /*
     * private String processAVI(int type, String inputFile) { File file = new File(tempFile_home); if (file.exists()) { return tempFile_home; } List<String> commend = new
     * java.util.ArrayList<String>(); commend.add(mencoder_home); commend.add(inputFile); commend.add("-oac"); commend.add("mp3lame"); commend.add("-lameopts");
     * commend.add("preset=64"); commend.add("-ovc"); commend.add("xvid"); commend.add("-xvidencopts"); commend.add("bitrate=600"); commend.add("-of"); commend.add("avi");
     * commend.add("-o"); commend.add(tempFile_home); try { ProcessBuilder builder = new ProcessBuilder(); builder.command(commend); Process p = builder.start();
     *//**
       * 清空Mencoder进程 的输出流和错误流 因为有些本机平台仅针对标准输入和输出流提供有限的缓冲区大小，
       * 如果读写子进程的输出流或输入流迅速出现失败，则可能导致子进程阻塞，甚至产生死锁。
       */
    /*
     * final InputStream is1 = p.getInputStream(); final InputStream is2 = p.getErrorStream(); new Thread() { public void run() { BufferedReader br = new BufferedReader(
     * new InputStreamReader(is1)); try { String lineB = null; while ((lineB = br.readLine()) != null) { if (lineB != null) logger.info(lineB); } } catch (IOException e) {
     * logger.error(e.getMessage()); } } }.start(); new Thread() { public void run() { BufferedReader br2 = new BufferedReader( new InputStreamReader(is2)); try { String
     * lineC = null; while ((lineC = br2.readLine()) != null) { if (lineC != null) logger.info(lineC); } } catch (IOException e) { logger.error(e.getMessage());
     * e.printStackTrace(); } } }.start(); // 等Mencoder进程转换结束，再调用ffmpeg进程 p.waitFor(); return tempFile_home; } catch (Exception e) { logger.error(e.getMessage()); return
     * null; } }
     */
}
