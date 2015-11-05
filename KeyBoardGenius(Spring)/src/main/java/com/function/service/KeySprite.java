package com.function.service;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.function.util.StaticParam;

public class KeySprite implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(KeySprite.class);
	
	private int totalGroup;
	
	private int oneCircleWaitTime;
	
	private int atWaitTime;
	
	private int pressWaitTime;
	public KeySprite(int totalGroup,int oneCircleWaitTime,int atWaitTime,int pressWaitTime){
		
		this.totalGroup = totalGroup;
		this.oneCircleWaitTime = oneCircleWaitTime;
		this.atWaitTime = atWaitTime;
		this.pressWaitTime = pressWaitTime;
	}
	
	public void run() {

//		String applicationPath = System.getProperty("user.dir");
//		String filename = "KeySprite.txt";
		try {
//			// 读取配置文件
//			Vector v = readFile(String.format("%s\\bin\\%s", applicationPath,
//					filename));
			// 执行文件
			//parseVector(v);
			excute();

		} catch (Exception e) {
			System.out.println("其他错误");
		}
	}

	/**
	 * 读取文件到Vector中
	 * 
	 * @param filepath
	 *            文件路径
	 */
	public Vector readFile(String filepath) throws IOException {
		Vector v = new Vector();

		// 文件缓冲输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(filepath)));
		// 读取数据
		String s = br.readLine();
		while (s != null) {
			// 添加到v
			v.add(s);
			// 读取下一行
			s = br.readLine();
		}
		// 关闭输入流
		br.close();
		// 返回数据
		return v;
	}
	
	public void excute(){
		
		try {
			Thread.sleep(5000);
			// 创建Robot对象
			Robot r = new Robot();
			for(int number =48;number < 58; number ++){
				int oneCount = totalGroup >=20 ? 20 : totalGroup;
				logger.info(MessageFormat.format("当前检测数字为:{0}，运行一圈需要输入的个数为:{1}，群总数为：{2},程序点击@后等待时间：{3},程序运行一圈等待时间{4}",number,oneCount,totalGroup,
						atWaitTime,oneCircleWaitTime));
				
				for(int i=0;i<totalGroup;i++){
					logger.info(MessageFormat.format("当前输入个数为:{0}",i));
					if(StaticParam.runFlg == 0){
						
						// r.mouseMove(300, 650);
						r.keyPress(KeyEvent.VK_SHIFT);
						r.keyPress(KeyEvent.VK_2);
						r.keyRelease(KeyEvent.VK_2);
						r.keyRelease(KeyEvent.VK_SHIFT);
						logger.info("输入@");
						r.keyPress(number);
						r.keyRelease(number);
						Thread.sleep(atWaitTime);
						for(int j=0;j<i;j++){
							
							r.keyPress(KeyEvent.VK_DOWN);
							r.keyRelease(KeyEvent.VK_DOWN);
							logger.info("按下往下的按钮");
							Thread.sleep(pressWaitTime);
						}
						logger.info("按下Enter选择按钮");
						r.keyPress(KeyEvent.VK_ENTER);
						r.keyRelease(KeyEvent.VK_ENTER);
						if((i+1)%oneCount==0){
							
							logger.info("按下粘贴按钮");
							r.keyPress(KeyEvent.VK_CONTROL);
							r.keyPress(KeyEvent.VK_V);
							
							r.keyRelease(KeyEvent.VK_V);
							r.keyRelease(KeyEvent.VK_CONTROL);
							r.keyPress(KeyEvent.VK_CONTROL);
							r.keyPress(KeyEvent.VK_ENTER);
							r.keyRelease(KeyEvent.VK_ENTER);
							r.keyRelease(KeyEvent.VK_CONTROL);
							Thread.sleep(oneCircleWaitTime);
							logger.info("按下回车发出信息");
						}
					} else {
						logger.info("停止");
						break;
						
					}
						
					
				}
			}
			
		} catch (Exception e) {
			logger.error("其他错误");
		}
	}

	/**
	 * 解析读到的Vector，并执行对应的操作
	 * 
	 * @param v
	 *            Vector对象
	 */
	public void parseVector(Vector v) {
		int size = v.size();
		
		try {
			Thread.sleep(5000);
			int count = 0;
			int oneCircleWaitTime = 1000;
			int atWaitTime = 500;
			for(int i=0;i<v.size();i++){
				
				String rowData = (String)v.get(i);
				String[] data = rowData.split(" ");
				if(data.length == 3){
					
					if(data[1].equals("总数")){
						
						count = Integer.parseInt(data[2]);
					}
					
					if(data[1].equals("oneCircleWaitTime")){
						
						oneCircleWaitTime = Integer.parseInt(data[2]);
					}
					
					if(data[1].equals("atWaitTime")){
						
						atWaitTime = Integer.parseInt(data[2]);
					}
				} else {
					
					logger.error("配置文件配置错误，格式应为：描述 关键字 值");
				}
			}
			// 创建Robot对象
			Robot r = new Robot();
			String s = (String) v.get(0);
			for(int number =48;number < 58; number ++){
				
				int oneCount = count >=20 ? 20 : count;
				
				for(int i=0;i<count;i++){
					
						
					//r.mouseMove(300, 650);
					r.keyPress(KeyEvent.VK_SHIFT);
					r.keyPress(KeyEvent.VK_2);
					r.keyRelease(KeyEvent.VK_2);
					r.keyRelease(KeyEvent.VK_SHIFT);
					r.keyPress(number);
					r.keyRelease(number);
					Thread.sleep(atWaitTime);
					for(int j=0;j<i;j++){
						
						r.keyPress(KeyEvent.VK_DOWN);
						r.keyRelease(KeyEvent.VK_DOWN);
						//Thread.sleep(10);
					}
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
					//Thread.sleep(100);
					if((i+1)%oneCount==0){
						
						r.keyPress(KeyEvent.VK_CONTROL);
						r.keyPress(KeyEvent.VK_V);
						
						r.keyRelease(KeyEvent.VK_V);
						r.keyRelease(KeyEvent.VK_CONTROL);
						r.keyPress(KeyEvent.VK_CONTROL);
						r.keyPress(KeyEvent.VK_ENTER);
						r.keyRelease(KeyEvent.VK_ENTER);
						r.keyRelease(KeyEvent.VK_CONTROL);
						Thread.sleep(oneCircleWaitTime);
					}
				}
			}
			
			
			
//			int count = 0;
//			for (int i = 0; i < size; i++) {
//				System.out.println(i);
//				if(count == 20){
//					
//					r.keyPress(KeyEvent.VK_PASTE);
//					r.keyRelease(KeyEvent.VK_PASTE);
//					count = 0;
//					Thread.sleep(5000);
//					i = 6;
//					System.out.println("粘贴");
//				}
//				
//				String s = (String) v.get(i);
//				// 分解
//				String[] data = s.split(" ");
//				// 解析执行
//				if (data[1].equals("移动")) {
//					// 获得坐标
//					int x = Integer.parseInt(data[2]);
//					int y = Integer.parseInt(data[3]);
//					// 移动
//					r.mouseMove(x, y);
//					Thread.sleep(5000);
//				} else if (
//					data[1].equals("按键")) {
//					
//					// 获得按键种类
//					String c = data[2].toLowerCase();
//					int keyCode = Integer.parseInt(c);
//					// 按键
//					r.keyPress(keyCode);
//					// 释放
//					r.keyRelease(keyCode);
//					
//					
//					System.out.println(data[0]);
//					
//					if(i == 13){
//						System.out.println("重新开始");
//						i = 6;
//					} 
//					Thread.sleep(5000);
//				} else if (data[1].equals("暂停")) {
//					// 获得暂停时间
//					int time = Integer.parseInt(data[2]);
//					// 暂停
//					Thread.sleep(time);
//				} else if(data[1].equals("右击")){
//					
//					r.mousePress(InputEvent.BUTTON3_MASK);
//					r.mouseRelease(InputEvent.BUTTON3_MASK);
//					System.out.println(data[0]);
//					Thread.sleep(5000);
//				} else if(data[1].equals("左击")){
//					
//					r.mousePress(InputEvent.BUTTON1_MASK);
//					r.mouseRelease(InputEvent.BUTTON1_MASK);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
