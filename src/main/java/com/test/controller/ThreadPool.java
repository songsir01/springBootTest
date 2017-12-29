/**
 * Project Name:SpringBootTest
 * File Name:ThreadPool.java
 * Package Name:com.test.controller
 * Date:2017年12月28日下午2:29:55
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.controller;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ClassName:ThreadPool <br/>
 * Function: <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年12月28日 下午2:29:55 <br/>
 * 
 * @author SongYapeng
 * @version
 * @since JDK 1.7
 * @see
 */
public class ThreadPool {
	
	static{
		
		System.out.println("静态代码块");
		
	}
	

	

	public ThreadPool() {
		
		testHashMap();
		
		
	}

	public static void main(String[] args) {
		
		
		System.out.println("这是main主线程1");
		
		for(int i =0;i<10;i++){
			
			Map<String, String> map = new HashMap<>();
			map.put("a"+i, "a"+i);
			System.out.println(map);
		}
		
//		try {
//			System.out.println(1 / 0);
//		} catch (Exception e) {
//			System.out.println("数学计算异常");
//			return;
//		} finally {
//			System.out.println("一定要执行");
//		}
//
//		long start = System.currentTimeMillis();
//
//		testHashMap();
//
//		Thread.sleep(3000);
//
//		testHashTable();
//
//		Thread.sleep(3000);
//
//		testConcurrentHashMap();
//
//		System.out.println("总时间：" + (System.currentTimeMillis() - start));
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("这是新建的线程");
			}
			
		}).start();;
		
		System.out.println("这是main主线程2");

		/*
		 * ConcurrentMap<String,String> map = new
		 * ConcurrentHashMap<String,String>();
		 * 
		 * Map<String, String> map2 = new HashMap<String,String>();
		 * 
		 * map.put("a", "a"); map.put("aa", "aa"); map.put("aaa", "aaa");
		 * map.put("aaaa", "aaaa"); map.put("aaaaa", "aaaaa"); map.put("aaaaaa",
		 * "aaaaaa");
		 * 
		 * map2.put("a", "b"); map2.put("b", "b"); map2.put("c", "c");
		 * map2.put("d", "d");
		 * 
		 * 
		 * map.putAll(map2);
		 * 
		 * 
		 * Set<Entry<String,String>> entrySet = map.entrySet();
		 * 
		 * Iterator<Entry<String, String>> it = entrySet.iterator();
		 * 
		 * while (it.hasNext()) { Entry<String, String> next = it.next();
		 * System.out.println("key is :"+next.getKey()+"   value is :"
		 * +next.getValue()); }
		 * System.out.println("///////////////////////////////////////////////")
		 * ; for (Entry<String, String> entry : entrySet) { System.out.println(
		 * "key is :"+entry.getKey()+"   value is :"+entry.getValue()); }
		 */
	}

	public static void testHashMap() {
		long start = System.currentTimeMillis();
		HashMap<Object, Object> map1 = new HashMap<>();
		for (int i = 0; i < 1000000; i++) {
			map1.put("a" + i, "a" + i);
		}
		System.out.println("hashMap  put执行时间：" + (System.currentTimeMillis() - start));
		// long mid = System.currentTimeMillis();
		// Set<Entry<Object,Object>> entrySet = map.entrySet();
		// for (Entry<Object, Object> entry : entrySet) {
		// System.out.println(entry.getKey()+ " "+entry.getValue());
		// }
		// System.out.println("hashMap
		// get执行时间："+(System.currentTimeMillis()-mid));
	}

	public static void testHashTable() {
		long start = System.currentTimeMillis();
		Hashtable<Object, Object> map2 = new Hashtable<>();
		for (int i = 0; i < 1000000; i++) {
			map2.put("a" + i, "a" + i);
		}
		System.out.println("hashTable  put执行时间：" + (System.currentTimeMillis() - start));

		// long mid = System.currentTimeMillis();
		// Set<Entry<Object,Object>> entrySet = map.entrySet();
		// for (Entry<Object, Object> entry : entrySet) {
		// System.out.println(entry.getKey()+ " "+entry.getValue());
		// }
		// System.out.println("hashTable
		// get执行时间："+(System.currentTimeMillis()-mid));

	}

	public static void testConcurrentHashMap() {
		long start = System.currentTimeMillis();
		ConcurrentMap<String, String> map3 = new ConcurrentHashMap<String, String>();
		for (int i = 0; i < 1000000; i++) {
			map3.put("a" + i, "a" + i);
		}
		System.out.println("concurrentHashMap  put执行时间：" + (System.currentTimeMillis() - start));

		// long mid = System.currentTimeMillis();
		// Set<Entry<String, String>> entrySet = map.entrySet();
		// for (Entry<String, String> entry : entrySet) {
		// System.out.println(entry.getKey()+ " "+entry.getValue());
		// }
		// System.out.println("concurrentHashMap
		// get执行时间："+(System.currentTimeMillis()-mid));

	}
	
	
	
	
	/*@Before
	public void a2a(){
		
		System.out.println(System.currentTimeMillis());
	}
	
	@Test
	public void a3a(){
		System.out.println("ahha");
	}
	@After
	public void a4a(){
		System.out.println(System.currentTimeMillis());
	}*/

	@Test
	public void t4() {

		long start = System.currentTimeMillis();

		testHashMap();

		testHashTable();

		testConcurrentHashMap();

		System.out.println("总时间：" + (System.currentTimeMillis() - start));
	}

}
