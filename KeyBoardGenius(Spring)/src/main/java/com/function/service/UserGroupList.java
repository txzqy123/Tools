package com.function.service;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.function.bean.qqGroup.QQUserInfo;
import com.function.bean.qqGroup.QqUserGroup;
import com.function.util.Constants;
import com.function.util.FileUtil;
import com.function.util.ObjectMapperUtils;
import com.function.util.StaticParam;
import com.function.util.WebConnect;

public class UserGroupList implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(UserGroupList.class);
	
	private String groupAddress;
	
	private String loginCookie;
	
	private String dataFilePath;
	
	private String qqGroupId;
	
	public UserGroupList(String groupAddress,String loginCookie,String dataFilePath,String qqGroupId){
		
		this.groupAddress = groupAddress;
		this.loginCookie = loginCookie;
		this.dataFilePath = dataFilePath;
		this.qqGroupId = qqGroupId;
	}
	
	public void run() {
		
		WebConnect web = new WebConnect();
		String data = web.sendUrlByGet(groupAddress,loginCookie);
		logger.info("转化前数据：" + data);
		String newData = data.substring(data.indexOf("{"), data.length() - 2).replaceAll("class", "class1").replaceAll("default", "default1");
		logger.info("转化后数据：" + newData);
		QQUserInfo qqUserInfo = null;
		
		try{
			
			qqUserInfo = ObjectMapperUtils.readValue(newData,QQUserInfo.class);
		} catch(Exception ex){
			
			logger.error("json转qq群对象出错，"+ ex);
		}
		
		FileUtil.createFile(MessageFormat.format("{0}//{1}.{2}",this.dataFilePath,qqGroupId,"txt"), createPrintContent(qqUserInfo));
	}
	
	public String createPrintContent(QQUserInfo qqUserInfo){
		
		StringBuffer content = new StringBuffer();
		for(QqUserGroup qqUserGroup : qqUserInfo.getData().getGroup()){
			
			content.append(qqUserGroup.getAuth()).append(Constants.SPLIT_SPACE)
			.append(qqUserGroup.getFlag()).append(Constants.SPLIT_SPACE)
			.append(qqUserGroup.getGroupid()).append(Constants.SPLIT_SPACE)
			.append(qqUserGroup.getGroupname()).append(Constants.SPLIT_SPACE)
			.append("\n");
		}
		return content.toString();
	}

}
