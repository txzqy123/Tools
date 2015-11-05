package com.function.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.function.util.FileUtil;

/**
 * 获取代理IP
 * @author zqy
 *
 */
public class GetProxy implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(GetProxy.class);
	private int threadCount;
	private int thread;
	public GetProxy(int thread,int threadCount){
		
		this.threadCount = threadCount;
		this.thread = thread;
	}
	
	public void run() {
		
		int startPoint = thread==1?0:(256/threadCount*(thread - 1) + 1);
		int endPoint = 256/threadCount*thread;
		for(int i = startPoint;i<endPoint;i++){
			
			for(int j=0;j<256;j++){
				
				for(int m=0;m<256;m++){
					
					for(int n=0;n<256;n++){
						
						createIPAddress(String.format("%s.%s.%s.%s", i,j,m,n),80);
						createIPAddress(String.format("%s.%s.%s.%s", i,j,m,n),8080);
						createIPAddress(String.format("%s.%s.%s.%s", i,j,m,n),3128);
						createIPAddress(String.format("%s.%s.%s.%s", i,j,m,n),8081);
						createIPAddress(String.format("%s.%s.%s.%s", i,j,m,n),9080);
					}
				}
			}
		}
		
		logger.info("执行完毕");
	}
	
	public void createIPAddress(String ip,int port) {
		URL url = null;
		try {
			url = new URL("http://www.baidu.com");
		} catch (MalformedURLException e) {
			System.out.println("url invalidate");
		}
		InetSocketAddress addr = null;
		addr = new InetSocketAddress(ip, port);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http proxy
		InputStream in = null;
		try {
			URLConnection conn = url.openConnection(proxy);
			conn.setConnectTimeout(1000);
			in = conn.getInputStream();
		} catch (Exception e) {
			logger.error("ip: " + ip + ":"+port+" is not aviable");
		}
		String s = convertStreamToString(in);
		// System.out.println(s);
		if (s.indexOf("baidu") > 0) {
			FileUtil.createFile("E:\\database\\GetProxy\\proxy"+thread+".txt",ip +":" + port + "\n");
		}
	}

	public static String convertStreamToString(InputStream is) {
		if (is == null)
			return "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();

	}
}
