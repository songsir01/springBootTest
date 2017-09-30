/**
 * Project Name:SpringBootTest
 * File Name:TestUtil.java
 * Package Name:com.test.testutil
 * Date:2017年9月15日下午3:16:23
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.testutil;
/**
 * ClassName:TestUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年9月15日 下午3:16:23 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TestUtil {
	
	
	public static int parttion(int [] array,int lo,int hi){
		int key=array[lo];
		while(lo<hi){
			while(array[hi]>key && hi>lo){
				hi--;
			}
			array[lo]=array[hi];
			while(array[lo]<=key && hi>lo){
				lo++;
			}
			array[hi]=array[lo];
			
		}
		array[hi]=key;
		
		return hi;
		
	}
	
	public static void main(String[] args) {
		String aString="来珍另一个的胸膛，tell me to your heart,tell me to your过一群手动你姐姐，，第三个】[p[e[vbaafopa";
		char[] charArray = aString.toCharArray();
		int 字母=0;
		int 数字=0;
		int 空格=0;
		int 其他字符=0;
		for (char c : charArray) {
			if(Character.isLetter(c)){
				字母++;
			}else if(Character.isSpace(c)){
				空格++;
			}else if(Character.isDigit(c)){
				数字++;
			}else{
				其他字符++;
			}
		}
		System.out.println("字母："+字母+"\n");
		System.out.println("数字："+数字+"\n");
		System.out.println("空格："+空格+"\n");
		System.out.println("其他字符："+其他字符+"\n");
	}
	
	
	

}

