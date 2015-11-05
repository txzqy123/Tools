package com.function.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs.EncodingException;

import com.function.bean.JavaBean;
import com.function.util.CommonUtil;
import com.function.util.Constants;
import com.function.util.DateUtils;
import com.function.util.JsonUtil;

/**
 * 旅行时间批量操作线程
 * 
 * @author  xo
 * @version 1.0 2012-07-14
 */
public class BatchProcessTask implements Runnable {
	
	private static final Logger logger  = LoggerFactory.getLogger(BatchProcessTask.class);

	/** redis tool */
	private CacheService cacheService;
	
	/**
	 * 构造函数
	 * 
	 * @param CacheService redis操作工具
	 * @param passObjList 过车信息列表
	 */
	public BatchProcessTask(CacheService _cacheService, List<String> _passObjList, int _agingTime) {
		this.cacheService	= _cacheService;
	}

	/**
	 * 线程运行
	 */
	public void run() {
		logger.info("批处理线程开始");
		
		logger.info("批处理线程结束");
	}
}