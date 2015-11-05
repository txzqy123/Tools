package com.function.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * 
 * @author zqy
 * @version 1.0 2015-09-20
 */
public class CacheService {

	@Autowired
    private RedisTemplate redisTemplate;

    private ValueOperations valueOps;

    private HashOperations hashOps;

    @PostConstruct
    public void init() {
        valueOps = redisTemplate.opsForValue();
        hashOps = redisTemplate.opsForHash();
    }

    /**
     * 设置键值
     */
    public void set(Object key, Object value) {
        valueOps.set(key, value);
    }
    
    /**
     * 根据键获得值
     */
    public Object get(Object key) {
        return valueOps.get(key);
    }

    /**
     * hash设置键值
     */
    public void hset(Object key, Object field, Object value) {
        hashOps.put(key, field, value);
    }

    /**
     * hash根据键获得值
     */
    public Object hget(Object key, Object field) {
        return hashOps.get(key, field);
    }
    
    public Object Incr(Object arg0, long arg1) {
        return valueOps.increment(arg0, arg1);
    }
    

    /**
     * hash根据键移除得值
     */
    public void remove(Object key, Object field) {
       hashOps.delete(key, field);
    }
    
    /**
     * 判断key是否存在
     */
    public Boolean hasKey(Object key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除键
     * 
     * @param key
     */
    public void delete(Object key) {
        redisTemplate.delete(key);
    }
    
    /**
     * 设置key过期
     * 
     * @param key
     */
    public void expire(Object key,  long timeout, TimeUnit unit) {
    	redisTemplate.expire(key, timeout, unit);
    }
    
    /**
     * keys 命令
     * 
     * @param key
     */
    public Set keys(Object key) {
    	return redisTemplate.keys(key);
    }
    
	/**
	 * 压栈
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long push(String key, Object value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * 出栈
	 * 
	 * @param key
	 * @return
	 */
	public Object pop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}
	
	/**
	 * 范围检索
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List range(String key, int start, int end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * @return the redisTemplate
	 */
	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	/**
	 * @param redisTemplate the redisTemplate to set
	 */
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}