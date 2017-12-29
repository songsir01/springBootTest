/**
 * Project Name:SpringBootTest
 * File Name:ThreadLocalDemo.java
 * Package Name:com.test.controller
 * Date:2017年12月25日下午5:47:38
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.controller;

import java.util.Random;

/**
 * ClassName:ThreadLocalDemo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年12月25日 下午5:47:38 <br/>
 * 
 * @author SongYapeng
 * @version
 * @since JDK 1.7
 * @see
 */
public class ThreadLocalDemo implements Runnable {
	
	private final static ThreadLocal<TestStudent> studentThreadLocal = new ThreadLocal<TestStudent>();
	
	private TestStudent student = new TestStudent();
	
//	static Map<Object,Object> map = new HashMap<>();

	@Override
	public void run() {
		accessStudent();
	}

	private void accessStudent() {
		studentThreadLocal.set(student);
//		TestStudent student = new TestStudent();
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(currentThreadName + "is running");

		Random random = new Random();
		int age = random.nextInt(100);
		System.out.println("thread " + currentThreadName + " set age to:" + age);
//		TestStudent student = getStudent();
//		TestStudent student = new TestStudent();

		student.setAge(age);
		System.out.println("thread " + currentThreadName + " first read age is:" + studentThreadLocal.get().getAge());

//		map.put(currentThreadName, studentThreadLocal.get().getAge());
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("thread " + currentThreadName + " second read age is:" +  studentThreadLocal.get().getAge());*/

	}

	/*private TestStudent getStudent() {
		TestStudent student = (TestStudent) studentThreadLocal.get();
		if (student == null) {
			student = new TestStudent();
			studentThreadLocal.set(student);
		}
		return student;
	}*/

	

	public static void main(String[] args) {
		
		ThreadLocalDemo td = new ThreadLocalDemo();
		
		Thread t1 = new Thread(td, "a");
		Thread t2 = new Thread(td, "b");
		Thread t3 = new Thread(td, "c");
		Thread t4 = new Thread(td, "d");
		Thread t5 = new Thread(td, "e");
		Thread t6 = new Thread(td, "f");
		Thread t7 = new Thread(td, "g");
		Thread t8 = new Thread(td, "h");
		Thread t9 = new Thread(td, "i");
		Thread t10 = new Thread(td, "j");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		
		

	}

}
