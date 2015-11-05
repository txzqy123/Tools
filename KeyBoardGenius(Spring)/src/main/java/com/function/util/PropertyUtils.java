package com.function.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件操作工具类
 * 
 * @author  xo
 * @version 1.0 2013-07-23
 */
public class PropertyUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);
    private static Map propMap = new HashMap();

    private static CompositeConfiguration config;
    static {
        config = new CompositeConfiguration();
        try {
            config.addConfiguration(new PropertiesConfiguration("application.properties"));
        } catch(ConfigurationException e) {
            logger.error("初始化配置文件工具类时出现异常！", e);
        }
    }
    
    /**
     * 根据键获取对应的值
     * 
     * @param  key 键
     * @return String 值
     */
    public static String getString(String key){
        String str = (String)propMap.get(key);
        if(str == null){
            str = config.getString(key);
            propMap.put(key, str);
        }
        return str;
    }
    
    /**
     * 根据键获取对应的值
     * 
     * @param  key 键
     * @return int 值
     */
    public static int getInt(String key){
        Integer i = (Integer)propMap.get(key);
        if(i == null){
            i = config.getInt(key);
            propMap.put(key, i);
        }
        return i;
    }
    
	private PropertyUtils() {}
}
