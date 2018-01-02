/**
 * Project Name:SpringBootTest
 * File Name:Permission.java
 * Package Name:com.test.pojo
 * Date:2017年8月28日下午1:34:29
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.pojo;
/**
 * ClassName:Permission <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月28日 下午1:34:29 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Permission {
	
	private int pid;
	
	private String pname;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Permission() {
		
		super();
		// TODO Auto-generated constructor stub
		
	}

	public Permission(int pid, String pname) {
		super();
		this.pid = pid;
		this.pname = pname;
	}

	@Override
	public String toString() {
		return "Permission [pid=" + pid + ", pname=" + pname + "]";
	}
	
	

}

