/**
 * Project Name:SpringBootTest
 * File Name:SerializeUtil.java
 * Package Name:com.test.util
 * Date:2017年12月28日下午5:45:03
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

/**
 * ClassName:SerializeUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年12月28日 下午5:45:03 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Component
public class SerializeUtil<E> {

	public String serialize(E object) {
		
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			
			String str = baos.toString("ISO-8859-1");
			return URLEncoder.encode(str,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				baos.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public E unserialize(String serializeStr){
		String readStr = "";
		if(serializeStr == null || "".equals(serializeStr)){
			return null;
		}
		try {
			readStr = URLDecoder.decode(serializeStr,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ObjectInputStream ois = null;
		InputStream  bais = null;
		try {
			bais = new ByteArrayInputStream(readStr.getBytes("ISO-8859-1"));
			ois = new ObjectInputStream(bais);
			return (E) ois.readObject();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
				bais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

