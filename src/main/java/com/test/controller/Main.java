/**
 * Project Name:SpringBootTest
 * File Name:Main.java
 * Package Name:com.test.controller
 * Date:2017年9月14日下午1:01:20
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.controller;
/**
 * ClassName:Main <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年9月14日 下午1:01:20 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Main{
	public static void main(String[] args) {
		String a="123";
		String b=new String(a);
		String c = b.intern();
		System.out.println(a==b); //false
		System.out.println(a==c); //true
		System.out.println(b==c); //false
	}
}

