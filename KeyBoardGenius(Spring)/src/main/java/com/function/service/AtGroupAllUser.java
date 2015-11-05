package com.function.service;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.function.util.Constants;
import com.function.util.FileUtil;
import com.function.util.StaticParam;

public class AtGroupAllUser implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(AtGroupAllUser.class);
	
	private int totalGroup;
	
	private int oneCircleWaitTime;
	
	private int atWaitTime;
	
	private int pressWaitTime;
	
	private String dataFilePath;
	
	public AtGroupAllUser(int totalGroup,int oneCircleWaitTime,int atWaitTime,int pressWaitTime,String dataFilePath){
		
		this.totalGroup = totalGroup;
		this.oneCircleWaitTime = oneCircleWaitTime;
		this.atWaitTime = atWaitTime;
		this.pressWaitTime = pressWaitTime;
		this.dataFilePath = dataFilePath;
	}
	
	public void run() {
		StaticParam.runFlg= 0;
		StaticParam.onRuning = 0;
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
			Thread.sleep(5000);
		} catch(Exception ex){
			
		}
		List<String> content = FileUtil.readFileByLines(this.dataFilePath);
		List<String> hadSend = FileUtil.readFileByLines(this.dataFilePath.replace(".txt", "_send.txt"));
		for(int row=0;row<content.size();row++){
			boolean hasSend = false;
			if(StaticParam.runFlg == 0){
				String[] items = content.get(row).split(Constants.SPLIT_SPACE);
				for(int i=0;i<hadSend.size();i++){
					
					String[] sendItems = content.get(i).split(Constants.SPLIT_SPACE);
					
					if(sendItems.length >3 && items.length>3){
						
						if(sendItems[3].equals(items[3])){
							hasSend = true;
							break;
						}
					}
				}
				if(items.length > 3 && !hasSend){
					FileUtil.appendMethod(this.dataFilePath.replace(".txt", "_send.txt"), content.get(row));
					FileUtil.appendMethod(this.dataFilePath.replace(".txt", "_send.txt"), "\n");
					String qqId = items[3];
					inputAtContent(qqId,row,content.size());
				}
			} else {
				
				break;
			}
		}
		
		StaticParam.onRuning = 1;
		
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
