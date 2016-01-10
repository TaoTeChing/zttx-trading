package com.zttx.web.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zttx.sdk.exception.BusinessException;

/**
 * HTTPCLIENT工具类，提供GET,POST方法
 * <p>File：HttpClientTool.java</p>
 * <p>Title: HttpClientTool</p>
 * <p>Description:HttpClientTool</p>
 * <p>Copyright: Copyright (c) Aug 15, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public class HttpClientTool
{
    private static String DEFAULT_CHARSET = "UTF-8";
    private static String DEFAULT_MIMETYPE = "text/plain";

    private static Logger log = LoggerFactory.getLogger(HttpClientTool.class);
    
    private RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(20000).build();

    /**
     * 获取Client
     * @return
     */
    public HttpClient getClient()
    {
        CloseableHttpClient client = HttpClients.createDefault();
        return client;
    }

    /**
     * GET提交
     * @param uri 请求地址
     * @return HttpResponse
     */
    public HttpResponse doGet(String uri)
    {
        HttpGet getMethod = new HttpGet(uri);
        return exctueRequest(getMethod);
    }

    /**
     * GET提交
     * @param uri 请求地址
     * @return String
     */
    public String doGetStr(String uri, String charset)
    {
        HttpGet getMethod = new HttpGet(uri);
        return exctueRequestStr(getMethod, charset);
    }

    /**
     * GET提交
     * @param uri 请求地址
     * @return String
     */
    public String doGetStr(String uri)
    {
        return doGetStr(uri, null);
    }

    /**
     * POST提交
     * @param uri 提交地址
     * @param entity 提交参数对象
     * @return HttpResponse 
     */
    public HttpResponse doPost(String uri, HttpEntity entity)
    {
        HttpPost postMethod = new HttpPost(uri);
        postMethod.setEntity(entity);
        return exctueRequest(postMethod);
    }

    /**
     * POST提交
     * @param uri 提交地址
     * @param entity 提交参数对象
     * @return String 
     */
    public String doPostStr(String uri, HttpEntity entity)
    {
        return doPostStr(uri, entity, null);
    }

    /**
     * POST提交
     * @param uri 提交地址
     * @param entity 提交参数对象
     * @return String 
     */
    public String doPostStr(String uri, HttpEntity entity, String charset)
    {
        HttpPost postMethod = new HttpPost(uri);
        postMethod.setEntity(entity);
        return exctueRequestStr(postMethod, charset);
    }

    /**
     * POST提交
     * @param uri 提交地址
     * @param Map<String,String> 提交参数MAP
     * @return HttpResponse 
     */
    public HttpResponse doPost(String uri, Map<String, String> paramMap, String charset)
    {
        return this.doPost(uri, getEntity(paramMap, charset));
    }

    /**
     * 根据参数获取HTTPEntity
     * @param paramMap
     * @return
     */
    private HttpEntity getEntity(Map<String, String> paramMap, String charset)
    {
        // 参数对象
        UrlEncodedFormEntity entity = null;
        // 参数LIST
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        for (Iterator<Entry<String, String>> it = paramMap.entrySet()
                .iterator(); it.hasNext();)
        {
            Entry<String, String> entry = it.next();
            paramList.add(new BasicNameValuePair(entry.getKey(), entry
                    .getValue()));
        }
        try
        {
            if (StringUtils.isEmpty(charset))
            {
                charset = DEFAULT_CHARSET;
            }
            entity = new UrlEncodedFormEntity(paramList, charset);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * POST提交
     * @param uri 提交地址
     * @param Map<String,String> 提交参数MAP
     * @return HttpResponse 
     */
    public HttpResponse doPost(String uri, Map<String, String> paramMap)
    {
        return this.doPost(uri, paramMap, null);
    }

    /**
     * POST提交
     * @param uri 提交地址
     * @param Map<String,String> 提交参数MAP
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    public String doPostStr(String uri, Map<String, String> paramMap)
            throws ParseException, IOException
    {
        return doPostStr(uri, getEntity(paramMap, DEFAULT_CHARSET));
    }
    
    /**
     * POST提交
     * @param uri 提交地址
     * @param Map<String,String> 提交参数MAP
     * @param file
     * @param fileFieldName
     * @return
     * @throws IOException 
     * @throws ParseException 
     * @author chenjp
     */
    public String doPostStr(String uri, Map<String, String> paramMap,File file,String fileFieldName)
    {
        //新建 http post 请求
        HttpPost httpPost=new HttpPost(uri);
        httpPost.setHeader("enctype", "multipart/form-data");
        //http post 方式发送多段数据管理器
        MultipartEntityBuilder multipartEntityBuilder=MultipartEntityBuilder.create();
        //设置浏览器兼容模式
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //设置请求的编码格式
        multipartEntityBuilder.setCharset(Charset.forName(DEFAULT_CHARSET));
        
        //配置要 post 的数据
        ContentType contentType = ContentType.create(DEFAULT_MIMETYPE, DEFAULT_CHARSET);
        for (Iterator<Entry<String, String>> it = paramMap.entrySet().iterator(); it.hasNext();)
        {
            Entry<String, String> entry = it.next();
            multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(),contentType);
        }
        if(file!=null&&file.exists()&&file.isFile()){
            multipartEntityBuilder.addBinaryBody(fileFieldName, file);
        }
        //生成 http post 实体  
        httpPost.setEntity(multipartEntityBuilder.build());
        
        return exctueRequestStr(httpPost,DEFAULT_CHARSET);
    }

    /**
     * POST提交
     * @param uri 提交地址
     * @param Map<String,String> 提交参数MAP
     * @param file
     * @param fileFieldName
     * @return
     * @throws IOException 
     * @throws ParseException 
     * @author chenjp
     */
    public String doPostStr(String uri, Map<String, String> paramMap,byte[] file,String fileFieldName) throws BusinessException
    {
        //新建 http post 请求
        HttpPost httpPost=new HttpPost(uri);
        httpPost.setHeader("enctype", "multipart/form-data");
        //http post 方式发送多段数据管理器
        MultipartEntityBuilder multipartEntityBuilder=MultipartEntityBuilder.create();
        //设置浏览器兼容模式
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //设置请求的编码格式
        multipartEntityBuilder.setCharset(Charset.forName(DEFAULT_CHARSET));
        
        //配置要 post 的数据
        ContentType contentType = ContentType.create(DEFAULT_MIMETYPE, DEFAULT_CHARSET);
        for (Iterator<Entry<String, String>> it = paramMap.entrySet().iterator(); it.hasNext();)
        {
            Entry<String, String> entry = it.next();
            multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(),contentType);
        }
        if(file!=null&&file.length>0){
		    multipartEntityBuilder.addBinaryBody(fileFieldName, file);
		}
        //生成 http post 实体  
        httpPost.setEntity(multipartEntityBuilder.build());
        return exctueRequestStr(httpPost,DEFAULT_CHARSET);
    }
    
    /**
     * POST提交
     * @param uri 提交地址
     * @param Map<String,String> 提交参数MAP
     * @param file
     * @param fileFieldName
     * @return
     * @throws IOException 
     * @throws ParseException 
     * @author chenjp
     */
    public String doPostStr(String uri, Map<String, String> paramMap,File[] files,String fileFieldName)
    {
        //新建 http post 请求
        HttpPost httpPost=new HttpPost(uri);
        httpPost.setHeader("enctype", "multipart/form-data");
        //http post 方式发送多段数据管理器
        MultipartEntityBuilder multipartEntityBuilder=MultipartEntityBuilder.create();
        //设置浏览器兼容模式
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //设置请求的编码格式
        multipartEntityBuilder.setCharset(Charset.forName(DEFAULT_CHARSET));
        
        //配置要 post 的数据
        ContentType contentType = ContentType.create(DEFAULT_MIMETYPE, DEFAULT_CHARSET);
        for (Iterator<Entry<String, String>> it = paramMap.entrySet().iterator(); it.hasNext();)
        {
            Entry<String, String> entry = it.next();
            multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(),contentType);
        }
        if(files!=null&&files.length>0){
            for(int i=0;i<files.length;i++){
                if(files[i]!=null&&files[i].exists()&&files[i].isFile()){
                    multipartEntityBuilder.addBinaryBody(fileFieldName, files[i]);
                }
            }
        }
        //生成 http post 实体  
        httpPost.setEntity(multipartEntityBuilder.build());
        
        return exctueRequestStr(httpPost,DEFAULT_CHARSET);
    }
    
    /**
     * POST提交
     * @param uri 提交地址
     * @param Map<String,String> 提交参数MAP
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    public String doPostStr(String uri, Map<String, String> paramMap,
            String charset) throws ParseException, IOException
    {
        return doPostStr(uri, getEntity(paramMap, charset), charset);
    }

    /**
     * 获取请求返回的响应HttpResponse
     * @param request
     * @return
     */
    private HttpResponse exctueRequest(HttpRequestBase request)
    {
        HttpResponse response = null;
        try
        {
        	request.setConfig(config);
            response = this.getClient().execute(request);
            // 获取状态
            int statuscode = response.getStatusLine().getStatusCode();
            // 重定向处理
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY)
                    || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statuscode == HttpStatus.SC_SEE_OTHER)
                    || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT))
            {
                Header redirectLocation = response.getFirstHeader("Location");
                String newUri = redirectLocation.getValue();
                if (StringUtils.isNotEmpty(newUri))
                {
                    request.setURI(new URI(newUri));
                    response = this.getClient().execute(request);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            releaseConnection(request);// 释放连接
        }
        return response;
    }
    
    /**
     * 获取请求返回的响应,返回响应字符串
     * @param request
     * @return
     */
    private String exctueRequestStr(HttpRequestBase request, String charset)
    {
        String responseText = "";
        try
        {
        	request.setConfig(config);
        	
        	HttpResponse response = this.getClient().execute(request);
            // 获取状态
            int statuscode = response.getStatusLine().getStatusCode();
            log.debug(statuscode+"========================================");
            // 重定向处理
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY)
                    || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statuscode == HttpStatus.SC_SEE_OTHER)
                    || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT))
            {
                Header redirectLocation = response.getFirstHeader("Location");
                String newUri = redirectLocation.getValue();
                if (StringUtils.isNotEmpty(newUri))
                {
                    request.setURI(new URI(newUri));
                    response = this.getClient().execute(request);
                }
            }
            if (StringUtils.isNotEmpty(charset))
            {
                responseText = EntityUtils.toString(response.getEntity(),
                        charset);
            }
            else
            {
                responseText = EntityUtils.toString(response.getEntity());
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        finally
        {
            releaseConnection(request);// 释放连接
        }
        return responseText;
    }

    /**
     * 释放请求连接
     * @param request
     */
    private void releaseConnection(HttpRequestBase request)
    {
        if (request != null)
        {
            request.releaseConnection();
        }
    }

    public static void main(String args[])
    {
        HttpClientTool tool = new HttpClientTool();
    }
}
