/**
 * Project Name:aTest
 * File Name:UserVo.java
 * Package Name:com.test.vo
 * Date:2017年8月11日下午3:08:48
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.util.vo;

/**
 * ClassName:UserVo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年8月11日 下午3:08:48 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.7
 * @see
 */
public class UserVo {
	private String userName;
	private int startPage;
	private int pageSize;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public UserVo() {

		super();
		// TODO Auto-generated constructor stub

	}

	public UserVo(String userName, int startPage, int pageSize) {
		super();
		this.userName = userName;
		this.startPage = startPage;
		this.pageSize = pageSize;
	}

}
