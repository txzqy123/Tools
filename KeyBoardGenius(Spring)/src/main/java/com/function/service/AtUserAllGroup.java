package com.function.service;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.function.util.Constants;
import com.function.util.FileUtil;
import com.function.util.StaticParam;

public class AtUserAllGroup implements Runnable{

private static final Logger logger = LoggerFactory.getLogger(AtGroupAllUser.class);
	
	private int totalGroup;
	
	private int oneCircleWaitTime;
	
	private int atWaitTime;
	
	private int pressWaitTime;
	
	private String dataFilePath;
	
	private String userId;
	
	public AtUserAllGroup(int totalGroup,int oneCircleWaitTime,int atWaitTime,int pressWaitTime,String dataFilePath,String groupId){
		
		this.totalGroup = totalGroup;
		this.oneCircleWaitTime = oneCircleWaitTime;
		this.atWaitTime = atWaitTime;
		this.pressWaitTime = pressWaitTime;
		this.dataFilePath = dataFilePath;
		this.userId = groupId;
	}
	
	public void run() {
		StaticParam.runFlg= 0;
//		String[] rows = StaticParam.groupAllUser.split("\n");
//		try{
//			Thread.sleep(5000);
//		} catch(Exception ex){
//			
//		}
//		
//		for(int row=0;row<rows.length;row++){
//			if(StaticParam.runFlg == 0){
//				String[] items = rows[row].split(Constants.SPLIT_SPACE);
//				String qqId = items[3];
//				inputAtContent(qqId,row,rows.length);
//			}
//		}
		try{
			
			
			List<String> allGroupId = FileUtil.readFileByLines(this.dataFilePath);
			List<String> hadSend = FileUtil.readFileByLines(this.dataFilePath.replace(".txt", "_send.txt"));
			Robot r = new Robot();
			for(int row=0;row<allGroupId.size();row++){
				String[] items= allGroupId.get(row).split(Constants.SPLIT_SPACE);
				String qqGroupCode = "";
				boolean hasSend = false;
				if(StaticParam.runFlg != 0){
					
					break;
				}
				if(items.length >2 && StaticParam.runFlg ==0){
					
					for(int hadItem =0;hadItem < hadSend.size();hadItem++){
						
						if(hadSend.get(hadItem).split(Constants.SPLIT_SPACE)[2].equals(items[2])){
							
							hasSend = true;
							break;
						}
						
					}
					
					if(!hasSend){
						
						qqGroupCode = items[2];
						
						logger.info("当前运行的群号为：" + qqGroupCode);
						FileUtil.appendMethod(this.dataFilePath.replace(".txt", "_send.txt"), allGroupId.get(row));
						FileUtil.appendMethod(this.dataFilePath.replace(".txt", "_send.txt"), "\n");
						for(int i=0;i<10;i++){
							
							r.keyPress(KeyEvent.VK_CONTROL);
							r.keyPress(KeyEvent.VK_ALT);
							r.keyPress(KeyEvent.VK_Z);
							r.keyRelease(KeyEvent.VK_CONTROL);
							r.keyRelease(KeyEvent.VK_ALT);
							r.keyRelease(KeyEvent.VK_Z);
							Thread.sleep(500);
						}
						
						for(int i=0;i<qqGroupCode.length();i++){
							
							char item = qqGroupCode.charAt(i);
							r.keyPress(item);
							r.keyRelease(item);
							Thread.sleep(5);
						}
						Thread.sleep(1000);
						r.keyPress(KeyEvent.VK_ENTER);
						r.keyRelease(KeyEvent.VK_ENTER);
						AtGroupAllUser users = new AtGroupAllUser(totalGroup,oneCircleWaitTime,atWaitTime,pressWaitTime,MessageFormat.format("{0}\\{1}.txt",
								this.dataFilePath.substring(0, this.dataFilePath.indexOf(".txt")),qqGroupCode));
						users.run();
						while(StaticParam.onRuning == 0){
							
							Thread.sleep(5000);
						}
					}
				}
				
			}
		} catch(Exception ex){
			
		}
		
		
	}
	
	private void inputAtContent(String qqId,int row,int rowCount){
		
		int oneCount = rowCount >=20 ? 20 : rowCount - 1;
		try{
			
			
			// 创建Robot对象
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_SHIFT);
			logger.info("输入@");
			Thread.sleep(atWaitTime);
			for(int i=0;i<qqId.length();i++){
				
				char item = qqId.charAt(i);
				r.keyPress(item);
				r.keyRelease(item);
				Thread.sleep(5);
			}
			Thread.sleep(pressWaitTime);
			logger.info("按下Enter选择按钮");
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			logger.info("行" + (row + 1) +",一次粘贴个数" + oneCount +",总数："+rowCount);
			if((row+1)%oneCount==0 || (row + 1) == rowCount){
				
				logger.info("按下粘贴按钮");
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);
				
				r.keyRelease(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_CONTROL);
				logger.info("按下回车发出信息");
			}
			Thread.sleep(atWaitTime);
		} catch(Exception ex){
			
			
		}
		
	}
}
