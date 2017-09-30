package com.test.util;
import java.security.MessageDigest;

/**
 * Project Name:ExtTest
 * File Name:Md5Util.java
 * Package Name:
 * Date:2017年8月23日上午9:50:55
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

/**
 * ClassName:Md5Util <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月23日 上午9:50:55 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Md5Util {
	public final static String MD5(String s){
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
				'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for(int i=0; i<j; i++){
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0  & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

}

