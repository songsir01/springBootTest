/**
 * Project Name:SpringBootTest
 * File Name:User.java
 * Package Name:com.test.pojo
 * Date:2017年8月24日下午5:44:07
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.pojo;
/**
 * ClassName:User <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月24日 下午5:44:07 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class User {
	
	private int uid;
	
	private String username;
	
	private String sex;
	
	private String password;
	
	private String description;
	
	private String img;
	
	private int rid;
	
	private Role role;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int uid, String username, String sex, String password,
			String description, String img, int rid, Role role) {
		super();
		this.uid = uid;
		this.username = username;
		this.sex = sex;
		this.password = password;
		this.description = description;
		this.img = img;
		this.rid = rid;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", sex=" + sex
				+ ", password=" + password + ", description=" + description
				+ ", img=" + img + ", rid=" + rid + ", role=" + role + "]";
	}
	
	

	



}

