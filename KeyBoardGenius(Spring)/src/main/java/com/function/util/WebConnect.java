package com.function.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebConnect {

	private static final Logger logger = LoggerFactory.getLogger(WebConnect.class);
	
	/** 
	 *  
	 * 方法说明：使用百度place v2.0 采集位置坐标 
	 * @author admin 2013-12-11 上午08:39:45 
	 * @param houses : 位置名称 
	 * @param city : 城市 
	 * @return 
	 */  
	public String sendUrl(String url){  
	    CloseableHttpClient client = null;  
	    String data = "";
	    try {  
	        client = HttpClients.createDefault();  
	        HttpPost httpPost = new HttpPost(url);  
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间  
	        httpPost.setConfig(requestConfig);  
	        HttpResponse response = client.execute(httpPost); 
	        if(response.getStatusLine().getStatusCode() == 302){
	        	
	        	Header header = response.getHeaders("location")[0];
	        	String newUrl = header.getValue();
	        	httpPost = new HttpPost(newUrl);
	        	response = client.execute(httpPost); 
	        }
	        HttpEntity entity = response.getEntity();  
	        if (entity != null) {  
	        	  data = EntityUtils.toString(entity, "UTF-8");  
	               ObjectMapper mapper = new ObjectMapper();  
	           }  
	    } catch (UnsupportedEncodingException e) {  
	        logger.error(e.toString());  
	    }  catch (Exception e) {  
	       
	        logger.error("异常:" + e);  
	    } finally{  
	        try {  
	            client.close();  
	        } catch (IOException e) {  
	            logger.error(e.toString());  
	        }   
	    }  
	    return data;  
	}  
	
	
	/** 
	 *  
	 * 方法说明：使用百度place v2.0 采集位置坐标 
	 * @author admin 2013-12-11 上午08:39:45 
	 * @param houses : 位置名称 
	 * @param city : 城市 
	 * @return 
	 */  
	public String sendUrlByGet(String url,String cookie){  
	    CloseableHttpClient client = null;  
	    String data = "";
	    try {  
	        client = HttpClients.createDefault();  
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.addHeader("Cookie", cookie);
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间  
	        httpGet.setConfig(requestConfig);  
	        HttpResponse response = client.execute(httpGet); 
	        if(response.getStatusLine().getStatusCode() == 302){
	        	
	        	Header header = response.getHeaders("location")[0];
	        	String newUrl = header.getValue();
	        	httpGet = new HttpGet(newUrl);
	        	response = client.execute(httpGet); 
	        }
	        HttpEntity entity = response.getEntity();  
	        if (entity != null) {  
	        	  data = EntityUtils.toString(entity, "UTF-8");  
	               ObjectMapper mapper = new ObjectMapper();  
	           }  
	    } catch (UnsupportedEncodingException e) {  
	        logger.error(e.toString());  
	    }  catch (Exception e) {  
	       
	        logger.error("异常:" + e);  
	    } finally{  
	        try {  
	            client.close();  
	        } catch (IOException e) {  
	            logger.error(e.toString());  
	        }   
	    }  
	    return data;  
	}  
}
