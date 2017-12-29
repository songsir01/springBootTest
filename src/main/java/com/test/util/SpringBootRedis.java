/**
 * Project Name:SpringBootTest
 * File Name:SpringBootRedis.java
 * Package Name:com.test.util
 * Date:2017年12月28日下午5:41:38
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * ClassName:SpringBootRedis <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年12月28日 下午5:41:38 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Component
public class SpringBootRedis<E> {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	SerializeUtil<E> serialize;
	
	
	public E get(String key){
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		return serialize.unserialize(opsForValue.get(key));
	}
	
	public boolean set(String key, E value){
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		
		try {
			opsForValue.set(key, serialize.serialize(value));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean set(String key, E value, long expire){
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		
		try {
			opsForValue.set(key, serialize.serialize(value),expire,TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean delete(String key){
		try {
			redisTemplate.delete(key);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void clear(){
		Set<String> keySet = redisTemplate.keys("*");
		redisTemplate.delete(keySet);
	}
	
	
	public int size(){
		Set<String> keySet = redisTemplate.keys("*");
		return keySet.size();
	}
	
	public Set<String> keySet(){
		return redisTemplate.keys("*");
	}
	
	public Collection<E> values(){
		Set<String> keySet = redisTemplate.keys("*");
		List<E> values = new ArrayList<E>();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()){
			values.add(get(iterator.next()));
		}
		return values;
	}
	
	
	
	
}

