package com.function;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.function.surface.KeyBoardGenius;


/**
 * 程序主入口
 * 
 * @author  xo
 * @version 1.0 2013-10-12
 */
public class ProgramMain {
    
	private static final Logger logger = LoggerFactory.getLogger(ProgramMain.class);

    /**
     * 启动程序、初始化数据
     * 
     * @param  args
     * @throws Exception
     */
	@SuppressWarnings("serial")
	public static void main(String[] args) throws Exception {
		logger.info("system is starting...");
		
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ac.registerShutdownHook();
		KeyBoardGenius keyBoardGenius = (KeyBoardGenius)ac.getBean("keyBoardGenius");
		keyBoardGenius.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		keyBoardGenius.setVisible(true);
		
//	    JdbcTemplate jdbcTemplate = (JdbcTemplate)ac.getBean("jdbcTemplate");
//	    CacheService cacheService = (CacheService)ac.getBean("cacheService");
        
	    
        logger.info("system start successful...");
	}
}