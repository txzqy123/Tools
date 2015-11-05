package com.function.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.function.util.FileUtil;

/**
 * 文件对比服务
 * @author zqy
 *
 */
public class FileCompare implements Runnable{

	/**
	 * 文件目录
	 */
	private String filePath;
	
	/**
	 * 源文件
	 */
	private String sourceNameA;
	
	/**
	 * 需匹配文件
	 */
	private String sourceNameB;
	
	/**
	 * 输出文件
	 */
	private String destationName;
	
	public FileCompare(String filePath,String sourceNameA,String sourceNameB,String destationName){
		
		this.filePath = filePath;
		
		this.sourceNameA = sourceNameA;
		
		this.sourceNameB = sourceNameB;
		
		this.destationName = destationName;
	}
	
	public void run() {
		
		List<String> contentA = FileUtil.readFileByLines(filePath + "\\" + sourceNameA);
		List<String> contentB = FileUtil.readFileByLines(filePath + "\\" + sourceNameB);
		
		Map<String,String> compareDataBase = new HashMap<String,String>();
		for(String content : contentA){
			
			String[] contentCol=content != null ? content.split(",") : null;
			if(contentCol != null && contentCol.length == 2){
				compareDataBase.put(contentCol[1].replace("\"", ""), contentCol[0].replace("\"", ""));
			}
		}
		
		List<Map<String,String>> compareData = new ArrayList<Map<String,String>>();
		for(String content : contentB){
			String[] contentCols=(content != null &&  content != "")? content.split(",") : null;
			if(contentCols != null){
				Map<String,String> lineData = new HashMap<String,String>();
				for(int i=0;i<contentCols.length;i++){
					lineData.put(contentCols[i], "");
				}
				compareData.add(lineData);
			} else {
				
				System.out.println("对比数据存在空白行");
			}
		}
		
		for(int i=0;i<compareData.size();i++){
			
			for(String key : compareData.get(i).keySet()){
				
				compareData.get(i).put(key, compareDataBase.get(key));
			}
		}
		
		String contentPrint = "";
		
		for(int i=0;i<compareData.size();i++){
			
			String lineSource = "";
			String lineDestation = "";
			for(String key : compareData.get(i).keySet()){
				
				lineSource = lineSource +  "," + key;
				lineDestation = lineDestation +  "," + compareData.get(i).get(key);
			}
			
			contentPrint = contentPrint + lineSource + "\t" + lineDestation + "\n";
		}
		
		FileUtil.createFile(filePath + "\\" + destationName, contentPrint);
		
		
	}
}
 