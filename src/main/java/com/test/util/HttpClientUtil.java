
package com.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * ClassName: HttpClientUtil <br/>
 * Function: httpClient工具类. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2018年1月2日 下午4:27:18 <br/>
 *
 * @author SongYapeng
 * @version 
 * @since JDK 1.7
 */
public class HttpClientUtil {  
    private static PoolingHttpClientConnectionManager cm;  
    private static String EMPTY_STR = "";  
    private static String UTF_8 = "UTF-8";  
  
    private static void init() {  
        if (cm == null) {  
            cm = new PoolingHttpClientConnectionManager();  
            cm.setMaxTotal(50);// 整个连接池最大连接数  
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2  
        }  
    }  
  
    /** 
     * 通过连接池获取HttpClient 
     *  
     * @return 
     */  
    private static CloseableHttpClient getHttpClient() {  
        init();  
        return HttpClients.custom().setConnectionManager(cm).build();  
    }  
  
    /** 
     *  
     * @param url 
     * @return 
     */  
    public static String httpGetRequest(String url, String encoding) {  
        HttpGet httpGet = new HttpGet(url);  
        httpGet.addHeader("Content-Type","text/html;charset="+encoding);
        return getResult(httpGet);  
    }  
  
    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {  
        URIBuilder ub = new URIBuilder();  
        ub.setPath(url);  
  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);  
        ub.setParameters(pairs);  
  
        HttpGet httpGet = new HttpGet(ub.build());  
        return getResult(httpGet);  
    }  
  
    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)  
            throws URISyntaxException {  
        URIBuilder ub = new URIBuilder();  
        ub.setPath(url);  
  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);  
        ub.setParameters(pairs);  
  
        HttpGet httpGet = new HttpGet(ub.build());  
        for (Map.Entry<String, Object> param : headers.entrySet()) {  
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));  
        }  
        return getResult(httpGet);  
    }  
  
    public static String httpPostRequest(String url) {  
        HttpPost httpPost = new HttpPost(url);  
        return getResult(httpPost);  
    }  
  
    public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {  
        HttpPost httpPost = new HttpPost(url);  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);  
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));  
        return getResult(httpPost);  
    }  
  
    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)  
            throws UnsupportedEncodingException {  
        HttpPost httpPost = new HttpPost(url);  
  
        for (Map.Entry<String, Object> param : headers.entrySet()) {  
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));  
        }  
  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);  
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));  
  
        return getResult(httpPost);  
    }  
  
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {  
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();  
        for (Map.Entry<String, Object> param : params.entrySet()) {  
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));  
        }  
  
        return pairs;  
    }  
  
    /** 
     * 处理Http请求 
     *  
     * @param request 
     * @return 
     */  
    private static String getResult(HttpRequestBase request) {  
        // CloseableHttpClient httpClient = HttpClients.createDefault();  
        CloseableHttpClient httpClient = getHttpClient();  
        try {  
            CloseableHttpResponse response = httpClient.execute(request);  
            // response.getStatusLine().getStatusCode();  
            HttpEntity entity = response.getEntity();  
            if (entity != null) {  
                // long len = entity.getContentLength();// -1 表示长度未知  
                String result = EntityUtils.toString(entity);  
                response.close();  
                // httpClient.close();  
                return result;  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
  
        }  
  
        return EMPTY_STR;  
    }
    /**
     * 
     * httpPostJSON:(json传参方式调用接口). <br/>
     * @author SongYapeng
     * @param url
     * @param params
     * @param json
     * @return
     * @since JDK 1.7
     */
    public static String httpPostJSON(String url, String params, JSONObject json) {
		String encoding = "UTF-8";
		String webUrl = url;
		
		if(params != null) webUrl = url + "?" + params;
		StringBuffer sBuffer = new StringBuffer();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建POS方法的实例
		PostMethod postMethod = new PostMethod(webUrl);
		
		try {
			if(json!=null){
				RequestEntity requestEntity = new StringRequestEntity(json.toString(),"text/html",encoding);
				postMethod.setRequestEntity(requestEntity);
				postMethod.getParams().setContentCharset(encoding);
			}
			httpClient.getHttpConnectionManager().getParams()
			.setConnectionTimeout(5000); // 连接5秒超时
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);// 读取30秒超时
			postMethod.setRequestHeader("Content-type","application/html; charset=" + encoding);
			// 执行postMethod
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("HTTP请求失败: "+ postMethod.getStatusLine());
				sBuffer = new StringBuffer();
			} else {
				InputStream inputStream = postMethod.getResponseBodyAsStream();  
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
				String str= "";  
				while((str = br.readLine()) != null){  
					sBuffer.append(str );  
				}  
			}
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("HTTP请求失败: Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		return sBuffer.toString();
	}

	public static String httpPostRequest(String url, Map<String, Object> map, HttpServletRequest request) throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(map);  
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        Cookie[] cookies = request.getCookies();
        String captchaId = "";
        for (Cookie cookie : cookies) {
			if(cookie.getName().equals("captchaId")){
				captchaId = cookie.getValue();
				break;
			}
		}
        httpPost.addHeader("Cookie", "captchaId="+captchaId);
        return getResult(httpPost); 
	}
	
	/**
	 * 
	 * httpPostXml:(post发送xml). <br/>
	 * @author SongYapeng
	 * @Date 2017年12月19日下午12:17:09
	 * @param url
	 * @param params
	 * @param xml
	 * @return
	 * @since JDK 1.7
	 */
	public static String httpPostXml(String url,String params,String xml){
		String encoding = "UTF-8";
		String webUrl = url;
		if(params != null){
			webUrl = url + "?" + params;
		}
		StringBuffer sBuffer = new StringBuffer();
		//构造httpClient实例
		HttpClient httpClient = new HttpClient();
		//穿件post方法实例
		PostMethod postMethod = new PostMethod(webUrl);
		try {
			RequestEntity requestEntity = new StringRequestEntity(xml,"text/xml",encoding);
			postMethod.setRequestEntity(requestEntity);
			postMethod.getParams().setContentCharset(encoding);
			httpClient.getHttpConnectionManager().getParams()
			.setConnectionTimeout(5000); // 连接5秒超时
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);// 读取30秒超时
			postMethod.setRequestHeader("Content-type", "text/xml; charset="	+ encoding);
			// 执行postMethod
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "+ postMethod.getStatusLine());
				sBuffer = new StringBuffer();
			} else {
				sBuffer = new StringBuffer(postMethod.getResponseBodyAsString());
			}
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放连接
			postMethod.releaseConnection();
		}
		return sBuffer.toString();
	}
  
}