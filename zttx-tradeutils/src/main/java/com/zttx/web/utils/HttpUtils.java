/*
 * @(#)HttpUtils.java 2014-1-8 下午1:12:28
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;
import javax.security.cert.CertificateException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.zttx.web.consts.ApplicationConst;

/**
 * <p>File：HttpUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:12:28</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class HttpUtils
{
    private static final Log loger = LogFactory.getLog(HttpUtils.class);
    
    // 私有构造器，防止类的实例化
    private HttpUtils()
    {
        super();
    }
    
    // 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
    private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler()
                                                               {
                                                                   // 自定义的恢复策略
                                                                   public boolean retryRequest(IOException exception, int executionCount, HttpContext context)
                                                                   {
                                                                       // 设置恢复策略，在发生异常时候将自动重试3次
                                                                       if (executionCount >= 3) { return false; }
                                                                       if (exception instanceof NoHttpResponseException) { return true; }
                                                                       if (exception instanceof SSLHandshakeException) { return false; }
                                                                       HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
                                                                       boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
                                                                       if (!idempotent) { return true; }
                                                                       return false;
                                                                   }
                                                               };
    
    // 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
    private static ResponseHandler<String> responseHandler     = new ResponseHandler<String>()
                                                               {
                                                                   // 自定义响应处理
                                                                   public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException
                                                                   {
                                                                       HttpEntity entity = response.getEntity();
                                                                       if (entity != null)
                                                                       {
                                                                           String charset = EntityUtils.getContentCharSet(entity) == null ? ApplicationConst.LANGUAGE_UT
                                                                                   : EntityUtils.getContentCharSet(entity);
                                                                           return new String(EntityUtils.toByteArray(entity), charset);
                                                                       }
                                                                       else
                                                                       {
                                                                           return null;
                                                                       }
                                                                   }
                                                               };
    
    /**
    * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn
    * @param url 提交地址，例：http://www.g.cn
    * @return String 响应消息，该地址的Html源码
    */
    public static String get(String url)
    {
        return get(url, null, null, null);
    }
    
    /**
    * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn
    * @param url 提交地址，例：http://www.g.cn
    * @param proxyHost 代理服务器主机
    * @param proxyPort 代理服务器端口
    * @return String 响应消息，该地址的Html源码
    */
    public static String getByProxy(String url, String proxyHost, int proxyPort)
    {
        return getByProxy(url, null, null, null, proxyHost, proxyPort);
    }
    
    /**
    * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn
    * @param url 提交地址，例：http://www.g.cn
    * @param referer 防采集处理，指定referer
    * @return String 响应消息，该地址的Html源码
    */
    public static String get(String url, String referer)
    {
        return get(url, null, null, referer);
    }
    
    /**
    * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn
    * @param url 提交地址，例：http://www.g.cn
    * @param referer 防采集处理，指定referer
    * @return String 响应消息，该地址的Html源码
    */
    public static String get(String url, String charset, String referer)
    {
        return get(url, null, charset, referer);
    }
    
    /**
    * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn?param1=value1&param2=value2.....
    * @param url 提交地址，例：http://www.g.cn
    * @param params 查询参数集, 键/值对
    * @return String 响应消息，该地址的Html源码
    */
    public static String get(String url, Map<String, String> params)
    {
        return get(url, params, null, null);
    }
    
    /**
    * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn?param1=value1&param2=value2.....
    * @param url 提交地址，例：http://www.g.cn 
    * @param params 查询参数集, 键/值对
    * @param charset 参数提交编码集，例：utf-8
    * @return String 响应消息，该地址的Html源码
    */
    public static String get(String url, Map<String, String> params, String charset)
    {
        return get(url, params, charset, null);
    }
    
    /**
    * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn?param1=value1&param2=value2.....
    * @param url 提交地址，例：http://www.g.cn 
    * @param params 查询参数集, 键/值对
    * @param charset 参数提交编码集，例：utf-8
    * @param referer 防采集处理，指定referer
    * @return String 响应消息，该地址的Html源码
    */
    public static String get(String url, Map<String, String> params, String charset, String referer)
    {
        if (StringUtils.isBlank(url)) { return null; }
        List<NameValuePair> qparams = getParamsList(params);
        if (qparams != null && qparams.size() > 0)
        {
            charset = (charset == null ? ApplicationConst.LANGUAGE_UT : charset);
            String formatParams = URLEncodedUtils.format(qparams, charset);
            url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams) : (url.substring(0, url.indexOf("?") + 1) + formatParams);
        }
        DefaultHttpClient httpclient = getDefaultHttpClient(charset);
        HttpGet hg = new HttpGet(url);
        if (StringUtils.isNotBlank(referer)) hg.addHeader("Referer", referer);
        // 发送请求，得到响应
        String responseStr = null;
        try
        {
            responseStr = httpclient.execute(hg, responseHandler);
        }
        catch (ClientProtocolException e)
        {
            loger.error("客户端连接协议错误：" + e);
        }
        catch (IOException e)
        {
            loger.error("IO操作异常：" + e);
            e.printStackTrace();
        }
        finally
        {
            abortConnection(hg, httpclient);
        }
        return responseStr;
    }
    
    /**
    * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn?param1=value1&param2=value2.....
    * @param url 提交地址，例：http://www.g.cn 
    * @param params 查询参数集, 键/值对
    * @param charset 参数提交编码集，例：utf-8
    * @param referer 防采集处理，指定referer
    * @param proxyHost 代理服务器主机地址
    * @param proxyPort 代理服务器端口 
    * @return String 响应消息，该地址的Html源码
    */
    public static String getByProxy(String url, Map<String, String> params, String charset, String referer, String proxyHost, int proxyPort)
    {
        if (StringUtils.isBlank(url)) { return null; }
        List<NameValuePair> qparams = getParamsList(params);
        if (qparams != null && qparams.size() > 0)
        {
            charset = (charset == null ? ApplicationConst.LANGUAGE_UT : charset);
            String formatParams = URLEncodedUtils.format(qparams, charset);
            url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams) : (url.substring(0, url.indexOf("?") + 1) + formatParams);
        }
        DefaultHttpClient httpclient = getDefaultHttpClient(charset, proxyHost, proxyPort);
        HttpGet hg = new HttpGet(url);
        if (StringUtils.isNotBlank(referer)) hg.addHeader("Referer", referer);
        // 发送请求，得到响应
        String responseStr = null;
        try
        {
            responseStr = httpclient.execute(hg, responseHandler);
        }
        catch (ClientProtocolException e)
        {
            loger.error("客户端连接协议错误：" + e);
        }
        catch (IOException e)
        {
            loger.error("IO操作异常：" + e);
            e.printStackTrace();
        }
        finally
        {
            abortConnection(hg, httpclient);
        }
        return responseStr;
    }
    
    /**
     * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn
     * @param url 提交地址
     * @return String 响应消息
     */
    public static String post(String url)
    {
        return post(url, null, null, null);
    }
    
    /**
     * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn
     * @param url 提交地址
     * @param referer 防采集处理，指定referer
     * @return String 响应消息
     */
    public static String post(String url, String referer)
    {
        return post(url, null, null, referer);
    }
    
    /**
     * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn
     * @param url 提交地址
     * @param charset 参数提交编码集
     * @param referer 防采集处理，指定referer
     * @return String 响应消息
     */
    public static String post(String url, String charset, String referer)
    {
        return post(url, null, charset, referer);
    }
    
    /**
    * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn
    * @param url 提交地址
    * @param params 提交参数集, 键/值对
    * @return String 响应消息
    */
    public static String post(String url, Map<String, String> params)
    {
        return post(url, params, null, null);
    }
    
    /**
    * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn....
    * @param url 提交地址
    * @param params 提交参数集, 键/值对
    * @param charset 参数提交编码集
    * @return String 响应消息
    */
    public static String post(String url, Map<String, String> params, String charset)
    {
        return post(url, params, charset, null);
    }
    
    /**
    * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn....
    * @param url 提交地址
    * @param params 提交参数集, 键/值对
    * @param charset 参数提交编码集
    * @param referer 防采集处理，指定referer
    * @return String 响应消息
    */
    public static String post(String url, Map<String, String> params, String charset, String referer)
    {
        if (StringUtils.isBlank(url)) { return null; }
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(charset);
        HttpPost hp = new HttpPost(url);
        if (StringUtils.isNotBlank(referer)) hp.addHeader("Referer", referer);
        if (null != params && params.size() > 0)
        {
            UrlEncodedFormEntity formEntity = null;
            try
            {
                if (StringUtils.isBlank(charset))
                {
                    formEntity = new UrlEncodedFormEntity(getParamsList(params));
                }
                else
                {
                    formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
                }
            }
            catch (UnsupportedEncodingException e)
            {
                loger.error("不支持的编码集：" + e);
            }
            hp.setEntity(formEntity);
        }
        // 发送请求，得到响应
        String responseStr = null;
        try
        {
            responseStr = httpclient.execute(hp, responseHandler);
        }
        catch (ClientProtocolException e)
        {
            loger.error("客户端连接协议错误：" + e);
        }
        catch (IOException e)
        {
            loger.error("IO操作异常：" + e);
        }
        finally
        {
            abortConnection(hp, httpclient);
        }
        return responseStr;
    }
    
    /**
    * Post方式提交,忽略URL中包含的参数,解决SSL双向数字证书认证
    * @param url 提交地址
    * @param params 提交参数集, 键/值对
    * @param charset 参数编码集
    * @param keystoreUrl 密钥存储库路径
    * @param keystorePassword 密钥存储库访问密码
    * @param truststoreUrl 信任存储库绝路径
    * @param truststorePassword 信任存储库访问密码, 可为null
    * @return String 响应消息
    */
    public static String post(String url, Map<String, String> params, String charset, final URL keystoreUrl, final String keystorePassword, final URL truststoreUrl,
            final String truststorePassword)
    {
        if (StringUtils.isBlank(url)) { return null; }
        DefaultHttpClient httpclient = getDefaultHttpClient(charset);
        UrlEncodedFormEntity formEntity = null;
        try
        {
            if (StringUtils.isBlank(charset))
            {
                formEntity = new UrlEncodedFormEntity(getParamsList(params));
            }
            else
            {
                formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            loger.error("不支持的编码集：" + e);
        }
        HttpPost hp = null;
        String responseStr = null;
        try
        {
            KeyStore keyStore = createKeyStore(keystoreUrl, keystorePassword);
            KeyStore trustStore = createKeyStore(truststoreUrl, keystorePassword);
            SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, keystorePassword, trustStore);
            Scheme scheme = new Scheme(ApplicationConst.SSL_DEFAULT_SCHEME, ApplicationConst.SSL_DEFAULT_PORT, socketFactory);
            httpclient.getConnectionManager().getSchemeRegistry().register(scheme);
            hp = new HttpPost(url);
            hp.setEntity(formEntity);
            responseStr = httpclient.execute(hp, responseHandler);
        }
        catch (NoSuchAlgorithmException e)
        {
            loger.error("指定的加密算法不可用：" + e);
        }
        catch (KeyStoreException e)
        {
            loger.error("keytore解析异常：" + e);
        }
        catch (CertificateException e)
        {
            loger.error("信任证书过期或解析异常：" + e);
        }
        catch (FileNotFoundException e)
        {
            loger.error("keystore文件不存在：" + e);
        }
        catch (IOException e)
        {
            loger.error("I/O操作失败或中断：" + e);
        }
        catch (UnrecoverableKeyException e)
        {
            loger.error("keystore中的密钥无法恢复异常：" + e);
        }
        catch (KeyManagementException e)
        {
            loger.error("处理密钥管理的操作异常：" + e);
        }
        finally
        {
            abortConnection(hp, httpclient);
        }
        return responseStr;
    }
    
    /**
    * 获取DefaultHttpClient实例
    * @param charset 参数编码集, 可空
    * @return DefaultHttpClient 对象
    */
    private static DefaultHttpClient getDefaultHttpClient(final String charset)
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        // HttpHost proxy = new HttpHost("", port);
        // httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
        // proxy);
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        // 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
        httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13");
        httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
        httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset == null ? ApplicationConst.LANGUAGE_UT : charset);
        httpclient.setHttpRequestRetryHandler(requestRetryHandler);
        return httpclient;
    }
    
    /**
    * 获取DefaultHttpClient实例
    * @param charset 参数编码集, 可空
    * @return DefaultHttpClient 对象
    */
    private static DefaultHttpClient getDefaultHttpClient(final String charset, String host, int port)
    {
        // DefaultHttpClient httpclient = new DefaultHttpClient();
        // httpclient = setProxy(httpclient, host, port);
        DefaultHttpClient httpclient = setProxy(new DefaultHttpClient(), host, port);
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        // 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
        httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13");
        httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
        httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset == null ? ApplicationConst.LANGUAGE_UT : charset);
        httpclient.setHttpRequestRetryHandler(requestRetryHandler);
        return httpclient;
    }
    
    /**
    * 释放HttpClient连接
    * @param hrb 请求对象 
    * @param httpclient DefaultHttpClient，client对象
    */
    private static void abortConnection(final HttpRequestBase hrb, final HttpClient httpclient)
    {
        if (hrb != null)
        {
            hrb.abort();
        }
        if (httpclient != null)
        {
            httpclient.getConnectionManager().shutdown();
        }
    }
    
    /**
    * 从给定的路径中加载此 KeyStore
    * @param url keystore URL路径
    * @param password keystore访问密钥
    * @return keystore 对象
    */
    private static KeyStore createKeyStore(final URL url, final String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException
    {
        if (url == null) { throw new IllegalArgumentException("Keystore url may not be null"); }
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = null;
        try
        {
            is = url.openStream();
            try
            {
                keystore.load(is, password != null ? password.toCharArray() : null);
            }
            catch (java.security.cert.CertificateException e)
            {
                e.printStackTrace();
            }
        }
        finally
        {
            if (is != null)
            {
                is.close();
                is = null;
            }
        }
        return keystore;
    }
    
    /**
    * 将传入的键/值对参数转换为NameValuePair参数集
    * @param paramsMap 参数集, 键/值对
    * @return NameValuePair参数集
    */
    private static List<NameValuePair> getParamsList(Map<String, String> paramsMap)
    {
        if (paramsMap == null || paramsMap.size() == 0) { return null; }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> map : paramsMap.entrySet())
        {
            params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
        }
        return params;
    }
    
    private static DefaultHttpClient setProxy(DefaultHttpClient httpClient, String proxyHost, int proxyPort)
    {
        HttpHost proxy = new HttpHost(proxyHost, proxyPort);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        return httpClient;
    }
    
    /**
    * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn
    * @param url 提交地址
    * @param params 提交参数集, 键/值对
    * @return String 响应消息
    */
    public static String postMultipart(String url, Map<String, String> params, String charset, String field, byte[] file, String filename, String contentType)
    {
        if (StringUtils.isBlank(url)) { return null; }
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(charset);
        HttpPost hp = new HttpPost(url);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        if (null != params && params.size() > 0)
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(), ContentType.create("text/html", charset));
            }
        }
        if (StringUtils.isNotEmpty(field))
        {
            multipartEntityBuilder.addBinaryBody(field, file, ContentType.create(contentType), filename);
        }
        hp.setEntity(multipartEntityBuilder.build());
        // 发送请求，得到响应
        String responseStr = null;
        try
        {
            System.out.println("WESHOP API-URL: " + url);
            System.out.println("WESHOP API-PARAMS: " + params + " " + field + " " + filename);
            responseStr = httpclient.execute(hp, responseHandler);
            System.out.println("WESHOP API-RESULT: " + responseStr);
        }
        catch (ClientProtocolException e)
        {
            loger.error("客户端连接协议错误：" + e);
        }
        catch (IOException e)
        {
            loger.error("IO操作异常：" + e);
        }
        finally
        {
            abortConnection(hp, httpclient);
        }
        return responseStr;
    }
    
    public static String postMultipart(String url, Map<String, String> params, String charset, InputStream inpusStream)
    {
        if (StringUtils.isBlank(url)) { return null; }
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(charset);
        HttpPost hp = new HttpPost(url);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        if (null != params && params.size() > 0)
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(), ContentType.create("text/html", charset));
            }
        }
        if (inpusStream != null)
        {
            multipartEntityBuilder.addBinaryBody("files", inpusStream);
        }
        hp.setEntity(multipartEntityBuilder.build());
        // 发送请求，得到响应
        String responseStr = null;
        try
        {
            System.out.println("WESHOP API-PARAMS: " + params);
            responseStr = httpclient.execute(hp, responseHandler);
            System.out.println("WESHOP API-RESULT: " + responseStr);
        }
        catch (ClientProtocolException e)
        {
            loger.error("客户端连接协议错误：" + e);
        }
        catch (IOException e)
        {
            loger.error("IO操作异常：" + e);
        }
        finally
        {
            abortConnection(hp, httpclient);
        }
        return responseStr;
    }
}
