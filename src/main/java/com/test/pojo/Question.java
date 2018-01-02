/**
 * Project Name:SpringBootTest
 * File Name:Question.java
 * Package Name:com.test.pojo
 * Date:2017年8月25日下午12:00:26
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.pojo;
/**
 * ClassName:Question <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月25日 下午12:00:26 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Question {
	
	private int qid;
	
	private String qname;

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public Question() {
		
		super();
		// TODO Auto-generated constructor stub
		
	}

	public Question(int qid, String qname) {
		super();
		this.qid = qid;
		this.qname = qname;
	}
	

}

