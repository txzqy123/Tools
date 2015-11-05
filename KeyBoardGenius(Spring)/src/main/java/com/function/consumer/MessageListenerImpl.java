package com.function.consumer;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import com.function.service.BatchProcessTask;
import com.function.service.CacheService;

/**
 * 旅行时间入库Bolt
 * 
 * @author  xo
 * @version 1.0 2013-10-12
 */
@SuppressWarnings("serial")
public class MessageListenerImpl implements MessageListener{
	
	private static final Logger logger = LoggerFactory.getLogger(MessageListenerImpl.class);

    private CacheService cacheService;
    
	/** 过车数据失效时间 */
	private int agingTime;
	
	/** 过车队列 */
	private List<String> passStrList = new ArrayList<String>();
	
	/** 多线程*/
	private int batchCount;
	private int batchCycle;
	private TaskExecutor taskExecutor;
	private long startMS = System.currentTimeMillis();
	
	/**
	 * 消息处理
	 * <br>
	 * 接收消息然后分发给其他bean进行处理
	 * 
	 * @param  message 消息
	 */
	public void onMessage(Message message) {
		try{
			if (message instanceof TextMessage) {
				String passObjstr = ((TextMessage) message).getText();
				passStrList.add(passObjstr);
				
				long endMS = System.currentTimeMillis();				
				if(passStrList.size() == batchCount || (endMS - startMS) > batchCycle ){
					taskExecutor.execute(new BatchProcessTask(cacheService, passStrList, agingTime));
					startMS = endMS;
					passStrList = new ArrayList<String>();
				}
				
			} else {
				throw new RuntimeException("MQ消息格式不正确");
			}
		}catch(Exception e){
			logger.error("接收到MQ消息后处理时出现异常", e);
		}
	}
	
	/**
	 * @return the cacheService
	 */
	public CacheService getCacheService() {
		return cacheService;
	}

	/**
	 * @param cacheService the cacheService to set
	 */
	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	/**
	 * @return the agingTime
	 */
	public int getAgingTime() {
		return agingTime;
	}

	/**
	 * @param agingTime the agingTime to set
	 */
	public void setAgingTime(int agingTime) {
		this.agingTime = agingTime;
	}

	public void setBatchCount(int batchCount) {
		this.batchCount = batchCount;
	}

	public void setBatchCycle(int batchCycle) {
		this.batchCycle = batchCycle;
	}
	
	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
} 