package com.function.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

/**
 * Json工具类 
 * <br>
 * 提供可复用的ObjectMapper
 * 
 * @author  xo
 * @version 1.0 2012-07-14
 */
public class ObjectMapperUtils {
	
	private static final Logger logger = Logger.getLogger(ObjectMapperUtils.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 获取mapper
     */
    public static ObjectMapper getObjectMapper() {
    	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    	mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    /**
     * 根据对象获取Json字符串
     * 如果对象为Null,返回"null". 
     * 如果集合为空集合,返回"[]". 
     * @param obj 对象
     * @return Json字符串
     */
    public static String writeValueAsString(Object obj) {
        String str = null;
        try {
            str = getObjectMapper().writeValueAsString(obj);
        } catch (JsonGenerationException e) {
        	logger.error("将对象转换为json字符串时出现异常", e);
        } catch (JsonMappingException e) {
        	logger.error("将对象转换为json字符串时出现异常", e);
        } catch (IOException e) {
        	logger.error("将对象转换为json字符串时出现异常", e);
        }
        return str;
    }

    /**
     * 根据json字符串获取对象
     * 
     * @param <T> 对象类型
     * @param str json字符串
     * @param clazz class
     * @return 对象
     */
    public static <T> T readValue(String str, Class<T> clazz) {
        T t = null;
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            t = getObjectMapper().readValue(str, clazz);
        } catch (JsonParseException e) {
        	logger.error("将json字符串转换为对象时出现异常", e);
        } catch (JsonMappingException e) {
        	logger.error("将json字符串转换为对象时出现异常", e);
        } catch (IOException e) {
        	logger.error("将json字符串转换为对象时出现异常", e);
        }
        return t;
    }
    
    /**
     * 根据json字符串获取对象List
     * 
     * @param <T> 对象类型
     * @param str json字符串
     * @param clazz class
     * @return 对象
     */
    public static <T> List<T> readValueList(String str, Class<T> clazz) {
        List<T> t = null;
        if (StringUtils.isBlank(str)) {
            return null;
        }
        
        try {
        	JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            t = getObjectMapper().readValue(str, javaType);
        } catch (JsonParseException e) {
        	logger.error("将json字符串转换为对象时出现异常", e);
        } catch (JsonMappingException e) {
        	logger.error("将json字符串转换为对象时出现异常", e);
        } catch (IOException e) {
        	logger.error("将json字符串转换为对象时出现异常", e);
        }
        return t;
    }
}