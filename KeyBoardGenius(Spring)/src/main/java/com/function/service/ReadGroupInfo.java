package com.function.service;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.function.bean.qqGroup.QqGroupBasicInfo;
import com.function.bean.qqGroup.QqGroupUserInfo;
import com.function.util.Constants;
import com.function.util.FileUtil;
import com.function.util.ObjectMapperUtils;
import com.function.util.StaticParam;
import com.function.util.WebConnect;


public class ReadGroupInfo implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(ReadGroupInfo.class);
	
	private String groupAddress;
	
	private String loginCookie;
	
	private String dataFilePath;
	
	private String qqGroupId;
	
	public ReadGroupInfo(String groupAddress,String loginCookie,String dataFilePath,String qqGroupId){
		
		this.groupAddress = groupAddress;
		this.loginCookie = loginCookie;
		this.dataFilePath = dataFilePath;
		this.qqGroupId = qqGroupId;
	}
	
	public void run() {
		
		List<String> content = FileUtil.readFileByLines(MessageFormat.format("{0}//{1}.{2}",this.dataFilePath,this.qqGroupId,"txt"));
		for(int i=0;i<content.size();i++){
			
			String[] items = content.get(i).split(Constants.SPLIT_SPACE);
			if(items.length > 3){
			
				WebConnect web = new WebConnect();
				String data = web.sendUrlByGet(MessageFormat.format(groupAddress,items[2]),loginCookie);
				logger.info("转化前数据：" + data);
				String newData = data.substring(data.indexOf("{"), data.length() - 2).replaceAll("class", "class1").replaceAll("default", "default1");
				logger.info("转化后数据：" + newData);
				QqGroupBasicInfo qqGroupBasicInfo = null;
				
				try{
					
					qqGroupBasicInfo = ObjectMapperUtils.readValue(newData,QqGroupBasicInfo.class);
				} catch(Exception ex){
					
					logger.error("json转qq群对象出错，"+ ex);
				}
				FileUtil.mkdirFolder(MessageFormat.format("{0}//{1}",this.dataFilePath,qqGroupId));
				FileUtil.createFile(MessageFormat.format("{0}//{1}//{2}.{3}",this.dataFilePath,qqGroupId,items[2],"txt"), createPrintContent(qqGroupBasicInfo));
				
			}
		}
		
		
	}
	
	public String getGroupId(String groupAddress){
		
		String[] items = groupAddress.split("&");
		return items[2].substring(8);
	}
	
	public String createPrintContent(QqGroupBasicInfo qqGroupBasicInfo){
		
		StringBuffer content = new StringBuffer();
		for(QqGroupUserInfo userInfo : qqGroupBasicInfo.getData().getItem()){
			
			content.append(userInfo.getIscreator()).append(Constants.SPLIT_SPACE)
			.append(userInfo.getIsmanager()).append(Constants.SPLIT_SPACE)
			.append(userInfo.getNick()).append(Constants.SPLIT_SPACE)
			.append(userInfo.getUin()).append(Constants.SPLIT_SPACE)
			.append("\n");
		}
		
		return content.toString();
	}
}
